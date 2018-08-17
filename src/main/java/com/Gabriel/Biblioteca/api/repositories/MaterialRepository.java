package com.Gabriel.Biblioteca.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Material;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "MaterialRepository.findByCodigo", query = "SELECT * FROM Material WHERE codigo = :codigo") })
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	Material findByCodigo(@Param("codigo") String codigo);

}
