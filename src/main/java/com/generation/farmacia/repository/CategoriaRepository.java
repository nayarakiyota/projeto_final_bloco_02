package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	// Buscar categorias pelo tipo
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);

	// Buscar categorias pelo id
	public List<Categoria> findAllById(Long id);

}
