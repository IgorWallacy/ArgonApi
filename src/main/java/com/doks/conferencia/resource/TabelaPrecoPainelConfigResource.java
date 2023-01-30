package com.doks.conferencia.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.ConfiguracaoPainelDePreco;
import com.doks.conferencia.repository.TabelaPrecoRepository;

@RestController
@RequestMapping("/api_public/tabelapreco/")
public class TabelaPrecoPainelConfigResource {

	
	@Autowired
	private TabelaPrecoRepository repository;
	
	private List<ConfiguracaoPainelDePreco> todos = new ArrayList<>();

	@GetMapping("/configuracao")
	private ResponseEntity<List<ConfiguracaoPainelDePreco>> configuracoes () {
		
		todos = repository.findAll();
		
		return ResponseEntity.ok(todos);
	}
	
	

}
