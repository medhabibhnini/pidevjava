/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;
import com.school.Entite.Livraison;
import com.school.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamedhabib - pc
 */
public class ServiceLivraison {
           private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceLivraison() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouterLivraison(Livraison C) throws SQLException  {

        String requeteInsert = "INSERT INTO livraison (id_user,idcommande) VALUES ( '" + C.getUser()+ "','" + C.getC().getIdcommande()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       
       return i;      
    }
          public int deleteLivraison(int idlivraison) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM livraison WHERE idlivraison="+idlivraison;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
     /*        public int getCombyIdlivr(int idc) throws SQLException
            
    { 
        int i=0;  
        String name="";
           String query="SELECT idcommande as idcommande FROM livraison WHERE idlivraison='"+idc+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("idlivre");
             i=Integer.valueOf(name);
          
        }
      
      return i;  
    }*/

}