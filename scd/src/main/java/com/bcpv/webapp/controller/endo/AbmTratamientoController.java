package com.bcpv.webapp.controller.endo;

import com.bcpv.Constants;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Paciente;
import com.bcpv.model.Prescripcion;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.PrescripcionManager;
import com.bcpv.service.TratamientoManager;
import com.bcpv.webapp.controller.forms.TratamientoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class AbmTratamientoController extends BaseFormController{

    @Autowired
    TratamientoManager tratamientoManager;

    @Autowired
    PrescripcionManager prescripcionManager;

    @Autowired
    MedicamentoManager medicamentoManager;

    @Autowired
    EndocrinologoManager endocrinologoManager;

    @Autowired
    PersonaManager personaManager;

    @Autowired
    PacienteManager pacienteManager;

    public AbmTratamientoController(){
    }

    @RequestMapping(value = "endos/tratamiento*", method = RequestMethod.GET)
    public ModelAndView showForm(HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/tratamiento");
        Locale locale = request.getLocale();
        List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
        Endocrinologo endocrinologo = endocrinologoManager.getEndocrinologoByPersona(
                personaManager.getPersonaByUsername(request.getRemoteUser()));
        /*List<Paciente> pacientes = new LinkedList<>();
        for (PacienteEnTratamiento pacienteEnTratamiento : endocrinologo.getPacientes()) {
            pacientes.add(pacienteEnTratamiento.getPaciente());
        }*/
        List<Paciente> pacientes = pacienteManager.getPacientes();
        TratamientoForm tratamiento = new TratamientoForm();
        tratamiento.setPaciente(search);
        mv.addObject("medicamentoList", medicamentos);
        mv.addObject("tratamientoForm", tratamiento);
        return mv;
    }

    @Transactional
    @RequestMapping(value = "endos/tratamiento*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("tratamientoForm") TratamientoForm tratamientoForm, BindingResult errors,
                           final HttpServletRequest request) {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(tratamientoForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/endos/tratamiento";
            }
        }

        log.debug("entering 'onSubmit' method...");

        String success = "redirect:/endos/pacienteList";
        Locale locale = request.getLocale();

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setFechaTratamiento(tratamientoForm.getFecha());
        /*tratamiento.setPaciente(pacienteManager.getPacienteByUsername(
                personaManager.getPersonaByDni(tratamientoForm.getPaciente()).getUsername()));*/
        tratamiento.setEndocrinologo(endocrinologoManager.getEndocrinologoByPersona(
                personaManager.getPersonaByUsername(request.getRemoteUser())));
        tratamiento.setPrescripciones(setPrescripciones(tratamientoForm, tratamiento));
        Paciente paciente = pacienteManager.getPacienteByUsername(
                personaManager.getPersonaByDni(tratamientoForm.getPaciente()).getUsername());
        //paciente.getTratamientos().add(tratamiento);
        //saveMessage(request, getText("user.savedData", locale));
        try{
            tratamiento.setPaciente(paciente);
            tratamientoManager.saveTratamiento(tratamiento);
            //pacienteManager.savePaciente(paciente);
        } catch (EntityExistsException e) {
            saveError(request, getText("user.endocrinologist.treatmentFail", locale));
            return success;
        }
        saveMessage(request, getText("user.endocrinologist.savedTreatment", locale));

        return success;
    }

    @RequestMapping(value = "endos/tratamientoList*", method = RequestMethod.GET)
    public ModelAndView showTratamientos(final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        Paciente paciente = pacienteManager.getPaciente(new Long(search));
        Set<Tratamiento> tratamientos = paciente.getTratamientos();
        if (tratamientos.isEmpty()) {
            ModelAndView mv = new ModelAndView("endos/pacienteList");
            saveInfo(request, "El paciente no tiene tratamientos");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("endos/tratamientoList");
            mv.addObject("tratamientoList", tratamientos);
            mv.addObject("dni", paciente.getPersona().getDni());
            mv.addObject("paciente", paciente.getPersona().getFullName());
            return mv;
        }
    }

    @RequestMapping(value = "endos/prescripciones*", method = RequestMethod.GET)
    public ModelAndView showPrescripciones(final HttpServletRequest request, @RequestParam(required=true, value="id") String search) {
        Set<Prescripcion> prescripciones = tratamientoManager.getTratamiento(new Long(search)).getPrescripciones();
        if (prescripciones.isEmpty()) {
            ModelAndView mv = new ModelAndView("endos/pacienteList");
            saveInfo(request, "No existen prescripciones");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("endos/prescripciones");
            mv.addObject("prescripcionesList", prescripciones);
            mv.addObject("idTratamiento", tratamientoManager.getTratamiento(new Long(search)).getPaciente().getId());
            return mv;
        }
    }

    private Set<Prescripcion> setPrescripciones(TratamientoForm tratamientoForm, Tratamiento tratamiento) {
        Set<Prescripcion> prescripciones = new HashSet<>(0);
        if (!Constants.NONE.equals(tratamientoForm.getMedicamento1())) {
            prescripciones.add(createPrescripcion(tratamientoForm.getMedicamento1(), tratamientoForm.getObsPrescripcion1(), tratamiento));
        }
        if (!Constants.NONE.equals(tratamientoForm.getMedicamento2())) {
            prescripciones.add(createPrescripcion(tratamientoForm.getMedicamento2(), tratamientoForm.getObsPrescripcion2(), tratamiento));
        }
        return prescripciones;
    }
    private Prescripcion createPrescripcion(String medicamento, String observacion, Tratamiento tratamiento) {
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setDescripcion(observacion);
        prescripcion.setMedicamento(medicamentoManager.getByNombreComercial(medicamento));
        prescripcion.setTratamiento(tratamiento);
        return prescripcion;
    }
}
