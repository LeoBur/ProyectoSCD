package com.bcpv.dao;

import com.bcpv.model.GrupoMedicamento;
import ro.isdc.wro.manager.callback.LifecycleCallbackSupport;

import java.util.List;

/**
 * Created by Leo on 23/03/2015.
 */
public interface GrupoMedicamentoDao extends GenericDao<GrupoMedicamento, Long>{

    GrupoMedicamento getByNombre(String nombre);

    GrupoMedicamento saveGrupo(GrupoMedicamento grupo);

    List<GrupoMedicamento> getGrupos();
}
