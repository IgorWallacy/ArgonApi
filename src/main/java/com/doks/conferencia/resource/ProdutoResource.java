package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Produto;
import com.doks.conferencia.repository.Produtos;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class ProdutoResource {

	
	@Autowired
	private Produtos produtos;
	
	@GetMapping
	public List<Produto> todos () {
		
		return produtos.buscarProdutos();
	}
	
}
