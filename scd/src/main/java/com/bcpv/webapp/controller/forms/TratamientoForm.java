package com.bcpv.webapp.controller.forms;

import java.util.Date;

/**
 * Created by Leo on 22/12/2014.
 */
public class TratamientoForm {

    private String medicamento1;
    private String obsPrescripcion1;
    private String medicamento2;
    private String obsPrescripcion2;
    private String paciente;
    private Date fecha;

    public String getMedicamento1() {
        return medicamento1;
    }

    public void setMedicamento1(String medicamento1) {
        this.medicamento1 = medicamento1;
    }

    public String getObsPrescripcion1() {
        return obsPrescripcion1;
    }

    public void setObsPrescripcion1(String obsPrescripcion1) {
        this.obsPrescripcion1 = obsPrescripcion1;
    }

    public String getMedicamento2() {
        return medicamento2;
    }

    public void setMedicamento2(String medicamento2) {
        this.medicamento2 = medicamento2;
    }

    public String getObsPrescripcion2() {
        return obsPrescripcion2;
    }

    public void setObsPrescripcion2(String obsPrescripcion2) {
        this.obsPrescripcion2 = obsPrescripcion2;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
