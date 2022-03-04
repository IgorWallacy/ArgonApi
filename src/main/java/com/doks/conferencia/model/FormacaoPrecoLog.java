package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="formacaoprecolog")
public class FormacaoPrecoLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "idfilial", nullable = false)
	@NotNull
	private Filial filial;
	
	@Column(name = "precovendaajustado")
	private BigDecimal precovendaajustado;
	
	@Column(name = "precovenda")
	private BigDecimal precovenda;
	
	@Column(name="datahora")
	private LocalDateTime dataHora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	

	

	public BigDecimal getPrecovendaajustado() {
		return precovendaajustado;
	}

	public void setPrecovendaajustado(BigDecimal precovendaajustado) {
		this.precovendaajustado = precovendaajustado;
	}

	public BigDecimal getPrecovenda() {
		return precovenda;
	}

	public void setPrecovenda(BigDecimal precovenda) {
		this.precovenda = precovenda;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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
		FormacaoPrecoLog other = (FormacaoPrecoLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
