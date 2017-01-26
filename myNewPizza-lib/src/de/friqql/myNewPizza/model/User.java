/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Teilnehmer
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uUsername"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUId", query = "SELECT u FROM User u WHERE u.uId = :uId")
    , @NamedQuery(name = "User.findByUUsername", query = "SELECT u FROM User u WHERE u.uUsername = :uUsername")
    , @NamedQuery(name = "User.findByUPassword", query = "SELECT u FROM User u WHERE u.uPassword = :uPassword")
    , @NamedQuery(name = "User.findByUTitle", query = "SELECT u FROM User u WHERE u.uTitle = :uTitle")
    , @NamedQuery(name = "User.findByUFirstname", query = "SELECT u FROM User u WHERE u.uFirstname = :uFirstname")
    , @NamedQuery(name = "User.findByULastname", query = "SELECT u FROM User u WHERE u.uLastname = :uLastname")
    , @NamedQuery(name = "User.findByUStreet", query = "SELECT u FROM User u WHERE u.uStreet = :uStreet")
    , @NamedQuery(name = "User.findByUHouse", query = "SELECT u FROM User u WHERE u.uHouse = :uHouse")
    , @NamedQuery(name = "User.findByUPlz", query = "SELECT u FROM User u WHERE u.uPlz = :uPlz")
    , @NamedQuery(name = "User.findByUPlace", query = "SELECT u FROM User u WHERE u.uPlace = :uPlace")
    , @NamedQuery(name = "User.findByURole", query = "SELECT u FROM User u WHERE u.uRole = :uRole")
    , @NamedQuery(name = "User.findByUSince", query = "SELECT u FROM User u WHERE u.uSince = :uSince")
    , @NamedQuery(name = "User.findByUVermerk", query = "SELECT u FROM User u WHERE u.uVermerk = :uVermerk")
    , @NamedQuery(name = "User.getVermerkById", query = "select u.uVermerk FROM User u WHERE u.uVermerk =:uVermerk")    
    , @NamedQuery(name = "User.count", query = "select count(u.uUsername) from User u")
    
})

  
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer uId;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uUsername;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String uPassword;
    @Basic(optional = false)
    @Column(nullable = false, length = 5)
    private String uTitle;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uFirstname;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uLastname;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uStreet;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uHouse;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uPlz;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uPlace;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String uRole;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date uSince;
    @Basic(optional = false)
    @Column(nullable = false, length = 75)
    private String uVermerk;
    private String newPass;
    private String oldPass;
    private String pwagain;

    public User() {
    }

    public User(Integer uId) {
        this.uId = uId;
    }

    public User(Integer uId, String uUsername, String uPassword, String uTitle, String uFirstname, String uLastname, String uStreet, String uHouse, String uPlz, String uPlace, String uRole, Date uSince, String uVermerk) {
        this.uId = uId;
        this.uUsername = uUsername;
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

    public String getPwagain() {
        return pwagain;
    }

    public void setPwagain(String pwagain) {
        this.pwagain = pwagain;
    }

    
    
    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUUsername() {
        return uUsername;
    }

    public void setUUsername(String uUsername) {
        this.uUsername = uUsername;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.mynewpizza.model.User[ uId=" + uId + " ]";
    }

}
