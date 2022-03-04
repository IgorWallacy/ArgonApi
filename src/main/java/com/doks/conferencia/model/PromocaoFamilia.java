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

@Table (name = "promocaofamilia")
@Entity
public class PromocaoFamilia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idfamilia", nullable = false)
	@NotNull
	private Familia idFamilia;
	
	@ManyToOne
	@JoinColumn(name = "idpromocao", nullable = false)
	@NotNull
	private Promocao idPromocao;
	
	@Column(name="codigopromocao")
	private String codigo;
	
	private BigDecimal valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Familia getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Familia idFamilia) {
		this.idFamilia = idFamilia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

	public Promocao getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(Promocao idPromocao) {
		this.idPromocao = idPromocao;
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
		PromocaoFamilia other = (PromocaoFamilia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
