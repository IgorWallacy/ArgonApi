package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.doks.conferencia.model.Embalagem;
import com.doks.conferencia.model.dto.ProdutoCompraDTO;
import com.doks.conferencia.repository.EmbalagemCompraRepository;
import com.doks.conferencia.repository.ProdutoCompraRepository;
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

import com.doks.conferencia.model.PedidoCompra;
import com.doks.conferencia.model.PedidoItemCompra;
import com.doks.conferencia.repository.PedidoCompraRepository;
import com.doks.conferencia.repository.PedidoItemCompraRepository;

import javax.persistence.criteria.Order;

@RestController
@RequestMapping("/api/pedido/compra")
public class PedidoCompraResource {

	@Autowired
	private PedidoItemCompraRepository repositoryItem;
	@Autowired
	private ProdutoCompraRepository produtoCompraRepository;

	@Autowired
	private EmbalagemCompraRepository embalagemCompraRepository;

	@Autowired
	private PedidoCompraRepository repository;

	List<PedidoItemCompra> itemPedidoList = new ArrayList<PedidoItemCompra>();

	// private List<PedidoItemCompra> pedidos = new ArrayList<PedidoItemCompra>();
	
	@GetMapping("/produtos/{idfilial}")
	public ResponseEntity<List<ProdutoCompraDTO>> produtos (@PathVariable Integer idfilial) {
		
		return ResponseEntity.ok(produtoCompraRepository.todosProdutos(idfilial));
	}

	@GetMapping("/embalagem/{idproduto}")
	public ResponseEntity<List<Embalagem>> embalagens (@PathVariable Integer idproduto) {

		return ResponseEntity.ok(embalagemCompraRepository.embalagemUltimaCompra(idproduto));
	}

	@GetMapping("/todos")
	public ResponseEntity<List<PedidoCompra>> todos () {

		return ResponseEntity.ok(repository.findAll(Sort.by("id").descending()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoCompra> porIdPedido (@PathVariable Integer id) {

		return ResponseEntity.ok(repository.porId(id));
	}
	

	@PostMapping("/salvar")
	public ResponseEntity<PedidoCompra> salvar(@RequestBody PedidoCompra pedido) {
		 


		
		return ResponseEntity.ok(repository.save(pedido));
	}

	@PostMapping("/salvar/{idPedido}")
	public ResponseEntity<PedidoItemCompra> salvarItem(@RequestBody PedidoItemCompra pedido,
			@PathVariable Integer idPedido) {


		
		repositoryItem.save(pedido);

		return ResponseEntity.ok(pedido);
	}

	@GetMapping("/itens/pedidoId/{idPedido}")
	public ResponseEntity<List<PedidoItemCompra>> getItemPedido(@PathVariable Integer idPedido) {



		return ResponseEntity.ok(repositoryItem.findByIdPedidoCompra(idPedido));

	}
	
	@GetMapping("/itens/pedidoId/{idPedido}/{idProduto}")
	public ResponseEntity<List<PedidoItemCompra>> getItemPedidoProduto(@PathVariable Integer idPedido , @PathVariable Integer idProduto) {

		itemPedidoList = repositoryItem.findByIdPedidoIdProduto(idPedido,idProduto);

		return ResponseEntity.ok(itemPedidoList);

	}

	@SuppressWarnings("unchecked")
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<PedidoItemCompra> deletarItemPedido(@PathVariable Integer id) {
		
		 PedidoItemCompra item = repositoryItem.porId(id);
		 
		 
		
		  if(item != null) {
			   
			  
			  PedidoItemCompra itemEncontrado = item;
			 
			//  repositoryItem.deletarITemPedido(id);
			 
			  repositoryItem.delete(itemEncontrado);
			   
			   return ResponseEntity.ok(itemEncontrado);
		  } else {
		
		  return (ResponseEntity<PedidoItemCompra>) ResponseEntity.notFound();
		  }

	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/deletar/pedido/{id}")
	public ResponseEntity<PedidoCompra> deletarPedido(@PathVariable Integer id) {
		
		 PedidoCompra item = repository.porId(id);
		
		 List<PedidoItemCompra> ic = repositoryItem.findByIdPedidoCompra(id);
		 
		 for (PedidoItemCompra pedidoItemCompra : ic) {
		
			// System.out.println(pedidoItemCompra.getIdpedido().getId());
			 
			 repositoryItem.delete(pedidoItemCompra);
			 
		}
		 
		 
		 if(item != null ) {
			   
			  
			  PedidoCompra itemEncontrado = item;
			 
			
			 
			  repository.delete(itemEncontrado);
			   
		   return ResponseEntity.ok(itemEncontrado);
		  } else {
		
		  return (ResponseEntity<PedidoCompra>) ResponseEntity.notFound();
		  }

	}

}
