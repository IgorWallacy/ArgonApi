package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doks_tabela_preco_display")
public class TabelaPrecoDisplay {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String codigo;
	
	private String produto;
	
	private BigDecimal preco;
	
	private BigDecimal precopromocao;
	
	private BigDecimal precopromocaofamilia;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPrecopromocao() {
		return precopromocao;
	}

	public void setPrecopromocao(BigDecimal precopromocao) {
		this.precopromocao = precopromocao;
	}

	public BigDecimal getPrecopromocaofamilia() {
		return precopromocaofamilia;
	}

	public void setPrecopromocaofamilia(BigDecimal precopromocaofamilia) {
		this.precopromocaofamilia = precopromocaofamilia;
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
		TabelaPrecoDisplay other = (TabelaPrecoDisplay) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
