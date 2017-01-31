/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.controller;





import de.friqql.jb.FoodHelperRemote;
import de.friqql.model.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Teilnehmer
 */
@Named("foodController")
@javax.enterprise.context.SessionScoped
public class FoodController implements Serializable {

    private Food food;
    
    private int fId;
    private ArrayList<Food> foodlist;
   

    
     private FoodHelperRemote helper() {
        try {
            Context c = new InitialContext();
             FoodHelperRemote sepp = (FoodHelperRemote)c.lookup("ejb/foodHelper");
            return (sepp);
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
          
        }
    }
    
    
    /**
     * Der Konstruktor des FoodControllers
     */
    public FoodController() {
     
    }
    @PostConstruct
    public void init(){
        food = new Food();
        
        foodlist = new ArrayList();
        
        for (int i = 1; i <= countFood(); i++) {
            foodlist.add((Food) getFoodById(i));

        }
    }
/**
 * Gibt eine Speise zurück
 * @return 
 */
    public Food getFood() {
        return food;
    }
/**
 * Setzt eine Speise
 * @param food 
 */
    public void setFood(Food food) {
        this.food = food;
    }
   
   
/**
 * Gibt eine fId einer Speise zurück
 * @return 
 */
    public int getfId() {
        return fId;
    }
/**
 * Setzt eine fId
 * @param fId 
 */
    public void setfId(int fId) {
        this.fId = fId;
    }
/**
 * Die Liste der Speisen wird zurückgegeben
 * @return 
 */
    public ArrayList<Food> getFoodlist() {

        return foodlist;
    }
/**
 * Die Liste der Speisen wird gesetzt
 * @param foodlist 
 */
    public void setFoodlist(ArrayList foodlist) {
        this.foodlist = foodlist;
    }

   
/**
 * Gibt eine Speise zurück nach der fId
 * @param fId
 * @return 
 */
    public Food getFoodById(Integer fId) {
        food = helper().getFoodById(fId);
        return food;
    }
/**
 * Zählt wieviele Speisen es gibt
 * @return 
 */
    public int countFood() {

        return helper().countFood();
    }
/**
 * Setzt die Speiseliste zurück
 * @param dieListe 
 */
    public void reset(ArrayList<Food> dieListe) {
        for (Food f : dieListe) {

            f.setFAmmount(0);
        }

    }

}
