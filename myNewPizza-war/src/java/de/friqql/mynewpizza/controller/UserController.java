/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;


import de.friqql.mynewpizza.ejb.ConversionHelperRemote;
import de.friqql.mynewpizza.ejb.UserHelperRemote;
import de.friqql.mynewpizza.model.User;
import de.friqql.mynewpizza.view.message.MessagesView;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean
@SessionScoped
public class UserController implements Serializable {

    private User myUser;
   
   
    private  NavigationController nc;
    private MessagesView mv;
    private List<User> allCustomers;
    private int customerNumber;
private User helpUser;
    
    public UserController() {
        myUser = new User();
       
        nc = new NavigationController();
        mv = new MessagesView();
       
        allCustomers = new ArrayList();
        allCustomers = allCustomers();
        customerNumber = 0;
        helpUser = new User();
    }

    
    private UserHelperRemote uh() {
        try {
            Context c = new InitialContext();
            return (UserHelperRemote) c.lookup("java:global/myNewPizza/myNewPizza-ejb/UserHelper!de.friqql.myNewPizza.UserHelperRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    private ConversionHelperRemote ch() {
        try {
            Context c = new InitialContext();
            return (ConversionHelperRemote) c.lookup("java:global/FirstEEBusiness/FirstEEBusiness-ejb/HalloWorldSession!com.ibb.ejb.HalloWorldSessionRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    /**
     * Gibt den aktuellen User zurück
     * @return 
     */
    public User getMyUser() {

        return myUser;
    }
/**
 * Setzt den aktuellen User
 * @param myUser 
 */
    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }
/**
 * Dient zum Login
 */
    public void login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            HttpSession session = request.getSession();
            
            
            
            request.login(myUser.getUUsername(), myUser.getUPassword());
            this.myUser= uh().getUserByUsername(myUser.getUUsername());
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
/**
 * Testet, ob ein User eingeloggt ist
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
 * Testet ob ein User ein Kunde ist
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
            
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            nc.toIndex();
        }
    }

    /**
     * Setzt bei der Registrierung den User zurück
     */
    public void reset() {
        myUser.setUUsername("");
        myUser.setUPassword("");
        myUser.setUTitle("");
        myUser.setUFirstname("");
        myUser.setULastname("");
        myUser.setUStreet("");
        myUser.setUHouse("");
        myUser.setUPlz("");

        myUser.setUPlace("");
        myUser.setURole("");
        nc.toIndex();
    }

    /**
     * Liste aller User (auch Admins, Name aus historischen Gründen)
     * @return 
     */
    public List<User> getAllCustomers() {
        return allCustomers;
    }
/**
 * Setzt die Liste aller User
 * @param allCustomers 
 */
    public void setAllCustomers(List<User> allCustomers) {
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
 * Dient zur Registrierung, wenn der Username nicht vergeben ist
 */
    public void register() {
if(uh().getUserByUsername(myUser.getUUsername())==null){
        uh().register(myUser);
        mv.reg_s();
}

else{
    mv.reg_e();
}

    }
/**
 * Updatet die Userdaten
 */
    public void updateUser(){
        uh().updateUser(myUser);
        
    }
    
    
    /**
     * Setzt ein neues Passwort
     */
    public void setNewPass() {
        if (ch().hash(myUser.getOldPass()).equals(myUser.getUPassword())) {
            
            
            myUser.setUPassword(myUser.getNewPass());
            uh().changePassword(myUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","Fehler beim ändern des Passwortes!"));
        }
    }
    /**
     * Die Liste aller User
     * @return 
     */
    public List<User> allCustomers(){
        allCustomers=uh().getAllUsers();
        uh().setAllUsers(new ArrayList());
        return allCustomers;
    
    }
    
    
    
}
