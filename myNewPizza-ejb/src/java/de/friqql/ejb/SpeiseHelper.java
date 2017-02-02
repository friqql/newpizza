/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;






import de.friqql.jb.SpeiseHelperRemote;
import de.friqql.model.Benutzer;
import de.friqql.model.Speise;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Teilnehmer
 */
@Stateful (mappedName="ejb/speiseHelper")
@SessionScoped
public class SpeiseHelper implements SpeiseHelperRemote{

    
    
    @PersistenceContext(unitName = "myNewPizza-ejb", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    
private List<Speise> speiselist;

/**
 * Der Standardkonstruktor des Speisehelpers
 */
    public SpeiseHelper() {
        
        speiselist=new ArrayList();
    }


/**
 * Zählt die Speisen
 * @return 
 * Gibt die Anzahl zurück
 */
    @Override
    public int countSpeise() {
        int i;
         Query query = entityManager.createQuery("SELECT s FROM Speise s");
         speiselist = (List<Speise>) query.getResultList();
         i = speiselist.size();
         return i;
    }

    /**
     * Die Speise nach der ID
     * @param fId
     * Die Speise-Id
     * @return 
     * Gibt die Speise zurück
     */
    @Override
    public Speise getSpeiseById(int fId) {
        Speise s;
        Query query = entityManager.createQuery("SELECT s FROM Speise s WHERE s.id =:id");
        query.setParameter("id",fId);
         try {
            s = (Speise) query.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        }
        return s;
        
       

    }
    
    
    
}
