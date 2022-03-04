package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name ="conferenciacegaitem")
public class ConferenciaCegaItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idconferencia" , nullable = false)
	@NotNull
	private NotaFiscalConfFisica idConferencia;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto idProduto;
	
	@ManyToOne
	@JoinColumn(name = "idunidademedida", nullable = false)
	@NotNull
	private UnidadeMedida idUnidadeMedida;
	
	@NotNull
	private BigDecimal quantidade;
	
	@NotNull
	@Column(name = "fatorconversao")
	private BigDecimal fatorConversao;
	
	@NotNull
	private LocalDate validade;
	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NotaFiscalConfFisica getIdConferencia() {
		return idConferencia;
	}

	public void setIdConferencia(NotaFiscalConfFisica idConferencia) {
		this.idConferencia = idConferencia;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public UnidadeMedida getIdUnidadeMedida() {
		return idUnidadeMedida;
	}

	public void setIdUnidadeMedida(UnidadeMedida idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}

	

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	

	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}
	
	

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
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
		ConferenciaCegaItem other = (ConferenciaCegaItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
