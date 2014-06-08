package com.bcpv.webapp.controller.forms;

import java.util.Date;

import com.bcpv.model.Comida;

public class PacienteForm {
	
	public Date fechaHora;
	public String medicion;
	public String peso;
	public Comida comida;
	
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

	public Comida getComida() {
		return comida;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}
}
