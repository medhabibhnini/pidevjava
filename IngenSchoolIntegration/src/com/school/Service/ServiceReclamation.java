/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import com.school.Entite.Reclamation;
import com.school.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author syrin
 */
public class ServiceReclamation 
{
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();
    }
    
    public void ajouterReclamation(Reclamation R) throws SQLException  {

        String requeteInsert = "INSERT INTO reclamation (nomr,sujetr,dater) VALUES ( '" +R.getNomr()+ "','" +R.getSujetr()+ "','" +R.getDater()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    
     
       finally{ste.close();
       
             
    }
   
    }
        public List<Reclamation> afficherReclamation() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getDate(7)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        

        return list;
        
    }
        
        public int deleteReclamation(int idr) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM reclamation WHERE idr="+idr;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
     }

