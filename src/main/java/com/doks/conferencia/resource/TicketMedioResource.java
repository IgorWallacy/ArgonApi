package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.TicketMedio;
import com.doks.conferencia.repository.TicketMedioRepository;

@RestController
@RequestMapping("/api/vendas/ticket_medio")
public class TicketMedioResource {

	
	@Autowired
	private TicketMedioRepository repository;
	
	@GetMapping("/{dataInicial}/{dataFinal}")
	private ResponseEntity<List<TicketMedio>> calcular (@PathVariable String dataInicial , @PathVariable String dataFinal) {
		
		LocalDate data1 = LocalDate.parse(dataInicial);
		LocalDate data2 = LocalDate.parse(dataFinal);
		
		return ResponseEntity.ok( repository.buscar(data1, data2));
		
	}
	
}
