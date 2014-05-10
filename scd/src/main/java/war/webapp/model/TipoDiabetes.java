package war.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TiposDiabetes")
public class TipoDiabetes implements Serializable{

	private static final long serialVersionUID = 2692401869897053144L;
	
	private int id_tipo;
	private String tipoDiab;
	private String Caract;
	
	@Id
	@Column(name = "id_tipo_dibetes")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_tipo() {
		return id_tipo;
	}
	
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	
	@Column(name = "tipo_dibetes")
	public String getTipoDiab() {
		return tipoDiab;
	}
	
	public void setTipoDiab(String tipoDiab) {
		this.tipoDiab = tipoDiab;
	}
	
	@Column(name = "caracteristica")
	public String getCaract() {
		return Caract;
	}
	public void setCaract(String caract) {
		Caract = caract;
	}

}
