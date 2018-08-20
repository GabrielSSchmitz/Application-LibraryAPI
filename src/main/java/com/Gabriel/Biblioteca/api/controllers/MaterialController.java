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
import com.Gabriel.Biblioteca.api.entities.Material;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.MaterialService;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

	private static final Logger log = LoggerFactory.getLogger(MaterialController.class);

	@Autowired
	private MaterialService service;

	/**
	 * 
	 * Consulta todos os materias
	 * 
	 * @return List<MaterialDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<MaterialDTO>>> listaTodos() {
		Response<List<MaterialDTO>> response = new Response<List<MaterialDTO>>();

		List<MaterialDTO> autorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		response.setData(autorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Material atravez do código
	 * 
	 * @param codigo
	 * @return Material ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<MaterialDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<MaterialDTO> response = new Response<>();
		Optional<Material> entity = service.findByCodigo(codigo);

		if (!entity.isPresent()) {
			log.error("Código de material não cadastrado na base de dados");
			response.getErrors().add("Código de material não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(converteEntityParaDTO(entity.get()));

		log.info("Consulta do material {}", response.getData());

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<MaterialDTO>> cadatrar(@Valid @RequestBody MaterialDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando material {}", DTO.toString());

		Response<MaterialDTO> response = new Response<>();

		validaSeExiste(DTO, result);
		Material entity = this.converteDTOParaEntity(DTO);
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
	private void validaSeExiste(MaterialDTO dTO, BindingResult result) {
		this.service.findByCodigo(dTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("Material", dTO.getNome() + " já existe")));

	}

	/**
	 * 
	 * Converte DTO para Entidade
	 * 
	 * @param periodicoDTO
	 * @return
	 */
	private Material converteDTOParaEntity(MaterialDTO convertFor) {
		Material convert = new Material();

		convert.setCodigo(convertFor.getCodigo());
		convert.setDescricao(convertFor.getDescricao());
		convert.setEstante(convertFor.getEstante());
		convert.setId(convertFor.getId());
		convert.setMaterial(convertFor.getMaterial());
		convert.setNome(convertFor.getNome());
		convert.setQuantidade(convertFor.getQuantidade());
		convert.setQuantidadeEmprestimo(convertFor.getQuantidadeEmprestimo());
		convert.setTipo(convertFor.getTipo());

		return convert;
	}

	/**
	 * Converte Entidade para DTO
	 * 
	 * @param material
	 * @return DTO
	 */
	private MaterialDTO converteEntityParaDTO(Material convertFor) {
		MaterialDTO convert = new MaterialDTO();

		convert.setCodigo(convertFor.getCodigo());
		convert.setDescricao(convertFor.getDescricao());
		convert.setEstante(convertFor.getEstante());
		convert.setId(convertFor.getId());
		convert.setMaterial(convertFor.getMaterial());
		convert.setNome(convertFor.getNome());
		convert.setQuantidade(convertFor.getQuantidade());
		convert.setQuantidadeEmprestimo(convertFor.getQuantidadeEmprestimo());
		convert.setTipo(convertFor.getTipo());

		return convert;
	}
}
