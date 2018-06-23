/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.controller;

import com.se201.pz.dao.KorisnikDao;
import com.se201.pz.dao.RasaZivotinjaDao;
import com.se201.pz.dao.VrstaZivotinjaDao;
import com.se201.pz.dao.ZivotinjaDao;
import com.se201.pz.entity.Korisnik;
import com.se201.pz.entity.RasaZivotinja;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.se201.pz.entity.Zivotinja;
import java.security.Principal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Controller
public class ZivotinjaController {

    @Autowired
    ZivotinjaDao zivotinjaDao;

    @Autowired
    RasaZivotinjaDao rasaZivotinjaDao;

    @Autowired
    VrstaZivotinjaDao vrstaZivotinjaDao;

    @Autowired
    KorisnikDao korisnikDao;

    @RequestMapping(value = "/add_zivotinja", method = RequestMethod.GET)
    public String addZivotinja(Model model) {
        model.addAttribute("zivotinja", new Zivotinja());
        List zivotinje = zivotinjaDao.getListaZivotinja();
        List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("zivotinje", zivotinje);
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        model.addAttribute("rase_zivotinja", rase_zivotinja);
        return "add_zivotinja";
    }

    @RequestMapping(value = "/add_zivotinja", method = RequestMethod.POST)
    public ModelAndView addZivotinjaPost(@ModelAttribute("zivotinja") Zivotinja zivotinja, BindingResult result, ModelAndView model) {
        System.out.println(">>>>> Ovo je dobijena zivotinja: " + zivotinja.getRasaZivotinjaId());
        RasaZivotinja rasaZivotinja = rasaZivotinjaDao.getRasaZivotinjaById(zivotinja.getRasaZivotinjaId());
        zivotinja.setRasaZivotinja(rasaZivotinja);
        zivotinja.setDatumKreiranja(new Date());
        zivotinja = zivotinjaDao.dodajZivotinju(zivotinja);
        List zivotinje = zivotinjaDao.getListaZivotinja();
        List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addObject("zivotinja", new Zivotinja());
        model.addObject("zivotinje", zivotinje);
        model.addObject("rase_zivotinja", rase_zivotinja);
        model.addObject("successMsg", "Zivotinja uspešno dodata");
        model.addObject("vrste_zivotinja", vrste_zivotinja);
        return model;
    }

    @RequestMapping(value = "/edit_zivotinja/{id}", method = RequestMethod.GET)
    public String editZivotinja(@PathVariable("id") int id, Model model) {
        Zivotinja zivotinja = zivotinjaDao.getZivotinjaById(id);
        System.out.println("*********** " + zivotinja.getDatumKreiranja());
        model.addAttribute("zivotinja", zivotinja);
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
        model.addAttribute("rase_zivotinja", rase_zivotinja);
        List zivotinje = zivotinjaDao.getListaZivotinja();
        model.addAttribute("zivotinje", zivotinje);
        return "add_zivotinja";
    }


    @RequestMapping(value = "/delete_zivotinja/{id}", method = RequestMethod.GET)
    public String deleteZivotinja(@PathVariable("id") int id, HttpServletRequest request) {
        Zivotinja zivotinja = zivotinjaDao.getZivotinjaById(id);
        if (zivotinja == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        zivotinjaDao.deleteZivotinja(zivotinja);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/lista_zivotinja", method = RequestMethod.GET)
    public String listaZivotinja(Model model) {
        model.addAttribute("zivotinja", new Zivotinja());
        List zivotinje = zivotinjaDao.getListaZivotinja();
        List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
        List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
        model.addAttribute("zivotinje", zivotinje);
        model.addAttribute("vrste_zivotinja", vrste_zivotinja);
        model.addAttribute("rase_zivotinja", rase_zivotinja);
        return "lista_zivotinja";
    }

    @RequestMapping(value = "/prijavi_zivotinja/{id}", method = RequestMethod.GET)
    public String prijaviZivotinja(@PathVariable("id") int id, Model model, Principal principal) {
        Zivotinja zivotinja = zivotinjaDao.getZivotinjaById(id);

        if (zivotinja.getKorisnikId() != null) {
            model.addAttribute("zivotinje", zivotinjaDao.getListaZivotinja());
            List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
            List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
            model.addAttribute("vrste_zivotinja", vrste_zivotinja);
            model.addAttribute("rase_zivotinja", rase_zivotinja);
            model.addAttribute("errorMsg", "Ne mozete rezervisati vec rezervisanu zivotinju");
            return "lista_zivotinja";
        } else {
            Korisnik korisnik = korisnikDao.getKorisnikByUsername(principal.getName());
            zivotinja.setKorisnikId(korisnik);
            zivotinjaDao.dodajZivotinju(zivotinja);
            List zivotinje = zivotinjaDao.getListaZivotinja();
            List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
            List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
            model.addAttribute("zivotinje", zivotinje);
            model.addAttribute("vrste_zivotinja", vrste_zivotinja);
            model.addAttribute("rase_zivotinja", rase_zivotinja);
            model.addAttribute("successMsg", "Uspesno ste rezervisali zivotinju");
            return "lista_zivotinja";
        }
    }

    @RequestMapping(value = "/odjavi_zivotinja/{id}", method = RequestMethod.GET)
    public String odjaviZivotinja(@PathVariable("id") int id, Model model, Principal principal) {
        Zivotinja zivotinja = zivotinjaDao.getZivotinjaById(id);
        if (zivotinja.getKorisnikId() != null) {
            if (!zivotinja.getKorisnikId().getUsername().equals(principal.getName())) {
                model.addAttribute("zivotinje", zivotinjaDao.getListaZivotinja());
                List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
                List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
                model.addAttribute("vrste_zivotinja", vrste_zivotinja);
                model.addAttribute("rase_zivotinja", rase_zivotinja);
                model.addAttribute("errorMsg", "Ne mozete odustati od zivotinje koju niste rezervisali");
                return "lista_zivotinja";
            } else{
                zivotinja.setKorisnikId(null);
                zivotinjaDao.dodajZivotinju(zivotinja);
                model.addAttribute("zivotinje", zivotinjaDao.getListaZivotinja());
                List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
                List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
                model.addAttribute("vrste_zivotinja", vrste_zivotinja);
                model.addAttribute("rase_zivotinja", rase_zivotinja);
                model.addAttribute("successMsg", "Uspesno ste ponistili rezervaciju zivotinje");
                return "lista_zivotinja";
            }

        } else {

            List zivotinje = zivotinjaDao.getListaZivotinja();
            List rase_zivotinja = rasaZivotinjaDao.getListaRasaZivotinja();
            List vrste_zivotinja = vrstaZivotinjaDao.getListaVrstaZivotinja();
            model.addAttribute("zivotinje", zivotinje);
            model.addAttribute("vrste_zivotinja", vrste_zivotinja);
            model.addAttribute("rase_zivotinja", rase_zivotinja);
            model.addAttribute("errorMsg", "Ne mozete odustati od zivotinje koju niste rezervisali");
            return "lista_zivotinja";
        }

    }
}
