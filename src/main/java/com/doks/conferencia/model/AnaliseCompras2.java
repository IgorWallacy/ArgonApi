package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doks_analise_compras_2")
public class AnaliseCompras2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String produto;
	
	private String ean;
	
	private String codigo;
	
	private String cfop;
	
	private Integer idfilial;
	

	private BigDecimal embalagem;
	
	private String unidade_compra;
	
	private Integer id_unidade_compra;
	
	private String unidade_venda;
	
	private BigDecimal ultimoprecocompra;
	
	private BigDecimal preco_medio_venda;
	
	private BigDecimal custo_unitario;
	
	private BigDecimal customedio;
	
	private BigDecimal quantidade_compra;
	
	private BigDecimal quantidade_comprada;
	
	 private BigDecimal quantidade_vendida;
	
	private BigDecimal saldo_estoque;
	
	private Integer numeronfultcompra;
	
	private String fornecedor;
	
	private LocalDateTime data_inclusao;

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

	public BigDecimal getUltimoprecocompra() {
		return ultimoprecocompra;
	}

	public void setUltimoprecocompra(BigDecimal ultimoprecocompra) {
		this.ultimoprecocompra = ultimoprecocompra;
	}



	public BigDecimal getCustomedio() {
		return customedio;
	}

	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	
	public Integer getIdfilial() {
		return idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}
	
	
	
	public Integer getId_unidade_compra() {
		return id_unidade_compra;
	}

	public void setId_unidade_compra(Integer id_unidade_compra) {
		this.id_unidade_compra = id_unidade_compra;
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
		AnaliseCompras2 other = (AnaliseCompras2) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BigDecimal getQuantidade_comprada() {
		return quantidade_comprada;
	}

	public void setQuantidade_comprada(BigDecimal quantidade_comprada) {
		this.quantidade_comprada = quantidade_comprada;
	}

	public BigDecimal getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(BigDecimal embalagem) {
		this.embalagem = embalagem;
	}

	public BigDecimal getCusto_unitario() {
		return custo_unitario;
	}

	public void setCusto_unitario(BigDecimal custo_unitario) {
		this.custo_unitario = custo_unitario;
	}

	public BigDecimal getSaldo_estoque() {
		return saldo_estoque;
	}

	public void setSaldo_estoque(BigDecimal saldo_estoque) {
		this.saldo_estoque = saldo_estoque;
	}

	public BigDecimal getQuantidade_vendida() {
		return quantidade_vendida;
	}

	public void setQuantidade_vendida(BigDecimal quantidade_vendida) {
		this.quantidade_vendida = quantidade_vendida;
	}

	public Integer getNumeronfultcompra() {
		return numeronfultcompra;
	}

	public void setNumeronfultcompra(Integer numeronfultcompra) {
		this.numeronfultcompra = numeronfultcompra;
	}

	public LocalDateTime getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(LocalDateTime data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public String getUnidade_compra() {
		return unidade_compra;
	}

	public void setUnidade_compra(String unidade_compra) {
		this.unidade_compra = unidade_compra;
	}

	public String getUnidade_venda() {
		return unidade_venda;
	}

	public void setUnidade_venda(String unidade_venda) {
		this.unidade_venda = unidade_venda;
	}

	public BigDecimal getPreco_medio_venda() {
		return preco_medio_venda;
	}

	public void setPreco_medio_venda(BigDecimal preco_medio_venda) {
		this.preco_medio_venda = preco_medio_venda;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public BigDecimal getQuantidade_compra() {
		return quantidade_compra;
	}

	public void setQuantidade_compra(BigDecimal quantidade_compra) {
		this.quantidade_compra = quantidade_compra;
	}


	
	
	

}
