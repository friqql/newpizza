/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;

import de.friqql.jb.BenutzerHelperRemote;

import de.friqql.model.Benutzer;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javax.persistence.Query;

/**
 *
 * @author Teilnehmer
 */
@Stateful(mappedName = "ejb/benutzerHelper")
@SessionScoped
public class BenutzerHelper implements BenutzerHelperRemote {

    @PersistenceContext(unitName = "myNewPizza-ejb", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    private Benutzer dbBenutzer;
    private ConversionHelper cc;
    private int i;
    private List<Benutzer> benutzerliste;

    private String helpstring;

    /**
     * Der Standardkonstruktor des BenutzerHelpers
     */
    public BenutzerHelper() {
        this.benutzerliste = new ArrayList();
        this.cc = new ConversionHelper();
        this.dbBenutzer = new Benutzer();
    }

    @Override
    /**
     * Die Registrierung passiert hier!
     */
    public void register(Benutzer helpBenutzer) {

        {
            this.dbBenutzer = helpBenutzer;
            dbBenutzer.setBenutzername(dbBenutzer.getBenutzername().toLowerCase());
            dbBenutzer.setRolle("Customer");
            dbBenutzer.setVermerk("Alles Ok!");
            dbBenutzer.setSince(new Date());
            dbBenutzer.setPassword(cc.hash(dbBenutzer.getPassword()));
            dbBenutzer.setNewPass("");
            dbBenutzer.setOldPass("");
            dbBenutzer.setPassAgain("");
            entityManager.persist(helpBenutzer);
            entityManager.flush();
        }

    }

    /**
     * Updatet den Benutzer
     * @Param helpBenutzer
     * Der Benutzer der geupdatet werden soll
     */
    @Override
    
    public void updateBenutzer(Benutzer helpBenutzer) {
        this.dbBenutzer = helpBenutzer;

        entityManager.merge(dbBenutzer);
        entityManager.flush();

    }
     /**
     * Updatet das Passwort des gewählten Benutzer
     * @Param helpBenutzer
     * Der Benutzer dessen Passwort geupdatet werden soll
     */
    @Override
    
    public void changePassword(Benutzer helpBenutzer) {
        this.dbBenutzer = helpBenutzer;
        dbBenutzer.setPassword(cc.hash(dbBenutzer.getNewPass()));
        dbBenutzer.setPassAgain("");
        dbBenutzer.setNewPass("");
        dbBenutzer.setOldPass("");
        entityManager.merge(dbBenutzer);
        entityManager.flush();

    }
     /**
     * Ermittelt einen Benutzer nach seiner ID
     * @param uid
     * Die Id des Benutzers
     * @return
     * der Benutzer
     */
    @Override
   
    public Benutzer getBenutzerByID(int uid) {
Benutzer b;
        Query q = entityManager.createQuery("SELECT b FROM Benutzer b WHERE b.id = :id");
        q.setParameter("id", uid);
        try {
            b = (Benutzer) q.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        }
        return b;
    }
      /**
     * Ermittelt einen Benutzer nach seinem Benutzernamen
     * @param benutzername
     * Die Id des Benutzers
     * @return
     * der Benutzer
     */

    @Override
    public Benutzer getBenutzerByBenutzername(String benutzername) {
        Benutzer b;
        Query q = entityManager.createQuery("SELECT b FROM Benutzer b WHERE b.benutzername = :bn");
        q.setParameter("bn", benutzername);
        try {
            b = (Benutzer) q.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        }
        return b;
    }
/**
 * zählt die Benutzer
 * @return 
 */
    @Override
    public int countBenutzers() {

        i = getBenutzerlist().size();
        return i;
    }
/**
 * Die Liste der Benutzer
 * @return 
 * Gibt die Liste der Benutzer zurück
 */
    @Override
    public List getBenutzerlist() {
        Query query = entityManager.createQuery("SELECT b FROM Benutzer b");
        benutzerliste = (List<Benutzer>) query.getResultList();
        return benutzerliste;
    }

    /**
     * Setzt die Liste aller Benutzer
     * @param benutzerliste
     */
    @Override
    public void setBenutzerlist(List<Benutzer> benutzerliste) {
        this.benutzerliste = benutzerliste;
    }

    /**
     * Setzt den Vermerk bei dem übergebenen Benutzer
     * @param helpBenutzer 
     * Der Benutzer
     */
    @Override
    public void setVermerk(Benutzer helpBenutzer) {
        
        this.dbBenutzer = helpBenutzer;
        dbBenutzer.setVermerk(helpBenutzer.getVermerk());
        dbBenutzer.setPassAgain("");
        dbBenutzer.setNewPass("");
        dbBenutzer.setOldPass("");
        entityManager.merge(dbBenutzer);
        entityManager.flush();
    }

    @Override
    public String getBenutzerVermerkById(int id) {

Benutzer b;
        Query q = entityManager.createQuery("SELECT b FROM Benutzer b WHERE b.id = :id");
        q.setParameter("id", id);
        try {
            b = (Benutzer) q.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        }
        return b.getVermerk();
    }
    }


