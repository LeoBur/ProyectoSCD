package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.bcpv.dao.RegistroComidasDao;
import com.bcpv.model.RegistroComidas;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("registroComidasDao")
public class RegistroComidasDaoHibernate extends GenericDaoHibernate<RegistroComidas, Long> implements RegistroComidasDao{

	public RegistroComidasDaoHibernate(){
		super(RegistroComidas.class);
	}

	@Override
	public RegistroComidas loadRegistroComidasById(Long idRegComidas)
			throws EntityNotFoundException {
		RegistroComidas regComidas = (RegistroComidas) getSession().createCriteria(RegistroComidas.class).add(Restrictions.eq("id_reg_comida", idRegComidas));
		if (regComidas ==null){
			throw new EntityNotFoundException("RegistroComidas id: "+idRegComidas+" not found...");
		} else{
			return regComidas;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroComidas> getRegistroComidas() {
		Query qry = getSession().createQuery("from registro_comidas rc order by (rc.fecha_reg_comida) DESC");
		return qry.list();
	}

	@Override
	public RegistroComidas saveRegistroComidas(RegistroComidas regComidas) {
		if (log.isDebugEnabled()){
			log.debug("RegistroComida id: "+ regComidas.getIdRegistroComida());
		}
		getSession().saveOrUpdate(regComidas);
		getSession().flush();
		return regComidas;
	}
}
