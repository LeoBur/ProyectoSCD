package com.bcpv.dao.hibernate;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bcpv.dao.RegistroMedicamentoDao;
import com.bcpv.model.RegistroMedicamento;

@Repository("registroMedicamentoDao")
public class RegistroMedicamentoDaoHibernate extends GenericDaoHibernate<RegistroMedicamento, Long> implements RegistroMedicamentoDao{

	public RegistroMedicamentoDaoHibernate(){
		super(RegistroMedicamento.class);
	}
	
	@Override
	public RegistroMedicamento loadRegistroMedicamentoById(Long idRegMedicamento)
			throws EntityNotFoundException {
		RegistroMedicamento regMedicamento = (RegistroMedicamento) getSession().createCriteria(RegistroMedicamento.class).add(Restrictions.eq("id_reg_medicamento", idRegMedicamento));
		if (regMedicamento ==null){
			throw new EntityNotFoundException("RegistroMedicamento id: "+idRegMedicamento+" not found...");
		} else{
			return regMedicamento;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMedicamento> getRegistroMedicamento() {
		Query qry = getSession().createQuery("from registro_medicamento rm order by (rc.fch_reg_medicamento) DESC");
		return qry.list();
	}

	@Override
	public RegistroMedicamento saveRegistroMedicamento(RegistroMedicamento regMedicamento) {
		if (log.isDebugEnabled()){
			log.debug("RegistroMedicamento id: "+ regMedicamento.getIdRegMedicamento());
		}
		getSession().saveOrUpdate(regMedicamento);
		getSession().flush();
		return regMedicamento;
	}
}
