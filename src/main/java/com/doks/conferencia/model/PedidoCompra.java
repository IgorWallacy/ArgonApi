package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="doks_pedidocompra")
public class PedidoCompra {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idfornecedor", nullable = false)
	@NotNull
	private Entidade fornecedor;
	
	@Column(name="dataprevisaoentrega")
	private LocalDate prazoEntrega;
	
	@ManyToOne
	@JoinColumn(name = "idcondicaopagamento", nullable = false)
	@NotNull
	private CondicaoPagamento condicaoPagamento;
	
	@Column(name = "dataemissao")
	private LocalDate dataEmissao;
	
	@Column(name="valortotal")
	private BigDecimal total;

	
	@Column(name="observacao")
	private String observacao;

	

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
		PedidoCompra other = (PedidoCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Entidade getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Entidade fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDate getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(LocalDate prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}



	

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
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

	
	
}
