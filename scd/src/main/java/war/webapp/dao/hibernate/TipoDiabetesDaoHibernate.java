package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import war.webapp.dao.TipoDiabetesDao;
import war.webapp.model.TipoDiabetes;

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
