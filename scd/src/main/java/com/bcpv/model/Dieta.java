package com.bcpv.model;

import java.io.Serializable;
import java.util.Date;
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

@Entity
@Table(name="dieta")
@Indexed
public class Dieta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809121412435221580L;
	
	private Long idDieta;
	private Paciente Paciente;
	private Date fechaAlta;
	private Set<DiaDieta> diasDieta = new HashSet<DiaDieta>();
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_dieta")
	public Long getIdDieta() {
		return idDieta;
	}
	public void setIdDieta(Long idDieta) {
		this.idDieta = idDieta;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paciente",nullable=true)
	public Paciente getPaciente() {
		return Paciente;
	}
	public void setPaciente(Paciente paciente) {
		Paciente = paciente;
	}
	
	@Column(name="fecha_alta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<DiaDieta> getDiasDieta() {
		return diasDieta;
	}
	public void setDiasDieta(Set<DiaDieta> diasDieta) {
		this.diasDieta = diasDieta;
	}
	
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
