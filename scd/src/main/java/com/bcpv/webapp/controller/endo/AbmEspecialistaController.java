package com.bcpv.webapp.controller.endo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.Constants;
import com.bcpv.model.*;
import com.bcpv.service.*;
import com.bcpv.webapp.controller.forms.EspecialistaForm;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.model.Especialista.TipoEspecialista;

@Controller
public class AbmEspecialistaController extends BaseFormController {
	
	@Autowired
	EspecialistaManager especialistaManager;

    @Autowired
    PersonaManager personaManager;

    @Autowired
    ProvinciaManager provinciaManager;

    @Autowired
    LocalidadManager localidadManager;

    @Autowired
    RoleManager roleManager;
	
	public AbmEspecialistaController(){
		
	}

    private void buscar(ModelAndView mv, EspecialistaForm especialistaForm, HttpServletRequest request,List<Provincia> provincias, List<Localidad> localidades, Locale locale) {
        final String dni = especialistaForm.getDni();
        try {
            if (StringUtils.isEmpty(dni)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByDni(especialistaForm.getDni());
            mv.addObject("provinciaList", provincias);
            if (persona.getId() != null) {
                especialistaForm.setId(persona.getId());
                especialistaForm.setDni(dni);
                especialistaForm.setUsername(persona.getUsername());
                especialistaForm.setFirstName(persona.getFirstName());
                especialistaForm.setLastName(persona.getLastName());
                especialistaForm.setEmail(persona.getEmail());
                especialistaForm.setUsername(persona.getUsername());
                especialistaForm.setPhoneNumber(persona.getPhoneNumber());
                especialistaForm.setFechaNac(persona.getFch_nac());
                especialistaForm.setSexo(persona.getSexo());
                especialistaForm.setDomicilio(persona.getDomicilio());
                especialistaForm.setMatricula(getMatricula(persona));
                especialistaForm.setEnabled(persona.isEnabled());
                especialistaForm.setTipoEspecialista(getTipoEspecialista(persona));
                mv.addObject("especialistaForm", especialistaForm);
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(especialistaForm.getProvincia())) {
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

    @RequestMapping(value = "endos/adminEspecialista*", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                                 final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv;
        if (null == search && request.getAttribute("especialistaForm") == null){
            mv = new ModelAndView("endos/newEspecialista");
        }else {
            mv = new ModelAndView("endos/editEspecialista");
        }
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        if (null == search && request.getAttribute("especialistaForm") == null) {
            EspecialistaForm especialista = new EspecialistaForm();
            mv.addObject("especialistaForm", especialista);
            mv.addObject("provinciaList", provincias);
            mv.addObject("localidadList", localidades);
        } else {
            buscar(mv, especialistaForm, request, provincias, localidades, locale);
        }
        return mv;
    }

    @RequestMapping(value = "endos/editEspecialista*", method = RequestMethod.GET)
    public ModelAndView showFormEdit(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                                     final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/editEspecialista");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
        buscar(mv, especialistaForm, request, provincias, localidades, locale);
        return mv;
    }
    
    @RequestMapping(value = "endos/especialistaList*", method = RequestMethod.GET)
    public ModelAndView showEspecialistas(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                                           final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("endos/especialistaList");
        List<Especialista> especialistas = especialistaManager.getEspecialistas();
        List<Especialista> especialistasFilter = new ArrayList<Especialista>();

        if (search == null) {
            mv.addObject("especialistaList", especialistas);
            return mv;
        } else {
            for (Especialista especialistafilter : especialistas) {
                if (especialistafilter.getPersona().getDni().startsWith(especialistaForm.getDni()) || (especialistafilter.getPersona().getLastName().startsWith(especialistaForm.getDni().toUpperCase()))) {
                    especialistasFilter.add(especialistafilter);
                }
            }
            if (especialistasFilter.size() == 0) {
                mv.addObject("especialistaList", especialistas);
                saveInfo(request, "No existe el Especialista");
                return mv;
            } else {
                mv.addObject("especialistaList", especialistasFilter);
                return mv;
            }
        }
    }

    @RequestMapping(value = "endos/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Especialista especialista : especialistaManager.getEspecialistas()) {
            data.add(new Tag(cont++, especialista.getPersona().getLastName()));
            dataFilter.add(new Tag(cont++, especialista.getPersona().getDni()));
            //data.add(new Tag(cont++, especialista.getPersona().getDni()));
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

    @RequestMapping(value = "endo/adminEspecialista*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(especialistaForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/adminEspecialista";
            }
        }

        boolean isNew = (especialistaForm.getId() == null);
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:adminEspecialista";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(especialistaForm.getDni());

        persona.setDni(especialistaForm.getDni());
        persona.setFirstName(especialistaForm.getFirstName());
        persona.setLastName(especialistaForm.getLastName());
        persona.setPassword(especialistaForm.getDni());
        persona.setConfirmPassword(especialistaForm.getDni());
        persona.setEmail(especialistaForm.getEmail());
        persona.setPhoneNumber(especialistaForm.getPhoneNumber());
        persona.setSexo(especialistaForm.getSexo());
        persona.setUsername(especialistaForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(especialistaForm.isEnabled());
        if (especialistaForm.getTipoEspecialista() == TipoEspecialista.NUTRICIONISTA){
            persona.addRole(roleManager.getRole(Constants.NUTRI_ROLE));
        } else {
            persona.addRole(roleManager.getRole(Constants.PTRAI_ROLE));
        }

        persona.setFch_nac(getFechaNac(especialistaForm));
        persona.setDomicilio(createDomicilio(especialistaForm));

        Especialista especialista = new Especialista(especialistaForm.getMatricula(), especialistaForm.getTipoEspecialista(), persona);

        if (request.getParameter("delete") != null) {
            Especialista espe = especialistaManager.getEspecialistaByPersona(persona);
            if (espe.getPacientes().isEmpty()){
                especialistaManager.remove(espe);
                if (espe.getTipo_esp() == TipoEspecialista.NUTRICIONISTA){
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
            }
            if (isNew) {
                saveMessage(request, getText("user.endocrinologist.specialistSaved", locale));
            } else {
                saveMessage(request, getText("user.endocrinologist.specialistUpdated", locale));
            }
        }
        return success;
    }

    @RequestMapping(value = "endos/editEspecialista*", method = RequestMethod.POST)
    public String onSubmitEdit(@ModelAttribute("especialistaForm") EspecialistaForm especialistaForm, BindingResult errors,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(especialistaForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/newEspecialista";
            }
        }

        boolean isNew = (especialistaForm.getId() == null);
        log.debug("entering 'onSubmitEdit' method...");

        String success = "redirect:newEspecialista";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(request.getParameter("dniposta"));

        persona.setDni(request.getParameter("dniposta"));
        persona.setFirstName(especialistaForm.getFirstName());
        persona.setLastName(especialistaForm.getLastName());
        persona.setEmail(especialistaForm.getEmail());
        persona.setPhoneNumber(especialistaForm.getPhoneNumber());
        persona.setSexo(especialistaForm.getSexo());
        persona.setUsername(especialistaForm.getEmail());
        persona.setAccountExpired(false);
        persona.setAccountLocked(false);
        persona.setEnabled(especialistaForm.isEnabled());
        if (especialistaForm.getTipoEspecialista() == TipoEspecialista.NUTRICIONISTA){
            persona.addRole(roleManager.getRole(Constants.NUTRI_ROLE));
        } else {
            persona.addRole(roleManager.getRole(Constants.PTRAI_ROLE));
        }
        persona.setFch_nac(getFechaNac(especialistaForm));
        persona.setDomicilio(createDomicilio(especialistaForm));

        Especialista especialista = especialistaManager.getEspecialista(especialistaForm.getId());;
        especialista.setMatricula(especialistaForm.getMatricula());

        if (request.getParameter("delete") != null) {
            Especialista espe = especialistaManager.getEspecialistaByPersona(persona);
            if (espe.getPacientes().isEmpty()){
                especialistaManager.remove(espe);
                if (espe.getTipo_esp() == TipoEspecialista.NUTRICIONISTA){
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
            }
            if (isNew) {
                saveMessage(request, getText("user.endocrinologist.specialistSaved", locale));
            } else {
                saveMessage(request, getText("user.endocrinologist.specialistUpdated", locale));
            }
        }
        return success;
    }

    private Domicilio createDomicilio(EspecialistaForm especialista) {
        Domicilio domicilio = new Domicilio();
        domicilio.setLocalidad(localidadManager.getLocalidadByNombreYProvincia(especialista.getLocalidad(), especialista.getProvincia()).get(0));
        domicilio.setPiso(especialista.getPiso());
        domicilio.setDpto(especialista.getDpto());
        domicilio.setCalle(especialista.getCalle());
        domicilio.setNumero(new Long (especialista.getNumero()));
        return domicilio;
    }

    private Date getFechaNac(EspecialistaForm especialista) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = especialista.getDia() + "/" + especialista.getMes() + "/" + especialista.getAnio();
        return formatter.parse(strDate);
    }

    private Long getMatricula (Persona persona) {
        try {
            return especialistaManager.getEspecialistaByPersona(persona).getMatricula();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    private TipoEspecialista getTipoEspecialista(Persona persona){
        try {
            return especialistaManager.getEspecialistaByPersona(persona).getTipo_esp();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
