/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import de.friqql.mynewpizza.model.Food;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Teilnehmer
 */
@Stateless (mappedName="ejb/foodHelper")

public class FoodHelper implements FoodHelperRemote{

    @PersistenceContext(unitName = "myNewPizza-libPU", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    private int foodCount;
    private Food foundFood;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int countFood() {
        Query query = entityManager.createNamedQuery("Food.count");
Long JonSilver = (Long)query.getSingleResult();    
foodCount =  JonSilver.intValue();
        return foodCount;
    }

    @Override
    public Food getFoodById(int fId) {
        Query query = entityManager.createNamedQuery("Food.findByFId");
        foundFood = (Food) query.getSingleResult();
        return foundFood;

    }
}
