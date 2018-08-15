package com.Gabriel.Biblioteca.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Autor;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "AutorRepository.findByCodigo", query = "SELECT * FROM Autor WHERE codigo = :codigo")})
public interface AutorRepository extends JpaRepository<Autor, Long> {

	Autor findByCodigo(@Param("codigo") String codigo);

	List<Autor> findAll();
}
