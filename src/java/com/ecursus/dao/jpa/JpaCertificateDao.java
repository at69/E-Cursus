/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao.jpa;

import com.ecursus.dao.CertificateDao;
import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.User;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class JpaCertificateDao implements CertificateDao {
    @PersistenceContext(name="ECursus-ejbPU")
    private EntityManager em;

    @Override
    public Collection<Certificate> getAllCertificates() {
        return em.createNamedQuery("Certificate.findAll").getResultList();
    }

    @Override
    public Collection<Certificate> getCertificatesByUser(User user) {
        return em.createNamedQuery("Certificate.findByUserId").setParameter("userId", user.getId()).getResultList();
    }

    @Override
    public Certificate findCertificateById(int certificateId) {
        return em.find(Certificate.class, certificateId);
    }

    @Override
    public Certificate addCertificate(Certificate certificate) {
        em.persist(certificate);
        return certificate;
    }

    @Override
    public Certificate passQuizz(int courseId, int userId, int score) {
        Certificate certificate = new Certificate(courseId, userId, new Date(), score);
        em.persist(certificate);
        return certificate;
    }

    @Override
    public Certificate updateCertificate(Certificate certificate) {
        em.merge(certificate);
        return certificate;
    }

    @Override
    public void removeCertificate(Certificate certificate) {
        em.remove(certificate);
    }

    @Override
    public Collection<Certificate> findCertificatesByCourse(Course course) {
        return em.createNamedQuery("Certificate.findByCourseId")
                .setParameter("courseId", course.getId())
                .getResultList();
    }

    @Override
    public Certificate findCertificateForUserAndCourse(User user, Course course) {
        Certificate crt = null;
        try {
            crt = (Certificate) em.createNamedQuery("Certificate.findByUserAndCourse")
                 .setParameter("userId", user.getId())
                 .setParameter("courseId", course.getId())
                 .getSingleResult(); 
        } catch(Exception e){
            System.out.println(e);
        }
        if(crt != null)
            return crt;
        
        return null;       
    }
    
}
