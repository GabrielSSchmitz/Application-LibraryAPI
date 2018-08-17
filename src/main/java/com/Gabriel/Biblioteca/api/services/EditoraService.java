package com.Gabriel.Biblioteca.api.services;

import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Editora;

public interface EditoraService {

	/**
	 * Procura editora pelo código
	 * 
	 * @param codigo
	 * @return Optional<Editora>
	 */
	public Optional<Editora> findByCodigo(String codigo);

	/**
	 * Salva a editora na base de dados
	 * 
	 * @param editora
	 * @return Editora
	 */
	public Editora persistir(Editora editora);
	

	public Optional<Editora> findById(int id);
}
