
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.converter;

import com.se201.pz.dao.VrstaZivotinjaDao;
import com.se201.pz.entity.VrstaZivotinja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Component
final class IntegerToVrstaZivotinja implements Converter<String , VrstaZivotinja> {

    @Autowired
    VrstaZivotinjaDao vrstaZivotinjaDao;

    @Override
    public VrstaZivotinja convert(String  s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        VrstaZivotinja cat = vrstaZivotinjaDao.getVrstaZivotinjaById(valeu);
        return cat;
    }
}
