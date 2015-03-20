package com.bcpv.webapp.controller.endo;

import com.bcpv.model.Tag;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.service.TipoDiabetesManager;
import com.bcpv.webapp.controller.BaseFormController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 20/03/2015.
 */
@Controller
public class ABMTipoDiabetes extends BaseFormController{
    
    @Autowired
    private TipoDiabetesManager tipoDiabetesManager;

    @ModelAttribute
    @RequestMapping(value = "/endos/tipoDiabetesList*", method = RequestMethod.GET)
    public ModelAndView showTipoDiabetess(@ModelAttribute("tipo") final TipoDiabetes tipoDiabetes,
                                         final HttpServletRequest request){
        ModelAndView mv = new ModelAndView("endos/tipoDiabetesList");
        List<TipoDiabetes> tipoDiabetesList;
        tipoDiabetesList = tipoDiabetesManager.getTiposDiabetes();
        if (tipoDiabetesList.size() == 0) {
            mv.addObject("tipoDiabetesList", tipoDiabetesList);
            saveInfo(request, "No existe ningun TipoDiabetes cargado");
            return mv;
        } else {
            mv.addObject("tipoDiabetesList", tipoDiabetesList);
        }
        return mv;
    }

    @RequestMapping(value = "/endos/tipoDiabetesList*", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("tipoDiabetes") final TipoDiabetes tipoDiabetes,
                                 final BindingResult errors,
                                 final HttpServletRequest request) throws Exception {

        ModelAndView mv = new ModelAndView("endos/tipoDiabetesList");
        List<TipoDiabetes> tiposFilter = new ArrayList<TipoDiabetes>();

		/*if (validator != null) { // validator is null during testing
			validator.validate(tipoDiabetes, errors);

			if (errors.hasErrors()) {
				return mv;
			}
		}*/
        try{
            if (/*endoSearch.getApellidoPaciente() != null || */tipoDiabetes.getTipoDiab() != " "){

                List<TipoDiabetes> tipoDiabetesList = tipoDiabetesManager.getTiposDiabetes();

                for (TipoDiabetes diabetes : tipoDiabetesList) {
                    if (diabetes.getTipoDiab().startsWith(tipoDiabetes.getTipoDiab())
                            || (diabetes.getTipoDiab().startsWith(tipoDiabetes.getTipoDiab().toUpperCase()))) {
                        tiposFilter.add(diabetes);
                    }
                }

                if (tiposFilter.size() == 0) {
                    mv.addObject("tipoDiabetesList", tipoDiabetesList);
                    saveInfo(request, "No existe el Tipo de Diabetes");
                    return mv;
                } else {
                    mv.addObject("tipoDiabetesList", tiposFilter);
                }

            } else {
                List<TipoDiabetes> tiposDiabetes = tipoDiabetesManager.getTiposDiabetes();
                mv.addObject("tipoDiabetesList", tiposDiabetes);
            }
        } catch (EntityNotFoundException enfe){

            System.out.println(enfe.getMessage());

            return mv;
        }

        return mv;
    }

    @ModelAttribute
    @RequestMapping(value = "/endos/tipoDiabetesForm*", method = RequestMethod.GET)
    public TipoDiabetes editTipo(final HttpServletRequest request) {
        final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return tipoDiabetesManager.getTipoDiabetes(new Long(id));
        }
        return new TipoDiabetes();
    }

    @RequestMapping(value = "/endos/tipoDiabetesForm*", method = RequestMethod.POST)
    public String updateTipo(TipoDiabetes tipoDiabetes, BindingResult errors, HttpServletRequest request,
                             HttpServletResponse response) {
        if (request.getParameter("cancel") != null) {
            return "redirect:/endos/tipoDiabetesList";
        }

        if (validator != null) { // validator is null during testing
            validator.validate(tipoDiabetes, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/tipoDiabetesForm";
            }
        }

        log.debug("entering 'onSubmit' method...");
        TipoDiabetes tipo = tipoDiabetesManager.getTipoDiabetesByName(tipoDiabetes.getTipoDiab());
        boolean isNew = (tipo == null);
        String success = "redirect:tipoDiabetesList";
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            tipoDiabetesManager.removeTipoDiabetes(tipoDiabetes);
            saveMessage(request, getText("user.endocrinologist.typeDeleted", locale));
        } else {
            tipoDiabetesManager.saveTipoDiabetes(tipoDiabetes);
            String key = (isNew) ? "user.endocrinologist.typeSaved" : "user.endocrinologist.typeUpdated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:tipoDiabetesList";
//                success = "redirect:medicamentoForm?id=" + tipoDiabetes.getIdTipoDiabetes();
            }
        }

        return success;
    }

    @RequestMapping(value = "/getTipoDiabetes", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (TipoDiabetes tipoDiabetes : tipoDiabetesManager.getTiposDiabetes()) {
            data.add(new Tag(cont++, tipoDiabetes.getTipoDiab()));
        }

        for (Tag tag : data) {
            if (tag.getTagName().toLowerCase().startsWith(tagName.toLowerCase())) {
                result.add(tag);
            }
        }
        return result;
    }
}
