/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;




import de.friqql.model.Bestellung;
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
import de.friqql.jb.BestellungHelperRemote;

/**
 *
 * @author Teilnehmer
 */
@Stateful(mappedName="ejb/bestellungHelper")
@SessionScoped
public class BestellungHelper implements BestellungHelperRemote {
    @PersistenceContext(unitName = "myNewPizza-ejb", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
double sum;
int num;
private List<Bestellung> od;

    public BestellungHelper() {
        od = new ArrayList();
    }
    
    



    @Override
    public double sumBestellungedToday(){
        Query query = entityManager.createQuery("SELECT b FROM Bestellung b WHERE b.since = CURRENT_DATE");
        od = (List)query.getResultList();
       sum= 0.00;
            for(Bestellung b:od){
                
                sum= sum+(b.getAmmount()*b.getPrice());
            }
        
       
       
       
       // Bestellung.count
       
        return sum;
    }
    
    @Override
    public int numBestellungedToday(){
    num = 0;
    Query query = entityManager.createQuery("SELECT b FROM Bestellung b WHERE b.since = CURRENT_DATE");
    od = (List)query.getResultList();
    for(Bestellung b:od)
    {
        
            num++;
       
    }
    
    return num;
}
    @Override
    public void storeBestellung(Bestellung saveBestellung){
    entityManager.merge(saveBestellung);
    entityManager.flush();
}
}
