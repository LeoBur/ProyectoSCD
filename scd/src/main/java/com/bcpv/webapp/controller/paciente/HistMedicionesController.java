package com.bcpv.webapp.controller.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Medicion;
import com.bcpv.model.Paciente;
import com.bcpv.service.MedicionManager;
import com.bcpv.webapp.controller.BaseFormController;

/**
 * Controlador para mostrar el historial de mediciones de un paciente
 *
 */

@Controller
public class HistMedicionesController extends BaseFormController{
	
	@Autowired
	MedicionManager medicionManager;
	
	public HistMedicionesController(){
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHistorialMediciones(){
		ModelAndView mav = new ModelAndView("paciente/historial/mediciones");
		Paciente paciente = (Paciente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Medicion> mediciones = medicionManager.getMedicionesByIdPaciente(paciente.getId());
        mav.addObject("medicionList", mediciones);
        return mav;
	}

}
