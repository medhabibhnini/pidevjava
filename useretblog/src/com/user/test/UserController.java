/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import com.user.Entite.User;
import com.user.Service.ServiceUser;
import com.user.Utils.DataBase;
import java.awt.AWTException;

import java.net.MalformedURLException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Daly
 */
public class UserController implements Initializable {
    private ObservableList<User> data;
      private ResultSet rs=null;
    private PreparedStatement pst;

    private Connection con;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_role;
    @FXML
    private TableColumn<User, ?> username_colum;
    @FXML
    private TableColumn<User, ?> email_colum;
    @FXML
    private TableColumn<User, ?> role_colum;
    @FXML
    private TableView<User> tab_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = DataBase.getInstance().getConnection();
        data= FXCollections.observableArrayList();
        setCellValueFromTableToTextFieldprod();
        afficheruser();
       loadDataUser();
        
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
                alert.setContentText("User added");
                alert.showAndWait();
                 afficheruser();
                loadDataUser();

    }
 
 }
  public void afficheruser(){

             username_colum.setCellValueFactory(new PropertyValueFactory <>("username"));
             email_colum.setCellValueFactory(new PropertyValueFactory <>("email"));
             role_colum.setCellValueFactory(new PropertyValueFactory <>("role"));
            
    }
private void loadDataUser() {
   data.clear();
         try {
           pst =con.prepareStatement("Select * from fos_user");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("roles")));
     }      
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view.setItems(data);
    }  
  @FXML
     public void updateprod(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        //boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
    // boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         //boolean isPriceEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prodprice, error_priceprod, "price is require");
       /**
        * tl
        */ 
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           User x=gettemp(edittedcell);
           int c=x.getId();
            //String idp = tf_idprod.getText();
            String Nomp = tf_name.getText();
           String mail=tf_email.getText();
           String pswd = tf_password.getText();
           String role=tf_role.getText();            
            
   
              //Category c = new Category(0,Namecat);
            ServiceUser prod=new ServiceUser();
            User u=new User(c, Nomp, mail, pswd, role);
         
       
            System.out.println(u);
            i=prod.modifier(u);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User updated");
                alert.showAndWait();
           afficheruser();
           loadDataUser();
           
        }
          
        
        
    }
                  @FXML
public void deleteUser(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            User x=gettemp(edittedcell);  
            System.out.println(x);
            int i=x.getId();
             System.out.println(i);
            ServiceUser liv=new ServiceUser();
           
           
            
            int s=liv.deleteUser(i);
                     
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User deleted");
                alert.showAndWait();
           afficheruser();
           loadDataUser();
            System.out.println(s);
        }
                
           
        
    }

@FXML
      public User gettemp(TableColumn.CellEditEvent edittedCell) {
        User test = tab_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
        private void setCellValueFromTableToTextFieldprod(){
       tab_view.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
User us=tab_view.getItems().get(tab_view.getSelectionModel().getSelectedIndex());

//tf_rate.setText(idprod);

tf_name.setText(us.getUsername());
 tf_email.setText(us.getEmail());
 tf_role.setText(us.getRole());
         
        }
});
    
}
}
