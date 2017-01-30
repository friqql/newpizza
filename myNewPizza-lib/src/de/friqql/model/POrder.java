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
@Table(name = "p_order", catalog = "pizzeriafaces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "POrder.findAll", query = "SELECT p FROM POrder p")
    , @NamedQuery(name = "POrder.findByOId", query = "SELECT p FROM POrder p WHERE p.oId = :oId")
    , @NamedQuery(name = "POrder.findByOuId", query = "SELECT p FROM POrder p WHERE p.ouId = :ouId")
    , @NamedQuery(name = "POrder.findByOFoodId", query = "SELECT p FROM POrder p WHERE p.oFoodId = :oFoodId")
    , @NamedQuery(name = "POrder.findByOAmmount", query = "SELECT p FROM POrder p WHERE p.oAmmount = :oAmmount")
    , @NamedQuery(name = "POrder.findByOName", query = "SELECT p FROM POrder p WHERE p.oName = :oName")
    , @NamedQuery(name = "POrder.findByOSessionId", query = "SELECT p FROM POrder p WHERE p.oSessionId = :oSessionId")
    , @NamedQuery(name = "POrder.findByOIp", query = "SELECT p FROM POrder p WHERE p.oIp = :oIp")
    , @NamedQuery(name = "POrder.findByOPrice", query = "SELECT p FROM POrder p WHERE p.oPrice = :oPrice")
    , @NamedQuery(name = "POrder.findByOSince", query = "SELECT p FROM POrder p WHERE p.oSince = :oSince")
    , @NamedQuery(name = "POrder.findByOZwischensumme", query = "SELECT p FROM POrder p WHERE p.oZwischensumme = :oZwischensumme")})
public class POrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer oId;
    @Basic(optional = false)
    @NotNull
    private int ouId;
    @Basic(optional = false)
    @NotNull
    private int oFoodId;
    @Basic(optional = false)
    @NotNull
    private int oAmmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String oName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String oSessionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String oIp;
    @Basic(optional = false)
    @NotNull
    private double oPrice;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date oSince;
    @Basic(optional = false)
    @NotNull
    private double oZwischensumme;

    public POrder() {
    }

    public POrder(Integer oId) {
        this.oId = oId;
    }

    public POrder(Integer oId, int ouId, int oFoodId, int oAmmount, String oName, String oSessionId, String oIp, double oPrice, Date oSince, double oZwischensumme) {
        this.oId = oId;
        this.ouId = ouId;
        this.oFoodId = oFoodId;
        this.oAmmount = oAmmount;
        this.oName = oName;
        this.oSessionId = oSessionId;
        this.oIp = oIp;
        this.oPrice = oPrice;
        this.oSince = oSince;
        this.oZwischensumme = oZwischensumme;
    }

    public Integer getOId() {
        return oId;
    }

    public void setOId(Integer oId) {
        this.oId = oId;
    }

    public int getOuId() {
        return ouId;
    }

    public void setOuId(int ouId) {
        this.ouId = ouId;
    }

    public int getOFoodId() {
        return oFoodId;
    }

    public void setOFoodId(int oFoodId) {
        this.oFoodId = oFoodId;
    }

    public int getOAmmount() {
        return oAmmount;
    }

    public void setOAmmount(int oAmmount) {
        this.oAmmount = oAmmount;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public String getOSessionId() {
        return oSessionId;
    }

    public void setOSessionId(String oSessionId) {
        this.oSessionId = oSessionId;
    }

    public String getOIp() {
        return oIp;
    }

    public void setOIp(String oIp) {
        this.oIp = oIp;
    }

    public double getOPrice() {
        return oPrice;
    }

    public void setOPrice(double oPrice) {
        this.oPrice = oPrice;
    }

    public Date getOSince() {
        return oSince;
    }

    public void setOSince(Date oSince) {
        this.oSince = oSince;
    }

    public double getOZwischensumme() {
        return oZwischensumme;
    }

    public void setOZwischensumme(double oZwischensumme) {
        this.oZwischensumme = oZwischensumme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oId != null ? oId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof POrder)) {
            return false;
        }
        POrder other = (POrder) object;
        if ((this.oId == null && other.oId != null) || (this.oId != null && !this.oId.equals(other.oId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.model.POrder[ oId=" + oId + " ]";
    }
    
}
