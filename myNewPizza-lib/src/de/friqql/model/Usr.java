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
import javax.persistence.Table;
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
@Table(catalog = "pizzeriafaces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u")
    , @NamedQuery(name = "Usr.findByUId", query = "SELECT u FROM Usr u WHERE u.uId = :uId")
    , @NamedQuery(name = "Usr.findByUUsrname", query = "SELECT u FROM Usr u WHERE u.uUsrname = :uUsrname")
    , @NamedQuery(name = "Usr.findByUPassword", query = "SELECT u FROM Usr u WHERE u.uPassword = :uPassword")
    , @NamedQuery(name = "Usr.findByUTitle", query = "SELECT u FROM Usr u WHERE u.uTitle = :uTitle")
    , @NamedQuery(name = "Usr.findByUFirstname", query = "SELECT u FROM Usr u WHERE u.uFirstname = :uFirstname")
    , @NamedQuery(name = "Usr.findByULastname", query = "SELECT u FROM Usr u WHERE u.uLastname = :uLastname")
    , @NamedQuery(name = "Usr.findByUStreet", query = "SELECT u FROM Usr u WHERE u.uStreet = :uStreet")
    , @NamedQuery(name = "Usr.findByUHouse", query = "SELECT u FROM Usr u WHERE u.uHouse = :uHouse")
    , @NamedQuery(name = "Usr.findByUPlz", query = "SELECT u FROM Usr u WHERE u.uPlz = :uPlz")
    , @NamedQuery(name = "Usr.findByUPlace", query = "SELECT u FROM Usr u WHERE u.uPlace = :uPlace")
    , @NamedQuery(name = "Usr.findByURole", query = "SELECT u FROM Usr u WHERE u.uRole = :uRole")
    , @NamedQuery(name = "Usr.findByUSince", query = "SELECT u FROM Usr u WHERE u.uSince = :uSince")
    , @NamedQuery(name = "Usr.findByUVermerk", query = "SELECT u FROM Usr u WHERE u.uVermerk = :uVermerk")
    , @NamedQuery(name = "Usr.findByNewPass", query = "SELECT u FROM Usr u WHERE u.newPass = :newPass")
    , @NamedQuery(name = "Usr.findByOldPass", query = "SELECT u FROM Usr u WHERE u.oldPass = :oldPass")
    , @NamedQuery(name = "Usr.findByPassAgain", query = "SELECT u FROM Usr u WHERE u.passAgain = :passAgain")})
public class Usr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer uId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uUsrname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String uPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    private String uTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uStreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uHouse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uPlz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uPlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String uRole;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date uSince;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String uVermerk;
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

    public Usr() {
    }

    public Usr(Integer uId) {
        this.uId = uId;
    }

    public Usr(Integer uId, String uUsrname, String uPassword, String uTitle, String uFirstname, String uLastname, String uStreet, String uHouse, String uPlz, String uPlace, String uRole, Date uSince, String uVermerk, String newPass, String oldPass, String passAgain) {
        this.uId = uId;
        this.uUsrname = uUsrname;
        this.uPassword = uPassword;
        this.uTitle = uTitle;
        this.uFirstname = uFirstname;
        this.uLastname = uLastname;
        this.uStreet = uStreet;
        this.uHouse = uHouse;
        this.uPlz = uPlz;
        this.uPlace = uPlace;
        this.uRole = uRole;
        this.uSince = uSince;
        this.uVermerk = uVermerk;
        this.newPass = newPass;
        this.oldPass = oldPass;
        this.passAgain = passAgain;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUUsrname() {
        return uUsrname;
    }

    public void setUUsrname(String uUsrname) {
        this.uUsrname = uUsrname;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getUTitle() {
        return uTitle;
    }

    public void setUTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public String getUFirstname() {
        return uFirstname;
    }

    public void setUFirstname(String uFirstname) {
        this.uFirstname = uFirstname;
    }

    public String getULastname() {
        return uLastname;
    }

    public void setULastname(String uLastname) {
        this.uLastname = uLastname;
    }

    public String getUStreet() {
        return uStreet;
    }

    public void setUStreet(String uStreet) {
        this.uStreet = uStreet;
    }

    public String getUHouse() {
        return uHouse;
    }

    public void setUHouse(String uHouse) {
        this.uHouse = uHouse;
    }

    public String getUPlz() {
        return uPlz;
    }

    public void setUPlz(String uPlz) {
        this.uPlz = uPlz;
    }

    public String getUPlace() {
        return uPlace;
    }

    public void setUPlace(String uPlace) {
        this.uPlace = uPlace;
    }

    public String getURole() {
        return uRole;
    }

    public void setURole(String uRole) {
        this.uRole = uRole;
    }

    public Date getUSince() {
        return uSince;
    }

    public void setUSince(Date uSince) {
        this.uSince = uSince;
    }

    public String getUVermerk() {
        return uVermerk;
    }

    public void setUVermerk(String uVermerk) {
        this.uVermerk = uVermerk;
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
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usr)) {
            return false;
        }
        Usr other = (Usr) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.model.Usr[ uId=" + uId + " ]";
    }
    
}
