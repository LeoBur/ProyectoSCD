package war.webapp.model;

import java.io.Serializable;

public class Domicilio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9013815718475143878L;
	
	private Long idDomicilio;
	private Localidad localidad;
	private String calle;
	private Long numero;
	private String piso;
	private String dpto;
	public Long getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(Long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

}
