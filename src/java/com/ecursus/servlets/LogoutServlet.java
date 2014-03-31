/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecursus.servlets;

import com.ecursus.entity.User;
import com.ecursus.service.UserService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @EJB
    private UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute("user");
        User user = userService.findUserByConnectionToken(token);
        user.setConnectionToken(null);
        userService.updateUser(user);
        
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login");
    }
    
}
