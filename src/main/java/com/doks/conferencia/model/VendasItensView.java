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

@Table(name="doks_vendas_itens_view")
@Entity
public class VendasItensView {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="data_emissao")
	private LocalDate dataEmissao;
	
	@Column(name="nome_promocao")
	private String promocaoNome;
	
	@Column(name="promocao")
	private Integer promocao;

	@Column(name="condicaopagamento")
	private String condicaopagamento;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="grupo_pai")
	private String grupoPai;
	
	@Column(name="grupo_filho")
	private String grupoFilho;
	
	@Column(name="grupo_neto")
	private String grupoNeto;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="quantidade")
	private BigDecimal quantidade;
	@Column(name="devolucao")
	private BigDecimal devolucao;

	@Column(name="quantidade_devolvida")
	private BigDecimal quantidadeDevolvida;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	@Column(name="desconto")
	private BigDecimal desconto;
	
	@Column(name="preco_ultima_compra")
	private BigDecimal precoultimacompra;
	
	@Column(name="preco_ultima_compra_total")
	private BigDecimal precoultimacompratotal;
	
	@Column(name="preco_unitario")
	private BigDecimal precounitario;
	
	@Column(name="codigo_filial")
	private String codigoFilial;
	
	@Column(name="nome_filial")
	private String nomeFilial;
	
	@Column(name="meta")
	private BigDecimal meta;
	




	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getGrupoPai() {
		return grupoPai;
	}

	public void setGrupoPai(String grupoPai) {
		this.grupoPai = grupoPai;
	}

	public String getGrupoFilho() {
		return grupoFilho;
	}

	public void setGrupoFilho(String grupoFilho) {
		this.grupoFilho = grupoFilho;
	}

	public String getGrupoNeto() {
		return grupoNeto;
	}

	public void setGrupoNeto(String grupoNeto) {
		this.grupoNeto = grupoNeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoultimacompra() {
		return precoultimacompra;
	}

	public void setPrecoultimacompra(BigDecimal precoultimacompra) {
		this.precoultimacompra = precoultimacompra;
	}

	public BigDecimal getPrecounitario() {
		return precounitario;
	}

	public void setPrecounitario(BigDecimal precounitario) {
		this.precounitario = precounitario;
	}

	public String getCodigoFilial() {
		return codigoFilial;
	}

	public void setCodigoFilial(String codigoFilial) {
		this.codigoFilial = codigoFilial;
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}

	public String getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(String condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}

	public String getPromocaoNome() {
		return promocaoNome;
	}

	public void setPromocaoNome(String promocaoNome) {
		this.promocaoNome = promocaoNome;
	}

	public Integer getPromocao() {
		return promocao;
	}

	public void setPromocao(Integer promocao) {
		this.promocao = promocao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

	public BigDecimal getMeta() {
		return meta;
	}

	public void setMeta(BigDecimal meta) {
		this.meta = meta;
	}

	public BigDecimal getPrecoultimacompratotal() {
		return precoultimacompratotal;
	}

	public void setPrecoultimacompratotal(BigDecimal precoultimacompratotal) {
		this.precoultimacompratotal = precoultimacompratotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(BigDecimal devolucao) {
		this.devolucao = devolucao;
	}

	public BigDecimal getQuantidadeDevolvida() {
		return quantidadeDevolvida;
	}

	public void setQuantidadeDevolvida(BigDecimal quantidadeDevolvida) {
		this.quantidadeDevolvida = quantidadeDevolvida;
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
		VendasItensView other = (VendasItensView) obj;
		return Objects.equals(id, other.id);
	}



	
	
	
	
	
	
	
}
