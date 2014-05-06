package war.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import war.webapp.dao.MedicionDao;
import war.webapp.model.Medicion;
import war.webapp.service.MedicionExistsException;
import war.webapp.service.MedicionManager;
import war.webapp.service.MedicionService;

@Service("medicionManager")
@WebService(serviceName = "MedicionService", endpointInterface = "war.webapp.service.MedicionService")
public class MedicionManagerImpl extends GenericManagerImpl<Medicion, Long> implements MedicionManager, MedicionService {

	private MedicionDao medicionDao;
	
	@Override
	public void setMedicionDao(MedicionDao medicionDao) {
		this.dao = medicionDao;
		this.medicionDao = medicionDao;
		
	}
	
	@Override
	public Medicion getMedicion(String id_medicion) {
		return medicionDao.get(new Long(id_medicion));
	}

	@Override
	public Medicion getMedicion(Long id_medicion) {
		return medicionDao.get(id_medicion);
	}

	@Override
	public List<Medicion> getMediciones() {
		if (medicionDao!=null){
			return medicionDao.getAllDistinct();
		}
		return new ArrayList<Medicion>();
	}

	@Override
	public Medicion saveMedicion(final Medicion medicion)
			throws MedicionExistsException {
		try {
			return medicionDao.saveMedicion(medicion);
		} catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new MedicionExistsException("Medición ya existe");
		}
	}

	@Override
	public List<Medicion> search(String searchTerm) {
		return super.search(searchTerm, Medicion.class);
	}

	@Override
	public void removeMedicion(Long id_medicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMedicion(Medicion medicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMedicion(String id_medicion) {
		// TODO Auto-generated method stub
		
	}

}
