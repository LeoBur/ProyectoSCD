package com.bcpv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name ="endocrinologo")
@Indexed
public class Endocrinologo implements Serializable{
	
	private static final long serialVersionUID = -1274195813657855646L;
	
	private Long id;
	private Long matricula;
	private List<PacienteEnTratamiento> pacientesEnTratamiento = new ArrayList<PacienteEnTratamiento>();
	private Persona persona;
    
	public Endocrinologo() {
	}
	
	public Endocrinologo(Long matricula, Persona persona){
		this.matricula = matricula;
		this.persona = persona;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endocrinologo")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name ="matricula_endo", nullable=false)
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public List<PacienteEnTratamiento> getPacientes() {
		return pacientesEnTratamiento;
	}

	public void setPacientes(List<PacienteEnTratamiento> pacientesEnTratamiento) {
		this.pacientesEnTratamiento = pacientesEnTratamiento;
	}

	public void addPacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento){
		this.pacientesEnTratamiento.add(pacienteEnTratamiento);
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", nullable = false,unique=true)
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
