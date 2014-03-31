/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.service;

import com.ecursus.dao.UserDao;
import com.ecursus.entity.User;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class UserService {
    
    @EJB
    private UserDao userDao;
    
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User findUserByConnectionToken(String connectionToken) {
        return userDao.findUserByConnectionToken(connectionToken);
    }

    public User authenticate(String email, String password) {
        return userDao.authenticate(email, password);
    }

    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    public User findByMail(String mail) {
        return userDao.findByMail(mail);
    }
}
