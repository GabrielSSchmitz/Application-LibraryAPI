package com.Gabriel.Biblioteca.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Livro;
@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "PeriodicoRepository.findByCodigo", query = "SELECT * FROM Periodico WHERE codigo = :codigo") })
public interface LivroRepository extends JpaRepository<Livro, Integer>{

	Livro findByCodigo(@Param("codigo") String codigo);
}
