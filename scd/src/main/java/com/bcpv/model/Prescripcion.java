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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Indexed;

import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
	public Long getIdPrescripcion() {
		return idPrescripcion;
	}
	public void setIdPrescripcion(Long idPrescripcion) {
		this.idPrescripcion = idPrescripcion;
	}

    @ManyToOne(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name="idMedicamento")
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

	@ManyToOne
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
	
}
