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
@Table(name="registro_comidas")
@Indexed
public class RegistroComidas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082921203015331328L;

	private Long idRegistroComida;
	private Paciente paciente;
	private Date fecha_registro_comida;
	private MomentoDia momentoDia= new MomentoDia();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_reg_comida")
	public Long getIdRegistroComida() {
		return idRegistroComida;
	}
	public void setIdRegistroComida(Long idRegistroComida) {
		this.idRegistroComida = idRegistroComida;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Column(name="fecha_reg_comida")
	public Date getFecha_registro_comida() {
		return fecha_registro_comida;
	}
	public void setFecha_registro_comida(Date fecha_registro_comida) {
		this.fecha_registro_comida = fecha_registro_comida;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="momentoDia", unique = true)
	public MomentoDia getMomentoDia() {
		return momentoDia;
	}
	public void setMomentoDia(MomentoDia momentoDia) {
		this.momentoDia = momentoDia;
	}
}
