package com.bcpv.webapp.controller.paciente;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcpv.webapp.controller.forms.RegistroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		setCancelView("redirect:/registrar");
		setSuccessView("redirect:/registrar");
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
                return "/registrar";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
        Paciente paciente = pacienteManager.getPacienteByUsername(registroForm.username);
        //Date date = formatter.parse(request.getParameter("datepicker"));
        
        if (registroForm.medicion != null){
        	Medicion medicion = new Medicion();
        	medicion.setF_medicion(registroForm.fechaHora);
        	medicion.setValor(new Integer (registroForm.medicion));
        	medicion.setPaciente(paciente);
        	
        	medicionManager.saveMedicion(medicion);
        }
        
        if(registroForm.peso != null){
        	Peso peso = new Peso();
        	peso.setFechaHora(registroForm.fechaHora);
        	peso.setPeso(new Float (registroForm.peso));
        	peso.setPaciente(paciente);
        	
        	pesoManager.savePeso(peso);
        }
        
        if(!registroForm.comidas.isEmpty() && registroForm.momento != null){
        	MomentoDia moment = new MomentoDia();
        	moment.setNombre(registroForm.momento);
        	moment.setComidas(registroForm.getComidas());
        	
        	RegistroComidas reg = new RegistroComidas();
        	reg.setFecha_registro_comida(registroForm.fechaHora);
        	reg.setPaciente(paciente);
        	reg.setMomentoDia(moment);
        	
        	registroComidasManager.saveRegistroComidas(reg);
        }
        
        if(registroForm.medicamento != null){
        	RegistroMedicamento regMed = new RegistroMedicamento();
        	regMed.setFch_reg_medicamento(registroForm.fechaHora);
        	regMed.setPaciente(paciente);
        	regMed.setMedicamento(medicamentoManager.getByNombreComercial(registroForm.medicamento));
        	regMed.setObservaciones(registroForm.observacionesMedicamento);
        	
        	registroMedicamentoManager.saveRegistroMedicamento(regMed);
        }
        
        if(registroForm.sintoma != null){
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

}
