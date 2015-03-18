package com.bcpv.model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "paciente_en_tratamiento")
@Indexed
@XmlRootElement
public class PacienteEnTratamiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2390575646187941257L;
	
	private Long idPacienteEnTratamiento;
	private Paciente paciente;
	private Calendar fch_alta;
	private boolean estado; //"activo"/"baja lógica"
	private Endocrinologo endocrinologo;
	private Especialista especialista;

	public PacienteEnTratamiento(){}

	public PacienteEnTratamiento(Paciente paciente, Endocrinologo endocrinologo){
		this.paciente = paciente;
		this.endocrinologo = endocrinologo;
		this.fch_alta = Calendar.getInstance();
		this.estado = true;
	}

	public PacienteEnTratamiento(Paciente paciente, Especialista especialista){
		this.paciente = paciente;
		this.especialista = especialista;
		this.fch_alta = Calendar.getInstance();
		this.estado = true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_paciente_en_tratamiento")
	public Long getIdPacienteEnTratamiento() {
		return idPacienteEnTratamiento;
	}
	public void setIdPacienteEnTratamiento(Long idPacienteEnTratamiento) {
		this.idPacienteEnTratamiento = idPacienteEnTratamiento;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_paciente", nullable = false)
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@Column(name="fecha_alta")
	public Calendar getFch_alta() {
		return fch_alta;
	}
	public void setFch_alta(Calendar fch_alta) {
		this.fch_alta = fch_alta;
	}
	
	@Column(name="estado")
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_endocrinologo",nullable=true)
	public Endocrinologo getEndocrinologo() {
		return endocrinologo;
	}
	public void setEndocrinologo(Endocrinologo endocrinologo) {
		this.endocrinologo = endocrinologo;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_especialista",nullable=true)
	public Especialista getEspecialista() {
		return especialista;
	}
	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

}
