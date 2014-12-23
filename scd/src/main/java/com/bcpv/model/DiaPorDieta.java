package com.bcpv.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Usuario on 08/12/2014.
 */
public class DiaPorDieta {
    public int id;
    public String dia;
    public ArrayList<String> momento;

    public ArrayList<String> getComida() {
        return comida;
    }

    public void setComida(ArrayList<String> comida) {
        this.comida = comida;
    }

    public ArrayList<String> comida;

    public int getId() {
        return id;
    }

    public String getDia() {
        return dia;
    }

    public ArrayList<String> getMomento() {
        return momento;
    }


    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMomento(ArrayList<String> momento) {
        this.momento = momento;
    }
}
