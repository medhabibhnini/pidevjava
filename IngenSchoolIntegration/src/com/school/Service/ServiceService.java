/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;


import com.school.Entite.Reclamation;
import com.school.Entite.Service;
import com.school.Utils.DataBase;
import com.school.Entite.SendEmail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author syrin
 */


public class ServiceService {
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceService() {
        con = DataBase.getInstance().getConnection();
    }
      public List<Service> afficherService() {
        List<Service> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM service ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Service(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDate(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
        return list;
    }
    
      public void ajouterService(Service R) throws SQLException, MessagingException  {

        String requeteInsert = "INSERT INTO service (description,date,ida) VALUES ( '" +R.getDescription()+ "','" +R.getDate()+ "','"+R.getIda()+"');";
        int i = 0;  
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
       }
        finally{ste.close();
		
      }
       
   
      }
    
    public void sendEmail(){
      String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "syrine.kerriou@esprit.tn";
        String password = "07494750";
        String mailTo = "syrine.kerriou@gmail.com";
        String subject = "Validating email ";
        String message =" done";
        SendEmail mailer = new SendEmail();
           try {
                    mailer.SendEmail(host, port, mailFrom, password, mailTo,subject, message);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Failed to sent email.");
                    ex.printStackTrace();
                }

    }    
                    
				 public int deleteService(int ids) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM service WHERE ids="+ids;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
}

       
              
    
      
     
    

