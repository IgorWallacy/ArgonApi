package com.doks.conferencia.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.bi.ProdutoBI;
import com.doks.conferencia.repository.bi.ProdutosBIRepository;

@RestController
@RequestMapping("/api_bi")
public class BiResourece {
	
	
	@Autowired
	private ProdutosBIRepository repository;

	private List<ProdutoBI> vendas = new ArrayList<ProdutoBI> ();
	
	@GetMapping("/vendas")
	public ResponseEntity<List<ProdutoBI>> vendasProdutos () {
		
		vendas = repository.buscarProdutos();
		
		return ResponseEntity.ok(vendas);
	}
	
	
}
