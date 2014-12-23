package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcpv.dao.PrescripcionDao;
import com.bcpv.model.Prescripcion;

public class PrescripcionDaoHibernate extends GenericDaoHibernate<Prescripcion, Long> implements PrescripcionDao{

	public PrescripcionDaoHibernate(){
		super(Prescripcion.class);
	}

	@Override
	public Prescripcion loadPrescripcionById(Long idPrescripcion)
			throws EntityNotFoundException {
		Prescripcion prescripcion = (Prescripcion) getSession().createCriteria(Prescripcion.class).add(Restrictions.eq("idPrescripcion", idPrescripcion));
		if (prescripcion == null){
			throw new EntityNotFoundException("Prescripcion id: "+idPrescripcion+" not found...");
		} else
			return prescripcion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prescripcion> getPrescripcionesByIdTratamiento(
			Long idTratamiento) {
		Query qry = getSession().createQuery("from Prescripcion p where p.id_tratamiento = idTratamiento order by (p.id_medicamento) DESC");
		return qry.list();
	}

	@Override
	public Prescripcion savePrescripcion(Prescripcion prescripcion) {
		if(log.isDebugEnabled()){
			log.debug("prescripcion id: "+prescripcion.getIdPrescripcion());
		}
		getSession().saveOrUpdate(prescripcion);
		return prescripcion;
	}
	
}
