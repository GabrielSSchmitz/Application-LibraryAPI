package com.Gabriel.Biblioteca.api.services.implement;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gabriel.Biblioteca.api.entities.Material;
import com.Gabriel.Biblioteca.api.repositories.MaterialRepository;
import com.Gabriel.Biblioteca.api.services.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	private MaterialRepository repository;

	@Override
	public Optional<Material> findByCodigo(String codigo) {
		log.info("Buscando material pelo codigo {}", codigo);
		return Optional.ofNullable(repository.findByCodigo(codigo));
	}

	@Override
	public Material persistir(Material material) {
		log.info("Cadastrando material: {}", material.toString());
		return repository.save(material);
	}

	@Override
	public List<Material> findAll() {
		log.info("Procurando todos os materiais");
		return repository.findAll();
	}

}
