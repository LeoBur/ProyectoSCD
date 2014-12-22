package com.bcpv.webapp.controller;

import com.bcpv.Constants;
import com.bcpv.model.Medicamento;
import com.bcpv.model.Role;
import com.bcpv.model.User;
import com.bcpv.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 21/12/2014.
 */

@Controller
@RequestMapping(value = "/home*")
public class HomeController {

    @Autowired
    UserManager userManager;

    public String showHome(final HttpServletRequest request){
        String username = request.getRemoteUser();
        User user = userManager.getUserByUsername(username);

        Set<String> roles = new HashSet<String>(0);
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        if (roles.contains(Constants.ADMIN_ROLE)) {
            return "/admin/endocrinologoList";
        } else if (roles.contains(Constants.ENDO_ROLE)) {
            return "/endos/newPaciente";
        } else if (roles.contains(Constants.NUTRI_ROLE)) {
            return "/home";
        } else if (roles.contains(Constants.PTRAI_ROLE)) {
            return "/home";
        }
        return "/paciente/registrar";
    }
}
