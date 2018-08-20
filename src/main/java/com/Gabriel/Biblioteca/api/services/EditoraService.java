package com.Gabriel.Biblioteca.api.services;

import java.util.List;
import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Editora;

public interface EditoraService {

	/**
	 * Procura editora pelo código
	 * 
	 * @param codigo
	 * @return Optional<Editora>
	 */
	Optional<Editora> findByCodigo(String codigo);

	/**
	 * Salva a editora na base de dados
	 * 
	 * @param editora
	 * @return Editora
	 */
	Editora persistir(Editora editora);

	Optional<Editora> findById(int id);

	List<Editora> findAll();
}
