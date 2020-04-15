/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Scolarite.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mohamedhabib - pc
 */
public class DataBase {
    private static DataBase db;
    private Connection con;

    private final String URL = "jdbc:mysql://localhost:3306/pidev";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    DataBase() {
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DataBase getInstance()
    {if(db==null)
        db=new DataBase();
    return db;
    }     
     
    
}
