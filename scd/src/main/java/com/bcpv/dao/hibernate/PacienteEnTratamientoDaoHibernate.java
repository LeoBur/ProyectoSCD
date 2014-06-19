package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcpv.dao.PacienteEnTratamientoDao;
import com.bcpv.model.PacienteEnTratamiento;

public class PacienteEnTratamientoDaoHibernate extends GenericDaoHibernate<PacienteEnTratamiento, Long> implements PacienteEnTratamientoDao{

	public PacienteEnTratamientoDaoHibernate(){
		super(PacienteEnTratamiento.class);
	}

	@Override
	public PacienteEnTratamiento loadPacienteEnTratamientoById(Long idPacienteEnTratamiento)
			throws EntityNotFoundException {
		PacienteEnTratamiento pacienteEnTratamiento = (PacienteEnTratamiento) getSession().createCriteria(PacienteEnTratamiento.class).add(Restrictions.eq("idPacienteEnTratamiento", idPacienteEnTratamiento));
		if (pacienteEnTratamiento == null){
			throw new EntityNotFoundException("PacienteEnTratamiento id: "+idPacienteEnTratamiento+" not found...");
		} else
			return pacienteEnTratamiento;
	}

	@Override
	public PacienteEnTratamiento savePacienteEnTratamiento(PacienteEnTratamiento pacienteEnTratamiento) {
		if(log.isDebugEnabled()){
			log.debug("pacienteEnTratamiento id: "+pacienteEnTratamiento.getIdPacienteEnTratamiento());
		}
		getSession().saveOrUpdate(pacienteEnTratamiento);
		getSession().flush();
		return pacienteEnTratamiento;
	}
}
