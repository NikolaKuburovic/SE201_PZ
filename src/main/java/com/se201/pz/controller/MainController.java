/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nikola Kuburovic 1095
 */

@Controller
public class MainController {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    public ModelAndView helloPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Сада си пријављен на апликацију!");
        model.setViewName("hello");
        return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        
        String[] beanNames = applicationContext.getBeanDefinitionNames();
                for (String beanName : beanNames){
                    System.out.println(beanName + "**********>> je ucitan >> *******" + applicationContext.getBean(beanName).getClass().toString());
                }//ovaj kod nije bitan za aplikaciju, jedino sluzi za prikaz ucitanih beanova
        
        System.out.println("Ovo je greska>>>>>>>>>" + error);
        if (error != null) {
            model.addObject("error", "Лоши подаци за пријаву!");
        }
        if (logout != null) {
            model.addObject("msg", "Одјавили сте се.");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
    }
}
