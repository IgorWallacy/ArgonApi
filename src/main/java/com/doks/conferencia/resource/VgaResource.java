package com.doks.conferencia.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.PddVga;
import com.doks.conferencia.model.VgaModel;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.PdvVgaRepository;
import com.doks.conferencia.repository.VgaRepository;

@RestController
@RequestMapping("/api_vga")
@CrossOrigin(origins = "*")
public class VgaResource {

	@Autowired
	private VgaRepository repository;

	@Autowired
	private PdvVgaRepository pdvVgaRepository;

	@Autowired
	private FilialRepository filialRepository;

	private List<VgaModel> vendas = new ArrayList<>();

	private List<PddVga> pdvs = new ArrayList<>();

	private List<Filial> filiais = new ArrayList<>();

	@GetMapping(value = "/vendas/filiais")
	public ResponseEntity<List<Filial>> buscarFiliais() {

		filiais = filialRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		return ResponseEntity.ok(filiais);
	}

	@GetMapping(value = "/vendas/pdvs/{idfilial}/{dataInicial}/{dataFinal}")
	public ResponseEntity<List<PddVga>> buscarPdvs(@PathVariable("idfilial") String idfilial, @PathVariable("dataInicial") String dataInicial, @PathVariable("dataFinal") String dataFinal) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate dateI = LocalDate.parse(dataInicial, formatter);
		LocalDate dateF = LocalDate.parse(dataFinal, formatter);

		pdvs = pdvVgaRepository.findPdv(idfilial,dateI, dateF);

		return ResponseEntity.ok(pdvs);
	}

	@GetMapping(value = "/vendas/total/{idfilial}/{dataInicial}/{dataFinal}/{pdv}")
	public ResponseEntity<List<VgaModel>> buscarvendasTotal(@PathVariable("idfilial") String idfilial,
			@PathVariable("dataInicial") String dataInicial, @PathVariable("dataFinal") String dataFinal,
			 @PathVariable("pdv") Integer pdv

	) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate dateI = LocalDate.parse(dataInicial, formatter);
		LocalDate dateF = LocalDate.parse(dataFinal, formatter);
		
		
		
		
		
		if(pdv > 0) {
			
		 

		  vendas = repository.findVendasTotalPDV(idfilial, dateI, dateF ,pdv);
		
		} else {
			
			if(Integer.parseInt(idfilial) == 0) {
				vendas = repository.findVendasTotal(dateI, dateF);
			} else {
		
		 vendas = repository.findVendasTotalFilial(idfilial, dateI, dateF);
		}}
		return ResponseEntity.ok(vendas);
	}

	@GetMapping(value = "/vendas/ecf/{idfilial}/{dataInicial}/{dataFinal}/{pdv}")
	public ResponseEntity<List<VgaModel>> buscarvendasEcf(@PathVariable("idfilial") String idfilial,
			@PathVariable("dataInicial") String dataInicial, @PathVariable("dataFinal") String dataFinal,
			 @PathVariable("pdv") Integer pdv) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate dateI = LocalDate.parse(dataInicial, formatter);
		LocalDate dateF = LocalDate.parse(dataFinal, formatter);
		
		if(pdv > 0) {

			vendas = repository.findVendasEcfPDV(idfilial, dateI, dateF,pdv);
		} else {
			
			if(Integer.parseInt(idfilial) == 0) {
				vendas = repository.findVendasEcf(dateI, dateF);
			} else {
		
		vendas = repository.findVendasEcfFilial(idfilial, dateI, dateF);
		}}
		return ResponseEntity.ok(vendas);
	}

	@GetMapping(value = "/vendas/nfce/{idfilial}/{dataInicial}/{dataFinal}/{pdv}")
	public ResponseEntity<List<VgaModel>> buscarvendasNfce(@PathVariable("idfilial") String idfilial,
			@PathVariable("dataInicial") String dataInicial, @PathVariable("dataFinal") String dataFinal,
			 @PathVariable("pdv") Integer pdv) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate dateI = LocalDate.parse(dataInicial, formatter);
		LocalDate dateF = LocalDate.parse(dataFinal, formatter);
		
		
		
		if(pdv > 0) {
		
			vendas = repository.findVendasNfcePDV(idfilial, dateI, dateF,pdv);
		} else {
			
			if(Integer.parseInt(idfilial) == 0) {
				vendas = repository.findVendasNfce(dateI, dateF);
			} else {

		vendas = repository.findVendasNfceFilial(idfilial, dateI, dateF);
		}}
		return ResponseEntity.ok(vendas);
	}

}
