package com.bcpv.webapp.controller;

import com.bcpv.model.Localidad;
import com.bcpv.service.LocalidadManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Leo on 13/11/2014.
 */

@Controller
public class UtilsController {

    @Autowired
    LocalidadManager localidadManager;

    @RequestMapping(method = RequestMethod.GET, value = "/getLocalidades")
    @ResponseBody
    public String getLocalidades( @RequestParam("provincia") String provincia) {
        return localidades(provincia);
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
