/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.Service;
import com.bibliotheque.Entite.Livraison;
import com.bibliotheque.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
