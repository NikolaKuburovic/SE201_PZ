/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.converter;

import com.se201.pz.dao.RasaZivotinjaDao;
import com.se201.pz.entity.RasaZivotinja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Component
final class IntegerToRasaZivotinja implements Converter<String , RasaZivotinja> {

    @Autowired
    RasaZivotinjaDao rasaZivotinjaDao;

    @Override
    public RasaZivotinja convert(String  s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        RasaZivotinja cat = rasaZivotinjaDao.getRasaZivotinjaById(valeu);
        return cat;
    }
}
