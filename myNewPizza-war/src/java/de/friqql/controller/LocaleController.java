/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Teilnehmer
 */
@Named("localeController")
@javax.enterprise.context.SessionScoped
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
     * Gibt die Locale zurück
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

 */
    public void switchLang() {
        if(getLanguage().getLanguage().equals("de"))
        {
        language = new Locale("en");
        }
        else
          {
        language = new Locale("de");
        }   
        FacesContext.getCurrentInstance().getViewRoot().setLocale(language);
        
    }

}
