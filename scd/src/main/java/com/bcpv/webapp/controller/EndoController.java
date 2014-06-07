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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.model.Especialista;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Paciente;
import com.bcpv.model.Sintoma;
import com.bcpv.model.Tag;
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

	public EndoController() {
		setCancelView("redirect:/home");
		setSuccessView("endo");
	}

	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	@ResponseBody
	public List<Tag> getTags(@RequestParam String tagName) {
		int cont = 0;
		List<Tag> data = new ArrayList<Tag>();
		List<Tag> result = new ArrayList<Tag>();

		for (Paciente paciente : pacienteManager.getPacientes()) {
			data.add(new Tag(cont++, paciente.getApellido()));
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

	@ModelAttribute("mainPlaceHolder")
	public String getMainPlaceHolder() {
		return getText("endo.pacienteToSearch", Locale.getDefault());
	}

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
	
	/*@RequestMapping(value = "/newPaciente", method = RequestMethod.POST)
	public String create(@ModelAttribute("newPaciente") Paciente paciente,
			BindingResult result, SessionStatus status) {
		validator.validate(paciente, result);
		if (result.hasErrors()) {
			return "newPaciente";
		}
		pacienteManager.savePaciente(paciente);
		status.setComplete();
		return "redirect:endo";
		
	}*/

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
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(paciente, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/pacienteForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (paciente.getId() == null);
        String success = "endos/pacienteList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	pacienteManager.removePaciente(paciente.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
        	pacienteManager.savePaciente(paciente);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:endos/pacienteForm?id=" + paciente.getId();
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
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(medicamento, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "endos/medicamentoForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (medicamento.getIdMedicamento() == null);
        String success = "endos/medicamentoList";
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	medicamentoManager.removeMedicamento(medicamento.getIdMedicamento());
            saveMessage(request, getText("person.deleted", locale));
        } else {
        	medicamentoManager.saveMedicamento(medicamento);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:endos/medicamentoForm?id=" + medicamento.getIdMedicamento();
            }
        }
 
        return success;
    }
    
    /*==========Administración de ESPECIALISTA================*/

    @RequestMapping(value = "/endos/especialistaList", method = RequestMethod.GET)
    public ModelAndView showEspecialistas(){
        ModelAndView mav = new ModelAndView("endos/especialistaList");
        List<Especialista> especialistas = especialistaManager.getEspecialistas();
        mav.addObject("especialistaList", especialistas);
        return mav;
    }

    @RequestMapping(value = "/endos/editEspecialista/{id}", method = RequestMethod.GET)
    public ModelAndView adminEspec(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("editEspecialista");
        Especialista especialista = especialistaManager.getEspecialista(id);
        mav.addObject("especialista", especialista);
        return mav;
    }

    @RequestMapping(value = "/endos/editEspecialista", method = RequestMethod.POST)
    public String updateEspec(@ModelAttribute("especialista") Especialista especialista,
                         BindingResult result, SessionStatus status) {
        validator.validate(especialista, result);
        if (result.hasErrors()) {
            return "endos/editEspecialista";
        }
        especialistaManager.saveEspecialista(especialista);
        status.setComplete();
        return "redirect:endos/especialistaList";
    }
    
    /*==========Administración de SINTOMA================*/

    @RequestMapping(value = "/endos/sintomaList", method = RequestMethod.GET)
    public ModelAndView showSintomas(){
        ModelAndView mav = new ModelAndView("endos/sintomaList");
        List<Sintoma> sintomas = sintomaManager.getSintomas();
        mav.addObject("sintomaList", sintomas);
        return mav;
    }
    
    /*
	 * @RequestMapping(value="/team/edit/{id}", method=RequestMethod.GET)
		public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team",team);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/team/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {
         
        ModelAndView modelAndView = new ModelAndView("home");
         
        teamService.updateTeam(team);
         
        String message = "Team was successfully edited.";
        modelAndView.addObject("message", message);
         
        return modelAndView;
    }
	 */

    @RequestMapping(value = "/endos/editSintoma/{id}", method = RequestMethod.GET)
    public ModelAndView adminSint(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("endos/editSintoma");
        Sintoma sintoma = sintomaManager.getSintoma(id);
        mav.addObject("sintoma", sintoma);
        return mav;
    }

    @RequestMapping(value = "/endos/editSintoma", method = RequestMethod.POST)
    public String updateSintoma(@ModelAttribute("sintoma") Sintoma sintoma,
                              BindingResult result, SessionStatus status) {
        validator.validate(sintoma, result);
        if (result.hasErrors()) {
            return "endos/editSintoma";
        }
        sintomaManager.saveSintoma(sintoma);
        status.setComplete();
        return "redirect:sintomaList";
    }
}
