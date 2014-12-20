package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.PacienteDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.TipoDiabetes;

@Repository("pacienteDao")
public class PacienteDaoHibernate extends GenericDaoHibernate<Paciente, Long> implements PacienteDao{

	public PacienteDaoHibernate() {
        super(Paciente.class);
    }
	
	public Paciente loadPacienteByDNI(Persona persona) throws EntityNotFoundException {
        Query qry = getSession().createQuery("from Paciente p where  p.persona.id = :id_persona");
        qry.setParameter("id_persona", persona.getId());
        Paciente paciente = (Paciente) qry.uniqueResult();
        if (paciente == null) {
            throw new EntityNotFoundException("No existe paciente asociado a la persona");
        } else {
            return paciente;
        }
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<Paciente> getPacientes() {
		Query qry = getSession().createQuery("from Paciente p order by upper(p.apellido)");
        return qry.list();
	}

    @Override
    public Paciente savePaciente(Paciente paciente) {
    	if (log.isDebugEnabled()) {
            log.debug("paciente id: " + paciente.getId());
        }
        getSession().saveOrUpdate(paciente);
        // necessary to throw a DataIntegrityViolation and catch it in MedicionManager
        getSession().flush();
        return paciente;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> loadPacientesByTipo(TipoDiabetes tipo) {
		List<Paciente> pacList = getSession().createCriteria(Paciente.class).add(Restrictions.eq("tipo", tipo)).list();
        if (pacList == null || pacList.isEmpty()) {
            throw new EntityNotFoundException("No existen pacientes de tipo '" + tipo);
        } else {
            return pacList;
        }
    }

	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Paciente> loadPacientesByApellido(List<Persona> persList) {
		List<Paciente> pacList = null;
		for(Persona persona:persList){
			pacList.add((Paciente) getSession().createCriteria(Paciente.class).add(Restrictions.eq("id_persona", persona.getId())));
		}
		if (pacList == null || pacList.isEmpty()) {
            throw new EntityNotFoundException("No existen pacientes");
        } else {
            return pacList;
        }
	}

	@Override
	public Paciente getPacienteByUsername(String username) {
        Query qry = getSession().createQuery("from Paciente p where p.persona.username = :user");
        qry.setParameter("user", username);
		Paciente paciente = (Paciente) qry.uniqueResult();
		if (paciente == null){
			throw new EntityNotFoundException("Paciente con username :" + username + " no existe");
		} else {
			return paciente;
		}
	}
}
