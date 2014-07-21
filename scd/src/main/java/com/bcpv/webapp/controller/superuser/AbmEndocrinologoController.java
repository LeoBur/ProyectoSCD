package com.bcpv.webapp.controller.superuser;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Localidad;
import com.bcpv.model.Persona;
import com.bcpv.model.Provincia;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.ProvinciaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;

@Controller
public class AbmEndocrinologoController extends BaseFormController{
	
	@Autowired
	PersonaManager personaManager;
	
	@Autowired
	EndocrinologoManager endocrinologoManager;
	
	@Autowired
	ProvinciaManager provinciaManager;
	
	@Autowired
	LocalidadManager localidadManager;
	
	public AbmEndocrinologoController(){
		
	}

	@RequestMapping(value = "admin/buscar*", method = RequestMethod.GET)
	public String buscar(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm, BindingResult errors, 
    					   HttpServletRequest request) {
		try {
			Persona persona = personaManager.getPersonaByDni(new Long (endocrinologoForm.getDni()));
			if (persona.getId() != null) {
				endocrinologoForm.setId(persona.getId());
				endocrinologoForm.setUsername(persona.getUsername());
				endocrinologoForm.setPassword(persona.getPassword());
				endocrinologoForm.setConfirmPassword(persona.getConfirmPassword());
				endocrinologoForm.setFirstName(persona.getFirstName());
				endocrinologoForm.setLastName(persona.getLastName());
				endocrinologoForm.setEmail(persona.getEmail());
				endocrinologoForm.setPhoneNumber(persona.getPhoneNumber());
				endocrinologoForm.setFch_nac(persona.getFch_nac());
				endocrinologoForm.setSexo(persona.getSexo());
				endocrinologoForm.setDomicilio(persona.getDomicilio());
			} else throw new EntityNotFoundException();
			
		} catch (EntityNotFoundException enfe){
			saveInfo(request, "No existe una persona con ese DNI. Por favor complete los campos.");
			return "admin/newEndocrinologo";
		}
		return "admin/newEndocrinologo";
	}
	
	@RequestMapping(value = "admin/newEndocrinologo*", method = RequestMethod.GET)
	public ModelAndView showForm(final HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/newEndocrinologo");
		if (request.getAttribute("endocrinologoForm") == null) {
			EndocrinologoForm endocrinologoForm = new EndocrinologoForm();
			mv.addObject("endocrinologoForm", endocrinologoForm);
		}
		List<Provincia> provincias = provinciaManager.getProvincias();
		List<Localidad> localidades = localidadManager.getLocalidades();
		mv.addObject("provinciaList", provincias);
		mv.addObject("localidadList", localidades);
		return mv;
	}
	
	@RequestMapping(value = "admin/newEndocrinologo*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm, BindingResult errors, 
    					   HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(endocrinologoForm, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/registrar";
            }
        }
 
        boolean isNew = (endocrinologoForm.getId() == null);
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
        
        Persona persona = personaManager.getPersonaByDni(new Long (endocrinologoForm.getDni()));
        
        persona.setDni(endocrinologoForm.getDni());
        persona.setFirstName(endocrinologoForm.getFirstName());
        persona.setLastName(endocrinologoForm.getLastName());
        persona.setPassword(endocrinologoForm.getPassword());
        persona.setConfirmPassword(endocrinologoForm.getConfirmPassword());
        persona.setEmail(endocrinologoForm.getEmail());
        persona.setPhoneNumber(endocrinologoForm.getPhoneNumber());
        persona.setFch_nac(endocrinologoForm.getFch_nac());
        persona.setSexo(endocrinologoForm.getSexo());
        persona.setDomicilio(endocrinologoForm.getDomicilio());
        
        Endocrinologo endocrinologo = new Endocrinologo(endocrinologoForm.getMatricula(), persona);
        
        saveMessage(request, getText("user.savedData", locale));
 
        if (request.getParameter("delete") != null) {
            endocrinologoManager.remove(endocrinologoForm.getId());
            saveMessage(request, getText("admin.endocrinologist.deleted", locale));
        } else {
        	personaManager.savePersona(persona);
        	endocrinologoManager.saveEndocrinologo(endocrinologo);
            String key = (isNew) ? "admin.endocrinologist.added" : "admin.endocrinologist.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:newEndocrinologo";
            }
        }
        return success;
    }	
}
