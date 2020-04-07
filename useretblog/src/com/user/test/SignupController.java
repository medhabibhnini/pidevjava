/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import com.user.Entite.User;
import com.user.Service.ServiceUser;
import com.user.Utils.DataBase;





import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Daly
 */
public class SignupController implements Initializable 
{
    private ObservableList<User> data;
    private ResultSet rs=null;
    private PreparedStatement pst;
    private Connection con;
    private String path ;
    
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_role;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        con = DataBase.getInstance().getConnection();
        data= FXCollections.observableArrayList();
        
    }   
    
    @FXML
    private void Adduser(ActionEvent event) throws SQLException {
            
        int i=0;
        String username =tf_name.getText();    
        String mail =tf_email.getText();
        String password = tf_password.getText();
        String role = tf_role.getText();
        ServiceUser sl = new ServiceUser();
        User u = new User(username, mail, password, role);
        System.out.println(u);
        i=sl.ajouterUser(u);
if (i == 1)
    {
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User ajouter");
                alert.showAndWait();
                

    }
 
 }
    
    
}
