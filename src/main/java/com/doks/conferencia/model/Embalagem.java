package com.doks.conferencia.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "doks_embalagem_compra")
public class Embalagem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private BigDecimal fatorConversao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public BigDecimal getFatorConversao() {
        return fatorConversao;
    }

    public void setFatorConversao(BigDecimal fatorConversao) {
        this.fatorConversao = fatorConversao;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Embalagem embalagem)) return false;

        return getId().equals(embalagem.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
