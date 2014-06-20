package com.bcpv.webapp.controller.endo;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Especialista;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.webapp.controller.BaseFormController;

public class AbmEspecialistaController extends BaseFormController {
	
	@Autowired
	EspecialistaManager especialistaManager;
	
	public AbmEspecialistaController(){
		
	}
	
	 @RequestMapping(value = "/endos/especialistaList*", method = RequestMethod.GET)
	    public ModelAndView showEspecialistas(){
	        ModelAndView mav = new ModelAndView("endos/especialistaList");
	        List<Especialista> especialistas = especialistaManager.getEspecialistas();
	        mav.addObject("especialistaList", especialistas);
	        return mav;
	    }

	    @ModelAttribute
	    @RequestMapping(value = "/endos/especialistaForm*", method = RequestMethod.GET)
	    public Especialista editEspec(final HttpServletRequest request) {
	        String id = request.getParameter("id");
	        if (!StringUtils.isBlank(id)) {
	            return especialistaManager.getEspecialista(new Long(id));
	        } 
	        return new Especialista();
	    }

	    @RequestMapping(value = "/endos/especialistaForm*", method = RequestMethod.POST)
	    public String updateEspec(Especialista especialista, BindingResult errors, HttpServletRequest request,
	            HttpServletResponse response) {
	    	
	    	if (request.getParameter("cancel") != null) {
	            return "redirect:/endos/especialistaList*";
	        }
	 
	        if (validator != null) { // validator is null during testing
	            validator.validate(especialista, errors);
	 
	            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
	                return "endos/especialistaForm";
	            }
	        }
	 
	        log.debug("entering 'onSubmit' method...");
	 
	        boolean isNew = (especialista.getId() == null);
	        String success = "redirect:especialistaList";
	        Locale locale = request.getLocale();
	 
	        if (request.getParameter("delete") != null) {
	        	especialistaManager.removeEspecialista(especialista.getId());
	            saveMessage(request, getText("user.endocrinologist.specialistDeleted", locale));
	        } else {
	        	especialistaManager.saveEspecialista(especialista);
	            String key = (isNew) ? "user.endocrinologist.specialistSaved" : "user.endocrinologist.specialistUpdated";
	            saveMessage(request, getText(key, locale));
	 
	            if (!isNew) {
	                success = "redirect:especialistaList";
	            }
	        }
	 
	        return success;
	    }

}
