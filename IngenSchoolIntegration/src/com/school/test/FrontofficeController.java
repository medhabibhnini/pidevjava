/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;

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
import com.school.Service.ServiceBlog;

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
       
        btnnn.setOnAction(e->{
                showMycommande();
            });
        
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

    
}
