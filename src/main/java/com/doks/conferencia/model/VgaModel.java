package com.doks.conferencia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "doks_vga")
public class VgaModel {

	
	
	
	@Id
	private Integer finalizador;

	
	private String nomefinalizador;

//	private String nomeRede;

	//private String ecfSerie;

//	private String nomeBandeira;

//	private String consumidorNome;

	private BigDecimal total;
	


	public String getNomefinalizador() {
		return nomefinalizador;
	}

	public void setNomefinalizador(String nomefinalizador) {
		this.nomefinalizador = nomefinalizador;
	}

	

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getFinalizador() {
		return finalizador;
	}

	public void setFinalizador(Integer finalizador) {
		this.finalizador = finalizador;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finalizador == null) ? 0 : finalizador.hashCode());
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
		VgaModel other = (VgaModel) obj;
		if (finalizador == null) {
			if (other.finalizador != null)
				return false;
		} else if (!finalizador.equals(other.finalizador))
			return false;
		return true;
	}

/*	public String getEcfSerie() {
		return ecfSerie;
	}

	public void setEcfSerie(String ecfSerie) {
		this.ecfSerie = ecfSerie;
	}
*/
	
/*	public String getConsumidorNome() {
		return consumidorNome;
	}

	public void setConsumidorNome(String consumidorNome) {
		this.consumidorNome = consumidorNome;
	}
*/
	
	
	

}
