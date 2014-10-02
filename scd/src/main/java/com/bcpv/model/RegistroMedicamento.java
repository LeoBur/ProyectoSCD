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
@Table(name="registro_medicamento")
@Indexed
public class RegistroMedicamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3163189736199269231L;

	private Long idRegMedicamento;
	private Paciente paciente;
	private Date fch_reg_medicamento;
	private Medicamento medicamento;
	private String observaciones;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_reg_medicamento")
	public Long getIdRegMedicamento() {
		return idRegMedicamento;
	}
	public void setIdRegMedicamento(Long idRegMedicamento) {
		this.idRegMedicamento = idRegMedicamento;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Column(name="fecha_reg_medicamento")
	public Date getFch_reg_medicamento() {
		return fch_reg_medicamento;
	}
	public void setFch_reg_medicamento(Date fch_reg_medicamento) {
		this.fch_reg_medicamento = fch_reg_medicamento;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicamento", nullable = false)
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
	@Column(name="observaciones")
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones.toUpperCase();
	}
}
