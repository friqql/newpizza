/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;


import java.io.IOException;
import java.io.Serializable;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teilnehmer
 */
@Named("navigationController")
@ManagedBean
@SessionScoped
public class NavigationController implements Serializable {

    /**
     * Navigation zum Index
     * @return 
     */
    public String toIndex() {
        return "toIndex";
    }
/**
 * Navigation zu MyAcconut
 * @return 
 */
    public String tmc() {
        return "tmc";
    }
/**
 * Navigation zu Administration
 * @return 
 */
    public String tad() {
        return "tad";
    }
/**
 * Navigation zur Dankepage
 */
    public void danke(){
        danke2();
    }
    
      public String danke2() {
        return "danke";
    }
    /**
     * Navigation zur Rechnung
     */
    public void tr() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpSession session = request.getSession();
        try {
           response.sendRedirect("/myPizza/rechnung");
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
