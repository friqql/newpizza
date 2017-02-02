package de.friqql.listener;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.annotation.WebListener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Speichert aktive (gültige) Sessions
 */
@WebListener
public class ActiveSessionsListener implements HttpSessionListener {

   //Map mit Sessions
   private static Map<String, HttpSession> activeS =new HashMap<String, HttpSession>();
  
   /**
    * Wird gerufen, wenn eine Session erzeugt wurde
    * @param event
    * Das event
    */
   @Override
   public void sessionCreated(HttpSessionEvent event) {
     //sessionId wird ermittelt vom HttpSessionEvent
       
      String sessId = event.getSession().getId();

      // Debug-Ausgabe auf der Kommandozeile
      
      System.out.println("Session: " + sessId + " erzeugt.");

      // Speichern der Session in den aktiven Sessions
      activeS.put(sessId, event.getSession());
   }

   /**
    * Wird gerufen, wenn eine Session verfällt oder auch zerstört
    * @param event
    * das event
    */
   @Override
   public void sessionDestroyed(HttpSessionEvent event) {
      // Ermitteln der Session ID vom HttpSessionEvent
      String sessId = event.getSession().getId();

      // Debug-Ausgabe auf der Kommandozeile
      System.out.println("Session: " + sessId + " verfallen.");

      // Entfernen der Session aus den aktiven Sessions
      activeS.remove(sessId);
   }

   /**
    * Gibt die Liste der aktiven Sessions zurück
    * @return activeS
    * Gibt die Liste der aktiven Sessions zurück
    */
   public  static Map<String, HttpSession> getActiveSessions() {
      return activeS;
   }
}
