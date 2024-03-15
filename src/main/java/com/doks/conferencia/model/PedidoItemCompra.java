package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pedidocompraitem")
public class PedidoItemCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	private Integer embalagem;


	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto idproduto;


	@Column(name = "idpedidocompra")
	private Integer idpedido;

	@Column(name="preco")
	private BigDecimal preco;

	@Column(name="doks_preco_venda_na_data")
	private BigDecimal precoVenda;

	@Column(name="quantidade")
	private BigDecimal quantidade;

	/*
	@ManyToOne
	@JoinColumn(name = "filial", nullable = false)
	@NotNull
	private Filial filial;
	*/

	@Column(name="doks_quantidade1")
	private BigDecimal quantidade1;

	@Column(name="doks_quantidade2")
	private BigDecimal quantidade2;

	@Column(name="fatorconversao")
	private BigDecimal fatorConversao;

	@Column(name="doks_quantidade_venda")
	private BigDecimal quantidadeVenda;

	@ManyToOne
	@JoinColumn(name = "idunidademedida", nullable = false)
	@NotNull
	private UnidadeMedida unidadeCompra;

	@Column(name="total")
    private BigDecimal total;


	@Column(name="observacao" , columnDefinition="text")
	private String observacao;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Integer embalagem) {
		this.embalagem = embalagem;
	}



	public Integer getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(Integer idpedido) {
		this.idpedido = idpedido;
	}

	public Produto getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Produto idproduto) {
		this.idproduto = idproduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidade1() {
		return quantidade1;
	}

	public void setQuantidade1(BigDecimal quantidade1) {
		this.quantidade1 = quantidade1;
	}

	public BigDecimal getQuantidade2() {
		return quantidade2;
	}

	public void setQuantidade2(BigDecimal quantidade2) {
		this.quantidade2 = quantidade2;
	}

	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

	public BigDecimal getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(BigDecimal quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public UnidadeMedida getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(UnidadeMedida unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PedidoItemCompra that = (PedidoItemCompra) o;

        return Objects.equals(id, that.id);
    }

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
