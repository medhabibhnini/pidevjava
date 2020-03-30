/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.test;

import com.bibliotheque.Entite.Commande;
import com.bibliotheque.Entite.Livraison;
import com.bibliotheque.Entite.Livre;
import com.bibliotheque.Service.ServiceCommande;
import com.bibliotheque.Service.ServiceLivraison;
import com.bibliotheque.Service.ServiceLivre;
import com.bibliotheque.Utils.DataBase;
import doryan.windowsnotificationapi.fr.Notification;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class CommandeController implements Initializable {
  private ObservableList<Commande> datac;
   private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private Pane biblio_pane;
    @FXML
    private Button brn_livrer;
    @FXML
    private Button btn_delete;
    @FXML
    private TableView<Commande> com_view;
    @FXML
    private TableColumn<Commande, ?> idliv_view;
    @FXML
    private TableColumn<Commande, ?> iduser_view;
    @FXML
    private TableColumn<Commande, ?> datecom_view;
    @FXML
    private ImageView imgview;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con=DataBase.getInstance().getConnection();
        datac= FXCollections.observableArrayList();
        affichercomd();
        loadDatacommande();
        setCellValueFromTableToTextFieldprod();
        btn_delete.setOnAction(e->{
            try {
                deletcommande();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        brn_livrer.setOnAction(e->{
            try {
                Livrer();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });        
    } 
    private void affichercomd(){

           
             idliv_view.setCellValueFactory(new PropertyValueFactory <>("idl"));
             iduser_view.setCellValueFactory(new PropertyValueFactory <>("user"));
             datecom_view.setCellValueFactory(new PropertyValueFactory <>("datecommande"));
    }
private void loadDatacommande() {
   datac.clear();
         try {
           pst =con.prepareStatement("Select * from commande");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datac.add(new  Commande(rs.getInt("idcommande"), rs.getInt("id_user"), rs.getInt("idlivre"), rs.getDate("datecommande")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
        com_view.setItems(datac);
    }
public void deletcommande() throws SQLException, AWTException, MalformedURLException {
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Commande x=gettempc(edittedcell);         
            int i=x.getIdcommande();
            ServiceCommande cmd=new ServiceCommande();
           
           
            
            int s=cmd.deleteCommande(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("order deleted");
                alert.showAndWait();
           affichercomd();
           loadDatacommande();
        }
             
}
          
  public Commande gettempc(TableColumn.CellEditEvent edittedCell) {
        Commande test = com_view.getSelectionModel().getSelectedItem();
        
        return test;
    
}
   private void setCellValueFromTableToTextFieldprod(){
    com_view.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Commande liv=com_view.getItems().get(com_view.getSelectionModel().getSelectedIndex());
     
         TableColumn.CellEditEvent edittedcell = null;
            Commande l=gettempc(edittedcell);  
            
      
            
        }
});

    }
 public void Livrer() throws SQLException, AWTException, MalformedURLException {
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
           Commande x=gettempc(edittedcell);

            //String idp = tf_idprod.getText();
          
           
             
   
              //Category c = new Category(0,Namecat);
            ServiceLivraison cmd=new ServiceLivraison();
            
         
            Livraison c = new Livraison(x,1);
            System.out.println(c);
            i=cmd.ajouterLivraison(c);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book order now");
                alert.showAndWait();
      Notification.sendNotification("Ingenschool"," \n your product is going to get livred ." ,MessageType.INFO);
            affichercomd();
        loadDatacommande();
   
        
           
        }
          
        
        
    }
}
