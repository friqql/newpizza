/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;

import de.friqql.mynewpizza.ejb.ConversionHelperRemote;
import de.friqql.mynewpizza.ejb.OrderHelperRemote;
import de.friqql.mynewpizza.model.Food;
import de.friqql.mynewpizza.model.POrder;
import de.friqql.mynewpizza.model.User;
import de.friqql.mynewpizza.view.message.MessagesView;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teilnehmer
 */
@ManagedBean
@SessionScoped
public class OrderController implements Serializable {

    private POrder myOrder;
    private ArrayList<POrder> orderL;
    private MessagesView mv;
    private double sum;
    private double sum2;
    private User myUser;
    
    
    private String ipAddress;
    private String sessionId;
    private NavigationController nc;
    private UserController uc;

    /**
     * Der Konstruktor des OrderControllers
     */
    public OrderController() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        this.myOrder = new POrder();
        this.orderL = new ArrayList();
        this.mv = new MessagesView();
        this.sum = 0.00;
        this.sum2 = 0.00;
        this.uc = new UserController();
        
        this.myUser = new User();
        this.nc = new NavigationController();
    }

    private OrderHelperRemote oh() {
        try {
            Context c = new InitialContext();
            return (OrderHelperRemote) c.lookup("java:global/myNewPizza/myNewPizza-ejb/OrderHelper!de.friqql.myNewPizza.OrderHelperRemote");
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
     * Gibt die aktuelle Bestellung zurück
     * @return 
     */
    public POrder getMyOrder() {
        return myOrder;
    }
/**
 * Setzt die aktuelle Bestellung
 * @param myOrder 
 */
    public void setMyOrder(POrder myOrder) {
        this.myOrder = myOrder;
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
    public void addToOrder(ArrayList<Food> dieListe) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();

        int i = 0;
        sum = 0.00;

        for (Food f : dieListe) {

            if (f.getFAmmount() > 0) {

                myOrder.setOFoodId(f.getFId());
                myOrder.setOName(f.getFName());
                myOrder.setOAmmount(f.getFAmmount());
                myOrder.setOIp(request.getRemoteAddr());
                myOrder.setOSessionId(session.getId());
                myOrder.setOPrice(f.getFPrice());
                myOrder.setOzwischensumme(f.getFAmmount() * f.getFPrice());
                myOrder.setOSince(ch().ConvertDate(new Date()));
                uc = (UserController) session.getAttribute("userController");
                myUser = uc.getMyUser();
                myOrder.setOuId(myUser.getUId());
                sum += myOrder.getOzwischensumme();
                orderL.add(i, myOrder);
                myOrder = new POrder();

                i++;

            }

        }
        try {

            if (!orderL.isEmpty()) {
                response.sendRedirect("/myPizza/zusammenfassung.xhtml");
            } else {
                mv.order_e();
            }
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean full(List<Food> dieListe) {
        for (Food f : dieListe) {

            if (f.getFAmmount() > 0) {
                return true;
            }
        }
        return false;
    }
/**
 * Speichert die Bestellungen der Liste OrderL in der Datenbank
 */
    public void save() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();

        try {
            for (POrder o : orderL) {
                oh().storeOrder(o);
            }

            response.sendRedirect("/myPizza/danke.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Die anzahl der Bestellungen
 * @return 
 */
    public int num() {

        int num = oh().numOrderedToday();
        return num;

    }
/**
 * Die Gesamtsumme sämtlicher Bestellungen
 * @return 
 */
    public double sum() {
        sum2 = oh().sumOrderedToday();
        return sum2;
    }

}