package com.bcpv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name = "localidades")
@Indexed
public class Localidad implements Serializable {

	private static final long serialVersionUID = 15876869834L;
	
	private int id;
	private Departamento departamento;        //required
	private Provincia provincia;              //required
	private String nombre;                    //required
	private Set<Domicilio> domicilios = new HashSet<Domicilio>();

	public Localidad() {
	}

	public Localidad(int id, Departamento departamento,
			Provincia provincia, String nombre) {
		this.id = id;
		this.departamento = departamento;
		this.provincia = provincia;
		this.nombre = nombre.toUpperCase();
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_departamento", nullable = false)
	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_provincia", nullable = false)
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}
	
	
	@OneToMany(fetch = FetchType.EAGER)
	//@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Set<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(Set<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Localidad)) {
            return false;
        }

        final Localidad localidad = (Localidad) o;

        return !(nombre != null ? !nombre.equals(localidad.nombre) : localidad.nombre != null);

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
