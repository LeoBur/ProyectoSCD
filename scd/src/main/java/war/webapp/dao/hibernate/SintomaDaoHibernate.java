package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.SintomaDao;
import war.webapp.model.Sintoma;

@Repository("sintomaDao")
public class SintomaDaoHibernate extends GenericDaoHibernate<Sintoma, Long> implements SintomaDao{

	public SintomaDaoHibernate(){
		super(Sintoma.class);
	}
	
	@Override
	public Sintoma save(Sintoma sintoma) {
		return this.saveSintoma(sintoma);
	}

	
	@Override
	public Sintoma loadSintomaById(Long idSintoma)
			throws EntityNotFoundException {
		Sintoma sintoma = (Sintoma) getSession().createCriteria(Sintoma.class).add(Restrictions.eq("idSintoma",idSintoma));
		if (sintoma == null) throw new EntityNotFoundException("Sintoma id: "+idSintoma+" not found");
		else return sintoma;
	}

	@SuppressWarnings("unchecked")
	public List<Sintoma> getSintomas() {
		Query qry = getSession().createQuery("from Sintoma s order by upper(s.nombre)");
		return qry.list();
	}

	@Override
	public Sintoma saveSintoma(Sintoma sintoma) {
		if (log.isDebugEnabled()){
			log.debug("sintoma id "+ sintoma.getIdSintoma());
		}
		getSession().saveOrUpdate(sintoma);
		getSession().flush();
		return sintoma;
	}

}
