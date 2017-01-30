/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.ejb;






import de.friqql.jb.FoodHelperRemote;
import de.friqql.model.Food;
import java.util.ArrayList;
import java.util.Date;
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
@Stateless (mappedName="ejb/foodHelper")

public class FoodHelper implements FoodHelperRemote{

    
    
    @PersistenceContext(unitName = "myNewPizza-ejb", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    private int foodCount;
    private Food foundFood;
private List<Food> foodlist;

    public FoodHelper() {
        
        foodlist=new ArrayList();
    }



    @Override
    public int countFood() {
        Query query = entityManager.createQuery("SELECT f FROM Food f");
        List<Food> lf=(List)query.getResultList();
        foodCount = lf.size();

        return foodCount;
    }

    @Override
    public Food getFoodById(int fId) {
        Query query = entityManager.createQuery("SELECT f FROM Food f");
        
        foodlist = (List)query.getResultList();
        
        for(Food f:foodlist){
            if(f.getFId().equals((Integer)fId)){
                foundFood = f;
            }
        }
        
        return foundFood;

    }
}
