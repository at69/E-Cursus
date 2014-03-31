/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.service;

import com.ecursus.dao.CourseDao;
import com.ecursus.entity.Course;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class CourseService {
    
    @EJB
    private CourseDao courseDao;
    
    public Collection<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public Collection<Course> getCoursesByCategory(String category) {
        return courseDao.findCoursesByCategory(category);
    }

    public Collection<String> getAllCategories() {
        return courseDao.getAllCategories();
    }

    public Course findCourseById(int courseId) {
        return courseDao.findCourseById(courseId);
    }

    public Course addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    public Course updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    public void removeCourse(Course course) {
        courseDao.removeCourse(course);
    }
}
