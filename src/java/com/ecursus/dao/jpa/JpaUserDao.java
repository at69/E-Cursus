/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao.jpa;

import com.ecursus.dao.UserDao;
import com.ecursus.encryptmanager.EncryptManager;
import com.ecursus.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class JpaUserDao implements UserDao {
    @PersistenceContext(name="ECursus-ejbPU")
    private EntityManager em;

    @Override
    public Collection<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findUserById(int userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User findUserByConnectionToken(String connectionToken) {
        return (User)em.createNamedQuery("User.findByConnectionToken")
                        .setParameter("connectionToken", connectionToken)
                        .getSingleResult();
    }

    @Override
    public User authenticate(String email, String password) {
        String hashedPassword;
        User user = null;
        try {
            hashedPassword = EncryptManager.getInstance().encrypt(password);
            List<User> users = em.createNamedQuery("User.findByAuthentication")
                                .setParameter("email", email)
                                .setParameter("password", hashedPassword)
                                .getResultList();
            
            if(users.size() == 1)
            {
                user = users.get(0);
            }
            if(user != null)
            {
                UUID token = UUID.randomUUID();
                user.setConnectionToken(token.toString());
                
                em.merge(user);
                return user;
            }
        } catch (Exception ex) {
            Logger.getLogger(JpaUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        em.merge(user);
        return user;
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public User findByMail(String mail) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email")
                        .setParameter("email", mail);
        
        List<User> users = query.getResultList();
        User user = null;
        
        if(users.size() == 1)
        {
            user = users.get(0);
        }
        return user;
    }
    
}
