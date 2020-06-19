/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import com.school.Entite.Cours;
import com.school.Entite.Formation;
import com.school.Utils.DataSource;
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
public class ServiceFormation {
    
      private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceFormation() {
        con = DataSource.getInstance().getCnx();
    }
    
    public int ajouterF(Formation F) throws SQLException  {

        String requeteInsert = "INSERT INTO formation (nomFormation,matiere,duree,prix,coursId) VALUES ('" +F.getNomf()+ "','" +F.getMatieref()+ "','" +F.getDureef()+ "','" +F.getPrixf()+ "','" +F.getIdc()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       return i;
             
    }
    
      public List<Formation> afficheF() {
        List<Formation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM formation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                  list.add(new Formation( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));

                 
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

      
         public int modifierF(Formation F) {
                   int e = 0;

        try {
            String requete = "UPDATE formation SET nomFormation='" + F.getNomf()+   "',matiere='" + F.getMatieref()+ "',duree='" + F.getDureef()+ "',prix='" + F.getPrixf()+ "',coursId='" + F.getIdc()+ "' WHERE id=" + F.getIdf();
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("Formation modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
      
      
       public int deleteF(int idf) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM formation WHERE id="+idf;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
        /*public Attestation getByUsername(String typea){
        Attestation tmp = new Attestation();
        try {
            String requete = "SELECT ida,typea,langue FROM attestation where typea='"+typea+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tmp=new Attestation(rs.getInt(1), rs.getString(2), rs.getString(3));
                System.out.print(tmp.getIda());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tmp;
    }*/
    
}

