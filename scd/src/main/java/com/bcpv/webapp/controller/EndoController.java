package com.bcpv.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	/*@Autowired
	EspecialistaManager especialistaManager;
	
	@Autowired
	SintomaManager sintomaManager;*/

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
	
	@RequestMapping(value = "/newPaciente", method = RequestMethod.POST)
	public String create(@ModelAttribute("newPaciente") Paciente paciente,
			BindingResult result, SessionStatus status) {
		validator.validate(paciente, result);
		if (result.hasErrors()) {
			return "newPaciente";
		}
		pacienteManager.savePaciente(paciente);
		status.setComplete();
		return "redirect:endo";
		
	}

	@RequestMapping(value = "/editPaciente", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Long id) {
		ModelAndView mav = new ModelAndView("editPaciente");
		Paciente paciente = pacienteManager.getPaciente(id);
		mav.addObject("editContact", paciente);
		return mav;
	}

	@RequestMapping(value = "/editPaciente", method = RequestMethod.POST)
	public String update(@ModelAttribute("editPaciente") Paciente paciente,
			BindingResult result, SessionStatus status) {
		validator.validate(paciente, result);
		if (result.hasErrors()) {
			return "editContact";
		}
		pacienteManager.savePaciente(paciente);
		status.setComplete();
		return "redirect:endo";
		
	}

	@RequestMapping("/deletePaciente")
	public ModelAndView delete(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("redirect:endo");
		
		pacienteManager.removePaciente(id);
		return mav;
	}

    @RequestMapping(value = "/endos/medicamentoList*", method = RequestMethod.GET)
    public ModelAndView showMedicamentos(){
        ModelAndView mav = new ModelAndView("endos/medicamentoList");
        List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
        mav.addObject("medicamentoList", medicamentos);
        return mav;
    }

    @RequestMapping(value = "/adminMedicamento", method = RequestMethod.GET)
    public ModelAndView adminMedic(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("adminMedicamento");
        Medicamento medicamento = medicamentoManager.getMedicamento(id);
        mav.addObject("adminMedicamento", medicamento);
        return mav;
    }

    @RequestMapping(value = "/adminMedicamento", method = RequestMethod.POST)
    public String updateMedic(@ModelAttribute("adminMedicamento") Medicamento medicamento,
                         BindingResult result, SessionStatus status) {
        validator.validate(medicamento, result);
        if (result.hasErrors()) {
            return "adminMedicamento";
        }
        medicamentoManager.saveMedicamento(medicamento);
        status.setComplete();
        return "redirect:medicamentoList";
    }

    /*@RequestMapping(value = "/especialistaList", method = RequestMethod.GET)
    public ModelAndView showEspecialistas(){
        ModelAndView mav = new ModelAndView("especialistaList");
        List<Especialista> especialistas = especialistaManager.getEspecialistas();
        mav.addObject("especialistaList", especialistas);
        return mav;
    }

    @RequestMapping(value = "/adminEspecialista", method = RequestMethod.GET)
    public ModelAndView adminEspec(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("adminEspecialista");
        Especialista especialista = especialistaManager.getEspecialista(id);
        mav.addObject("adminEspecialista", especialista);
        return mav;
    }

    @RequestMapping(value = "/adminEspecialista", method = RequestMethod.POST)
    public String updateEspec(@ModelAttribute("adminEspecialista") Especialista especialista,
                         BindingResult result, SessionStatus status) {
        validator.validate(especialista, result);
        if (result.hasErrors()) {
            return "adminEspecialista";
        }
        especialistaManager.saveEspecialista(especialista);
        status.setComplete();
        return "redirect:especialistaList";
    }

    @RequestMapping(value = "/sintomaList", method = RequestMethod.GET)
    public ModelAndView showSintomas(){
        ModelAndView mav = new ModelAndView("sintomaList");
        List<Sintoma> sintomas = sintomaManager.getSintomas();
        mav.addObject("especialistaList", sintomas);
        return mav;
    }

    @RequestMapping(value = "/adminSintoma", method = RequestMethod.GET)
    public ModelAndView adminSint(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("adminSintoma");
        Sintoma sintoma = sintomaManager.getSintoma(id);
        mav.addObject("adminEspecialista", sintoma);
        return mav;
    }

    @RequestMapping(value = "/adminSintoma", method = RequestMethod.POST)
    public String updateSintoma(@ModelAttribute("adminSintoma") Sintoma sintoma,
                              BindingResult result, SessionStatus status) {
        validator.validate(sintoma, result);
        if (result.hasErrors()) {
            return "adminSintoma";
        }
        sintomaManager.saveSintoma(sintoma);
        status.setComplete();
        return "redirect:sintomaList";
    }*/
}
