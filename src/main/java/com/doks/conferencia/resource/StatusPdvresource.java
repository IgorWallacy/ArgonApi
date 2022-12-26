package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.StatusPdv;
import com.doks.conferencia.repository.StatusPdvRepository;

@RestController
@RequestMapping("/api/pdv/")
public class StatusPdvresource {

	
	@Autowired
	private StatusPdvRepository repository;
	
	@GetMapping("/status")
	public ResponseEntity< List<StatusPdv>> statusPdv () {
		
		
		return ResponseEntity.ok(repository.status());
		
	}
	
	
}
