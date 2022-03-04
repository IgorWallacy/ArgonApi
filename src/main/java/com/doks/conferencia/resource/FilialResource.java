package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.repository.FilialRepository;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class FilialResource {
	
	@Autowired
	private  FilialRepository repository;
	
	@GetMapping("/api/filial")
	private List<Filial> getFilial() {
		
		return repository.findAll(Sort.by("codigo"));
	}
	
	@GetMapping("/api_react/filial")
	private List<Filial> getFilialReact() {
		
		return repository.findAll(Sort.by("codigo"));
		
	}

}
