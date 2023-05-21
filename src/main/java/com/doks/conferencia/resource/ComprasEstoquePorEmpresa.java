package com.doks.conferencia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.EstoqueCompras;
import com.doks.conferencia.model.dto.CompraEstoqueEsmpresaDTO;
import com.doks.conferencia.repository.EstoqueComprasRepository;

@RestController
@RequestMapping("/api/estoque/compras")
public class ComprasEstoquePorEmpresa {
	
	@Autowired
	private EstoqueComprasRepository repository;

	@PostMapping("/empresa")
	public ResponseEntity<List<EstoqueCompras>> comprasPorEmpresa (@RequestBody CompraEstoqueEsmpresaDTO data ) {
		
	
		
		
		return ResponseEntity.ok(repository.porDataProdutoEmpresa(data.getDataInicialCompra(), data.getDataFinalCompra(), data.getDataInicialVenda() , data.getDataFinalVenda() , data.getFornecedor() ));
		
		
	}
	
}
