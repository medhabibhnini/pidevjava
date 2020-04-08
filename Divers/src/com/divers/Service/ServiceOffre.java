/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Service;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.divers.Utils.DataBase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.divers.Entite.Offre;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author farouk
 */
public class ServiceOffre {
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
    public ServiceOffre() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouteroffre(Offre O) throws SQLException  {

        String requeteInsert = "INSERT INTO offre (prix,dateDebut,dateFin,description) VALUES ( '" + O.getPrixoffre()+ "','" + O.getDate_debut()+ "','" + O.getDate_fin()+ "','" + O.getDescription()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       return i ;
             
    }
    
     public int deleteoffre(int idoffre) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM offre WHERE id="+idoffre;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
     
     
     public int modifieroffre(Offre O) {
                   int e = 0;

        try {
            String requete = "UPDATE offre SET prix='" + O.getPrixoffre()+   "',dateDebut='" + O.getDate_debut()+ "',dateFin='" + O.getDate_fin()+"',description='" + O.getDescription()+ "' WHERE id=" + O.getIdoffre();
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("Offre modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
     
     public List<Offre> afficheroffre() {
        List<Offre> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Offre(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
}
