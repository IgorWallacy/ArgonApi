package com.doks.conferencia.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hierarquia")
public class Hierarquia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String codigo;
	
	private String nome;
	
	@Column(name="doks_grupo_pai")
	private String grupo_pai;
	
	@Column(name="doks_grupo_filho")
	private String grupo_filho;
	
	@Column(name="doks_meta")
	private BigDecimal meta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	public BigDecimal getMeta() {
		return meta;
	}

	public void setMeta(BigDecimal meta) {
		this.meta = meta;
	}
	
	

	public String getGrupo_pai() {
		return grupo_pai;
	}

	public void setGrupo_pai(String grupo_pai) {
		this.grupo_pai = grupo_pai;
	}

	public String getGrupo_filho() {
		return grupo_filho;
	}

	public void setGrupo_filho(String grupo_filho) {
		this.grupo_filho = grupo_filho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Hierarquia other = (Hierarquia) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	 
}
