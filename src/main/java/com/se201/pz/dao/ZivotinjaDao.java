/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao;

import com.se201.pz.entity.Zivotinja;
import java.util.List;

/**
 *
 * @author Nikola Kuburovic 1095
 */
public interface ZivotinjaDao {
    public List<Zivotinja> getListaZivotinja();
    public Zivotinja dodajZivotinju(Zivotinja zivotinja);
    public Zivotinja getZivotinjaById(Integer id);
    public boolean deleteZivotinja(Zivotinja zivotinja);
}
