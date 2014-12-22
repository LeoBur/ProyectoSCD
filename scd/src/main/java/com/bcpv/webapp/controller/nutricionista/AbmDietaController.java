package com.bcpv.webapp.controller.nutricionista;

import com.bcpv.model.Alimento;
import com.bcpv.model.Comida;
import com.bcpv.model.DiaDieta;
import com.bcpv.model.Dieta;
import com.bcpv.model.MomentoDia;
import com.bcpv.service.AlimentoManager;
import com.bcpv.service.DietaManager;
import com.bcpv.service.PacienteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bcpv.webapp.controller.BaseFormController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    public ModelAndView showForm() {
        ModelAndView mv = new ModelAndView("paciente/registrar");

        Alimento alimento = alimentoManager.getAlimento((long) 2);
        Comida comida = new Comida();
        comida.setAlimento(alimento);
        comida.setCantidad("5");
        comida.setObservaciones("De pollo");
        Set<Comida> comidaSet = new HashSet<>(0);
        comidaSet.add(comida);
        MomentoDia momento = new MomentoDia();
        momento.setComidas(comidaSet);
        momento.setNombre(MomentoDia.MomentosDia.ALMUERZO);
        Set<MomentoDia> momentoDiaSet = new HashSet<>(0);
        momentoDiaSet.add(momento);
        DiaDieta diaDieta = new DiaDieta();
        diaDieta.setMomentosDia(momentoDiaSet);
        diaDieta.setNombreDiaDieta(DiaDieta.Dias.DOMINGO_1);
        Set<DiaDieta> diaDietaSet = new HashSet<>(0);
        diaDietaSet.add(diaDieta);

        Dieta dieta = new Dieta();
        dieta.setDescripcion("Primer dieta");
        dieta.setFechaAlta(new Date());
        dieta.setDiasDieta(diaDietaSet);
        dieta.setPaciente(pacienteManager.getPacienteByUsername("ALGUN@MAIL.COM"));

        dietaManager.saveDieta(dieta);
        return mv;
    }
}
