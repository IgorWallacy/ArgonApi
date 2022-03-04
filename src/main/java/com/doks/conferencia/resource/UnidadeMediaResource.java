package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.UnidadeMedida;
import com.doks.conferencia.repository.UnidadeMedidaRepository;

@RestController
@RequestMapping("/api/unidademedida")
@CrossOrigin(origins = "*")
public class UnidadeMediaResource {

	@Autowired
	private UnidadeMedidaRepository repository;
	
	@GetMapping
	public List<UnidadeMedida> todas () {
		
		return repository.findAll();
	}
}
