/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;
import com.school.Entite.Commande;
import com.school.Entite.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.school.Utils.DataBase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mohamedhabib - pc
 */
public class ServiceCommande {
       private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceCommande() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouterCommande(Commande C) throws SQLException  {

        String requeteInsert = "INSERT INTO commande (id_user,idlivre,datecommande) VALUES ( '" + C.getUser()+ "','" + C.getL().getIdlivre()+ "','" + C.getDatecommande()+ "');";
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
          public int deleteCommande(int idcommande) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM commande WHERE idcommande="+idcommande;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }

    public int ajouterLivraison(Livraison c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
