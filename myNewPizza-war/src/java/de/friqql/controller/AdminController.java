/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;





import de.friqql.jb.UsrHelperRemote;
import de.friqql.model.Usr;
import de.friqql.listener.ActiveSessionsListener;


import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

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
 
    private Usr helpUsr;
  
    private String vermerk;

    public AdminController() {
        
    }
    @PostConstruct
    public void init(){
        this.asl = new ActiveSessionsListener();
        this.sessionMap = new HashMap();
        this.sessionMaps = new ArrayList();
      
        this.helpUsr = new Usr();
    
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
    
     private UsrHelperRemote uh() {
        try {
            Context c = new InitialContext();
            return (UsrHelperRemote) c.lookup("ejb/userHelper");
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
     * Ändert das Passwort eines Usrs
     * @param helpUsr 
     */
    public void setNewOtherPass(Usr helpUsr) {

        helpUsr.setUPassword(helpUsr.getNewPass());
        uh().changePassword(helpUsr);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
    }


    /**
     * Gibt den Usr zurück, mit dem gearbeitet wird
     * @return 
     */
    public Usr getHelpUsr() {
        return helpUsr;
    }
/**
 * Setzt den Usr, mit dem gearbeitet wird
 * @param helpUsr 
 */
    public void setHelpUsr(Usr helpUsr) {
        this.helpUsr = helpUsr;
    }

    /**
     * Gibt den UsrHelper zurück der zum Datenbankzugriff dient
     * @return 
     */
  
/**
 * Setzt einen Vermerk über Probleme beim Usr
 * @param helpUsr 
 */
    public void setVermerk(Usr helpUsr) {
        uh().setVermerk(helpUsr);

    }
/**
 * Gibt einen Vermerk zurück
 * @param id
 * @return 
 */
    public String getVermerkById(int id) {

        this.vermerk = uh().getUsrVermerkById(id);
        return vermerk;
    }

}
