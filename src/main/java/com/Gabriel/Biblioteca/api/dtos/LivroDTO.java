package com.Gabriel.Biblioteca.api.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.Gabriel.Biblioteca.api.entities.Livro;

public class LivroDTO {

	private int id;
	private String codigo;
	private int tipo = 0;
	private String nome = "";
	private String data;
	private int edicao;
	private int volume;
	private int id_editora;
	private int id_autor;
	private int quantidade;
	private int quantidadeEmprestimo;

	// Definições dos metodos get

	public int getId() {
		return id;
	}

	@NotEmpty(message = "Código não deve ser vazio")
	public String getCodigo() {
		return codigo;
	}

	@Min(value = 1, message = "Tipo não deve ser nulo")
	public int getTipo() {
		return tipo;
	}

	@NotEmpty(message = "Nome não deve ser vazio")
	public String getNome() {
		return nome;
	}

	@NotEmpty(message = "Data não deve ser vazio")
	public String getData() {
		return data;
	}

	@Min(value = 1, message = "Edição não deve ser nulo")
	public int getEdicao() {
		return edicao;
	}

	@Min(value = 1, message = "Volume não deve ser nulo")
	public int getVolume() {
		return volume;
	}

	@Min(value = 1, message = "Quantidade não deve ser nulo")
	public int getQuantidade() {
		return quantidade;
	}

	public int getQuantidadeEmprestimo() {
		return quantidadeEmprestimo;
	}

	@Min(value = 1, message = "Editora não deve ser nulo")
	public int getId_editora() {
		return id_editora;
	}

	@Min(value = 1, message = "Autor não deve ser nulo")
	public int getId_autor() {
		return id_autor;
	}
	// Definições dos metodos Set

	public void setId(int id) {
		this.id = id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
		this.quantidadeEmprestimo = quantidadeEmprestimo;
	}

	public void setId_editora(int id_editora) {
		this.id_editora = id_editora;
	}

	public void setId_autor(int id_autor) {
		this.id_autor = id_autor;
	}

	// ===============================================================================

}
