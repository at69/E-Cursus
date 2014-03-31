/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.servlets;

import com.ecursus.entity.User;
import com.ecursus.service.UserService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.setAttribute("wrong", false);
        session.setAttribute("message", "");
        request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first = request.getParameter("firstname");
        String last = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        String conf = request.getParameter("conf");

        HttpSession session = request.getSession();
        if(first.isEmpty() || last.isEmpty() || mail.isEmpty() || pass.isEmpty()) 
        {
            session.setAttribute("wrong", true);
            session.setAttribute("message", "All fields must be filled");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } 
        else if(conf.equalsIgnoreCase(pass))			// check if the 2 passwords are equal
        {
            if(userService.findByMail(mail) == null)			// check if this mail doesn't exist
            {
                User user = new User(first, last, pass, mail, null);
                userService.addUser(user);
                
                session.setAttribute("wrong", true);
                session.setAttribute("message", "An Email has been sending to your mail address, please wait a couple of time");
                
                response.sendRedirect(request.getContextPath() + "/login");
            }
            else
            {
                session.setAttribute("wrong", true);
                session.setAttribute("message", "Mail already exists");
                request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            }
        }
        else
        {
            session.setAttribute("wrong", true);
            session.setAttribute("message", "The 2 passwords are different");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }

}
