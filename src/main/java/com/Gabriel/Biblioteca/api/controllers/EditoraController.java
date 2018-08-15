package com.Gabriel.Biblioteca.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<EditoraDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<EditoraDTO> response = new Response<EditoraDTO>();
		Optional<Editora> editora = editoraService.findByCodigo(codigo);

		if (!editora.isPresent()) {
			log.error("Código de editora não cadastrado na base de dados");
			response.getErrors().add("Código da editora não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

}