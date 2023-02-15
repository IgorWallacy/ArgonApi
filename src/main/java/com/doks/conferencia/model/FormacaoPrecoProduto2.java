package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formacaoprecoproduto")
public class FormacaoPrecoProduto2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="doks_preco_agendado")
	private BigDecimal precoAgendado;
	
	@Column(name="doks_data_agendada")
	private LocalDate dataAgendada;
	
	@Column(name="doks_usuario_nome_agendado")
	private String usuarioAgendado;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormacaoPrecoProduto2 other = (FormacaoPrecoProduto2) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
