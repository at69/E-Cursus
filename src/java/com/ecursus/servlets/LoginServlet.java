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

/**
 *
 * @author Emmanuel
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.authenticate(email, password);
        
        if(user != null) {
            req.getSession().setAttribute("user", user.getConnectionToken());
            resp.sendRedirect(req.getContextPath() + "/courses");
        } else {
            doGet(req, resp);
        }
    }

}
