/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;

import com.jfoenix.controls.JFXListView;
import com.school.Entite.Attestation;
import com.school.Entite.Commande;
import com.school.Entite.Livre;
import com.school.Service.ServiceCommande;
import com.school.Service.ServiceLivre;
import com.school.Utils.DataBase;
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
import javafx.scene.Node;
import javafx.scene.Scene;
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
import com.school.Entite.Blog;
import com.school.Entite.Evenement;
import com.school.Entite.Offre;
import com.school.Entite.Participation;
import com.school.Entite.Reclamation;
import com.school.Entite.Service;
import com.school.Entite.Session;
import com.school.Service.ServiceAttestation;
import com.school.Service.ServiceBlog;
import com.school.Service.ServiceEvenement;
import com.school.Service.ServiceOffre;
import com.school.Service.ServiceParticipation;
import com.school.Service.ServiceReclamation;
import com.school.Service.ServiceService;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import rsscreator.ui.main.FXMLDocumentController;
import static rsscreator.ui.main.FXMLDocumentController.createFeedListItem;
import rsscreator.ui.resourses.FeedMessage;
import rsscreator.ui.resourses.filesHandler;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class FrontofficeController implements Initializable {
 private Connection con;
    private ResultSet rs=null;
     Stage primaryStage = null ;
    private PreparedStatement pst;
    private FXMLLoader loader;
       private ObservableList<Blog> databl;
           private ObservableList<Reclamation> dataR;
    private ObservableList<Attestation> dataA;
    private ObservableList<Service> dataS;

    public static int id_user = Session.getCurrentSession();
   
    private ObservableList<Livre> data;
    @FXML
    private Button biblio_btn;
    @FXML
    private Button blog_btn;
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
    @FXML
    private Button btnnn;
    @FXML
    private Pane blog_pane;
      @FXML
    private TableView<Blog> t_view;
    @FXML
    private TableColumn<Blog, ?> sujet_colum;
    @FXML
    private TableColumn<Blog, ?> description_colum;
    @FXML
    private TextField search1;
    @FXML
    private ImageView imgview1;
    @FXML
    private Button Recl_btn;
    @FXML
    private Button Attes_btn;
    @FXML
    private Button Service_pane;
    @FXML
    private Pane reclam_pane;
    @FXML
    private Pane attest_pane;
    @FXML
    private Pane Servi_pane;
    @FXML
    private Pane home_pane;
    @FXML
    private ImageView homebtn;
    @FXML
    private ImageView bib_img;
    @FXML
    private TableView<Reclamation> tab_view;
    @FXML
    private TableColumn<Reclamation, ?> nom_col;
    @FXML
    private TableColumn<Reclamation, ?> sujet_col;
    @FXML
    private TableColumn<Reclamation, ?> date_col;
    @FXML
    private TextField tf_nomr;
    @FXML
    private TextField tf_sujetr;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<Attestation> tab_view1;
    @FXML
    private TableColumn<Attestation, ?> nom_col1;
    @FXML
    private TableColumn<Attestation, ?> sujet_col1;
    @FXML
    private TextField tff_langue;
    @FXML
    private TextField tff_langue1;
    @FXML
    private TableView<Service> tab_view2;
    @FXML
    private TableColumn<Service, ?> des_col;
    @FXML
    private TableColumn<Service, ?> ida_col;
    @FXML
    private TableColumn<Service, ?> date_col1;
    @FXML
    private Pane offre_pane;
    @FXML
    private TableView<Offre> tab_offre;
    @FXML
    private TableColumn<Offre, ?> descOffre;
    @FXML
    private TableColumn<Offre, ?> duOffre;
    @FXML
    private TableColumn<Offre, ?> aOffre;
    @FXML
    private TableColumn<Offre, ?> prixOffre;
    @FXML
    private ImageView image;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelPrix;
    @FXML
    private Label errorOffre;
    @FXML
    private Pane evenement_pane;
    @FXML
    private TableView<Evenement> tab_evenement;
    @FXML
    private TableColumn<Evenement, ?> descEvent;
    @FXML
    private TableColumn<Evenement, ?> dateEvent;
    @FXML
    private TableColumn<Evenement, ?> ensEvent;
    private ListView<TextFlow> listView;
    @FXML
    private Label errorParticipation;
    private TextField search11;
    public static ListView<TextFlow> myList;
    @FXML
    private Button offre_btn;
    @FXML
    private Button event_btn;
    @FXML
    private TextField searchoffre;
    @FXML
    private Label error_nomr;
    @FXML
    private Label error_sujetr;
    @FXML
    private Label error_attes;
    @FXML
    private Label error_langue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   // TODO
             con = DataBase.getInstance().getConnection();
              data= FXCollections.observableArrayList();
              setCellValueFromTableToTextFieldprod();
              setCellValueFromTableToTextFieldblog();
              afficherLivre();
       loadDataLivre();
       searchLivre();
           con = DataBase.getInstance().getConnection();
        databl= FXCollections.observableArrayList();
        afficherblogg();
        loadDataBlogg();
        searchBlogg();
        setCellValueFromTableToTextFieldprod();
           dataR= FXCollections.observableArrayList();
          dataA= FXCollections.observableArrayList();
          dataS= FXCollections.observableArrayList();
            afficherReclamation();  
            loadDataReclamation();
            afficherAttestation();  
            loadDataAttestation();
              afficherService();  
              setCellValueFromTableToTextFieldOffre();
               loadDataService();
       
        btnnn.setOnAction(e->{
                showMycommande();
            });
        // listView.setExpanded(true);
       // listView.depthProperty().set(1);
        myList = (ListView<TextFlow>) listView;
        try {
                url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");
               
            } catch (MalformedURLException ex) 
            {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try 
        {
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
          //tmp.setEnseignant(Se.getById(tmp.getIdenseignant()));
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
       private void bibliobuttonAction(ActionEvent event){
              if (event.getSource() == biblio_btn)
           {
               biblio_pane.toFront();
           } 
          
              
         
       }
       @FXML
       private void biblioimgAction(MouseEvent event){
              if (event.getSource() == bib_img)
           {
               biblio_pane.toFront();
           } 
          
              
         
       }
       @FXML
       private void homeAction(MouseEvent event){
         
                 if (event.getSource() == homebtn)
           {
               home_pane.toFront();
           } 
              
         
       }
         @FXML
       private void blogbuttonAction(ActionEvent event){
           
                 if (event.getSource() == blog_btn)
           {
               blog_pane.toFront();
           } 
       }
         @FXML
       private void reclambuttonAction(ActionEvent event){
              if (event.getSource() == Recl_btn)
           {
               reclam_pane.toFront();
           } 
              
         
       }
         @FXML
       private void attestbuttonAction(ActionEvent event){
           
                 if (event.getSource() == Attes_btn)
           {
               attest_pane.toFront();
           } 
       }
         @FXML
       private void servicebuttonAction(ActionEvent event){
              if (event.getSource() == Service_pane)
           {
               Servi_pane.toFront();
           } 
              
         
       }
       @FXML
       private void offrebuttonAction(ActionEvent event){
              if (event.getSource() == offre_btn)
           {
               offre_pane.toFront();
           } 
          
              
         
       }
       @FXML
       private void eventbuttonAction(ActionEvent event){
              if (event.getSource() == event_btn)
           {
               evenement_pane.toFront();
           } 
          
              
         
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
         int prix=rs.getInt("prixlivre");
         int quantite=rs.getInt("quantitelivre");
                 ;
    
             data.add(new Livre(idlivre, titre, prix, quantite));
 
        }
        tab_livre.setItems(data);
    } catch (SQLException ex) {
        Logger.getLogger(FrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
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
             data.add(new  Livre(rs.getInt("idlivre"), rs.getString("nomlivre"), rs.getInt("prixlivre"), rs.getInt("quantitelivre")));
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
     public void commander(MouseEvent event) throws SQLException, AWTException, MalformedURLException {
  
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           Livre x=gettemp(edittedcell);
           SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
Date date = new Date(System.currentTimeMillis());
System.out.println(formatter.format(date));
        
          
           
           
            ServiceCommande cmd=new ServiceCommande();
            
         
            Commande c = new Commande(x,id_user,date);
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
           
           
           
            Image imageURL= new Image("file:///C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images/" + photo);

                    
       
        imgview.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(FrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
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

     

  
       
    public void afficherblogg()
  {

             sujet_colum.setCellValueFactory(new PropertyValueFactory <>("sujet"));
             description_colum.setCellValueFactory(new PropertyValueFactory <>("description"));
             
            
    }

   
 private void loadDataBlogg()
 {
   databl.clear();
         try {
           pst =con.prepareStatement("Select * from blog");

    rs=pst.executeQuery();
     while (rs.next()) {                
             databl.add(new  Blog(rs.getInt("idb"), rs.getString("sujet"), rs.getString("description"), rs.getString("type")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
       }
        t_view.setItems(databl);
    } 
    
    
       public Blog getttemp(TableColumn.CellEditEvent edittedCell) 
        {
        Blog test = t_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
    
       
    public void searchBlogg()
        {
search1.setOnKeyReleased(e->
{
    if(search1.getText().equals(""))
    {
        loadDataBlogg();
    }
    else
    {
        databl.clear();
          String sql = "Select * from blog where sujet LIKE '%"+search1.getText()+"%'"
                + "UNION Select * from blog where description LIKE '%"+search1.getText()+"%'" ;
    try 
    {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
         String sujet =rs.getString("sujet");  
         String description=rs.getString("description");
         String type=rs.getString("type");
         
     
                      
    
             databl.add(new Blog(sujet, description, type));
 
        }
        t_view.setItems(databl);
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(FrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }
    
  

    @FXML
    private void Deco(javafx.scene.input.MouseEvent event) throws IOException
    {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
        
    }
    
    
    private void setCellValueFromTableToTextFieldblog()
{
    t_view.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent event) {
        Blog B=t_view.getItems().get(t_view.getSelectionModel().getSelectedIndex());

   
         TableColumn.CellEditEvent edittedcell = null;
            Blog l=getttemp(edittedcell);  
            
      
            String photo;
            try {
                photo = getImageblogbyId(l.getIdb());
                System.out.println(photo);
           
           
           
            Image imageURL= new Image("file:///C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images/" + photo);

                    
       
        imgview1.setImage(imageURL);
             } catch (SQLException ex) 
             {
                Logger.getLogger(FrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
    
    
    public String getImageblogbyId(int ide) throws SQLException
    {
        String i="";
          Statement ste;
        String  id=null;
           String query="SELECT type as type FROM blog WHERE idb LIKE '%"+ide+"%'";
           ste=con.createStatement();
        ResultSet rst = ste.executeQuery(query); 
         while(rst.next())
        {
             i=rst.getString("type");

        }


        return i;
    }
/*-----------Scolarite-------------*/
     
    @FXML
    private void AddReclamation(ActionEvent event) throws SQLException
    {
boolean issujetnEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomr,error_nomr, "field is require");
boolean isdesciprtionEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_sujetr,error_sujetr, "field is require");

          if(isdesciprtionEmpty && issujetnEmpty)
          {
        // int id = Integer.valueOf(Ab_IdAb.getText());
        Date dater = Date.valueOf(datepicker.getValue());
         String nomr = tf_nomr.getText();
          String sujetr = tf_sujetr.getText();
         ServiceReclamation sl = new ServiceReclamation();
        Reclamation l = new Reclamation(nomr,sujetr,dater,id_user);
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
        tab_view.setItems(dataR);
    }

public Reclamation gettempR(TableColumn.CellEditEvent edittedCell) 
      {
        Reclamation test = (Reclamation) tab_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
    @FXML
public void deleteReclamation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Reclamation x=gettempR(edittedcell);  
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
boolean isdesciprtionEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tff_langue,error_attes, "field is require");
boolean islangueEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tff_langue1,error_langue, "field is require");
        // int id = Integer.valueOf(Ab_IdAb.getText());
        if(isdesciprtionEmpty && islangueEmpty)
        {
        String typea = tff_langue1.getText();
          String langue = tff_langue.getText();
         ServiceAttestation sa = new ServiceAttestation();
       Attestation a = new Attestation(typea,langue,id_user);
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

public Attestation getttempA(TableColumn.CellEditEvent edittedCell) 
      {
        Attestation test = (Attestation) tab_view1.getSelectionModel().getSelectedItem();
        
        return test;
    }


    @FXML 
    public void deleteAttestation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Attestation x=getttempA(edittedcell);  
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
     @FXML 
    public void searchOffre(){
        
        try {
                     ServiceOffre ser = new ServiceOffre();
                     List<Offre> list = ser.displayClause(" WHERE description LIKE '%"+searchoffre.getText()+"%' or dateDebut LIKE '%"+searchoffre.getText()+"%' or dateFin LIKE '%"+searchoffre.getText()+"%' or prix LIKE '%"+searchoffre.getText()+"%'");
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
   /* public void searchEvent(){
        try {
        ServiceEvenement se = new ServiceEvenement();
        ServiceEnseignant Se = new ServiceEnseignant();
        List<Evenement> lst = se.displayClause(" WHERE date LIKE '%"+search11.getText()+"%' or description LIKE '%"+search11.getText()+"%'");
        ObservableList<Evenement> cl = FXCollections.observableArrayList();
        for (Evenement aux : lst)
        {
          Evenement tmp = new Evenement(aux.getIdevenement(),aux.getDateevenement(), aux.getDescription(), aux.getIdenseignant());
          //tmp.setEnseignant(Se.getById(tmp.getIdenseignant()));
          cl.add(tmp);  
        }
        tab_evenement.setItems(cl);
                     
                    
            }
        catch (Exception ex) {
                     System.out.println(ex);
            }
    }*/
           public Evenement gettempEvenement(TableColumn.CellEditEvent edittedCell) {
        Evenement test = tab_evenement.getSelectionModel().getSelectedItem();
        
        return test;
    }
    @FXML
    public void participer(){
        if(tab_evenement.getSelectionModel().getSelectedIndex()!=-1){
        int i=0;
        try{
            TableColumn.CellEditEvent edittedcell = null;
           Evenement x=gettempEvenement(edittedcell);
         
          int  a=x.getIdevenement();
        Participation P = new Participation(id_user,a);
        ServiceParticipation sp=new ServiceParticipation();
           i=sp.Participer(P);
                  System.out.println(i);
           
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
    
    private void setCellValueFromTableToTextFieldOffre()
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
       Image img=new Image("file:///C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images/"+o.getImage());
      

                    
            
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
    
    @FXML
    private void Cours(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Cours.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    
}
