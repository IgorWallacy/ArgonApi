package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doks_ticket_medio")
public class TicketMedio {
	
	
	
	
	
	
	private LocalDate data;
	
	private String filial;
	
	private BigDecimal valorLiquido;
	
	private BigDecimal quantidadeVendas;
	
	@Id
	private BigDecimal quantidadeItens;

	
	
	
	

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getQuantidadeVendas() {
		return quantidadeVendas;
	}

	public void setQuantidadeVendas(BigDecimal quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}

	public BigDecimal getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(BigDecimal quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	
	
	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(quantidadeItens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketMedio other = (TicketMedio) obj;
		return Objects.equals(quantidadeItens, other.quantidadeItens);
	}

	
	
	
	

}
