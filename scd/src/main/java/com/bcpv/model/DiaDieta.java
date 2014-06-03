package com.bcpv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="dia_Dieta")
@Indexed
public class DiaDieta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328510555606183210L;
	
	public enum Dias{LUNES_1,MARTES_1,MIERCOLES_1,JUEVES_1,VIERNES_1,SABADO_1,DOMINGO_1,
					LUNES_2,MARTES_2,MIERCOLES_2,JUEVES_2,VIERNES_2,SABADO_2,DOMINGO_2,
					LUNES_3,MARTES_3,MIERCOLES_3,JUEVES_3,VIERNES_3,SABADO_3,DOMINGO_3,
					LUNES_4,MARTES_4,MIERCOLES_4,JUEVES_4,VIERNES_4,SABADO_4,DOMINGO_4}
	
	private Long idDiaDieta;
	private Dias nombreDiaDieta;
	private Set<MomentoDia> momentosDia = new HashSet<MomentoDia>();
	private Dieta dieta;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idDiaDieta")
	public Long getIdDiaDieta() {
		return idDiaDieta;
	}
	public void setIdDiaDieta(Long idDiaDieta) {
		this.idDiaDieta = idDiaDieta;
	}
	
	@Column(name="nombreDiaDieta")
	@Enumerated(EnumType.STRING)
	public Dias getNombreDiaDieta() {
		return nombreDiaDieta;
	}
	public void setNombreDiaDieta(Dias nombreDiaDieta) {
		this.nombreDiaDieta = nombreDiaDieta;
	}
	
	@ManyToMany(cascade={})
	@JoinTable(name="dia_Dieta_Momentos", joinColumns={@JoinColumn(name="idDiaDieta")}, inverseJoinColumns={@JoinColumn(name="idMomentoD")})
	@Column(name="momentosDia")
	public Set<MomentoDia> getMomentosDia() {
		return momentosDia;
	}
	public void setMomentosDia(Set<MomentoDia> momentosDia) {
		this.momentosDia = momentosDia;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dieta",nullable=false)
	public Dieta getDieta() {
		return dieta;
	}
	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

}
