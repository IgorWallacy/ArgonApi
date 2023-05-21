package com.doks.conferencia.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Hierarquia;
import com.doks.conferencia.repository.GruposRepository;

@RestController
@RequestMapping("/api/grupos")
public class HierarquiaResource {
	
	
	@Autowired
	private GruposRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Hierarquia>> buscarGrupos () {
		
		return ResponseEntity.ok(repository.todos());
	}
	
	
	@Transactional
	@PutMapping("/atualizarmeta/{codigo}/{meta}")
	public void atualizarMeta (@PathVariable String codigo, @PathVariable BigDecimal meta){
	
		repository.updateMeta(codigo, meta);
		
		repository.updateMarkup(codigo, meta);
		
	}

}
