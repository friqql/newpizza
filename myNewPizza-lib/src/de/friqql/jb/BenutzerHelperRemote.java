/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.jb;


import de.friqql.model.Benutzer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface BenutzerHelperRemote {

    public void register(Benutzer helpBenutzer);

    public void updateBenutzer(Benutzer helpBenutzer);

    public void changePassword(Benutzer helpBenutzer);

    public Benutzer getBenutzerByID(int uid);

    public Benutzer getBenutzerByBenutzername(String username);

    public int countBenutzers();

    public void setBenutzerlist(List<Benutzer> benutzerliste);
    
    public List getBenutzerlist();
    
    public void setVermerk(Benutzer helpBenutzer);
    
    public String getBenutzerVermerkById(int id);

}
