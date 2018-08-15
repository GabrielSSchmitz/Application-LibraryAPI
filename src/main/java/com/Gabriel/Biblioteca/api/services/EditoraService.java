package com.Gabriel.Biblioteca.api.services;

import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Editora;

public interface EditoraService {



	public Optional<Editora> findByCodigo(String codigo);
	
	public Editora persistir (Editora editora);
}
