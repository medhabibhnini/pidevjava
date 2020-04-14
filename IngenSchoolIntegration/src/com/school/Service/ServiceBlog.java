/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Service;

import com.school.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.school.Entite.Blog;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daly
 */
public class ServiceBlog 
{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;

    public ServiceBlog()
    {
        con = DataBase.getInstance().getConnection();
    }
    
    public int ajouterBlog(Blog B) throws SQLException 
    {

        String requeteInsert = "INSERT INTO blog (sujet,description,type) VALUES ( '" +B.getSujet()+ "','" +B.getDescription()+ "','" +B.getType()+ "');";
        int i = 0;   
       try 
       {
           ste = con.createStatement();
           i = ste.executeUpdate(requeteInsert);
           System.out.println(requeteInsert);
           
       } 
       catch (SQLException ex) 
       {
           Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{ste.close();} 
       return i;
             
    }
    
    public int deleteBlog(int idb) throws SQLException  
    {
        int i = 0;
       try 
       {
           ste=con.createStatement();
           String sql="DELETE FROM blog WHERE idb="+idb;   
            i=ste.executeUpdate(sql);
       } 
       catch (SQLException ex) 
       {
           Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
       }
     finally{ste.close();}
      return i;  
    }
    
     public List<Blog> afficherblog() {
        List<Blog> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM blog";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    
}
