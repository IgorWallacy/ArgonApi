package com.doks.conferencia.resource;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.Produto;
import com.doks.conferencia.model.PromocaoProdutoFamiliaFiltro;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.Produtos;
import com.doks.conferencia.repository.PromocaoProdutoFamiliaRepository;

@RestController
@RequestMapping("/api_react/promocao")
@CrossOrigin(origins = "*")
public class PromocaoResource {

	
	
	
	@Autowired
	private Produtos produtoRepository;
	
	@Autowired
	private PromocaoProdutoFamiliaRepository produtos2Repository;
	
	
	
	@Autowired
	private FilialRepository filialRepository;
	
	
	
	private List<PromocaoProdutoFamiliaFiltro> produtos2 = new ArrayList<>();
	
	
	
	
	
	
/*	@GetMapping
	public List<PromocaoProduto> todos () { 
		
		return promocoes.findAll();
	}
*/
	
	@GetMapping(value = "/vigente/{idFilial}" )
	public ResponseEntity<List<PromocaoProdutoFamiliaFiltro>> porVencimento ( @PathVariable String idFilial, HttpServletResponse response) throws IOException, SQLException {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		 
		 LocalDate dataInicial ;
		 
		 int id = Integer.parseInt(idFilial);
		 
		dataInicial = LocalDate.now();
		
		
		
		produtos2 = produtos2Repository.findByVencimentoBetween(dataInicial, id);
		
		
			
		
		
		return ResponseEntity.ok(produtos2);
	}
	
	
	

	
	
	@RequestMapping (value="/imagem/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getQRImage(@PathVariable final Integer id) {
	  //  byte[] bytes = this.photoBytes;
	    
	    
	    Optional<Produto> p = produtoRepository.findById(id);
		byte[] imageBytes = null;
		if (p.isPresent()) {
		
			imageBytes = p.get().getImagem();
			
		//	System.out.println("Pegouo hash " + imageBytes);
		}

	    // Set headers
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);

	    return new ResponseEntity<byte[]> (imageBytes, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping (value="/imagem/filial/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getQRImageFilial(@PathVariable final Integer id) {
	  //  byte[] bytes = this.photoBytes;
	    
	    
	    Optional<Filial> f = filialRepository.findById(id);
		byte[] imageBytes = null;
		if (f.isPresent()) {
		
			imageBytes = f.get().getLogoFilial();
			
		//	System.out.println("Pegouo hash " + imageBytes);
		}

	    // Set headers
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);

	    return new ResponseEntity<byte[]> (imageBytes, headers, HttpStatus.CREATED);
	}
	
	
	
	
}
