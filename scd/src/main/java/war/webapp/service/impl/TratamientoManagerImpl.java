package war.webapp.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import war.webapp.dao.TratamientoDao;
import war.webapp.model.Tratamiento;
import war.webapp.service.TratamientoManager;
import war.webapp.service.TratamientoService;

@Service("tratamientoManager")
@WebService(serviceName="TratamientoManager", endpointInterface = "war.webapp.service.TratamientoService")
public class TratamientoManagerImpl extends GenericManagerImpl<Tratamiento, Long> implements TratamientoManager, TratamientoService{

	private TratamientoDao tratamientoDao;
	
	@Override
	public void setTratamientoDao(TratamientoDao tratamientoDao){
		this.dao=tratamientoDao;
		this.tratamientoDao=tratamientoDao;
	}
	
	@Override
	public Tratamiento getTratamiento(String idTratamiento) {
		return tratamientoDao.get(new Long(idTratamiento));
	}

	@Override
	public List<Tratamiento> getTratamientosByIdPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tratamiento getUltimoTratamientoByIdPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Tratamiento getTratamiento(Long idTratamiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tratamiento> getTratamientosByIdPaciente(Long idPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tratamiento saveTratamiento(Tratamiento tratamiento)
			throws EntityExistsException {
		try{
			return tratamientoDao.save(tratamiento);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("Tratamiento ya existe");
		}
	}

	@Override
	public void removeTratamiento(Tratamiento tratamiento) {
		log.debug("Removing tratamiento id: "+tratamiento.getIdTratamiento());
		tratamientoDao.remove(tratamiento);
		
	}

	@Override
	public void removeTratamiento(Long idTratamiento) {
		log.debug("Removing tratamiento id: "+idTratamiento);
		tratamientoDao.remove(idTratamiento);
		
	}

	@Override
	public List<Tratamiento> search(String searchTerm) {
		return super.search(searchTerm, Tratamiento.class);
	}

}
