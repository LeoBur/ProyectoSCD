package com.bcpv.webapp.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Especialista;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Paciente;
import com.bcpv.model.Sintoma;
import com.bcpv.model.Tag;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.SintomaManager;
import com.bcpv.webapp.controller.forms.EndoSearch;

@Controller
public class EndoController extends BaseFormController {

	@Autowired
	PacienteManager pacienteManager;
	
	@Autowired
	MedicamentoManager medicamentoManager;
	
	@Autowired
	EspecialistaManager especialistaManager;
	
	@Autowired
	SintomaManager sintomaManager;
	
	@Autowired
	EndocrinologoManager endocrinologoManager;

	public EndoController() {
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

	/*@ModelAttribute("mainPlaceHolder")
	public String getMainPlaceHolder() {
		return getText("endo.pacienteToSearch", Locale.getDefault());
	}*/

	/*==========Busqueda de PACIENTE================*/
	@ModelAttribute
	@RequestMapping(value = "/endos/endo*", method = RequestMethod.GET)
	public EndoSearch showForm() {
		EndoSearch search = new EndoSearch();
		return search;
	}

	@RequestMapping(value = "/endos/endo*", method = RequestMethod.POST)
	public ModelAndView onSubmit(EndoSearch endoSearch, BindingResult errors,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("endos/endo");

		if (validator != null) { // validator is null during testing
			validator.validate(endoSearch, errors);

			if (errors.hasErrors()) {
				return mv;
			}
		}

		List<Paciente> pacientes = pacienteManager.loadPacientesByApellido(endoSearch.getPacienteToSearch());
		mv.addObject(Constants.PACIENTE_LIST, pacientes);
		return mv;
	}

	@ModelAttribute
	@RequestMapping(value = "/endos/pacienteForm*", method = RequestMethod.GET)
	public Paciente edit(final HttpServletRequest request) {
		
		final String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return pacienteManager.getPaciente(new Long(id));
        }
        return new Paciente();
	}
	
	@RequestMapping(value = "/endos/pacienteForm*", method = RequestMethod.POST)
    public String onSubmit(Paciente paciente, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:/endos/endo";
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(paciente, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/pacienteForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (paciente.getId() == null);
        String success = "redirect:pacienteList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	pacienteManager.removePaciente(paciente.getId());
            saveMessage(request, getText("user.endocrinologist.pacientDeleted", locale));
        } else {
        	paciente.setEndocrinologo(endocrinologoManager.getEndocrinologo(new Long(0))); //para probar BORRAR!!
        	pacienteManager.savePaciente(paciente);
            String key = (isNew) ? "user.endocrinologist.pacientSaved" : "user.endocrinologist.pacientUpdated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:pacienteList";
            }
        }
 
        return success;
    }

	/*==========Administración de MEDICAMENTOS================*/
	
    @RequestMapping(value = "/endos/medicamentoList*", method = RequestMethod.GET)
    public ModelAndView showMedicamentos(){
        ModelAndView mav = new ModelAndView("endos/medicamentoList");
        List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
        mav.addObject("medicamentoList", medicamentos);
        return mav;
    }

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
            return "redirect:/endos/medicamentoList*";
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
//                success = "redirect:medicamentoForm?id=" + medicamento.getIdMedicamento();
            }
        }
 
        return success;
    }
    
    /*==========Administración de ESPECIALISTA================*/

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
    
    /*==========Administración de SINTOMA================*/

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
