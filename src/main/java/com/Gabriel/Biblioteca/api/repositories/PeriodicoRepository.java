package com.Gabriel.Biblioteca.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Periodico;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "PeriodicoRepository.findByCodigo", query = "SELECT * FROM Periodico WHERE codigo = :codigo") })
public interface PeriodicoRepository extends JpaRepository<Periodico, Integer> {

	Periodico findByCodigo(@Param("codigo") String codigo);
}
