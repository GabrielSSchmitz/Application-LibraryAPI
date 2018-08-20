package com.Gabriel.Biblioteca.api.services;

import java.util.List;
import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Periodico;
import com.Gabriel.Biblioteca.api.entities.Pessoa;

public interface PessoaService {

	Optional<Pessoa> findbyCPF(String CPF);

	Pessoa persistir(Pessoa pessoa);

	List<Pessoa> findAll();
}
