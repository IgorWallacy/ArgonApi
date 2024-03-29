package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="doks_precificacao")
public class PrecificacaoItem {
	
	
	
	@Id
	private Integer id;
	
	@Column(name="idfilial")
	private Integer idfilial;
	
	@Column(name="idnotafiscal")
	private Integer idnotafiscal;
	
	@Column(name = "nome")
	private String nomeFilial;
	
	@Column(name="numeronotafiscal")
	private Integer numeronotafiscal;
	
	@Column(name="razaosocial")
	private String razaosocial;
	
	@Column(name="entradasaida")
	private LocalDateTime entradasaida;
	
	
	@Column(name="idproduto")
	private Integer idproduto;
	
	
	@Column(name="ean")
	private String ean;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="idfamilia")
	private String idfamilia;
	
	@Column(name="precounitario")
	private BigDecimal precounitario;
	
	@Column(name="precopromocional")
	private BigDecimal precopromocional;
	
	@Column(name="precopromocionalfamilia")
	private BigDecimal precopromocionalfamilia;
	
	@Column(name="percentualmarkup")
	private BigDecimal percentualmarkup;
	
	@Column(name="percentualmarkdown")
	private BigDecimal percentualmarkdown;
	
	@Column(name="precocusto")
	private BigDecimal precocusto;
	
	@Column(name="precoagendado")
	private BigDecimal precoagendado;
	
	@Column(name="precoatual")
	private BigDecimal precoAtual;
	
	@Column(name="dataagendada")
	private LocalDate dataagendada;

	
	@Column(name="usuarioAgendado")
	private String usuarioAgendado;
	
	@Column(name="doks_precificado")
	private Boolean precificado;
	@Column(name="cfop")
	private String cfop;
	@Column(name="doks_data_inclusao")
	private LocalDateTime dataInclusao;


	@Column(name="doks_revisado", columnDefinition = "bit default 0")
	private Boolean revisado;
	
	public Boolean getPrecificado() {
		return precificado;
	}

	public void setPrecificado(Boolean precificado) {
		this.precificado = precificado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdfilial() {
		return idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}

	public Integer getNumeronotafiscal() {
		return numeronotafiscal;
	}

	public void setNumeronotafiscal(Integer numeronotafiscal) {
		this.numeronotafiscal = numeronotafiscal;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecounitario() {
		return precounitario;
	}

	public void setPrecounitario(BigDecimal precounitario) {
		this.precounitario = precounitario;
	}

	public BigDecimal getPrecocusto() {
		return precocusto;
	}

	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}


	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public BigDecimal getPrecopromocionalfamilia() {
		return precopromocionalfamilia;
	}

	public void setPrecopromocionalfamilia(BigDecimal precopromocionalfamilia) {
		this.precopromocionalfamilia = precopromocionalfamilia;
	}

	public BigDecimal getPrecopromocional() {
		return precopromocional;
	}

	public void setPrecopromocional(BigDecimal precopromocional) {
		this.precopromocional = precopromocional;
	}

	public Integer getIdnotafiscal() {
		return idnotafiscal;
	}

	public void setIdnotafiscal(Integer idnotafiscal) {
		this.idnotafiscal = idnotafiscal;
	}

	public BigDecimal getPrecoAtual() {
		return precoAtual;
	}

	public void setPrecoAtual(BigDecimal precoAtual) {
		this.precoAtual = precoAtual;
	}

	public String getIdfamilia() {
		return idfamilia;
	}

	public void setIdfamilia(String idfamilia) {
		this.idfamilia = idfamilia;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}


	

	public BigDecimal getPrecoagendado() {
		return precoagendado;
	}

	public void setPrecoagendado(BigDecimal precoagendado) {
		this.precoagendado = precoagendado;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
	
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	
	public BigDecimal getPercentualmarkup() {
		return percentualmarkup;
	}

	public void setPercentualmarkup(BigDecimal percentualmarkup) {
		this.percentualmarkup = percentualmarkup;
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}


	public Boolean getRevisado() {
		return revisado;
	}

	public void setRevisado(Boolean revisado) {
		this.revisado = revisado;
	}

	public LocalDate getDataagendada() {
		return dataagendada;
	}

	public void setDataagendada(LocalDate dataagendada) {
		this.dataagendada = dataagendada;
	}

	public LocalDateTime getEntradasaida() {
		return entradasaida;
	}

	public void setEntradasaida(LocalDateTime entradasaida) {
		this.entradasaida = entradasaida;
	}
	
	

	public BigDecimal getPercentualmarkdown() {
		return percentualmarkdown;
	}

	public void setPercentualmarkdown(BigDecimal percentualmarkdown) {
		this.percentualmarkdown = percentualmarkdown;
	}

	public String getUsuarioAgendado() {
		return usuarioAgendado;
	}

	public void setUsuarioAgendado(String usuarioAgendado) {
		this.usuarioAgendado = usuarioAgendado;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
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
		PrecificacaoItem other = (PrecificacaoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	

	
	
	
	
	
	
	
	

}
