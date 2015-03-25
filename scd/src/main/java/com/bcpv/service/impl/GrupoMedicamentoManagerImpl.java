package com.bcpv.service.impl;

import com.bcpv.dao.GrupoMedicamentoDao;
import com.bcpv.model.GrupoMedicamento;
import com.bcpv.service.GrupoMedicamentoManager;
import com.bcpv.service.GrupoMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 23/03/2015.
 */
@Service("grupoMedicamentoManager")
@WebService(serviceName = "GrupoMedicamentoService", endpointInterface = "com.bcpv.service.GrupoMedicamentoService")
@Transactional
public class GrupoMedicamentoManagerImpl extends GenericManagerImpl<GrupoMedicamento, Long> implements GrupoMedicamentoManager, GrupoMedicamentoService{
    
    private GrupoMedicamentoDao grupoMedicamentoDao;

    @Override
    @Autowired
    public void setGrupoMedicamentoDao(GrupoMedicamentoDao alimentoDao) {
        this.dao = alimentoDao;
        this.grupoMedicamentoDao = alimentoDao;

    }

    public GrupoMedicamento getGrupoMedicamento(String idGrupoMedicamento) {
        return grupoMedicamentoDao.get(new Long(idGrupoMedicamento));
    }

    @Override
    public GrupoMedicamento getGrupoMedicamento(Long idGrupoMedicamento) {
        return grupoMedicamentoDao.get(idGrupoMedicamento);
    }

    @Override
    public List<GrupoMedicamento> getGrupoMedicamentos() {
        if (grupoMedicamentoDao != null){
            return grupoMedicamentoDao.getGrupos();
        }
        else
            return new ArrayList<GrupoMedicamento>();
    }

    @Override
    public GrupoMedicamento saveGrupoMedicamento(final GrupoMedicamento grupo)
            throws EntityExistsException {
        try {
            return grupoMedicamentoDao.saveGrupo(grupo);
        } catch (final Exception e){
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new EntityExistsException("GrupoMedicamento ya existe");
        }
    }

    @Override
    public List<GrupoMedicamento> search(String searchTerm) {
        return super.search(searchTerm, GrupoMedicamento.class);
    }

    @Override
    public void removeGrupoMedicamento(GrupoMedicamento grupo) {
        log.debug("removing alimento con id: " + grupo.getIdGrupo());
        grupoMedicamentoDao.remove(grupo);
    }

    @Override
    public void removeGrupoMedicamento(Long idGrupoMedicamento) {
        log.debug("removing alimento con id: " + idGrupoMedicamento);
        grupoMedicamentoDao.remove(idGrupoMedicamento);

    }

    @Override
    public GrupoMedicamento getByNombre(String nombre) {
        log.debug("Searching GrupoMedicamento by nombre");
        return grupoMedicamentoDao.getByNombre(nombre);
    }
}
