package war.webapp.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import war.webapp.model.Tratamiento;

public interface TratamientoDao extends GenericDao<Tratamiento, Long> {
	
	Tratamiento loadTratamientoById(Long idTratamiento) throws EntityNotFoundException;
	
	List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente);
	
	Tratamiento saveTratamiento(Tratamiento tratamiento);
	
	Tratamiento getUltimoTratamientoByIdPaciente(Long idPaciente);

}
