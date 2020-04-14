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
import com.bibliotheque.test.PaymentController;
import com.bibliotheque.Service.ServiceLivraison;
import com.bibliotheque.Service.ServiceLivre;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import java.sql.Connection;
import com.bibliotheque.Utils.DataBase;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import java.awt.AWTException;
import java.net.MalformedURLException;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;

import org.apache.commons.lang3.RandomStringUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static org.apache.commons.lang3.time.FastDateParserSDFTest.data;
import static org.apache.commons.lang3.time.FastDatePrinterTimeZonesTest.data;
import static org.apache.commons.lang3.time.WeekYearTest.data;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class LivreController implements Initializable {
private ObservableList<Livre> data;
private ObservableList<Commande> datac;
private ObservableList<Livraison> dataL;
    private FXMLLoader loader;
   private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_auteur;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_contenu;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField tf_quantite;
    @FXML
    private Button btn_import;
    @FXML
    private ImageView imgview;
    @FXML
    private TableView<Livre> tab_livre;
    @FXML
    private TableColumn<Livre,?> idlivre;
    @FXML
    private TableColumn<Livre,?> titre;
    @FXML
    private TableColumn<Livre,?> prix;
    @FXML
    private TableColumn<Livre,?> quantite;
    @FXML
    private TableView<Commande> com_view;
    @FXML
    private TableColumn<Commande, ?> idcom_view;
    @FXML
    private TableColumn<Commande, ?> idliv_view;
    @FXML
    private TableColumn<Commande, ?> iduser_view;
    @FXML
    private TableColumn<Commande, ?> datecom_view;
    @FXML
    private TableView<Livraison> livraison_view;
    @FXML
    private TableColumn<Livraison, ?> idlivraison_colum;
    @FXML
    private TableColumn<Livraison, ?> userlivraison_colum;
    @FXML
    private TableColumn<Commande, ?> comlivraison_colum;
    @FXML
    private Button payer;
  
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con = DataBase.getInstance().getConnection();
         data= FXCollections.observableArrayList();
        datac= FXCollections.observableArrayList();
        dataL= FXCollections.observableArrayList();
        affichercomd();
        loadDatacommande();
        setCellValueFromTableToTextFieldprod();
        afficherLivre();
        loadDataLivre();
        loadDataLivraison();
        afficherLivraison();
    
            
    }  
     @FXML
    private void addImage(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgview.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\pidev\\bibliotheque\\src\\com\\bibliotheque\\images");
        String name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
        @FXML
    private void AddLivre(ActionEvent event) throws SQLException {

 
        Date date = Date.valueOf(datepicker.getValue());
         String titre = tf_titre.getText();
          String auteur = tf_auteur.getText();
          int quantite = Integer.valueOf(tf_quantite.getText());
           int prix = Integer.valueOf(tf_prix.getText());
             String contenu = tf_contenu.getText();
Image image1=null;
             image1= imgview.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
        ServiceLivre sl = new ServiceLivre();
        Livre l = new Livre(titre, auteur, date, prix, contenu, quantite,photo);
            System.out.println(l);
         sl.ajouterlivre(l);

      
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book added");
                alert.showAndWait();
                    afficherLivre();
       loadDataLivre();

    }
     
private void afficherLivre(){

             //idlivre.setCellValueFactory(new PropertyValueFactory <>("idlivre"));
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
             data.add(new  Livre(rs.getInt("idlivre"), rs.getString("nomlivre"), rs.getInt("prixlivre"), rs.getInt("quantitelivre")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivre.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_livre.setItems(data);
    }
private void affichercomd(){

            // idcom_view.setCellValueFactory(new PropertyValueFactory <>("idcommande"));
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
  
    private void setCellValueFromTableToTextFieldprod(){
    tab_livre.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Livre liv=tab_livre.getItems().get(tab_livre.getSelectionModel().getSelectedIndex());





tf_titre.setText(liv.getNomlivre());
tf_prix.setText(Float.toString(liv.getPrixlivre()));
 tf_quantite.setText(Integer.toString(liv.getQuantitelivre()));      
         TableColumn.CellEditEvent edittedcell = null;
            Livre l=gettemp(edittedcell);  
            
      
            String photo;
            try {
                photo = getImagebyId(l.getIdlivre());
                
           
           
           
            Image imageURL= new Image("file:///C:/wamp64/www/pidev/bibliotheque/src/com/bibliotheque/images/" + photo);

                    
       
        imgview.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(LivreController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

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
           Livre x=gettemp(edittedcell);
           int c=x.getIdlivre();
            //String idp = tf_idprod.getText();
            String Nomp = tf_titre.getText();
            int Pricep=Integer.valueOf(tf_prix.getText());
            int quantite=Integer.valueOf(tf_quantite.getText());
           Image image1=null;
             image1= imgview.getImage();
              String photo = null;
             photo = saveToFileImageNormal(image1);
   
              //Category c = new Category(0,Namecat);
            ServiceLivre prod=new ServiceLivre();
            
         
            Livre p = new Livre(c, Nomp, Pricep, quantite, photo);
            System.out.println(p);
            i=prod.modifier(p);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book updated");
                alert.showAndWait();
           afficherLivre();
           loadDataLivre();
           
        }
          
        
        
    }
    /*  @FXML
     public void commander(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        //boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
    // boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         //boolean isPriceEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prodprice, error_priceprod, "price is require");
     
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           Livre x=gettemp(edittedcell);
           SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
Date date = new Date(System.currentTimeMillis());
System.out.println(formatter.format(date));
            //String idp = tf_idprod.getText();
          
           
             
   
              //Category c = new Category(0,Namecat);
            ServiceCommande cmd=new ServiceCommande();
            
         
            Commande c = new Commande(x,1,date);
            System.out.println(c);
            i=cmd.ajouterCommande(c);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book order now");
                alert.showAndWait();
           afficherLivre();
           loadDataLivre();
            affichercomd();
        loadDatacommande();
           
        }
          
        
        
    }*/
                  @FXML
public void deletelivre(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Livre x=gettemp(edittedcell);         
            int i=x.getIdlivre();
            ServiceLivre liv=new ServiceLivre();
           
           
            
            int s=liv.deletelivre(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book deleted");
                alert.showAndWait();
           afficherLivre();
           loadDataLivre();
        }
              
          
             
          
           
        
    }
@FXML
public void deletcommande(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
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
    public Livre gettemp(TableColumn.CellEditEvent edittedCell) {
        Livre test = tab_livre.getSelectionModel().getSelectedItem();
        
        return test;
    }
     public Commande gettempc(TableColumn.CellEditEvent edittedCell) {
        Commande test = com_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
      public Livraison gettempt(TableColumn.CellEditEvent edittedCell) {
        Livraison test = livraison_view.getSelectionModel().getSelectedItem();
        
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
     
private void afficherLivraison(){

             idlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("idlivraison"));
            
                comlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("user"));
                userlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("idc"));
             
    }

private void loadDataLivraison() {
   dataL.clear();
         try {
           pst =con.prepareStatement("Select * from livraison");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataL.add(new  Livraison(rs.getInt("idlivraison"), rs.getInt("id_user"), rs.getInt("idcommande")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
       }
        livraison_view.setItems(dataL);
    }    
/*@FXML
 public void Livrer(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        //boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
    // boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         //boolean isPriceEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prodprice, error_priceprod, "price is require");
     
       
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
           afficherLivre();
           loadDataLivre();
            affichercomd();
        loadDatacommande();
        loadDataLivraison();
        afficherLivraison();
        
           
        }
          
        
        
    }*/
@FXML
 public void deletlivraison(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Livraison x=gettempt(edittedcell);         
            int i=x.getIdlivraison();
            ServiceLivraison cmd=new ServiceLivraison();
           
           
            
            int s=cmd.deleteLivraison(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("order deleted");
                alert.showAndWait();
         loadDataLivraison();
        afficherLivraison();
        }
              
          
             
          
           
        
    }
 /*
 public void charge() throws StripeException
 {
     Stripe.apiKey = "pk_test_u9EXruHrMT2njpoJCfZrNib500V6stJCjf";

Map<String, Object> chargeParams = new HashMap<>();
chargeParams.put("amount",500 );
chargeParams.put("currency", "usd");
chargeParams.put("description", "My First Test Charge (created for API docs)");
chargeParams.put("source", "tok_mastercard");
// ^ obtained with Stripe.js

RequestOptions options = RequestOptions
  .builder()
  .setIdempotencyKey("3XmtVJIIGuqVXrog")
  .build();

Charge.create(chargeParams, options);
 }
*/
 private void showMycommande(){
                        try {
       loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Payment.fxml"));
                            Object load = loader.load();
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

