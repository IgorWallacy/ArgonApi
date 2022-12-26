package com.doks.conferencia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statuspdv")
public class StatusPdv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String filial;
	
	private Integer pdv;
	
	private String usuario;
	
	
	private LocalDateTime ultimoping;
	
	private LocalDateTime ultimacarga;
	
	private String versao;
	
	private String ipservidor;
	
	private String ip;
	
	private BigDecimal currenttimemillis;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public Integer getPdv() {
		return pdv;
	}

	public void setPdv(Integer pdv) {
		this.pdv = pdv;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getUltimoping() {
		return ultimoping;
	}

	public void setUltimoping(LocalDateTime ultimoping) {
		this.ultimoping = ultimoping;
	}

	public LocalDateTime getUltimacarga() {
		return ultimacarga;
	}

	public void setUltimacarga(LocalDateTime ultimacarga) {
		this.ultimacarga = ultimacarga;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getIpservidor() {
		return ipservidor;
	}

	public void setIpservidor(String ipservidor) {
		this.ipservidor = ipservidor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusPdv other = (StatusPdv) obj;
		return Objects.equals(id, other.id);
	}

	public BigDecimal getCurrenttimemillis() {
		return currenttimemillis;
	}

	public void setCurrenttimemillis(BigDecimal currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}

	


	

}
