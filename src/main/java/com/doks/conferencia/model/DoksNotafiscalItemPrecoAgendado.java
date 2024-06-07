package com.doks.conferencia.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="doks_notafiscalitem_preco_agendado")
public class DoksNotafiscalItemPrecoAgendado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = true)
    private Integer id;

    @Column(name="idnotafiscal")
    private Integer idnotafiscal;
    @Column(name="idproduto")
    private Integer idproduto;

    @Column(name="doks_preco_agendado")
    private BigDecimal precoAgendado;

    @Column(name="doks_data_agendada")
    private LocalDate dataAgendada;

    @Column(name="doks_usuario_nome_agendado")
    private String usuarioAgendado;
    @Column(name="doks_data_inclusao")
    private LocalDateTime dataInclusao;
    @Column(name=" doks_revisado")
    private Boolean revisaod;

    public Integer getIdnotafiscal() {
        return idnotafiscal;
    }

    public void setIdnotafiscal(Integer idnotafiscal) {
        this.idnotafiscal = idnotafiscal;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecoAgendado() {
        return precoAgendado;
    }

    public void setPrecoAgendado(BigDecimal precoAgendado) {
        this.precoAgendado = precoAgendado;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }



    public String getUsuarioAgendado() {
        return usuarioAgendado;
    }

    public void setUsuarioAgendado(String usuarioAgendado) {
        this.usuarioAgendado = usuarioAgendado;
    }

    public Boolean getRevisaod() {
        return revisaod;
    }

    public void setRevisaod(Boolean revisaod) {
        this.revisaod = revisaod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoksNotafiscalItemPrecoAgendado)) return false;

        DoksNotafiscalItemPrecoAgendado that = (DoksNotafiscalItemPrecoAgendado) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
