package com.bcpv.service;

import com.bcpv.dao.GrupoMedicamentoDao;
import com.bcpv.model.GrupoMedicamento;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * Created by Leo on 23/03/2015.
 */
public interface GrupoMedicamentoManager extends GenericManager<GrupoMedicamento, Long> {
    
    void setGrupoMedicamentoDao(GrupoMedicamentoDao grupoDao);


    GrupoMedicamento getGrupoMedicamento(Long idGrupoMedicamento);

    List<GrupoMedicamento> getGrupoMedicamentos();

    GrupoMedicamento saveGrupoMedicamento(GrupoMedicamento grupo) throws EntityExistsException;

    void removeGrupoMedicamento(GrupoMedicamento grupo);

    void removeGrupoMedicamento(Long idGrupoMedicamento);

    List<GrupoMedicamento> search(String searchTerm);

    GrupoMedicamento getByNombre(String nombre);
}
