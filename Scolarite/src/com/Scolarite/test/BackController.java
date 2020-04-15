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
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.awt.AWTException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author syrin
 */
public class BackController implements Initializable {

     private ObservableList<Reclamation> dataR;
    private ObservableList<Attestation> dataA;
    private ObservableList<Service> dataS;
   private Connection con;

    private ResultSet rs=null;
    private PreparedStatement pst;
 
    @FXML
    private TableView<Reclamation> tab_view1;
    @FXML
    private TableColumn<Reclamation, ?> nom_col1;
    @FXML
    private TableColumn<Reclamation, ?> sujet_col1;
    @FXML
    private TableColumn<Reclamation, ?> date_col1;
    @FXML
    private TableView<Attestation> tab_view11;
    @FXML
    private TableColumn<Attestation, ?> nom_col11;
    @FXML
    private TableColumn<Attestation, ?> sujet_col11;
    @FXML
    private TextArea des_area;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<Service> tab_view21;
    @FXML
    private TableColumn<Service, ?> ida_col1;
    @FXML
    private TableColumn<Service, ?> des_col1;
    @FXML
    private TableColumn<Service, ?> date_col11;
    @FXML
    private TextField search;
    @FXML
    private TextField search1;
    @FXML
    private TextField search11;
    private TextField id;
    @FXML
    private ComboBox<String> w;
    @FXML
    private TableColumn<?, ?> nom_col111;
    private TableColumn<Attestation, ?> date_col111;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con = DataBase.getInstance().getConnection();
         dataR= FXCollections.observableArrayList();
          dataA= FXCollections.observableArrayList();
          dataS= FXCollections.observableArrayList();
            afficherReclamation();  
            loadDataReclamation();
               afficherAttestation();  
            loadDataAttestation();
               afficherService();  
            loadDataService();
            searchReclamation();
            searchAttestation();
            searchService();
            
  

              w.getItems().removeAll(w.getItems());
w.getItems().addAll("0","1","2","3");
w.getSelectionModel().select("ida");
 

   
    } 
    
 

    private void afficherReclamation()
    {

             nom_col1.setCellValueFactory(new PropertyValueFactory <>("nomr"));
             sujet_col1.setCellValueFactory(new PropertyValueFactory <>("sujetr"));
             date_col1.setCellValueFactory(new PropertyValueFactory <>("dater"));
    }
private void loadDataReclamation() 
{
   dataR.clear();
         try {
           pst =con.prepareStatement("Select * from reclamation");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataR.add(new  Reclamation(rs.getInt("idr"), rs.getString("nomr"), rs.getString("sujetr"), rs.getDate("dater")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view1.setItems(dataR);
    }

public Reclamation gettemp(TableColumn.CellEditEvent edittedCell) 
      {
        Reclamation test = (Reclamation) tab_view1.getSelectionModel().getSelectedItem();
        
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

 

  
    private void afficherAttestation()
    {

             nom_col11.setCellValueFactory(new PropertyValueFactory <>("typea"));
             sujet_col11.setCellValueFactory(new PropertyValueFactory <>("langue"));
            nom_col111.setCellValueFactory(new PropertyValueFactory <>("ida"));
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
        tab_view11.setItems(dataA);
    }

public Attestation getttemp(TableColumn.CellEditEvent edittedCell) 
      {
        Attestation test = (Attestation) tab_view11.getSelectionModel().getSelectedItem();
        
        return test;
    }


    @FXML 
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
    
 
    
public void searchReclamation()
        {
search.setOnKeyReleased(e->{
    if(search.getText().equals("")){
        loadDataReclamation();
    }
    else{
        dataR.clear();
          String sql = "Select * from reclamation where nomr LIKE '%"+search.getText()+"%'"
                + "UNION Select * from reclamation where sujetr LIKE '%"+search.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
          String nomr =rs.getString("nomr");  
         String sujetr=rs.getString("sujetr");
         Date dater=rs.getDate("dater");
         
     
                      
    
             dataR.add(new Reclamation(nomr, sujetr, dater));
 
        }
        tab_view1.setItems(dataR);
    } catch (SQLException ex) {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }


public void searchAttestation()
        {
search1.setOnKeyReleased(e->{
    if(search1.getText().equals("")){
        loadDataAttestation();
    }
    else{
        dataA.clear();
          String sql = "Select * from attestation where typea LIKE '%"+search1.getText()+"%'"
                + "UNION Select * from attestation where langue LIKE '%"+search1.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
             int ida =rs.getInt("ida");
      String typea=rs.getString("typea");
         String langue=rs.getString("langue");
        
         
     
                      
    
             dataA.add(new Attestation(ida,typea,langue));
 
        }
        tab_view11.setItems(dataA);
    } catch (SQLException ex) {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }
public void searchService()
        {
search11.setOnKeyReleased(e->{
    if(search11.getText().equals("")){
        loadDataService();
    }
    else{
        dataS.clear();
          String sql = "Select * from service where description LIKE '%"+search11.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
             int ids =rs.getInt("ids");
          String description =rs.getString("description");  
          int ida =rs.getInt("ida");
         Date date=rs.getDate("date");
         
     
                      
    
             dataS.add(new Service(ids,description, date,ida));
 
        }
        tab_view21.setItems(dataS);
    } catch (SQLException ex) {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
        }
    
 
   private void afficherService(){

            
            
            

       
                  des_col1.setCellValueFactory(new PropertyValueFactory <>("description"));
              date_col11.setCellValueFactory(new PropertyValueFactory <>("date"));
                       ida_col1.setCellValueFactory(new PropertyValueFactory <>("ida"));
                       
                     
    }

private void loadDataService() {
   dataS.clear();
         try {
           pst =con.prepareStatement("Select s.ids,  s.description , s.date , s.ida, a.typea, a.langue  from service  s JOIN attestation a on s.ida=a.ida");

    rs=pst.executeQuery();
     while (rs.next()) {  
          String description = rs.getString("description");
                Date date = rs.getDate("date");
             
                int ida = rs.getInt("ida");
                   String typea = rs.getString("typea");
                      String langue = rs.getString("langue");
                Attestation a = new Attestation(ida, typea,langue);
             dataS.add(new  Service(rs.getInt("ids"), rs.getString("description"), rs.getDate("date"),rs.getInt("ida")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view21.setItems(dataS);
    }   


public Service gettttemp(TableColumn.CellEditEvent edittedCell) 
      {
        Service test = (Service) tab_view21.getSelectionModel().getSelectedItem();
        
        return test;
 
}
 @FXML
    private void addservice(ActionEvent event) throws SQLException, MessagingException
    {

   
         String description = des_area.getText();
            Date date = Date.valueOf(datepicker.getValue());
            int ida = Integer.valueOf(id.getText());
         ServiceService sl = new ServiceService();
      Service l=new Service(description,date,ida);
            System.out.println(l);
         sl.ajouterService(l);
 
      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Service added");
                alert.showAndWait();
                   afficherService();
           loadDataService();
      

    }
     
     @FXML
 private void ResetS()
{
    
    des_area.setText(null);
    datepicker.setValue(null);
     w.setValue(null);
}

     @FXML
    private void Ajouter(ActionEvent event) {
        
         String description = des_area.getText();
            Date date = Date.valueOf(datepicker.getValue());

       String id = w.getValue();
     
       
       try {

    PreparedStatement pst=con.prepareStatement("INSERT INTO service (description,date ) VALUES ('" +description+"','"+date+"')");
       pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

  
   

//       ServiceEvenement sp = new ServiceEvenement();
       
//        Evenement e = new Evenement(Name,dd,Location,number,Desc,Cat,u);
       // sp.insertPST(e);
         JOptionPane.showMessageDialog(null, "ajout avec succes");
        afficherService();
         loadDataService();
         ServiceService sl = new ServiceService();
sl.sendEmail();
    }
    @FXML
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