/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.service;

import com.ecursus.dao.CertificateDao;
import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.User;
import com.ecursus.jms.MessageSender;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class CertificateService {
    
    @EJB
    private CertificateDao certificateDao;
    
    public Collection<Certificate> getAllCertificates() {
        return certificateDao.getAllCertificates();
    }

    public Collection<Certificate> getCertificatesByUser(User user) {
        return certificateDao.getCertificatesByUser(user);
    }
    
    public Collection<Certificate> findCertificatesByCourse(Course course) {
        return certificateDao.findCertificatesByCourse(course);
    }

    public Certificate findCertificateForUserAndCourse(User user, Course course) {
        System.out.println("service courseid et userid:");
        System.out.print(course.getId());
        System.out.print(user.getId());
        System.out.println("-----------");
        User usr = (User) user;
        System.out.println("service nvel userid:");
        System.out.print(usr.getId());
        System.out.println("------------");
        return certificateDao.findCertificateForUserAndCourse(usr, course);
    }
    
    public Certificate findCertificateById(Integer certificateId) {
        return certificateDao.findCertificateById(certificateId);
    }

    public Certificate addCertificate(Certificate certificate) {
        return certificateDao.addCertificate(certificate);
    }

    public Certificate passQuizz(int courseId, int userId, int score) {
        return certificateDao.passQuizz(courseId, userId, score);
    }

    public Certificate updateCertificate(Certificate certificate) {
        return certificateDao.updateCertificate(certificate);
    }

    public void removeCertificate(Certificate certificate) {
        certificateDao.removeCertificate(certificate);
    }
    
    public void printCertificate(int certificateId) throws NamingException {
         new MessageSender(String.valueOf(certificateId));
    }
}
