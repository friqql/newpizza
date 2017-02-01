/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.jb;



import de.friqql.model.Speise;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface SpeiseHelperRemote {
    public int countSpeise();
public Speise getSpeiseById(int fId);
}
