package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Domicilio")
public class Domicilio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9013815718475143878L;
	
	private Long id;
	private Localidad localidad;
	private String calle;
	private Long numero;
	private String piso;
	private String dpto;
	
	@Id
	@Column(name="id", unique = true, nullable= false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="Localidad", unique = false, nullable=false)
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	@Column(name="calle", unique = false, nullable = false)
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@Column(name="nro")
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	@Column(name="piso")
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	@Column(name="dpto")
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

}
