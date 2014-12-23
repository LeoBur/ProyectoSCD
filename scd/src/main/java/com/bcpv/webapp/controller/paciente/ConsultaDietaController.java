package com.bcpv.webapp.controller.paciente;

import com.bcpv.model.*;
import com.bcpv.service.*;
import com.bcpv.webapp.controller.forms.EndocrinologoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("paciente/dieta*")
public class ConsultaDietaController extends BaseFormController{

	@Autowired
	DietaManager dietaManager;

	@Autowired
	DiaDietaManager diaDietaManager;

	@Autowired
	ComidaManager comidaManager;

	@Autowired
	MomentoDiaManager momentoDiaManager;

	@Autowired
	RegistroComidasManager registroComidasManager;
	
	public ConsultaDietaController(){
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mv = new ModelAndView("paciente/dieta");

		DiaPorDieta lunes = new DiaPorDieta();
		lunes.setDia("Lunes");
		ArrayList<String> comidas = new ArrayList<String>();
		ArrayList<String> momentos = new ArrayList<String>();
		String desayuno = new String("Te con mermeladas");
		String mediaManiana = new String("Manzana");
		String almuerzo = new String("Milanesa de Merluza");
		String mediaTarde = new String("Cocido con tostada");
		String cena = new String("Sopa");
		String antesDeDormir = new String("Ensalada de Fruta");
		comidas.add(desayuno);
		comidas.add(mediaManiana);
		comidas.add(almuerzo);
		comidas.add(mediaTarde);
		comidas.add(cena);
		comidas.add(antesDeDormir);
		lunes.setComida(comidas);

		momentos.add("DESAYUNO");
		momentos.add("MEDIA MANIANA");
		momentos.add("ALMUERZO");
		momentos.add("MEDIA TARDE");
		momentos.add("CENA");
		momentos.add("ANTES DE DORMIR");
		lunes.setMomento(momentos);
		mv.addObject("lunes", lunes);
		DiaPorDieta martes = new DiaPorDieta();
		martes.setDia("Martes");
		desayuno = new String("Te con mermeladsdfgsas");
		mediaManiana = new String("Manzanasdfg");
		almuerzo = new String("Milasdfnessdfgsdfa de Merluza");
		mediaTarde = new String("Cddfokjsdfgcido con tostada");
		cena = new String("Ssfdgsfdopa");
		antesDeDormir = new String("Ensalada de Frusfdgsta");
		ArrayList<String> momentos2 = new ArrayList<String>();
		momentos2.add(desayuno);
		momentos2.add(mediaManiana);
		momentos2.add(almuerzo);
		momentos2.add(mediaTarde);
		momentos2.add(cena);
		momentos2.add(antesDeDormir);
		martes.setMomento(momentos2);
		mv.addObject("martes", martes);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView showFormPost() {
		ModelAndView mv = new ModelAndView("paciente/dieta");
		Comida comida = null;
		comidaManager.saveComida(comida);

		return mv;
	}

}
