package com.doks.conferencia.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "doks_pedido_item_compra")
public class PedidoItemCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private Integer embalagem;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto idproduto;
	
	@ManyToOne
	@JoinColumn(name = "idpedido", nullable = false)
	@NotNull
	private PedidoCompra idpedido;
	
	private BigDecimal preco;
	
	private BigDecimal quantidade1;
	
	private BigDecimal quantidade2;
	
	@Column(name="quantidade_venda")
	private BigDecimal quantidadeVenda;
	
	@Column(name="unidade_compra")
	private String unidadeCompra;

	



	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Integer embalagem) {
		this.embalagem = embalagem;
	}

	public Produto getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Produto idproduto) {
		this.idproduto = idproduto;
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

	public BigDecimal getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(BigDecimal quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public String getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(String unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}
	
	

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public PedidoCompra getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(PedidoCompra idpedido) {
		this.idpedido = idpedido;
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
		PedidoItemCompra other = (PedidoItemCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
	

	
	
	

}
