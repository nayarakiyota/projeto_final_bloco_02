package com.generation.farmacia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// Método para listar todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// Método para listar produtos por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// Método para listar produtos por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// Método para listar produtos por tipo de categoria
	@GetMapping("/categoria/{tipo}")
	public ResponseEntity<List<Produto>> getByCategoriaTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(produtoRepository.findByCategoria_TipoContainingIgnoreCase(tipo));
	}

	// Método para listar produtos por fabricante
	@GetMapping("/fabricante/{fabricante}")
	public ResponseEntity<List<Produto>> getByFabricante(@PathVariable String fabricante) {
		return ResponseEntity.ok(produtoRepository.findByFabricanteContainingIgnoreCase(fabricante));
	}

	// Buscar por descrição
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(produtoRepository.findByDescricaoContainingIgnoreCase(descricao));
	}

	// Buscar por quantidade mínima
	@GetMapping("/estoque/{quantidade}")
	public ResponseEntity<List<Produto>> getByEstoque(@PathVariable Integer quantidade) {
		return ResponseEntity.ok(produtoRepository.findByQuantidadeGreaterThanEqual(quantidade));
	}

	// Buscar produtos vencidos
	@GetMapping("/vencidos")
	public ResponseEntity<List<Produto>> getProdutosVencidos() {
		return ResponseEntity.ok(produtoRepository.findByDataValidadeBefore(LocalDate.now()));
	}

	// Buscar produtos que vencem entre duas datas
	@GetMapping("/validade/entre")
	public ResponseEntity<List<Produto>> getProdutosPorValidade(@RequestParam LocalDate inicio,
			@RequestParam LocalDate fim) {
		return ResponseEntity.ok(produtoRepository.findByDataValidadeBetween(inicio, fim));
	}

	// Buscar produtos por data de validade exata
	@GetMapping("/validade/{data}")
	public ResponseEntity<List<Produto>> getProdutosPorData(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		return ResponseEntity.ok(produtoRepository.findByDataValidade(data));
	}

	// Listar produtos ordenados por validade
	@GetMapping("/validade/ordenado")
	public ResponseEntity<List<Produto>> getProdutosOrdenadosPorValidade() {
		return ResponseEntity.ok(produtoRepository.findAllByOrderByDataValidadeAsc());
	}

	// Método para criar um novo produto
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	// Método para atualizar um produto existente
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}

	// Método para deletar um produto por ID
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtoRepository.deleteById(id);
	}

}