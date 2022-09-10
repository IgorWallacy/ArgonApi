package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.AnaliseCompras2;
import com.doks.conferencia.repository.AnaliseComprasRepository2;

@RestController
@RequestMapping("/api_react/compras/")
public class AnaliseComprasResource {
	
	/*@Autowired
	private AnaliseComprasrepository repository;
	*/
	
	@Autowired
	private AnaliseComprasRepository2 repository2;
	
	private List<AnaliseCompras2> compras = new ArrayList<AnaliseCompras2>();
	
	
	@PostMapping("/produtos/{dataInicial}/{dataFinal}/{fornecedor}/{dataInicialVenda}/{dataFinalVenda}")
	public ResponseEntity<List<AnaliseCompras2>> produtosCompradosFornecedor (
			@PathVariable String dataInicial, 
			@PathVariable String dataFinal ,
			@PathVariable String fornecedor,
			@RequestBody(required = false) String filial,
			@PathVariable String dataInicialVenda, 
			@PathVariable String dataFinalVenda 
			) {
		
		
		
		
		String filialId = StringUtils.substringBetween(filial, "[", "]");
		
		LocalDate dataI = LocalDate.parse(dataInicial);
		LocalDate dataF = LocalDate.parse(dataFinal);
		
		
		
		LocalDate dataIV = LocalDate.parse(dataInicialVenda);
		LocalDate dataFV = LocalDate.parse(dataFinalVenda);
		
		Integer idFornecedor = Integer.parseInt(fornecedor); 
		
		
	//	compras = repository.comprasProdutos(dataI,dataF.plusDays(1),idFornecedor,idFilial, dataIV, dataFV.plusDays(1));
		
		
		
		compras = repository2.comprasProdutos(dataI,dataF.plusDays(1),idFornecedor, dataIV, dataFV.plusDays(1) );
		
		return ResponseEntity.ok(compras);
	}
	
	
	
	@PostMapping("/produtos/{idProduto}/{dataInicial}/{dataFinal}/{fornecedor}/{filial}/{dataInicialVenda}/{dataFinalVenda}")
	public ResponseEntity<List<AnaliseCompras2>> produtosCompradosFornecedorFilial (
			@PathVariable Integer idProduto,
			@PathVariable String dataInicial, 
			@PathVariable String dataFinal ,
			@PathVariable String fornecedor,
			@PathVariable Integer filial,
			@PathVariable String dataInicialVenda, 
			@PathVariable String dataFinalVenda 
			) {
		
		
		
		
		
		LocalDate dataI = LocalDate.parse(dataInicial);
		LocalDate dataF = LocalDate.parse(dataFinal);
		
		
		
		LocalDate dataIV = LocalDate.parse(dataInicialVenda);
		LocalDate dataFV = LocalDate.parse(dataFinalVenda);
		
		Integer idFornecedor = Integer.parseInt(fornecedor); 
		
		
	//	compras = repository.comprasProdutos(dataI,dataF.plusDays(1),idFornecedor,idFilial, dataIV, dataFV.plusDays(1));
		
		
		
		compras = repository2.comprasProdutosFilial(dataI,dataF.plusDays(1),idFornecedor, filial,idProduto, dataIV, dataFV.plusDays(1) );
		
		return ResponseEntity.ok(compras);
	}
 	

}
