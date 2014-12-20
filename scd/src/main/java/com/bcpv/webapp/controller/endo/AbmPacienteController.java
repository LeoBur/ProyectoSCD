package com.bcpv.webapp.controller.endo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.Constants;
import com.bcpv.model.Paciente;
import com.bcpv.model.TipoDiabetes;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.RoleManager;
import com.bcpv.service.TipoDiabetesManager;
import com.bcpv.webapp.controller.forms.PacienteForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Domicilio;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Localidad;
import com.bcpv.model.Persona;
import com.bcpv.model.Provincia;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.ProvinciaManager;
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

    public AbmPacienteController(){

    }

    private void buscar(ModelAndView mv, PacienteForm pacienteForm, HttpServletRequest request, List<Localidad> localidades, Locale locale) {
        final String dni = pacienteForm.getDni();
        try {
            if (StringUtils.isEmpty(dni)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByDni(pacienteForm.getDni());
            if (persona.getId() != null) {
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
                Paciente paciente = pacienteManager.getPacienteByUsername(persona.getUsername());
                if (paciente != null) {
                    pacienteForm.setLimiteInferior(paciente.getLimiteInf());
                    pacienteForm.setLimiteSuperior(paciente.getLimiteSup());
                    pacienteForm.setTipoDiabetes(paciente.getTipo().getTipoDiab());
                }
                mv.addObject("pacienteForm", pacienteForm);
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(pacienteForm.getProvincia())) {
                        filtradas.add(localidad);
                    }
                }
                mv.addObject("localidadList", filtradas);
            } else throw new EntityNotFoundException();

        } catch (NullPointerException npe){
            saveInfo(request, getText("user.superUser.info.dni", locale));
        } catch (EntityNotFoundException enfe) {
            saveInfo(request, getText("user.superUser.info.nuevaPersona", locale));
        }
    }

    @RequestMapping(value = "endos/newPaciente*", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors,
                                 final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/newPaciente");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        List<String> tipoDiabetesList = tipoDiabetesManager.getTipoDiabetes();
        if (null == search && request.getAttribute("pacienteForm") == null) {
            PacienteForm paciente = new PacienteForm();
            mv.addObject("localidadList", localidades);
            mv.addObject("pacienteForm", paciente);
        } else {
            buscar(mv, pacienteForm, request, localidades, locale);
        }
        mv.addObject("provinciaList", provincias);
        mv.addObject("tipoDiabetesList", tipoDiabetesList);
        return mv;
    }

    @RequestMapping(value = "endos/pacienteList*", method = RequestMethod.GET)
    public ModelAndView showPacientes() {
        ModelAndView mv = new ModelAndView("endos/pacienteList");
        List<Paciente> pacientes = pacienteManager.getPacientes();
        mv.addObject("pacienteList", pacientes);
        return mv;
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
                return "/newPaciente";
            }
        }

        boolean isNew = (pacienteForm.getId() == null);
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:newPaciente";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(pacienteForm.getDni());

        persona.setDni(pacienteForm.getDni());
        persona.setFirstName(pacienteForm.getFirstName());
        persona.setLastName(pacienteForm.getLastName());
        persona.setPassword(pacienteForm.getDni());
        persona.setConfirmPassword(pacienteForm.getDni());
        persona.setEmail(pacienteForm.getEmail());
        persona.setPhoneNumber(pacienteForm.getPhoneNumber());
        persona.setSexo(pacienteForm.getSexo());
        persona.setUsername(pacienteForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(pacienteForm.isEnabled());
        persona.addRole(roleManager.getRole(Constants.USER_ROLE));
        /*Role role = roleManager.getRole(Constants.ENDO_ROLE);
        if (role == null) {
            role = new Role();
            role.setDescription("Endocrinologist role (can edit users)");
            role.setName(Constants.ENDO_ROLE);
            roleManager.saveRole(role);
        }
        persona.addRole(roleManager.getRole(Constants.ENDO_ROLE));*/

        persona.setFch_nac(pacienteForm.getDia());
        persona.setDomicilio(createDomicilio(pacienteForm));
        Endocrinologo endo = endocrinologoManager.getEndocrinologoByPersona(personaManager.getPersonaByUsername(request.getRemoteUser()));
        TipoDiabetes tipoDiabetes = tipoDiabetesManager.getTipoDiabetesByName(pacienteForm.getTipoDiabetes());
        Paciente paciente = new Paciente(tipoDiabetes, endo, pacienteForm.getLimiteInferior(), pacienteForm.getLimiteSuperior(), persona);

        //saveMessage(request, getText("user.savedData", locale));

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
                pacienteManager.savePaciente(paciente);
            } catch (EntityExistsException e) {
                if (!isNew) {
                    saveMessage(request, getText("user.endocrinologist.pacientUpdated", locale));
                }
            }
            if (isNew) {
                saveMessage(request, getText("user.endocrinologist.pacientSaved", locale));
            }
        }
        return success;
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

    /*private Date getFechaNac(PacienteForm pacienteForm) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = pacienteForm.getDia() + "/" + pacienteForm.getMes() + "/" + pacienteForm.getAnio();
        return formatter.parse(strDate);
    }*/
}
