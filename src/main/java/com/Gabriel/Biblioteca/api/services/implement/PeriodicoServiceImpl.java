package com.Gabriel.Biblioteca.api.services.implement;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gabriel.Biblioteca.api.entities.Periodico;
import com.Gabriel.Biblioteca.api.repositories.PeriodicoRepository;
import com.Gabriel.Biblioteca.api.services.PeriodicoService;

@Service
public class PeriodicoServiceImpl implements PeriodicoService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	private PeriodicoRepository repository;

	@Override
	public Optional<Periodico> findByCodigo(String codigo) {
		log.info("Buscando periodico pelo codigo {}", codigo);
		return Optional.ofNullable(repository.findByCodigo(codigo));
	}

	@Override
	public Periodico persistir(Periodico periodico) {
		log.info("Cadastrando periodico: {}", periodico.toString());
		return repository.save(periodico);
	}

	@Override
	public List<Periodico> findAll() {
		log.info("Procurando todos os periodicos");
		return repository.findAll();
	}

}
