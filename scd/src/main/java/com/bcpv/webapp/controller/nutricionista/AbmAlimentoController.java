package com.bcpv.webapp.controller.nutricionista;

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

    @ModelAttribute
	@RequestMapping(value = "/nutricionista/alimentoList*", method = RequestMethod.GET)
    public ModelAndView showAlimentos(@ModelAttribute("alimento") final Alimento alimento, final HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/nutricionista/alimentoList");
        List<Alimento> alimentos;
        alimentos = alimentoManager.getAlimentos();
        if (alimentos.size() == 0) {
            mv.addObject("alimentoList", alimentos);
            saveInfo(request, "No existe ningun Alimento cargado");
            return mv;
        } else {
            mv.addObject("alimentoList", alimentos);
        }
        return mv;
    }

    @RequestMapping(value = "/nutricionista/alimentoList*", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("alimento") final Alimento alimento, final BindingResult errors,
                                 final HttpServletRequest request) throws Exception {

        ModelAndView mv = new ModelAndView("nutricionista/alimentoList");
        List<Alimento> alimentosFilter = new ArrayList<Alimento>();

        try{
            if (alimento.getNombre() != " "){

                List<Alimento> alimentos = alimentoManager.getAlimentos();

                for (Alimento alimentofilter : alimentos) {
                    if (alimentofilter.getNombre().startsWith(alimento.getNombre()) || (alimentofilter.getNombre().startsWith(alimento.getNombre().toUpperCase()))) {
                        alimentosFilter.add(alimentofilter);
                    }
                }

                if (alimentosFilter.size() == 0) {
                    mv.addObject("alimentoList", alimentos);
                    saveInfo(request, "No existe el Alimento");
                    return mv;
                } else {
                    mv.addObject("alimentoList", alimentosFilter);
                }

            } else {
                List<Alimento> alimentos = alimentoManager.getAlimentos();
                mv.addObject("alimentoList", alimentos);
            }
        } catch (EntityNotFoundException enfe){

            System.out.println(enfe.getMessage());
            //
            return mv;
        }

        return mv;
    }

    @ModelAttribute
    @RequestMapping(value = "/nutricionista/alimentoForm*", method = RequestMethod.GET)
    public Alimento editAlimento(final HttpServletRequest request) {
    	final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return alimentoManager.getAlimento(new Long(id));
        }
        return new Alimento();
    }

    @RequestMapping(value = "/nutricionista/alimentoForm*", method = RequestMethod.POST)
    public String updateAlimento(Alimento alimento, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
    	if (request.getParameter("cancel") != null) {
            return "redirect:/nutricionista/alimentoList*";
        }

        if (validator != null) { // validator is null during testing
            validator.validate(alimento, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "nutricionista/alimentoForm";
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
            	success = "redirect:alimentoList";
            }
        }
 
        return success;
    }

    @ModelAttribute
    @RequestMapping(value = "/nutricionista/cargarAlimento*", method = RequestMethod.GET)
    public Alimento cargarAlimento(final HttpServletRequest request) {
        String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return alimentoManager.getAlimento(new Long(id));
        }
        return new Alimento();
    }

    @RequestMapping(value = "/nutricionista/cargarAlimento*", method = RequestMethod.POST)
    public String guardarAlimento(Alimento alimento, BindingResult errors, HttpServletRequest request,
                             HttpServletResponse response) {

        if (request.getParameter("cancel") != null) {
            return "redirect:/nutricionista/alimentoList";
        }

        if (validator != null) { // validator is null during testing
            validator.validate(alimento, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "nutricionista/cargarAlimento";
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
                success = "redirect:alimentoList";
            }
        }

        return success;
    }

    @RequestMapping(value = "/getAlimento", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Alimento alimento : alimentoManager.getAlimentos()) {
            data.add(new Tag(cont++, alimento.getNombre()));
        }

        for (Tag tag : data) {
            if (tag.getTagName().toLowerCase().startsWith(tagName.toLowerCase())) {
                result.add(tag);
            }
        }
        return result;
    }
}
