/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.jb;


import de.friqql.model.Bestellung;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface BestellungHelperRemote {
    public double sumBestellungedToday();
public int numBestellungedToday();
public void storeBestellung(Bestellung saveBestellung);
}
