package com.bcpv.webapp.controller.endo;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.Tag;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndoSearch;

@Controller
public class BusquedaPacienteController extends BaseFormController{
	
	@Autowired
	PacienteManager pacienteManager;
	
	@Autowired
	PersonaManager personaManager;
	
	public BusquedaPacienteController(){
		
	}
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	@ResponseBody
	public List<Tag> getTags(@RequestParam String tagName) {
		int cont = 0;
		List<Tag> data = new ArrayList<Tag>();
		List<Tag> result = new ArrayList<Tag>();

		for (Paciente paciente : pacienteManager.getPacientes()) {
			data.add(new Tag(cont++, paciente.getPersona().getLastName()));
		}

		// iterate a list and filter by tagName
		for (Tag tag : data) {
			if (tag.getTagName().toLowerCase()
					.startsWith(tagName.toLowerCase())) {
				result.add(tag);
			}
		}
		return result;
	}

	@ModelAttribute("apellidoPlaceHolder")
	public String getApellidoPlaceHolder() {
		return getText("endo.apellidoEjemplo", Locale.getDefault());
	}
	
	@ModelAttribute("dniPlaceHolder")
	public String getDNIPlaceHolder() {
		return getText("endo.dniEjemplo", Locale.getDefault());
	}

	/*==========Busqueda de PACIENTE================*/
	@ModelAttribute
	@RequestMapping(value = "/endos/endo*", method = RequestMethod.GET)
	public EndoSearch showForm() {
		EndoSearch search = new EndoSearch();
		return search;
	}

	@RequestMapping(value = "/endos/endo*", method = RequestMethod.POST)
	public ModelAndView onSubmit(final EndoSearch endoSearch, final BindingResult errors,
			final HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("endos/endo");

		if (validator != null) { // validator is null during testing
			validator.validate(endoSearch, errors);

			if (errors.hasErrors()) {
				return mv;
			}
		}
		try{
			if (/*endoSearch.getApellidoPaciente() != null || */endoSearch.getApellidoPaciente() != " "){
				List<Persona> persList = personaManager.getPersonasByApellido(endoSearch.getApellidoPaciente());
				pacienteManager.loadPacientesByApellido(persList).isEmpty();
				mv.addObject(Constants.PACIENTE_LIST, pacienteManager.loadPacientesByApellido(persList));
				
			} else {
				Persona persona = personaManager.getPersonaByDni(endoSearch.getDniPaciente());
				mv.addObject(Constants.PACIENTE_LIST, pacienteManager.loadPacienteByDNI(persona));
			}
		} catch (EntityNotFoundException enfe){
			//COMPLETAR EL TRATAMIENTO DEL ERROR
			/*enfe.getMessage();
			errors.rejectValue(endoSearch.getApellidoPaciente(), "No existe");*/
			//para probar sin tratatmiento de error
			saveError(request, "No Existe Paciente");
			//errors.addError(new ObjectError(ERRORS_MESSAGES_KEY, "No Existe Paciente"));
			System.out.println(enfe.getMessage());
			//
			return mv;
		}
			
		return mv;
	}

}
