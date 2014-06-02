package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

import war.webapp.model.Departamento;

public interface DepartamentoDao extends GenericDao<Departamento, Long> {
	
	@Transactional
    Departamento loadDepartamentoByNombre(String nombre) throws EntityNotFoundException;

    List<Departamento> getDepartamentos();
    
    Departamento saveDepartamento (Departamento departamento);

}
