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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;

@Entity
@Table(name ="especialista")
@Indexed
public class Especialista implements Serializable{
	
	private static final long serialVersionUID = 3657563589343488236L;
	
	private Long id;
	private String dni;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private String telefono;
	private String email;
	
	private String tipo_esp;
	private Date fch_nac;
	
	public Especialista(){
	}
	
	public Especialista(String dni, String nombre, String apellido, String telefono, String email, String tipo,Domicilio domicilio,
								Date fch_nac){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.domicilio=domicilio;
		this.tipo_esp = tipo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDomicilio", nullable = false)
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	@Column(name = "tipo_esp", nullable=false)
	@Field
	public String getTipo_esp() {
		return tipo_esp;
	}

	public void setTipo_esp(String tipo_esp) {
		this.tipo_esp = tipo_esp;
	}
	
	@Column(name = "fch_nac", nullable=false)
	public Date getFch_nac() {
		return fch_nac;
	}

	public void setFch_nac(Date fch_nac) {
		this.fch_nac = fch_nac;
	}
	

}
