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
import com.school.Entite.Livre;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamedhabib - pc
 */
public class ServiceLivre 
{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceLivre() {
        con = DataBase.getInstance().getConnection();
    }
    
    public void ajouterlivre(Livre L) throws SQLException  {

        String requeteInsert = "INSERT INTO livre (nomlivre,auteurlivre,datelivre,prixlivre,contenu,quantitelivre,image_name) VALUES ( '" + L.getNomlivre()+ "','" + L.getAuteurlivre()+ "','" + L.getDatelivre()+ "','" + L.getPrixlivre()+ "','" + L.getContenu()+ "','" + L.getQuantitelivre()+ "','" + L.getImage_name()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       
             
    }
        public int deletelivre(int idlivre) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM livre WHERE idlivre="+idlivre;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    public int modifier(Livre L) {
                   int e = 0;

        try {
            String requete = "UPDATE livre SET nomlivre='" + L.getNomlivre()+   "',prixlivre='" + L.getPrixlivre()+ "',quantitelivre='" + L.getQuantitelivre()+ "',image_name='" + L.getImage_name()+ "' WHERE idlivre=" + L.getIdlivre();
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("Livre modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
  
    public List<Livre> afficherlivre() {
        List<Livre> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM livre";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Livre(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public int getprixcmdbyId(int id) throws SQLException
            
    { 
           int i=0;
        String name="";
           String query="SELECT prixlivre as prixlivre FROM livre WHERE idlivre='"+id+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("prixlivre");
            i=Integer.valueOf(name);
        }
      
      return i;  
    }     

}
