package com.bcpv.webapp.controller.superuser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.model.Tag;
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
import com.bcpv.model.Domicilio;
import com.bcpv.model.Endocrinologo;
import com.bcpv.model.Localidad;
import com.bcpv.model.Persona;
import com.bcpv.model.Provincia;
import com.bcpv.model.Role;
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

    @RequestMapping(method = RequestMethod.GET, value = "/getLocalidades")
    @ResponseBody
    public String getLocalidades( @RequestParam("provincia") String provincia) {
        return localidades(provincia);
    }

	private void buscar(ModelAndView mv, EndocrinologoForm endocrinologoForm, HttpServletRequest request,List<Provincia> provincias, List<Localidad> localidades, Locale locale) {
        final String dni = endocrinologoForm.getDni();
        try {
            if (StringUtils.isEmpty(dni)){
                throw new NullPointerException();
            }

            Persona persona = personaManager.getPersonaByDni(endocrinologoForm.getDni());
            mv.addObject("provinciaList", provincias);
            if (persona.getId() != null) {
                endocrinologoForm.setId(persona.getId());
                endocrinologoForm.setDni(dni);
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setFirstName(persona.getFirstName());
                endocrinologoForm.setLastName(persona.getLastName());
                endocrinologoForm.setEmail(persona.getEmail());
                endocrinologoForm.setUsername(persona.getUsername());
                endocrinologoForm.setPhoneNumber(persona.getPhoneNumber());
                endocrinologoForm.setFechaNac(persona.getFch_nac());
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
		} else {
            buscar(mv, endocrinologoForm, request, provincias, localidades, locale);
        }
		return mv;
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

        boolean isNew = (endocrinologoForm.getId() == null);
        log.debug("entering 'onSubmit' method...");

        String success = "redirect:newEndocrinologo";
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

        Role role = roleManager.getRole(Constants.ENDO_ROLE);
        if (role == null) {
            role = new Role();
            role.setDescription("Endocrinologist role (can edit users)");
            role.setName(Constants.ENDO_ROLE);
            roleManager.saveRole(role);
        }
        persona.addRole(roleManager.getRole(Constants.ENDO_ROLE));

        persona.setFch_nac(getFechaNac(endocrinologoForm));
        persona.setDomicilio(createDomicilio(endocrinologoForm));
        
        Endocrinologo endocrinologo = new Endocrinologo(endocrinologoForm.getMatricula(), persona);
        
        saveMessage(request, getText("user.savedData", locale));
 
        if (request.getParameter("delete") != null) {
            Endocrinologo endo = endocrinologoManager.getEndocrinologoByPersona(persona);
            endocrinologoManager.remove(endo);
            persona.getRoles().remove(roleManager.getRole(Constants.ENDO_ROLE));
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

    private Domicilio createDomicilio(EndocrinologoForm endo) {
        Domicilio domicilio = new Domicilio();
        domicilio.setLocalidad(localidadManager.getLocalidadByNombreYProvincia(endo.getLocalidad(), endo.getProvincia()).get(0));
        domicilio.setPiso(endo.getPiso());
        domicilio.setDpto(endo.getDpto());
        domicilio.setCalle(endo.getCalle());
        domicilio.setNumero(new Long (endo.getNumero()));
        return domicilio;
    }

    private Date getFechaNac(EndocrinologoForm endo) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = endo.getDia() + "/" + endo.getMes() + "/" + endo.getAnio();
        return formatter.parse(strDate);
    }

    private Long getMatricula (Persona persona) {
        try {
            return endocrinologoManager.getEndocrinologoByPersona(persona).getMatricula();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    private String localidades (String provincia) {
        List<Localidad> localidades = localidadManager.getLocalidades();
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
        return ja.toString();
    }
}
