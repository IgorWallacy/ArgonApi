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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "doks_pedidocompra_item")
public class PedidoItemCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	private Integer embalagem;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto idproduto;
	
	@ManyToOne
	@JoinColumn(name = "idpedidocompra", nullable = false)
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PedidoCompra idpedido;
	
	@Column(name="preco")
	private BigDecimal preco;
	
	@Column(name="quantidade")
	private BigDecimal quantidade;
	
	@ManyToOne
	@JoinColumn(name = "filial", nullable = false)
	@NotNull
	private Filial filial;
	
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

	

	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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

	
	
	

	

	public UnidadeMedida getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(UnidadeMedida unidadeCompra) {
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
	
	

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}



	

	
	
	

}
