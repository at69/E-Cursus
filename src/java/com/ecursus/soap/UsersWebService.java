package com.ecursus.soap;

import com.ecursus.entity.User;
import javax.ejb.EJB;
import javax.jws.WebService;
import com.ecursus.service.UserService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alban
 */
@WebService(serviceName = "UsersWebService")
public class UsersWebService {
    @EJB
    private UserService userService;
    
    @WebMethod(operationName = "authenticate")
    public String authenticate(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        User user = userService.authenticate(email, password);
        
        if(user != null)
        {
            return user.getConnectionToken();
        }
        return null;
    }
            
            
    @WebMethod(operationName = "findUserById")
    public User findUserById(@WebParam(name = "userId") int userId, @WebParam(name = "token") String token) {
        User connectedUser = userService.findUserByConnectionToken(token);
        if(connectedUser != null)
        {
            User user = userService.findUserById(userId);
            return user;
        }
        return null;
    }
}
