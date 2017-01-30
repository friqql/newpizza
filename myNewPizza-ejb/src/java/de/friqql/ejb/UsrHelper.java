/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;

import de.friqql.jb.UsrHelperRemote;
import de.friqql.model.Food;
import de.friqql.model.Usr;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javax.persistence.Query;

/**
 *
 * @author Teilnehmer
 */
@Stateless(mappedName = "ejb/userHelper")
public class UsrHelper implements UsrHelperRemote {

    @PersistenceContext(unitName = "myNewPizza-ejb", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    private Usr dbUsr;
    private ConversionHelper cc;
    private int i;
    private List<Usr> allUsrs;

    private String helpstring;

    public UsrHelper() {
        this.allUsrs = new ArrayList();
this.cc = new ConversionHelper();
        this.dbUsr = new Usr();
    }

    @Override
    public void register(Usr helpUsr) {
        this.dbUsr = helpUsr;
//        
        helpUsr.setURole("Customer");
        helpUsr.setUVermerk("Alles Ok!");
        helpUsr.setUSince(new Date());
        entityManager.persist(helpUsr);
        entityManager.flush();

    }

    @Override
    public void updateUsr(Usr helpUsr) {
        this.dbUsr = helpUsr;

        entityManager.merge(dbUsr);
        entityManager.flush();

    }

    @Override
    public void changePassword(Usr helpUsr) {
        this.dbUsr = helpUsr;
        entityManager.merge(dbUsr);
        dbUsr.setPassAgain("");
        dbUsr.setUPassword(cc.hash(dbUsr.getNewPass()));
        entityManager.merge(dbUsr);
        entityManager.flush();

    }

    @Override
    public Usr getUsrByID(int uid) {

        allUsrs = getAllUsrs();

        for (Usr u : allUsrs) {
            if (u.getUId().equals((Integer) uid)) {
                dbUsr = u;
            }
        }

       
        return dbUsr;
    }

    @Override
    public Usr getUsrByUsrname(String username) {

        allUsrs = getAllUsrs();

        for (Usr u : allUsrs) {
            if (u.getUUsrname().equals(username)) {
                dbUsr = u;
            }
        }
        
        return dbUsr;
    }

    @Override
    public int countUsrs() {

        
        i = getAllUsrs().size();
        return i;
    }

    @Override
    public List getAllUsrs() {
        Query query = entityManager.createQuery("SELECT u FROM Usr u");
        allUsrs = (List) query.getResultList();
        
        for(Usr u:allUsrs) {
            entityManager.refresh(u);
        }
        return allUsrs;
    }

    /**
     * Setzt die Liste aller Usr
     *
     * @param allUsrs
     */
    @Override
    public void setAllUsrs(List<Usr> allUsrs) {
        this.allUsrs = allUsrs;
    }

    @Override
    public void setVermerk(Usr helpUsr) {
       helpUsr.setUVermerk(helpUsr.getUVermerk());
        entityManager.merge(dbUsr);
        entityManager.flush();
    }

    @Override
    public String getUsrVermerkById(int id) {

       
        for (Usr u : allUsrs) {
            if (u.getUId().equals((Integer) id)) {
                helpstring = u.getUVermerk();
            }
        }
        return helpstring;

    }
}
