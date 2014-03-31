package com.ecursus.servlets;

import com.ecursus.entity.Course;
import com.ecursus.service.CourseService;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/courses")
public class ListCoursesServlet extends HttpServlet {

    @EJB
    private CourseService courseService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Course> courses = courseService.getAllCourses();
        req.setAttribute("courses", courses);
        req.setAttribute("categories", courseService.getAllCategories());
        
        req.getRequestDispatcher("/jsp/listCourses.jsp").forward(req, resp);
    }

}
