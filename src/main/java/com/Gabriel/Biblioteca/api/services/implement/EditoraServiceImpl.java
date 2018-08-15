package com.Gabriel.Biblioteca.api.services.implement;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gabriel.Biblioteca.api.entities.Editora;
import com.Gabriel.Biblioteca.api.repositories.EditoraRepository;
import com.Gabriel.Biblioteca.api.services.EditoraService;

@Service
public class EditoraServiceImpl implements EditoraService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	EditoraRepository editoraRepository;

	@Override
	public Optional<Editora> findByCodigo(String codigo) {
		log.info("Buscando Editora pelo codigo {}", codigo);
		return Optional.ofNullable(editoraRepository.findByCodigo(codigo));
	}

	@Override
	public Editora persistir(Editora editora) {
		log.info("Cadastrando autor: {}", editora);
		return this.editoraRepository.save(editora);
	}

}
