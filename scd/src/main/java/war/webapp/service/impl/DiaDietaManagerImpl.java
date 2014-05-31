package war.webapp.service.impl;

import javax.persistence.EntityExistsException;

import war.webapp.dao.DiaDietaDao;
import war.webapp.model.DiaDieta;
import war.webapp.service.DiaDietaManager;
import war.webapp.service.DiaDietaService;

public class DiaDietaManagerImpl extends GenericManagerImpl<DiaDieta, Long> implements DiaDietaManager, DiaDietaService{

	private DiaDietaDao diaDietaDao;
	
	@Override
	public DiaDieta getDiaDieta(String idDiaDieta) {
		return diaDietaDao.get(new Long(idDiaDieta));
	}

	@Override
	public void removeDiaDieta(DiaDieta diaDieta) {
		log.debug("removing diaDieta id: "+diaDieta.getIdDiaDieta());
		diaDietaDao.remove(diaDieta);
		
	}

	@Override
	public void setDiaDietaDao(DiaDietaDao diaDietaDao) {
		this.dao=diaDietaDao;
		this.diaDietaDao=diaDietaDao;
		
	}

	@Override
	public DiaDieta getDiaDieta(Long idDiaDieta) {
		return diaDietaDao.get(idDiaDieta);
	}

	@Override
	public DiaDieta saveDiaDieta(DiaDieta diaDietaD)
			throws EntityExistsException {
		try{
			return diaDietaDao.save(diaDietaD);
		}
		catch (final Exception e){
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new EntityExistsException("DiaDieta ya existe");
		}
	}


	@Override
	public void removeDiaDieta(String idDiaDieta) {
		log.debug("removing DiaDieta id: "+idDiaDieta);
		diaDietaDao.remove(new Long(idDiaDieta));
		
	}

}
