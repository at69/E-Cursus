/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao.jpa;

import com.ecursus.dao.CourseDao;
import com.ecursus.entity.Course;
import com.ecursus.entity.User;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class JpaCourseDao implements CourseDao {
    @PersistenceContext(name="ECursus-ejbPU")
    private EntityManager em;

    @Override
    public Collection<Course> getAllCourses() {
        return em.createNamedQuery("Course.findAll").getResultList();
    }

    @Override
    public Collection<Course> findCoursesByCategory(String category) {
        return em.createNamedQuery("Course.findByCategory").setParameter("category", category).getResultList();
    }

    @Override
    public Collection<String> getAllCategories() {
        return em.createNamedQuery("Course.getAllCategories").getResultList();
    }

    @Override
    public Course findCourseById(int courseId) {
        Course course = null;
        try {
            course = em.find(Course.class, courseId);
        } catch(Exception e) {
            System.out.println(e);
        }
        if(course != null)
            return course;
        
        return null;
    }

    @Override
    public Course addCourse(Course course) {
        em.persist(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        em.merge(course);
        return course;
    }

    @Override
    public void removeCourse(Course course) {
        em.remove(course);
    }
    
}
