package com.Gabriel.Biblioteca.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Editora;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "AutorRepository.procuraPorCodigo", query = "SELECT * FROM Editora WHERE codigo = :codigo") })
public interface EditoraRepository extends JpaRepository<Editora, Integer> {

	Editora findByCodigo(@Param("codigo") String codigo);
}
