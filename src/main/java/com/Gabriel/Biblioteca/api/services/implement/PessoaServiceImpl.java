package com.Gabriel.Biblioteca.api.services.implement;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gabriel.Biblioteca.api.entities.Pessoa;
import com.Gabriel.Biblioteca.api.repositories.PessoaRepository;
import com.Gabriel.Biblioteca.api.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	PessoaRepository pessoaRepository;

	@Override
	public Optional<Pessoa> findbyCPF(String CPF) {
		log.info("Buscando pelo CPF {}", CPF);
		return Optional.ofNullable(this.pessoaRepository.findByCpf(CPF));
	}

	@Override
	public Pessoa persistir(Pessoa pessoa) {
		log.info("Cadastrando usuario: {}", pessoa.toString());
		return this.pessoaRepository.save(pessoa);
	}

}
