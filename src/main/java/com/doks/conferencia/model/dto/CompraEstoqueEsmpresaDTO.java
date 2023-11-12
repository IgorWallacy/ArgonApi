package com.doks.conferencia.model.dto;

import java.time.LocalDate;

public class CompraEstoqueEsmpresaDTO {
	
	
	private Integer fornecedor;

	private String grupo;
	
	private LocalDate dataInicialCompra;
	
	private LocalDate dataFinalCompra;
	
	private LocalDate dataInicialVenda;
	
	private LocalDate dataFinalVenda;

	public Integer getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Integer fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDate getDataInicialCompra() {
		return dataInicialCompra;
	}

	public void setDataInicialCompra(LocalDate dataInicialCompra) {
		this.dataInicialCompra = dataInicialCompra;
	}

	public LocalDate getDataFinalCompra() {
		return dataFinalCompra;
	}

	public void setDataFinalCompra(LocalDate dataFinalCompra) {
		this.dataFinalCompra = dataFinalCompra;
	}

	public LocalDate getDataInicialVenda() {
		return dataInicialVenda;
	}

	public void setDataInicialVenda(LocalDate dataInicialVenda) {
		this.dataInicialVenda = dataInicialVenda;
	}

	public LocalDate getDataFinalVenda() {
		return dataFinalVenda;
	}

	public void setDataFinalVenda(LocalDate dataFinalVenda) {
		this.dataFinalVenda = dataFinalVenda;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
