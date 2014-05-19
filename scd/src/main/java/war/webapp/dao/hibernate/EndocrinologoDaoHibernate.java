package war.webapp.dao.hibernate;

import javax.persistence.EntityNotFoundException;

import org.hibernate.criterion.Restrictions;

import war.webapp.dao.EndocrinologoDao;
import war.webapp.model.Endocrinologo;

public class EndocrinologoDaoHibernate extends PersonaDaoHibernate implements EndocrinologoDao{

	@Override
	public Endocrinologo getEndocrinologoByMatricula(Long matricula) {
		Endocrinologo endocrinologo = (Endocrinologo) getSession().createCriteria(Endocrinologo.class).add(Restrictions.eq("matricula_endo", matricula));
		if (endocrinologo == null){
			throw new EntityNotFoundException("No existe Endocrinologo con matricula :" + matricula);
		} else {
			return endocrinologo;
		}
	}
	
	

}
