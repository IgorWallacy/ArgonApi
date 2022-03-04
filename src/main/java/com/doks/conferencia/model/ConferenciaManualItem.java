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
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "doks_conferencia_manual_item")
public class ConferenciaManualItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idconferencia", nullable = false)
	@NotNull
	private ConferenciaManual conferenciaManual;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto produto;
	
	@NotNull
	private BigDecimal quantidade;
	
	@NotNull
	@Column(name = "fatorconversao")
	private BigDecimal fatorConversao;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "idunidademedida", nullable = false)
	@NotNull
	private UnidadeMedida idUnidadeMedida;
		
	private LocalDate dataVencimento;
	
	private String observacao;
	
	
	@Column(name="quantidade_saida")
	private BigDecimal quantidadeSaida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	

	public ConferenciaManual getConferenciaManual() {
		return conferenciaManual;
	}

	public void setConferenciaManual(ConferenciaManual conferenciaManual) {
		this.conferenciaManual = conferenciaManual;
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

	public UnidadeMedida getIdUnidadeMedida() {
		return idUnidadeMedida;
	}

	public void setIdUnidadeMedida(UnidadeMedida idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	

	public BigDecimal getQuantidadeSaida() {
		return quantidadeSaida;
	}

	public void setQuantidadeSaida(BigDecimal quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
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
		ConferenciaManualItem other = (ConferenciaManualItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

	
	

}
