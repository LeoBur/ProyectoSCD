package war.webapp.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.ComidaDao;
import war.webapp.model.Comida;

@Repository("comidaDao")
public class ComidaDaoHibernate extends GenericDaoHibernate<Comida, Long> implements ComidaDao{
	
	public ComidaDaoHibernate(){
		super(Comida.class);
	}

	@Override
	public Comida loadComidaById(Long idComida) throws EntityNotFoundException {
		Comida comida = (Comida) getSession().createCriteria(Comida.class).add(Restrictions.eq("idComida", idComida));
		if (comida ==null){
			throw new EntityNotFoundException("Comida id: "+idComida+" not found...");
		} else{
			return comida;
		}
	}

	@Override
	public Comida saveComida(Comida comida) {
		if (log.isDebugEnabled()){
			log.debug("Comida id: "+ comida.getIdComida());
		}
		getSession().saveOrUpdate(comida);
		getSession().flush();
		return comida;
	}

}
