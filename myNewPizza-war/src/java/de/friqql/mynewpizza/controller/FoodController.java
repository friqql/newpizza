/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.controller;

import de.friqql.mynewpizza.helper.FoodHelper;
import de.friqql.mypizza.model.Food;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Teilnehmer
 */
@ManagedBean
@SessionScoped
public class FoodController implements Serializable {

    private Food food;
    private FoodHelper helper;
    private int fId;
    private ArrayList<Food> foodlist;
    private ArrayList<Food> antipastilist;
    private ArrayList<Food> pizzalist;
    private ArrayList<Food> pastalist;

    /**
     * Der Konstruktor des FoodControllers
     */
    public FoodController() {
        food = new Food();
        helper = new FoodHelper();
        foodlist = new ArrayList();
        antipastilist = new ArrayList();
        pizzalist = new ArrayList();
        pastalist = new ArrayList();
        for (int i = 1; i <= countFood(); i++) {
            foodlist.add((Food) getFoodById(i));

            if (getFoodById(i).getFsection().equals("Pizza")) {
                pizzalist.add(getFoodById(i));
            } else if (getFoodById(i).getFsection().equals("Pasta")) {
                pastalist.add(getFoodById(i));
            } else {
                antipastilist.add(getFoodById(i));
            }

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
 * Gibt den FoodHelper für den Datenbankzugriff zurück
 * @return 
 */
    public FoodHelper getHelper() {
        return helper;
    }
/**
 * Setzt den FoodHelper für den Datenbankzugriff
 * @param helper 
 */
    public void setHelper(FoodHelper helper) {
        this.helper = helper;
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
 * Die Liste der Antipasti wird zurückgegeben(nicht eingesetzt)
 * @return 
 */
    public ArrayList<Food> getAntipastilist() {
        return antipastilist;
    }
/**
 * Setzt die Liste der Antipasti
 * @param antipastilist 
 */
    public void setAntipastilist(ArrayList antipastilist) {
        this.antipastilist = antipastilist;
    }
/**
 * Die Liste der Pizzas wird zurückgegeben(nicht eingesetzt)
 * @return 
 */
    public ArrayList<Food> getPizzalist() {
        return pizzalist;
    }
/**
 * Setzt die Liste der Pizze
 * @param pizzalist 
 */
    public void setPizzalist(ArrayList pizzalist) {
        this.pizzalist = pizzalist;
    }
/**
 * Die Liste der Pasta wird zurückgegeben(nicht eingesetzt)
 * @return 
 */
    public ArrayList<Food> getPastalist() {
        return pastalist;
    }

    
    /**
 * Setzt die Liste der Pasta
 * @param pastalist 
 */
    public void setPastalist(ArrayList pastalist) {
        this.pastalist = pastalist;
    }
/**
 * Gibt eine Speise zurück nach der fId
 * @param fId
 * @return 
 */
    public Food getFoodById(int fId) {
        food = helper.getFoodById(fId);
        return food;
    }
/**
 * Zählt wieviele Speisen es gibt
 * @return 
 */
    public int countFood() {

        return helper.countFood();
    }
/**
 * Setzt die Speiseliste zurück
 * @param dieListe 
 */
    public void reset(ArrayList<Food> dieListe) {
        for (Food f : dieListe) {

            f.setFammount(0);
        }

    }

}
