/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import static com.user.Entite.Session.setIdSession;
import com.user.Utils.DataBase;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daly
 */
public class LoginController implements Initializable 
{

    @FXML
    private TextField user;
    @FXML
    private PasswordField pw;
    @FXML
    private Button log;
    @FXML
    private Label check;
    @FXML
    private Label registre;

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
    private void Login(javafx.scene.input.MouseEvent event) throws SQLException, IOException {
        String role = "";
         String req = "SELECT * FROM fos_user Where email = ? and password ='"+pw.getText()+"'";
       
       PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, user.getText());
        
       ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
      
        } else { 
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            role=rs.getString("roles");
            int k = rs.getInt("id");
            setIdSession(k);
            
            
            if (role.equals("Etudiant")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Front.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                        if (role.equals("Admin")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("User.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
            
            
            
            
            
            
            

        }
        
    }

    @FXML
    private void Register(javafx.scene.input.MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
