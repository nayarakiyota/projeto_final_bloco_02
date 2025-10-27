package com.generation.farmacia.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
	private Long id;
	
	// Tipo da categoria (Ex: Medicamentos, Cosmeticos, Higiene, etc)
	@NotBlank(message = "O atributo tipo é obrigatório!")
	private String tipo;
	
	// Relação OneToMany com Produto
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
	
