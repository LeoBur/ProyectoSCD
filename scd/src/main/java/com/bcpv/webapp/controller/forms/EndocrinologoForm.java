package com.bcpv.webapp.controller.forms;

import java.util.Date;

import com.bcpv.model.Domicilio;

public class EndocrinologoForm {

	private Long id;
    private String username;                    
    private String password;                    
    private String confirmPassword;
    private String firstName;                   
    private String lastName;                    
    private String email;                       
    private String phoneNumber;
    private String dni;
    private Date fch_nac;
    private com.bcpv.model.Persona.Sexo sexo;
    private Domicilio domicilio = new Domicilio();
	private Long matricula;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFch_nac() {
		return fch_nac;
	}
	public void setFch_nac(Date fch_nac) {
		this.fch_nac = fch_nac;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public com.bcpv.model.Persona.Sexo getSexo() {
		return sexo;
	}
	public void setSexo(com.bcpv.model.Persona.Sexo sexo) {
		this.sexo = sexo;
	}
}
