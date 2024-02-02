package com.doks.conferencia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="doks_nfce")
public class NFCe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "formaemissaonfce")
    private Integer formaEmissaoNfce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormaEmissaoNfce() {
        return formaEmissaoNfce;
    }

    public void setFormaEmissaoNfce(Integer formaEmissaoNfce) {
        this.formaEmissaoNfce = formaEmissaoNfce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NFCe nfCe = (NFCe) o;

        return Objects.equals(id, nfCe.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
