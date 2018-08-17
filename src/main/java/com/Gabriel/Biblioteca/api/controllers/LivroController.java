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

import com.Gabriel.Biblioteca.api.dtos.LivroDTO;
import com.Gabriel.Biblioteca.api.entities.Livro;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.EditoraService;
import com.Gabriel.Biblioteca.api.services.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private LivroService service;

	@Autowired
	private EditoraService serviceEditora;


	/**
	 * 
	 * Consulta periodico no banco atravez do codigo
	 * 
	 * @param codigo
	 * @return ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<LivroDTO>> consulta(@PathVariable("codigo") String codigo) {
		Response<LivroDTO> response = new Response<>();
		Optional<Livro> entity = service.findByCodigo(codigo);

		// =========================================================================================================

		if (!entity.isPresent()) {
			log.error("Código de livro não cadastrado na base de dados");
			response.getErrors().add("Código de livro não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(converteEntityParaDTO(entity.get()));

		log.info("Consulta do periodico {}", response.getData());

		return ResponseEntity.ok(response);
	}

	private LivroDTO converteEntityParaDTO(Livro convert) {
		LivroDTO convertTo = new LivroDTO();

		convertTo.setId(convert.getId());
		convertTo.setCodigo(convert.getCodigo());
		convertTo.setTipo(convert.getTipo());
		convertTo.setNome(convert.getNome());
		convertTo.setData(convert.getData());
		convertTo.setEdicao(convert.getEdicao());
		convertTo.setVolume(convert.getVolume());
		convertTo.setEditora(serviceEditora.findById(convert.getEditora()).get());
		convertTo.setAutores(convert.getAutores());
		convertTo.setQuantidade(convert.getQuantidade());
		convertTo.setQuantidadeEmprestimo(convert.getQuantidadeEmprestimo());
		return convertTo;
	}

}
