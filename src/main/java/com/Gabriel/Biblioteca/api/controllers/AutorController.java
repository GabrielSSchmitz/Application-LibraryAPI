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

import com.Gabriel.Biblioteca.api.dtos.AutorDTO;
import com.Gabriel.Biblioteca.api.entities.Autor;
import com.Gabriel.Biblioteca.api.response.Response;
import com.Gabriel.Biblioteca.api.services.AutorService;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private AutorService autorService;

//	@GetMapping
//	public ResponseEntity<Response<AutorDTO>> listaAutores() {
//		Response<AutorDTO> response = new Response<AutorDTO>();
//
//		response.setData(autorService.findAll());
//		
//		return ResponseEntity.ok(response);
//	}

	/**
	 * 
	 * Consulta de autor atravez do código
	 * 
	 * @param codigo
	 * @return Autor ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<AutorDTO>> consulta(@PathVariable("codigo") String codigo) {

		Response<AutorDTO> response = new Response<AutorDTO>();
		Optional<Autor> autor = autorService.findByCodigo(codigo);

		if (!autor.isPresent()) {
			log.error("Código de autor não cadastrado na base de dados");
			response.getErrors().add("Código de autor não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AutorDTO autorDTO = converteAutorParaDTO(autor.get());

		response.setData(autorDTO);

		log.info("Consulta do autor {}", autorDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo autor na base de dados
	 * 
	 * @param autorDTO
	 * @param result
	 * @return Autor
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<AutorDTO>> cadatrar(@Valid @RequestBody AutorDTO autorDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando autor {}", autorDTO.toString());

		Response<AutorDTO> response = new Response<>();
		validaSeAutorExiste(autorDTO, result);
		Autor autor = this.converteDTOParaAutor(autorDTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informalções: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.autorService.persistir(autor);
		response.setData(this.converteAutorParaDTO(autor));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param autorDTO
	 * @return Entity
	 */
	private Autor converteDTOParaAutor(AutorDTO autorDTO) {
		Autor autor = new Autor();
		autor.setCodigo(autorDTO.getCodigo());
		autor.setNome(autorDTO.getNome());
		autor.setSobrenome(autorDTO.getSobrenome());
		return autor;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param autor
	 * @return DTO
	 */
	private AutorDTO converteAutorParaDTO(Autor autor) {
		AutorDTO autorDTO = new AutorDTO();
		autorDTO.setCodigo(autor.getCodigo());
		autorDTO.setNome(autor.getNome());
		autorDTO.setSobrenome(autor.getSobrenome());
		autorDTO.setId(autor.getId());
		return autorDTO;
	}

	/**
	 * 
	 * Valida se o Autor ja existe na base de dados
	 * 
	 * @param autorDTO
	 * @param result
	 */
	private void validaSeAutorExiste(AutorDTO autorDTO, BindingResult result) {
		this.autorService.findByCodigo(autorDTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("autor", autorDTO.getNome() + " já existe")));

	}
//
//	private List<AutorDTO> converteListaParaListaDTO(List<Autor> autors) {
//		List<AutorDTO> autorDTO = new ArrayList<>();
//
//		autors.forEach(autor -> autorDTO.add(converteAutorParaDTO(autor)));
//		return autorDTO;
//
//	}
}
