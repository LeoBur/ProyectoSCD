package com.bcpv.webapp.controller.forms;

import java.util.Date;
import java.util.Set;

import com.bcpv.model.Comida;
import com.bcpv.model.MomentoDia.MomentosDia;

public class PacienteForm {
	
	public Date fechaHora;
	public String medicion;
	public String peso;
	public MomentosDia momento;
	public Set<Comida> comidas;
	public String username; //pageContext.request.remoteUser
	
	public Date getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getMedicion() {
		return medicion;
	}
	
	public void setMedicion(String medicion) {
		this.medicion = medicion;
	}
	
	public String getPeso() {
		return peso;
	}
	
	public void setPeso(String peso) {
		this.peso = peso;
	}

	public Set<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(Set<Comida> comidas) {
		this.comidas = comidas;
	}

	
}
