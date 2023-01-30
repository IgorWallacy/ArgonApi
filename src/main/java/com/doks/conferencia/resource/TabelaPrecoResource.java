package com.doks.conferencia.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.TabelaPrecoDisplay;
import com.doks.conferencia.model.TabelaPrecoPainel;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.TabelaPrecoDisplayRepository;
import com.doks.conferencia.repository.TabelaPrecoPainelRepository;

@RestController
@RequestMapping("/api_public/tabelapreco")
public class TabelaPrecoResource {

	@Autowired
	private TabelaPrecoPainelRepository repository;
	
	@Autowired
	private TabelaPrecoDisplayRepository displayRepository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	private List<Filial> filiais = new ArrayList<>();
	
	@GetMapping("/todas")
	private ResponseEntity<List<TabelaPrecoPainel>> tabela () {
		
		
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	@GetMapping("/display/{id}/{filial}")
	private ResponseEntity<List<TabelaPrecoDisplay>> produtosTabela (@PathVariable Integer id , @PathVariable Integer filial) {
		
		filiais = filialRepository.findAll();
		int size = filiais.size();

		if (size == 1) {
			
			return ResponseEntity.ok(displayRepository.porTabelaSingle(id));
			
			
			
		} else {
			
			return ResponseEntity.ok(displayRepository.porTabela(id, filial));
			
		}
		
		
	}
	
	
}
