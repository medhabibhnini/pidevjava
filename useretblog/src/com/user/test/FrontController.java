/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import com.user.Entite.Blog;
import com.user.Service.ServiceBlog;
import com.user.Utils.DataBase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Daly
 */
public class FrontController implements Initializable 
{

    private ObservableList<Blog> databl;
    private ResultSet rs=null;
    private PreparedStatement pst;
    private Connection con;
    
    
    @FXML
    private TableView<Blog> t_view;
    @FXML
    private TableColumn<Blog, ?> sujet_colum;
    @FXML
    private TableColumn<Blog, ?> description_colum;
    @FXML
    private TextField search;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        con = DataBase.getInstance().getConnection();
        databl= FXCollections.observableArrayList();
        afficherblogg();
        loadDataBlogg();
        searchBlogg();
    }    
    
       
    public void afficherblogg()
  {

             sujet_colum.setCellValueFactory(new PropertyValueFactory <>("sujet"));
             description_colum.setCellValueFactory(new PropertyValueFactory <>("description"));
             
            
    }

   
 private void loadDataBlogg()
 {
   databl.clear();
         try {
           pst =con.prepareStatement("Select * from blog");

    rs=pst.executeQuery();
     while (rs.next()) {                
             databl.add(new  Blog(rs.getInt("idb"), rs.getString("sujet"), rs.getString("description"), rs.getString("type")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
       }
        t_view.setItems(databl);
    } 
    
    
       public Blog getttemp(TableColumn.CellEditEvent edittedCell) 
        {
        Blog test = t_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
    
       
    public void searchBlogg()
        {
search.setOnKeyReleased(e->
{
    if(search.getText().equals(""))
    {
        loadDataBlogg();
    }
    else
    {
        databl.clear();
          String sql = "Select * from blog where sujet LIKE '%"+search.getText()+"%'"
                + "UNION Select * from blog where description LIKE '%"+search.getText()+"%'" ;
    try 
    {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
         String sujet =rs.getString("sujet");  
         String description=rs.getString("description");
         String type=rs.getString("type");
         
     
                      
    
             databl.add(new Blog(sujet, description, type));
 
        }
        t_view.setItems(databl);
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }   
    
    
}
