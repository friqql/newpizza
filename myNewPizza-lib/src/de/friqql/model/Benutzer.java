/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Teilnehmer
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Benutzer.findAll", query = "SELECT b FROM Benutzer b")
    , @NamedQuery(name = "Benutzer.findById", query = "SELECT b FROM Benutzer b WHERE b.id = :id")
    , @NamedQuery(name = "Benutzer.findByBenutzername", query = "SELECT b FROM Benutzer b WHERE b.benutzername = :benutzername")
    , @NamedQuery(name = "Benutzer.findByPassword", query = "SELECT b FROM Benutzer b WHERE b.password = :password")
    , @NamedQuery(name = "Benutzer.findByTitle", query = "SELECT b FROM Benutzer b WHERE b.title = :title")
    , @NamedQuery(name = "Benutzer.findByFirstname", query = "SELECT b FROM Benutzer b WHERE b.firstname = :firstname")
    , @NamedQuery(name = "Benutzer.findByLastname", query = "SELECT b FROM Benutzer b WHERE b.lastname = :lastname")
    , @NamedQuery(name = "Benutzer.findByStreet", query = "SELECT b FROM Benutzer b WHERE b.street = :street")
    , @NamedQuery(name = "Benutzer.findByHouse", query = "SELECT b FROM Benutzer b WHERE b.house = :house")
    , @NamedQuery(name = "Benutzer.findByPlz", query = "SELECT b FROM Benutzer b WHERE b.plz = :plz")
    , @NamedQuery(name = "Benutzer.findByPlace", query = "SELECT b FROM Benutzer b WHERE b.place = :place")
    , @NamedQuery(name = "Benutzer.findByRolle", query = "SELECT b FROM Benutzer b WHERE b.rolle = :rolle")
    , @NamedQuery(name = "Benutzer.findBySince", query = "SELECT b FROM Benutzer b WHERE b.since = :since")
    , @NamedQuery(name = "Benutzer.findByVermerk", query = "SELECT b FROM Benutzer b WHERE b.vermerk = :vermerk")
    , @NamedQuery(name = "Benutzer.findByNewPass", query = "SELECT b FROM Benutzer b WHERE b.newPass = :newPass")
    , @NamedQuery(name = "Benutzer.findByOldPass", query = "SELECT b FROM Benutzer b WHERE b.oldPass = :oldPass")
    , @NamedQuery(name = "Benutzer.findByPassAgain", query = "SELECT b FROM Benutzer b WHERE b.passAgain = :passAgain")})
public class Benutzer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String benutzername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String house;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String plz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String place;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String rolle;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date since;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String vermerk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String newPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String oldPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String passAgain;

    public Benutzer() {
    }

    public Benutzer(Integer id) {
        this.id = id;
    }

    public Benutzer(Integer id, String benutzername, String password, String title, String firstname, String lastname, String street, String house, String plz, String place, String rolle, Date since, String vermerk, String newPass, String oldPass, String passAgain) {
        this.id = id;
        this.benutzername = benutzername;
        this.password = password;
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.house = house;
        this.plz = plz;
        this.place = place;
        this.rolle = rolle;
        this.since = since;
        this.vermerk = vermerk;
        this.newPass = newPass;
        this.oldPass = oldPass;
        this.passAgain = passAgain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public String getVermerk() {
        return vermerk;
    }

    public void setVermerk(String vermerk) {
        this.vermerk = vermerk;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getPassAgain() {
        return passAgain;
    }

    public void setPassAgain(String passAgain) {
        this.passAgain = passAgain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Benutzer)) {
            return false;
        }
        Benutzer other = (Benutzer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.model.Benutzer[ id=" + id + " ]";
    }
    
}
