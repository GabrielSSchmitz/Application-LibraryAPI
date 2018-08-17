package com.Gabriel.Biblioteca.api.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialDTO {

	private int id;
	private String codigo;
	private int tipo = 0;
	private String nome = "";

//	----------------------------

	private String descricao;
	private String material;
	private String estante;
	private int quantidade;
	private int quantidadeEmprestimo;

	public MaterialDTO() {
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

	@Min(value = 1, message = "Tipo não deve ser nulo")
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

	@NotEmpty(message = "Descrição não deve ser vazio")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotEmpty(message = "Material não deve ser vazio")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@NotEmpty(message = "Estante não deve ser vazio")
	@NotNull
	public String getEstante() {
		return estante;
	}

	public void setEstante(String estante) {
		this.estante = estante;
	}

	@Min(value = 1, message = "Quantidade não deve ser nulo")
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
		return "MaterialDTO [id=" + id + ", codigo=" + codigo + ", tipo=" + tipo + ", nome=" + nome + ", descricao="
				+ descricao + ", material=" + material + ", estante=" + estante + ", quantidade=" + quantidade
				+ ", quantidadeEmprestimo=" + quantidadeEmprestimo + "]";
	}

}
