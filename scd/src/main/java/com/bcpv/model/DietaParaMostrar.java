package com.bcpv.model;

/**
 * Created by Usuario on 03/03/2015.
 */
public class DietaParaMostrar {
    public String momento;
    public String comidaDia;

    public DietaParaMostrar(String unMomento, String unaComida) {
        this.momento = unMomento;
        this.comidaDia = unaComida;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getComidaDia() {
        return comidaDia;
    }

    public void setComidaDia(String comidaDia) {
        this.comidaDia = comidaDia;
    }
}
