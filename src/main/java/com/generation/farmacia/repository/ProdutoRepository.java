package com.generation.farmacia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	// Buscar produtos por Id
    List<Produto> findAllById(Long id);
	
	// Buscar produtos pelo nome
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	// Buscar produto pelo tipo
	List<Produto> findByCategoria_TipoContainingIgnoreCase(String tipo);
	
	// Buscar produtos pelo fabricante
	List<Produto> findByFabricanteContainingIgnoreCase(String fabricante);
	
	// Buscar produtos pela descrição
	List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
	
	// Buscar produtos pela quantidade em estoque	
	List<Produto> findByQuantidadeGreaterThanEqual(Integer quantidade);
	
	// Buscar produtos pela data de validade antes de uma data específica
	List<Produto> findByDataValidadeBefore(LocalDate data);
	
	// Buscar produtos pela data de validade entre duas datas específicas
	List<Produto> findByDataValidadeBetween(LocalDate inicio, LocalDate fim);
	
	// Buscar produtos pela data de validade em uma data específica
	List<Produto> findByDataValidade(LocalDate data);
	
	// Listar produtos ordenados pela data de validade em ordem crescente
	List<Produto> findAllByOrderByDataValidadeAsc();

}
