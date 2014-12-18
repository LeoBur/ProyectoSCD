package com.bcpv.webapp.controller.endo;

import com.bcpv.model.Domicilio;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Localidad;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.Provincia;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.ProvinciaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;
import com.bcpv.webapp.controller.forms.PacienteForm;
import com.bcpv.webapp.controller.forms.PersonaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 28/10/2014.
 */
@Controller
public class EditProfileController extends BaseFormController{
    @Autowired
    PersonaManager personaManager;

    @Autowired
    EndocrinologoManager endocrinologoManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    ProvinciaManager provinciaManager;

    @Autowired
    LocalidadManager localidadManager;

    private EditProfileController() {
        this.setCancelView("endos/editProfile");
    }

    @RequestMapping(value = "endos/editProfile*", method = RequestMethod.GET)
    public ModelAndView showEndoForm(final HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("endos/editProfile");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        if (request.getRemoteUser() == null) {
            saveInfo(request, getText("user.superUser.info.dni", locale));
        } else {
            buscarEndo(mv, request, provincias, localidades, locale);
        }
        return mv;
    }

    @RequestMapping(value = "paciente/editProfile*", method = RequestMethod.GET)
    public ModelAndView showPacienteForm(final HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("endos/editProfile");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        if (request.getRemoteUser() == null) {
            saveInfo(request, getText("user.superUser.info.dni", locale));
        } else {
            buscarPaciente(mv, request, provincias, localidades, locale);
        }
        return mv;
    }

    private void buscarEndo(ModelAndView mv, HttpServletRequest request, List<Provincia> provincias,
                            List<Localidad> localidades, Locale locale) {
        try {
            if ((request.getRemoteUser() == null)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByUsername(request.getRemoteUser());
            EndocrinologoForm endocrinologoForm = new EndocrinologoForm();
            mv.addObject("provinciaList", provincias);
            if (persona.getId() != null) {
                endocrinologoForm.setId(persona.getId());
                endocrinologoForm.setDni(persona.getDni());
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setFirstName(persona.getFirstName());
                endocrinologoForm.setLastName(persona.getLastName());
                endocrinologoForm.setEmail(persona.getEmail());
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setPhoneNumber(persona.getPhoneNumber());
                endocrinologoForm.setDia(persona.getFch_nac());
                endocrinologoForm.setSexo(persona.getSexo());
                endocrinologoForm.setDomicilio(persona.getDomicilio());
                endocrinologoForm.setMatricula(getMatricula(persona));
                mv.addObject("endocrinologoForm", endocrinologoForm);
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(endocrinologoForm.getProvincia())) {
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

    private void buscarPaciente(ModelAndView mv, HttpServletRequest request, List<Provincia> provincias,
                            List<Localidad> localidades, Locale locale) {
        try {
            if ((request.getRemoteUser() == null)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByUsername(request.getRemoteUser());
            PacienteForm pacienteForm = new PacienteForm();
            mv.addObject("provinciaList", provincias);
            if (persona.getId() != null) {
                pacienteForm.setId(persona.getId());
                pacienteForm.setDni(persona.getDni());
                pacienteForm.setUsername(persona.getUsername());
                pacienteForm.setFirstName(persona.getFirstName());
                pacienteForm.setLastName(persona.getLastName());
                pacienteForm.setEmail(persona.getEmail());
                pacienteForm.setUsername(persona.getUsername());
                pacienteForm.setPhoneNumber(persona.getPhoneNumber());
                pacienteForm.setDia(persona.getFch_nac());
                pacienteForm.setSexo(persona.getSexo());
                pacienteForm.setDomicilio(persona.getDomicilio());
                setPacienteInfo(persona, pacienteForm);
                mv.addObject("endocrinologoForm", pacienteForm);
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

    /*private Date getFechaNac(PersonaForm endo) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = endo.getDia() + "/" + endo.getMes() + "/" + endo.getAnio();
        return formatter.parse(strDate);
    }*/

    private Long getMatricula (Persona persona) {
        try {
            return endocrinologoManager.getEndocrinologoByPersona(persona).getMatricula();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    private void setPacienteInfo (Persona persona, PacienteForm pacienteForm) {
        try {
            Paciente paciente = pacienteManager.loadPacienteByDNI(persona);
            pacienteForm.setLimiteInferior(paciente.getLimiteInf());
            pacienteForm.setLimiteSuperior(paciente.getLimiteSup());
            pacienteForm.setTipoDiabetes(paciente.getTipo().getTipoDiab());
        } catch (EntityNotFoundException e) {
            pacienteForm.setLimiteInferior(0);
            pacienteForm.setLimiteSuperior(0);
            pacienteForm.setTipoDiabetes(null);
        }
    }

    @RequestMapping(value = "endos/editProfile*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm, BindingResult errors,
                           HttpServletRequest request)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(endocrinologoForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/editProfile";
            }
        }

        boolean isNew = (endocrinologoForm.getId() == null);
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:/home";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(endocrinologoForm.getDni());

        persona.setDni(endocrinologoForm.getDni());
        persona.setFirstName(endocrinologoForm.getFirstName());
        persona.setLastName(endocrinologoForm.getLastName());
        persona.setPassword(endocrinologoForm.getDni());
        persona.setConfirmPassword(endocrinologoForm.getDni());
        persona.setEmail(endocrinologoForm.getEmail());
        persona.setPhoneNumber(endocrinologoForm.getPhoneNumber());
        persona.setSexo(endocrinologoForm.getSexo());
        persona.setUsername(endocrinologoForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(true);

        persona.setFch_nac(endocrinologoForm.getDia());
        persona.setDomicilio(createDomicilio(endocrinologoForm));

        Endocrinologo endocrinologo = new Endocrinologo(endocrinologoForm.getMatricula(), persona);

        //saveMessage(request, getText("user.savedData", locale));

        if (request.getParameter("delete") != null) {
            Endocrinologo endo = endocrinologoManager.getEndocrinologoByPersona(persona);
            endocrinologoManager.remove(endo);
            personaManager.savePersona(persona);
            saveMessage(request, getText("admin.endocrinologist.deleted", locale));
        } else {
            try{
                personaManager.savePersona(persona);
                endocrinologoManager.saveEndocrinologo(endocrinologo);
            } catch (EntityExistsException e) {
                if (!isNew) {
                    saveMessage(request, getText("admin.endocrinologist.updated", locale));
                }
            }
            if (isNew) {
                saveMessage(request, getText("admin.endocrinologist.added", locale));
            }
        }
        return success;
    }

    private Domicilio createDomicilio(PersonaForm endo) {
        Domicilio domicilio = new Domicilio();
        domicilio.setLocalidad(localidadManager.getLocalidadByNombreYProvincia(endo.getLocalidad(), endo.getProvincia()).get(0));
        domicilio.setPiso(endo.getPiso());
        domicilio.setDpto(endo.getDpto());
        domicilio.setCalle(endo.getCalle());
        domicilio.setNumero(new Long (endo.getNumero()));
        return domicilio;
    }
}
