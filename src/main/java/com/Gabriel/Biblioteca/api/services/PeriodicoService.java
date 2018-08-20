package com.Gabriel.Biblioteca.api.services;

import java.util.List;
import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Periodico;

public interface PeriodicoService {

	Optional<Periodico> findByCodigo(String codigo);

	Periodico persistir(Periodico periodico);

	List<Periodico> findAll();
}
