/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao;

import com.se201.pz.entity.Korisnik;
import java.util.List;

/**
 *
 * @author Nikola Kuburovic 1095
 */
public interface KorisnikDao {
    public List<Korisnik> getListaKorisnika();
    public Korisnik dodajKorisnika(Korisnik korisnik);
    public Korisnik getKorisnikById(Integer id);
    public Korisnik getKorisnikByUsername(String username);
    public boolean deleteKorisnik(Korisnik korisnik);
}
