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

@Entity
@Table(name="comida")
public class Comida implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7379603373775505732L;
	
	private Long idComida;
	private String cantidad;
	private Alimento alimento;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdComida() {
		return idComida;
	}
	public void setIdComida(Long idComida) {
		this.idComida = idComida;
	}
	
	@Column(name="cantidad",nullable=false)
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idAlimento",nullable=false)
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

}
