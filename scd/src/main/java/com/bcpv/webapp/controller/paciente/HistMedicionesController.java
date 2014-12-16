package com.bcpv.webapp.controller.paciente;

import java.util.List;
import java.util.Set;

import com.bcpv.model.Peso;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PesoManager;
import com.bcpv.webapp.controller.UtilsController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class HistMedicionesController extends BaseFormController{
	
	@Autowired
	MedicionManager medicionManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    PesoManager pesoManager;
	
	public HistMedicionesController(){
		
	}
	
	/*@RequestMapping(value = "/paciente/historial/mediciones",method = RequestMethod.GET)
	public ModelAndView showHistorialMediciones(){
		ModelAndView mav = new ModelAndView("paciente/historial/mediciones");
		Paciente paciente = (Paciente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Medicion> mediciones = medicionManager.getMedicionesByIdPaciente(paciente.getId().toString());
        mav.addObject("medicionList", mediciones);
        return mav;
	}*/

    @RequestMapping(method = RequestMethod.GET, value = "/paciente/histMediciones*")
    @ResponseBody
    public ModelAndView getMediciones(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paciente/histMediciones");
        String pacienteId = pacienteManager.getPacienteByUsername(request.getRemoteUser()).getId().toString();
        String data = datos(pacienteId);
        mv.addObject("data", data);
        return mv;
    }

    public String datos (String pacienteId) {
        List<Medicion> mediciones = medicionManager.getMedicionesByIdPaciente(pacienteId);
        JSONArray ja = new JSONArray();
        int i = 0;
        for (Medicion medicion : mediciones) {
            JSONObject j = new JSONObject();
            j.put(medicion.getF_medicion().toString(), medicion.getValor());
            ja.add(i, j);
            i++;
        }
        return ja.toString();
    }


    /*List<Localidad> localidades = localidadManager.getLocalidades();
    JSONArray ja = new JSONArray();
    int i = 0;
    for (Localidad localidad : localidades) {
        JSONObject j = new JSONObject();
        if (provincia.equals(localidad.getProvincia().getNombre())) {
            j.put("optionValue", localidad.getNombre());
            j.put("optionDisplay", localidad.getNombre());
            ja.add(i, j);
            i++;
        }
    }
    return ja.toString();*/
}
