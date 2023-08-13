package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.Lote;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.LoteRepository;

@RestController
@RequestMapping("/api/lote")
public class LoteResource {
	
	
	@Autowired
	private LoteRepository repository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	List<Lote> todos = new ArrayList<Lote>();
	
	@PostMapping("/salvar/filial/{idfilial}")
	public  ResponseEntity<Lote> salvar(@PathVariable ("idfilial") Integer idfilial, @RequestBody Lote lote )  {
		


				
			Filial filial = filialRepository.porId(idfilial);
			
			
			

			Lote lote1 = new Lote();

			lote1.setId(lote.getId());
			lote1.setIdfilial(filial);
			lote1.setCodigo(lote.getCodigo());

			lote1.setEntrada(lote.getEntrada());
			lote1.setFabricacao(null);
			
			lote1.setIdProduto(lote.getIdProduto());
			lote1.setVencimento(lote.getVencimento());
			
			repository.save(lote1);
			
			return ResponseEntity.ok().body(lote1);
		
		
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		
		repository.deleteById(id);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<Lote>> listar () {
		
		List<Lote> todos = repository.findAll(Sort.by("entrada").descending());
		
		return ResponseEntity.ok(todos);
		
	}
	
	@GetMapping("/todos/filial/{idfilial}")
	public ResponseEntity<List<Lote>> listarPorFilial (@PathVariable ("idfilial") Integer idfilial ) {
		
		List<Lote> todos = repository.findByIdfilial(idfilial);
		
		return ResponseEntity.ok(todos);
		
	}
	
	@GetMapping("/filial/{idfilial}/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
	public ResponseEntity<List<Lote>> porVencimento (@PathVariable ("idfilial") Integer idfilial ,@PathVariable("dataInicial") String dataInicialP, @PathVariable("dataFinal") String dataFinalP) {
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		 
		 LocalDate dataInicial = LocalDate.parse(dataInicialP, formatter);
		 LocalDate dataFinal = LocalDate.parse(dataFinalP, formatter);
		 
	//	  System.out.println(dataInicial +"Datas " + dataFinal);
		 
		 if( (dataFinal != null) ||  (dataInicial != null)) {
			 
			  todos = repository.findByVencimentoBetween(idfilial, dataInicial, dataFinal);
			  
			
			 
		 } else {
		
		 todos = repository.findAll(Sort.by("vencimento"));
		 }
		return ResponseEntity.ok(todos);
		
	}
	
	@GetMapping("/contador/filial/{idfilial}/venceHoje")
	public ResponseEntity<Integer> contarProdutosVencendoHoje(@PathVariable("idfilial") Integer idfilial){
		
		LocalDate agora = LocalDate.now();
		
		 Integer total = repository.findByVencimentoBetweenContador(idfilial, agora, agora);
		 
		 return ResponseEntity.ok(total);
	}
	
	@GetMapping("/contador/filial/{idfilial}/venceProximos7Dias")
	public ResponseEntity<Integer> contarProdutosVencendo7Dias(@PathVariable("idfilial") Integer idfilial){
		
		LocalDate agora = LocalDate.now();
		
		
		
		 Integer total =repository.findByVencimentoBetweenContador(idfilial, agora, agora.plusDays(7));
		 
		 return ResponseEntity.ok(total);
	}
	
	@GetMapping("/contador/filial/{idfilial}/venceProximos15Dias")
	public ResponseEntity<Integer> contarProdutosVencendo15Dias(@PathVariable("idfilial") Integer idfilial){
		
		LocalDate agora = LocalDate.now();
		
		
		
		 Integer total = repository.findByVencimentoBetweenContador(idfilial, agora, agora.plusDays(15));
		 
		 return ResponseEntity.ok(total);
	}
	
	@GetMapping("/contador/filial/{idfilial}/venceProximos30Dias")
	public ResponseEntity<Integer> contarProdutosVencendo30Dias(@PathVariable("idfilial") Integer idfilial){
		
		LocalDate agora = LocalDate.now();
		
		
		
		 Integer total =repository.findByVencimentoBetweenContador(idfilial, agora, agora.plusDays(30));
		 
		 return ResponseEntity.ok(total);
	}

}
