package com.bcpv.webapp.controller.endo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.model.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Sintoma;
import com.bcpv.service.SintomaManager;
import com.bcpv.webapp.controller.BaseFormController;

@Controller
public class AbmSintomaController extends BaseFormController {

	@Autowired
	SintomaManager sintomaManager;
	
	public AbmSintomaController(){
		
	}

		@ModelAttribute
	 	@RequestMapping(value = "/endos/sintomaList*", method = RequestMethod.GET)
	    public ModelAndView showSintomas(@ModelAttribute("sintoma") final Sintoma sintoma, final HttpServletRequest request){
	        //Sintoma sintoma = new Sintoma();
			//return sintoma;
			ModelAndView mv = new ModelAndView("endos/sintomaList");
			List<Sintoma> sintomas;
			sintomas = sintomaManager.getSintomas();
			if (sintomas.size() == 0) {
				mv.addObject("sintomaList", sintomas);
				saveInfo(request, "No existe ningun Sintoma cargado");
				return mv;
			} else {
				mv.addObject("sintomaList", sintomas);
			}
			return mv;
	    }

	@RequestMapping(value = "/endos/sintomaList*", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("sintoma") final Sintoma sintoma, final BindingResult errors,
								 final HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("endos/sintomaList");
		List<Sintoma> sintomasFilter = new ArrayList<Sintoma>();

		/*if (validator != null) { // validator is null during testing
			validator.validate(medicamento, errors);

			if (errors.hasErrors()) {
				return mv;
			}
		}*/
		try{
			if (/*endoSearch.getApellidoPaciente() != null || */sintoma.getNombre() != " "){

				List<Sintoma> sintomas = sintomaManager.getSintomas();

				for (Sintoma sintomafilter : sintomas) {
					if (sintomafilter.getNombre().startsWith(sintoma.getNombre()) || (sintomafilter.getNombre().startsWith(sintoma.getNombre().toUpperCase()))) {
						sintomasFilter.add(sintomafilter);
					}
				}

				if (sintomasFilter.size() == 0) {
					mv.addObject("sintomaList", sintomas);
					saveInfo(request, "No existe el Sintoma");
					return mv;
				} else {
					mv.addObject("sintomaList", sintomasFilter);
				}

			} else {
				List<Sintoma> sintomas = sintomaManager.getSintomas();
				mv.addObject("sintomaList", sintomas);
			}
		} catch (EntityNotFoundException enfe){
			//COMPLETAR EL TRATAMIENTO DEL ERROR
			/*enfe.getMessage();
			errors.rejectValue(endoSearch.getApellidoPaciente(), "No existe");*/
			//para probar sin tratatmiento de error
			System.out.println(enfe.getMessage());
			//
			return mv;
		}

		return mv;
	}

	    @ModelAttribute
	    @RequestMapping(value = "/endos/sintomaForm*", method = RequestMethod.GET)
	    public Sintoma adminSint(final HttpServletRequest request) {
	    	String id = request.getParameter("id");
	    	if (!StringUtils.isBlank(id)) {
	            return sintomaManager.getSintoma(new Long(id));
	        } 
	        return new Sintoma();
	    }

	    @RequestMapping(value = "/endos/sintomaForm*", method = RequestMethod.POST)
	    public String updateSint(Sintoma sintoma, BindingResult errors, HttpServletRequest request,
	            HttpServletResponse response) {
	    	
	    	if (request.getParameter("cancel") != null) {
	            return "redirect:/endos/sintomaList";
	        }
	 
	        if (validator != null) { // validator is null during testing
	            validator.validate(sintoma, errors);
	 
	            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
	                return "endos/sintomaForm";
	            }
	        }
	 
	        log.debug("entering 'onSubmit' method...");
	 
	        boolean isNew = (sintoma.getIdSintoma() == null);
	        String success = "redirect:sintomaList";
	        Locale locale = request.getLocale();
	 
	        if (request.getParameter("delete") != null) {
	        	sintomaManager.removeSintoma(sintoma.getIdSintoma());
	            saveMessage(request, getText("user.endocrinologist.symptomDeleted", locale));
	        } else {
	        	sintomaManager.saveSintoma(sintoma);
	            String key = (isNew) ? "user.endocrinologist.symptomSaved" : "user.endocrinologist.symptomUpdated";
	            saveMessage(request, getText(key, locale));
	 
	            if (!isNew) {
	                success = "redirect:sintomaList";
	            }
	        }
	 
	        return success;
	    }
	@RequestMapping(value = "/getSintoma", method = RequestMethod.GET)
	@ResponseBody
	public List<Tag> getTags(@RequestParam String tagName) {
		int cont = 0;
		List<Tag> data = new ArrayList<Tag>();
		List<Tag> result = new ArrayList<Tag>();

		for (Sintoma sintoma : sintomaManager.getSintomas() ) {
			data.add(new Tag(cont++, sintoma.getNombre()));
		}

		for (Tag tag : data) {
			if (tag.getTagName().toLowerCase().startsWith(tagName.toLowerCase())) {
				result.add(tag);
			}
		}
		return result;
	}
}
	        
