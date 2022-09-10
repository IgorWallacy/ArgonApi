package com.doks.conferencia.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.CondicaoPagamento;
import com.doks.conferencia.repository.CondicaoPagamentoRepository;

@RestController
@RequestMapping("/api/condicaopagamento")
public class CondicaoPagamentoResource {
	
	@Autowired
	private CondicaoPagamentoRepository repository;
	
	private List<CondicaoPagamento> todas = new ArrayList<>();
	
	@GetMapping("/todas")
	public ResponseEntity<List<CondicaoPagamento>> ListarTodas () {
		
		todas = repository.findAll(Sort.by(Sort.Order.desc("descricao")));
		
		
		return ResponseEntity.ok(todas);
	}

}
