package com.Gabriel.Biblioteca.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Gabriel.Biblioteca.api.entities.Pessoa;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "PessoaRepository.findByCpf", query = "SELECT * FROM Pessoa WHERE cpf = :cpf")})
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findByCpf(@Param("cpf") String cpf);
}
