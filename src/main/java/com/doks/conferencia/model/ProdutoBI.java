package com.doks.conferencia.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="doks_produto_bi")
@Entity
public class ProdutoBI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="filial")
	private String filial;
	
	@Column(name="preco_medio_venda")
	private BigDecimal precoMedioVenda;
	
	@Column(name="quantidade_vendida")
	private BigDecimal quantidadeVendida;
	
	@Column(name="quantidade_comprada")
	private BigDecimal quantidadeComprada;
	
	@Column(name="total_vendido")
	private BigDecimal totalVendido;
	
	@Column(name="total_desconto")
	private BigDecimal totalDesconto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	


	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public BigDecimal getTotalVendido() {
		return totalVendido;
	}

	public void setTotalVendido(BigDecimal totalVendido) {
		this.totalVendido = totalVendido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(BigDecimal quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public BigDecimal getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(BigDecimal quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	
	

	public BigDecimal getPrecoMedioVenda() {
		return precoMedioVenda;
	}

	public void setPrecoMedioVenda(BigDecimal precoMedioVenda) {
		this.precoMedioVenda = precoMedioVenda;
	}
	
	

	public BigDecimal getTotalDesconto() {
		return totalDesconto;
	}

	public void setTotalDesconto(BigDecimal totalDesconto) {
		this.totalDesconto = totalDesconto;
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
		ProdutoBI other = (ProdutoBI) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
