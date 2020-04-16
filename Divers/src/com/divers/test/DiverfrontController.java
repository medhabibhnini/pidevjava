/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.test;


import com.divers.Entite.Offre;
import com.divers.Entite.Evenement;
import com.divers.Entite.Enseignant;
import com.divers.Entite.Participation;
import com.divers.Service.ServiceOffre;
import com.divers.Service.ServiceEvenement;
import com.divers.Service.ServiceEnseignant;
import com.divers.Service.ServiceParticipation;
import com.jfoenix.controls.JFXListView;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;
import rsscreator.ui.main.FXMLDocumentController;
import static rsscreator.ui.main.FXMLDocumentController.createFeedListItem;
import rsscreator.ui.resourses.FeedMessage;
import rsscreator.ui.resourses.filesHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.converter.LocalDateStringConverter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MrStealYourMom
 */
public class DiverfrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Offre> tab_offre;
    @FXML
    private TableColumn<String, Offre> descOffre;
    @FXML
    private TableColumn<String, Offre> duOffre;
    @FXML
    private TableColumn<String, Offre> aOffre;
    @FXML
    private TableColumn<String, Offre> prixOffre;
    @FXML
    private TextField search1;
    
    @FXML
    private TableView<Evenement> tab_evenement;
    @FXML
    private TableColumn<String, Evenement> descEvent;
    @FXML
    private TableColumn<String, Evenement> dateEvent;
    @FXML
    private TableColumn<String, Evenement> ensEvent;
    @FXML
    private TextField search2;
    @FXML
    private Label errorParticipation;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelPrix;
    @FXML
    private Label errorOffre;
    @FXML
    private ImageView image;
    
    @FXML
    private JFXListView<TextFlow> listView;
    public static JFXListView<TextFlow> myList;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        listView.setExpanded(true);
        listView.depthProperty().set(1);
        myList = listView;
        try {
                URL url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");
                filesHandler.importFrom(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
        ServiceOffre ser = new ServiceOffre();
        List<Offre> list = ser.afficheroffre();
        ObservableList<Offre> cls = FXCollections.observableArrayList();
        for (Offre aux : list)
        {
          cls.add(aux);  
        }
        
        descOffre.setCellValueFactory(new PropertyValueFactory<>("Description"));
        duOffre.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
        aOffre.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        prixOffre.setCellValueFactory(new PropertyValueFactory<>("Prixoffre"));
        tab_offre.setItems(cls);
        
        ServiceEvenement se = new ServiceEvenement();
        ServiceEnseignant Se = new ServiceEnseignant();
        List<Evenement> lst = se.afficherevenement();
        ObservableList<Evenement> cl = FXCollections.observableArrayList();
        for (Evenement aux : lst)
        {
          Evenement tmp = new Evenement(aux.getIdevenement(),aux.getDateevenement(), aux.getDescription(), aux.getIdenseignant());
          tmp.setEnseignant(Se.getById(tmp.getIdenseignant()));
          cl.add(tmp);  
        }
        
        descEvent.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateEvent.setCellValueFactory(new PropertyValueFactory<>("Dateevenement"));
        ensEvent.setCellValueFactory(new PropertyValueFactory<>("Enseignant"));
        tab_evenement.setItems(cl);
        }
        catch(Exception ex){
            
        }
        setCellValueFromTableToTextFieldprod();
        
    }    
    
    @FXML 
    public void searchOffre(){
        
        try {
                     ServiceOffre ser = new ServiceOffre();
                     List<Offre> list = ser.displayClause(" WHERE description LIKE '%"+search1.getText()+"%' or dateDebut LIKE '%"+search1.getText()+"%' or dateFin LIKE '%"+search1.getText()+"%' or prix LIKE '%"+search1.getText()+"%'");
                     ObservableList<Offre> cls = FXCollections.observableArrayList();
                     for (Offre aux : list)
                        {
                            
                            cls.add(aux);  
                        }
                     tab_offre.setItems(cls);
                     
                    
            }
        catch (Exception ex) {
                     System.out.println(ex);
            }
        
    }
    @FXML
    public void searchEvent(){
        try {
        ServiceEvenement se = new ServiceEvenement();
        ServiceEnseignant Se = new ServiceEnseignant();
        List<Evenement> lst = se.displayClause(" WHERE date LIKE '%"+search2.getText()+"%' or description LIKE '%"+search2.getText()+"%'");
        ObservableList<Evenement> cl = FXCollections.observableArrayList();
        for (Evenement aux : lst)
        {
          Evenement tmp = new Evenement(aux.getIdevenement(),aux.getDateevenement(), aux.getDescription(), aux.getIdenseignant());
          tmp.setEnseignant(Se.getById(tmp.getIdenseignant()));
          cl.add(tmp);  
        }
        tab_evenement.setItems(cl);
                     
                    
            }
        catch (Exception ex) {
                     System.out.println(ex);
            }
    }
    @FXML
    public void participer(){
        if(tab_evenement.getSelectionModel().getSelectedIndex()!=-1){
        int i=0;
        try{
        Participation P = new Participation(0,1,tab_evenement.getSelectionModel().getSelectedItem().getIdevenement());
        ServiceParticipation sp=new ServiceParticipation();
           i=sp.Participer(P);
        }
        catch(SQLException ex){
            
        }
         
if (i == 1)
    {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Participation Enregistrée");
                alert.showAndWait();
                sendnotificationforevent();
                errorParticipation.setText("");
        
    }
        }
        else{
            errorParticipation.setText("Choix Invalide");
        }
        
    }
    
    public static void createList(){
        if (filesHandler.feed==null)return;
        myList.getItems().clear();
        for (FeedMessage msg:filesHandler.feed.getEntries()){
            myList.getItems().add(createFeedListItem(msg));
        }
    }
    
    private void setCellValueFromTableToTextFieldprod()
        {
       tab_offre.setOnMouseClicked(new EventHandler<MouseEvent>()
             {
        @Override
        public void handle(MouseEvent event) {
       Offre o=tab_offre.getSelectionModel().getSelectedItem();
       labelPrix.setText(Double.toString(o.getPrixoffre())+" DT");
       labelDesc.setText(o.getDescription());
       labelDate.setText("Du: "+o.getDate_debut().toString()+" A: "+o.getDate_fin().toString());
       
       //IMAGE
       System.out.println(o.getImage());
       Image img=new Image("uploads/"+o.getImage());
            
       image.setImage(img);
                    }
               });
       }
    
    @FXML
    public void acheter(){
        if(tab_offre.getSelectionModel().getSelectedIndex()!=-1){
        Notifications notificationBuilder=  Notifications.create()
                        .title("Achat")
                        .text("Vous avez acheté l'Offre "+tab_offre.getSelectionModel().getSelectedItem().getIdoffre()+":\n"+tab_offre.getSelectionModel().getSelectedItem().getDescription()+".")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                sendnotificationforoffre();
                errorOffre.setText("");
        }
        else{
            errorOffre.setText("Choix Invalide.");
        }
    }
   
    @FXML
    public void sendnotificationforoffre() {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic Y2MwOTFlZjctNjgwOC00ZTNiLWE2YWItM2E1NDRkMjBmZTQ3");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    + "\"app_id\": \"253ac335-60e8-481d-8b7d-dd74c1905e42\","
                    + "\"included_segments\": [\"All\"],"
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"contents\": {\"en\": \"vous avez achetez une offre ! Profiter\"}"
                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        
        
        
    
    
}
    
    
    
    
     @FXML
    public void sendnotificationforevent() {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic Y2MwOTFlZjctNjgwOC00ZTNiLWE2YWItM2E1NDRkMjBmZTQ3");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    + "\"app_id\": \"253ac335-60e8-481d-8b7d-dd74c1905e42\","
                    + "\"included_segments\": [\"All\"],"
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"contents\": {\"en\": \"vous avez participer à l'event ! Profiter\"}"
                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        
        
        
    
    
}
    
    
    
}
