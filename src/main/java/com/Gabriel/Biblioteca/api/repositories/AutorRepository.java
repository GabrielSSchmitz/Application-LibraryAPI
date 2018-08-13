package com.Gabriel.Biblioteca.api.repositories;



import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Autor;

@Transactional (readOnly = true)
@NamedQueries({
	@NamedQuery(name = "AutorRepository.findByCodigo", query = "SELECT * FROM `autor` WHERE `codigo`= ':codigo'") })

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	Autor findByCodigo(@Param("codigo") String codigo);
	
}
