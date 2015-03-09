package com.bcpv.webapp.controller.nutricionista;

import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Paciente;
import com.bcpv.model.PacienteEnTratamiento;
import com.bcpv.model.Tag;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;
import com.bcpv.webapp.controller.forms.PacienteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Romeo-Charlie Team <lan-linea-romeo-charlie@globant.com>
 */
@Controller
public class PacienteList extends BaseFormController{

    @Autowired
    EspecialistaManager especialistaManager;

    @Autowired
    PersonaManager personaManager;

    @Autowired
    PacienteManager pacienteManager;

    @RequestMapping(value = "nutricionista/pacienteList*", method = RequestMethod.GET)
    public ModelAndView showEndocrinologos(@ModelAttribute("endocrinologoForm") PacienteForm pacienteForm, BindingResult errors,
                                           final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("nutricionista/pacienteList");
        Set<Paciente> pacientes = new HashSet<>(0);
        for (PacienteEnTratamiento pacienteEnTratamiento : especialistaManager.getEspecialistaByPersona(
                personaManager.getPersonaByUsername(request.getRemoteUser())).getPacientes()) {
            pacientes.add(pacienteEnTratamiento.getPaciente());
        }
        List<Paciente> endocrinologosFilter = new ArrayList<Paciente>();

        if (search == null) {
            mv.addObject("endocrinologoList", pacientes);
            return mv;
        } else {
            for (Paciente endocrinologofilter : pacientes) {
                if (endocrinologofilter.getPersona().getDni().startsWith(pacienteForm.getDni()) || (endocrinologofilter.getPersona().getLastName().startsWith(pacienteForm.getDni().toUpperCase()))) {
                    endocrinologosFilter.add(endocrinologofilter);
                }
            }
            if (endocrinologosFilter.size() == 0) {
                mv.addObject("endocrinologoList", pacientes);
                saveInfo(request, "No existe el Paciente");
                return mv;
            } else {
                mv.addObject("endocrinologoList", endocrinologosFilter);
                return mv;
            }
        }
    }

    @RequestMapping(value = "nutricionista/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName, HttpServletRequest request) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();
        Set<Paciente> pacientes = new HashSet<>(0);
        for (PacienteEnTratamiento pacienteEnTratamiento : especialistaManager.getEspecialistaByPersona(
                personaManager.getPersonaByUsername(request.getRemoteUser())).getPacientes()) {
            pacientes.add(pacienteEnTratamiento.getPaciente());
        }

        for (Paciente paciente : pacientes) {
            data.add(new Tag(cont++, paciente.getPersona().getLastName()));
            dataFilter.add(new Tag(cont++, paciente.getPersona().getDni()));
            //data.add(new Tag(cont++, endocrinologo.getPersona().getDni()));
        }

        HashSet set = new HashSet<Tag>();
        for (Tag filter : data) {
            if (set.add(filter.getTagName())) {
                //set.add(filter.getTagName());
                dataFilter.add(new Tag(cont++, filter.getTagName()));
            }
        }

        // iterate a list and filter by tagName
        for (Tag tag : dataFilter) {
            if (tag.getTagName().toLowerCase()
                    .startsWith(tagName.toLowerCase())) {
                result.add(tag);
            }
        }
        return result;
    }
}
