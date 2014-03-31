/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao;

import java.util.Collection;
import javax.ejb.Local;
import com.ecursus.entity.Course;

/**
 *
 * @author Emmanuel
 */
@Local
public interface CourseDao {
    public Collection<Course> getAllCourses();

    public Course findCourseById(int courseId);
    
    public Collection<Course> findCoursesByCategory(String category);
    
    public Collection<String> getAllCategories();
    
    public Course addCourse(Course course);

    public Course updateCourse(Course course);
    
    public void removeCourse(Course course);
}
