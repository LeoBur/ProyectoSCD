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
//import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.model.Paciente;
import com.bcpv.model.Tag;
import com.bcpv.service.PacienteManager;
import com.bcpv.webapp.controller.forms.EndoSearch;

@Controller
public class EndoController extends BaseFormController {

	@Autowired
	PacienteManager pacienteManager;

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
	@RequestMapping(value = "/endo/endo*", method = RequestMethod.GET)
	public EndoSearch showForm() {
		EndoSearch search = new EndoSearch();
		return search;
	}

	@RequestMapping(value = "/endo/endo*", method = RequestMethod.POST)
	public ModelAndView onSubmit(EndoSearch endoSearch, BindingResult errors,
			HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("endo/endo");

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
}
