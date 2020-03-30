/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.test;

import com.bibliotheque.Entite.Commande;
import com.bibliotheque.Entite.Livre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField carte;
    @FXML
    private TextField prix;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Commande aa=gettempc(edittedcell);  
     //   prix.setText(String.valueOf(aa));
        
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
        chargeParams.put("amount", Integer.parseInt(prix.getText()));
        chargeParams.put("currency", "usd");
        chargeParams.put("customer", a.getId());
       
        
        Charge.create(chargeParams);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(a));
       
              
            } catch (StripeException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
                     
            
        });
        
        
        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("Mycommande.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(MycommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        
    }
    
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
