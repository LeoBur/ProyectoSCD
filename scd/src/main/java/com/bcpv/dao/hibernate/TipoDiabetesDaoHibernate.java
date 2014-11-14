package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.TipoDiabetesDao;
import com.bcpv.model.TipoDiabetes;

@Repository("tipoDiabetesDao")
public class TipoDiabetesDaoHibernate extends GenericDaoHibernate<TipoDiabetes, Long> implements TipoDiabetesDao {
	
	public TipoDiabetesDaoHibernate() {
        super(TipoDiabetes.class);
    }

	@Override
	public TipoDiabetes loadTipoDiabetesById(Long id) throws EntityNotFoundException {
		TipoDiabetes tipo = (TipoDiabetes) getSession().createCriteria(TipoDiabetes.class).add(Restrictions.eq("id_tipo", id));
		if (tipo == null){
			throw new EntityNotFoundException("Tipo de diabetes: " + id + "not found");
		} else {
			return tipo;
		}
	}

    @Override
    public TipoDiabetes loadTipoDiabetesByTipo(String name) throws EntityNotFoundException {
        TipoDiabetes tipo = (TipoDiabetes) getSession().createCriteria(TipoDiabetes.class).add(Restrictions.eq("tipo_diabetes", name));
        if (tipo == null){
            throw new EntityNotFoundException("Tipo de diabetes: " + name + "not found");
        } else {
            return tipo;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoDiabetes> getTipoDiabeteses() {
		Query qry = getSession().createQuery("from TipoDiabetes t order by upper (t.id_tipo)");
		return qry.list();
	}

	@Override
	public TipoDiabetes saveTipoDiabetes(TipoDiabetes tipo) {
		
		if (log.isDebugEnabled()) {
            log.debug("tipo de diabetes id: " + tipo.getId_tipo());
        }
		getSession().saveOrUpdate(tipo);
		getSession().flush();
		return tipo;
	}

}
