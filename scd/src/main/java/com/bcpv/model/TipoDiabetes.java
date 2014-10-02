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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "tiposDiabetes")
@Indexed
public class TipoDiabetes implements Serializable{

	private static final long serialVersionUID = 2692401869897053144L;
	
	private int id_tipo;
	private String tipoDiab;
	private String Caract;
	private Set<Paciente> pacientes = new HashSet<Paciente>();
	
	@Id
	@Column(name = "id_tipo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_tipo() {
		return id_tipo;
	}
	
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	
	@Column(name = "tipo_dibetes")
	public String getTipoDiab() {
		return tipoDiab;
	}
	
	public void setTipoDiab(String tipoDiab) {
		this.tipoDiab = tipoDiab.toUpperCase();
	}
	
	@Column(name = "caracteristica")
	public String getCaract() {
		return Caract;
	}
	public void setCaract(String caract) {
		Caract = caract.toUpperCase();
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	

}
