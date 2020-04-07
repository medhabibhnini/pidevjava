/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import com.user.Utils.DataBase;
import com.user.Entite.User;
import com.user.Utils.PasswordUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daly
 */
public class LoginController implements Initializable 
{
 private ResultSet rs=null;
 private PreparedStatement pst;
 private Connection con;
 private String path ;
 public String role; 
 public static String ss;

static User lol=null;
PasswordUtils p= new PasswordUtils();   

    /// -- 
   
    
    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;

    
     public void setSs(String ss)
     {
        this.ss = ss;
    }
     
    public void handleButtonAction(ActionEvent event) throws SQLException, IOException 
    {

        
        if (event.getSource() == btnSignin) 
        {
            
            if (logIn().equals("admin")) 
            {
              
              
                          
     Parent loader =  FXMLLoader.load(getClass().getResource("User.fxml"));
      Scene s= new Scene(loader);
      Stage Window= (Stage)((Node)event.getSource()).getScene().getWindow();
     Window.setScene(s);
     Window.show(); 
            }
            
            else  if (logIn().equals("client")) 
            {
                
                
                Parent loader =  FXMLLoader.load(getClass().getResource("User.fxml"));
                
      
      Scene s= new Scene(loader);
      Stage Window= (Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(s);
        Window.show(); 
            }
            }
        }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        // TODO
        if (con == null)
        {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
          
             
        } 
        else 
        {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }
    public LoginController() throws ClassNotFoundException 
    {
        con = DataBase.getInstance().getConnection();
    }

    //we gonna use string to check for status
    private String logIn() throws SQLException 
    {
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
           

        }
        else 
        {
            //query
            String sql = "SELECT * FROM fos_user Where username = ?";
            
            setSs(email);

                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if ((rs.next()) &&  (p.checkPassword(txtPassword.getText(), rs.getString("password")))){
     
              java.sql.Timestamp timeStamp = new java.sql.Timestamp(new java.util.Date().getTime());
                System.out.println(timeStamp);
               PreparedStatement pre=con.prepareStatement("UPDATE fos_user SET last_login='"+timeStamp+"' WHERE id =?");
              
                     pre.setString(1,rs.getString("id"));
                        pre.executeUpdate();
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                } 
                else 
                {
                                           setLblError(Color.TOMATO, "Enter Correct Email/Password");    
                                            return "errur"; 

        }
                      

        }
                              return rs.getString("role");  


    }
    
    
    private void setLblError(Color color, String text) 
    {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
     public void Sign(ActionEvent event) throws SQLException  
     {

        if (event.getSource() == btnSignup) {
              
            try {
                
                Parent loader =  FXMLLoader.load(getClass().getResource("signup.fxml"));
                Scene s= new Scene(loader);
                Stage Window= (Stage)((Node)event.getSource()).getScene().getWindow();
                Window.setScene(s);
                Window.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

               
      
    }
     }
     
     
    
    
       // public int getidLogin() throws SQLException
       
    public User getUserLogin() throws SQLException
      {
         String sql = "SELECT * FROM fos_user Where username =?";
            
                pst = con.prepareStatement(sql);
                pst.setString(1, ss);
                rs = pst.executeQuery();
                while (rs.next() )
                {
       lol = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("role"));

                }
                System.out.println(lol);
          return lol;  
      }
    
    
}
