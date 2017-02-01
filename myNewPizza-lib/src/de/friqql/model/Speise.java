/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Speise.findAll", query = "SELECT s FROM Speise s")
    , @NamedQuery(name = "Speise.findById", query = "SELECT s FROM Speise s WHERE s.id = :id")
    , @NamedQuery(name = "Speise.findByName", query = "SELECT s FROM Speise s WHERE s.name = :name")
    , @NamedQuery(name = "Speise.findByPrice", query = "SELECT s FROM Speise s WHERE s.price = :price")
    , @NamedQuery(name = "Speise.findBySection", query = "SELECT s FROM Speise s WHERE s.section = :section")
    , @NamedQuery(name = "Speise.findByAmmount", query = "SELECT s FROM Speise s WHERE s.ammount = :ammount")})
public class Speise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String name;
    @Basic(optional = false)
    @NotNull
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    private String section;
    @Basic(optional = false)
    @NotNull
    private int ammount;

    public Speise() {
    }

    public Speise(Integer id) {
        this.id = id;
    }

    public Speise(Integer id, String name, double price, String section, int ammount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.section = section;
        this.ammount = ammount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
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
        if (!(object instanceof Speise)) {
            return false;
        }
        Speise other = (Speise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.friqql.model.Speise[ id=" + id + " ]";
    }
    
}
