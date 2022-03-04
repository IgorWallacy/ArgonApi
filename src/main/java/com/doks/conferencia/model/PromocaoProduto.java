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

@Table(name = "promocaoproduto")
@Entity
public class PromocaoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "idpromocao", nullable = false)
	@NotNull
	private Promocao idPromocao; 
	
	
	
	@Column(name="codigo")
	private String codigo;
	
	
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto idProduto;
	
	private BigDecimal valor;
	
	
	

	

	public Promocao getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(Promocao idPromocao) {
		this.idPromocao = idPromocao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		PromocaoProduto other = (PromocaoProduto) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	
	
	
	

}
