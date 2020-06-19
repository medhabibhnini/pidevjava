/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.school.Utils.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.school.Entite.Cours;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zied
 */
public class ServiceCours 
{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceCours() {
        con = DataSource.getInstance().getCnx();
    }
    
    public void ajouterCours(Cours C) throws SQLException  {

        String requeteInsert = "INSERT INTO cours (titrecours,matiere,duree,image_name,pdfname) VALUES ( '" + C.getTitreCours()+ "','" + C.getMatiere()+ "','" + C.getDuree()+ "','" + C.getImage_name()+ "','" + C.getPdfname()+ "');";
        int i = 0;   
       try {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();}
       
             
    }
        public int deleteCours(int idcours) throws SQLException  {
        int i = 0;
       try {
           ste=con.createStatement();
           String sql="DELETE FROM cours WHERE id="+idcours;   
            i=ste.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    public int modifierCours(Cours C) {
                   int e = 0;

        try {
            String requete = "UPDATE cours SET titrecours='" + C.getTitreCours()+   "',matiere='" + C.getMatiere()+ "',duree='" + C.getDuree()+ "',image_name='" + C.getImage_name()+ "',pdfname='" + C.getPdfname()+ "' WHERE id=" + C.getIdcours();
            Statement st = con.createStatement();
            e=st.executeUpdate(requete);
            System.out.println("Cours modifiée !");
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
  
    public List<Cours> afficherCours() {
        List<Cours> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM cours";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Cours(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(7)));
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
   public String getnomcmdbyId(int id) throws SQLException
            
    { 
          
        String name="";
           String query="SELECT nomlivre as livre FROM livre WHERE idlivre='"+id+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             name=rst.getString("nomlivre");
          
        }
      
      return(name);  
    } 
   
      public String getmatiereCdbyId(int idc) throws SQLException
            
    { 
          
        String matiere="";
           String query="SELECT matiere as cours FROM cours WHERE id='"+idc+"'";
           ste=con.createStatement();
           
        ResultSet rst = ste.executeQuery(query); 
        while(rst.next())
        {
             matiere=rst.getString("matiere");
          
        }
      
      return(matiere);  
    } 
}
   
   
   

