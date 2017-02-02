/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;



import de.friqql.jb.ConversionHelperRemote;
import de.friqql.model.Benutzer;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import de.friqql.jb.BenutzerHelperRemote;


/**
 *
 * @author Teilnehmer
 */
@Named("benutzerController")
@javax.enterprise.context.SessionScoped
public class BenutzerController implements Serializable {

    private Benutzer myBenutzer;
   
   @Inject
    private  NavigationController navigationController;
   @Inject
    private MessagesController messagesController;
    private List<Benutzer> benutzerlist;
   
    
    @PostConstruct
    /**
     * Die Initmethode
     */
    public void init(){
        
       myBenutzer = new Benutzer();
       
       
        benutzerlist = new ArrayList();
        benutzerlist = benutzerlist();
    }
    
    
/**
 * Das Interface des BenutzerHelpers
 * @return 
 * Gibt das Interface zurück
 */
 private BenutzerHelperRemote uh() {
        try {
            Context c = new InitialContext();
            return (BenutzerHelperRemote) c.lookup("ejb/benutzerHelper");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    /**
     * Das Interface des ConversionHelpers
     * @return 
     *Gibt den ConversionHelper zurück
     */
    private ConversionHelperRemote ch() {
        try {
            Context c = new InitialContext();
            return (ConversionHelperRemote) c.lookup("ejb/conversionHelper");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    /**
     * Der Standardkonstruktor des Benutzerkontrollers
     */
    public BenutzerController() {
        
       
        
    }
    
    

    
   
    /**
     * Der aktuelle Benutzer
     * @return 
     * Gibt den aktuellen Benutzer zurück
     */
    public Benutzer getMyBenutzer() {

        return myBenutzer;
    }
/**
 * Setzt den aktuellen Benutzer
 * @param myBenutzer 
 */
    public void setMyBenutzer(Benutzer myBenutzer) {
        this.myBenutzer = myBenutzer;
    }
/**
 * Dient zum Login
 */
    public void login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(myBenutzer.getBenutzername(), myBenutzer.getPassword());
            myBenutzer = uh().getBenutzerByBenutzername(myBenutzer.getBenutzername());
           
        } catch (Exception ex) {
            messagesController.nnli();
        }
       

    }
/**
 * Testet, ob ein Benutzer eingeloggt ist
 * @return 
 * Gibt zurück ob er eingeloggt ist, oder nicht
 */
    public boolean isLogedIn() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.isUserInRole("Admin") || request.isUserInRole("Customer")) {
            return true;
        }
        return false;

    }
/**
 * Testet ob ein Benutzer ein Kunde ist
 * @return 
 * Gibt zurück, ob er ein Kunde ist
 */
    public boolean isCustomer() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.isUserInRole("Customer")) {
            return true;
        }
        return false;

    }
/**
 * Logt den Nutzer aus
 */
    public void logOut() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            
           
            request.logout();
             myBenutzer= new Benutzer();
            
        } catch (ServletException ex) {
            
            Logger.getLogger(BenutzerController.class.getName()).log(Level.SEVERE, null, ex);
            navigationController.toIndex();
        }
    }

    /**
     * Setzt bei der Registrierung den Benutzer zurück
     */
    public void reset() {
        myBenutzer = new Benutzer();
        navigationController.toIndex();
    }

    /**
     * Liste aller Benutzer 
     * @return 
     * Gibt alle Benutzer zurück
     */
    public List<Benutzer> getBenutzerlist() {
        return benutzerlist;
    }
/**
 * Setzt die Liste aller Benutzer
 * @param benutzerlist 
 * Die Liste der Benutzer
 */
    public void setBenutzerlist(List<Benutzer> benutzerlist) {
        this.benutzerlist = benutzerlist;
    }

    
    
    
    

/**
 * Dient zur Registrierung, wenn der Benutzername nicht vergeben ist
 */
    public void register() {
        
       
if(uh().getBenutzerByBenutzername(myBenutzer.getBenutzername())==null){
        uh().register(myBenutzer);
        messagesController.reg_s();
}

else{
    messagesController.reg_e();
}

    }
/**
 * Updatet die Benutzerdaten
 */
    public void updateBenutzer(){
        uh().updateBenutzer(myBenutzer);
        
    }
    
    
    /**
     * Setzt ein neues Passwort
     */
    public void setNewPass() {
       
        
        if (ch().hash(myBenutzer.getOldPass()).equals(myBenutzer.getPassword())) {
            
            
            
            uh().changePassword(myBenutzer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","Fehler beim ändern des Passwortes!"));
        }
    }
    /**
     * Die Liste aller Benutzer
     * @return 
     * Gibt die Liste aller Benutzer zurück
     */
    public List<Benutzer> benutzerlist(){
        benutzerlist=uh().getBenutzerlist();
        return benutzerlist;
    
    }
    
    
    
}
