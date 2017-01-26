/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import de.friqql.mynewpizza.ejb.UserHelperRemote;
import de.friqql.mynewpizza.model.User;
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
@Stateless(mappedName="ejb/userHelper")
public class UserHelper implements UserHelperRemote {

    @PersistenceContext(unitName = "myNewPizza-libPU", type= PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    private User dbUser;
    private ConversionHelper cc;
    private int i;
    private List allUsers;

    @Override
    public void register(User helpUser) {
        this.dbUser = helpUser;
//        
        helpUser.setURole("Customer");
        helpUser.setUVermerk("Alles Ok!");
        helpUser.setUSince(new Date());
        entityManager.persist(helpUser);
        entityManager.flush();

    }

    @Override
    public void updateUser(User helpUser) {
        this.dbUser = helpUser;

        entityManager.persist(dbUser);
        entityManager.flush();

    }

    @Override
    public void changePassword(User helpUser) {
        this.dbUser = helpUser;

        dbUser.setUPassword(cc.hash(dbUser.getNewPass()));
        dbUser.setUVermerk("Alles Ok!");
        dbUser.setUSince(new Date());
        entityManager.persist(dbUser);
        entityManager.flush();

    }

    @Override
    public User getUserByID(int uid) {

        entityManager.find(User.class, uid);
        return dbUser;
    }

    @Override
    public User getUserByUsername(String username) {
        Query query = entityManager.createNamedQuery("User.findByUUsername");
        query.setParameter("uUsername", username);
        dbUser = (User) query.getSingleResult();
        return dbUser;
    }

    @Override
    public int countUsers() {
        Query query = entityManager.createNamedQuery("User.count");

        i = (int) query.getSingleResult();
        return i;
    }

    @Override
    public List getAllUsers() {
        Query query = entityManager.createNamedQuery("User.findAll");
        allUsers = (List) query.getResultList();
        return allUsers;
    }

    /**
     * Setzt die Liste aller User
     *
     * @param allUsers
     */
    @Override
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    @Override
    public void setVermerk(User helpUser) {
        helpUser.setUVermerk(helpUser.getUVermerk());
        entityManager.persist(dbUser);
        entityManager.flush();
    }

    @Override
    public String getUserVermerkById(int id) {
        User helpUser = entityManager.find(User.class, id);
        return helpUser.getUVermerk();

    }
}
