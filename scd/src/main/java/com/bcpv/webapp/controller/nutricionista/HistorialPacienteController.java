package com.bcpv.webapp.controller.nutricionista;

import com.bcpv.model.Medicion;
import com.bcpv.model.Paciente;
import com.bcpv.model.Peso;
import com.bcpv.service.MedicionManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PesoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Leo on 19/03/2015.
 */
@Controller
public class HistorialPacienteController {

    @Autowired
    private PacienteManager pacienteManager;

    @Autowired
    private MedicionManager medicionManager;

    @Autowired
    private PesoManager pesoManager;

    @RequestMapping(method = RequestMethod.GET, value = "/nutricionista/medicionesPaciente*")
    @ResponseBody
    public ModelAndView getMediciones(@RequestParam(required=true, value="idPaciente") String pacienteId,
                                      HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/nutricionista/medicionesPaciente");
        Paciente paciente = pacienteManager.get(Long.valueOf(pacienteId));
        List<Medicion> mediciones = medicionManager.getMedicionesByIdPaciente(paciente.getId());
        mv.addObject("medicionesList", mediciones);
        mv.addObject("namePaciente", paciente.getPersona().getFullName());
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nutricionista/pesosPaciente*")
    @ResponseBody
    public ModelAndView getPesos(@RequestParam(required = true, value="idPaciente") String pacienteId,
                                 HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/nutricionista/pesosPaciente");
        Paciente paciente = pacienteManager.get(Long.valueOf(pacienteId));
        List<Peso> pesos = pesoManager.getPesosByIdPaciente(paciente.getId());
        mv.addObject("pesosList", pesos);
        mv.addObject("namePaciente", paciente.getPersona().getFullName());
        return mv;
    }
}
