package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Entidade;
import com.doks.conferencia.repository.EntidadeRepository;

@RestController
@RequestMapping("/api/entidade")
public class EntidadeResource {

	@Autowired
	private EntidadeRepository repository;
	
	@GetMapping("/fornecedores")
	public List<Entidade> fornecedores () {
		
		return repository.fornecedores();
		
	}

	@GetMapping("/compradores")
	public List<Entidade> compradores () {

		return repository.compradores();

	}

}
