package com.doks.conferencia.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="doks_recebimento")
public class Recebimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer evento;

    private String codigoCliente;

    private String nomeCliente;

    private String cnpjcpf;

    private String documento;

    private String tipo;

    private LocalDate emissao;

    private LocalDate vencimento;

    private BigDecimal valor;

    private BigDecimal saldo;

    private BigDecimal valorPago;

    private BigDecimal desconto;

    private BigDecimal jurosPago;

    private BigDecimal multaPaga;

    private LocalDate pagamento;

    private String numeronfce;

    private String usuariomovimentacao;

    private String loja;

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public Integer getEvento() {
        return evento;
    }

    public void setEvento(Integer evento) {
        this.evento = evento;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getJurosPago() {
        return jurosPago;
    }

    public void setJurosPago(BigDecimal jurosPago) {
        this.jurosPago = jurosPago;
    }

    public BigDecimal getMultaPaga() {
        return multaPaga;
    }

    public void setMultaPaga(BigDecimal multaPaga) {
        this.multaPaga = multaPaga;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public String getNumeronfce() {
        return numeronfce;
    }

    public void setNumeronfce(String numeronfce) {
        this.numeronfce = numeronfce;
    }

    public String getUsuariomovimentacao() {
        return usuariomovimentacao;
    }

    public void setUsuariomovimentacao(String usuariomovimentacao) {
        this.usuariomovimentacao = usuariomovimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recebimento that = (Recebimento) o;

        return Objects.equals(evento, that.evento);
    }

    @Override
    public int hashCode() {
        return evento != null ? evento.hashCode() : 0;
    }
}
