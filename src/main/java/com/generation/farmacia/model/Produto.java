package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
	private Long id;
	
	//Nome do Produto
	@Column(length = 100)
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
	private String nome;
	
	//Descrição do Produto
	@Column(length = 255)
	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
	private String descricao;
	
	// Fabricante do Produto
	@Column(length = 50)
	@NotBlank(message = "O atributo fabricante é obrigatório!")
	@Size(min = 2, max = 50, message = "O fabricante deve ter entre 2 e 50 caracteres")
	private String fabricante;
	
	// Preço do Produto
	@Column(nullable = false, precision = 10, scale = 2)
	@DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
	@Digits(integer = 10, fraction = 2, message = "O preço deve ter até 10 dígitos inteiros e 2 decimais.")
	@NotNull(message = "O preço do produto é obrigatório.")
	private BigDecimal preco;
	
	// Foto do Produto
	@Column(name = "foto_url", nullable = false)
	@NotBlank(message = "A foto do produto é obrigatória.")
	private String foto;
	
	// Quantidade em Estoque
	@Column(nullable = false)
	@NotNull(message = "A quantidade em estoque é obrigatória.")
	private Integer quantidade;
	
	// Validade do Produto
	@Column(name = "data_validade")
	@Future(message = "A data de validade deve ser futura.")
	private LocalDate dataValidade;

	// Relação ManyToOne com Categoria
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}