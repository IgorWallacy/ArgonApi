package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.ConferenciaCegaItem;
import com.doks.conferencia.model.Lote;
import com.doks.conferencia.model.NotaFiscal;
import com.doks.conferencia.model.NotaFiscalConfFisica;
import com.doks.conferencia.model.Usuario;
import com.doks.conferencia.repository.ConferenciaCegaItemRepository;
import com.doks.conferencia.repository.LoteRepository;
import com.doks.conferencia.repository.NotaFiscalConfFisicaRepository;
import com.doks.conferencia.repository.NotaFiscalRepository;
import com.doks.conferencia.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/nota")
@CrossOrigin(origins = "*")
public class NotaFiscalResource {

	private NotaFiscal notaFiscalSelecionada;
	
	private Usuario usuarioSelecionado;

	@Autowired
	private NotaFiscalRepository repository;
	
	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private NotaFiscalConfFisicaRepository repositoryNotaFisica;
	
	@Autowired
	private ConferenciaCegaItemRepository repositoryItem;
	
	@Autowired
	private LoteRepository loteRepository;

	@GetMapping
	private List<NotaFiscal> todas() {

		return repository.filtradoConferencia();
	}
	
	@GetMapping("/porFilial/{id}")
	private List<NotaFiscal> todasPorFilial(@PathVariable("id") Integer id) {

		return repository.filialbyId(id);
	}

	@GetMapping("/notaId/{notaId}/usuarioCodigo/{codigo}")
	private ResponseEntity<Optional<NotaFiscal>> porId(@PathVariable("notaId") Integer id, @PathVariable("codigo") Integer codigo) {

		Optional<NotaFiscal> notaEncontrada = repository.findById(id);

		notaFiscalSelecionada = notaEncontrada.get();
		
		Optional<Usuario> usuarioEncontrado = usuariorepository.findById(codigo);

		usuarioSelecionado = usuarioEncontrado.get();
		
		

		return notaEncontrada != null ? ResponseEntity.ok(notaEncontrada) : ResponseEntity.notFound().build();

	}
	
	

	@GetMapping("/conferencia/{id}")
	private List<NotaFiscalConfFisica> buscarNotaFiscalConfFisicaPorIdNotaFiscal(@PathVariable("id") Integer id) {

		return repositoryNotaFisica.findBynotafiscal(id);

	}

	@PostMapping("/fisica")

	private void incluirConferencia() {

		NotaFiscalConfFisica fisica = new NotaFiscalConfFisica();
		
		fisica.setUsuario(usuarioSelecionado);

		fisica.setNotafiscal(notaFiscalSelecionada);

		fisica.setFilial(notaFiscalSelecionada.getFilial());

		LocalDateTime agora = LocalDateTime.now();

		fisica.setData(agora);
		
	

		repositoryNotaFisica.save(fisica);

	}
	
	@PostMapping("/conferencia/item")
	private void cadastrarConferenciaItem(@Valid @RequestBody ConferenciaCegaItem item) {
		
		
		if(item.getValidade() != null) {
			
		Lote lote = new Lote();
		
		LocalDate agora = LocalDate.now();
		lote.setEntrada(agora);
		
		lote.setIdfilial(item.getIdConferencia().getFilial());
		
		lote.setIdProduto(item.getIdProduto());
		
		lote.setVencimento(item.getValidade());
		
		loteRepository.save(lote);
		
		}
		
		repositoryItem.save(item);
	}
	
	@GetMapping("/conferenciaItem/{id}")
	public List<ConferenciaCegaItem> buscarConferenciaItem(@PathVariable("id") Integer id){
		
		return repositoryItem.findByidConferencia(id);
	}
	
	
	//Deletar Item da conferencia
	@DeleteMapping("/conferenciaItem/{id}")
	public void DeletarItemConferencia(@PathVariable("id") Integer id) {
		
		 repositoryItem.deleteById(id);
	}

	public NotaFiscal getNotaFiscalSelecionada() {
		return notaFiscalSelecionada;
	}

	public void setNotaFiscalSelecionada(NotaFiscal notaFiscalSelecionada) {
		this.notaFiscalSelecionada = notaFiscalSelecionada;
	}

}
