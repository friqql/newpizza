/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import de.friqql.mynewpizza.model.POrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Teilnehmer
 */
@Stateless(mappedName="ejb/orderHelper")
public class OrderHelper {
    @PersistenceContext(unitName = "myNewPizza-libPU", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
double sum;
int num;
private List<POrder> od;
    
    
    public double sumOrderedToday(){
        Query query = entityManager.createNamedQuery("POrder.findAll");
        od = (List)query.getResultList();
       sum= 0.00;
            for(POrder o:od){
                sum= sum+(o.getOAmmount()*o.getOPrice());
            }
        
       
       
       
       // POrder.count
       
        return sum;
    }
    
public int numOrderedToday(){
    Query query = entityManager.createNamedQuery("POrder.count");
    return (int) query.getSingleResult();
}
public void storeOrder(POrder saveOrder){
    
}
}
