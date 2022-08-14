package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doks_analise_compras")
public class AnaliseCompras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer idproduto;

	private String produto;
	
	private String codigo;
	
	private String ean;
	
	private String fornecedor;
	
	private LocalDate data_inclusao;
	
	private String cfop;
	
	private String cfop_descricao;
	
	private String embalagem;
	
	private String saldo_estoque;
	
	
	private String unidade_compra;
	
	private String unidade_venda;
	
	private BigDecimal quantidade_comprada;
	
	private BigDecimal quantidade_vendida;
	
	private BigDecimal total_comprado;
	
	private BigDecimal custo_unitario;
	
	private BigDecimal preco_medio_venda;
	
	
	
	
  


	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	

	public String getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	public String getCfop_descricao() {
		return cfop_descricao;
	}

	public void setCfop_descricao(String cfop_descricao) {
		this.cfop_descricao = cfop_descricao;
	}

	public BigDecimal getPreco_medio_venda() {
		return preco_medio_venda;
	}

	public void setPreco_medio_venda(BigDecimal preco_medio_venda) {
		this.preco_medio_venda = preco_medio_venda;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade_comprada() {
		return quantidade_comprada;
	}

	public void setQuantidade_comprada(BigDecimal quantidade_comprada) {
		this.quantidade_comprada = quantidade_comprada;
	}

	

	public String getUnidade_venda() {
		return unidade_venda;
	}

	public void setUnidade_venda(String unidade_venda) {
		this.unidade_venda = unidade_venda;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getTotal_comprado() {
		return total_comprado;
	}

	public void setTotal_comprado(BigDecimal total_comprado) {
		this.total_comprado = total_comprado;
	}

	public String getUnidade_compra() {
		return unidade_compra;
	}

	public void setUnidade_compra(String unidade_compra) {
		this.unidade_compra = unidade_compra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	public LocalDate getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(LocalDate data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public BigDecimal getCusto_unitario() {
		return custo_unitario;
	}

	public void setCusto_unitario(BigDecimal custo_unitario) {
		this.custo_unitario = custo_unitario;
	}
	
	

	public BigDecimal getQuantidade_vendida() {
		return quantidade_vendida;
	}

	public void setQuantidade_vendida(BigDecimal quantidade_vendida) {
		this.quantidade_vendida = quantidade_vendida;
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
		AnaliseCompras other = (AnaliseCompras) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public String getSaldo_estoque() {
		return saldo_estoque;
	}

	public void setSaldo_estoque(String saldo_estoque) {
		this.saldo_estoque = saldo_estoque;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}


	
	
	
	
	
	
	

}
