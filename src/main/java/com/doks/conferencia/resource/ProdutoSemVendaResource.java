package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.ProdutosSemVendas;
import com.doks.conferencia.repository.ProdutosSemVendasRepository;

@RestController
@RequestMapping("/api_vendas/sem_vendas")
public class ProdutoSemVendaResource {
	
	
	@Autowired
	private ProdutosSemVendasRepository repository;
	
	@GetMapping("/produto/{dataI}/{dataF}/{filial}/{ultimaCompra}")
	public ResponseEntity<List<ProdutosSemVendas>> todos (@PathVariable String dataI , @PathVariable String  dataF, @PathVariable Integer filial, @PathVariable String ultimaCompra) {
		
		LocalDate data1 = LocalDate.parse(dataI);
		LocalDate data2 = LocalDate.parse(dataF);
		LocalDate dataUltimaCompra = LocalDate.parse(ultimaCompra);
		
		
		
		
		
		return ResponseEntity.ok(repository.todos( data1, data2, filial, dataUltimaCompra));
		
	}

}
