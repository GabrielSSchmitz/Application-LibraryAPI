package com.Gabriel.Biblioteca.api.services;

import java.util.List;
import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Livro;

public interface LivroService {

	Optional<Livro> findByCodigo(String codigo);

	Livro persistir(Livro livro);

	List<Livro> findAll();
}
