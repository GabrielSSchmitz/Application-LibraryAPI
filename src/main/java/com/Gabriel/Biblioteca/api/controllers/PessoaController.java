package com.Gabriel.Biblioteca.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gabriel.Biblioteca.api.dtos.PessoaDTO;
import com.Gabriel.Biblioteca.api.entities.Pessoa;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	PessoaService service;

	@GetMapping
	public ResponseEntity<Response<List<PessoaDTO>>> listaTodos() {
		Response<List<PessoaDTO>> response = new Response<List<PessoaDTO>>();

		List<PessoaDTO> autorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		response.setData(autorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de pessoa atravez do CPF
	 * 
	 * @param cpf
	 * @return
	 */
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<Response<PessoaDTO>> consulta(@PathVariable("cpf") String cpf) {

		Response<PessoaDTO> response = new Response<>();
		Optional<Pessoa> pessoa = service.findbyCPF(cpf);

		if (!pessoa.isPresent()) {
			log.error("Código de pessoa não cadastrado na base de dados");
			response.getErrors().add("Código de pessoa não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		PessoaDTO pessoaDTO = converteEntityParaDTO(pessoa.get());
		response.setData(pessoaDTO);

		log.info("Consulta do usuario {}", pessoaDTO);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<PessoaDTO>> cadastrar(@Valid @RequestBody PessoaDTO pessoaDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando pessoa {}", pessoaDTO.toString());

		Response<PessoaDTO> response = new Response<>();
		validaSePessoaExiste(pessoaDTO, result);
		Pessoa pessoa = converteDTOParaEntity(pessoaDTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informalções: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(pessoa);
		response.setData(converteEntityParaDTO(service.findbyCPF(pessoa.getCpf()).get()));
		return ResponseEntity.ok(response);
	}

	private void validaSePessoaExiste(PessoaDTO pessoaDTO, BindingResult result) {
		this.service.findbyCPF(pessoaDTO.getCpf())
				.ifPresent(aut -> result.addError(new ObjectError("pessoa", pessoaDTO.getNome() + " já existe")));
	}

	/**
	 * Converte Entity em DTO
	 * 
	 * @param pessoa
	 * @return DTO
	 */
	private PessoaDTO converteEntityParaDTO(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setCpf(pessoa.getCpf());
		pessoaDTO.setNome(pessoa.getNome());
		pessoaDTO.setTelefone(pessoa.getTelefone());
		pessoaDTO.setId(pessoa.getId());
		return pessoaDTO;
	}

	/**
	 * Converte DTO em Entity
	 * 
	 * @param pessoaDTO
	 * @return Entity
	 */
	private Pessoa converteDTOParaEntity(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setTelefone(pessoaDTO.getTelefone());
		pessoa.setId(pessoaDTO.getId());
		return pessoa;
	}
}
