/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.test;

import com.bibliotheque.Entite.Commande;
import com.bibliotheque.Entite.Livraison;
import com.bibliotheque.Service.ServiceCommande;
import com.bibliotheque.Service.ServiceLivraison;
import com.bibliotheque.Service.ServiceLivre;
import com.bibliotheque.Utils.DataBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.teknikindustries.bulksms.SMS;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.JSType.TO_NUMBER;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class MycommandeController implements Initializable {
  private ObservableList<Commande> datac;
   private Connection con;
    private ResultSet rs=null;
      private PreparedStatement pst;
    @FXML
    private TableView<Commande> com_view;
    @FXML
    private TableColumn<Commande, ?> idliv_view;
    @FXML
    private TableColumn<Commande, ?> iduser_view;
    @FXML
    private TableColumn<Commande, ?> datecom_view;
    @FXML
    private Button brn_livrer;
    @FXML
    private Button btn_delete;
    @FXML
    private Button payer;
    private FXMLLoader loader;
    @FXML
    private TextField carte;
    @FXML
    private TextField cvc;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private TextField prix;
    @FXML
    private Pane payment;
    @FXML
    private Pane commande;
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 con=DataBase.getInstance().getConnection();
    valider.setOnAction((ActionEvent event) -> {
           
            
            try {
               
             if (controleDeSaisi()) {
              
            if (carte.getText().isEmpty()) {
                carte.setText("");
            }
            if (month.getText().isEmpty()) {
                month.setText("");
            }
            if (year.getText().isEmpty()) {
                year.setText("");
            }
            if (cvc.getText().isEmpty()) {
                cvc.setText("");
            }   
            
               }
                
                 Stripe.apiKey="sk_test_gpKkx2i75i9ZEgbDtmczQo3600enJvzSfr";
        
        
        Customer a =Customer.retrieve("cus_H0RyFibMrama3r");
       Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", Long.parseLong(carte.getText()));
        cardParams.put("exp_month", Integer.parseInt(month.getText()));
        cardParams.put("exp_year", Integer.parseInt(year.getText()));
        cardParams.put("cvc",  Integer.parseInt(cvc.getText()));
        
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        tokenParams.put("card", cardParams);
        
        Token token = Token.create(tokenParams);
        
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());
        
        a.getSources().create(source);
 
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        //chargeParams.put("amount", l.getL().getPrixlivre());
       
        chargeParams.put("amount", Integer.parseInt(prix.getText()));
       // chargeParams.put("amount", Float.(l.getL().getPrixlivre()));
        chargeParams.put("currency", "usd");
        chargeParams.put("customer", a.getId());
       
        Charge.create(chargeParams);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(a));
        
            } catch (StripeException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
                     
            
        });
        
     
        
        datac= FXCollections.observableArrayList();
        affichercomd();
        loadDatacommande();
        setCellValueFromTableToTextFieldprod();
        btn_delete.setOnAction(e->{
            try {
                deletcommande();
            } catch (SQLException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        brn_livrer.setOnAction(e->{
            try {
                Livrer();
            } catch (SQLException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
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
  @FXML
       private void handlebuttonAction(ActionEvent event){
              if (event.getSource() == payer)
           {
               payment.toFront();
           } 
                 if (event.getSource() == annuler)
           {
               commande.toFront();
           } 
       }
   public void setCellValueFromTableToTextFieldprod(){
    com_view.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Commande liv=com_view.getItems().get(com_view.getSelectionModel().getSelectedIndex());
            System.out.println(liv.getIdl());
           ServiceLivre l=new ServiceLivre();  
            try {
                prix.setText(Integer.toString(l.getprixcmdbyId(liv.getIdl())));
            } catch (SQLException ex) {
                Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
      
            
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
     
            affichercomd();
        loadDatacommande();
   
  NexmoClient client = NexmoClient.builder().apiKey("c8febd5f").apiSecret("1NuY5EfzhWPG7G3s").build();
  TextMessage message = new TextMessage("ingenschool",
                   "+21650431288",
                    "your book has been livered"
            );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}




           
        }
          
        
        
    }
/* private void showMycommande(){
                        try {
       loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Payment.fxml"));

        

    loader.load();
    Scene scene= new Scene(loader.getRoot());
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();            
    } catch (Exception e) {
        e.printStackTrace();
    }
		} */
   private boolean controleDeSaisi() {  

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
 
}
