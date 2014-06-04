package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.TratamientoDao;
import com.bcpv.model.Tratamiento;

@Repository("tratamientoDao")
public class TratamientoDaoHibernate extends GenericDaoHibernate<Tratamiento, Long> implements TratamientoDao{
	
	public TratamientoDaoHibernate(){
		super(Tratamiento.class);
	}

	@Override
	public Tratamiento loadTratamientoById(Long idTratamiento) throws EntityNotFoundException {
		Tratamiento tratamiento = (Tratamiento) getSession().createCriteria(Tratamiento.class).add(Restrictions.eq("idTratamiento", idTratamiento));
		if (tratamiento == null){
			throw new EntityNotFoundException("Tratamiento id: "+idTratamiento+" not found...");
		} else
			return tratamiento;
	}

	
	@Override
	public Tratamiento saveTratamiento(Tratamiento tratamiento) {
		if(log.isDebugEnabled()){
			log.debug("tratamiento id: "+tratamiento.getIdTratamiento());
		}
		getSession().saveOrUpdate(tratamiento);
		getSession().flush();
		return tratamiento;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente) {
		Query qry = getSession().createQuery("from Tratamiento t where t.idPaciente = idPaciente order by (t.fechaTratamiento) DESC");
		return qry.list();
	}

	@Override
	public Tratamiento getUltimoTratamientoByIdPaciente(Long idPaciente) {
		Query qry = getSession().createQuery("from Tratamiento t where t.idPaciente = idPaciente order by (t.fechaTratamiento) DESC");
		if(qry == null){
			return null;
		} else
			return (Tratamiento) qry.list().get(0);
			
	}

}
