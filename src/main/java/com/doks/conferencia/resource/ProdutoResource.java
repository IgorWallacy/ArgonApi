package com.doks.conferencia.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	
	@Transactional
	@PutMapping("/atualizarmarkupminimo/{idproduto}/{idfamilia}/{percentual}")
	public void atualizarMarkupMinimo(@PathVariable Integer idproduto , @PathVariable Integer idfamilia, @PathVariable BigDecimal percentual) {
		
		
	
		if(idfamilia == 0) {
		
		produtos.atualizarMarkupMinimo(idproduto , percentual);
		} else {
			
			
			produtos.atualizarMarkupMinimoFamilia(idfamilia , percentual);
			
			
		}
		
	}
	
	@Transactional
	@PutMapping("/atualizarmarkdownminimo/{idproduto}/{idfamilia}/{percentual}")
	public void atualizarMarkDownMinimo(@PathVariable Integer idproduto , @PathVariable Integer idfamilia, @PathVariable BigDecimal percentual) {
		
		
	
		if(idfamilia == 0) {
		
		produtos.atualizarMarkDownMinimo(idproduto , percentual);
		} else {
			
			
			produtos.atualizarMarkDownMinimoFamilia(idfamilia , percentual);
			
			
		}
		
	}
	
}
