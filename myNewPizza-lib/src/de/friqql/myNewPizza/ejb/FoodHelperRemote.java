/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import de.friqql.mynewpizza.model.Food;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface FoodHelperRemote {
    public int countFood();
public Food getFoodById(int fId);
}