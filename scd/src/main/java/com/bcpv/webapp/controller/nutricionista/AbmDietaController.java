package com.bcpv.webapp.controller.nutricionista;

import com.bcpv.model.*;
import com.bcpv.service.AlimentoManager;
import com.bcpv.service.DietaManager;
import com.bcpv.service.PacienteManager;
import com.bcpv.webapp.controller.forms.DietaRecomendadaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AbmDietaController extends BaseFormController{

    @Autowired
    AlimentoManager alimentoManager;

    @Autowired
    PacienteManager pacienteManager;

    @Autowired
    DietaManager dietaManager;

    private static Set<DiaDieta> diaDietasTotal;

	public AbmDietaController(){
	}

    @RequestMapping(value = "nutricionista/dietaList*", method = RequestMethod.GET)
    public ModelAndView showTratamientos(final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        Paciente paciente = pacienteManager.getPaciente(new Long(search));
        Set<Dieta> dietas = paciente.getDietas();
        if (dietas.isEmpty()) {
            saveMessage(request, "El paciente no tiene dietas, ingrese uno nuevo");
        }
        ModelAndView mv = new ModelAndView("nutricionista/dietaRecomendadaList");
        mv.addObject("dietaList", dietas);
        mv.addObject("username", paciente.getPersona().getUsername());
        mv.addObject("paciente", paciente.getPersona().getFullName());
        return mv;
    }

    @RequestMapping(value = "nutricionista/dietas*", method = RequestMethod.GET)
    public ModelAndView showDietas(final HttpServletRequest request, @RequestParam(required=true, value="id") String search) {
        Set<Dieta> dietas = dietaManager.getDieta(new Long(search)).getDietas();
        if (dietas.isEmpty()) {
            ModelAndView mv = new ModelAndView("nutricionista/pacienteList");
            saveInfo(request, "No existen dietas");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("nutricionista/dietas");
            Dieta dieta= dietaManager.getDieta(new Long(search));
            Set<DietaParaMostrar> lunes = new HashSet<>();
            Set<DietaParaMostrar> martes = new HashSet<>();
            Set<DietaParaMostrar> miercoles = new HashSet<>();
            Set<DietaParaMostrar> jueves = new HashSet<>();
            Set<DietaParaMostrar> viernes = new HashSet<>();
            Set<DietaParaMostrar> sabado = new HashSet<>();
            int i = 0;
                for (i = 0; i < dietas.size(); i++) {
                    Iterator<DiaDieta> dias = dieta.getDiasDieta().iterator();
                    while (dias.hasNext()) {
                            DiaDieta e = dias.next();

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
            mv.addObject("lunesList", lunes);
            mv.addObject("martesList", martes);
            mv.addObject("miercolesList", miercoles);
            mv.addObject("juevesList", jueves);
            mv.addObject("viernesList", viernes);
            mv.addObject("sabadoList", sabado);

            mv.addObject("idDieta", dieta.getPaciente().getPersona().getDni());
            mv.addObject("fechaAlta", dieta.getFechaAlta());
            mv.addObject("paciente", dieta.getPaciente().getPersona().getFullName());
            return mv;
        }
    }

    @RequestMapping(value = "/nutricionista/editDieta*", method = RequestMethod.GET)
    public ModelAndView editDieta(final HttpServletRequest request, @RequestParam(required=false, value="search") String search) {
        ModelAndView mv = new ModelAndView("nutricionista/editDieta");
        Dieta dieta = dietaManager.getDieta(new Long(search));
        DietaRecomendadaForm dietaRecomendadaForm = new DietaRecomendadaForm();
        Iterator<DiaDieta> diaDieta = dieta.getDiasDieta().iterator();
        List<DiaDieta> lunesDiaDieta = new ArrayList<>();
        List<DiaDieta> martesDiaDieta = new ArrayList<>();
        List<DiaDieta> miercolesDiaDieta = new ArrayList<>();
        List<DiaDieta> juevesDiaDieta = new ArrayList<>();
        List<DiaDieta> viernesDiaDieta = new ArrayList<>();
        List<DiaDieta> sabadoDiaDieta = new ArrayList<>();

        while (diaDieta.hasNext()){
            DiaDieta e = diaDieta.next();
            if (e.getNombreDiaDieta().name().equals("LUNES_1")){
                lunesDiaDieta.add(e);
            } else if (e.getNombreDiaDieta().name().equals("MARTES_1")){
                martesDiaDieta.add(e);
            } else if (e.getNombreDiaDieta().name().equals("MIERCOLES_1")){
                miercolesDiaDieta.add(e);
            } else if (e.getNombreDiaDieta().name().equals("JUEVES_1")){
                juevesDiaDieta.add(e);
            } else if (e.getNombreDiaDieta().name().equals("VIERNES_1")){
                viernesDiaDieta.add(e);
            } else {
                sabadoDiaDieta.add(e);
            }
        }

        dietaRecomendadaForm.setName(dieta.getPaciente().getPersona().getUsername());
        List<Alimento> alimentos = alimentoManager.getAlimentos();
        String options = "";
        for (Alimento ali : alimentos) {
            options = options + "<option value=\""+ali.getNombre()+"\">"+ali.getNombre()+"</option>";
        }
        List<String> momentoDias = new ArrayList<>();
        momentoDias.add("DESAYUNO");
        momentoDias.add("MEDIA_MANIANA");
        momentoDias.add("ALMUERZO");
        momentoDias.add("MEDIA_TARDE");
        momentoDias.add("CENA");
        momentoDias.add("ANTES_DE_ACOSTARSE");
        String optionsMomentos = "";
        for (String momentosDias: momentoDias) {
            optionsMomentos = optionsMomentos + "<option value=\""+momentosDias.toString()+"\">"+momentosDias.toString()+"</option>";
        }

        mv.addObject("dietaRecomendadaForm", dieta);
        mv.addObject("lunes", lunesDiaDieta);
        mv.addObject("martes", martesDiaDieta);
        mv.addObject("miercoles", miercolesDiaDieta);
        mv.addObject("jueves", juevesDiaDieta);
        mv.addObject("viernes", viernesDiaDieta);
        mv.addObject("sabado", sabadoDiaDieta);
        mv.addObject("options", options);
        mv.addObject("optionsMomentos", optionsMomentos);
        mv.addObject("alimentoList", alimentos);
        mv.addObject("momentoDias", momentoDias);
        return mv;
    }

    @RequestMapping(value = "/nutricionista/dieta*", method = RequestMethod.GET)
    public ModelAndView showForm(@RequestParam(required=false, value="username") String username) {
        ModelAndView mv = new ModelAndView("nutricionista/dietaRecomendada");
        DietaRecomendadaForm dietaRecomendadaForm = new DietaRecomendadaForm();
        dietaRecomendadaForm.setName(username);
        List<Alimento> alimentos = alimentoManager.getAlimentos();
        String options = "";
        for (Alimento ali : alimentos) {
            options = options + "<option value=\""+ali.getNombre()+"\">"+ali.getNombre()+"</option>";
        }
        List<String> momentoDias = new ArrayList<>();
        momentoDias.add("DESAYUNO");
        momentoDias.add("MEDIA_MANIANA");
        momentoDias.add("ALMUERZO");
        momentoDias.add("MEDIA_TARDE");
        momentoDias.add("CENA");
        momentoDias.add("ANTES_DE_ACOSTARSE");
        String optionsMomentos = "";
        for (String momentosDias: momentoDias) {
            optionsMomentos = optionsMomentos + "<option value=\""+momentosDias.toString()+"\">"+momentosDias.toString()+"</option>";
        }

        mv.addObject("options", options);
        mv.addObject("optionsMomentos", optionsMomentos);
        mv.addObject("alimentoList", alimentos);
        mv.addObject("momentoDias", momentoDias);
        mv.addObject("dietaRecomendadaForm", dietaRecomendadaForm);
        return mv;
    }

    @RequestMapping(value = "/nutricionista/dieta*", method = RequestMethod.POST)
    public  String onSubmit(@ModelAttribute("DietaRecomendadForm") DietaRecomendadaForm dietaRecomendadaForm, @RequestParam MultiValueMap<String, String> params, BindingResult errors,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        String success = "redirect:pacienteList";;
        diaDietasTotal = new HashSet<>();
        Locale locale = request.getLocale();
        DateFormat format = new SimpleDateFormat("DD/MM/yyyy", locale);

        if (request.getParameter("cancel") != null) {
            //return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(dietaRecomendadaForm, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                //return "/paciente/registrar";
            }
        }

        List<Dieta> dietas = new ArrayList<>();
        dietas = dietaManager.getDietas();
        int k;
        Date fechaInicio = null;
        Calendar calendario = Calendar.getInstance();
        String formatin="MM";
        String anio="YYYY";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatin);
        SimpleDateFormat anioFormat = new SimpleDateFormat(anio);
        int añoActual = Integer.parseInt(anioFormat.format(dietaRecomendadaForm.getFechaAlta().getTime()));
        int mesActual = Integer.parseInt(dateFormat.format(calendario.getTime()));
        boolean flag = false;

        for (k = 0; k < dietas.size(); k++){
            fechaInicio = dietas.get(k).getFechaAlta();
            calendario.setTime(fechaInicio);
            int añoDB = Integer.parseInt(anioFormat.format(dietas.get(k).getFechaAlta()));
            int mesDB = Integer.parseInt(dateFormat.format(dietas.get(k).getFechaAlta()));
            if (añoActual == añoDB && mesActual == mesDB && dietas.get(k).getPaciente().getPersona().getUsername().equals(dietaRecomendadaForm.getName())){
                flag = true;
                break;
            }
        }

        if (flag){
            saveInfo(request, "Existe dieta cargada para este mes. Comuniquese con su nutricionista...");
            return success;
        }
        //Lunes
        Alimento alimentoLunes1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoLunes1());
        Comida comidaLunes1 = new Comida();
        comidaLunes1.setAlimento(alimentoLunes1);
        comidaLunes1.setCantidad(dietaRecomendadaForm.getCantidadLunes1());
        comidaLunes1.setObservaciones(dietaRecomendadaForm.getObservacionesLunes1());
        Set<Comida> comidasLunes = new HashSet<Comida>();
        comidasLunes.add(comidaLunes1);
        MomentoDia momentoDiaLunes1 = new MomentoDia();
        momentoDiaLunes1.setComidas(comidasLunes);
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("DESAYUNO")) {
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("MEDIA_MANIANA")){
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("ALMUERZO")){
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("MEDIA_TARDE")){
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("CENA")){
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoLunes1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaLunes1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }
        Set<MomentoDia> momentoDiasLunes = new HashSet<>(0);
        momentoDiasLunes.add(momentoDiaLunes1);

        DiaDieta diaDietaLunes = new DiaDieta();
        diaDietaLunes.setMomentosDia(momentoDiasLunes);
        diaDietaLunes.setNombreDiaDieta(DiaDieta.Dias.LUNES_1);
        diaDietasTotal.add(diaDietaLunes);

        if (params.containsKey("nombreAlimentoLunes[]")) {
            List<String> nombres = params.get("nombreAlimentoLunes[]");
            List<String> momentos = params.get("momentoAlimentoLunes[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 2; j < 10; j++){
                if (params.containsKey("observacionAlimentoLunes"+j)) {
                    observaciones.add(params.get("observacionAlimentoLunes" + j).get(0));
                }
            }
            for (int j = 2; j < 10; j++){
                if (params.containsKey("observacionAlimentoLunes"+j)) {
                    cantidades.add(params.get("cantidadAlimentoLunes" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoLunes = alimentoManager.getByNombre(params.get("nombreAlimentoLunes[]").get(index));
                Comida comidasDia = agregarComida(alimentoLunes, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.LUNES_1);
                diaDietasTotal.add(diaDieta1);
            }
        }
        //Martes
        Alimento alimentoMartes1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMartes1());
        Comida comidaMartes1 = new Comida();
        comidaMartes1.setAlimento(alimentoMartes1);
        comidaMartes1.setCantidad(dietaRecomendadaForm.getCantidadMartes1());
        comidaMartes1.setObservaciones(dietaRecomendadaForm.getObservacionesMartes1());
        Set<Comida> comidasMartes = new HashSet<Comida>();
        comidasMartes.add(comidaMartes1);
        MomentoDia momentoDiaMartes1 = new MomentoDia();
        momentoDiaMartes1.setComidas(comidasMartes);
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("DESAYUNO")) {
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("MEDIA_MANIANA")){
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("ALMUERZO")){
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("MEDIA_TARDE")){
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("CENA")){
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoMartes1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaMartes1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }

        Set<MomentoDia> momentoDiasMartes = new HashSet<>(0);
        momentoDiasMartes.add(momentoDiaMartes1);

        DiaDieta diaDietaMartes = new DiaDieta();
        diaDietaMartes.setMomentosDia(momentoDiasMartes);
        diaDietaMartes.setNombreDiaDieta(DiaDieta.Dias.MARTES_1);
        diaDietasTotal.add(diaDietaMartes);

        if (params.containsKey("nombreAlimentoMartes[]")) {
            List<String> nombres = params.get("nombreAlimentoMartes[]");
            List<String> momentos = params.get("momentoAlimentoMartes[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 11; j < 20; j++){
                if (params.containsKey("observacionAlimentoMartes"+j)) {
                    observaciones.add(params.get("observacionAlimentoMartes" + j).get(0));
                }
            }
            for (int j = 11; j < 20; j++){
                if (params.containsKey("cantidadAlimentoMartes"+j)) {
                    cantidades.add(params.get("cantidadAlimentoMartes" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoMartes = alimentoManager.getByNombre(params.get("nombreAlimentoMartes[]").get(index));
                Comida comidasDia = agregarComida(alimentoMartes, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.JUEVES_2);
                diaDietasTotal.add(diaDieta1);
            }
        }
        //Miercoles
        Alimento alimentoMiercoles1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoMiercoles1());
        Comida comidaMiercoles1 = new Comida();
        comidaMiercoles1.setAlimento(alimentoMiercoles1);
        comidaMiercoles1.setCantidad(dietaRecomendadaForm.getCantidadMiercoles1());
        comidaMiercoles1.setObservaciones(dietaRecomendadaForm.getObservacionesMiercoles1());
        Set<Comida> comidasMiercoles = new HashSet<Comida>();
        comidasMiercoles.add(comidaMiercoles1);
        MomentoDia momentoDiaMiercoles1 = new MomentoDia();
        momentoDiaMiercoles1.setComidas(comidasMiercoles);
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("DESAYUNO")) {
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("MEDIA_MANIANA")){
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("ALMUERZO")){
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("MEDIA_TARDE")){
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("CENA")){
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoMiercoles1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaMiercoles1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }

        Set<MomentoDia> momentoDiasMiercoles = new HashSet<>(0);
        momentoDiasMiercoles.add(momentoDiaMiercoles1);

        DiaDieta diaDietaMiercoles = new DiaDieta();
        diaDietaMiercoles.setMomentosDia(momentoDiasMiercoles);
        diaDietaMiercoles.setNombreDiaDieta(DiaDieta.Dias.MIERCOLES_1);
        diaDietasTotal.add(diaDietaMiercoles);

        if (params.containsKey("nombreAlimentoMiercoles[]")) {
            List<String> nombres = params.get("nombreAlimentoMiercoles[]");
            List<String> momentos = params.get("momentoAlimentoMiercoles[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 21; j < 30; j++){
                if (params.containsKey("observacionAlimentoMiercoles"+j)) {
                    observaciones.add(params.get("observacionAlimentoMiercoles" + j).get(0));
                }
            }
            for (int j = 21; j < 30; j++){
                if (params.containsKey("cantidadAlimentoMiercoles"+j)) {
                    cantidades.add(params.get("cantidadAlimentoMiercoles" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoMiercoles = alimentoManager.getByNombre(params.get("nombreAlimentoMiercoles[]").get(index));
                Comida comidasDia = agregarComida(alimentoMiercoles, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.JUEVES_2);
                diaDietasTotal.add(diaDieta1);
            }
        }
        //Jueves
        Alimento alimentoJueves1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoJueves1());
        Comida comidaJueves1 = new Comida();
        comidaJueves1.setAlimento(alimentoJueves1);
        comidaJueves1.setCantidad(dietaRecomendadaForm.getCantidadJueves1());
        comidaJueves1.setObservaciones(dietaRecomendadaForm.getObservacionesJueves1());
        Set<Comida> comidasJueves = new HashSet<Comida>();
        comidasJueves.add(comidaJueves1);
        MomentoDia momentoDiaJueves1 = new MomentoDia();
        momentoDiaJueves1.setComidas(comidasJueves);
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("DESAYUNO")) {
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("MEDIA_MANIANA")){
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("ALMUERZO")){
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("MEDIA_TARDE")){
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("CENA")){
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoJueves1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaJueves1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }

        Set<MomentoDia> momentoDiasJueves = new HashSet<>(0);
        momentoDiasJueves.add(momentoDiaJueves1);

        DiaDieta diaDietaJueves = new DiaDieta();
        diaDietaJueves.setMomentosDia(momentoDiasJueves);
        diaDietaJueves.setNombreDiaDieta(DiaDieta.Dias.JUEVES_1);
        diaDietasTotal.add(diaDietaJueves);

        if (params.containsKey("nombreAlimentoJueves[]")) {
            List<String> nombres = params.get("nombreAlimentoJueves[]");
            List<String> momentos = params.get("momentoAlimentoJueves[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 31; j < 40; j++){
                if (params.containsKey("observacionAlimentoJueves"+j)) {
                    observaciones.add(params.get("observacionAlimentoJueves" + j).get(0));
                }
            }
            for (int j = 31; j < 40; j++){
                if (params.containsKey("cantidadAlimentoJueves"+j)) {
                    cantidades.add(params.get("cantidadAlimentoJueves" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoJueves = alimentoManager.getByNombre(params.get("nombreAlimentoJueves[]").get(index));
                Comida comidasDia = agregarComida(alimentoJueves, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.JUEVES_2);
                diaDietasTotal.add(diaDieta1);
            }
        }

        //Viernes
        Alimento alimentoViernes1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoViernes1());
        Comida comidaViernes1 = new Comida();
        comidaViernes1.setAlimento(alimentoViernes1);
        comidaViernes1.setCantidad(dietaRecomendadaForm.getCantidadViernes1());
        comidaViernes1.setObservaciones(dietaRecomendadaForm.getObservacionesViernes1());
        Set<Comida> comidasViernes = new HashSet<Comida>();
        comidasViernes.add(comidaViernes1);
        MomentoDia momentoDiaViernes1 = new MomentoDia();
        momentoDiaViernes1.setComidas(comidasViernes);
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("DESAYUNO")) {
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("MEDIA_MANIANA")){
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("ALMUERZO")){
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("MEDIA_TARDE")){
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("CENA")){
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoViernes1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaViernes1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }

        Set<MomentoDia> momentoDiasViernes = new HashSet<>(0);
        momentoDiasViernes.add(momentoDiaViernes1);

        DiaDieta diaDietaViernes = new DiaDieta();
        diaDietaViernes.setMomentosDia(momentoDiasViernes);
        diaDietaViernes.setNombreDiaDieta(DiaDieta.Dias.VIERNES_1);
        diaDietasTotal.add(diaDietaViernes);

        if (params.containsKey("nombreAlimentoViernes[]")) {
            List<String> nombres = params.get("nombreAlimentoViernes[]");
            List<String> momentos = params.get("momentoAlimentoViernes[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 41; j < 50; j++){
                if (params.containsKey("observacionAlimentoViernes"+j)) {
                    observaciones.add(params.get("observacionAlimentoViernes" + j).get(0));
                }
            }
            for (int j = 41; j < 50; j++){
                if (params.containsKey("cantidadAlimentoViernes"+j)) {
                    cantidades.add(params.get("cantidadAlimentoViernes" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoViernes = alimentoManager.getByNombre(params.get("nombreAlimentoViernes[]").get(index));
                Comida comidasDia = agregarComida(alimentoViernes, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.VIERNES_1);
                diaDietasTotal.add(diaDieta1);
            }
        }
        //Sabado
        Alimento alimento1 = alimentoManager.getByNombre(dietaRecomendadaForm.getNombreAlimentoSabado1());
        Comida comidaSabados1 = new Comida();
        comidaSabados1.setAlimento(alimento1);
        comidaSabados1.setCantidad(dietaRecomendadaForm.getCantidadSabado1());
        comidaSabados1.setObservaciones(dietaRecomendadaForm.getObservacionesSabado1());

        Set<Comida> comidasSabados = new HashSet<Comida>();
        comidasSabados.add(comidaSabados1);
        //comidasSabados.add(comidaLunes2);
        MomentoDia momentoDiaSabados1 = new MomentoDia();
        momentoDiaSabados1.setComidas(comidasSabados);
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("DESAYUNO")) {
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("MEDIA_MANIANA")){
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("ALMUERZO")){
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("MEDIA_TARDE")){
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("CENA")){
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (dietaRecomendadaForm.getMomentoSabado1().name().equals("ANTES_DE_ACOSTARSE")){
            momentoDiaSabados1.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }

        Set<MomentoDia> momentoDiasSabados = new HashSet<>(0);
        momentoDiasSabados.add(momentoDiaSabados1);

        DiaDieta diaDieta = new DiaDieta();
        diaDieta.setMomentosDia(momentoDiasSabados);
        diaDieta.setNombreDiaDieta(DiaDieta.Dias.SABADO_1);
        diaDietasTotal.add(diaDieta);
        if (params.containsKey("nombreAlimentoSabado[]")) {
            List<String> nombres = params.get("nombreAlimentoSabado[]");
            List<String> momentos = params.get("momentoAlimentoSabado[]");
            //List<String> cantidades = params.get("cantidadAlimentoLunes[]");
            List<String> cantidades = new ArrayList<>();
            //List<String> observaciones = params.get("observacionAlimentoLunes[]");
            List<String> observaciones = new ArrayList<>();
            for (int j = 51; j < 60; j++){
                if (params.containsKey("observacionAlimentoSabado"+j)) {
                    observaciones.add(params.get("observacionAlimentoSabado" + j).get(0));
                }
            }
            for (int j = 51; j < 60; j++){
                if (params.containsKey("cantidadAlimentoSabado"+j)) {
                    cantidades.add(params.get("cantidadAlimentoSabado" + j).get(0));
                }
            }

            int i = nombres.size();
            for (int index = 0; index<i; index++) {
                Alimento alimentoSabado1 = alimentoManager.getByNombre(params.get("nombreAlimentoSabado[]").get(index));
                Comida comidasDia = agregarComida(alimentoSabado1, cantidades.get(index), observaciones.get(index));
                Set<Comida> comidas = agregarComidasDia(comidasDia);
                MomentoDia momentoDia = agregarMomentoDia(comidas, momentos.get(index));
                Set<MomentoDia> momentoDias = agregarMomentosDia(momentoDia);
                DiaDieta diaDieta1 = agregarDiaDieta(momentoDias, DiaDieta.Dias.SABADO_1);
                diaDietasTotal.add(diaDieta1);
            }
        }

        Dieta dieta = new Dieta();
        dieta.setDiasDieta(diaDietasTotal);
        dieta.setPaciente(pacienteManager.getPacienteByUsername(dietaRecomendadaForm.getName()));
        dieta.setDescripcion("");
        dieta.setFechaAlta(dietaRecomendadaForm.getFechaAlta());
        //dieta.setFechaHasta(dietaRecomendadaForm.getFechaHasta());

        dietaManager.saveDieta(dieta);

        return success;
    }

    private Comida agregarComida(Alimento alimento, String cantidad, String observaciones){
        Comida comidaDia = new Comida();
        comidaDia.setAlimento(alimento);
        comidaDia.setCantidad(cantidad);
        comidaDia.setObservaciones(observaciones);
        return comidaDia;
    }

    private Set<Comida> agregarComidasDia(Comida comidasDia) {
        Set<Comida> comidasDias = new HashSet<Comida>();
        comidasDias.add(comidasDia);
        return comidasDias;
    }

    private MomentoDia agregarMomentoDia (Set<Comida> comidasDia, String momento){
        MomentoDia momentoDia = new MomentoDia();
        momentoDia.setComidas(comidasDia);
        if (momento.equals("DESAYUNO")){
            momentoDia.setNombre(MomentoDia.MomentosDia.DESAYUNO);
        }
        if (momento.equals("MEDIA_MANIANA")){
            momentoDia.setNombre(MomentoDia.MomentosDia.MEDIA_MANIANA);
        }
        if (momento.equals("ALMUERZO")){
            momentoDia.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        }
        if (momento.equals("MEDIA_TARDE")){
            momentoDia.setNombre(MomentoDia.MomentosDia.MEDIA_TARDE);
        }
        if (momento.equals("CENA")){
            momentoDia.setNombre(MomentoDia.MomentosDia.CENA);
        }
        if (momento.equals("ANTES_DE_ACOSTARSE")){
            momentoDia.setNombre(MomentoDia.MomentosDia.ANTES_DE_ACOSTARSE);
        }
        return momentoDia;
    }

    private Set<MomentoDia> agregarMomentosDia (MomentoDia momentoDia) {
        Set<MomentoDia> momentosDias = new HashSet<>(0);
        momentosDias.add(momentoDia);
        return momentosDias;
    }

    private DiaDieta agregarDiaDieta (Set<MomentoDia> momentosDias, DiaDieta.Dias unDia) {
        DiaDieta diaDieta = new DiaDieta();
        diaDieta.setMomentosDia(momentosDias);
        diaDieta.setNombreDiaDieta(unDia);
        return diaDieta;
    }

    //falta setear los dias

    private void saveDietaTotal (Set<DiaDieta> diaDietas, Paciente paciente, String descripcion) {
        Dieta dieta = new Dieta();
        dieta.setDiasDieta(diaDietas);
        dieta.setPaciente(paciente);
        dieta.setDescripcion(descripcion);

        dietaManager.saveDieta(dieta);
    }
}
