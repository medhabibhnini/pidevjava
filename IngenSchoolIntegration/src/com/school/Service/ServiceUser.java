/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.school.Entite.User;
import com.school.Utils.DataBase;

/**
 *
 * @author Daly
 */
public class ServiceUser 
{
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceUser() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouterUser(User U) throws SQLException  {

        String requeteInsert = "INSERT INTO fos_user (username,email,password,roles) VALUES ( '" + U.getUsername()+ "','" +U.getEmail()+ "','" +U.getPassword() + "','" +U.getRole()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       
       return i;
             
    }
        public int deleteUser(int id) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM fos_user WHERE id="+id;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    public int modifier(User U) 
    {
                   int e = 0;

        try {
            String requete = "UPDATE fos_user SET username='" +U.getUsername()+   "',email='" +U.getEmail()+ "',password='" +U.getPassword()+ "',roles='" +U.getRole()+ "' WHERE id=" + U.getId();
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("User modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
    
    
    
    
 
     
        
    
  
}
