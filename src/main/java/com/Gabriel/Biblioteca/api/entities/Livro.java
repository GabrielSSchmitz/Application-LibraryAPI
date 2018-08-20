package com.Gabriel.Biblioteca.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.Gabriel.Biblioteca.api.dtos.LivroDTO;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3818399695794545918L;

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	@Column(name = "codigo")
	public String getCodigo() {
		return codigo;
	}

	@Column(name = "tipo")
	public int getTipo() {
		return tipo;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	@Column(name = "data")
	public String getData() {
		return data;
	}

	@Column(name = "edicao")
	public int getEdicao() {
		return edicao;
	}

	@Column(name = "volume")
	public int getVolume() {
		return volume;
	}

	@Column(name = "quantidade")
	public int getQuantidade() {
		return quantidade;
	}

	@Column(name = "quantidade_emprestimo")
	public int getQuantidadeEmprestimo() {
		return quantidadeEmprestimo;
	}

	@Column(name = "id_editora")
	public int getId_editora() {
		return id_editora;
	}

	@Column(name = "id_autor")
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
