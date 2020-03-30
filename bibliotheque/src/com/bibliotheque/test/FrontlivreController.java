/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.test;

import com.bibliotheque.Entite.Commande;
import com.bibliotheque.Entite.Livre;
import com.bibliotheque.Service.ServiceCommande;
import com.bibliotheque.Service.ServiceLivre;
import com.bibliotheque.Utils.DataBase;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class FrontlivreController implements Initializable {
 private Connection con;
    private ResultSet rs=null;
     Stage primaryStage = null ;
    private PreparedStatement pst;
    private ObservableList<Livre> data;
    @FXML
    private Pane biblio_pane;
    @FXML
    private TableView<Livre> tab_livre;
    @FXML
    private TableColumn<Livre, ?> titre;
    @FXML
    private TableColumn<Livre, ?> prix;
    @FXML
    private TableColumn<Livre, ?> quantite;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField search;
    private FXMLLoader loader;
    @FXML
    private Button btnnn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             con = DataBase.getInstance().getConnection();
              data= FXCollections.observableArrayList();
              setCellValueFromTableToTextFieldprod();
              afficherLivre();
       loadDataLivre();
       searchLivre();
       
        btnnn.setOnAction(e->{
                showMycommande();
            });
    }  
    public void searchLivre(){
search.setOnKeyReleased(e->{
    if(search.getText().equals("")){
        loadDataLivre();
    }
    else{
        data.clear();
          String sql = "Select * from livre where nomlivre LIKE '%"+search.getText()+"%'"
                + "UNION Select * from livre where prixlivre LIKE '%"+search.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
          int idlivre=rs.getInt("idlivre");  
         String titre=rs.getString("nomlivre");
         Float prix=rs.getFloat("prixlivre");
         int quantite=rs.getInt("quantitelivre");
                 ;
    
             data.add(new Livre(idlivre, titre, prix, quantite));
 
        }
        tab_livre.setItems(data);
    } catch (SQLException ex) {
        Logger.getLogger(FrontlivreController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
}
    private void afficherLivre(){

          
             titre.setCellValueFactory(new PropertyValueFactory <>("nomlivre"));
             prix.setCellValueFactory(new PropertyValueFactory <>("prixlivre"));
             quantite.setCellValueFactory(new PropertyValueFactory <>("quantitelivre"));
    }
    private void loadDataLivre() {
   data.clear();
         try {
           pst =con.prepareStatement("Select * from livre");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  Livre(rs.getInt("idlivre"), rs.getString("nomlivre"), rs.getFloat("prixlivre"), rs.getInt("quantitelivre")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_livre.setItems(data);
    }
       
    
          public Livre gettemp(TableColumn.CellEditEvent edittedCell) {
        Livre test = tab_livre.getSelectionModel().getSelectedItem();
        
        return test;
    }
            public String getImagebyId(int ide) throws SQLException
    {
        String i="";
          Statement ste;
        String  id=null;
           String query="SELECT image_name as image_name FROM livre WHERE idlivre LIKE '%"+ide+"%'";
           ste=con.createStatement();
        ResultSet rst = ste.executeQuery(query); 
         while(rst.next())
        {
             i=rst.getString("image_name");
            
        }
      
        
        return i;
    }
               @FXML
     public void commander(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
  
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           Livre x=gettemp(edittedcell);
           SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
Date date = new Date(System.currentTimeMillis());
System.out.println(formatter.format(date));
        
          
           
           
            ServiceCommande cmd=new ServiceCommande();
            
         
            Commande c = new Commande(x,1,date);
            System.out.println(c);
            i=cmd.ajouterCommande(c);
              if(i==1)
        {
           
              
           afficherLivre();
           loadDataLivre();
      
            Notification.sendNotification("Ingenschool"," \n your book has been ordered ." ,TrayIcon.MessageType.INFO);
        }
          
        
        
    }
      private void setCellValueFromTableToTextFieldprod(){
    tab_livre.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Livre liv=tab_livre.getItems().get(tab_livre.getSelectionModel().getSelectedIndex());

   
         TableColumn.CellEditEvent edittedcell = null;
            Livre l=gettemp(edittedcell);  
            
      
            String photo;
            try {
                photo = getImagebyId(l.getIdlivre());
                System.out.println(photo);
           
           
           
            Image imageURL= new Image("file:///C:/wamp64/www/pidev/bibliotheque/src/com/bibliotheque/images/" + photo);

                    
       
        imgview.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(LivreController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
      
  
private void showMycommande(){
                        try {
       loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("mycommande.fxml"));
    

    loader.load();
    Scene scene= new Scene(loader.getRoot());
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();            
    } catch (Exception e) {
        e.printStackTrace();
    }
		} 
}

