/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.test;

import com.divers.Entite.Offre;
import com.divers.Entite.Evenement;
import com.divers.Entite.Enseignant;
import com.divers.Service.ServiceOffre;
import com.divers.Service.ServiceEvenement;
import com.divers.Service.ServiceEnseignant;
import com.divers.Utils.DataBase;
import java.awt.AWTException;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;


/**
 * FXML Controller class
 *
 * @author farouk
 */
public class DiverbackController implements Initializable 
{

    private ObservableList<Offre> data;
    private ObservableList<Evenement> data1;
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_desc;
    @FXML
    private DatePicker datepicker;
    @FXML
    private DatePicker datepicker1;
    @FXML
    private TableView<Offre> tab_Offre;
    @FXML
    private TableColumn<Offre, ?> idoffre_col;
    @FXML
    private TableColumn<Offre, ?> prix_col;
    @FXML
    private TableColumn<Offre, ?> date_debut_col;
    @FXML
    private TableColumn<Offre, ?> date_fin_col;
    @FXML
    private TableColumn<Offre, ?> description_col;
    
    
    
    
    
    
    @FXML
    private DatePicker datepicker11;
    @FXML
    private TextField tf_desc1;
    @FXML
    private ComboBox ENS;
    @FXML
    private TableView<Evenement> tab_Evenement;
    @FXML
    private TableColumn<Evenement, ?> idvenement_col;
    @FXML
    private TableColumn<Evenement, ?> date_event_col;
    @FXML
    private TableColumn<Evenement, ?> descriptionE_col;
    @FXML
    private TableColumn<Evenement, ?> enseignant_col;
    
    @FXML
    private TextField searchOffre;
    @FXML
    private TextField searchEvent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       con = DataBase.getInstance().getConnection();
       data= FXCollections.observableArrayList();
       data1= FXCollections.observableArrayList();
       setCellValueFromTableToTextFieldprod();
       setCellValueFromTableToTextFieldevent();
       afficherOffre();
       loadDataOffre();
       afficherEvenement();
       loadDataEvenement();
       
    }    
    
            
@FXML
    private void Addoffre(ActionEvent event) throws SQLException {
        
        int i=0;
         double prixoffre = Float.valueOf(tf_prix.getText());
         Date date_debut = Date.valueOf(datepicker.getValue());
         Date date_fin = Date.valueOf(datepicker1.getValue());
        String description =tf_desc.getText();
        ServiceOffre Bl = new ServiceOffre();
        Offre B = new Offre(prixoffre,date_debut,date_fin,description);
         System.out.println(B);
         i=Bl.ajouteroffre(B);
         
if (i == 1)
    {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Offre ajouter");
                alert.showAndWait();
                afficherOffre();
                loadDataOffre();
        
    }
 }
    
 @FXML
    private void AddEvenement(ActionEvent event) throws SQLException {
        
        int i=0;
        ServiceEnseignant se= new ServiceEnseignant();
        Date dateevenemet = Date.valueOf(datepicker11.getValue());
        String description =tf_desc1.getText();
        Enseignant tmp=se.getByUsername(ENS.getSelectionModel().getSelectedItem().toString());
        int id_es=tmp.getId();
        System.out.print(id_es);
        ServiceEvenement Bl = new ServiceEvenement();
        Evenement B = new Evenement(dateevenemet,description,id_es);
         System.out.println(B);
         i=Bl.ajouterevenement(B);
         
if (i == 1)
    {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Evenement ajouter");
                alert.showAndWait();
                
        
    }
    loadDataEvenement();
 }   
     
    
    
    private void afficherOffre(){

             idoffre_col.setCellValueFactory(new PropertyValueFactory <>("idoffre"));
             prix_col.setCellValueFactory(new PropertyValueFactory <>("prixoffre"));
             date_debut_col.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
             date_fin_col.setCellValueFactory(new PropertyValueFactory <>("date_fin"));
             description_col.setCellValueFactory(new PropertyValueFactory <>("description"));
    }
    
    
     private void loadDataOffre() {
   data.clear();
         try {
           pst =con.prepareStatement("Select * from offre");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  Offre(rs.getInt("id"), rs.getInt("prix"), rs.getDate("datedebut"), rs.getDate("datefin"),rs.getString("description")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_Offre.setItems(data);
        searchOffre.setText("");
    }
    
    
    private void afficherEvenement(){

             idvenement_col.setCellValueFactory(new PropertyValueFactory <>("idevenement"));
             date_event_col.setCellValueFactory(new PropertyValueFactory <>("dateevenement"));
             descriptionE_col.setCellValueFactory(new PropertyValueFactory <>("description"));
             enseignant_col.setCellValueFactory(new PropertyValueFactory <>("idenseignant"));
             
    }
    
    private void loadDataEvenement() {
   data1.clear();
         try {
           pst =con.prepareStatement("Select * from evenement");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data1.add(new  Evenement(rs.getInt("id"),rs.getDate("date"),rs.getString("description"),rs.getInt("IdEnseignant")));
     }   
     ServiceEnseignant SE=new ServiceEnseignant();
     List<Enseignant> ls= SE.getList();
     ObservableList<Enseignant> cls4 = FXCollections.observableArrayList();
     for (Enseignant tmp : ls)
     {
         cls4.add(tmp);
     }
     ENS.setItems(cls4);
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_Evenement.setItems(data1);
        searchEvent.setText("");
    }
    
   
    
    
     public Offre gettemp(TableColumn.CellEditEvent edittedCell) {
        Offre test = tab_Offre.getSelectionModel().getSelectedItem();
        
        return test;
    }
     
     
     private void setCellValueFromTableToTextFieldprod()
        {
       tab_Offre.setOnMouseClicked(new EventHandler<MouseEvent>()
             {
        @Override
        public void handle(MouseEvent event) {
       Offre o=tab_Offre.getItems().get(tab_Offre.getSelectionModel().getSelectedIndex());
       tf_prix.setText(Double.toString(o.getPrixoffre()));
       tf_desc.setText(o.getDescription());
       String dtstr=o.getDate_debut().toString();
       String dtstr1=o.getDate_fin().toString();       
            LocalDate dt=new LocalDateStringConverter().fromString(dtstr.substring(5,7)+"/"+dtstr.substring(8,10)+"/"+dtstr.substring(0,4));
            LocalDate dt1=new LocalDateStringConverter().fromString(dtstr1.substring(5,7)+"/"+dtstr1.substring(8,10)+"/"+dtstr1.substring(0,4));
            datepicker.setValue(dt);
            datepicker1.setValue(dt1);
                    }
               });
       }
     
     private void setCellValueFromTableToTextFieldevent()
        {
       tab_Evenement.setOnMouseClicked(new EventHandler<MouseEvent>()
             {
        @Override
        public void handle(MouseEvent event) {
       Evenement ev=tab_Evenement.getItems().get(tab_Evenement.getSelectionModel().getSelectedIndex());
       tf_desc1.setText(ev.getDescription());
       String dtstr=ev.getDateevenement().toString();
            LocalDate dt=new LocalDateStringConverter().fromString(dtstr.substring(5,7)+"/"+dtstr.substring(8,10)+"/"+dtstr.substring(0,4));
            datepicker11.setValue(dt);
                    }
               });
       }
     
     

    
    @FXML
   public void deleteOffre(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
         TableColumn.CellEditEvent edittedcell = null;
         Offre x=gettemp(edittedcell);         
         int i=x.getIdoffre();
         ServiceOffre liv=new ServiceOffre();
           
           
            
            int s=liv.deleteoffre(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("offre supprimé");
                alert.showAndWait();
           afficherOffre();
           loadDataOffre();
        }
               
    }

    @FXML
     public void updateOffre(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
       
       
        int i;
           
            
           TableColumn.CellEditEvent edittedcell = null;
           Offre x=gettemp(edittedcell);
           int c=x.getIdoffre();
           Double prixoffre=Double.valueOf(tf_prix.getText());
           Date date_debut=Date.valueOf(datepicker.getValue());
           Date date_fin=Date.valueOf(datepicker1.getValue());
           String description=String.valueOf(tf_desc.getText());            
            
   
              //Category c = new Category(0,Namecat);
            ServiceOffre prod=new ServiceOffre();
            Offre u=new Offre(c,prixoffre,date_debut,date_fin ,description);
         
       
            System.out.println(u);
            i=prod.modifieroffre(u);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User modifier");
                alert.showAndWait();
           afficherOffre();
           loadDataOffre();
           
        }
          
        
        

    }
     
     @FXML
     public void deleteEvent(){
         ServiceEvenement se=new ServiceEvenement();
         Evenement tmp=tab_Evenement.getSelectionModel().getSelectedItem();
         try{
         int i =se.deleteevenement(tmp.getIdevenement());
        if (i == 1)
    {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Evenement Supprimé");
                alert.showAndWait();
                
        
    }
    loadDataEvenement();
         }
         catch(SQLException ex)
         {
             
         }
         
         
         
     }
     
     @FXML
     public void updateEvent(){
         
         int i=0;
        ServiceEnseignant se= new ServiceEnseignant();
        Date dateevenemet = Date.valueOf(datepicker11.getValue());
        String description =tf_desc1.getText();
        Enseignant tmp=se.getByUsername(ENS.getSelectionModel().getSelectedItem().toString());
        int id_es=tmp.getId();
        System.out.print(id_es);
        ServiceEvenement Bl = new ServiceEvenement();
         Evenement tmp2=tab_Evenement.getSelectionModel().getSelectedItem();
        Evenement B = new Evenement(tmp2.getIdevenement(),dateevenemet,description,id_es);
         System.out.println(B);
       
         i=Bl.modifierevenement(B);
         
if (i == 1)
    {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Evenement modifié");
                alert.showAndWait();
                
        
    }
    loadDataEvenement();
     }
    @FXML
    public void search_offre(){            
                 ServiceOffre ser = new ServiceOffre();
                     List<Offre> list = ser.displayClause(" WHERE id LIKE '%"+searchOffre.getText()+"%' or prix LIKE '%"+searchOffre.getText()+"%' or dateDebut LIKE '%"+searchOffre.getText()+"%' or dateFin LIKE '%"+searchOffre.getText()+"%' or description LIKE '%"+searchOffre.getText()+"%'");
                     ObservableList<Offre> cls = FXCollections.observableArrayList();
                     for (Offre aux : list)
                     {
                      cls.add(new Offre(aux.getIdoffre(),aux.getPrixoffre(), aux.getDate_debut(), aux.getDate_fin(), aux.getDescription()));  
                     }
                     tab_Offre.setItems(cls);
                    
                 }
        
    @FXML void search_event(){
        ServiceEvenement ser = new ServiceEvenement();
                     List<Evenement> list = ser.displayClause(" WHERE id LIKE '%"+searchEvent.getText()+"%' or date LIKE '%"+searchEvent.getText()+"%' or description LIKE '%"+searchEvent.getText()+"%' or idEnseignant LIKE '%"+searchEvent.getText()+"%'");
                     ObservableList<Evenement> cls = FXCollections.observableArrayList();
                     for (Evenement aux : list)
                     {
                          cls.add(new Evenement(aux.getIdevenement(),aux.getDateevenement(), aux.getDescription(), aux.getIdenseignant()));  
                     }
                     tab_Evenement.setItems(cls);
    }
     
 }


     






































    
    
    



