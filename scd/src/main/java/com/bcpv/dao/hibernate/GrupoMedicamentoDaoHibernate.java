package com.bcpv.dao.hibernate;

import com.bcpv.dao.GrupoMedicamentoDao;
import com.bcpv.model.GrupoMedicamento;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Leo on 23/03/2015.
 */
@Repository("grupoMedicamentoDao")
public class GrupoMedicamentoDaoHibernate extends GenericDaoHibernate<GrupoMedicamento, Long> implements GrupoMedicamentoDao{

    public GrupoMedicamentoDaoHibernate(){
        super (GrupoMedicamento.class);
    }

    @Override
    public GrupoMedicamento getByNombre(String nombre) {
        Query qry = getSession().createQuery("from GrupoMedicamento a where a.nombre = :grupo");
        qry.setParameter("grupo", nombre);
        if (qry.list() == null || qry.list().isEmpty()) {
            throw new EntityNotFoundException("Grupo de Medicamento '" + nombre + "' not found...");
        } else {
            return (GrupoMedicamento) qry.uniqueResult();
        }
    }

    @Override
    public GrupoMedicamento saveGrupo(GrupoMedicamento grupo) {
        if (log.isDebugEnabled()) {
            log.debug("Grupo medicamento id: " + grupo.getIdGrupo());
        }
        getSession().saveOrUpdate(grupo);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return grupo;
    }

    @Override
    public List<GrupoMedicamento> getGrupos() {
        Query qry = getSession().createQuery("from GrupoMedicamento g order by upper(g.nombre)");
        return qry.list();
    }
}
