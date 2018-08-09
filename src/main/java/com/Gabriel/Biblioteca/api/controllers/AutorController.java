package com.Gabriel.Biblioteca.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

	@GetMapping(value= "/{codigo}")
	public String consulta(@PathVariable("codigo") String codigo) {
		return "Código apresentado: " + codigo;
	}
}
