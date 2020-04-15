/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Scolarite.Service;

import com.Scolarite.Entite.Attestation;
import com.Scolarite.Utils.DataBase;
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
public class ServiceAttestation {
    
      private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceAttestation() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouterAttestation(Attestation R) throws SQLException  {

        String requeteInsert = "INSERT INTO attestation (typea,langue) VALUES ('" +R.getLangue()+ "','" +R.getTypea()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceAttestation.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       return i;
             
    }
    
      public List<Attestation> afficheAttestation() {
        List<Attestation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM attestation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                  list.add(new Attestation(rs.getInt(1), rs.getString(2), rs.getString(5)));

                 
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

      
       public int deleteAttestation(int ida) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM attestation WHERE ida="+ida;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceAttestation.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
        public Attestation getByUsername(String typea){
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
    }
    
}

