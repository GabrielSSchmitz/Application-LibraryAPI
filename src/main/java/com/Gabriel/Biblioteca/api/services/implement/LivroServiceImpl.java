package com.Gabriel.Biblioteca.api.services.implement;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gabriel.Biblioteca.api.entities.Livro;
import com.Gabriel.Biblioteca.api.repositories.LivroRepository;
import com.Gabriel.Biblioteca.api.services.LivroService;

@Service
public class LivroServiceImpl implements LivroService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	LivroRepository repository;

	@Override
	public Optional<Livro> findByCodigo(String codigo) {
		log.info("Buscando livro pelo codigo {}", codigo);
		return Optional.ofNullable(repository.findByCodigo(codigo));
	}

	@Override
	public Livro persistir(Livro livro) {
		log.info("Cadastrando livro: {}", livro);
		return repository.save(livro);
	}

	@Override
	public List<Livro> findAll() {
		log.info("Procurando todos os livros");
		return repository.findAll();
	}

}
