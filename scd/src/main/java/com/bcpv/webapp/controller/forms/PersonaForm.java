package com.bcpv.webapp.controller.forms;

import java.util.Date;

import com.bcpv.model.Domicilio;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Especialista;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.Role;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.model.Especialista.TipoEspecialista;

public class PersonaForm {
	
	//Atributos de Persona
	private Long idPersona;
    private String username;                    // required
    private String password;                    // required
    private String confirmPassword;
    private String passwordHint;
    private String firstName;                   // required
    private String lastName;                    // required
    private String email;                       // required; unique
    private String phoneNumber;
    private String dni;
    private Date fch_nac;
    private boolean sexo;
    private Domicilio domicilio;
    private Role rol;
    //Atributos de Paciente
    private Long idPaciente;
	private int limiteInf;
	private int limiteSup;
	private TipoDiabetes tipo;
	private Persona personaPaciente;
	//Atributos de Especialista
	private Long idEspecialista;
	private Long matricula;
	private TipoEspecialista tipo_esp;
	private Persona personaEspecialista;
	//Atributos de PacienteEnTratamiento
	private Long idPacienteEnTratamiento;
	private Paciente paciente;
	private Date fch_alta;
	private boolean estado; //"activo"/"baja lógica"
	private Endocrinologo endocrinologo;
	private Especialista especialista;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
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
	public boolean isSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Role getRol() {
		return rol;
	}
	public void setRol(Role rol) {
		this.rol = rol;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getLimiteInf() {
		return limiteInf;
	}
	public void setLimiteInf(int limiteInf) {
		this.limiteInf = limiteInf;
	}
	public int getLimiteSup() {
		return limiteSup;
	}
	public void setLimiteSup(int limiteSup) {
		this.limiteSup = limiteSup;
	}
	public TipoDiabetes getTipo() {
		return tipo;
	}
	public void setTipo(TipoDiabetes tipo) {
		this.tipo = tipo;
	}
	public Long getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Long idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public TipoEspecialista getTipo_esp() {
		return tipo_esp;
	}
	public void setTipo_esp(TipoEspecialista tipo_esp) {
		this.tipo_esp = tipo_esp;
	}
	public Persona getPersonaPaciente() {
		return personaPaciente;
	}
	public void setPersonaPaciente(Persona personaPaciente) {
		this.personaPaciente = personaPaciente;
	}
	public Persona getPersonaEspecialista() {
		return personaEspecialista;
	}
	public void setPersonaEspecialista(Persona personaEspecialista) {
		this.personaEspecialista = personaEspecialista;
	}
	public Long getIdPacienteEnTratamiento() {
		return idPacienteEnTratamiento;
	}
	public void setIdPacienteEnTratamiento(Long idPacienteEnTratamiento) {
		this.idPacienteEnTratamiento = idPacienteEnTratamiento;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getFch_alta() {
		return fch_alta;
	}
	public void setFch_alta(Date fch_alta) {
		this.fch_alta = fch_alta;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Endocrinologo getEndocrinologo() {
		return endocrinologo;
	}
	public void setEndocrinologo(Endocrinologo endocrinologo) {
		this.endocrinologo = endocrinologo;
	}
	public Especialista getEspecialista() {
		return especialista;
	}
	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}
}
