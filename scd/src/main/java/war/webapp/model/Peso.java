package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.joda.time.DateTime;

@Entity
@Table(name="peso")
@Indexed
public class Peso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1317519453669811352L;
	
	private Long id;
	
	private DateTime fechaHora;
	
	private float peso;
	private Paciente paciente;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_peso")
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_paciente",nullable=false)
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
