/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao;

import com.se201.pz.entity.RasaZivotinja;
import java.util.List;

/**
 *
 * @author Nikola Kuburovic 1095
 */
public interface RasaZivotinjaDao {
    public List<RasaZivotinja> getListaRasaZivotinja();
    public RasaZivotinja dodajRasaZivotinja(RasaZivotinja rasaZivotinja);
    public RasaZivotinja getRasaZivotinjaById(Integer id);
    public boolean deleteRasaZivotinja(RasaZivotinja rasaZivotinja);
}
