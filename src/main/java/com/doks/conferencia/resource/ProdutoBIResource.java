package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.repository.ProdutoBIRepository;

@RestController
@RequestMapping("/api/produto/bi")
public class ProdutoBIResource {
	
	@Autowired
	private ProdutoBIRepository repository;

	@GetMapping("/vendacompra/{idproduto}/{idfilial}/{diasVenda}/{diasCompra}")
	private ResponseEntity<List<com.doks.conferencia.model.ProdutoBI>> vendaProduto (@PathVariable Integer idproduto , @PathVariable Integer idfilial , @PathVariable Integer diasVenda, @PathVariable Integer diasCompra)
	{
		
		LocalDate now = LocalDate.now();
		LocalDate qtdeDiasVenda = LocalDate.now().minusDays(diasVenda); 
		
		LocalDate qtdeDiasCompra = LocalDate.now().minusDays(diasCompra);
	
		
		
		return ResponseEntity.ok(repository.totalVendaPorIdProduto(idproduto, idfilial, qtdeDiasVenda, qtdeDiasCompra, now));
		
	}
	
	
}
