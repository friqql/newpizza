/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;






import de.friqql.jb.SpeiseHelperRemote;
import de.friqql.model.Speise;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;

import javax.persistence.EntityManager;
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
    private int speiseCount;
    private Speise foundSpeise;
private List<Speise> speiselist;

    public SpeiseHelper() {
        
        speiselist=new ArrayList();
    }



    @Override
    public int countSpeise() {
        Query query = entityManager.createQuery("SELECT s FROM Speise s");
        List<Speise> lf=(List)query.getResultList();
        speiseCount = lf.size();

        return speiseCount;
    }

    @Override
    public Speise getSpeiseById(int fId) {
        Query query = entityManager.createQuery("SELECT s FROM Speise s");
        
        speiselist = (List)query.getResultList();
        
        for(Speise s:speiselist){
            if(s.getId().equals((Integer)fId)){
                foundSpeise = s;
            }
        }
        
        return foundSpeise;

    }
}
