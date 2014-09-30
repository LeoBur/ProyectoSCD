package com.bcpv.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="registro_sintoma")
@Indexed
public class RegistroSintoma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6322408874536174349L;

	private Long idRegSintoma;
	private Paciente paciente;
	private Date fch_reg_sintoma;
	private Sintoma sintoma;
	private String observaciones;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_reg_sintoma")
	public Long getIdRegSintoma() {
		return idRegSintoma;
	}
	public void setIdRegSintoma(Long idRegSintoma) {
		this.idRegSintoma = idRegSintoma;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Column(name="fecha_reg_sintoma")
	public Date getFch_reg_sintoma() {
		return fch_reg_sintoma;
	}
	public void setFch_reg_sintoma(Date fch_reg_sintoma) {
		this.fch_reg_sintoma = fch_reg_sintoma;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sintoma", nullable = false)
	public Sintoma getSintoma() {
		return sintoma;
	}
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}
	
	@Column(name="observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones.toUpperCase();
	}
}
