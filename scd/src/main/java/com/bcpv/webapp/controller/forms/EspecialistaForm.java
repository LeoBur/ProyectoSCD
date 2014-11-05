package com.bcpv.webapp.controller.forms;

import com.bcpv.model.Especialista;
import com.bcpv.model.Especialista.TipoEspecialista;

/**
 * Created by marcos on 03/11/2014.
 */
public class EspecialistaForm extends EndocrinologoForm {

    private TipoEspecialista tipoEspecialista;

    public TipoEspecialista getTipoEspecialista() {
        return tipoEspecialista;
    }

    public void setTipoEspecialista(TipoEspecialista tipoEspecialista) {
        this.tipoEspecialista = tipoEspecialista;
    }
}
