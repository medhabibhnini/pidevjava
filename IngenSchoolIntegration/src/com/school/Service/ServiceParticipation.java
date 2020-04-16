/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import com.school.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.school.Entite.Participation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrStealYourMom
 */
public class ServiceParticipation {
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceParticipation() {
        con = DataBase.getInstance().getConnection();
    }
    
    public int Participer(Participation P) throws SQLException{
        String requeteInsert = "INSERT INTO participation (idUser,idEvent) VALUES ( '" + P.getIdu()+ "','" + P.getIdEvent()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
       }
       finally{ste.close();}
    
      return i ;
    }
    
    public List<Participation> afficherparticipation() {
        List<Participation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Participation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<Participation> displayClause(String cls) {
        List<Participation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation "+cls;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Participation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     public String getnomuserbyId(int id) throws SQLException
            
    { 
         
        String name="";
           String query="SELECT email as email FROM fos_user WHERE id='"+id+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("email");
          
        }
      
      return (name);  
    }
    
}
