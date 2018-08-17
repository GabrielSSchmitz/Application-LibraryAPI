package com.Gabriel.Biblioteca.api.dtos;

import java.util.ArrayList;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.Gabriel.Biblioteca.api.entities.Autor;
import com.Gabriel.Biblioteca.api.entities.Editora;

public class LivroDTO {

	private int id;
	private String codigo;
	private int tipo = 0;
	private String nome = "";
	private String data;
	private int edicao;
	private int volume;
	private Editora editora;
	private ArrayList<Autor> autores;
	private int quantidade;
	private int quantidadeEmprestimo;

	public LivroDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Código não deve ser vazio")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Min(value = 1, message = "Tipo não deve ser vazio")
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@NotEmpty(message = "Nome não deve ser vazio")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "Data não deve ser vazio")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Min(value = 1, message = "Edição não deve ser vazio")
	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	@Min(value = 1, message = "Volume não deve ser vazio")
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Min(value = 1, message = "Id editora não deve ser vazio")
	public int getEditora() {
		return editora.getId();
	}

	public void setEditora(int editora) {
		this.editora.setId(editora);
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	@NotEmpty(message = "Autores não deve ser vazio")
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	@Min(value = 1, message = "Quantidade não deve ser vazio")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeEmprestimo() {
		return quantidadeEmprestimo;
	}

	public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
		this.quantidadeEmprestimo = quantidadeEmprestimo;
	}

	@Override
	public String toString() {
		return "LivroDTO [id=" + id + ", codigo=" + codigo + ", tipo=" + tipo + ", nome=" + nome + ", data=" + data
				+ ", edicao=" + edicao + ", volume=" + volume + ", editora=" + editora + ", autores=" + autores
				+ ", quantidade=" + quantidade + ", quantidadeEmprestimo=" + quantidadeEmprestimo + "]";
	}

}
