package war.webapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alimento {
	
	@Id
	private int id_alimento;

	public int getId_alimento() {
		return id_alimento;
	}

	public void setId_alimento(int id_alimento) {
		this.id_alimento = id_alimento;
	}

}
