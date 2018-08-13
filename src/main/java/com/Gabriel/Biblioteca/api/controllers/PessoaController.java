package com.Gabriel.Biblioteca.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gabriel.Biblioteca.api.dtos.PessoaDTO;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	@GetMapping(value = "/{codigo}")
	public String consulta(@PathVariable("codigo") String codigo) {
		return "Código apresentado: " + codigo;
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> cadastrar(@RequestBody PessoaDTO pessoaDTO) {

		return ResponseEntity.ok(pessoaDTO);
	}
}