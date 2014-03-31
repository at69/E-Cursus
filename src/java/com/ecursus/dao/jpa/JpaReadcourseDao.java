/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao.jpa;

import com.ecursus.dao.CourseDao;
import com.ecursus.dao.ReadcourseDao;
import com.ecursus.entity.Course;
import com.ecursus.entity.Readcourse;
import com.ecursus.entity.User;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class JpaReadcourseDao implements ReadcourseDao {
    @PersistenceContext(name="ECursus-ejbPU")
    private EntityManager em;

    @EJB
    private CourseDao courseDao;
    
    @Override
    public Collection<Readcourse> getAllReadcourses() {
        return em.createNamedQuery("Readcourse.findAll").getResultList();
    }

    @Override
    public Collection<Readcourse> findReadcoursesByUser(User user) {
        return em.createNamedQuery("Readcourse.findByUserId").setParameter("userId", user.getId()).getResultList();
    }

    @Override
    public Readcourse findReadcourseById(int readCourseId) {
        return em.find(Readcourse.class, readCourseId);
    }

    @Override
    public Readcourse addReadcourse(Readcourse readCourse) {
        em.persist(readCourse);
        return readCourse;
    }

    @Override
    public Readcourse takeACourse(int courseId, User user) {
        Readcourse readCourse = new Readcourse(courseId, user.getId(), new Date()); 
        this.addReadcourse(readCourse);
        return readCourse;
    }

    @Override
    public Readcourse updateReadcourse(Readcourse readCourse) {
        em.merge(readCourse);
        return readCourse;
    }

    @Override
    public void removeReadcourse(Readcourse readCourse) {
        em.remove(readCourse);
    }

    @Override
    public Readcourse findReadcourseForUserAndCourse(User user, Course course) {
        Readcourse rc = null;
        try {
            rc = (Readcourse) em.createNamedQuery("Readcourse.findByUserAndCourse")
                                .setParameter("userId", user.getId())
                                .setParameter("courseId", course.getId())
                                .getSingleResult();
        } catch(Exception e) {
            System.out.println(e);
        }
        if(rc != null)
            return rc;
        
        return null;
    }
    
}
