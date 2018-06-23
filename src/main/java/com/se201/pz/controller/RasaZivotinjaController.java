/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.controller;

import com.se201.pz.dao.RasaZivotinjaDao;
import com.se201.pz.dao.VrstaZivotinjaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.se201.pz.entity.RasaZivotinja;
import com.se201.pz.entity.VrstaZivotinja;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Controller
public class RasaZivotinjaController {

    @Autowired
    RasaZivotinjaDao rasaZivotinjaDao;
    
    @Autowired
    VrstaZivotinjaDao vrstaZivotinjaDao;


    @RequestMapping(value = "/add_rasazivotinja", method = RequestMethod.GET)
    public String addRasaZivotinja(Model model) {
        model.addAttribute("rasa_zivotinja", new RasaZivotinja());
        List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        model.addAttribute("rase_zivotinja", rase_zivotinja);
        return "add_rasazivotinja";
    }

    @RequestMapping(value = "/add_rasazivotinja", method = RequestMethod.POST)
    public ModelAndView addRasaZivotinjaPost(@ModelAttribute("rasa_zivotinja") RasaZivotinja rasa_zivotinja, ModelAndView model) {
        System.out.println(">>>>> Ovo su dobijene rase zivotinja: " + rasa_zivotinja.getVrstaZivotinjaId());
        rasa_zivotinja = rasaZivotinjaDao.dodajRasaZivotinja(rasa_zivotinja);
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addObject("rase_zivotinja", rasaZivotinjaDao.getListaRasaZivotinja());
        model.addObject("successMsg", "Rase zivotinje uspešno dodata");
        model.addObject("rasa_zivotinja", new RasaZivotinja());
        model.addObject("vrste_zivotinja", vrste_zivotinja);
        return model;
    }

    @RequestMapping(value = "/edit_rasazivotinja/{id}", method = RequestMethod.GET)
    public String editRasaZivotinja(@PathVariable("id") int id, Model model) {
        RasaZivotinja rasaZivotinja = rasaZivotinjaDao.getRasaZivotinjaById(id);
        model.addAttribute("rasa_zivotinja", rasaZivotinja);
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        List mesta = rasaZivotinjaDao.getListaRasaZivotinja();
        model.addAttribute("rase_zivotinja", rasaZivotinjaDao.getListaRasaZivotinja());
        return "add_rasazivotinja";
    }


 
    @RequestMapping(value = "/delete_rasazivotinja/{id}", method = RequestMethod.GET)
    public String deleteRasaZivotinja(@PathVariable("id") int id, HttpServletRequest request) {
        RasaZivotinja rasaZivotinja = rasaZivotinjaDao.getRasaZivotinjaById(id);
        if (rasaZivotinja == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        rasaZivotinjaDao.deleteRasaZivotinja(rasaZivotinja);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
