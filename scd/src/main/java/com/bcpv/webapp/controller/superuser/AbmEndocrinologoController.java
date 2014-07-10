package com.bcpv.webapp.controller.superuser;

import java.util.List;
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
@RequestMapping("admin/newEndocrinologo*")
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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mv = new ModelAndView("paciente/registrar");
		EndocrinologoForm endocrinologoForm = new EndocrinologoForm();
		List<Provincia> provincias = provinciaManager.getProvincias();
		List<Localidad> localidades = localidadManager.getLocalidades();
		mv.addObject("endocrinologoForm", endocrinologoForm);
		mv.addObject("provinciaList", provincias);
		mv.addObject("localidadList", localidades);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
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
 
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
        
        Persona persona = personaManager.getPersona(endocrinologoForm.getDni()); //Implementar una busqueda por dni en personaManager y cambiar por ese metodo
        	
        persona.setFirstName(endocrinologoForm.getFirstName());
        persona.setLastName(endocrinologoForm.getLastName());
        persona.setPassword(endocrinologoForm.getPassword());
        persona.setConfirmPassword(endocrinologoForm.getConfirmPassword());
        persona.setEmail(endocrinologoForm.getEmail());
        persona.setPhoneNumber(endocrinologoForm.getPhoneNumber());
        persona.setFch_nac(endocrinologoForm.getFch_nac());
        persona.setSexo(endocrinologoForm.getSexo());
        persona.setDomicilio(endocrinologoForm.getDomicilio());
        	
        personaManager.save(persona);
        
        Endocrinologo endocrinologo = new Endocrinologo(endocrinologoForm.getMatricula(), persona);
        endocrinologoManager.saveEndocrinologo(endocrinologo);
        
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
