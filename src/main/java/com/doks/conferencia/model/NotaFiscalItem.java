package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notafiscalitem")
public class NotaFiscalItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="doks_preco_agendado")
	private BigDecimal precoAgendado;
	
	@Column(name="doks_data_agendada")
	private LocalDate dataAgendada;
	
	@Column(name="doks_usuario_nome_agendado")
	private String usuarioAgendado;
	@Column(name="doks_data_inclusao")
	private LocalDateTime dataInclusao;
	@Column(name=" doks_revisado")
	private Boolean revisaod;

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPrecoAgendado() {
		return precoAgendado;
	}

	public void setPrecoAgendado(BigDecimal precoAgendado) {
		this.precoAgendado = precoAgendado;
	}

	public LocalDate getDataAgendada() {
		return dataAgendada;
	}

	public void setDataAgendada(LocalDate dataAgendada) {
		this.dataAgendada = dataAgendada;
	}
	
	

	public String getUsuarioAgendado() {
		return usuarioAgendado;
	}

	public void setUsuarioAgendado(String usuarioAgendado) {
		this.usuarioAgendado = usuarioAgendado;
	}

	public Boolean getRevisaod() {
		return revisaod;
	}

	public void setRevisaod(Boolean revisaod) {
		this.revisaod = revisaod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscalItem other = (NotaFiscalItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
