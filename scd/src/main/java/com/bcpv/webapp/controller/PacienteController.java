package com.bcpv.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.model.Medicion;
import com.bcpv.service.MedicionManager;

@RequestMapping("/paciente")
public class PacienteController extends BaseFormController {

	@Autowired
	MedicionManager medicionManager;
		
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public ModelAndView showRegistros() {
		ModelAndView mav = new ModelAndView("registrar");
		List<Medicion> registros = medicionManager.getMediciones();
		mav.addObject(Constants.MEDICION_LIST, registros);
		return mav;
	}

	@RequestMapping(value = "/saveMedicion", method = RequestMethod.GET)
	public ModelAndView newmedicionForm() {
		ModelAndView mav = new ModelAndView("newMedicion");
		Medicion medicion = new Medicion();
		mav.getModelMap().put("newMedicion", medicion);
		return mav;
	}

	@RequestMapping(value = "/saveMedicion", method = RequestMethod.POST)
	public String create(@ModelAttribute("newMedicion") Medicion medicion,
			BindingResult result, SessionStatus status) {
		validator.validate(medicion, result);
		if (result.hasErrors()) {
			return "newMedicion";
		}
		medicionManager.save(medicion);
		status.setComplete();
		return "redirect:registrar";
	}



}
