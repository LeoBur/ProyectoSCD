package war.webapp.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import war.webapp.dao.EndocrinologoDao;
import war.webapp.model.Endocrinologo;

public interface EndocrinologoManager extends GenericManager<Endocrinologo, Long>{
	
	void setEndocrinologoDao(EndocrinologoDao endocrinologoDao);
	   
    Endocrinologo getEndocrinologo(Long id);

    List<Endocrinologo> getEndocrinologos();

    Endocrinologo saveEndocrinologo(Endocrinologo endocrinologo) throws EntityExistsException;
    
    void removeEndocrinologo(Endocrinologo endocrinologo);
    
    void removeEndocrinologo(String id);

    List<Endocrinologo> search(String searchTerm);
		
	Endocrinologo getEndocrinologoByMatricula(Long matricula);

}
