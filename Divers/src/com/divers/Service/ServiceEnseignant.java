/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Service;

import com.divers.Entite.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.divers.Utils.DataBase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.divers.Entite.Evenement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MrStealYourMom
 */
public class ServiceEnseignant {
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    
    public ServiceEnseignant(){
        con = DataBase.getInstance().getConnection();
    }
    
    public List<Enseignant> getList() {
        List<Enseignant> list = new ArrayList<>();

        try {
            String requete = "SELECT id,username,email FROM fos_user";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Enseignant(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public Enseignant getByUsername(String username){
        Enseignant tmp = new Enseignant();
        try {
            String requete = "SELECT id,username,email FROM fos_user where username='"+username+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tmp=new Enseignant(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tmp;
    }
    
    public Enseignant getById(int id){
        Enseignant tmp = new Enseignant();
        try {
            String requete = "SELECT id,username,email FROM fos_user where id='"+id+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                tmp=new Enseignant(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tmp;
    }
    
}
