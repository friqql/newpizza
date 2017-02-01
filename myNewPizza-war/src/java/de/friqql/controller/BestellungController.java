/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;


import de.friqql.jb.ConversionHelperRemote;

import de.friqql.model.Speise;
import de.friqql.model.Bestellung;
import de.friqql.model.Benutzer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import de.friqql.jb.BestellungHelperRemote;

/**
 *
 * @author Teilnehmer
 */
@Named("bestellungController")
@javax.enterprise.context.SessionScoped
public class BestellungController implements Serializable {

    private Bestellung myBestellung;
    private ArrayList<Bestellung> orderL;
    @Inject
    private MessagesController messagesController;
    private double sum;
    private double sum2;
    private Benutzer myBenutzer;
    
    
    private String ipAddress;
    private String sessionId;
    
    
    @Inject
    private BenutzerController benutzerController;

    /**
     * Der Konstruktor des BestellungControllers
     */
    private BestellungHelperRemote oh() {
        try {
            Context c = new InitialContext();
            return (BestellungHelperRemote) c.lookup("ejb/bestellungHelper");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public BenutzerController getuC() {
        return benutzerController;
    }

    public void setuC(BenutzerController uC) {
        this.benutzerController = uC;
    }
    
    
    
    public BestellungController() {
        
    }

    @PostConstruct
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        this.myBestellung = new Bestellung();
        this.orderL = new ArrayList();
        
        this.sum = 0.00;
        this.sum2 = 0.00;
        this.messagesController = new MessagesController();
        
        this.myBenutzer = new Benutzer();
        
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
    
    
    
    /**
     * Gibt die aktuelle Bestellung zurück
     * @return 
     */
    public Bestellung getMyBestellung() {
        return myBestellung;
    }
/**
 * Setzt die aktuelle Bestellung
 * @param myBestellung 
 */
    public void setMyBestellung(Bestellung myBestellung) {
        this.myBestellung = myBestellung;
    }
/**
 * Gibt die Liste der Aktuellen Bestellungen
 * @return 
 */
    public ArrayList getOrderL() {
        return orderL;
    }
/**
 * Setzt die Liste der aktuellen Bestellungen
 * @param orderL 
 */
    public void setOrderL(ArrayList orderL) {
        this.orderL = orderL;
    }
/**
 * Gibt die Summe zurück
 * @return 
 */
    public double getSum() {
        return sum;
    }
/**
 * Setzt die Summe
 * @param sum 
 */
    public void setSum(double sum) {
        this.sum = sum;
    }
/**
 * Gibt die IP zurück
 * @return 
 */
    public String getIpAddress() {
        return ipAddress;
    }
/**
 * Setzt die IP
 * @param ipAddress 
 */
    public void setIpAddress(String ipAddress) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.ipAddress = request.getRemoteAddr();
    }
/**
 * Gibt die SessionId zurück
 * @return 
 */
    public String getSessionId() {
        return sessionId;
    }
/**
 * Setzt die SessionId
 * @param sessionId 
 */
    public void setSessionId(String sessionId) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sess = request.getSession();
        this.sessionId = sess.getId();
    }
/**
 * Fügt eine Bestellung hinzu
 * @param dieListe 
 */
    public void addToBestellung(ArrayList<Speise> dieListe) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();

        int i = 0;
        sum = 0.00;
        this.myBestellung = new Bestellung();
myBestellung.setZwischensumme(0.00);
        for (Speise f : dieListe) {

            if (f.getAmmount() > 0) {

                myBestellung.setFid(f.getId());
                myBestellung.setName(f.getName());
                myBestellung.setAmmount(f.getAmmount());
                myBestellung.setIp(request.getRemoteAddr());
                myBestellung.setSessionId(session.getId());
                myBestellung.setPrice(f.getPrice());
                myBestellung.setZwischensumme(f.getAmmount() * f.getPrice());
                myBestellung.setSince(ch().ConvertDate(new Date()));
                
                myBenutzer = benutzerController.getMyBenutzer();
                myBestellung.setUid(myBenutzer.getId());
                sum += myBestellung.getZwischensumme();
                orderL.add(i, myBestellung);
                myBestellung = new Bestellung();

                i++;

            }

        }
        try {

            if (!orderL.isEmpty()) {
                response.sendRedirect("/myNewPizza-war/zusammenfassung.xhtml");
            } else {
                messagesController.order_e();
            }
        } catch (IOException ex) {
            Logger.getLogger(BestellungController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Setzt die Liste der Bestellungen zurück
 */
    public void reset() {
        orderL = new ArrayList();
    }
/**
 * Testet, ob die Liste der Speisen gefüllt ist
 * @param dieListe
 * @return 
 */
    public boolean full(List<Speise> dieListe) {
        for (Speise f : dieListe) {

            if (f.getAmmount() > 0) {
                return true;
            }
        }
        return false;
    }
/**
 * Speichert die Bestellungen der Liste BestellungL in der Datenbank
 */
    public void save() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();

        try {
            for (Bestellung b : orderL) {
                oh().storeBestellung(b);
            }

            response.sendRedirect("/myNewPizza-war/danke.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(BestellungController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Die anzahl der Bestellungen
 * @return 
 */
    public int num() {

        int num = oh().numBestellungedToday();
        return num;

    }
/**
 * Die Gesamtsumme sämtlicher Bestellungen
 * @return 
 */
    public double sum() {
        sum2 = oh().sumBestellungedToday();
        return sum2;
    }

}
