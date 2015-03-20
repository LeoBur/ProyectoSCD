package com.bcpv.webapp.controller.forms;

public class PacienteForm extends PersonaForm {

    public PacienteForm(){
        enabled = true;
    }

    private int limiteInferior;
    private int limiteSuperior;
    private String tipoDiabetes;

    public int getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public String getTipoDiabetes() {
        return tipoDiabetes;
    }

    public void setTipoDiabetes(String tipoDiabetes) {
        this.tipoDiabetes = tipoDiabetes;
    }
}
