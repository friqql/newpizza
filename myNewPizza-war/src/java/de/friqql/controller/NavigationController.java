/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;


import java.io.IOException;
import java.io.Serializable;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teilnehmer
 */
@Named("navigationController")
@javax.enterprise.context.SessionScoped
public class NavigationController implements Serializable {
@Inject
private LocaleController localeController;
private String page;

    public NavigationController() {
        
        page = "toIndex";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }





    /**
     * Navigation zum Index
    
     */
    public void toIndex() {
        
            FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLanguage());
            page ="toIndex";
       
    }
/**
 * Navigation zu MyAcconut

 */
    public void tmc() {
         FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLanguage());
         page = "tmc";
        
    }
/**
 * Navigation zu Administration

 */
    public void tad() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLanguage());
        page = "tad";
        
    }
/**
 * Navigation zur Dankepage

 */
    public void danke(){
        FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLanguage());
          page="danke";
    }
    
      
         public void zsf() {
          FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLanguage());
          page="zsf";
        
    }
    /**
     * Navigation zur Rechnung
     */
    public void tr() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();
        try {
           response.sendRedirect("/myNewPizza-war/rechnung");
        } catch (IOException ex) {
            Logger.getLogger(BestellungController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
