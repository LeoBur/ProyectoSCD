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
@Table(name="prescripcion")
@Indexed
public class Prescripcion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2394781862567675959L;

	private Long idPrescripcion;
	private Medicamento medicamento;
	private String descripcion;
	private Tratamiento tratamiento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdPrescripcion() {
		return idPrescripcion;
	}
	public void setIdPrescripcion(Long idPrescripcion) {
		this.idPrescripcion = idPrescripcion;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicamento", nullable = false)
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tratamiento",nullable=false)
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
	
}
