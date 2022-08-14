package com.doks.conferencia.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.DocumentosAPagar;
import com.doks.conferencia.repository.DocumentosAPagarRepository;

@RestController
@RequestMapping("/api/documentos/apagar")
public class DocumentosAPagarResource {
	
	
	@Autowired
	private DocumentosAPagarRepository repository;

	private List<DocumentosAPagar> d = new ArrayList<>();
	
	@GetMapping("/hoje/{filial}")
	public ResponseEntity<List<DocumentosAPagar>> buscarDocumentosAPagarHoje(@PathVariable Integer filial){
		
		if(filial == 0) {
		
		d = repository.buscarVencimentoHoje();
		}
		else {
			d = repository.buscarVencimentoHoje(filial);
			
		}
		return ResponseEntity.ok(d); 
	}
	
}
