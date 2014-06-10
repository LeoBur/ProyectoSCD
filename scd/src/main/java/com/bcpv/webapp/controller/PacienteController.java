package com.bcpv.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcpv.model.Medicion;
import com.bcpv.model.MomentoDia;
import com.bcpv.model.Paciente;
import com.bcpv.model.Peso;
import com.bcpv.model.RegistroComidas;
import com.bcpv.service.ComidaManager;
import com.bcpv.service.MedicionManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PesoManager;
import com.bcpv.service.RegistroComidasManager;
import com.bcpv.webapp.controller.forms.PacienteForm;

@RequestMapping("/paciente/registrar*")
public class PacienteController extends BaseFormController {

	@Autowired
	MedicionManager medicionManager;
	
	@Autowired
	PesoManager pesoManager;
	
	@Autowired
	ComidaManager comidaManager;
	
	@Autowired
	PacienteManager pacienteManager;
	
	@Autowired
	RegistroComidasManager registroComidasManager;
	
	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	
	public PacienteController() {
		setCancelView("redirect:/home");
		setSuccessView("paciente");
	}
	
	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public PacienteForm showForm() {
		PacienteForm pacienteForm = new PacienteForm();
		return pacienteForm;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors, 
    					   HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(pacienteForm, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/registrar";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
        Paciente paciente = pacienteManager.getPacienteByUsername(pacienteForm.username);
        //Date date = formatter.parse(request.getParameter("datepicker"));
        
        if (pacienteForm.medicion != null){
        	Medicion medicion = new Medicion();
        	medicion.setF_medicion(pacienteForm.fechaHora);
        	medicion.setValor(new Integer (pacienteForm.medicion));
        	medicion.setPaciente(paciente);
        	
        	medicionManager.saveMedicion(medicion);
        }
        
        if(pacienteForm.peso != null){
        	Peso peso = new Peso();
        	peso.setFechaHora(pacienteForm.fechaHora);
        	peso.setPeso(new Float (pacienteForm.peso));
        	peso.setPaciente(paciente);
        	
        	pesoManager.savePeso(peso);
        }
        
        if(!pacienteForm.comidas.isEmpty() && pacienteForm.momento != null){
        	MomentoDia moment = new MomentoDia();
        	moment.setNombre(pacienteForm.momento);
        	moment.setComidas(pacienteForm.getComidas());
        	
        	RegistroComidas reg = new RegistroComidas();
        	reg.setFecha_registro_comida(pacienteForm.fechaHora);
        	reg.setPaciente(paciente);
        	reg.setMomentoDia(moment);
        	
        	registroComidasManager.saveRegistroComidas(reg);
        }
        
        saveMessage(request, getText("user.paciente.savedData", locale));
 
        /*if (request.getParameter("delete") != null) {
            personManager.remove(person.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
            personManager.save(person);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:personform?id=" + person.getId();
            }
        }*/
        return success;
    }
}
