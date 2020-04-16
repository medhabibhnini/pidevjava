/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.school.Utils.DataBase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.school.Entite.Evenement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author farouk
 */
public class ServiceEvenement {
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
    public ServiceEvenement() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    
    
    public int ajouterevenement(Evenement E) throws SQLException  {

        String requeteInsert = "INSERT INTO evenement (date,description,idEnseignant) VALUES ( '" + E.getDateevenement()+ "','" + E.getDescription()+ "','" + E.getIdenseignant()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
    
      return i ;
    }
    
    public int deleteevenement(int idevenement) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM evenement WHERE id="+idevenement;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
    
    public int modifierevenement(Evenement E) {
                   int e = 0;

        try {
            String requete = "UPDATE evenement SET date='" + E.getDateevenement()+   "',description='" + E.getDescription()+ "',idEnseignant='" + E.getIdenseignant()+"' WHERE id='"+E.getIdevenement()+"'";
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("Evenement modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
    
    public List<Evenement> afficherevenement() {
        List<Evenement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<Evenement> displayClause(String cls) {
        List<Evenement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement"+cls;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    

    
    
    
}
