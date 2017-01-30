/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.jb;


import de.friqql.model.Usr;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface UsrHelperRemote {

    public void register(Usr helpUsr);

    public void updateUsr(Usr helpUsr);

    public void changePassword(Usr helpUsr);

    public Usr getUsrByID(int uid);

    public Usr getUsrByUsrname(String username);

    public int countUsrs();

    public void setAllUsrs(List<Usr> allUsrs);
    
    public List getAllUsrs();
    
    public void setVermerk(Usr helpUsr);
    
    public String getUsrVermerkById(int id);

}
