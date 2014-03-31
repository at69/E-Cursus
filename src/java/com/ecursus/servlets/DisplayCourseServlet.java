/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.servlets;

import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.Readcourse;
import com.ecursus.entity.User;
import com.ecursus.service.CertificateService;
import com.ecursus.service.CourseService;
import com.ecursus.service.ReadcourseService;
import com.ecursus.service.UserService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
@WebServlet(name = "DisplayCourseServlet", urlPatterns = {"/course"})
public class DisplayCourseServlet extends HttpServlet {

    @EJB
    private UserService userService;
    
    @EJB
    private ReadcourseService readcourseService;
    
    @EJB
    private CourseService courseService;
    
    @EJB
    private CertificateService certificateService;
    
   /* @EJB
    private Course course;
    
    @EJB
    private User user;
    
    @EJB
    private Certificate certificate;*/
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));			// retrieve the link id given in parameter
        String token = null;
        System.out.println("servlet user param:");
        System.out.print(request.getParameter("user"));
        if(request.getParameter("user") != null) {
            if (!"".equals(request.getParameter("user"))) {
                token = (String) request.getParameter("user");
            }
        }
        
        System.out.println("servlet token:");
        System.out.print(token);
        
        Course course = courseService.findCourseById(id);
        System.out.println("servlet course id:");
        System.out.print(course.getId());
        System.out.println("-------------");
        if(token != null) {
            User user = userService.findUserByConnectionToken(token);
            if(user != null) {
                System.out.println("servlet user id:");
                System.out.print(user.getId()); 
                System.out.println("-------------");
                //l'utilisateur a-t-il déjà lu ce cours?
                Readcourse readcourse = readcourseService.findReadcourseForUserAndCourse(user, course);
                //si ce n'est pas le cas, on ajoute une entrée readCourse en base
                if(readcourse == null) {
                    System.out.println("Pas de readcourse en base, on en ajoute un");
                    readcourseService.takeACourse(course.getId(), user);
                    System.out.println("Ajout OK.");
                }
                Certificate certificate = certificateService.findCertificateForUserAndCourse(user, course);
                if(certificate != null) {
                    System.out.println("servlet certif id:");
                    System.out.print(certificate.getId()); 
                    System.out.println("-------------");
                    request.setAttribute("certificate", certificate.getId());
                }
            }
        }
        
        String chaine = "";
	String fichier = getServletContext().getRealPath(course.getPath());
        try {
            InputStream ips = new FileInputStream(fichier); 
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null){
                chaine += ligne + "<br/>";
            }
            br.close(); 
        }		
        catch (Exception e){
            System.out.println(e.toString());
        }
        
        request.setAttribute("course", course);
        request.setAttribute("content", chaine);
        request.getRequestDispatcher("/jsp/courseDetails.jsp").forward(request, response);
    }

}
