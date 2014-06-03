package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.PacienteDao;
import com.bcpv.model.Paciente;
import com.bcpv.model.TipoDiabetes;

@Repository("pacienteDao")
public class PacienteDaoHibernate extends GenericDaoHibernate<Paciente, Long> implements PacienteDao{

	public PacienteDaoHibernate() {
        super(Paciente.class);
    }
	
	@Override
	public Paciente loadPacienteByDNI(Long dni) throws EntityNotFoundException {
		Paciente paciente = (Paciente) getSession().createCriteria(Paciente.class).add(Restrictions.eq("dni", dni));
		if (paciente == null){
			throw new EntityNotFoundException("Paciente con DNI :" + dni + " no existe");
		} else {
			return paciente;
		}
	}
	
    @SuppressWarnings("unchecked")
    public List<Paciente> getPacientes() {
		Query qry = getSession().createQuery("from paciente p order by upper(p.apellido)");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> loadPacientesByApellido(String apellido) {
		List<Paciente> pacList = getSession().createCriteria(Paciente.class).add(Restrictions.like("apellido", apellido)).list();
        if (pacList == null || pacList.isEmpty()) {
            throw new EntityNotFoundException("No existen pacientes " + apellido);
        } else {
            return pacList;
        }
	}
}
