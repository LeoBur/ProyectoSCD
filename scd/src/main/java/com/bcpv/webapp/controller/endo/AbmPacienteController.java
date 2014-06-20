package com.bcpv.webapp.controller.endo;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcpv.model.Paciente;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.webapp.controller.BaseFormController;

public class AbmPacienteController extends BaseFormController{
	
	@Autowired
	PacienteManager pacienteManager;
	
	@Autowired
	EndocrinologoManager endocrinologoManager;
	
	public AbmPacienteController(){
		
	}
	
	@ModelAttribute
	@RequestMapping(value = "/endos/pacienteForm*", method = RequestMethod.GET)
	public Paciente edit(final HttpServletRequest request) {
		
		final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return pacienteManager.getPaciente(new Long(id));
        }
        return new Paciente();
	}
	
	@RequestMapping(value = "/endos/pacienteForm*", method = RequestMethod.POST)
    public String onSubmit(Paciente paciente, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:/endos/endo";
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(paciente, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/pacienteForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (paciente.getId() == null);
        String success = "redirect:pacienteList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	pacienteManager.removePaciente(paciente.getId());
            saveMessage(request, getText("user.endocrinologist.pacientDeleted", locale));
        } else {
        	paciente.setEndocrinologo(endocrinologoManager.getEndocrinologo(new Long(0))); //para probar BORRAR!!
        	pacienteManager.savePaciente(paciente);
            String key = (isNew) ? "user.endocrinologist.pacientSaved" : "user.endocrinologist.pacientUpdated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:pacienteList";
            }
        }
 
        return success;
    }

}
