/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;

import de.friqql.mypizza.model.Food;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Teilnehmer
 */
@ManagedBean
@SessionScoped
public class LocaleController implements Serializable {

    private Locale language;

    /**
     * Der Konstruktor für den Language Controller
     */
    public LocaleController() {
        this.language = new Locale("de");
    }

    
    
    /**
     * Gibt die Locale zurück
     * @return 
     */
    public Locale getLanguage() {
        return language;
    }
/**
 * Setzt die Locale
 * @param language 
 */
    public void setLanguage(Locale language) {
        this.language = language;
    }
/**
 * Ändert die Sprache
 * @param l 
 */
    public void switchLang(String l) {
        language = new Locale(l);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(language);
        
    }

}
