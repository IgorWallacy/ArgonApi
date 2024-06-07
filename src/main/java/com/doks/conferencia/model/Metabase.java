package com.doks.conferencia.model;

import javax.persistence.*;

@Entity
@Table(name="doks_metabase")
public class Metabase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Column(length = 1000)
    private String url;

    @Column(length = 1000)
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metabase metabase = (Metabase) o;

        return id.equals(metabase.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
