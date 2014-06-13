package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.EndocrinologoDao;
import com.bcpv.model.Endocrinologo;

@Repository("endocrinologoDao")
public class EndocrinologoDaoHibernate extends GenericDaoHibernate<Endocrinologo, Long> implements EndocrinologoDao{

	public EndocrinologoDaoHibernate() {
        super(Endocrinologo.class);
    }
	
	@Override
	public Endocrinologo loadEndocrinologoByDNI(Long dni) throws EntityNotFoundException {
		Endocrinologo endocrinologo = (Endocrinologo) getSession().createCriteria(Endocrinologo.class).add(Restrictions.eq("dni", dni));
		if (endocrinologo == null){
			throw new EntityNotFoundException("Endocrinologo con DNI :" + dni + " no existe");
		} else {
			return endocrinologo;
		}
	}
	
    @SuppressWarnings("unchecked")
    public List<Endocrinologo> getEndocrinologos() {
		Query qry = getSession().createQuery("from endocrinologo e order by upper(e.apellido)");
        return qry.list();
	}

    @Override
    public Endocrinologo saveEndocrinologo(Endocrinologo endo) {
    	if (log.isDebugEnabled()) {
            log.debug("Persona id: " + endo.getId());
        }
        getSession().saveOrUpdate(endo);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return endo;
    }
    
	@Override
	public Endocrinologo getEndocrinologoByMatricula(Long matricula) {
		Endocrinologo endocrinologo = (Endocrinologo) getSession().createCriteria(Endocrinologo.class).add(Restrictions.eq("matricula_endo", matricula));
		if (endocrinologo == null){
			throw new EntityNotFoundException("No existe Endocrinologo con matricula :" + matricula);
		} else {
			return endocrinologo;
		}
	}

	@Override
	public Long loadIdEndocrinologoByUsername(String username)
			throws EntityNotFoundException {
		Endocrinologo endocrinologo = (Endocrinologo) getSession().createCriteria(Endocrinologo.class).add(Restrictions.eq("username", username));
		if (endocrinologo == null){
			throw new EntityNotFoundException("No existe Endocrinologo con username :" + username);
		} else {
			return endocrinologo.getId();
		}
	}
	
	

}
