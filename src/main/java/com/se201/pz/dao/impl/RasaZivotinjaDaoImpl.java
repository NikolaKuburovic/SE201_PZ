/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao.impl;

import com.se201.pz.entity.RasaZivotinja;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.se201.pz.dao.RasaZivotinjaDao;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Repository("rasaZivotinjaDao")
@Service
public class RasaZivotinjaDaoImpl implements RasaZivotinjaDao {

    @SuppressWarnings("unused")
    private final Log logger = LogFactory.getLog(getClass());

    //Instanciramo sesiju
    @Autowired
    private SessionFactory sessionFactory;

    //kreiramo seter za sesiju
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //kreiramo geter za sesiju
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<RasaZivotinja> getListaRasaZivotinja() {
        return getSession().createCriteria(RasaZivotinja.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public RasaZivotinja dodajRasaZivotinja(RasaZivotinja rasaZivotinja) {
        return (RasaZivotinja) getSession().merge(rasaZivotinja);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public RasaZivotinja getRasaZivotinjaById(Integer id) {
        return (RasaZivotinja) getSession().createCriteria(RasaZivotinja.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Transactional
    @Override
    public boolean deleteRasaZivotinja(RasaZivotinja rasaZivotinja) {
        try {
            getSession().delete(rasaZivotinja);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
