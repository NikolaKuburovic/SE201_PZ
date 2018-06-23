/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.dao.impl;

import com.se201.pz.entity.Zivotinja;
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
import com.se201.pz.dao.ZivotinjaDao;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Repository("zivotinjaDao")
@Service
public class ZivotinjaDaoImpl implements ZivotinjaDao {

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
    public List<Zivotinja> getListaZivotinja() {
        return getSession().createCriteria(Zivotinja.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Zivotinja dodajZivotinju(Zivotinja zivotinja) {
        return (Zivotinja) getSession().merge(zivotinja);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Zivotinja getZivotinjaById(Integer id) {
        return (Zivotinja) getSession().createCriteria(Zivotinja.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Transactional
    @Override
    public boolean deleteZivotinja(Zivotinja zivotinja) {
        try {
            getSession().delete(zivotinja);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
