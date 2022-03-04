package com.doks.conferencia.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doks.conferencia.model.ConferenciaManualItem;
import com.doks.conferencia.repository.ConferenciaManualItemRepository;

@Service
public class ConferenciaManualService {

	@Autowired
	private ConferenciaManualItemRepository repository;
	private List<ConferenciaManualItem> todos = new ArrayList<>();

	public List<ConferenciaManualItem> itensVencimento(Integer idFilial) {

		return repository.buscarPorFilial(idFilial);

	}

	public List<ConferenciaManualItem> porVencimento(Integer idfilial, String dataInicialP, String dataFinalP) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		LocalDate dataInicial = LocalDate.parse(dataInicialP, formatter);
		LocalDate dataFinal = LocalDate.parse(dataFinalP, formatter);

		if ((dataFinal != null) || (dataInicial != null)) {

			todos = repository.findByVencimentoBetween(idfilial, dataInicial, dataFinal);
			
			

		} else {

			todos = repository.buscarPorFilial(idfilial);
		}

		return todos;

	}

	public List<ConferenciaManualItem> porVencimentoSaldoPositivo(Integer idfilial, String dataInicialP,  String dataFinalP) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		LocalDate dataInicial = LocalDate.parse(dataInicialP, formatter);
		LocalDate dataFinal = LocalDate.parse(dataFinalP, formatter);

		if ((dataFinal != null) || (dataInicial != null)) {

			todos = repository.findByVencimentoBetweenSaldoPositivo(idfilial, dataInicial, dataFinal);
			
			

		} else {

			todos = repository.buscarPorFilial(idfilial);
		}

		return todos;
	}

}
