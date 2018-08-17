package com.Gabriel.Biblioteca.api.services;

import java.util.Optional;

import com.Gabriel.Biblioteca.api.entities.Material;

public interface MaterialService {

	Optional<Material> findByCodigo(String codigo);

	Material persistir(Material material);
}
