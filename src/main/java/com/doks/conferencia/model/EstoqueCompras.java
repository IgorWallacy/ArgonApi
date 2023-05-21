package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doks_estoque_compras")
public class EstoqueCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nomefilial;

	private String codigoproduto;

	private String nomeproduto;
	
	private String unvenda;

	private String codigounidademedida;

	private String nomefornecedor;

	private BigDecimal total;

	private BigDecimal quantidadesaldoestoque;

	private BigDecimal quantidadeembalagem;

	private String numeronf;

	private String numeronfultcompra;
	
	private String condicaopagamento;

	private BigDecimal precoultimacompra;

	private BigDecimal quantidadecompra;
	
	private BigDecimal quantidadevendida;

	private BigDecimal precocusto;

	private LocalDate dataEmissao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomefilial() {
		return nomefilial;
	}

	public void setNomefilial(String nomefilial) {
		this.nomefilial = nomefilial;
	}

	public String getCodigoproduto() {
		return codigoproduto;
	}

	public void setCodigoproduto(String codigoproduto) {
		this.codigoproduto = codigoproduto;
	}

	public String getNomeproduto() {
		return nomeproduto;
	}

	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}

	public String getCodigounidademedida() {
		return codigounidademedida;
	}

	public void setCodigounidademedida(String codigounidademedida) {
		this.codigounidademedida = codigounidademedida;
	}

	public String getNomefornecedor() {
		return nomefornecedor;
	}

	public void setNomefornecedor(String nomefornecedor) {
		this.nomefornecedor = nomefornecedor;
	}

	public BigDecimal getPrecoultimacompra() {
		return precoultimacompra;
	}

	public void setPrecoultimacompra(BigDecimal precoultimacompra) {
		this.precoultimacompra = precoultimacompra;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getQuantidadesaldoestoque() {
		return quantidadesaldoestoque;
	}

	public void setQuantidadesaldoestoque(BigDecimal quantidadesaldoestoque) {
		this.quantidadesaldoestoque = quantidadesaldoestoque;
	}

	public BigDecimal getQuantidadeembalagem() {
		return quantidadeembalagem;
	}

	public void setQuantidadeembalagem(BigDecimal quantidadeembalagem) {
		this.quantidadeembalagem = quantidadeembalagem;
	}

	public String getNumeronfultcompra() {
		return numeronfultcompra;
	}

	public void setNumeronfultcompra(String numeronfultcompra) {
		this.numeronfultcompra = numeronfultcompra;
	}

	public BigDecimal getQuantidadecompra() {
		return quantidadecompra;
	}

	public void setQuantidadecompra(BigDecimal quantidadecompra) {
		this.quantidadecompra = quantidadecompra;
	}

	public BigDecimal getPrecocusto() {
		return precocusto;
	}

	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getNumeronf() {
		return numeronf;
	}

	public void setNumeronf(String numeronf) {
		this.numeronf = numeronf;
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
		EstoqueCompras other = (EstoqueCompras) obj;
		return Objects.equals(id, other.id);
	}

	public BigDecimal getQuantidadevendida() {
		return quantidadevendida;
	}

	public void setQuantidadevendida(BigDecimal quantidadevendida) {
		this.quantidadevendida = quantidadevendida;
	}

	public String getUnvenda() {
		return unvenda;
	}

	public void setUnvenda(String unvenda) {
		this.unvenda = unvenda;
	}

	public String getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(String condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}

}
