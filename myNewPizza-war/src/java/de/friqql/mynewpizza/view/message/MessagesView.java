/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.view.message;

import de.friqql.mynewpizza.controller.LocaleController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Teilnehmer
 */
@ManagedBean
public class MessagesView {
     
    private LocaleController lc;

    public MessagesView() {
  
        this.lc = new LocaleController();
    }
    
    
    
    /**
     * Registrierung erfolgreich
     */
    public void reg_s() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registriert", "Registration erfolgreich!"));
    }
    /**
     * Registrierung nicht erfolgreich
     */
        public void reg_e() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nicht Registriert", "Registration nicht erfolgreich! (Username bereits vergeben oder Pflichtfeld nicht ausgefüllt)"));
    }
     /**
      * Bestellung erfolgreich
      */
     public void order_s() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bestellung erfolgreich", "Die Bestellung war erfolgreich!"));
    }
    /**
     * Bestellung nicht erfolgreich
     */
      public void order_e() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Keine Bestellung", "Bestellen Sie etwas!"));
    }
      
      /**
       * Passwort geändert
       */
      public void cp_s() {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwort geändert", "Hat funktioniert!"));
      }
      /**
       * Fehler beim Ändern des Passworts
       */
      public void cp_e() {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Passwort nicht geändert", "Fehler beim ändern des Passworts!"));
      }
      /**
       * Das Passwortfeld ist leer
       * @return 
       */
      public String pwnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Passwort darf nicht leer sein!";
          }
          else{
            
          return "The field Password must not be empty!"; 
          }
      }
      /**
       * Das Benutzernamefeld ist leer
       * @return 
       */
       public String bnnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Benutzername darf nicht leer sein!";
          }
          else{
            
          return "The field Username must not be empty!"; 
          }
      }
       /**
        * Der Vorname ist leer
        * @return 
        */
       public String fnnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Vorname darf nicht leer sein!";
          }
          else{
            
          return "The field First Name must not be empty!"; 
          }
      }
       /**
        * Der Nachname ist leer
        * @return 
        */
       public String lnnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Nachname darf nicht leer sein!";
          }
          else{
            
          return "The field Last Name must not be empty!"; 
          }
      }
      /**
       * Die Straße ist leer
       * @return 
       */
      public String stnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Straße darf nicht leer sein!";
          }
          else{
            
          return "The field Street must not be empty!"; 
          }
      }
      /**
       * Die Hausnummer ist leer
       * @return 
       */
      public String hnnl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Hausnummer darf nicht leer sein!";
          }
          else{
            
          return "The field House must not be empty!"; 
          }
      }
      /**
       * Die PLZ ist leer
       * @return 
       */
      public String plznl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Postleitzahl darf nicht leer sein!";
          }
          else{
            
          return "The field Street must not be empty!"; 
          }
      }
      /**
       * Der Ort ist leer
       * @return 
       */
      public String ornl(){
          if (lc.getLanguage().getLanguage().equals("de")){
          
          return "Das Feld Ort darf nicht leer sein!";
          }
          else{
            
          return "The field Place must not be empty!"; 
          }
      }
      
      
      
}
