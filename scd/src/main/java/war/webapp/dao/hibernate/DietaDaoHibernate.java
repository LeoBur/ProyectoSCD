package war.webapp.dao.hibernate;

import org.springframework.stereotype.Repository;

import war.webapp.dao.DietaDao;
import war.webapp.model.Dieta;

@Repository("dietaDao")
public class DietaDaoHibernate extends GenericDaoHibernate<Dieta, Long> implements DietaDao{
	
	public DietaDaoHibernate(){
		super(Dieta.class);
	}

}
