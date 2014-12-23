package com.bcpv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="tratamiento")
@Indexed

public class Tratamiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3047536263144125853L;
	
	private Long idTratamiento;
	private Date fechaTratamiento;
	private Paciente paciente;
	private Endocrinologo endocrinologo;
	private Set<Prescripcion> prescripciones = new HashSet<Prescripcion>();
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getIdTratamiento() {
		return idTratamiento;
	}
	public void setIdTratamiento(Long idTratamiento) {
		this.idTratamiento = idTratamiento;
	}
	
	@Column(name="fecha_tratamiento", nullable= false)
	public Date getFechaTratamiento() {
		return fechaTratamiento;
	}
	public void setFechaTratamiento(Date fechaTratamiento) {
		this.fechaTratamiento = fechaTratamiento;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endo", nullable = false)
	public Endocrinologo getEndocrinologo() {
		return endocrinologo;
	}
	public void setEndocrinologo(Endocrinologo endocrinologo) {
		this.endocrinologo = endocrinologo;
	}
	
	@OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "idTratamiento")
	public Set<Prescripcion> getPrescripciones() {
		return prescripciones;
	}
	public void setPrescripciones(Set<Prescripcion> prescripciones) {
		this.prescripciones = prescripciones;
	}

}
