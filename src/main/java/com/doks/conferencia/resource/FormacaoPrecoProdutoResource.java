package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.FormacaoPrecoProduto;
import com.doks.conferencia.repository.FormacaoPrecoProdutoRepository;

@RestController
@RequestMapping("/api/produto/consulta")
public class FormacaoPrecoProdutoResource {

	@Autowired
	private FormacaoPrecoProdutoRepository repository;
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<List<FormacaoPrecoProduto>> porId (@PathVariable Integer id) {
		
		
		
		return ResponseEntity.ok(repository.porId(id));
	}
			
	
	
	@GetMapping()
	public ResponseEntity<List<FormacaoPrecoProduto>> todos () {
		
		
		
		return ResponseEntity.ok(repository.todos());
	}
}
