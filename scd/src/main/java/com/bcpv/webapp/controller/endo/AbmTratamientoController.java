package com.bcpv.webapp.controller.endo;

import com.bcpv.Constants;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Paciente;
import com.bcpv.model.PacienteEnTratamiento;
import com.bcpv.model.Prescripcion;
import com.bcpv.model.Tratamiento;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.PrescripcionManager;
import com.bcpv.service.TratamientoManager;
import com.bcpv.webapp.controller.forms.TratamientoForm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    public ModelAndView showForm(HttpServletRequest request,
                                 @RequestParam(required=false, value="search") String search) {
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
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setPaciente(pacienteManager.loadPacienteByDNI(
                personaManager.getPersonaByDni(search)));
        tratamiento.setEndocrinologo(endocrinologo);
        String options = "";
        for (Medicamento med : medicamentos) {
            options = options + "<option value=\""+med.getNombreComercial()+"\">"+med.getNombreComercial()+"</option>";
        }

        /*List<String> nombres = new ArrayList<>();
        for (Medicamento med : medicamentos) {
            nombres.add(med.getNombreComercial());
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(nombres);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("jsonMeds", json);*/
        tratamiento.getPrescripciones().add(new Prescripcion());
        mv.addObject("options", options);
        mv.addObject("medicamentoList", medicamentos);
        mv.addObject("tratamientoForm", tratamiento);
        return mv;
    }

    @Transactional
    @RequestMapping(value = "endos/tratamiento*", method = RequestMethod.POST)
    public String onSubmit(@RequestParam MultiValueMap<String, String> params,
                           final HttpServletRequest request) {
        if (request.getParameter("cancel") != null) {
            return "redirect:/endos/tratamientoList?search="+pacienteManager.loadPacienteByDNI(
                    personaManager.getPersonaByDni(params.get("paciente.persona.dni").get(0))).getId();
        }

        BindingResult errors = new BeanPropertyBindingResult(params, "params");
        if (validator != null) { // validator is null during testing
            validator.validate(params, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/endos/tratamiento";
            }
        }

        log.debug("entering 'onSubmit' method...");

        String success = "redirect:/endos/pacienteList";
        Locale locale = request.getLocale();
        Tratamiento tratamiento = new Tratamiento();
        DateFormat format = new SimpleDateFormat("DD/MM/yyyy", locale);
        try {
            tratamiento.setFechaTratamiento(format.parse( params.get("fechaTratamiento").get(0)));
        } catch (ParseException e) {
            saveError(request, "Error en la fecha");
            return success;
        }
        tratamiento.setPaciente(pacienteManager.getPacienteByUsername(
                personaManager.getPersonaByDni(params.get("paciente.persona.dni").get(0)).getUsername()));
        tratamiento.setEndocrinologo(endocrinologoManager.getEndocrinologoByPersona(
                personaManager.getPersonaByUsername(request.getRemoteUser())));

        //Para agregar la primer prescripcion
        if (params.containsKey("prescripciones[0].medicamento.nombreComercial")) {
            Prescripcion prescripcion = new Prescripcion();
            prescripcion.setMedicamento(medicamentoManager.getByNombreComercial(params.get("prescripciones[0].medicamento.nombreComercial").get(0)));
            prescripcion.setDescripcion(params.get("prescripciones[0].descripcion").get(0));
            tratamiento.getPrescripciones().add(prescripcion);
        }

        if (params.containsKey("prescripciones[].medicamento.nombreComercial")) {
            List<String> nombres = params.get("prescripciones[].medicamento.nombreComercial");
            List<String> descs = params.get("prescripciones[].descripcion");
            int i = nombres.size();
            for (int index = 0; index < i; index++) {
                Prescripcion presc = new Prescripcion();
                presc.setMedicamento(medicamentoManager.getByNombreComercial(nombres.get(index)));
                presc.setDescripcion(descs.get(index));
                tratamiento.getPrescripciones().add(presc);
            }
        }
        try{
            tratamientoManager.saveTratamiento(tratamiento);
        } catch (EntityExistsException e) {
            saveError(request, getText("user.endocrinologist.treatmentFail", locale));
            return success;
        }
        saveMessage(request, getText("user.endocrinologist.savedTreatment", locale));

        return success;
    }

    @RequestMapping(value = "endos/editTratamiento*", method = RequestMethod.GET)
    public ModelAndView editForm(HttpServletRequest request,
                                 @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/editTratamiento");
        Locale locale = request.getLocale();
        List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
        Tratamiento tratamiento = tratamientoManager.getTratamiento(new Long(search));
        List<Paciente> pacientes = pacienteManager.getPacientes();
        String options = "";
        for (Medicamento med : medicamentos) {
            options = options + "<option value=\""+med.getNombreComercial()+"\">"+med.getNombreComercial()+"</option>";
        }
        mv.addObject("medicamentoList", medicamentos);
        mv.addObject("tratamientoForm", tratamiento);
        mv.addObject("options", options);
        return mv;
    }

    @Transactional
    @RequestMapping(value = "endos/editTratamiento*", method = RequestMethod.POST)
    public String editTratamiento(@RequestParam MultiValueMap<String, String> params,
                                  final HttpServletRequest request) {
        if (request.getParameter("cancel") != null) {
            return "redirect:/endos/tratamientoList?search="+pacienteManager.loadPacienteByDNI(
                    personaManager.getPersonaByDni(params.get("dni").get(0))).getId();
        }

        log.debug("entering 'editTratamiento' method...");

        String success = "redirect:/endos/tratamientoList?search="+pacienteManager.loadPacienteByDNI(
                            personaManager.getPersonaByDni(params.get("dni").get(0))).getId();
        Locale locale = request.getLocale();

        Tratamiento tratamiento = tratamientoManager.getTratamiento(new Long(params.get("idTratamiento").get(0)));

        List<Prescripcion> prescripcions = new ArrayList<>();
        prescripcions.addAll(tratamiento.getPrescripciones());
        int i = prescripcions.size();
        for (int index=0; index<i; index++){
            prescripcions.get(index).setDescripcion(params.get("prescripciones["+index+"].descripcion").get(0));
            prescripcions.get(index).setMedicamento(medicamentoManager.getByNombreComercial(
                    params.get("prescripciones["+index+"].medicamento.nombreComercial").get(0)));
        }
        if (params.containsKey("prescripciones[].medicamento.nombreComercial")) {
            List<String> nombres = params.get("prescripciones[].medicamento.nombreComercial");
            List<String> descs = params.get("prescripciones[].descripcion");
            i = nombres.size();
            for(int index=0; index<i; index++) {
                Prescripcion presc = new Prescripcion();
                presc.setMedicamento(medicamentoManager.getByNombreComercial(nombres.get(index)));
                presc.setDescripcion(descs.get(index));
                tratamiento.getPrescripciones().add(presc);
            }
        }
        try {
            tratamientoManager.saveTratamiento(tratamiento);
        } catch (Exception e) {
            saveError(request, getText("user.endocrinologist.treatmentFail", locale));
            return success;
        }
        saveMessage(request, getText("user.endocrinologist.updatedTreatment", locale));

        return success;
    }

    @RequestMapping(value = "endos/tratamientoList*", method = RequestMethod.GET)
    public ModelAndView showTratamientos(final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        Paciente paciente = pacienteManager.getPaciente(new Long(search));
        Set<Tratamiento> tratamientos = paciente.getTratamientos();
        if (tratamientos.isEmpty()) {
            Set<PacienteEnTratamiento> pacientes = endocrinologoManager.getEndocrinologoByPersona(
                    personaManager.getPersonaByUsername(request.getRemoteUser())).getPacientes();
            List<Paciente> pacienteList = new ArrayList<>();
            for (PacienteEnTratamiento pac : pacientes) {
                pacienteList.add(pac.getPaciente());
            }
            ModelAndView mv = new ModelAndView("endos/pacienteList");
            mv.addObject("pacienteList", pacienteList);
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
