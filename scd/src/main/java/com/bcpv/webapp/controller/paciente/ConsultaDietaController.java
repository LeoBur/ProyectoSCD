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
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import sun.util.calendar.*;

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
	public ModelAndView showForm(final HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("paciente/dieta");

		Locale locale = request.getLocale();
		Calendar calendario = Calendar.getInstance();
		DateFormat formato = new SimpleDateFormat("DD/MM/yyyy", locale);
		Date actual = null;
		Date fechaInicio = null;
		Date fechaHasta = null;
		Set<DietaParaMostrar> lunes = new HashSet<>();
		Set<DietaParaMostrar> martes = new HashSet<>();
		Set<DietaParaMostrar> miercoles = new HashSet<>();
		Set<DietaParaMostrar> jueves = new HashSet<>();
		Set<DietaParaMostrar> viernes = new HashSet<>();
		Set<DietaParaMostrar> sabado = new HashSet<>();
		String formatin="MM";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatin);

		List<Dieta> dietas = new ArrayList<>();
		dietas = dietaManager.getDietas();
		if (dietas.size() != 0) {
			int añoActual = calendario.get(Calendar.YEAR);
			int mesActual = Integer.parseInt(dateFormat.format(calendario.getTime()));
			int i = 0;
			for (i = 0; i < dietas.size(); i++) {
				fechaInicio = dietas.get(i).getFechaAlta();
				calendario.setTime(fechaInicio);
				int añoDB = calendario.get(Calendar.YEAR);
				int mesDB = Integer.parseInt(dateFormat.format(dietas.get(i).getFechaAlta()));

				if ((añoActual == añoDB) && (mesActual == mesDB)){
					String username = dietas.get(i).getPaciente().getPersona().getUsername();
					String dia = dietas.get(i).getDiasDieta().iterator().next().getNombreDiaDieta().name();
					Iterator<DiaDieta> dias = dietas.get(i).getDiasDieta().iterator();
					while (dias.hasNext()) {
						DiaDieta e = dias.next();

						if (username.equals(request.getRemoteUser())) {
							if (e.getNombreDiaDieta().name().equals("LUNES_1")) {
								lunes.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							} else if (e.getNombreDiaDieta().name().equals("MARTES_1")) {
								martes.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							} else if (e.getNombreDiaDieta().name().equals("MIERCOLES_1")) {
								miercoles.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							} else if (e.getNombreDiaDieta().name().equals("JUEVES_1")) {
								jueves.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							} else if (e.getNombreDiaDieta().name().equals("VIERNES_1")) {
								viernes.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							} else {
								sabado.add(new DietaParaMostrar(e.getMomentosDia().iterator().next().getNombre().name(), e.getMomentosDia().iterator().next().getComidas().iterator().next().getAlimento().getNombre()));
							}
						}
					}
				}
			}
		}
		if (lunes.isEmpty() && martes.isEmpty() && miercoles.isEmpty() && jueves.isEmpty() && viernes.isEmpty() && sabado.isEmpty()) {
			saveInfo(request, "No existe dieta cargada para este mes. Comuniquese con su nutricionista para que le recete una");
		}
		mv.addObject("lunesList", lunes);
		mv.addObject("martesList", martes);
		mv.addObject("miercolesList", miercoles);
		mv.addObject("juevesList", jueves);
		mv.addObject("viernesList", viernes);
		mv.addObject("sabadoList", sabado);

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
