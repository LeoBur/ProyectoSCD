package com.bcpv.model;

import java.io.Serializable;
import java.util.Date;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;

@Entity
@Table(name ="endocrinologo")
@Indexed
public class Endocrinologo implements Serializable{
	
	private static final long serialVersionUID = -1274195813657855646L;
	
	private Long id;
	private String dni;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private String telefono;
	private String email;
	
	private Long matricula;
	private Set<Paciente> pacientes = new HashSet<Paciente>();
	private Date fch_nac;
    
	public Endocrinologo() {
	}
	
	public Endocrinologo(String dni, String nombre, String apellido, String telefono, String email, Long matricula,Domicilio domicilio,
							Date fch_nac){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.domicilio=domicilio;
		this.matricula = matricula;
		this.fch_nac=fch_nac;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "dni", nullable = false, unique = true)
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Column(name = "nombre", nullable=false)
	@Field
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido", nullable=false)
	@Field
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Column(name = "email")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDomicilio", nullable = false)
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	@Column(name ="matricula_endo", nullable=false)
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	@Column(name = "fch_nac", nullable=false)
	public Date getFch_nac() {
		return fch_nac;
	}

	public void setFch_nac(Date fch_nac) {
		this.fch_nac = fch_nac;
	}
	
	

}
