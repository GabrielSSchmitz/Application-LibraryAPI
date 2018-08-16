package com.Gabriel.Biblioteca.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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
import com.Gabriel.Biblioteca.api.entities.Editora;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.EditoraService;

@RestController
@RequestMapping("/api/editora")
public class EditoraController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	EditoraService editoraService;

	/**
	 * 
	 * Consulta de editora atravez do código
	 * 
	 * @param codigo
	 * @return Editora ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<EditoraDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<EditoraDTO> response = new Response<EditoraDTO>();
		Optional<Editora> editora = editoraService.findByCodigo(codigo);

		if (!editora.isPresent()) {
			log.error("Código de autor não cadastrado na base de dados");
			response.getErrors().add("Código de editora não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		EditoraDTO editoraDTO = converteEditoraParaDTO(editora.get());

		response.setData(editoraDTO);

		log.info("Consulta do autor {}", editoraDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra nova editora na base de dados
	 * 
	 * 
	 * @param editoraDTO
	 * @param result
	 * @return Editora ResponseEntity.ok
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<EditoraDTO>> cadastrar(@Valid @RequestBody EditoraDTO editoraDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando autor {}", editoraDTO.toString());

		Response<EditoraDTO> response = new Response<>();
		validaSeEditoraExiste(editoraDTO, result);
		Editora editora = converteDTOParaEditora(editoraDTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informalções: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.editoraService.persistir(editora);
		response.setData(editoraDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param editora
	 * @return DTO
	 */
	private EditoraDTO converteEditoraParaDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		editoraDTO.setCodigo(editora.getCodigo());
		editoraDTO.setNome(editora.getNome());
		editoraDTO.setNacional(editora.isNacional());
		editoraDTO.setId(editora.getId());
		return editoraDTO;
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param DTO
	 * @return editora
	 */
	private Editora converteDTOParaEditora(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		editora.setCodigo(editoraDTO.getCodigo());
		editora.setNome(editoraDTO.getNome());
		editora.setNacional(editoraDTO.isNacional());
		editora.setId(editoraDTO.getId());
		return editora;
	}

	/**
	 * 
	 * Valida se o Editora ja existe na base de dados
	 * 
	 * @param editoraDTO
	 * @param result
	 */
	private void validaSeEditoraExiste(EditoraDTO editoraDTO, BindingResult result) {
		this.editoraService.findByCodigo(editoraDTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("Editora", editoraDTO.getNome() + " já existe")));

	}

}