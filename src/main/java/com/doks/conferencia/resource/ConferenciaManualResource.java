package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.ConferenciaManual;
import com.doks.conferencia.model.ConferenciaManualItem;
import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.Usuario;
import com.doks.conferencia.repository.ConferenciaManualItemRepository;
import com.doks.conferencia.repository.ConferenciaManualRepository;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.UsuarioRepository;
import com.doks.conferencia.service.ConferenciaManualService;

@RestController
@RequestMapping("/api/conferenciamanual")
public class ConferenciaManualResource {
	
	@Autowired
	private ConferenciaManualService service;
	
	@Autowired
	private ConferenciaManualRepository repository;
	
	@Autowired
	private ConferenciaManualItemRepository conferenciaManualItemRepository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	private List<ConferenciaManualItem> conferenciaItens;
	
	@PostMapping(value = "/salvar/{usuario}/{filial}")
	public ResponseEntity<ConferenciaManual> salvar (@PathVariable Integer usuario, @PathVariable Integer filial,  @RequestBody ConferenciaManual conferenciaManual ) {
		
		Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuario);
		
		Optional<Filial> filialEncontrada = filialRepository.findById(filial);
		
		
		LocalDate agora = LocalDate.now();
		
	
		
		conferenciaManual.setIdfilial(filialEncontrada.get());
		
		conferenciaManual.setUsuario(usuarioEncontrado.get());
		
	
		if(conferenciaManual.getDataEntrada() == null) 
		{
		
			conferenciaManual.setDataEntrada(agora); 
			
		}
		
		conferenciaManual.setFinalizado(false);
		
		 repository.save(conferenciaManual);
		 
		 return ResponseEntity.ok(conferenciaManual);
	}
	
	@PostMapping(value = "/salvarItem/{id}")
	public ResponseEntity<ConferenciaManualItem> salvarItem(@PathVariable Long id, @RequestBody ConferenciaManualItem item) {
		
		
		
		Optional<ConferenciaManual> conferenciaEncontrada = repository.findById(id);
		
		item.setConferenciaManual(conferenciaEncontrada.get());
		
	/*	
		
		if(item.getId() == null) {
		
		
		
	
		Lote lote = new Lote();
		lote.setEntrada(agora);
		lote.setIdfilial(conferenciaEncontrada.get().getIdfilial());
		lote.setIdProduto(item.getProduto());
		lote.setVencimento(item.getDataVencimento());
		loteRepository.save(lote);
	
		
	}
	*/
		
		conferenciaManualItemRepository.save(item);
		
		return ResponseEntity.ok(item);
	}
	
	@GetMapping("/todos/{idFilial}")
	public List<ConferenciaManual> todos (@PathVariable int idFilial) {
		
		return repository.porFilial(idFilial);
	}
	
	@GetMapping("/conferenciaId/{id}")
	public ResponseEntity<List<ConferenciaManualItem>> buscarConferenciaItens(@PathVariable Long id){
		
		conferenciaItens = conferenciaManualItemRepository.buscarPeloIdConferencia(id);
		
		return ResponseEntity.ok(conferenciaItens);
	}
	
	@PutMapping("/status/atualizar/aprovado/{c}")
	public void atualizarStatusAprovado(@PathVariable ConferenciaManual c  ) {
		
		
	//	Optional<ConferenciaManual> conferenciaEncontrada = repository.findById(id);
		
	
		
		
		
	//	ConferenciaManual c = conferenciaEncontrada.get();
		
		c.setStatus(true);
		
		repository.save(c);
		
	}
	
	@PutMapping("/status/atualizar/reprovado/{c}")
	public void atualizarStatusReprovado(@PathVariable ConferenciaManual c  ) {
		
		
	//	Optional<ConferenciaManual> conferenciaEncontrada = repository.findById(id);
		
	
		
		
		
//		ConferenciaManual c = conferenciaEncontrada.get();
		
		c.setStatus(false);
		
		repository.save(c);
		
	}
	
	@PutMapping("/status/atualizar/finalizado/{f}")
	public void atualizarFinalizado(@PathVariable ConferenciaManual f  ) {
		
	//	System.out.println("Dados da conf"+f.getId());
		
		Optional<ConferenciaManual> conferenciaEncontrada = repository.findById(f.getId());
		
		if(conferenciaEncontrada.get() != null) {
		
		conferenciaEncontrada.get().setFinalizado(true);
		
		repository.save(conferenciaEncontrada.get());
		
	}
	}
	
	
	@PutMapping("/status/atualizar/item/aprovado/{c}")
	public ResponseEntity<ConferenciaManualItem> atualizarStatusItemAprovado(@PathVariable ConferenciaManualItem  c) {
		
		

		c.setStatus("ACEITO");
	//	System.out.println("ACEITO " + c.getId() + c.getProduto().getNome() + c.getStatus());
		
		conferenciaManualItemRepository.save(c);

		 
		 return ResponseEntity.ok(c);
		
	}
	
	@PutMapping("/status/atualizar/item/reprovado/{c}")
	public  ResponseEntity<ConferenciaManualItem> atualizarStatusItemReprovado(@PathVariable ConferenciaManualItem  c ) {
		
	
	
		
		
		c.setStatus("RECUSADO");
		
	//	System.out.println("RECUSADO " + c.getId() + c.getProduto().getNome() + c.getStatus());
		
		conferenciaManualItemRepository.save(c);

		 
		 return ResponseEntity.ok(c);
		 
		 
		 
		 
		
	}
	
	//Deletar   conferencia Manual
		@DeleteMapping("/deletar/{id}")
		public void DeletarConferenciaManual(@PathVariable("id") Long id) {
			
			 repository.deleteById(id);
		}
		
				//Deletar   Item conferencia Manual
				@DeleteMapping("/deletar/item/{id}")
				public void DeletarItemConferencia(@PathVariable("id") Long id) {
					
					conferenciaManualItemRepository.deleteById(id);
				}
				
				
				@GetMapping("/vencimento/{idfilial}")
				public List<ConferenciaManualItem> vencimentoLista (@PathVariable("idfilial") Integer idfilial) {
					
					return service.itensVencimento(idfilial);
				}
				
				@GetMapping("/vencimento/filial/{idfilial}/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
				public List<ConferenciaManualItem> vencimentoListaPordata (@PathVariable ("idfilial") Integer idfilial ,@PathVariable("dataInicial") String dataInicialP, @PathVariable("dataFinal") String dataFinalP) {
					
					return service.porVencimento(idfilial, dataInicialP, dataFinalP);
				}
				
				@GetMapping("/vencimento/filial/{idfilial}/dataInicial/{dataInicial}/dataFinal/{dataFinal}/SaldoPositivo")
				public List<ConferenciaManualItem> vencimentoListaPordataSaldoPositivo (@PathVariable ("idfilial") Integer idfilial ,@PathVariable("dataInicial") String dataInicialP, @PathVariable("dataFinal") String dataFinalP) {
					
					return service.porVencimentoSaldoPositivo(idfilial, dataInicialP, dataFinalP);
				}


}
