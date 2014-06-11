package com.bcpv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="comida")
@Indexed
public class Comida implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7379603373775505732L;
	
	private Long idComida;
	private String cantidad;
	private Alimento alimento;
	private String observaciones;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idComida")
	public Long getIdComida() {
		return idComida;
	}
	public void setIdComida(Long idComida) {
		this.idComida = idComida;
	}
	
	@Column(name="cantidad",nullable=false)
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idAlimento",nullable=false)
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	@Column(name="observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
