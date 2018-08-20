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

import com.Gabriel.Biblioteca.api.dtos.MaterialDTO;
import com.Gabriel.Biblioteca.api.dtos.PeriodicoDTO;
import com.Gabriel.Biblioteca.api.entities.Periodico;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.PeriodicoService;

@RestController
@RequestMapping("/api/periodico")
public class PeriodicoController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private PeriodicoService service;

	@GetMapping
	public ResponseEntity<Response<List<PeriodicoDTO>>> listaTodos() {
		Response<List<PeriodicoDTO>> response = new Response<List<PeriodicoDTO>>();

		List<PeriodicoDTO> autorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		response.setData(autorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta periodico no banco atravez do codigo
	 * 
	 * @param codigo
	 * @return ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<PeriodicoDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<PeriodicoDTO> response = new Response<>();
		Optional<Periodico> entity = service.findByCodigo(codigo);

		// =========================================================================================================

		if (!entity.isPresent()) {
			log.error("Código de periodico não cadastrado na base de dados");
			response.getErrors().add("Código de periodico não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(converteEntityParaDTO(entity.get()));

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
	public ResponseEntity<Response<PeriodicoDTO>> cadatrar(@Valid @RequestBody PeriodicoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando periodico {}", DTO.toString());
		Response<PeriodicoDTO> response = new Response<>();

		// =========================================================================================================

		validaSeExiste(DTO, result);
		Periodico entity = this.converteDTOParaEntity(DTO);
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
	private void validaSeExiste(@Valid PeriodicoDTO dTO, BindingResult result) {
		this.service.findByCodigo(dTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("periodico", dTO.getNome() + " já existe")));

	}

	/**
	 * 
	 * Converte Entidade para DTO
	 * 
	 * @param periodico
	 * @return
	 */
	private PeriodicoDTO converteEntityParaDTO(Periodico periodico) {
		PeriodicoDTO periodicoDTO = new PeriodicoDTO();
		periodicoDTO.setCodigo(periodico.getCodigo());
		periodicoDTO.setId(periodico.getId());
		periodicoDTO.setNome(periodico.getNome());
		periodicoDTO.setQuantidade(periodico.getQuantidade());
		periodicoDTO.setQuantidadeEmprestimo(periodico.getQuantidadeEmprestimo());
		periodicoDTO.setTipo(periodico.getTipo());
		periodicoDTO.setVolume(periodico.getVolume());
		return periodicoDTO;
	}

	/**
	 * 
	 * Converte DTO para Entidade
	 * 
	 * @param periodicoDTO
	 * @return
	 */
	private Periodico converteDTOParaEntity(PeriodicoDTO periodicoDTO) {
		Periodico periodico = new Periodico();
		periodico.setCodigo(periodicoDTO.getCodigo());
		periodico.setId(periodicoDTO.getId());
		periodico.setNome(periodicoDTO.getNome());
		periodico.setQuantidade(periodicoDTO.getQuantidade());
		periodico.setQuantidadeEmprestimo(periodicoDTO.getQuantidadeEmprestimo());
		periodico.setTipo(periodicoDTO.getTipo());
		periodico.setVolume(periodicoDTO.getVolume());
		return periodico;
	}

}
