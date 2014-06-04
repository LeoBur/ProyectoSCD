package com.bcpv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="momento_dia")
public class MomentoDia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002754156113557807L;
	
	public enum MomentosDia {DESAYUNO,MEDIA_MANIANA,ALMUERZO,MEDIA_TARDE,CENA,ANTES_DE_ACOSTARSE } 
	
	private Long idMomentoD;
	private MomentosDia nombre;
	private Set<Comida> comidas = new HashSet<Comida>();
	private Set<DiaDieta> diasDieta = new HashSet<DiaDieta>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idMomentoD")
	public Long getIdMomentoD() {
		return idMomentoD;
	}
	public void setIdMomentoD(Long idMomentoD) {
		this.idMomentoD = idMomentoD;
	}
	
	@Column(name="nombre_momento")
	public MomentosDia getNombre() {
		return nombre;
	}
	public void setNombre(MomentosDia nombre) {
		this.nombre = nombre;
	}
	
	@ManyToMany(cascade={})
	@JoinTable(name="momento_dia_comidas", joinColumns={@JoinColumn(name="idMomentoD")},inverseJoinColumns={@JoinColumn(name="idComida")})
	public Set<Comida> getComidas() {
		return comidas;
	}
	public void setComidas(Set<Comida> comidas) {
		this.comidas = comidas;
	}
	
	@ManyToMany(cascade={},mappedBy="momentosDia")
	public Set<DiaDieta> getDiasDieta() {
		return diasDieta;
	}
	public void setDiasDieta(Set<DiaDieta> diasDieta) {
		this.diasDieta = diasDieta;
	}

}
