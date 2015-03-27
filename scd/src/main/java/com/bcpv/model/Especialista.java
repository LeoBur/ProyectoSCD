package com.bcpv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name ="especialista")
@Indexed
public class Especialista implements Serializable{
	
	private static final long serialVersionUID = 3657563589343488236L;
	
	public enum TipoEspecialista {NUTRICIONISTA,ENTRENADOR_PERSONAL}
	
	private Long id;
	private Long matricula;
	private TipoEspecialista tipo_esp;
	private Set<PacienteEnTratamiento> pacientesEnTratamiento = new HashSet<PacienteEnTratamiento>();
	private Persona persona;

	
	public Especialista(){
	}

    public Especialista(Long matricula, TipoEspecialista tipoEspecialista, Persona persona){
        this.setMatricula(matricula);
        this.setTipo_esp(tipoEspecialista);
        this.setPersona(persona);
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_especialista")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tipo_esp", nullable=false)
	@Enumerated(EnumType.STRING)
	public TipoEspecialista getTipo_esp() {
		return tipo_esp;
	}

	public void setTipo_esp(TipoEspecialista tipo_esp) {
		this.tipo_esp = tipo_esp;
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
	public Set<PacienteEnTratamiento> getPacientes() {
		return pacientesEnTratamiento;
	}

	public void setPacientes(Set<PacienteEnTratamiento> pacientesEnTratamiento) {
		this.pacientesEnTratamiento = pacientesEnTratamiento;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", nullable = false,unique=true)
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void addPacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento){
		this.pacientesEnTratamiento.add(pacienteEnTratamiento);
	}

	public void removePacienteEnTratamiento(Long idPacienteEnTratamiento){
		PacienteEnTratamiento pacienteEnTratamiento = getPacienteEnTratamiento(idPacienteEnTratamiento);
		this.pacientesEnTratamiento.remove(pacienteEnTratamiento);
	}

	private PacienteEnTratamiento getPacienteEnTratamiento (Long idPacienteEnTratamiento){
		for(PacienteEnTratamiento pacienteEnTratamiento:this.getPacientes()){
			if(pacienteEnTratamiento.getIdPacienteEnTratamiento().longValue() == idPacienteEnTratamiento.longValue()){
				return pacienteEnTratamiento;
			}
		}
		return null;
	}
}
