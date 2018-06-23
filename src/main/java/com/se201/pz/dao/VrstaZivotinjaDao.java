/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao;

import com.se201.pz.entity.VrstaZivotinja;
import java.util.List;

/**
 *
 * @author Nikola Kuburovic 1095
 */
public interface VrstaZivotinjaDao {
    public List<VrstaZivotinja> getListaVrstaZivotinja();
    public VrstaZivotinja dodajVrstaZivotinja(VrstaZivotinja mesto);
    public VrstaZivotinja getVrstaZivotinjaById(Integer id);
    public boolean deleteVrstaZivotinja(VrstaZivotinja mesto);
}
