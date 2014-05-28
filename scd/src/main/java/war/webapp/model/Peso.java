package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="peso")
public class Peso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1317519453669811352L;
	
	private Long id;
	
	private DateTime fechaHora;
	
	private float peso;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="fechaHora",nullable= false)
	public DateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(DateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Column(name="peso", nullable=false)
	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

}
