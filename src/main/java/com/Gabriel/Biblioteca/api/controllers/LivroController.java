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

import com.Gabriel.Biblioteca.api.dtos.EditoraDTO;
import com.Gabriel.Biblioteca.api.dtos.LivroDTO;
import com.Gabriel.Biblioteca.api.dtos.PeriodicoDTO;
import com.Gabriel.Biblioteca.api.entities.Livro;
import com.Gabriel.Biblioteca.api.entities.Periodico;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	LivroService service;

	@GetMapping
	public ResponseEntity<Response<List<LivroDTO>>> listaTodos() {
		Response<List<LivroDTO>> response = new Response<List<LivroDTO>>();

		List<LivroDTO> autorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		response.setData(autorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de editora atravez do código
	 * 
	 * @param codigo
	 * @return Editora ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<LivroDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<LivroDTO> response = new Response<>();
		Optional<Livro> entity = service.findByCodigo(codigo);

		// =========================================================================================================

		if (!entity.isPresent()) {
			log.error("Código de periodico não cadastrado na base de dados");
			response.getErrors().add("Código de periodico não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converteEntityParaDTO(entity.get()));

		log.info("Consulta do periodico {}", response.getData());

		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra periodico no banco de dados
	 * 
	 * @param periodicoDTO
	 * @return ResponseEntity.ok
	 */
	@PostMapping
	public ResponseEntity<Response<LivroDTO>> cadatrar(@Valid @RequestBody LivroDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando periodico {}", DTO.toString());
		Response<LivroDTO> response = new Response<>();

		// =========================================================================================================

		validaSeExiste(DTO, result);
		Livro entity = this.converteDTOParaEntity(DTO);
		entity.setQuantidadeEmprestimo(entity.getQuantidade());

		if (result.hasErrors()) {
			log.error("Erro ao validar informalções: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(entity);
		response.setData(this.converteEntityParaDTO(entity));
		return ResponseEntity.ok(response);

	}

	/**
	 * 
	 * Valida se a entidade existe no bando de dados
	 * 
	 * @param dTO
	 * @param result
	 */
	private void validaSeExiste(@Valid LivroDTO dTO, BindingResult result) {
		this.service.findByCodigo(dTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("livro", dTO.getNome() + " já existe")));

	}

	public Livro converteDTOParaEntity(LivroDTO convertFor) {
		Livro convert = new Livro();

		convert.setCodigo(convertFor.getCodigo());
		convert.setData(convertFor.getData());
		convert.setEdicao(convertFor.getEdicao());
		convert.setId_autor(convertFor.getId_autor());
		convert.setId_editora(convertFor.getId_editora());
		convert.setNome(convertFor.getNome());
		convert.setQuantidade(convertFor.getQuantidade());
		convert.setQuantidadeEmprestimo(convertFor.getQuantidadeEmprestimo());
		convert.setTipo(convertFor.getTipo());

		return convert;
	}

	public LivroDTO converteEntityParaDTO(Livro convertFor) {
		LivroDTO convert = new LivroDTO();

		convert.setCodigo(convertFor.getCodigo());
		convert.setData(convertFor.getData());
		convert.setEdicao(convertFor.getEdicao());
		convert.setId_autor(convertFor.getId_autor());
		convert.setId_editora(convertFor.getId_editora());
		convert.setNome(convertFor.getNome());
		convert.setQuantidade(convertFor.getQuantidade());
		convert.setQuantidadeEmprestimo(convertFor.getQuantidadeEmprestimo());
		convert.setTipo(convertFor.getTipo());

		return convert;
	}

}
