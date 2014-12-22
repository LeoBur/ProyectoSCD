package com.bcpv.webapp.controller.paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bcpv.model.Peso;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PesoManager;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Medicion;
import com.bcpv.model.Paciente;
import com.bcpv.service.MedicionManager;
import com.bcpv.webapp.controller.BaseFormController;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlador para mostrar el historial de mediciones de un paciente
 *
 */

@Controller
public class HistorialController extends BaseFormController{
	
	@Autowired
	MedicionManager medicionManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    PesoManager pesoManager;
	
	public HistorialController(){
		
	}

    @RequestMapping(method = RequestMethod.GET, value = "/paciente/histMediciones*")
    @ResponseBody
    public ModelAndView getMediciones(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paciente/histMediciones");
        Long pacienteId = pacienteManager.getPacienteByUsername(request.getRemoteUser()).getId();
        List<Medicion> mediciones = medicionManager.getMedicionesByIdPaciente(pacienteId);
        mv.addObject("medicionesList", mediciones);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/paciente/histPesos*")
    @ResponseBody
    public ModelAndView getPesos(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paciente/histPesos");
        Long pacienteId = pacienteManager.getPacienteByUsername(request.getRemoteUser()).getId();
        List<Peso> pesos = pesoManager.getPesosByIdPaciente(pacienteId);
        mv.addObject("pesosList", pesos);
        return mv;
    }
}
