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
@Table(name="doks_promocao_produto_familia")
public class PromocaoProdutoFamiliaFiltro {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "idpromocao", nullable = false)
	@NotNull
	private Promocao idPromocao; 
	
	@Column(name="nome_produto")
	private String nomeProduto;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", nullable = false)
	@NotNull
	private Produto produto;
	
	
	
	
	private BigDecimal valor;
	
	@Column(name="datainicial")
	private LocalDate dataInicio;
	
	@Column(name="datafinal")
	private LocalDate dataFinal;

	

	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	
	
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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
		PromocaoProdutoFamiliaFiltro other = (PromocaoProdutoFamiliaFiltro) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	
	
	
	
	
	

}
