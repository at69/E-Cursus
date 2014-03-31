/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.soap;

import com.ecursus.service.CourseService;
import com.ecursus.entity.Course;
import com.ecursus.entity.Readcourse;
import com.ecursus.entity.User;
import com.ecursus.service.ReadcourseService;
import com.ecursus.service.UserService;
import java.util.Collection;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Emmanuel
 */
@WebService(serviceName="CoursesWebService")
public class CoursesWebService {
    
    @EJB
    private CourseService courseService;
    @EJB
    private ReadcourseService readCourseService;
    @EJB
    private UserService userService;
    
    @WebMethod(operationName = "getById")
    public Course getById(@WebParam(name = "id") int id) {
        return courseService.findCourseById(id);
    }
    
    @WebMethod(operationName = "listCourses")
    public Collection<Course> listCourses() {
        return courseService.getAllCourses();
    }
    
    @WebMethod(operationName = "takeACourse")
    public Readcourse takeACourse(@WebParam(name = "courseId") int courseId, @WebParam(name = "userId") int userId, @WebParam(name = "token") String token) {
        User connectedUser = userService.findUserByConnectionToken(token);
        if(connectedUser != null)
        {
            User user = userService.findUserById(userId);
            Readcourse readCourse = readCourseService.takeACourse(courseId, user);
            return readCourse;
        }
        return null;
    }
}
