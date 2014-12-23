package com.bcpv.webapp.controller.nutricionista;

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

import com.bcpv.model.Alimento;
import com.bcpv.service.AlimentoManager;
import com.bcpv.webapp.controller.BaseFormController;

@Controller
public class AbmAlimentoController extends BaseFormController{
	
	@Autowired
	AlimentoManager alimentoManager;
	
	public AbmAlimentoController(){
//		ver si es necesario esto:
//		setCancelView("redirect:");
//		setSuccessView("");	
	}
	
	@RequestMapping(value = "/nutri/alimentoList*", method = RequestMethod.GET)
    public ModelAndView showAlimentos(){
        ModelAndView mav = new ModelAndView("nutri/alimentoList");
        List<Alimento> alimentos = alimentoManager.getAlimentos();
        mav.addObject("alimentoList", alimentos);
        return mav;
    }

    @ModelAttribute
    @RequestMapping(value = "/nutri/alimentoForm*", method = RequestMethod.GET)
    public Alimento editAlimento(final HttpServletRequest request) {
    	final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return alimentoManager.getAlimento(new Long(id));
        }
        return new Alimento();
    }

    @RequestMapping(value = "/nutri/alimentoForm*", method = RequestMethod.POST)
    public String updateAlimento(Alimento alimento, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
    	if (request.getParameter("cancel") != null) {
            return "redirect:/nutri/alimentoList*";
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(alimento, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "nutri/alimentoForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (alimento.getIdAlimento() == null);
        String success = "redirect:alimentoList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	alimentoManager.removeAlimento(alimento.getIdAlimento());
            saveMessage(request, getText("user.nutritionist.foodDeleted", locale));
        } else {
        	alimentoManager.saveAlimento(alimento);
            String key = (isNew) ? "user.nutritionist.foodSaved" : "user.nutritionist.foodUpdated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
            	success = "redirect:medicamentoList";
            }
        }
 
        return success;
    }

    @RequestMapping(value = "/nutricionista/cargarAlimento*", method = RequestMethod.GET)
     public ModelAndView cargarAlimento() {
        ModelAndView mv = new ModelAndView("nutricionista/cargarAlimento");
        Alimento alimento = new Alimento();
        mv.addObject("alimentoForm", alimento);
        return mv;
    }

    @RequestMapping(value = "/nutricionista/cargarAlimento*", method = RequestMethod.POST)
    public String guardarAlimento(@ModelAttribute("Alimento") Alimento alimentoForm, BindingResult errors,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        String success = "redirect:pacienteList";
        Alimento alimento = new Alimento();
        alimento.setNombre(alimentoForm.getNombre());
        alimento.setCantGlucosaX100(alimentoForm.getCantGlucosaX100());
        alimento.setCantGrasasX100(alimentoForm.getCantGrasasX100());
        alimento.setCantProteinasX100(alimentoForm.getCantProteinasX100());
        alimento.setCantCarbohidratosX100(alimentoForm.getCantCarbohidratosX100());
        alimento.setCantCaloriasX100(alimentoForm.getCantCaloriasX100());
        alimentoManager.saveAlimento(alimento);
        return success;
    }

}
