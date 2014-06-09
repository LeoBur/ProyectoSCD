package com.bcpv.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcpv.model.Comida;
import com.bcpv.model.Medicion;
import com.bcpv.model.Peso;
import com.bcpv.service.ComidaManager;
import com.bcpv.service.MedicionManager;
import com.bcpv.service.PesoManager;
import com.bcpv.webapp.controller.forms.PacienteForm;

@RequestMapping("/paciente*")
public class PacienteController extends BaseFormController {

	@Autowired
	MedicionManager medicionManager;
	
	@Autowired
	PesoManager pesoManager;
	
	@Autowired
	ComidaManager comidaManager;
	
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
    public String onSubmit(PacienteForm paciente, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(paciente, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/registrar";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
        //Date date = formatter.parse(request.getParameter("datepicker"));
        
        if (paciente.medicion != null){
        	Medicion medicion = new Medicion();
        	medicion.setF_medicion(paciente.fechaHora);
        	medicion.setValor(new Integer (paciente.medicion));
        	
        	medicionManager.saveMedicion(medicion);
        }
        
        if(paciente.peso != null){
        	Peso peso = new Peso();
        	peso.setFechaHora(paciente.fechaHora);
        	peso.setPeso(new Float (paciente.peso));
        	
        	pesoManager.savePeso(peso);
        }
        
        if(paciente.comida !=null){
        	Comida comida = new Comida();
        	comida.setAlimento(paciente.comida.getAlimento());
        	comida.setCantidad(paciente.comida.getCantidad());
        	
        	comidaManager.saveComida(comida);
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
