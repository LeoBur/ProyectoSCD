package war.webapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "provincias")
@Indexed
@XmlRootElement
public class Provincia implements Serializable {

	private static final long serialVersionUID = 197685765342342L;
	
	private int id;
	private String nombre;
	private Set<Departamento> departamentos = new HashSet<Departamento>();
	private Set<Localidad> localidades = new HashSet<Localidad>();

	public Provincia() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Departamento> getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Localidad> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}

	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Provincia)) {
            return false;
        }

        final Provincia provincia = (Provincia) o;

        return !(nombre != null ? !nombre.equals(provincia.nombre) : provincia.nombre != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (nombre != null ? nombre.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.nombre)
                .toString();
    }

}
