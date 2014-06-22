package com.bcpv.webapp.controller.endo;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Persona;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.service.PacienteEnTratamientoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.PersonaForm;

@Controller
public class ABMPersonaController extends BaseFormController{
	
	@Autowired
	PacienteManager pacienteManager;
	
	@Autowired
	PersonaManager personaManager;
	
	@Autowired
	EspecialistaManager especialistaManager;
	
	@Autowired
	PacienteEnTratamientoManager pacienteEnTratamientoManager;
	
	public ABMPersonaController(){
		setCancelView("");//COMPLETAR
		setSuccessView("");//COMPLETAR
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mv = new ModelAndView("");//COMPLETAR
		PersonaForm personaForm = new PersonaForm();
		mv.addObject("personaForm", personaForm);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("personaForm") PersonaForm personaForm, BindingResult errors, 
    					   HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(personaForm, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "";//COMPLETAR
            }
        }
 
        log.debug("entering 'onSubmit' method...");
        boolean isNew = (personaForm.getIdPersona() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
        if (request.getParameter("delete") != null) {
        	//Evaluar el delete
        	/*pacienteManager.removePaciente(paciente.getId());
            saveMessage(request, getText("user.endocrinologist.pacientDeleted", locale));*/
        } else {
        	Persona persona = new Persona();
        	//SEGUIR DESDE ACA
        	
        	
            String key = (isNew) ? "user.endocrinologist.pacientSaved" : "user.endocrinologist.pacientUpdated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:pacienteList";
            }
        }
 
        return success;
	}
}
