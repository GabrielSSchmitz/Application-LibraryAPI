package com.Gabriel.Biblioteca.api.services;

import java.util.List;
import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Autor;

public interface AutorService {

	Optional<Autor> findByCodigo(String codigo);

	Autor persistir(Autor autor);

	List<Autor> findAll();

}
