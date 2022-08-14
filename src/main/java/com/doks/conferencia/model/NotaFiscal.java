package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notafiscal")
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	@ManyToOne
	@JoinColumn(name = "idfilial")
	private Filial filial;
	
	@Column(name="numeronotafiscal")
	private String numeroNotaFiscal;
	
	@Column(name="razaosocial")
	private String razaoSocial;
	
	@Column(name="cnpjcpf")
	private String cnpjcpf;
	
	@Column(name="emissao")
	private LocalDate emissao;
	
	@Column(name="valortotalnota")
	private BigDecimal valorTotalNota;
	
	@Column(name="conferencia")
	private int conferencia;
	
	@Column(name="doks_precificado")
	private boolean precificado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpjcpf() {
		return cnpjcpf;
	}

	public void setCnpjcpf(String cnpjcpf) {
		this.cnpjcpf = cnpjcpf;
	}

	public BigDecimal getValorTotalNota() {
		return valorTotalNota;
	}

	public void setValorTotalNota(BigDecimal valorTotalNota) {
		this.valorTotalNota = valorTotalNota;
	}

	
	
	
	public boolean isPrecificado() {
		return precificado;
	}

	public void setPrecificado(boolean precificado) {
		this.precificado = precificado;
	}

	public LocalDate getEmissao() {
		return emissao;
	}

	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}

	public int getConferencia() {
		return conferencia;
	}

	public void setConferencia(int conferencia) {
		this.conferencia = conferencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		NotaFiscal other = (NotaFiscal) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
