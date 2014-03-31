/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.service;

import com.ecursus.dao.ReadcourseDao;
import com.ecursus.entity.Course;
import com.ecursus.entity.Readcourse;
import com.ecursus.entity.User;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class ReadcourseService {
    
    @EJB
    private ReadcourseDao readCourseDao;
    
    public Collection<Readcourse> getAllReadcourses() {
        return readCourseDao.getAllReadcourses();
    }

    public Collection<Readcourse> getReadcoursesByUser(User user) {
        return readCourseDao.findReadcoursesByUser(user);
    }
    
    public Readcourse findReadcourseForUserAndCourse(User user, Course course) {
        System.out.println("service courseid et userid:");
        System.out.print(course.getId());
        System.out.print(user.getId());
        System.out.println("-----------");
        User usr = (User) user;
        System.out.println("service nvel userid:");
        System.out.print(usr.getId());
        System.out.println("------------");
        return readCourseDao.findReadcourseForUserAndCourse(usr, course);
    }

    public Readcourse findReadcourseById(int readCourseId) {
        return readCourseDao.findReadcourseById(readCourseId);
    }

    public Readcourse addReadcourse(Readcourse readCourse) {
        return readCourseDao.addReadcourse(readCourse);
    }

    public Readcourse takeACourse(int courseId, User user) {
        return readCourseDao.takeACourse(courseId, user);
    }

    public Readcourse updateReadcourse(Readcourse readCourse) {
        return readCourseDao.updateReadcourse(readCourse);
    }

    public void removeReadcourse(Readcourse readCourse) {
        readCourseDao.removeReadcourse(readCourse);
    }
}
