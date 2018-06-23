/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Entity
@Table(name = "RASA_ZIVOTINJA")
@NamedQueries({
    @NamedQuery(name = "RasaZivotinja.findAll", query = "SELECT p FROM RasaZivotinja p")})
public class RasaZivotinja implements Serializable {

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
    @JoinColumn(name = "VRSTA_ZIVOTINJA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VrstaZivotinja vrstaZivotinjaId;

    public RasaZivotinja() {
    }

    public RasaZivotinja(Integer id) {
        this.id = id;
    }

    public RasaZivotinja(Integer id, String ime) {
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

    public VrstaZivotinja getVrstaZivotinjaId() {
        return vrstaZivotinjaId;
    }

    public void setVrstaZivotinjaId(VrstaZivotinja vrstaZivotinjaId) {
        this.vrstaZivotinjaId = vrstaZivotinjaId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RasaZivotinja)) {
            return false;
        }
        RasaZivotinja other = (RasaZivotinja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RasaZivotinja[ id=" + getId() + ", ime=" + getIme() + " ]";
    }
    
}
