package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doks_formacaoprecoproduto")
public class FormacaoPrecoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer idproduto;
	private String produto;
	private String ean;
	private String codigo;
	
	@Column(name="percentualmarkup")
	private BigDecimal percentualmarkup;

	@Column(name = "dataultimacompra")
	private LocalDate dataultimacompra;

	@Column(name = "dataalteracaopreco")
	private LocalDate dataalteracaopreco;

	@Column(name = "custoalteradopor")
	private String custoalteradopor;

	@Column(name = "dataprecocusto")
	private LocalDate dataprecocusto;

	private Integer idfilial;

	private String filial;

	private String hierarquia;
	
	private String hierarquiaII;
	
	private String hierarquiaIII;
	
	@Column(name="idfamilia")
	private Integer idfamilia;

	private String unidade;

	@Column(name = "estoque")
	private BigDecimal estoque;

	@Column(name = "preco")
	private BigDecimal preco;

	@Column(name = "precocusto")
	private BigDecimal precocusto;
	
	@Column(name="precopromocional")
	private BigDecimal precopromocional;
	
	@Column(name="precopromocionalfamilia")
	private BigDecimal precopromocionalfamilia;
	
	@Column(name="precopromocionalfilial")
	private BigDecimal precopromocionalfilial;
	
	@Column(name="doks_preco_agendado")
	private BigDecimal precoAgendado;
	
	@Column(name="doks_meta")
	private BigDecimal meta;
	
	@Column(name="doks_data_agendada")
	private LocalDate dataAgendada;
	
	@Column(name="doks_usuario_nome_agendado")
	private String usuarioAgendado;
	
	

	public BigDecimal getPercentualmarkup() {
		return percentualmarkup;
	}

	public void setPercentualmarkup(BigDecimal percentualmarkup) {
		this.percentualmarkup = percentualmarkup;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
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

	public Integer getIdfilial() {
		return idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setHierarquia(String hierarquia) {
		this.hierarquia = hierarquia;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPrecocusto() {
		return precocusto;
	}

	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}

	public BigDecimal getEstoque() {
		return estoque;
	}

	public void setEstoque(BigDecimal estoque) {
		this.estoque = estoque;
	}

	public String getHierarquia() {
		return hierarquia;
	}

	public LocalDate getDataultimacompra() {
		return dataultimacompra;
	}

	public void setDataultimacompra(LocalDate dataultimacompra) {
		this.dataultimacompra = dataultimacompra;
	}

	public String getCustoalteradopor() {
		return custoalteradopor;
	}

	public void setCustoalteradopor(String custoalteradopor) {
		this.custoalteradopor = custoalteradopor;
	}

	public LocalDate getDataprecocusto() {
		return dataprecocusto;
	}

	public void setDataprecocusto(LocalDate dataprecocusto) {
		this.dataprecocusto = dataprecocusto;
	}

	public LocalDate getDataalteracaopreco() {
		return dataalteracaopreco;
	}

	public void setDataalteracaopreco(LocalDate dataalteracaopreco) {
		this.dataalteracaopreco = dataalteracaopreco;
	}
	
	

	public BigDecimal getPrecopromocional() {
		return precopromocional;
	}

	public void setPrecopromocional(BigDecimal precopromocional) {
		this.precopromocional = precopromocional;
	}

	public BigDecimal getPrecopromocionalfamilia() {
		return precopromocionalfamilia;
	}

	public void setPrecopromocionalfamilia(BigDecimal precopromocionalfamilia) {
		this.precopromocionalfamilia = precopromocionalfamilia;
	}
	
	

	public BigDecimal getPrecopromocionalfilial() {
		return precopromocionalfilial;
	}

	public void setPrecopromocionalfilial(BigDecimal precopromocionalfilial) {
		this.precopromocionalfilial = precopromocionalfilial;
	}
	
	

	public String getHierarquiaII() {
		return hierarquiaII;
	}

	public void setHierarquiaII(String hierarquiaII) {
		this.hierarquiaII = hierarquiaII;
	}

	public String getHierarquiaIII() {
		return hierarquiaIII;
	}

	public void setHierarquiaIII(String hierarquiaIII) {
		this.hierarquiaIII = hierarquiaIII;
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
	
	
	
	

	public BigDecimal getMeta() {
		return meta;
	}

	public void setMeta(BigDecimal meta) {
		this.meta = meta;
	}

	public Integer getIdfamilia() {
		return idfamilia;
	}

	public void setIdfamilia(Integer idfamilia) {
		this.idfamilia = idfamilia;
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
		FormacaoPrecoProduto other = (FormacaoPrecoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
