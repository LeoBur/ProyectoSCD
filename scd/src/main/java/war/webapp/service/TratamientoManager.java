package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.TratamientoDao;
import war.webapp.model.Tratamiento;

public interface TratamientoManager extends GenericManager<Tratamiento, Long> {
	
	void setTratamientoDao(TratamientoDao tratamientoDao);
	
	Tratamiento getTratamiento(Long idTratamiento);
	
	List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente);
	
	Tratamiento saveTratamiento(Tratamiento tratamiento) throws EntityExistsException;
	
	void removeTratamiento(Tratamiento tratamiento);
	
	void removeTratamiento(Long idTratamiento);
	
	List<Tratamiento> search(String searchTerm);

}
