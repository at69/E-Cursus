/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Crocell
 */
public class ECursusMessageReceiverJMS implements MessageListener, ExceptionListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new ECursusMessageReceiverJMS().getMessages();
        } catch (NamingException|InterruptedException ex) {
            Logger.getLogger(ECursusMessageReceiverJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getMessages() throws NamingException, InterruptedException{
        
        Connection connection;
        MessageConsumer messageConsumer;
        InitialContext context = null;
        
      /*
        String host = "localhost";
        String port = "7676";
        Properties props = new Properties();
        
        props.setProperty("org.omg.CORBA.ORBInitialHost", host);
        props.setProperty("org.omg.CORBA.ORBInitialPort", port);
      */
        try{
            context = new InitialContext();
            
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/GlassFishECursusQueueFactory");
            Queue destination = (Queue) context.lookup("jms/GlassFishECursusQueue");

            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(destination);
            
            messageConsumer.setMessageListener(this);
            connection.setExceptionListener(this);
            
            connection.start();

            System.out.println("Waiting for messages...");
            for(int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            System.out.println();
            
            messageConsumer.close();
            session.close();
            connection.close();
        }
            catch (JMSException|NamingException e)
        {
            e.printStackTrace();
        }
        finally{
            System.exit(0); // if you want the program to close after receiving messages
        }
        
     }

    @Override
    public void onMessage(Message message) {
        
        TextMessage textMessage = (TextMessage) message;
        if (textMessage != null){
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException ex) {
                Logger.getLogger(ECursusMessageReceiverJMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void onException(JMSException exception) {
        System.err.println("an error occurred: " + exception);
    }
    
}
