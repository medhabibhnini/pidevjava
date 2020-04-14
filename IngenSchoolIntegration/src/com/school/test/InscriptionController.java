/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;


import com.school.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daly
 */
public class InscriptionController implements Initializable
{

    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton roleEtudiant;
    @FXML
    private RadioButton roleAdmin;
    @FXML
    private Button register;
    @FXML
    private Label login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    Connection con = DataBase.getInstance().getConnection();
    
        @FXML
    private void goToLogin(MouseEvent event) throws IOException {
                     
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

          
    }

    @FXML
    private void register(MouseEvent event) throws SQLException, IOException 
    {
        String leRole="";
         if(roleAdmin.isSelected()){
            leRole="Admin";
        }
        if(roleEtudiant.isSelected()){
            leRole="Etudiant";
        }
        
        
        String req ="INSERT INTO `fos_user` (`id`, `username`, `email`,`password`,`roles`) VALUES (NULL,'"+userName.getText()+"','"+email.getText()+"','"+password.getText()+"','"+leRole+"');";
      PreparedStatement  ps = con.prepareStatement(req);
        int executeUpdate = ps.executeUpdate();
        
       
       
       
       
                    if (leRole.equals("Etudiant")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                    if (leRole.equals("Admin")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
        
        
        
    }
    
}
