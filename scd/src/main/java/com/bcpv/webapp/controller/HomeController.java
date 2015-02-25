package com.bcpv.webapp.controller;

import com.bcpv.Constants;
import com.bcpv.model.Role;
import com.bcpv.service.RoleManager;
import com.bcpv.webapp.controller.forms.HomeForm;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Created by Leo on 18/02/2015.
 */

@Controller
@RequestMapping("/rolePicker*")
public class HomeController {

    @Autowired
    RoleManager roleManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showRoles(@ModelAttribute("homeForm") HomeForm homeForm,
                                  BindingResult errors, final HttpServletRequest request) {
        if (isAdminAndAnother(request) || isEndoAndAnother(request) ||
                isNutriAndAnother(request) || isPTraiAndAnother(request) ||
                isUserAndAnother(request)) {
            ModelAndView mv = new ModelAndView("rolePicker");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> authorityList = auth.getAuthorities();
            mv.addObject("roleList", authorityList);
            return mv;
        }
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @Transactional
    @RequestMapping(value = "rolePicker*", method = RequestMethod.POST)
    public String onSubmit(@RequestParam MultiValueMap<String, String> params,
                           final HttpServletRequest request) {
        /*if (params.containsKey("roles")) {
            for (Object obj : params.get("roles")) {

            }
        }*/
        if (params.containsKey("roles")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Collection<Role> authorityList = (Collection<Role>) auth.getAuthorities();
            Role role = roleManager.getRole(params.get("roles").get(0));
            authorityList.clear();
            authorityList.add(role);
            return "home";
        }
        return "redirect:/endos/pacienteList";
    }

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    private boolean isAdminAndAnother(final HttpServletRequest request) {
        if (request.isUserInRole(Constants.ADMIN_ROLE) && (request.isUserInRole(Constants.ENDO_ROLE) ||
                request.isUserInRole(Constants.NUTRI_ROLE) || request.isUserInRole(Constants.PTRAI_ROLE) ||
                request.isUserInRole(Constants.USER_ROLE))) {
            return true;
        }
        return false;
    }

    private boolean isEndoAndAnother(final HttpServletRequest request) {
        if (request.isUserInRole(Constants.ENDO_ROLE) && (request.isUserInRole(Constants.ADMIN_ROLE) ||
                request.isUserInRole(Constants.NUTRI_ROLE) || request.isUserInRole(Constants.PTRAI_ROLE) ||
                request.isUserInRole(Constants.USER_ROLE))) {
            return true;
        }
        return false;
    }

    private boolean isNutriAndAnother(final HttpServletRequest request) {
        if (request.isUserInRole(Constants.NUTRI_ROLE) && (request.isUserInRole(Constants.ENDO_ROLE) ||
                request.isUserInRole(Constants.ADMIN_ROLE) || request.isUserInRole(Constants.PTRAI_ROLE) ||
                request.isUserInRole(Constants.USER_ROLE))) {
            return true;
        }
        return false;
    }

    private boolean isPTraiAndAnother(final HttpServletRequest request) {
        if (request.isUserInRole(Constants.PTRAI_ROLE) && (request.isUserInRole(Constants.ENDO_ROLE) ||
                request.isUserInRole(Constants.NUTRI_ROLE) || request.isUserInRole(Constants.ADMIN_ROLE) ||
                request.isUserInRole(Constants.USER_ROLE))) {
            return true;
        }
        return false;
    }

    private boolean isUserAndAnother(final HttpServletRequest request) {
        if (request.isUserInRole(Constants.USER_ROLE) && (request.isUserInRole(Constants.ENDO_ROLE) ||
                request.isUserInRole(Constants.NUTRI_ROLE) || request.isUserInRole(Constants.PTRAI_ROLE) ||
                request.isUserInRole(Constants.ADMIN_ROLE))) {
            return true;
        }
        return false;
    }
}
