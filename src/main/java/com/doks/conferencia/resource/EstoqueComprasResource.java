package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.EstoqueCompras;
import com.doks.conferencia.repository.EstoqueComprasRepository;

@RestController
@RequestMapping("/api/estoquecompras")
public class EstoqueComprasResource {

	@Autowired
	private EstoqueComprasRepository repository;
	
	@GetMapping("/{dataInicial}/{dataFinal}/{fornecedor}")
	private ResponseEntity<List<EstoqueCompras>> porData (@PathVariable String dataInicial, @PathVariable String dataFinal, @PathVariable Integer fornecedor) {
		
	   LocalDate dataI = LocalDate.parse(dataInicial);
	   LocalDate dataF = LocalDate.parse(dataFinal);
	   
	  
		
		return ResponseEntity.ok(repository.porData( dataI,dataF, fornecedor));
	}
	
	
}
