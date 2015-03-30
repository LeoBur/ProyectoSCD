package com.bcpv.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Cecilia on 26/03/2015.
 */
public class DietaParaEditar {
    public String alimento;
    public String momento;
    public String cantidad;
    public String observacion;
    public Set<DietaParaEditar> list;

    public Set<DietaParaEditar> getList() {
        return list;
    }

    public void setList(Set<DietaParaEditar> list) {
        this.list = list;
    }


    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public DietaParaEditar (String unAlimento, String unMomento, String unaCantidad, String unaObservacion){
        this.alimento = unAlimento;
        this.momento = unMomento;
        this.cantidad = unaCantidad;
        this.observacion = unaObservacion;
    }
}
