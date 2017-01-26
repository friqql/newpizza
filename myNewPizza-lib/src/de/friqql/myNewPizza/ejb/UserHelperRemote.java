/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import de.friqql.mynewpizza.model.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface UserHelperRemote {

    public void register(User helpUser);

    public void updateUser(User helpUser);

    public void changePassword(User helpUser);

    public User getUserByID(int uid);

    public User getUserByUsername(String username);

    public int countUsers();

    public void setAllUsers(List<User> allUsers);
    
    public List getAllUsers();
    
    public void setVermerk(User helpUser);
    
    public String getUserVermerkById(int id);

}
