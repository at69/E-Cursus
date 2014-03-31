package com.ecursus.soap;

import com.ecursus.entity.Certificate;
import com.ecursus.entity.User;
import com.ecursus.service.CertificateService;
import com.ecursus.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.naming.NamingException;

/**
 *
 * @author Alban
 */
@WebService(serviceName = "CertificatesWebService")
public class CertificatesWebService {
    
    @EJB
    private CertificateService certificateService;
    @EJB
    private UserService userService;
    
    @WebMethod(operationName = "passQuiz")
    public Certificate passQuiz(@WebParam(name = "courseId") int courseId, @WebParam(name = "userId") int userId, @WebParam(name = "score") int score, @WebParam(name = "token") String token) {
        User connectedUser = userService.findUserByConnectionToken(token);
        if(connectedUser != null)
        {
            User user = userService.findUserById(userId);
            Certificate certificate = certificateService.passQuizz(courseId, user.getId(), score);
            return certificate;
        }
        return null;
    }
    
    @WebMethod(operationName = "printCertification")
    public void printCertification(@WebParam(name = "certificate") int certificateId, @WebParam(name = "token") String token) 
    {
       try {
            User connectedUser = userService.findUserByConnectionToken(token);
            if(connectedUser != null)
            {
                certificateService.printCertificate(certificateId);
            }
        
       }catch(NamingException e) {
           Logger.getLogger(CertificatesWebService.class.getName()).log(Level.SEVERE, null, e);
       }
    }
}
