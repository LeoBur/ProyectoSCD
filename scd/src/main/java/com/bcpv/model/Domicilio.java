package com.bcpv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="Domicilio")
@Indexed
public class Domicilio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9013815718475143878L;
	
	private Long idDomicilio;
	private Localidad localidad;
	private String calle;
	private Long numero;
	private String piso;
	private String dpto;
	
	@Id
	@Column(name="id", unique = true, nullable= false)
	public Long getId() {
		return idDomicilio;
	}
	public void setId(Long id) {
		this.idDomicilio = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_localidad", nullable = false)
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	@Column(name="calle", unique = false, nullable = false)
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@Column(name="nro")
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	@Column(name="piso")
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	@Column(name="dpto")
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

}
