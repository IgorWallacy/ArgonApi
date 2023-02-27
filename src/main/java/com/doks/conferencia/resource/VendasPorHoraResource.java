package com.doks.conferencia.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.VendasPorHora;
import com.doks.conferencia.repository.VendasPorHoraRepository;

@RestController
@RequestMapping("/api/vendas/por_hora")
public class VendasPorHoraResource {
	
  @Autowired
  private VendasPorHoraRepository repository; 
  
  @GetMapping("/{dataI}/{dataF}/{filial}")
  private ResponseEntity<List<VendasPorHora>> buscarVendas (@PathVariable String dataI, @PathVariable String dataF, String filial) {
	  
	  LocalDateTime data1 = LocalDateTime.parse(dataI);
	  LocalDateTime data2 = LocalDateTime.parse(dataF);
	  
	  return ResponseEntity.ok(repository.porHora(data1,data2,filial));
  }

}
