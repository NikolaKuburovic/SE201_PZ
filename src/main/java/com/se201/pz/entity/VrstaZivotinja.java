/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Entity
@Table(name = "VRSTA_ZIVOTINJA")
@NamedQueries({
    @NamedQuery(name = "VrstaZivotinja.findAll", query = "SELECT t FROM VrstaZivotinja t")})
public class VrstaZivotinja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IME")
    private String ime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vrstaZivotinjaId")
    private List<Zivotinja> zivotinjaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vrstaZivotinjaId")
    private List<RasaZivotinja> rasaZivotinjaList;

    public VrstaZivotinja() {
    }

    public VrstaZivotinja(Integer id) {
        this.id = id;
    }

    public VrstaZivotinja(Integer id, String ime) {
        this.id = id;
        this.ime = ime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public List<Zivotinja> getZivotinjaList() {
        return zivotinjaList;
    }

    public void setZivotinjaList(List<Zivotinja> zivotinjaList) {
        this.zivotinjaList = zivotinjaList;
    }

    public List<RasaZivotinja> getRasaZivotinjaList() {
        return rasaZivotinjaList;
    }

    public void setRasaZivotinjaList(List<RasaZivotinja> rasaZivotinjaList) {
        this.rasaZivotinjaList = rasaZivotinjaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VrstaZivotinja)) {
            return false;
        }
        VrstaZivotinja other = (VrstaZivotinja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime;
    }
    
}
