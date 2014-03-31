/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao;

import com.ecursus.entity.Course;
import java.util.Collection;
import javax.ejb.Local;
import com.ecursus.entity.Readcourse;
import com.ecursus.entity.User;

/**
 *
 * @author Emmanuel
 */
@Local
public interface ReadcourseDao {
    public Collection<Readcourse> getAllReadcourses();
    
    public Collection<Readcourse> findReadcoursesByUser(User user);
    
    public Readcourse findReadcourseForUserAndCourse(User user, Course course);

    public Readcourse findReadcourseById(int readCourseId);
    
    public Readcourse addReadcourse(Readcourse readCourse);
    
    public Readcourse takeACourse(int courseId, User user);

    public Readcourse updateReadcourse(Readcourse readCourse);
    
    public void removeReadcourse(Readcourse readCourse);
}
