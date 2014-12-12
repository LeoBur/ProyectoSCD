package com.bcpv.webapp.controller.paciente;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.model.Comida;
import com.bcpv.webapp.controller.forms.RegistroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.bcpv.model.Alimento;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Medicion;
import com.bcpv.model.MomentoDia;
import com.bcpv.model.Paciente;
import com.bcpv.model.Peso;
import com.bcpv.model.RegistroComidas;
import com.bcpv.model.RegistroMedicamento;
import com.bcpv.model.RegistroSintoma;
import com.bcpv.model.Sintoma;
import com.bcpv.service.AlimentoManager;
import com.bcpv.service.ComidaManager;
import com.bcpv.service.MedicamentoManager;
import com.bcpv.service.MedicionManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.service.PesoManager;
import com.bcpv.service.RegistroComidasManager;
import com.bcpv.service.RegistroMedicamentoManager;
import com.bcpv.service.RegistroSintomaManager;
import com.bcpv.service.SintomaManager;
import com.bcpv.webapp.controller.BaseFormController;
import com.bcpv.webapp.controller.forms.PacienteForm;

@Controller
@RequestMapping("/paciente/registrar*")
public class RegistrarController extends BaseFormController{

	@Autowired
	MedicionManager medicionManager;

	@Autowired
	PesoManager pesoManager;

	@Autowired
	ComidaManager comidaManager;

	@Autowired
	PacienteManager pacienteManager;

	@Autowired
	RegistroComidasManager registroComidasManager;

	@Autowired
	RegistroMedicamentoManager registroMedicamentoManager;

	@Autowired
	RegistroSintomaManager registroSintomaManager;

	@Autowired
	MedicamentoManager medicamentoManager;

	@Autowired
	SintomaManager sintomaManager;

	@Autowired
	AlimentoManager alimentoManager;

	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

	public RegistrarController(){
		setCancelView("redirect:/paciente/registrar");
		setSuccessView("redirect:/paciente/registrar");
	}

    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Integer.class, null,
                new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
        binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
        SimpleDateFormat dateFormat =
                new SimpleDateFormat(getText("datetime.format", request.getLocale()));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));
    }

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mv = new ModelAndView("paciente/registrar");
		RegistroForm registroForm = new RegistroForm();
		List<Medicamento> medicamentos = medicamentoManager.getMedicamentos();
		List<Sintoma> sintomas = sintomaManager.getSintomas();
		List<Alimento> alimentos = alimentoManager.getAlimentos();
		mv.addObject("pacienteForm", registroForm);
		mv.addObject("medicamentoList", medicamentos);
		mv.addObject("sintomaList", sintomas);
		mv.addObject("alimentoList", alimentos);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("pacienteForm") RegistroForm registroForm, BindingResult errors,
    					   HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(registroForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/paciente/registrar";
            }
        }

        log.debug("Registro del paciente " + request.getRemoteUser());

        String success = getSuccessView();
        Locale locale = request.getLocale();
        Paciente paciente = pacienteManager.getPacienteByUsername(request.getRemoteUser());
        Set<Comida> comidas = setComidas(registroForm);
        if (!registroForm.hora.isEmpty()) {
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(registroForm.fechaHora);
            //calendar.set(Calendar.HOUR_OF_DAY, registroForm.hora);
            Date date=calendar.getTime();
            registroForm.setFechaHora(date);
        } else {
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(registroForm.fechaHora);
            calendar.setTimeInMillis(request.getSession().getLastAccessedTime());
            Date date=calendar.getTime();
            registroForm.setFechaHora(date);
        }
        //Date date = formatter.parse(request.getParameter("datepicker"));

        if (!registroForm.medicion.isEmpty()){
        	Medicion medicion = new Medicion();
        	medicion.setF_medicion(registroForm.fechaHora);
        	medicion.setValor(new Integer (registroForm.medicion));
        	medicion.setPaciente(paciente);

        	medicionManager.saveMedicion(medicion);
        }

        if(!registroForm.peso.isEmpty()){
        	Peso peso = new Peso();
        	peso.setFechaHora(registroForm.fechaHora);
        	peso.setPeso(new Float (registroForm.peso));
        	peso.setPaciente(paciente);

        	pesoManager.savePeso(peso);
        }

        if(comidas != null && registroForm.momento != null){
        	MomentoDia moment = new MomentoDia();
        	moment.setNombre(registroForm.momento);
        	moment.setComidas(comidas);

        	RegistroComidas reg = new RegistroComidas();
        	reg.setFecha_registro_comida(registroForm.fechaHora);
        	reg.setPaciente(paciente);
        	reg.setMomentoDia(moment);

        	registroComidasManager.saveRegistroComidas(reg);
        }

        if(!registroForm.medicamento.isEmpty()){
        	RegistroMedicamento regMed = new RegistroMedicamento();
        	regMed.setFch_reg_medicamento(registroForm.fechaHora);
        	regMed.setPaciente(paciente);
        	regMed.setMedicamento(medicamentoManager.getByNombreComercial(registroForm.medicamento));
        	regMed.setObservaciones(registroForm.observacionesMedicamento);

        	registroMedicamentoManager.saveRegistroMedicamento(regMed);
        }

        if(!registroForm.sintoma.isEmpty()){
        	RegistroSintoma regSint = new RegistroSintoma();
        	regSint.setFch_reg_sintoma(registroForm.getFechaHora());
        	regSint.setPaciente(paciente);
        	regSint.setObservaciones(registroForm.getObservacionesSintoma());
        	regSint.setSintoma(sintomaManager.getByNombre(registroForm.sintoma));

        	registroSintomaManager.saveRegistroSintoma(regSint);
        }

        saveMessage(request, getText("user.paciente.savedData", locale));

        /*if (request.getParameter("delete") != null) {
            personManager.remove(person.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
            personManager.save(person);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:personform?id=" + person.getId();
            }
        }*/
        return success;
    }

    private Set<Comida> setComidas(RegistroForm registroForm) {
        Set<Comida> comidas = new HashSet<Comida>();
        if (!registroForm.cantidad1.isEmpty()) {
            comidas.add(createComida(registroForm.alimento1, registroForm.cantidad1, registroForm.observacion1));
        }
        if (!registroForm.cantidad2.isEmpty()) {
            comidas.add(createComida(registroForm.alimento2, registroForm.cantidad2, registroForm.observacion2));
        }
        if (!registroForm.cantidad3.isEmpty()) {
            comidas.add(createComida(registroForm.alimento3, registroForm.cantidad3, registroForm.observacion3));
        }
        if (!registroForm.cantidad4.isEmpty()) {
            comidas.add(createComida(registroForm.alimento4, registroForm.cantidad4, registroForm.observacion4));
        }
        if (!registroForm.cantidad5.isEmpty()) {
            comidas.add(createComida(registroForm.alimento5, registroForm.cantidad5, registroForm.observacion5));
        }
        return comidas;
    }

    private Comida createComida(String alimento, String cant, String obs) {
        Comida comida = new Comida();
        comida.setAlimento(alimentoManager.getByNombre(alimento));
        comida.setCantidad(cant);
        comida.setObservaciones(obs);
        return comida;
    }
}