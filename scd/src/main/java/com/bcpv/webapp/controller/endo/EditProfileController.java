package com.bcpv.webapp.controller.endo;

import com.bcpv.Constants;
import com.bcpv.model.Domicilio;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Especialista;
import com.bcpv.model.Localidad;
import com.bcpv.model.Paciente;
import com.bcpv.model.Persona;
import com.bcpv.model.Provincia;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.EspecialistaManager;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.ProvinciaManager;
import com.bcpv.service.RoleManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;
import com.bcpv.webapp.controller.forms.EspecialistaForm;
import com.bcpv.webapp.controller.forms.PacienteForm;
import com.bcpv.webapp.controller.forms.PersonaForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private EspecialistaManager especialistaManager;

    @Autowired
    private RoleManager roleManager;

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
        ModelAndView mv = new ModelAndView("paciente/editProfile");
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

    @RequestMapping(value = "paciente/editProfile*", method = RequestMethod.POST)
    public String submitPaciente(@ModelAttribute("pacienteForm") PacienteForm pacienteForm, BindingResult errors,
                           HttpServletRequest request)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pacienteForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/editProfile";
            }
        }

        boolean isNew = (pacienteForm.getId() == null);
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:/paciente/registrar";
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
        persona.setEnabled(true);

        persona.setFch_nac(pacienteForm.getDia());
        persona.setDomicilio(createDomicilio(pacienteForm));

        //saveMessage(request, getText("user.savedData", locale));

        if (request.getParameter("delete") != null) {
            personaManager.savePersona(persona);
            saveMessage(request, getText("admin.endocrinologist.deleted", locale));
        } else {
            try{
                personaManager.savePersona(persona);
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

    @RequestMapping(value = "nutricionista/editProfile*", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                                 final HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("nutricionista/editProfile");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        if (request.getRemoteUser() == null) {
            saveInfo(request, getText("user.superUser.info.dni", locale));
        } else {
            buscar(mv, especialistaForm, request, provincias, localidades, locale);
        }
        /*
        if (null == search && request.getAttribute("especialistaForm") == null) {
            EspecialistaForm especialista = new EspecialistaForm();
            mv.addObject("especialistaForm", especialista);
            mv.addObject("provinciaList", provincias);
            mv.addObject("localidadList", localidades);
        } else {
            buscar(mv, especialistaForm, request, provincias, localidades, locale);
        } */
        return mv;
    }

    private void buscar(ModelAndView mv, EspecialistaForm especialistaForm, HttpServletRequest request,List<Provincia> provincias, List<Localidad> localidades, Locale locale) {
        try {
            mv.addObject("provinciaList", provincias);
            //mv.addObject("localidadList", localidades);

            Persona persona = personaManager.getPersonaByUsername(request.getRemoteUser());
            if (persona.getId() != null) {
                especialistaForm.setNuevaPersona(false);
                especialistaForm.setId(persona.getId());
                especialistaForm.setDni(persona.getDni());
                especialistaForm.setUsername(persona.getUsername());
                especialistaForm.setFirstName(persona.getFirstName());
                especialistaForm.setLastName(persona.getLastName());
                especialistaForm.setEmail(persona.getEmail());
                especialistaForm.setUsername(persona.getUsername());
                especialistaForm.setPhoneNumber(persona.getPhoneNumber());
                especialistaForm.setDia(persona.getFch_nac());
                especialistaForm.setSexo(persona.getSexo());
                especialistaForm.setDomicilio(persona.getDomicilio());

                Long matricula = getMatriculaEspecialista(persona);
                if (matricula != null) {
                    especialistaForm.setIdEspecialista(getIdEspecialista(persona));
                    especialistaForm.setMatricula(matricula);
                    especialistaForm.setEnabled(persona.isEnabled());
                    especialistaForm.setTipoEspecialista(getTipoEspecialista(persona));
                } else {
                    saveInfo(request, getText("user.superUser.info.nuevoEsp", locale));
                }

                mv.addObject("especialistaForm", especialistaForm);
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(especialistaForm.getProvincia())) {
                        filtradas.add(localidad);
                    }
                }
                mv.addObject("localidadList", filtradas);
            } else {
                mv.addObject("localidadList", localidades);
                saveInfo(request, getText("user.superUser.info.nuevaPersona", locale));
            }

        } catch (NullPointerException npe) {
            saveInfo(request, getText("user.superUser.info.dni", locale));
        }
    }

    private Long getMatriculaEspecialista (Persona persona) {
        try {
            return especialistaManager.getEspecialistaByPersona(persona).getMatricula();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    private Especialista.TipoEspecialista getTipoEspecialista(Persona persona){
        try {
            return especialistaManager.getEspecialistaByPersona(persona).getTipo_esp();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    private Long getIdEspecialista(Persona persona) {
        try {
            Especialista especialista= especialistaManager.getEspecialistaByPersona(persona);
            return especialista.getId();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "nutricionista/editProfile*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(especialistaForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/pacienteList";
            }
        }

        boolean isNew;
        Especialista especialista = new Especialista();
        try {
            especialista = especialistaManager.getEspecialista(especialistaForm.getIdEspecialista());
            isNew = false;
        } catch (EntityNotFoundException e){
            log.warn(e.getMessage());
            isNew = true;
        }


        log.debug("entering 'onSubmit' method from AbmEspecialistaController...");

        String success = "redirect:pacienteList";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(especialistaForm.getDni()); //recupera una persona o crea una nueva instancia

        persona.setDni(especialistaForm.getDni());
        persona.setFirstName(especialistaForm.getFirstName());
        persona.setLastName(especialistaForm.getLastName());
        persona.setEmail(especialistaForm.getEmail());
        persona.setPhoneNumber(especialistaForm.getPhoneNumber());
        persona.setSexo(especialistaForm.getSexo());
        persona.setUsername(especialistaForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(especialistaForm.isEnabled());
        if (especialistaForm.getTipoEspecialista() == Especialista.TipoEspecialista.NUTRICIONISTA){
            persona.addRole(roleManager.getRole(Constants.NUTRI_ROLE));
        } else {
            persona.addRole(roleManager.getRole(Constants.PTRAI_ROLE));
        }
        especialista.setTipo_esp(especialistaForm.getTipoEspecialista());

        persona.setFch_nac(especialistaForm.getDia());
        persona.setDomicilio(createDomicilio(especialistaForm));


        if(isNew){
            persona.setPassword(especialistaForm.getDni());
            persona.setConfirmPassword(especialistaForm.getDni());
            especialista = new Especialista(especialistaForm.getMatricula(), especialistaForm.getTipoEspecialista(), persona);
        } else {
            especialista.setMatricula(especialistaForm.getMatricula());
        }

        if (request.getParameter("delete") != null) {
            Especialista espe = especialistaManager.getEspecialistaByPersona(persona);
            if (espe.getPacientes().isEmpty()){
                especialistaManager.remove(espe);
                if (espe.getTipo_esp() == Especialista.TipoEspecialista.NUTRICIONISTA){
                    persona.getRoles().remove(roleManager.getRole(Constants.NUTRI_ROLE));
                } else {
                    persona.getRoles().remove(roleManager.getRole(Constants.PTRAI_ROLE));
                }
                personaManager.savePersona(persona);
                saveMessage(request, getText("user.endocrinologist.specialistDeleted", locale));
            } else {
                saveMessage(request, getText("user.endocrinologist.specialistNotDeleted", locale));
            }

        } else {
            try{
                personaManager.savePersona(persona);
                especialistaManager.saveEspecialista(especialista);
            } catch (EntityExistsException e) {
                log.warn(e.getMessage());
                saveMessage(request, "No se realizaron cambios en el perfil");
                return "redirect:pacienteList";
            }
            if (isNew) {
                saveMessage(request, getText("user.endocrinologist.specialistSaved", locale));
            } else {
                saveMessage(request, getText("user.endocrinologist.specialistUpdated", locale));
            }
        }
        return success;
    }
}
