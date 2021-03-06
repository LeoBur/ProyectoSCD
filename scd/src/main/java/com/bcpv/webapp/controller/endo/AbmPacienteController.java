package com.bcpv.webapp.controller.endo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.Constants;
import com.bcpv.model.*;
import com.bcpv.service.*;
import com.bcpv.service.impl.EspecialistaManagerImpl;
import com.bcpv.webapp.controller.forms.PacienteForm;
import org.apache.commons.lang3.StringUtils;
import org.displaytag.decorator.CheckboxTableDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.webapp.controller.BaseFormController;

@Controller
public class AbmPacienteController extends BaseFormController {

    @Autowired
    PersonaManager personaManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    EndocrinologoManager endocrinologoManager;

    @Autowired
    ProvinciaManager provinciaManager;

    @Autowired
    LocalidadManager localidadManager;

    @Autowired
    TipoDiabetesManager tipoDiabetesManager;

    @Autowired
    RoleManager roleManager;

    @Autowired
    PacienteEnTratamientoManager pacienteEnTratamientoManager;

    @Autowired
    EspecialistaManager especialistaManager;

    public AbmPacienteController(){

    }

    @RequestMapping(value = "endos/newPaciente*", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors,
                                 final HttpServletRequest request,
                                 @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/newPaciente");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        List<String> tipoDiabetesList = tipoDiabetesManager.getTipoDiabetes();
        if (null == search && request.getAttribute("pacienteForm") == null) {
            PacienteForm paciente = new PacienteForm();
            paciente.setEnabled(true);
            mv.addObject("pacienteForm", paciente);
        } else {
            boolean b = false;
            buscar(mv, pacienteForm, request, localidades, locale, b);
        }
        mv.addObject("provinciaList", provincias);
        mv.addObject("localidadList", localidades);
        mv.addObject("tipoDiabetesList", tipoDiabetesList);
        return mv;
    }

    @RequestMapping(value = "endos/pacienteList*", method = RequestMethod.GET)
    public ModelAndView showPacientes(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors,
                                           final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/pacienteList");
        List<PacienteEnTratamiento> pacientes = endocrinologoManager.getEndocrinologoByPersona(personaManager.getPersonaByUsername(request.getRemoteUser())).getPacientes();
        List<PacienteEnTratamiento> pacientesFilter = new ArrayList<PacienteEnTratamiento>();

        if (search == null) {
            mv.addObject("pacienteList", pacientes);
        } else {
            for (PacienteEnTratamiento pacientefilter : pacientes) {
                if (pacientefilter.getPaciente().getPersona().getDni().startsWith(pacienteForm.getDni()) || (pacientefilter.getPaciente().getPersona().getLastName().startsWith(pacienteForm.getDni().toUpperCase()))) {
                    pacientesFilter.add(pacientefilter);
                }
            }
            if (pacientesFilter.size() == 0) {
                mv.addObject("pacienteList", pacientes);
                saveInfo(request, "No tiene pacientes");
            } else {
                mv.addObject("pacienteList", pacientesFilter);
            }
        }
        return mv;
    }

    @RequestMapping(value = "endos/pacienteList/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Paciente paciente : pacienteManager.getPacientes()) {
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

    @RequestMapping(value = "endos/getDNITags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getDNITags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Paciente paciente : pacienteManager.getPacientes()) {
            data.add(new Tag(cont++, paciente.getPersona().getDni()));
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

    @RequestMapping(value = "endos/newPaciente*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pacienteForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/pacienteList";
            }
        }

        boolean isNew = (null == pacienteManager.getPacienteByUsername(pacienteForm.getEmail()));
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:pacienteList";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(pacienteForm.getDni());

        persona.setDni(pacienteForm.getDni());
        persona.setFirstName(pacienteForm.getFirstName());
        persona.setLastName(pacienteForm.getLastName());
        persona.setEmail(pacienteForm.getEmail());
        persona.setPhoneNumber(pacienteForm.getPhoneNumber());
        persona.setSexo(pacienteForm.getSexo());
        persona.setUsername(pacienteForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(pacienteForm.isEnabled());
        persona.addRole(roleManager.getRole(Constants.USER_ROLE));

        persona.setFch_nac(pacienteForm.getDia());
        persona.setDomicilio(createDomicilio(pacienteForm));
        Endocrinologo endo = endocrinologoManager.getEndocrinologoByPersona(personaManager.getPersonaByUsername(request.getRemoteUser()));
        TipoDiabetes tipoDiabetes = null;
        try {
            tipoDiabetes = tipoDiabetesManager.getTipoDiabetesByName(pacienteForm.getTipoDiabetes());
        } catch (EntityNotFoundException e) {
            isNew = false;
        }
        Boolean isPerson = false;
        Paciente paciente = null;
        if (isNew) {
            paciente = new Paciente(tipoDiabetes, pacienteForm.getLimiteInferior(), pacienteForm.getLimiteSuperior(), persona);
            persona.setPassword(pacienteForm.getDni());
            persona.setConfirmPassword(pacienteForm.getDni());
        } else {
            try {
                paciente = pacienteManager.loadPacienteByDNI(persona);
                paciente.setLimiteInf(pacienteForm.getLimiteInferior());
                paciente.setLimiteSup(pacienteForm.getLimiteSuperior());
                paciente.setTipo(tipoDiabetes);
            } catch (EntityNotFoundException e) {
                isPerson = true;
                saveInfo(request, "La persona se habilito, ingrese para asignarla como paciente");
                log.debug("No existe paciente", e);
            }
        }

        if (request.getParameter("delete") != null) {
            paciente = pacienteManager.loadPacienteByDNI(persona);
            if (paciente.getMediciones().isEmpty() && paciente.getDietas().isEmpty()
                    && paciente.getTratamientos().isEmpty() && paciente.getPesos().isEmpty()
                    && paciente.getRegistroComidas().isEmpty()){
                pacienteManager.remove(paciente);
                personaManager.savePersona(persona);
                log.debug("Se eliminó al paciente");
                saveMessage(request, getText("user.endocrinologist.pacient.deleted", locale));
            } else {
                saveMessage(request, getText("user.endocrinologist.pacient.not.deleted", locale));
            }

        } else {
            try{
                personaManager.savePersona(persona);
                if (null != paciente) {
                    paciente = pacienteManager.savePaciente(paciente);
                }
            } catch (EntityExistsException e) {
                log.warn(e.getMessage());
            }
            if (isNew) {
                PacienteEnTratamiento pacienteEnTratamiento = new PacienteEnTratamiento(paciente,endo);
                pacienteEnTratamientoManager.savePacienteEnTratamiento(pacienteEnTratamiento);
                endo.addPacienteEnTratamiento(pacienteEnTratamiento);
                endocrinologoManager.saveEndocrinologo(endo);
                saveMessage(request, getText("user.endocrinologist.pacientSaved", locale));
            } else if (!isPerson){
                saveMessage(request, getText("user.endocrinologist.pacientUpdated", locale));
            }
        }
        return success;
    }

    private void buscar(ModelAndView mv, PacienteForm pacienteForm, HttpServletRequest request,
                        List<Localidad> localidades, Locale locale, boolean b) {
        final String dni = pacienteForm.getDni();
        try {
            if (StringUtils.isEmpty(dni)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByDni(pacienteForm.getDni());
            Paciente paciente = null;
            if (persona.getId() != null) {
                pacienteForm.setNuevaPersona(false);
                pacienteForm.setId(persona.getId());
                pacienteForm.setDni(dni);
                pacienteForm.setUsername(persona.getUsername());
                pacienteForm.setFirstName(persona.getFirstName());
                pacienteForm.setLastName(persona.getLastName());
                pacienteForm.setEmail(persona.getEmail());
                pacienteForm.setUsername(persona.getUsername());
                pacienteForm.setPhoneNumber(persona.getPhoneNumber());
                pacienteForm.setDia(persona.getFch_nac());
                pacienteForm.setSexo(persona.getSexo());
                pacienteForm.setDomicilio(persona.getDomicilio());
                pacienteForm.setEnabled(persona.isEnabled());
                pacienteForm.setEnableFields(persona.isEnabled());
                if (b == false) {
                    paciente = pacienteManager.getPacienteByUsername(persona.getUsername());
                }
                if (paciente != null) {
                    pacienteForm.setLimiteInferior(paciente.getLimiteInf());
                    pacienteForm.setLimiteSuperior(paciente.getLimiteSup());
                    pacienteForm.setTipoDiabetes(paciente.getTipo().getTipoDiab());
                } else {
                    throw new EntityNotFoundException("Paciente no existe");
                }
                mv.addObject("pacienteForm", pacienteForm);
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(pacienteForm.getProvincia())) {
                        filtradas.add(localidad);
                    }
                }
                mv.addObject("localidadList", filtradas);
            } else throw new EntityNotFoundException("La persona no existe");

        } catch (NullPointerException npe){
            saveInfo(request, getText("user.superUser.info.dni", locale));
        } catch (EntityNotFoundException enfe) {
            if (enfe.getMessage().equals("La persona no existe")) {
                saveInfo(request, getText("user.superUser.info.nuevaPersona", locale));
            } else if (enfe.getMessage().equals("Paciente no existe"))
            saveInfo(request, getText("user.superUser.info.nuevoPaciente", locale));
        }
    }

    private Domicilio createDomicilio(PacienteForm pacienteForm) {
        Domicilio domicilio = new Domicilio();
        domicilio.setLocalidad(localidadManager.getLocalidadByNombreYProvincia(pacienteForm.getLocalidad(), pacienteForm.getProvincia()).get(0));
        domicilio.setPiso(pacienteForm.getPiso());
        domicilio.setDpto(pacienteForm.getDpto());
        domicilio.setCalle(pacienteForm.getCalle());
        domicilio.setNumero(new Long(pacienteForm.getNumero()));
        return domicilio;
    }

    @RequestMapping(value = "endos/especialistaListPaciente*", method = RequestMethod.GET)
    public ModelAndView showEspecialistasDePaciente(@ModelAttribute PacienteEnTratamiento pacienteEnTratamiento, BindingResult errors,
                                                    final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/especialistaListPaciente");
        Locale locale = request.getLocale();
        try{
            pacienteEnTratamiento = pacienteEnTratamientoManager.getPacienteEnTratamiento(search);
        }
        catch (EntityNotFoundException enfe) {
            saveInfo(request, getText("user.endocrinologist.pacienteEnTratamientoNotFound", locale));
            return mv;
        }

        if (pacienteEnTratamiento.getEspecialista() == null){
            saveInfo(request, getText("user.endocrinologist.specialistsNotAssociated", locale));
        } else {
            mv.addObject("especialistaPaciente",pacienteEnTratamiento.getEspecialista());
        }
        mv.addObject("pacienteEnTratamiento",pacienteEnTratamiento);
        mv.addObject("pacienteFullName",pacienteEnTratamiento.getPaciente().getPersona().getFullName());
        mv.addObject("idPacienteTratamiento", pacienteEnTratamiento.getIdPacienteEnTratamiento());
        return mv;
    }

    @RequestMapping(value = "endos/asignarEspecialista*", method = RequestMethod.GET)
    public ModelAndView asignarEspecialista(@ModelAttribute PacienteEnTratamiento pacienteEnTratamiento, BindingResult errors,
                           HttpServletRequest request, HttpServletResponse response,@RequestParam(required=false, value="idPacienteTratamiento") String idPacienteTratamiento ){
        ModelAndView mv = new ModelAndView("endos/asignarEspecialista");
        pacienteEnTratamiento = pacienteEnTratamientoManager.getPacienteEnTratamiento(new Long(idPacienteTratamiento));
        List<Especialista> especialistas = especialistaManager.getEspecialistasActivos();
        mv.addObject("especialistasActivos", especialistas);
        mv.addObject("idPacienteTratamiento", pacienteEnTratamiento.getIdPacienteEnTratamiento());
        mv.addObject("pacienteEnTratamiento", pacienteEnTratamiento);
        //String success = "redirect:especialistaListPaciente";
        //return success ;
        return mv;
    }

    @RequestMapping(value = "endos/asignarUnEspecialistaAPaciente*",method = {RequestMethod.GET, RequestMethod.POST})
    public String asignarUnEspecialista(@ModelAttribute PacienteEnTratamiento pacienteEnTratamiento, BindingResult errors,
                                            HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam(required=false, value="idPacienteTratamiento") String idPacienteTratamiento,
                                            @RequestParam(required=false, value="idEspecialista") String idEspecialista){
        ModelAndView mv = new ModelAndView("endos/pacienteList");
        Locale locale = request.getLocale();
        pacienteEnTratamiento = pacienteEnTratamientoManager.getPacienteEnTratamiento(new Long(idPacienteTratamiento));
        Especialista especialista = especialistaManager.getEspecialista(new Long(idEspecialista));
        pacienteEnTratamiento.setEspecialista(especialista);
        especialista.addPacienteEnTratamiento(pacienteEnTratamiento);
        especialistaManager.saveEspecialista(especialista);
        pacienteEnTratamientoManager.savePacienteEnTratamiento(pacienteEnTratamiento);
        saveInfo(request, getText("user.endocrinologist.specialistAssigned", locale));
        mv.addObject("especialistaPaciente", pacienteEnTratamiento.getEspecialista());
        mv.addObject("pacienteEnTratamiento",pacienteEnTratamiento);
        mv.addObject("idPacienteTratamiento", pacienteEnTratamiento.getIdPacienteEnTratamiento());
        return "redirect:pacienteList";
    }

    @RequestMapping(value = "endos/desvincularEspecialista*", method = {RequestMethod.GET, RequestMethod.POST})
    public String desvincularEspecialista(@ModelAttribute("pacienteForm") PacienteEnTratamiento pacienteEnTratamiento, BindingResult errors,
                                      HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(required=false, value="idPacienteTratamiento") String idPacienteTratamiento,
                                      @RequestParam(required=false, value="idEspecialista") String idEspecialista){
        Locale locale = request.getLocale();
        pacienteEnTratamiento = pacienteEnTratamientoManager.getPacienteEnTratamiento(new Long(idPacienteTratamiento));
        Especialista especialista = especialistaManager.getEspecialista(new Long(idEspecialista));
        pacienteEnTratamiento.setEspecialista(null);
        especialista.removePacienteEnTratamiento(new Long(idPacienteTratamiento));
        especialistaManager.saveEspecialista(especialista);
        pacienteEnTratamientoManager.savePacienteEnTratamiento(pacienteEnTratamiento);
        saveInfo(request, getText("user.endocrinologist.specialistRemoved", locale));
        return "redirect:pacienteList";
    }

    @RequestMapping(value = "endos/asignarEspecialista/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getEspecialistaTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Especialista especialista : especialistaManager.getEspecialistas()) {
            data.add(new Tag(cont++, especialista.getPersona().getLastName()));
            dataFilter.add(new Tag(cont++, especialista.getPersona().getDni()));
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
