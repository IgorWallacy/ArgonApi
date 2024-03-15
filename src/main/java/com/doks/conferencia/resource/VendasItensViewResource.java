package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.VendasItensView;
import com.doks.conferencia.repository.VendasItensViewRepository;

@RestController
@RequestMapping("/api_vendas")
public class VendasItensViewResource {
	
	@Autowired
	private VendasItensViewRepository repository;


	@GetMapping("/bi/sync/{dataI}/{dataF}/{modocusto}/{somenteVendaPdv}")
	private ResponseEntity<List<VendasItensView>> vendasSync(@PathVariable String dataI, @PathVariable String dataF, @PathVariable Integer modocusto, @PathVariable Integer somenteVendaPdv) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		LocalDate dataInicial = LocalDate.parse(dataI, formatter);
		LocalDate dataFinal = LocalDate.parse(dataF, formatter);

		if( somenteVendaPdv == 1 ) {
			return ResponseEntity.ok(repository.getVendasBISomentePDV( dataInicial , dataFinal, modocusto));
		} else {
			return ResponseEntity.ok(repository.getVendasBI( dataInicial , dataFinal, modocusto));
		}



	}
	
}
