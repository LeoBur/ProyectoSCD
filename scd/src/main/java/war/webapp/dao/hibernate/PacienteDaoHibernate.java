package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import war.webapp.dao.PacienteDao;
import war.webapp.model.Paciente;
import war.webapp.model.TipoDiabetes;

public class PacienteDaoHibernate extends PersonaDaoHibernate implements PacienteDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> loadPacientesByTipo(TipoDiabetes tipo) {
		List<Paciente> pacList = getSession().createCriteria(Paciente.class).add(Restrictions.eq("tipo", tipo)).list();
        if (pacList == null || pacList.isEmpty()) {
            throw new EntityNotFoundException("No existen pacientes de tipo '" + tipo);
        } else {
            return (List<Paciente>) pacList.get(0);
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Paciente> getPacientes(){
		Query qry = getSession().createQuery("from Paciente p order by upper(p.apellido_persona)");
        return qry.list();
	}
}
