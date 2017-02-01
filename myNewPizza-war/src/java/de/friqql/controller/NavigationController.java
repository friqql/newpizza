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
    /**
     * Navigation zum Index
     * @return 
     */
    public String toIndex() {
        
            localeController.switchLang(localeController.getLanguage().toString());
        return "toIndex";
    }
/**
 * Navigation zu MyAcconut
 * @return 
 */
    public String tmc() {
         localeController.switchLang(localeController.getLanguage().toString());
        return "tmc";
    }
/**
 * Navigation zu Administration
 * @return 
 */
    public String tad() {
        localeController.switchLang(localeController.getLanguage().toString());
        return "tad";
    }
/**
 * Navigation zur Dankepage
 */
    public void danke(){
        localeController.switchLang(localeController.getLanguage().toString());
        danke2();
    }
    
      public String danke2() {
          localeController.switchLang(localeController.getLanguage().toString());
        return "danke";
    }
      
         public String zsf() {
          localeController.switchLang(localeController.getLanguage().toString());
        return "zsf";
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
