package com.bcpv.webapp.controller.paciente;

import com.bcpv.model.Paciente;
import com.bcpv.model.Prescripcion;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.TratamientoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class ConsultaTratamientoController extends BaseFormController{

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    TratamientoManager tratamientoManager;

	public ConsultaTratamientoController(){
		
	}

    @RequestMapping(method = RequestMethod.GET, value = "/paciente/medicacion*")
    @ResponseBody
    public ModelAndView getTratamiento(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paciente/medicacion");
        Paciente paciente = pacienteManager.getPacienteByUsername(request.getRemoteUser());
        Set<Tratamiento> tratamientos = paciente.getTratamientos();
        for (Tratamiento tratamiento : tratamientos) {
            Set<Prescripcion> prescripcions = tratamiento.getPrescripciones();
            mv.addObject("prescripcionList", prescripcions);
            return mv;
        }
        return mv;
    }

}
