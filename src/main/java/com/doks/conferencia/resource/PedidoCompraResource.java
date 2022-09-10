package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/pedido/compra")
public class PedidoCompraResource {

	@Autowired
	private PedidoItemCompraRepository repositoryItem;

	@Autowired
	private PedidoCompraRepository repository;

	List<PedidoItemCompra> itemPedidoList = new ArrayList<PedidoItemCompra>();

	// private List<PedidoItemCompra> pedidos = new ArrayList<PedidoItemCompra>();
	
	@GetMapping("/todos")
	public ResponseEntity<List<PedidoCompra>> todos () {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PedidoCompra>> porIdPedido (@PathVariable Integer id) {
		
		return ResponseEntity.ok(repository.findById(id));
	}
	

	@PostMapping("/salvar")
	public ResponseEntity<PedidoCompra> salvar(@RequestBody PedidoCompra pedido) {
		 
		LocalDate now = LocalDate.now();

		
		
		PedidoCompra pedidoSalvo = new PedidoCompra();

		pedidoSalvo.setFornecedor(pedido.getFornecedor());
		pedido.setDataEmissao(now);

		
	
		
		pedidoSalvo = repository.save(pedido);

		
		return ResponseEntity.ok(pedidoSalvo);
	}

	@PostMapping("/salvar/{idPedido}")
	public ResponseEntity<PedidoItemCompra> salvarItem(@RequestBody PedidoItemCompra pedido,
			@PathVariable Integer idPedido) {

		

		PedidoItemCompra pedidoSalvo = new PedidoItemCompra();

		
		
		pedidoSalvo.setIdproduto(pedido.getIdproduto());
		pedidoSalvo.setIdpedido(pedido.getIdpedido());
		pedidoSalvo.setUnidadeCompra(pedido.getUnidadeCompra());
		pedidoSalvo.setFilial(pedido.getFilial());
		
		
		pedidoSalvo = repositoryItem.save(pedido);

		return ResponseEntity.ok(pedidoSalvo);
	}

	@GetMapping("/itens/pedidoId/{idPedido}")
	public ResponseEntity<List<PedidoItemCompra>> getItemPedido(@PathVariable Integer idPedido) {

		itemPedidoList = repositoryItem.findByIdPedido(idPedido);

		return ResponseEntity.ok(itemPedidoList);

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
		
		 List<PedidoItemCompra> ic = repositoryItem.findByIdPedido(id);
		 
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
