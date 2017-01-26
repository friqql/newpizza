/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Teilnehmer
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFId", query = "SELECT f FROM Food f WHERE f.fId = :fId")
    , @NamedQuery(name = "Food.findByFName", query = "SELECT f FROM Food f WHERE f.fName = :fName")
    , @NamedQuery(name = "Food.findByFPrice", query = "SELECT f FROM Food f WHERE f.fPrice = :fPrice")
    , @NamedQuery(name = "Food.findByFSection", query = "SELECT f FROM Food f WHERE f.fSection = :fSection")
    , @NamedQuery(name = "Food.findByFAmmount", query = "SELECT f FROM Food f WHERE f.fAmmount = :fAmmount")
        , @NamedQuery(name = "Food.count", query = "select count(f.fName) from Food f")
    
    
})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer fId;
    @Basic(optional = false)
    @Column(nullable = false, length = 75)
    private String fName;
    @Basic(optional = false)
    @Column(nullable = false)
    private double fPrice;
    @Basic(optional = false)
    @Column(nullable = false, length = 75)
    private String fSection;
    @Basic(optional = false)
    @Column(nullable = false)
    private int fAmmount;

    public Food() {
    }

    public Food(Integer fId) {
        this.fId = fId;
    }

    public Food(Integer fId, String fName, double fPrice, String fSection, int fAmmount) {
        this.fId = fId;
        this.fName = fName;
        this.fPrice = fPrice;
        this.fSection = fSection;
        this.fAmmount = fAmmount;
    }

    public Integer getFId() {
        return fId;
    }

    public void setFId(Integer fId) {
        this.fId = fId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public double getFPrice() {
        return fPrice;
    }

    public void setFPrice(double fPrice) {
        this.fPrice = fPrice;
    }

    public String getFSection() {
        return fSection;
    }

    public void setFSection(String fSection) {
        this.fSection = fSection;
    }

    public int getFAmmount() {
        return fAmmount;
    }

    public void setFAmmount(int fAmmount) {
        this.fAmmount = fAmmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fId != null ? fId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.fId == null && other.fId != null) || (this.fId != null && !this.fId.equals(other.fId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.mynewpizza.model.Food[ fId=" + fId + " ]";
    }
    
}
