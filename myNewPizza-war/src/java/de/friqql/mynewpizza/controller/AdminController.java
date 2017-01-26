/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;


import de.friqql.mynewpizza.ejb.UserHelperRemote;
import de.friqql.mynewpizza.listener.ActiveSessionsListener;
import de.friqql.mynewpizza.model.User;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teilnehmer
 */
@ManagedBean
@SessionScoped
public class AdminController implements Serializable {

    private ActiveSessionsListener asl;
    private Map sessionMap;
    private List<Map> sessionMaps;
 
    private User helpUser;
  
    private String vermerk;

    public AdminController() {
        this.asl = new ActiveSessionsListener();
        this.sessionMap = new HashMap();
        this.sessionMaps = new ArrayList();
      
        this.helpUser = new User();
    
        this.vermerk = "Alles Ok!";
    }
/**
 * Eine Liste mit den Sessions
 * @return  die Sessionliste
 */
    public List<Map> sessionMaps() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        sessionMaps = new ArrayList();
        for (HttpSession tmp : asl.getActiveSessions().values()) {

            Date creationDate = new Date(tmp.getCreationTime());
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date inactiveDate = new Date(tmp.getLastAccessedTime());
            this.sessionMap = new HashMap();
            sessionMap.put("id", tmp.getId());

            sessionMap.put("creation", df.format(creationDate));

            sessionMap.put("inactive", df.format(inactiveDate));

            sessionMaps.add((Map) sessionMap);

        }

        return sessionMaps;
    }
    
     private UserHelperRemote uh() {
        try {
            Context c = new InitialContext();
            return (UserHelperRemote) c.lookup("java:global/myNewPizza/mynewpizza-ejb/UserHelper!de.friqql.myNewPizza.UserHelperRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
/**
 * Gibt den Listener zurück
 * @return 
 */
    public ActiveSessionsListener getAsl() {
        return asl;
    }
/**
 * Setzt den Listener
 * @param asl 
 */
    public void setAsl(ActiveSessionsListener asl) {
        this.asl = asl;
    }
/**
 * Gibt die Sessionmap zurück
 * @return 
 */
    public Map getSessionMap() {
        return sessionMap;
    }
/**
 * Setzt die SessionMap
 * @param sessionMap 
 */
    public void setSessionMap(Map sessionMap) {
        this.sessionMap = sessionMap;
    }
/**
 * Gibt die Session Map zurück
 * @return 
 */
    public List<Map> getSessionMaps() {
        return sessionMaps;
    }
/**
 * Setzt die Liste der Session Maps
 * @param sessionMaps 
 */
    public void setSessionMaps(List<Map> sessionMaps) {
        this.sessionMaps = sessionMaps;
    }

    /**
     * Ändert das Passwort eines Users
     * @param helpUser 
     */
    public void setNewOtherPass(User helpUser) {

        helpUser.setUPassword(helpUser.getNewPass());
        uh().changePassword(helpUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
    }


    /**
     * Gibt den User zurück, mit dem gearbeitet wird
     * @return 
     */
    public User getHelpUser() {
        return helpUser;
    }
/**
 * Setzt den User, mit dem gearbeitet wird
 * @param helpUser 
 */
    public void setHelpUser(User helpUser) {
        this.helpUser = helpUser;
    }

    /**
     * Gibt den UserHelper zurück der zum Datenbankzugriff dient
     * @return 
     */
  
/**
 * Setzt einen Vermerk über Probleme beim User
 * @param helpUser 
 */
    public void setVermerk(User helpUser) {
        uh().setVermerk(helpUser);

    }
/**
 * Gibt einen Vermerk zurück
 * @param id
 * @return 
 */
    public String getVermerkById(int id) {

        this.vermerk = uh().getUserVermerkById(id);
        return vermerk;
    }

}
