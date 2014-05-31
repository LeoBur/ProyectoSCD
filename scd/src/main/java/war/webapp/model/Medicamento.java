package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="medicamento")
@Indexed
public class Medicamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -582669174967971464L;

	public enum GrupoMedicamento {INHIBIDOR_DE_ALFA_GLUCOSIDASA, SULFONILUREA, BIGUANIDA, GLINIDA, MEGLITINIDA, TZD, INSULINA, GLP_1,IAPP, DPP_4};
	
	private Long idMedicamento;
	private String nombreGenerico;
	private String nombreComercial;
	private String presentacion;
	private GrupoMedicamento grupoMedicamento;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	@Column(name="Nom_Generico",nullable = false)
	public String getNombreGenerico() {
		return nombreGenerico;
	}
	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}
	
	@Column(name="Nom_Comercial", nullable = false)
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
	@Column(name="Presentacion", nullable = false)
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	@Column(name="Grupo_Medicamento",nullable = false)
	@Enumerated(EnumType.STRING)
	public GrupoMedicamento getGrupoMedicamento() {
		return grupoMedicamento;
	}
	public void setGrupoMedicamento(GrupoMedicamento grupoMedicamento) {
		this.grupoMedicamento = grupoMedicamento;
	}
	

}
