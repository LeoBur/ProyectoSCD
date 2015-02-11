package com.bcpv.webapp.controller.paciente;

import com.bcpv.model.Paciente;
import com.bcpv.model.Prescripcion;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.TratamientoManager;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
        Set<Tratamiento> tratamientos = pacienteManager.getPacienteByUsername(
                request.getRemoteUser()).getTratamientos();
        Tratamiento tratamiento = null;
        for (Tratamiento tr : tratamientos) {
            if (null == tratamiento ||  tratamiento.getIdTratamiento() < tr.getIdTratamiento()) {
                tratamiento = tr;
            }
        }
        mv.addObject("prescripcionesList", tratamiento.getPrescripciones());
        mv.addObject("fecha", new SimpleDateFormat("dd-MM-yyyy").format(tratamiento.getFechaTratamiento()));
        return mv;
    }

}
