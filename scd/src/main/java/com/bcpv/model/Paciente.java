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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "paciente")
@Indexed
@XmlRootElement
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = -6094399498007212046L;
	
	private Long id;
	private int limiteInf;
	private int limiteSup;
	private TipoDiabetes tipo;
	private Set<Medicion> mediciones = new HashSet<Medicion>();
	private Set<Peso> pesos = new HashSet<Peso>();
	private Set<Dieta> dietas = new HashSet<Dieta>();
	private Set<RegistroComidas> registroComidas = new HashSet<RegistroComidas>();
	private Set<Tratamiento> tratamientos = new HashSet<Tratamiento>();
	private Persona persona;
	
	public Paciente(){
	}
	
	public Paciente(TipoDiabetes tipo, Endocrinologo endo,int limiteInf,int limiteSup, Persona pers){
		this.tipo = tipo;
        this.limiteInf = limiteInf;
        this.limiteSup = limiteSup;
        this.persona = pers;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_paciente")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo",nullable=false)
	public TipoDiabetes getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDiabetes tipo) {
		this.tipo = tipo;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Medicion> getMediciones() {
		return this.mediciones;
	}

	public void setMediciones(Set<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(Set<Peso> pesos) {
		this.pesos = pesos;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(Set<Dieta> dietas) {
		this.dietas = dietas;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<RegistroComidas> getRegistroComidas() {
		return registroComidas;
	}

	public void setRegistroComidas(Set<RegistroComidas> registroComidas) {
		this.registroComidas = registroComidas;
	}

    @OneToMany(mappedBy="paciente", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	public Set<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(Set<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	@Column(name = "limite_inf")
	public int getLimiteInf() {
		return limiteInf;
	}

	public void setLimiteInf(int limiteInf) {
		this.limiteInf = limiteInf;
	}

	@Column(name = "limite_sup")
	public int getLimiteSup() {
		return limiteSup;
	}

	public void setLimiteSup(int limiteSup) {
		this.limiteSup = limiteSup;
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
