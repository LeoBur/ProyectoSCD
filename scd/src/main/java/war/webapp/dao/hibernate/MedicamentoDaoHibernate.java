package war.webapp.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import war.webapp.dao.MedicamentoDao;
import war.webapp.model.Medicamento;

@Repository("medicamentoDao")
public class MedicamentoDaoHibernate extends GenericDaoHibernate<Medicamento, Long> implements MedicamentoDao{

	public MedicamentoDaoHibernate(){
		super(Medicamento.class);
	}
	

	@Override
	public Medicamento loadMedicamentoById(Long idMedicamento)
			throws EntityNotFoundException {
		Medicamento medicamento = (Medicamento) getSession().createCriteria(Medicamento.class).add(Restrictions.eq("idMedicamento", idMedicamento));
		if(medicamento == null)
			throw new EntityNotFoundException("Medicamento id: "+idMedicamento+" is not found...");
		else 
			return medicamento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> getMedicamentos() {
		Query qry = getSession().createQuery("from Medicamento m order by upper(nombreGenerico)");
		return qry.list();
	}

	@Override
	public Medicamento save(Medicamento medicamento) {
		if(log.isDebugEnabled()){
			log.debug("medicamento id: "+medicamento.getIdMedicamento());
		}
		getSession().saveOrUpdate(medicamento);
		getSession().flush();
		return medicamento;
	}

}
