/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao;

import java.util.Collection;
import javax.ejb.Local;
import com.ecursus.entity.User;

/**
 *
 * @author Emmanuel
 */
@Local
public interface UserDao {
    public Collection<User> getAllUsers();

    public User findUserById(int userId);
    
    public User authenticate(String email, String password);
    
    public User findUserByConnectionToken(String connectionToken);
    
    public User addUser(User user);

    public User updateUser(User user);
    
    public void removeUser(User user);

    public User findByMail(String mail);
}
