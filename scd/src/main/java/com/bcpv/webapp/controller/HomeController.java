package com.bcpv.webapp.controller;

import com.bcpv.Constants;
import com.bcpv.model.Role;
import com.bcpv.model.User;
import com.bcpv.service.RoleManager;
import com.bcpv.service.UserManager;
import com.bcpv.webapp.controller.forms.HomeForm;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Leo on 18/02/2015.
 */

@Controller
public class HomeController {

    @Autowired
    RoleManager roleManager;

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
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
        } else if (request.isUserInRole(Constants.ADMIN_ROLE)) {
            return new ModelAndView("redirect:admin/endocrinologoList");
        } else if (request.isUserInRole(Constants.ENDO_ROLE)) {
            return new ModelAndView("redirect:endos/pacienteList");
        } else if (request.isUserInRole(Constants.NUTRI_ROLE)) {
            return new ModelAndView("redirect:nutricionista/pacienteList");
        } else if (request.isUserInRole(Constants.USER_ROLE)) {
            return new ModelAndView("redirect:paciente/registrar");
        }
        return new ModelAndView("404");
    }

    //@RequestParam MultiValueMap<String, String> params
    @Transactional
    @RequestMapping(value = "/rolePicker*",method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("homeForm") HomeForm homeForm, BindingResult errors,
                           final HttpServletRequest request) {
        if ("" != homeForm.getRoles()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userManager.getUserByUsername(request.getRemoteUser());
            Set<GrantedAuthority> userAuth = user.getAuthorities();
            Role role = roleManager.getRole(homeForm.getRoles());

            Authentication authentication= new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), Arrays.asList(role));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (request.isUserInRole(Constants.ADMIN_ROLE)) {
                return "redirect:admin/endocrinologoList";
            } else if (request.isUserInRole(Constants.ENDO_ROLE)) {
                return "redirect:endos/pacienteList";
            } else if (request.isUserInRole(Constants.NUTRI_ROLE)) {
                return "redirect:nutricionista/pacienteList";
            } else if (request.isUserInRole(Constants.USER_ROLE)) {
                return "redirect:paciente/registrar";
            }
        }
        return "404";
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
