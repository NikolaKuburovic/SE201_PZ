/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se201.pz.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nikola Kuburovic 1095
 */
@Entity
@Table(name = "ZIVOTINJA")
@NamedQueries({
    @NamedQuery(name = "Zivotinja.findAll", query = "SELECT z FROM Zivotinja z")})
public class Zivotinja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
//    @Column(name = "RASA_ZIVOTINJA_ID")
    @Transient 
   private Integer rasaZivotinjaId;
//    @JoinColumn(name = "RASA_ZIVOTINJA_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private RasaZivotinja rasaZivotinjaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IME")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "OPIS")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM_KREIRANJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumKreiranja;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "ID")
    @ManyToOne
    private Korisnik korisnikId;
    @JoinColumn(name = "VRSTA_ZIVOTINJA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VrstaZivotinja vrstaZivotinjaId;
    @JoinColumn(name = "RASA_ZIVOTINJA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RasaZivotinja rasaZivotinja;

    public Zivotinja() {
    }

    public Zivotinja(Integer id) {
        this.id = id;
    }

    public Zivotinja(Integer id, String ime, String opis, Date datumKreiranja) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.datumKreiranja = datumKreiranja;
    }
    
    public Zivotinja(Integer id, String ime, String opis) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRasaZivotinjaId() {
        return rasaZivotinjaId;
    }

    public void setRasaZivotinjaId(Integer rasaZivotinjaId) {
        this.rasaZivotinjaId = rasaZivotinjaId;
    }
    
    public RasaZivotinja getRasaZivotinja() {
        return rasaZivotinja;
    }

    public void setRasaZivotinja(RasaZivotinja rasaZivotinja) {
        this.rasaZivotinja = rasaZivotinja;
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
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
        if (!(object instanceof Zivotinja)) {
            return false;
        }
        Zivotinja other = (Zivotinja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.se201.pz.entity.Zivotinja[ id=" + id + " ]";
    }
    
}
