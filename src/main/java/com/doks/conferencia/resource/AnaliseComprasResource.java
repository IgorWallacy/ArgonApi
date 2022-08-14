package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/produtos/{dataInicial}/{dataFinal}/{fornecedor}/{filial}/{dataInicialVenda}/{dataFinalVenda}")
	public ResponseEntity<List<AnaliseCompras2>> produtosCompradosFornecedor (
			@PathVariable String dataInicial, 
			@PathVariable String dataFinal ,
			@PathVariable String fornecedor,
			@PathVariable String filial,
			@PathVariable String dataInicialVenda, 
			@PathVariable String dataFinalVenda 
			) {
		
		LocalDate dataI = LocalDate.parse(dataInicial);
		LocalDate dataF = LocalDate.parse(dataFinal);
		
		
		
		LocalDate dataIV = LocalDate.parse(dataInicialVenda);
		LocalDate dataFV = LocalDate.parse(dataFinalVenda);
		
		Integer idFornecedor = Integer.parseInt(fornecedor); 
		Integer idFilial = Integer.parseInt(filial);
		
	//	compras = repository.comprasProdutos(dataI,dataF.plusDays(1),idFornecedor,idFilial, dataIV, dataFV.plusDays(1));
		
		compras = repository2.comprasProdutos(dataI,dataF.plusDays(1),idFornecedor,idFilial, dataIV, dataFV.plusDays(1));
		
		return ResponseEntity.ok(compras);
	}
 	

}
