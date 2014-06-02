package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.DepartamentoDao;
import war.webapp.model.Departamento;

@Repository("departamentoDao")
public class DepartamentoDaoHibernate extends GenericDaoHibernate<Departamento, Long> implements DepartamentoDao{
	
	public DepartamentoDaoHibernate() {
        super(Departamento.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<Departamento> getDepartamentos() {
        Query qry = getSession().createQuery("from departamentos d order by upper(u.nombre)");
        return qry.list();
    }

    
    public Departamento saveDepartamento(Departamento departamento) {
        if (log.isDebugEnabled()) {
            log.debug("departamento id: " + departamento.getId());
        }
        getSession().saveOrUpdate(departamento);
        
        getSession().flush();
        return departamento;
    }
    
    @Override
    public Departamento save(Departamento departamento) {
        return this.saveDepartamento(departamento);
    }

    
    public Departamento loadDepartamentoByNombre(String nombre) throws EntityNotFoundException {
        List<?> depList = getSession().createCriteria(Departamento.class).add(Restrictions.eq("nombre", nombre)).list();
        if (depList == null || depList.isEmpty()) {
            throw new EntityNotFoundException("departamento '" + nombre + "' not found...");
        } else {
            return (Departamento) depList.get(0);
        }
    }
}
