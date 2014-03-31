package com.ecursus.servlets;

import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.Question;
import com.ecursus.entity.User;
import com.ecursus.service.CertificateService;
import com.ecursus.service.CourseService;
import com.ecursus.service.QuestionService;
import com.ecursus.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuizzServlet", urlPatterns = {"/quizz"})
public class QuizzServlet extends HttpServlet{
    
    @EJB
    QuestionService questionService;
    
    @EJB
    CourseService courseService;
    
    @EJB
    CertificateService certificateService;
    
    @EJB
    UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = null;
        if(request.getParameter("courseId") != null) {
            if (!"".equals(request.getParameter("courseId"))) {
                id = (String) request.getParameter("courseId");
            }
        }
 
        String token = null;
        if(request.getParameter("user") != null) {
            if (!"".equals(request.getParameter("user"))) {
                token = (String) request.getParameter("user");
            }
        }
        
        if(token == null || id == null)
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");
        
        int courseId = Integer.parseInt(id);
        Course course = courseService.findCourseById(courseId);
        if(course == null)
            response.sendRedirect(request.getServletContext().getContextPath() + "/courses");
        
        Collection<Question> questions = questionService.getQuestionsByCourse(course);
        
        request.setAttribute("questions", questions);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/jsp/quizz.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String value1 = request.getParameter("q1");
        String value2 = request.getParameter("q2");
        String value3 = request.getParameter("q3");
        String value4 = request.getParameter("q4");
        String value5 = request.getParameter("q5");
        
        String id = null;
        if(request.getParameter("courseId") != null) {
            if (!"".equals(request.getParameter("courseId"))) {
                id = (String) request.getParameter("courseId");
            }
        }
 
        String token = null;
        if(request.getParameter("user") != null) {
            if (!"".equals(request.getParameter("user"))) {
                token = (String) request.getParameter("user");
            }
        }
        
        if(token == null || id == null)
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");

        HttpSession session = request.getSession();
        if(value1 == null || value2 == null || value3 == null || value4 == null || value5 == null) {
            //session.setAttribute("wrong", true);
           // session.setAttribute("message", "Merci de répondre à toutes les questions");
            response.sendRedirect(request.getServletContext().getContextPath() + "/courses");
        }
        
        int courseId = Integer.parseInt(id);
        User user = userService.findUserByConnectionToken(token);
        if(user == null)
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");
        
        int userId = user.getId();
        /**
         * séparation: l'index 0 contiendra l'id de question
         * et l'index 1 le numéro de la réponse choisie
         */
        
        String[] val1 = value1.split(",");
        String[] val2 = value2.split(",");
        String[] val3 = value3.split(",");
        String[] val4 = value4.split(",");
        String[] val5 = value5.split(",");

        /**
         * tableau associatif key => value
         *          id de question => numéro de la réponse choisie
         */
        Map<Integer,Integer> answers = new HashMap<Integer,Integer>();
        answers.put(Integer.parseInt(val1[0]), Integer.parseInt(val1[1]));
        answers.put(Integer.parseInt(val2[0]), Integer.parseInt(val2[1]));
        answers.put(Integer.parseInt(val3[0]), Integer.parseInt(val3[1]));
        answers.put(Integer.parseInt(val4[0]), Integer.parseInt(val4[1]));
        answers.put(Integer.parseInt(val5[0]), Integer.parseInt(val5[1]));
        
        //récupère le score du quizz
        int score = questionService.validateQuizz(answers);
        Certificate certificate = null;
        
        //3 points sur 5 signifie que le quizz est réussi: on crée un certificat en base
        if(score >= 3)
            certificate = certificateService.passQuizz(userId, courseId, score);
        //sinon on redirige vers la liste des cours
        response.sendRedirect(request.getServletContext().getContextPath() + "/courses");
    }
}
