package com.doks.conferencia.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.FormacaoPrecoLog;
import com.doks.conferencia.repository.FormacaoPrecoLogRepository;

@RestController
@RequestMapping("/api/formacaoprecoproduto")
public class FormacaoPrecoLogResource {

	
	@Autowired
	private FormacaoPrecoLogRepository repository;
	
	@GetMapping("/filial/{idfilial}/dataInicial/{data1}/dataFinal/{data2}")
	public ResponseEntity<List<FormacaoPrecoLog>> listar(@PathVariable ("idfilial") Integer idfilial,
			@PathVariable("data1") String dataInicial, @PathVariable("data2") String dataFinal ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm:ss");
		 
		 LocalDateTime dataInicialF = LocalDateTime.parse(dataInicial, formatter);
		 LocalDateTime dataFinalF = LocalDateTime.parse(dataFinal, formatter);
		 
	
		 
		 
		
		List<FormacaoPrecoLog> produtos = repository.produtosAlteradosPorDataHora(idfilial, dataInicialF, dataFinalF);
		 
		 return ResponseEntity.ok(produtos);
	}
	
	
}
