package com.doks.conferencia.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="configuracaopaineldepreco")
public class ConfiguracaoPainelDePreco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer idtabelapreco;
	
	@Column(name="prodtempotrocapaginaemsegundos")
	private Integer tempoPagina;
	
	private Integer numprodutosporpagina;
	
	private Integer idfilial;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdtabelapreco() {
		return idtabelapreco;
	}

	public void setIdtabelapreco(Integer idtabelapreco) {
		this.idtabelapreco = idtabelapreco;
	}

	public Integer getTempoPagina() {
		return tempoPagina;
	}

	public void setTempoPagina(Integer tempoPagina) {
		this.tempoPagina = tempoPagina;
	}

	public Integer getNumprodutosporpagina() {
		return numprodutosporpagina;
	}

	public void setNumprodutosporpagina(Integer numprodutosporpagina) {
		this.numprodutosporpagina = numprodutosporpagina;
	}

	public Integer getIdfilial() {
		return idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
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
		ConfiguracaoPainelDePreco other = (ConfiguracaoPainelDePreco) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
