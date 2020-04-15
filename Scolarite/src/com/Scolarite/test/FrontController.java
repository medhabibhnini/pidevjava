/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Scolarite.test;


import com.Scolarite.Entite.Attestation;
import com.Scolarite.Entite.Reclamation;
import com.Scolarite.Entite.Service;
import com.Scolarite.Service.ServiceAttestation;
import com.Scolarite.Service.ServiceReclamation;
import com.Scolarite.Service.ServiceService;
import com.Scolarite.Utils.DataBase;
import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.image.Image;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author syrin
 */
public class FrontController implements Initializable {
    private ObservableList<Reclamation> data;
    private ObservableList<Attestation> dataA;
    private ObservableList<Service> dataS;
   private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private TextField tf_nomr;
    @FXML
    private TextField tf_sujetr;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<Reclamation> tab_view;
    @FXML
    private TableColumn<Reclamation, ?> nom_col;
    @FXML
    private TableColumn<Reclamation, ?> sujet_col;
    @FXML
    private TableColumn<Reclamation, ?> date_col;
 
    @FXML
    private TextField tff_langue;
    @FXML
    private TextField tff_langue1;
    @FXML
    private Tab tf_typea;
    @FXML
    private TableView<Attestation> tab_view1;
    @FXML
    private TableColumn<Attestation, ?> nom_col1;
    @FXML
    private TableColumn<Attestation, ?> sujet_col1;
   @FXML
    private TableView<Service> tab_view2;
    @FXML
    private TableColumn<Service, ?> des_col;
    @FXML
    private TableColumn<Service, ?> date_col1;
    @FXML
    private TableColumn<Service, ?> ida_col;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        con = DataBase.getInstance().getConnection();
         data= FXCollections.observableArrayList();
          dataA= FXCollections.observableArrayList();
          dataS= FXCollections.observableArrayList();
            afficherReclamation();  
            loadDataReclamation();
            afficherAttestation();  
            loadDataAttestation();
              afficherService();  
               loadDataService();
    }      
    
    @FXML
    private void AddReclamation(ActionEvent event) throws SQLException
    {

        // int id = Integer.valueOf(Ab_IdAb.getText());
        Date dater = Date.valueOf(datepicker.getValue());
         String nomr = tf_nomr.getText();
          String sujetr = tf_sujetr.getText();
         ServiceReclamation sl = new ServiceReclamation();
        Reclamation l = new Reclamation(nomr,sujetr,dater);
            System.out.println(l);
         sl.ajouterReclamation(l);

      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation added");
                alert.showAndWait();
                   afficherReclamation();
           loadDataReclamation();

    }
       

 @FXML
    private void ResetR()
{
    
    tf_nomr.setText(null);
    tf_sujetr.setText(null);
    datepicker.setValue(null);
}
    private void afficherReclamation()
    {

             nom_col.setCellValueFactory(new PropertyValueFactory <>("nomr"));
             sujet_col.setCellValueFactory(new PropertyValueFactory <>("sujetr"));
             date_col.setCellValueFactory(new PropertyValueFactory <>("dater"));
    }
private void loadDataReclamation() 
{
   data.clear();
         try {
           pst =con.prepareStatement("Select * from reclamation");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  Reclamation(rs.getInt("idr"), rs.getString("nomr"), rs.getString("sujetr"), rs.getDate("dater")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view.setItems(data);
    }

public Reclamation gettemp(TableColumn.CellEditEvent edittedCell) 
      {
        Reclamation test = (Reclamation) tab_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
    @FXML
public void deleteReclamation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Reclamation x=gettemp(edittedcell);  
            System.out.println(x);
            int i=x.getIdr();
             System.out.println(i);
            ServiceReclamation liv=new ServiceReclamation();
           
           
            
            int s=liv.deleteReclamation(i);
                     
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation deleted");
                alert.showAndWait();
           afficherReclamation();
           loadDataReclamation();
            System.out.println(s);
        }
                
           
        
    }

 
    @FXML
    private void AddAttestation(ActionEvent event) throws SQLException
    {

        // int id = Integer.valueOf(Ab_IdAb.getText());
        
        String typea = tff_langue1.getText();
          String langue = tff_langue.getText();
         ServiceAttestation sa = new ServiceAttestation();
       Attestation a = new Attestation(typea,langue);
            System.out.println(a);
         sa.ajouterAttestation(a);

      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Atteastation added");
                alert.showAndWait();
                   afficherAttestation();
                 loadDataAttestation();

    }
    
 @FXML
    private void ResetA()
{
    
    tff_langue1.setText(null);
    tff_langue.setText(null);
   
}
  
    private void afficherAttestation()
    {

             nom_col1.setCellValueFactory(new PropertyValueFactory <>("typea"));
             sujet_col1.setCellValueFactory(new PropertyValueFactory <>("langue"));
           
    }
private void loadDataAttestation() 
{
   dataA.clear();
         try {
           pst =con.prepareStatement("Select * from attestation");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataA.add(new  Attestation( rs.getInt("ida"),rs.getString("typea"), rs.getString("langue")));
     }      
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceAttestation.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view1.setItems(dataA);
    }

public Attestation getttemp(TableColumn.CellEditEvent edittedCell) 
      {
        Attestation test = (Attestation) tab_view1.getSelectionModel().getSelectedItem();
        
        return test;
    }


public void deleteAttestation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Attestation x=getttemp(edittedcell);  
            System.out.println(x);
            int i=x.getIda();
             System.out.println(i);
            ServiceAttestation liv=new ServiceAttestation();
           
           
            
            int s=liv.deleteAttestation(i);
                     
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Attestation deleted");
                alert.showAndWait();
           afficherAttestation();
           loadDataAttestation();
            System.out.println(s);
        }
                
           
        
    }


private void afficherService(){

            
            
                des_col.setCellValueFactory(new PropertyValueFactory <>("description"));
              date_col1.setCellValueFactory(new PropertyValueFactory <>("date"));

                ida_col.setCellValueFactory(new PropertyValueFactory <>("ida"));
    }

private void loadDataService() {
   dataS.clear();
         try {
           pst =con.prepareStatement("Select * from service");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataS.add(new  Service(rs.getInt("ids"), rs.getString("description"), rs.getDate("date"),rs.getInt("ida")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view2.setItems(dataS);
    }  
public Service gettttemp(TableColumn.CellEditEvent edittedCell) 
      {
        Service test = (Service) tab_view2.getSelectionModel().getSelectedItem();
        
        return test;
 
}
public void deleteService(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Service x=gettttemp(edittedcell);  
            System.out.println(x);
            int i=x.getIds();
             System.out.println(i);
            ServiceService liv=new ServiceService();
           
           
            
            int s=liv.deleteService(i);
                     
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Service deleted");
                alert.showAndWait();
           afficherService();
           loadDataService();
            System.out.println(s);
        }
                
           
        
    }

}

  
