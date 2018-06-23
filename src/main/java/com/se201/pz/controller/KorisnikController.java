/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.controller;

import com.se201.pz.dao.KorisnikDao;
import com.se201.pz.entity.Korisnik;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Controller
public class KorisnikController {


    @Autowired
    KorisnikDao korisnikDao;

    @RequestMapping(value = "/add_korisnik", method = RequestMethod.GET)
    public String addKorisnik(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        List korisnici = korisnikDao.getListaKorisnika();
        
        model.addAttribute("korisnici", korisnici);
        
        return "add_korisnik";
    }

    @RequestMapping(value = "/add_korisnik", method = RequestMethod.POST)
    public ModelAndView addKorisnikPost(@ModelAttribute("korisnik") Korisnik korisnik, BindingResult result, ModelAndView model) {
        
        korisnik = korisnikDao.dodajKorisnika(korisnik);

        model.addObject("korisnik", new Korisnik());
        model.addObject("korisnici", korisnikDao.getListaKorisnika());
        model.addObject("successMsg", "Korisnik uspešno dodat");
        return model;
    }

    @RequestMapping(value = "/edit_korisnik/{id}", method = RequestMethod.GET)
    public String editKorisnik(@PathVariable("id") int id, Model model) {
        Korisnik korisnik = korisnikDao.getKorisnikById(id);
        model.addAttribute("korisnik", korisnik);
        List korisnici = korisnikDao.getListaKorisnika();
        model.addAttribute("korisnici", korisnici);
        return "add_korisnik";
    }


    @RequestMapping(value = "/delete_korisnik/{id}", method = RequestMethod.GET)
    public String deleteZivotinja(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Korisnik korisnik = korisnikDao.getKorisnikById(id);
        if (korisnik == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        korisnikDao.deleteKorisnik(korisnik);
        String referer = request.getHeader("Referer");
        model.addAttribute("successMsg", "Korisnik uspešno obrisan");
        return "redirect:" + referer;
    }
    
    @RequestMapping(value = "/korisnici", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Korisnik>> korisniciRest() {
        List<Korisnik> korisnici = korisnikDao.getListaKorisnika();
        System.out.println("*******Ovo su korisnici: " + korisnici);
        if (korisnici.isEmpty()) {
            System.out.println("Lista korisnika je prazna");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }
  
}
