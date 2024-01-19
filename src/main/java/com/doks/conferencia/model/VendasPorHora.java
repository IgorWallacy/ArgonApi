package com.doks.conferencia.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doks_vendas_por_hora")
public class VendasPorHora {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer iddocumento;

	//@JsonFormat(pattern = "HH:MM")
	private LocalDateTime horaInicial;
	
	
	
	private BigDecimal valorliquido;
	
	private String filial;

	public Integer getIddocumento() {
		return iddocumento;
	}

	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}

	public LocalDateTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalDateTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public BigDecimal getValorliquido() {
		return valorliquido;
	}

	public void setValorliquido(BigDecimal valorliquido) {
		this.valorliquido = valorliquido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iddocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendasPorHora other = (VendasPorHora) obj;
		return Objects.equals(iddocumento, other.iddocumento);
	}
	
	
	
	
}
