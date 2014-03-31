/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.jms;

import com.ecursus.entity.Certificate;
import com.ecursus.entity.Course;
import com.ecursus.entity.User;
import com.ecursus.service.CertificateService;
import com.ecursus.service.CourseService;
import com.ecursus.service.UserService;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MessageSender {
    
   private String params;
   
   @EJB
   private CertificateService certificateService;

   @EJB
   private CourseService courseService;
   
   @EJB
   private UserService userService;
   
   public MessageSender(String params) throws NamingException {
       
       this.params = params;
       
       try{
           produceMessages();
       }catch(JMSException e) {
           Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
       }
   }
   
   private void produceMessages() throws JMSException, NamingException {
       Context ctx = new InitialContext();
       
       ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/GlassFishECursusQueueFactory");
       Destination destination =(Destination) ctx.lookup("jms/GlassFishECursusQueue");
       
       Connection connection = null;
       Session session = null;
       MessageProducer messageProducer = null;
       
       try{
           connection = connectionFactory.createConnection();
           session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
           messageProducer = session.createProducer(destination);
           
           MapMessage mapMessage = session.createMapMessage(); 
           TextMessage textMessage = session.createTextMessage();
           
           int certificateId = Integer.valueOf(this.params);
           Certificate certificate = null;
           //récupérer le certificat 
           try {
            certificate = certificateService.findCertificateById(certificateId);
           }catch(Exception e) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
            }

           if(certificate != null) {
               
               //récupérer l'utilisateur
               User user = userService.findUserById(certificate.getUserId());
               
               //récupérer le cours
               Course course = courseService.findCourseById(certificate.getCourseId());
               
               Boolean isValid = false;
               
               if(user != null) {
                   if(course != null) {
                       isValid = true;
                   }
                   else textMessage.setText("Course with id " + certificate.getCourseId() + " not found.");
               }
               else textMessage.setText("User with id " + certificate.getUserId() + " not found.");
               
               if(isValid) {
                 mapMessage.setString("firstName", user.getFirstName());
                 mapMessage.setString("lastName", user.getLastName());
                 mapMessage.setString("courseName", course.getName());
                 mapMessage.setString("date", certificate.getDate().toString());
               }
                      
           }
           else textMessage.setText("Certificate with id " + this.params + " not found.");
           
           if(textMessage.getText() != null) {
                System.out.println("[JMS] Sending the following message: " + textMessage.getText());
                messageProducer.send(textMessage);
           }
           else {
                System.out.println("[JMS] Sending the following message: " + mapMessage.getMapNames());
                messageProducer.send(mapMessage);
           }
           
           connection.close();
       }catch(JMSException e) {
           e.printStackTrace();
       }
   
   }
}
