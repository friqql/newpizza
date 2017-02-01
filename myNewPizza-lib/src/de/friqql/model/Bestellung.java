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
    @NamedQuery(name = "Bestellung.findAll", query = "SELECT b FROM Bestellung b")
    , @NamedQuery(name = "Bestellung.findById", query = "SELECT b FROM Bestellung b WHERE b.id = :id")
    , @NamedQuery(name = "Bestellung.findByUid", query = "SELECT b FROM Bestellung b WHERE b.uid = :uid")
    , @NamedQuery(name = "Bestellung.findByFid", query = "SELECT b FROM Bestellung b WHERE b.fid = :fid")
    , @NamedQuery(name = "Bestellung.findByAmmount", query = "SELECT b FROM Bestellung b WHERE b.ammount = :ammount")
    , @NamedQuery(name = "Bestellung.findByName", query = "SELECT b FROM Bestellung b WHERE b.name = :name")
    , @NamedQuery(name = "Bestellung.findBySessionId", query = "SELECT b FROM Bestellung b WHERE b.sessionId = :sessionId")
    , @NamedQuery(name = "Bestellung.findByIp", query = "SELECT b FROM Bestellung b WHERE b.ip = :ip")
    , @NamedQuery(name = "Bestellung.findByPrice", query = "SELECT b FROM Bestellung b WHERE b.price = :price")
    , @NamedQuery(name = "Bestellung.findBySince", query = "SELECT b FROM Bestellung b WHERE b.since = :since")
    , @NamedQuery(name = "Bestellung.findByZwischensumme", query = "SELECT b FROM Bestellung b WHERE b.zwischensumme = :zwischensumme")})
public class Bestellung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    private int uid;
    @Basic(optional = false)
    @NotNull
    private int fid;
    @Basic(optional = false)
    @NotNull
    private int ammount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String sessionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String ip;
    @Basic(optional = false)
    @NotNull
    private double price;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date since;
    @Basic(optional = false)
    @NotNull
    private double zwischensumme;

    public Bestellung() {
    }

    public Bestellung(Integer id) {
        this.id = id;
    }

    public Bestellung(Integer id, int uid, int fid, int ammount, String name, String sessionId, String ip, double price, Date since, double zwischensumme) {
        this.id = id;
        this.uid = uid;
        this.fid = fid;
        this.ammount = ammount;
        this.name = name;
        this.sessionId = sessionId;
        this.ip = ip;
        this.price = price;
        this.since = since;
        this.zwischensumme = zwischensumme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public double getZwischensumme() {
        return zwischensumme;
    }

    public void setZwischensumme(double zwischensumme) {
        this.zwischensumme = zwischensumme;
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
        if (!(object instanceof Bestellung)) {
            return false;
        }
        Bestellung other = (Bestellung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.model.Bestellung[ id=" + id + " ]";
    }
    
}
