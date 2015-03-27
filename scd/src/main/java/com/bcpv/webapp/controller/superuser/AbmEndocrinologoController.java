package com.bcpv.webapp.controller.superuser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.model.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.Constants;
import com.bcpv.service.EndocrinologoManager;
import com.bcpv.service.LocalidadManager;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.ProvinciaManager;
import com.bcpv.service.RoleManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;

@Controller
public class AbmEndocrinologoController extends BaseFormController {

	@Autowired
	PersonaManager personaManager;

	@Autowired
	EndocrinologoManager endocrinologoManager;

	@Autowired
	ProvinciaManager provinciaManager;

	@Autowired
	LocalidadManager localidadManager;

    @Autowired
    RoleManager roleManager;
	
	public AbmEndocrinologoController(){
	
	}

	@RequestMapping(value = "admin/newEndocrinologo*", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm, BindingResult errors,
                                 final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("admin/newEndocrinologo");
        Locale locale = request.getLocale();
        List<Provincia> provincias = provinciaManager.getProvincias();
        List<Localidad> localidades = localidadManager.getLocalidades();
		if (null == search && request.getAttribute("endocrinologoForm") == null) {
			EndocrinologoForm endo = new EndocrinologoForm();
			mv.addObject("endocrinologoForm", endo);
            mv.addObject("provinciaList", provincias);
            mv.addObject("localidadList", localidades);
            return mv;
		} else {
            buscar(mv, endocrinologoForm, request, provincias, localidades, locale);
            return mv;
        }
	}

	@RequestMapping(value = "admin/newEndocrinologo*", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm, BindingResult errors, 
    					   HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(endocrinologoForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/newEndocrinologo";
            }
        }

        boolean isNew;
        Endocrinologo endocrinologo = null;
        try {
            endocrinologo = endocrinologoManager.getEndocrinologo(endocrinologoForm.getIdEndo());
            isNew = false;
        } catch (EntityNotFoundException e){
            log.warn(e.getMessage());
            isNew = true;
        }
        log.debug("entering 'onSubmit' method from AbmEndocrinologoController...");

        String success = "redirect:endocrinologoList";
        Locale locale = request.getLocale();

        Persona persona = personaManager.getPersonaByDni(endocrinologoForm.getDni());  //recupera una persona o crea una nueva instancia

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
        persona.setEnabled(endocrinologoForm.isEnabled());

        persona.addRole(roleManager.getRole(Constants.ENDO_ROLE));

        persona.setFch_nac(endocrinologoForm.getDia());
        persona.setDomicilio(createDomicilio(endocrinologoForm));

        if(isNew){
            persona.setPassword(endocrinologoForm.getDni());
            persona.setConfirmPassword(endocrinologoForm.getDni());
            endocrinologo = new Endocrinologo(endocrinologoForm.getMatricula(), persona);
        } else {
            endocrinologo.setMatricula(endocrinologoForm.getMatricula());
        }

        if (request.getParameter("delete") != null) {
            Endocrinologo endo;
            try {
                endo = endocrinologoManager.getEndocrinologoByPersona(persona);
            } catch (EntityNotFoundException e) {
                saveMessage(request, "No existe el endocrinologo");
                return "redirect:newEndocrinologo";
            }
            if (endo.getPacientes().isEmpty()){
                endocrinologoManager.remove(endo);
                persona.getRoles().remove(roleManager.getRole(Constants.ENDO_ROLE));
                persona.setEnabled(false);
                personaManager.savePersona(persona);
                saveMessage(request, getText("admin.endocrinologist.deleted", locale));
            } else {
                saveMessage(request, getText("admin.endocrinologist.not.deleted", locale));
            }

        } else {
            try{
                personaManager.savePersona(persona);
                endocrinologoManager.saveEndocrinologo(endocrinologo);
            } catch (EntityExistsException e) {
                log.warn(e.getMessage());
                saveError(request,e.getMessage().toString());
                return "redirect:newEndocrinologo";
            }
            if (isNew) {
                saveMessage(request, getText("admin.endocrinologist.added", locale));
            } else {
                saveMessage(request, getText("admin.endocrinologist.updated", locale));
            }
        }
        return success;
    }

    @RequestMapping(value = "admin/endocrinologoList*", method = RequestMethod.GET)
    public ModelAndView showEndocrinologos(@ModelAttribute("endocrinologoForm") EndocrinologoForm endocrinologoForm,
                                           BindingResult errors, final HttpServletRequest request,
                                           @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("admin/endocrinologoList");
        List<Endocrinologo> endocrinologos = endocrinologoManager.getEndocrinologos();
        List<Endocrinologo> endocrinologosFilter = new ArrayList<Endocrinologo>();

        if (search == null) {
            mv.addObject("endocrinologoList", endocrinologos);
            return mv;
        } else {
            for (Endocrinologo endocrinologofilter : endocrinologos) {
                if (endocrinologofilter.getPersona().getDni().startsWith(endocrinologoForm.getDni())
                        || (endocrinologofilter.getPersona().getLastName().startsWith(endocrinologoForm.getDni().toUpperCase()))) {
                    endocrinologosFilter.add(endocrinologofilter);
                }
            }
            if (endocrinologosFilter.size() == 0) {
                mv.addObject("endocrinologoList", endocrinologos);
                saveInfo(request, "No existe el Endocrinologo");
                return mv;
            } else {
                mv.addObject("endocrinologoList", endocrinologosFilter);
                return mv;
            }
        }
    }

    @RequestMapping(value = "admin/getTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Endocrinologo endocrinologo : endocrinologoManager.getEndocrinologos()) {
            data.add(new Tag(cont++, endocrinologo.getPersona().getLastName()));
            dataFilter.add(new Tag(cont++, endocrinologo.getPersona().getDni()));
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

    @RequestMapping(value = "admin/getDNITags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getDNITags(@RequestParam String tagName) {
        int cont = 0;
        List<Tag> data = new ArrayList<Tag>();
        List<Tag> dataFilter = new ArrayList<Tag>();
        List<Tag> result = new ArrayList<Tag>();

        for (Endocrinologo endocrinologo : endocrinologoManager.getEndocrinologos()) {
            data.add(new Tag(cont++, endocrinologo.getPersona().getDni()));
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

    private void buscar(ModelAndView mv, EndocrinologoForm endocrinologoForm, HttpServletRequest request,List<Provincia> provincias, List<Localidad> localidades, Locale locale) {
        final String dni = endocrinologoForm.getDni();
        try {
            if (StringUtils.isEmpty(dni)){
                mv.addObject("provinciaList", provincias);
                mv.addObject("localidadList", localidades);
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByDni(endocrinologoForm.getDni());
            mv.addObject("provinciaList", provincias);

            if (persona.getId() != null) {
                endocrinologoForm.setNuevaPersona(false);
                endocrinologoForm.setId(persona.getId());
                endocrinologoForm.setDni(dni);
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setFirstName(persona.getFirstName());
                endocrinologoForm.setLastName(persona.getLastName());
                endocrinologoForm.setEmail(persona.getEmail());
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setPhoneNumber(persona.getPhoneNumber());
                endocrinologoForm.setDia(persona.getFch_nac());
                endocrinologoForm.setSexo(persona.getSexo());
                endocrinologoForm.setDomicilio(persona.getDomicilio());
                endocrinologoForm.setEnabled(persona.isEnabled());
                endocrinologoForm.setEnableFields(persona.isEnabled());

                Long matricula = getMatricula(persona);
                if (matricula != null) {
                    endocrinologoForm.setMatricula(getMatricula(persona));
                    endocrinologoForm.setIdEndo(getIdEndocrinologo(persona));
                } else {
                    saveInfo(request, getText("user.superUser.info.nuevoEndo", locale));
                }
                List<Localidad> filtradas = new ArrayList<>();
                for (Localidad localidad : localidades) {
                    if (localidad.getProvincia().getNombre().equals(endocrinologoForm.getProvincia())) {
                        filtradas.add(localidad);
                    }
                }
                mv.addObject("endocrinologoForm", endocrinologoForm);
                mv.addObject("localidadList", filtradas);
            } else {
                mv.addObject("localidadList", localidades);
                saveInfo(request, getText("user.superUser.info.nuevaPersona", locale));
            }
        } catch (NullPointerException npe) {
            saveInfo(request, getText("user.superUser.info.dni", locale));
        }
    }

    private Domicilio createDomicilio(EndocrinologoForm endo) {
        Domicilio domicilio = new Domicilio();
        domicilio.setLocalidad(localidadManager.getLocalidadByNombreYProvincia(endo.getLocalidad(), endo.getProvincia()).get(0));
        domicilio.setPiso(endo.getPiso());
        domicilio.setDpto(endo.getDpto());
        domicilio.setCalle(endo.getCalle());
        domicilio.setNumero(new Long (endo.getNumero()));
        return domicilio;
    }

    private Long getMatricula (Persona persona) {
        try {
            return endocrinologoManager.getEndocrinologoByPersona(persona).getMatricula();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    private Long getIdEndocrinologo(Persona persona) {
        try {
            Endocrinologo endocrinologo = endocrinologoManager.getEndocrinologoByPersona(persona);
            return endocrinologo.getId();
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
