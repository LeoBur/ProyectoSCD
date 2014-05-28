package war.webapp.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import war.webapp.dao.EspecialistaDao;
import war.webapp.model.Especialista;

@Repository("especialistaDao")
public class EspecialistaDaoHibernate extends PersonaDaoHibernate implements EspecialistaDao{
	
	public List<Especialista> getEspecialistaByTipo(String tipo){
		Query qry = getSession().createQuery("from Especialista e order by upper(e.tipo_esp)");
        return qry.list();
	}

}
