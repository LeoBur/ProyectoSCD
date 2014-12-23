package com.bcpv.webapp.controller.forms;

public class EndocrinologoForm extends PersonaForm {

	private Long matricula;
	private Long idEndo;

	public Long getIdEndo() {
		return idEndo;
	}
	public void setIdEndo(Long idEndo) {
		this.idEndo = idEndo;
	}
    public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
    public void setMatriculaByPersona() {

    }
}
