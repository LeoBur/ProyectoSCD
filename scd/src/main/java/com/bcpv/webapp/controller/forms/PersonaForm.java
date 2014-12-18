package com.bcpv.webapp.controller.forms;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bcpv.model.Domicilio;
import com.bcpv.model.Persona;

public class PersonaForm {

    Long id;
    String username;
    String password;
    String confirmPassword;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String dni;
    Date dia;
    Persona.Sexo sexo;
    String provincia;
    String localidad;
    String calle;
    String numero;
    String dpto;
    String piso;
    boolean enabled;


    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.setProvincia(domicilio.getLocalidad().getProvincia().getNombre());
        this.setLocalidad(domicilio.getLocalidad().getNombre());
        this.setCalle(domicilio.getCalle());
        this.setDpto(domicilio.getDpto());
        this.setPiso(domicilio.getPiso());
        this.setNumero(domicilio.getNumero().toString());
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    /*public void setFechaNac(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        this.setDia(strDate);
        //this.setDia(strDate.substring(0, 2));
        //this.setMes(strDate.substring(3,5));
        //this.setAnio(strDate.substring(6,10));
    }*/

    public com.bcpv.model.Persona.Sexo getSexo() {
        return sexo;
    }
    public void setSexo(com.bcpv.model.Persona.Sexo sexo) {
        this.sexo = sexo;
    }
}
