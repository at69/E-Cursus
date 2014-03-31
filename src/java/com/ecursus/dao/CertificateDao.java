/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao;

import java.util.Collection;
import javax.ejb.Local;
import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.User;

/**
 *
 * @author Emmanuel
 */
@Local
public interface CertificateDao {
    public Collection<Certificate> getAllCertificates();
    
    public Collection<Certificate> getCertificatesByUser(User user);
    
    public Collection<Certificate> findCertificatesByCourse(Course course);
    
    public Certificate findCertificateForUserAndCourse(User user, Course course);

    public Certificate findCertificateById(int certificateId);
    
    public Certificate addCertificate(Certificate certificate);
    
    public Certificate passQuizz(int courseId, int userId, int score);

    public Certificate updateCertificate(Certificate certificate);
    
    public void removeCertificate(Certificate certificate);
    
}
