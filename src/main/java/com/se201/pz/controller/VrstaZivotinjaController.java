/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.se201.pz.dao.VrstaZivotinjaDao;
import com.se201.pz.entity.VrstaZivotinja;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Controller
public class VrstaZivotinjaController {

    @Autowired
    VrstaZivotinjaDao vrstaZivotinjaDao;


    @RequestMapping(value = "/add_vrstazivotinja", method = RequestMethod.GET)
    public String addVrstaZivotinja(Model model) {
        model.addAttribute("vrsta_zivotinje", new VrstaZivotinja());
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        return "add_vrstazivotinja";
    }

    @RequestMapping(value = "/add_vrstazivotinja", method = RequestMethod.POST)
    public ModelAndView addVrstaZivotinjaPost(@ModelAttribute("vrsta_zivotinje") VrstaZivotinja vrsta_zivotinje, ModelAndView model) {
        vrsta_zivotinje = vrstaZivotinjaDao.dodajVrstaZivotinja(vrsta_zivotinje);
        model.addObject("vrste_zivotinja", vrstaZivotinjaDao.getListaVrstaZivotinja());
        model.addObject("successMsg", "Vrsta zivotinje uspešno dodata");
        model.addObject("vrsta_zivotinje", new VrstaZivotinja());
        return model;
    }

    @RequestMapping(value = "/edit_vrstazivotinja/{id}", method = RequestMethod.GET)
    public String editVrstaZivotinja(@PathVariable("id") int id, Model model) {
        VrstaZivotinja vrstaZivotinja = vrstaZivotinjaDao.getVrstaZivotinjaById(id);
        model.addAttribute("vrsta_zivotinje", vrstaZivotinja);
        List mesta = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("vrste_zivotinja", vrstaZivotinjaDao.getListaVrstaZivotinja());
        return "add_vrstazivotinja";
    }


 
    @RequestMapping(value = "/delete_vrstazivotinja/{id}", method = RequestMethod.GET)
    public String deleteVrstaZivotinja(@PathVariable("id") int id, HttpServletRequest request) {
        VrstaZivotinja vrstaZivotinja = vrstaZivotinjaDao.getVrstaZivotinjaById(id);
        if (vrstaZivotinja == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        vrstaZivotinjaDao.deleteVrstaZivotinja(vrstaZivotinja);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
