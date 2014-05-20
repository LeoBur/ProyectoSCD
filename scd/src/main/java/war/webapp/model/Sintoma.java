package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Sintoma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 976324763501492361L;

	private Long idSintoma;
	private String nombre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdSintoma() {
		return idSintoma;
	}
	public void setIdSintoma(Long idSintoma) {
		this.idSintoma = idSintoma;
	}
	
	@Column(name = "Nombre_Sintoma", nullable = false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
