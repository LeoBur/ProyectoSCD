package com.bcpv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name="Alimento")
@Indexed
public class Alimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855173095772179711L;
	
	private Long idAlimento;
	private String nombre;
	private Long cantGlucosaX100;
	private Long cantGrasasX100;
	private Long cantProteinasX100;
	private Long cantCarbohidratosX100;
	private Long cantCaloriasX100;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}
	
	@Column(name = "Nombre_Alimento", nullable = false)
	public String getNombreAlimento() {
		return nombre;
	}
	public void setNombreAlimento(String nombreAlimento) {
		this.nombre = nombreAlimento.toUpperCase();
	}
	
	@Column(name = "cantGlucosaX100")
	public Long getCantGlucosaX100() {
		return cantGlucosaX100;
	}
	public void setCantGlucosaX100(Long cantGlucosaX100) {
		this.cantGlucosaX100 = cantGlucosaX100;
	}
	
	@Column(name = "cantGrasasX100")
	public Long getCantGrasasX100() {
		return cantGrasasX100;
	}
	public void setCantGrasasX100(Long cantGrasasX100) {
		this.cantGrasasX100 = cantGrasasX100;
	}
	
	@Column(name = "cantProteinasX100")
	public Long getCantProteinasX100() {
		return cantProteinasX100;
	}
	public void setCantProteinasX100(Long cantProteinasX100) {
		this.cantProteinasX100 = cantProteinasX100;
	}
	
	@Column(name = "cantCaloriasX100")
	public Long getCantCaloriasX100() {
		return cantCaloriasX100;
	}
	public void setCantCaloriasX100(Long cantCaloriasX100) {
		this.cantCaloriasX100 = cantCaloriasX100;
	}
	
	@Column(name = "cantCarbohidratosX100")
	public Long getCantCarbohidratosX100() {
		return cantCarbohidratosX100;
	}
	public void setCantCarbohidratosX100(Long cantCarbohidratosX100) {
		this.cantCarbohidratosX100 = cantCarbohidratosX100;
	}
	

}
