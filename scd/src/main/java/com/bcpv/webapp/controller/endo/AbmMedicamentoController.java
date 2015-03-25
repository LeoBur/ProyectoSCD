package com.bcpv.webapp.controller.endo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.model.GrupoMedicamento;
import com.bcpv.service.GrupoMedicamentoManager;
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

import com.bcpv.Constants;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Persona;
import com.bcpv.model.Tag;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndoSearch;

@Controller
public class AbmMedicamentoController extends BaseFormController{
	
	@Autowired
	MedicamentoManager medicamentoManager;

    @Autowired
    GrupoMedicamentoManager grupoMedicamentoManager;
	
	public AbmMedicamentoController(){
		
	}
	
	/*Busqueda de Medicamentos	 */
	
	@ModelAttribute
	@RequestMapping(value = "/endos/medicamentoList*", method = RequestMethod.GET)
    public ModelAndView showMedicamentos(@ModelAttribute("medicamento") final Medicamento medicamento, final HttpServletRequest request){
		ModelAndView mv = new ModelAndView("endos/medicamentoList");
		List<Medicamento> medicamentos;
		medicamentos = medicamentoManager.getMedicamentos();
		if (medicamentos.size() == 0) {
			mv.addObject("medicamentoList", medicamentos);
			saveInfo(request, "No existe ningun Medicamento cargado");
			return mv;
		} else {
			mv.addObject("medicamentoList", medicamentos);
		}
		return mv;
    }
	
	@RequestMapping(value = "/endos/medicamentoList*", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("medicamento") final Medicamento medicamento, final BindingResult errors,
			final HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("endos/medicamentoList");
		List<Medicamento> medicamentosFilter = new ArrayList<Medicamento>();

		/*if (validator != null) { // validator is null during testing
			validator.validate(medicamento, errors);

			if (errors.hasErrors()) {
				return mv;
			}
		}*/
		try{
			if (/*endoSearch.getApellidoPaciente() != null || */medicamento.getNombreComercial() != " "){

				List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();

				for (Medicamento medicamentofilter : medicamentos) {
					if (medicamentofilter.getNombreComercial().startsWith(medicamento.getNombreComercial()) || (medicamentofilter.getNombreComercial().startsWith(medicamento.getNombreComercial().toUpperCase()))) {
						medicamentosFilter.add(medicamentofilter);
					}
				}

				if (medicamentosFilter.size() == 0) {
					mv.addObject("medicamentoList", medicamentos);
					saveInfo(request, "No existe el Medicamento");
					return mv;
				} else {
					mv.addObject("medicamentoList", medicamentosFilter);
				}
				
			} else {
				List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
		        mv.addObject("medicamentoList", medicamentos);
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
	
	/*@RequestMapping(value = "/endos/medicamentoList*", method = RequestMethod.GET)
    public ModelAndView showMedicamentos(){
        ModelAndView mav = new ModelAndView("endos/medicamentoList");
        List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
        mav.addObject("medicamentoList", medicamentos);
        return mav;
    }*/

    @ModelAttribute
    @RequestMapping(value = "/endos/medicamentoForm*", method = RequestMethod.GET)
    public Medicamento editMedic(final HttpServletRequest request) {
    	final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return medicamentoManager.getMedicamento(new Long(id));
        }
        return new Medicamento();
    }

    @RequestMapping(value = "/endos/medicamentoForm*", method = RequestMethod.POST)
    public String updateMedic(Medicamento medicamento, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
    	if (request.getParameter("cancel") != null) {
            return "redirect:/endos/medicamentoList";
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(medicamento, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/medicamentoForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (medicamento.getIdMedicamento() == null);
        String success = "redirect:medicamentoList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	medicamentoManager.removeMedicamento(medicamento.getIdMedicamento());
            saveMessage(request, getText("user.endocrinologist.medicinDeleted", locale));
        } else {
        	medicamentoManager.saveMedicamento(medicamento);
            String key = (isNew) ? "user.endocrinologist.medicinSaved" : "user.endocrinologist.medicinUpdated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
            	success = "redirect:medicamentoList";
            }

            GrupoMedicamento grupo = null;
            try {
                 grupo = grupoMedicamentoManager.getByNombre(medicamento.getGrupoMedicamento());
            } catch (EntityNotFoundException e) {
                grupo = new GrupoMedicamento(medicamento.getGrupoMedicamento());
                grupoMedicamentoManager.saveGrupoMedicamento(grupo);
            }
        }
 
        return success;
    }
    
    @RequestMapping(value = "/getMedicamento", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
    	int cont = 0;
    	List<Tag> data = new ArrayList<Tag>();
    	List<Tag> result = new ArrayList<Tag>();

    	for (Medicamento medicamento : medicamentoManager.getMedicamentos() ) {
    		data.add(new Tag(cont++, medicamento.getNombreComercial()));
    		data.add(new Tag(cont++, medicamento.getNombreGenerico()));
    	}

    	for (Tag tag : data) {
    		if (tag.getTagName().toLowerCase().startsWith(tagName.toLowerCase())) {
    			result.add(tag);
    		}
    	}
    	return result;
    }

    @RequestMapping(value = "/getGrupos", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getGruposTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (GrupoMedicamento grupoMedicamento : grupoMedicamentoManager.getGrupoMedicamentos()) {
            data.add(new Tag(cont++, grupoMedicamento.getNombre()));
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
}
