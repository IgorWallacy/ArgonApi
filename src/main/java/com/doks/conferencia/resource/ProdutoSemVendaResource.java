package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.ProdutosSemVendas;
import com.doks.conferencia.repository.FilialRepository;

import com.doks.conferencia.repository.ProdutosSemVendasRepository;

@RestController
@RequestMapping("/api_vendas/sem_vendas")
public class ProdutoSemVendaResource {
	
	
	@Autowired
	private ProdutosSemVendasRepository repository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	private List<Filial> filiais = new ArrayList<>();
	
	@GetMapping("/produto/{dataI}/{dataF}/{filial}/{ultimaCompra}/{ultimaVenda}")
	public ResponseEntity<List<ProdutosSemVendas>> todos (@PathVariable String dataI , @PathVariable String  dataF, @PathVariable Integer filial, @PathVariable String ultimaCompra, @PathVariable String ultimaVenda) {
		
		
		
		LocalDate data1 = LocalDate.parse(dataI);
		LocalDate data2 = LocalDate.parse(dataF);
		LocalDate dataUltimaCompra = LocalDate.parse(ultimaCompra);
		LocalDate dataUltimaVenda = LocalDate.parse(ultimaVenda);
		
		filiais = filialRepository.findAll();
		int size = filiais.size();

		if (size == 1) { 
			
			return ResponseEntity.ok(repository.todos1Filial( data1, data2, filial, dataUltimaCompra , dataUltimaVenda));
		} else {
		
		
		
		return ResponseEntity.ok(repository.todos( data1, data2, filial, dataUltimaCompra, dataUltimaVenda));
		}
	}

}
