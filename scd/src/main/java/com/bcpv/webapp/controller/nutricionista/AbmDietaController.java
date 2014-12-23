package com.bcpv.webapp.controller.nutricionista;

import com.bcpv.model.Alimento;
import com.bcpv.model.Comida;
import com.bcpv.model.DiaDieta;
import com.bcpv.model.Dieta;
import com.bcpv.model.MomentoDia;
import com.bcpv.service.AlimentoManager;
import com.bcpv.service.DietaManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.webapp.controller.forms.DietaRecomendadaForm;
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
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/nutricionista/dieta*")
public class AbmDietaController extends BaseFormController{

    @Autowired
    AlimentoManager alimentoManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    DietaManager dietaManager;

	public AbmDietaController(){
	}

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(@RequestParam(required=false, value="username") String username) {
        ModelAndView mv = new ModelAndView("nutricionista/dietaRecomendada");
        DietaRecomendadaForm dietaRecomendadaForm = new DietaRecomendadaForm();
        dietaRecomendadaForm.setDni(username);
        List<Alimento> alimentos = alimentoManager.getAlimentos();
        mv.addObject("alimentoList", alimentos);
        mv.addObject("dietaRecomendadaForm", dietaRecomendadaForm);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public  String onSubmit(@ModelAttribute("DietaRecomendadForm") DietaRecomendadaForm dietaRecomendadaForm, BindingResult errors,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        String success = "redirect:pacienteList";;

        if (request.getParameter("cancel") != null) {
            //return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(dietaRecomendadaForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                //return "/paciente/registrar";
            }
        }

        Alimento alimento1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoLunes1());
        Alimento alimento2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoLunes2());
        Comida comidaLunes1 = new Comida();
        comidaLunes1.setAlimento(alimento1);
        comidaLunes1.setCantidad(dietaRecomendadaForm.getCantidadLunes1());
        comidaLunes1.setObservaciones(dietaRecomendadaForm.getObservacionesLunes1());
        Comida comidaLunes2 = new Comida();
        comidaLunes2.setAlimento(alimento2);
        comidaLunes2.setCantidad(dietaRecomendadaForm.getCantidadLunes2());
        comidaLunes2.setObservaciones(dietaRecomendadaForm.getObservacionesLunes2());
        Set<Comida> comidasLunes = new HashSet<Comida>();
        comidasLunes.add(comidaLunes1);
        comidasLunes.add(comidaLunes2);
        MomentoDia momentoDiaLunes1 = new MomentoDia();
        momentoDiaLunes1.setComidas(comidasLunes);
        momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasLunes = new HashSet<>(0);
        momentoDiasLunes.add(momentoDiaLunes1);

        DiaDieta diaDieta = new DiaDieta();
        diaDieta.setMomentosDia(momentoDiasLunes);
        diaDieta.setNombreDiaDieta(DiaDieta.Dias.LUNES_1);
        //Martes
        Alimento alimentoMartes1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMartes1());
        Alimento alimentoMartes2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMartes2());
        Comida comidaMartes1 = new Comida();
        comidaMartes1.setAlimento(alimentoMartes1);
        comidaMartes1.setCantidad(dietaRecomendadaForm.getCantidadMartes1());
        comidaMartes1.setObservaciones(dietaRecomendadaForm.getObservacionesMartes1());
        Comida comidaMartes2 = new Comida();
        comidaMartes2.setAlimento(alimentoMartes2);
        comidaMartes2.setCantidad(dietaRecomendadaForm.getCantidadMartes2());
        comidaMartes2.setObservaciones(dietaRecomendadaForm.getObservacionesMartes2());
        Set<Comida> comidasMartes = new HashSet<Comida>();
        comidasMartes.add(comidaMartes1);
        comidasMartes.add(comidaMartes2);
        MomentoDia momentoDiaMartes1 = new MomentoDia();
        momentoDiaMartes1.setComidas(comidasLunes);
        momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasMartes = new HashSet<>(0);
        momentoDiasMartes.add(momentoDiaMartes1);

        DiaDieta diaDietaMartes = new DiaDieta();
        diaDietaMartes.setMomentosDia(momentoDiasMartes);
        diaDietaMartes.setNombreDiaDieta(DiaDieta.Dias.MARTES_1);
        //Miercoles
        Alimento alimentoMiercoles1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMiercoles1());
        Alimento alimentoMiercoles2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMiercoles2());
        Comida comidaMiercoles1 = new Comida();
        comidaMiercoles1.setAlimento(alimentoMiercoles1);
        comidaMiercoles1.setCantidad(dietaRecomendadaForm.getCantidadMiercoles1());
        comidaMiercoles1.setObservaciones(dietaRecomendadaForm.getObservacionesMiercoles1());
        Comida comidaMiercoles2 = new Comida();
        comidaMiercoles2.setAlimento(alimentoMiercoles2);
        comidaMiercoles2.setCantidad(dietaRecomendadaForm.getCantidadMiercoles2());
        comidaMiercoles2.setObservaciones(dietaRecomendadaForm.getObservacionesMiercoles2());
        Set<Comida> comidasMiercoles = new HashSet<Comida>();
        comidasMiercoles.add(comidaMiercoles1);
        comidasMiercoles.add(comidaMiercoles2);
        MomentoDia momentoDiaMiercoles1 = new MomentoDia();
        momentoDiaMiercoles1.setComidas(comidasMiercoles);
        momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasMiercoles = new HashSet<>(0);
        momentoDiasMiercoles.add(momentoDiaMiercoles1);

        DiaDieta diaDietaMiercoles = new DiaDieta();
        diaDietaMiercoles.setMomentosDia(momentoDiasMiercoles);
        diaDietaMiercoles.setNombreDiaDieta(DiaDieta.Dias.MIERCOLES_1);

        //Jueves
        Alimento alimentoJueves1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoJueves1());
        Alimento alimentoJueves2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoJueves2());
        Comida comidaJueves1 = new Comida();
        comidaJueves1.setAlimento(alimentoJueves1);
        comidaJueves1.setCantidad(dietaRecomendadaForm.getCantidadJueves1());
        comidaJueves1.setObservaciones(dietaRecomendadaForm.getObservacionesJueves1());
        Comida comidaJueves2 = new Comida();
        comidaJueves2.setAlimento(alimentoJueves2);
        comidaJueves2.setCantidad(dietaRecomendadaForm.getCantidadJueves2());
        comidaJueves2.setObservaciones(dietaRecomendadaForm.getObservacionesJueves2());
        Set<Comida> comidasJueves = new HashSet<Comida>();
        comidasJueves.add(comidaJueves1);
        comidasJueves.add(comidaJueves2);
        MomentoDia momentoDiaJueves1 = new MomentoDia();
        momentoDiaJueves1.setComidas(comidasJueves);
        momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasJueves = new HashSet<>(0);
        momentoDiasJueves.add(momentoDiaJueves1);

        DiaDieta diaDietaJueves = new DiaDieta();
        diaDietaJueves.setMomentosDia(momentoDiasJueves);
        diaDietaJueves.setNombreDiaDieta(DiaDieta.Dias.JUEVES_1);

        //Viernes
        Alimento alimentoViernes1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoViernes1());
        Alimento alimentoViernes2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoViernes2());
        Comida comidaViernes1 = new Comida();
        comidaViernes1.setAlimento(alimentoViernes1);
        comidaViernes1.setCantidad(dietaRecomendadaForm.getCantidadViernes1());
        comidaViernes1.setObservaciones(dietaRecomendadaForm.getObservacionesViernes1());
        Comida comidaViernes2 = new Comida();
        comidaViernes2.setAlimento(alimentoViernes2);
        comidaViernes2.setCantidad(dietaRecomendadaForm.getCantidadViernes2());
        comidaViernes2.setObservaciones(dietaRecomendadaForm.getObservacionesViernes2());
        Set<Comida> comidasViernes = new HashSet<Comida>();
        comidasViernes.add(comidaViernes1);
        comidasViernes.add(comidaViernes2);
        MomentoDia momentoDiaViernes1 = new MomentoDia();
        momentoDiaViernes1.setComidas(comidasViernes);
        momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasViernes = new HashSet<>(0);
        momentoDiasViernes.add(momentoDiaJueves1);

        DiaDieta diaDietaViernes = new DiaDieta();
        diaDietaViernes.setMomentosDia(momentoDiasViernes);
        diaDietaViernes.setNombreDiaDieta(DiaDieta.Dias.VIERNES_1);

        //Sabado
        Alimento alimentoSabado1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoSabado1());
        Alimento alimentoSabado2 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoSabado2());
        Comida comidaSabado1 = new Comida();
        comidaSabado1.setAlimento(alimentoSabado1);
        comidaSabado1.setCantidad(dietaRecomendadaForm.getCantidadSabado1());
        comidaSabado1.setObservaciones(dietaRecomendadaForm.getObservacionesSabado1());
        Comida comidaSabado2 = new Comida();
        comidaSabado2.setAlimento(alimentoSabado2);
        comidaSabado2.setCantidad(dietaRecomendadaForm.getCantidadSabado2());
        comidaSabado2.setObservaciones(dietaRecomendadaForm.getObservacionesSabado2());
        Set<Comida> comidasSabado = new HashSet<Comida>();
        comidasSabado.add(comidaSabado1);
        comidasSabado.add(comidaSabado2);
        MomentoDia momentoDiaSabado1 = new MomentoDia();
        momentoDiaSabado1.setComidas(comidasSabado);
        momentoDiaSabado1.setNombre(MomentoDia.MomentosDia.DESAYUNO);

        Set<MomentoDia> momentoDiasSabado = new HashSet<>(0);
        momentoDiasSabado.add(momentoDiaSabado1);

        DiaDieta diaDietaSabado = new DiaDieta();
        diaDietaSabado.setMomentosDia(momentoDiasSabado);
        diaDietaSabado.setNombreDiaDieta(DiaDieta.Dias.SABADO_1);

        //Aca seteo los dias!!!
        Set<DiaDieta> diaDietas = new HashSet<>(0);
        diaDietas.add(diaDieta);
        diaDietas.add(diaDietaMartes);
        diaDietas.add(diaDietaMiercoles);
        diaDietas.add(diaDietaJueves);
        diaDietas.add(diaDietaViernes);
        diaDietas.add(diaDietaSabado);

        Dieta dieta = new Dieta();
        dieta.setDiasDieta(diaDietas);
        dieta.setPaciente(pacienteManager.getPacienteByUsername(dietaRecomendadaForm.getDni()));
        dieta.setDescripcion("");

        dietaManager.saveDieta(dieta);

        return success;
    }
}
