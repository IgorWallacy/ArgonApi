package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "doks_conferencia_manual")
public class ConferenciaManual {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String codigo;
	
	private String numeroNotaFiscal;
		
	private String serieNotaFiscal;
	
	private BigDecimal valorNotaFiscal;
	
	private LocalDate dataEntrada;
	
	private LocalDate dataEmissao;
	
	private Boolean status;
	
	private Boolean finalizado;
	
	@ManyToOne
	@JoinColumn(name = "identidade", nullable = false)
	@NotNull
	private Entidade fornecedor;
	
	
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	@NotNull
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idfilial", nullable = false)
	@NotNull
	private Filial idfilial;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}



	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}



	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}



	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}



	public BigDecimal getValorNotaFiscal() {
		return valorNotaFiscal;
	}



	public void setValorNotaFiscal(BigDecimal valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}



	public LocalDate getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public LocalDate getDataEmissao() {
		return dataEmissao;
	}



	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}



	public Entidade getFornecedor() {
		return fornecedor;
	}



	public void setFornecedor(Entidade fornecedor) {
		this.fornecedor = fornecedor;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	



	public Filial getIdfilial() {
		return idfilial;
	}



	public void setIdfilial(Filial idfilial) {
		this.idfilial = idfilial;
	}
	
	



	public Boolean getStatus() {
		return status;
	}



	public void setStatus(Boolean status) {
		this.status = status;
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
		ConferenciaManual other = (ConferenciaManual) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public Boolean getFinalizado() {
		return finalizado;
	}



	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	
	
	

}
