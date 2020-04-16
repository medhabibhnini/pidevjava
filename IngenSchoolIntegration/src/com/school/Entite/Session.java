/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;

import java.util.Properties;
import javax.mail.Authenticator;

/**
 *
 * @author Daly
 */
public class Session 
{
     private static int idSession;
    public static void start(int currentUserID) {
        idSession = currentUserID;
    }
    public static int getIdSession() {
        return idSession;
    }

    public static void setIdSession(int idSession) {
        Session.idSession = idSession;
    }
     public static int getCurrentSession() {
        if (idSession != -1) 
            
            return idSession;
        return -1;
        
    }
      public static void close() throws Exception {
        if (idSession != -1) {
            idSession = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static Session getDefaultInstance(Properties props, Authenticator authenticator) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
