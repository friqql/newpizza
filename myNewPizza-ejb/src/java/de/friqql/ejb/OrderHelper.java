/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;




import de.friqql.jb.OrderHelperRemote;
import de.friqql.model.POrder;
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
@Stateful(mappedName="ejb/orderHelper")
@SessionScoped
public class OrderHelper implements OrderHelperRemote {
    @PersistenceContext(unitName = "myNewPizza-ejb", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
double sum;
int num;
private List<POrder> od;

    public OrderHelper() {
        od = new ArrayList();
    }
    
    



    public double sumOrderedToday(){
        Query query = entityManager.createQuery("SELECT p FROM POrder p WHERE p.oSince = CURRENT_DATE");
        od = (List)query.getResultList();
       sum= 0.00;
            for(POrder o:od){
                
                sum= sum+(o.getOAmmount()*o.getOPrice());
            }
        
       
       
       
       // POrder.count
       
        return sum;
    }
    
public int numOrderedToday(){
    num = 0;
    Query query = entityManager.createQuery("SELECT p FROM POrder p WHERE p.oSince = CURRENT_DATE");
    od = (List)query.getResultList();
    for(POrder o:od)
    {
        
            num++;
       
    }
    
    return num;
}
public void storeOrder(POrder saveOrder){
    entityManager.merge(saveOrder);
    entityManager.flush();
}
}
