/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;



import de.friqql.jb.ConversionHelperRemote;
import de.friqql.jb.UsrHelperRemote;
import de.friqql.model.Usr;
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


/**
 *
 * @author Teilnehmer
 */
@Named
@javax.enterprise.context.SessionScoped
public class UsrController implements Serializable {

    private Usr myUsr;
   
   @Inject
    private  NavigationController navigationController;
   @Inject
    private MessagesController messagesController;
    private List<Usr> allCustomers;
   

 private UsrHelperRemote uh() {
        try {
            Context c = new InitialContext();
            return (UsrHelperRemote) c.lookup("ejb/userHelper");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    private ConversionHelperRemote ch() {
        try {
            Context c = new InitialContext();
            return (ConversionHelperRemote) c.lookup("ejb/conversionHelper");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public UsrController() {
        
       
        
    }
    
    @PostConstruct
    public void init(){
        
       myUsr = new Usr();
       
       
        allCustomers = new ArrayList();
        allCustomers = allCustomers();
    }

    
   
    /**
     * Gibt den aktuellen Usr zurück
     * @return 
     */
    public Usr getMyUsr() {

        return myUsr;
    }
/**
 * Setzt den aktuellen Usr
 * @param myUsr 
 */
    public void setMyUsr(Usr myUsr) {
        this.myUsr = myUsr;
    }
/**
 * Dient zum Login
 */
    public void login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(myUsr.getUUsrname(), myUsr.getUPassword());
            myUsr = uh().getUsrByUsrname(myUsr.getUUsrname());
           
        } catch (Exception ex) {
            messagesController.nnli();
        }
       

    }
/**
 * Testet, ob ein Usr eingeloggt ist
 * @return 
 */
    public boolean isLogedIn() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.isUserInRole("Admin") || request.isUserInRole("Customer")) {
            return true;
        }
        return false;

    }
/**
 * Testet ob ein Usr ein Kunde ist
 * @return 
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
             
            
        } catch (ServletException ex) {
            
            Logger.getLogger(UsrController.class.getName()).log(Level.SEVERE, null, ex);
            navigationController.toIndex();
        }
    }

    /**
     * Setzt bei der Registrierung den Usr zurück
     */
    public void reset() {
        myUsr.setUUsrname("");
        myUsr.setUPassword("");
        myUsr.setUTitle("");
        myUsr.setUFirstname("");
        myUsr.setULastname("");
        myUsr.setUStreet("");
        myUsr.setUHouse("");
        myUsr.setUPlz("");

        myUsr.setUPlace("");
        myUsr.setURole("");
        navigationController.toIndex();
    }

    /**
     * Liste aller Usr (auch Admins, Name aus historischen Gründen)
     * @return 
     */
    public List<Usr> getAllCustomers() {
        return allCustomers;
    }
/**
 * Setzt die Liste aller Usr
 * @param allCustomers 
 */
    public void setAllCustomers(List<Usr> allCustomers) {
        this.allCustomers = allCustomers;
    }

    
    
    
    
   /**
    * Führt zum Index
    * @return 
    */ 
    public String toIndex() {
        return "toIndex";
    }
/**
 * Dient zur Registrierung, wenn der Usrname nicht vergeben ist
 */
    public void register() {
        myUsr= new Usr();
        myUsr.setUUsrname(myUsr.getUUsrname());
if(uh().getUsrByUsrname(myUsr.getUUsrname())==null){
        uh().register(myUsr);
        messagesController.reg_s();
}

else{
    messagesController.reg_e();
}

    }
/**
 * Updatet die Usrdaten
 */
    public void updateUsr(){
        uh().updateUsr(myUsr);
        
    }
    
    
    /**
     * Setzt ein neues Passwort
     */
    public void setNewPass() {
       
        
        if (ch().hash(myUsr.getOldPass()).equals(myUsr.getUPassword())) {
            
            
            
            uh().changePassword(myUsr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","Fehler beim ändern des Passwortes! Altes PW eingegeben: " +ch().hash(myUsr.getOldPass())+"Tatsächliches altes PW: "+myUsr.getUPassword()+""));
        }
    }
    /**
     * Die Liste aller Usr
     * @return 
     */
    public List<Usr> allCustomers(){
        allCustomers=uh().getAllUsrs();
        uh().setAllUsrs(new ArrayList());
        return allCustomers;
    
    }
    
    
    
}
