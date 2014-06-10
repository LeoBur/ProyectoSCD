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
	public String medicamento;
	public String observacionesMedicamento;
	public String sintoma;
	public String observacionesSintoma;
	
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

	public MomentosDia getMomento() {
		return momento;
	}

	public void setMomento(MomentosDia momento) {
		this.momento = momento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getObservacionesMedicamento() {
		return observacionesMedicamento;
	}

	public void setObservacionesMedicamento(String observacionesMedicamento) {
		this.observacionesMedicamento = observacionesMedicamento;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public String getObservacionesSintoma() {
		return observacionesSintoma;
	}

	public void setObservacionesSintoma(String observacionesSintoma) {
		this.observacionesSintoma = observacionesSintoma;
	}
}
