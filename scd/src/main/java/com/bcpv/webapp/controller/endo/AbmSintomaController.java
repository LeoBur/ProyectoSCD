package com.bcpv.webapp.controller.endo;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	 @RequestMapping(value = "/endos/sintomaList*", method = RequestMethod.GET)
	    public ModelAndView showSintomas(){
	        ModelAndView mav = new ModelAndView("endos/sintomaList");
	        List<Sintoma> sintomas = sintomaManager.getSintomas();
	        mav.addObject("sintomaList", sintomas);
	        return mav;
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
}
	        
