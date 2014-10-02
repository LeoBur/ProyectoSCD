package com.bcpv.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@XmlRootElement
public class Persona extends User implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083353825043735836L;

	public enum Sexo {F,M};
    
	private String dni;
    private Date fch_nac;
    private Sexo sexo;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Persona() {
    }
    
    @Column(name = "dni", nullable = false, unique = true)
    public String getDni() {
        return dni;
    }
    
    @Column(name = "fch_nac")
	public Date getFch_nac() {
		return fch_nac;
	}
    
    @Column(name = "sexo")
    public Sexo getSexo() {
		return sexo;
	}

    public void setDni(String dni) {
        this.dni=dni;
    }
    
    public void setFch_nac(Date fch_nac) {
		this.fch_nac = fch_nac;
	}
    
    public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
