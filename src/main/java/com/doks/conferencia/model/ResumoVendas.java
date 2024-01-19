package com.doks.conferencia.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="doks_resumo_vendas")
public class ResumoVendas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal quantidade_cupom;

    private BigDecimal quantidade_cupom_cancelado;

    private BigDecimal quantidade_item_cancelado;

    private BigDecimal venda_cancelada_item;

    private BigDecimal venda_bruta;

    private BigDecimal venda_liquida;

    private BigDecimal venda_cancelada_cupom;

    private BigDecimal descontos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidade_cupom() {
        return quantidade_cupom;
    }

    public void setQuantidade_cupom(BigDecimal quantidade_cupom) {
        this.quantidade_cupom = quantidade_cupom;
    }

    public BigDecimal getQuantidade_cupom_cancelado() {
        return quantidade_cupom_cancelado;
    }

    public void setQuantidade_cupom_cancelado(BigDecimal quantidade_cupom_cancelado) {
        this.quantidade_cupom_cancelado = quantidade_cupom_cancelado;
    }

    public BigDecimal getVenda_bruta() {
        return venda_bruta;
    }

    public void setVenda_bruta(BigDecimal venda_bruta) {
        this.venda_bruta = venda_bruta;
    }

    public BigDecimal getVenda_liquida() {
        return venda_liquida;
    }

    public void setVenda_liquida(BigDecimal venda_liquida) {
        this.venda_liquida = venda_liquida;
    }

    public BigDecimal getQuantidade_item_cancelado() {
        return quantidade_item_cancelado;
    }

    public void setQuantidade_item_cancelado(BigDecimal quantidade_item_cancelado) {
        this.quantidade_item_cancelado = quantidade_item_cancelado;
    }

    public BigDecimal getVenda_cancelada_item() {
        return venda_cancelada_item;
    }

    public void setVenda_cancelada_item(BigDecimal venda_cancelada_item) {
        this.venda_cancelada_item = venda_cancelada_item;
    }

    public BigDecimal getVenda_cancelada_cupom() {
        return venda_cancelada_cupom;
    }

    public void setVenda_cancelada_cupom(BigDecimal venda_cancelada_cupom) {
        this.venda_cancelada_cupom = venda_cancelada_cupom;
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public void setDescontos(BigDecimal descontos) {
        this.descontos = descontos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResumoVendas that = (ResumoVendas) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
