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
@Table(name="doks_produtos_sem_venda")
public class ProdutosSemVendas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String produto;
	
	private String codigo;
	
	private String ean;
	
	private String grupoI;
	
	private String grupoII;
	
	private String grupoIII;
	
	private String fornecedor;
	
	private LocalDate ultimacompra;
	
	private BigDecimal saldo_estoque;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getGrupoI() {
		return grupoI;
	}

	public void setGrupoI(String grupoI) {
		this.grupoI = grupoI;
	}

	public String getGrupoII() {
		return grupoII;
	}

	public void setGrupoII(String grupoII) {
		this.grupoII = grupoII;
	}

	public String getGrupoIII() {
		return grupoIII;
	}

	public void setGrupoIII(String grupoIII) {
		this.grupoIII = grupoIII;
	}

	public LocalDate getUltimacompra() {
		return ultimacompra;
	}

	public void setUltimacompra(LocalDate ultimacompra) {
		this.ultimacompra = ultimacompra;
	}

	public BigDecimal getSaldo_estoque() {
		return saldo_estoque;
	}

	public void setSaldo_estoque(BigDecimal saldo_estoque) {
		this.saldo_estoque = saldo_estoque;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
		ProdutosSemVendas other = (ProdutosSemVendas) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
