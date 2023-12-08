package com.doks.conferencia.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="doks_produto_contagem")
public class ProdutoContagemInventarioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   @NotNull(message = "O produto não pode ser nulo")
    @Column(name="idproduto")
    private Integer idproduto;

   @NotNull(message = "O id do inventario esta nulo")
    @Column(name="idinventario")
    private Long idinventario;
    @NotNull(message = "Produto não pode ser nulo")
    @Column(name="produto")
    private String produto;

    @Column(name="unidade_medida")
    private String unidadeMedida;
    @Column(name="ean")
    private String ean;

    @Column(name="codigo")
    private String codigo;
    @Column(name="loja")
    private String loja;
   @NotNull(message = "o id da filial nao pode ser nulo")
    @Column(name="idfilial")
    private Integer idfilial;
    @NotNull(message = "A Quantidade não pode ser nula")
    @Column(name="quantidade_lida",columnDefinition="Decimal(10,3) default '100.000'" )
    private Double quantidadeLida;

    @Column(name="quantidade_estoque",columnDefinition="Decimal(10,3) default '100.000'" )
    private Double quantidadeEstoque;

    @Column(name="quantidade_vendida_durante",columnDefinition="Decimal(10,3) default '100.000'")
    private Double QuantidadeVendidaDurante;

   @Column(name = "divergencia")
   private BigDecimal divergencia;

    @Column(name="nome_usuario")
    private String nomeUsuario;

    @Column(name = "entrada")
    private LocalDateTime entrada;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public Integer getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(Integer idfilial) {
        this.idfilial = idfilial;
    }

    public Double getQuantidadeLida() {
        return quantidadeLida;
    }

    public void setQuantidadeLida(Double quantidadeLida) {
        this.quantidadeLida = quantidadeLida;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(Long idinventario) {
        this.idinventario = idinventario;
    }

    public BigDecimal getDivergencia() {
        return divergencia;
    }

    public void setDivergencia(BigDecimal divergencia) {
        this.divergencia = divergencia;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getQuantidadeVendidaDurante() {
        return QuantidadeVendidaDurante;
    }

    public void setQuantidadeVendidaDurante(Double quantidadeVendidaDurante) {
        QuantidadeVendidaDurante = quantidadeVendidaDurante;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoContagemInventarioItem that = (ProdutoContagemInventarioItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
