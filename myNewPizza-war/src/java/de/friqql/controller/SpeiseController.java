/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;





import de.friqql.model.Speise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import de.friqql.jb.SpeiseHelperRemote;

/**
 *
 * @author Teilnehmer
 */
@Named("speiseController")
@javax.enterprise.context.SessionScoped
public class SpeiseController implements Serializable {

    private Speise speise;
    
    private int fId;
    private ArrayList<Speise> speiselist;
   

    
     private SpeiseHelperRemote helper() {
        try {
            Context c = new InitialContext();
             SpeiseHelperRemote sepp = (SpeiseHelperRemote)c.lookup("ejb/speiseHelper");
            return (sepp);
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
          
        }
    }
    
    
    /**
     * Der Konstruktor des SpeiseControllers
     */
    public SpeiseController() {
     
    }
    @PostConstruct
    public void init(){
        speise = new Speise();
        
        speiselist = new ArrayList();
        
        for (int i = 1; i <= countSpeise(); i++) {
            speiselist.add((Speise) getSpeiseById(i));

        }
    }
/**
 * Gibt eine Speise zurück
 * @return 
 * Gibt eine Speise zurückt
 */
    public Speise getSpeise() {
        return speise;
    }
/**
 * Setzt eine Speise
 * @param speise 
 */
    public void setSpeise(Speise speise) {
        this.speise = speise;
    }
   
   
/**
 * Gibt eine fId einer Speise zurück
 * @return 
 * Gibt eine fId einer Speise zurück
 */
    public int getfId() {
        return fId;
    }
/**
 * Setzt eine fId
 * @param fId 
 * Die Id
 */
    public void setfId(int fId) {
        this.fId = fId;
    }
/**
 * Die Liste der Speisen wird zurückgegeben
 * @return 
 * Gibt die Liste der Speisen zurück
 */
    public ArrayList<Speise> getSpeiselist() {

        return speiselist;
    }
/**
 * Die Liste der Speisen wird gesetzt
 * @param speiselist 
 * die Liste der speisen
 */
    public void setSpeiselist(ArrayList speiselist) {
        this.speiselist = speiselist;
    }

   
/**
 *  eine Speise nach der fId
 * @param fId
 * @return 
 * gibt die Speise zurück
 */
    public Speise getSpeiseById(Integer fId) {
        speise = helper().getSpeiseById(fId);
        return speise;
    }
/**
 * Zählt wieviele Speisen es gibt
 * @return 
 * gibt die Anzahl zurück
 */
    public int countSpeise() {

        return helper().countSpeise();
    }
/**
 * Setzt die Speiseliste zurück
 * @param dieListe 
 * Die Liste der speisen
 */
    public void reset(ArrayList<Speise> dieListe) {
        for (Speise s : dieListe) {

            s.setAmmount(0);
        }

    }

}
