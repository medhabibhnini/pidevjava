/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;
import com.school.Entite.Commande;
import com.school.Entite.Livraison;
import com.school.Entite.Livre;
import com.school.Service.ServiceCommande;
import com.school.Entite.User;
import com.school.Service.ServiceUser;
import com.school.Entite.Blog;
import com.school.Service.ServiceBlog;
import com.school.Service.ServiceLivraison;
import com.school.Service.ServiceLivre;
import com.school.Entite.Attestation;
import com.school.Entite.Reclamation;
import com.school.Entite.Service;
import com.school.Service.ServiceAttestation;
import com.school.Service.ServiceReclamation;
import com.school.Service.ServiceService;
import com.school.Utils.DataBase;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Mohamedhabib - pc
 */
public class BackController implements Initializable {
    private ObservableList<Livre> data;
    private ObservableList<Commande> datac;
    private ObservableList<Livraison> dataL;
    private ObservableList<User> datau;
    private ObservableList<Blog> datab;
    private ObservableList<Reclamation> dataR;
    private ObservableList<Attestation> dataA;
    private ObservableList<Service> dataS;
    private String path ;
    private FXMLLoader loader;
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
    @FXML
    private ImageView imgview;
    @FXML
    private TableView<Livre> tab_livre;
    @FXML
    private TableColumn<Livre, ?> titre;
    @FXML
    private TableColumn<Livre, ?> prix;
    @FXML
    private TableColumn<Livre, ?> quantite;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_auteur;
    @FXML
    private TextField tf_prix;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField tf_contenu;
    @FXML
    private TextField tf_quantite;
    @FXML
    private Button btn_import;
    @FXML
    private TableView<Commande> com_view;
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
    private TableColumn<Livraison, ?> comlivraison_colum;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email; 
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_role;
    @FXML
    private TableColumn<User, ?> username_colum;
    @FXML
    private TableColumn<User, ?> email_colum;
    @FXML
    private TableColumn<User, ?> role_colum;
    @FXML
    private TextField tf_sujet;
    @FXML
    private TextField tf_description;
    @FXML
    private TableView<Blog> t_view;
    @FXML
    private TableColumn<Blog, ?> sujet_colum;
    @FXML
    private TableColumn<Blog, ?> description_colum;
    @FXML
    private Button browse;
    @FXML
    private TableView<User> tab_viewU;
    @FXML
    private TextField searchb;
    @FXML
    private ImageView imgviewb;
    @FXML
    private TableView<Reclamation> tab_view1;
    @FXML
    private TableColumn<Reclamation, ?> nom_col1;
    @FXML
    private TableColumn<Reclamation, ?> sujet_col1;
    @FXML
    private TableColumn<Reclamation, ?> date_col1;
    @FXML
    private TextField search;
    @FXML
    private TableView<Attestation> tab_view11;
    @FXML
    private TableColumn<Attestation, ?> nom_col111;
    @FXML
    private TableColumn<Attestation, ?> nom_col11;
    @FXML
    private TableColumn<Attestation, ?> sujet_col11;
    @FXML
    private TextArea des_area;
    @FXML
    private DatePicker datepicker1;
    @FXML
    private TextField search1;
    @FXML
    private ComboBox<String> w;
    @FXML
    private TableView<Service> tab_view21;
    @FXML
    private TableColumn<Service, ?> ida_col1;
    @FXML
    private TableColumn<Service, ?> des_col1;
    @FXML
    private TableColumn<Service, ?> date_col11;
    @FXML
    private TextField search11;

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
        setCellValueFromTableToTextFieldlivre();
        afficherLivre();
        loadDataLivre();
        loadDataLivraison();
        afficherLivraison();
         datau= FXCollections.observableArrayList();
        datab= FXCollections.observableArrayList();
        afficheruser();
        loadDataUser();
        setCellValueFromTableToTextFieldUser();
        setCellValueFromTableToTextFieldBlog();
        afficherblog();
        loadDataBlog();
        searchBlog();
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
        File dir = new File("C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images");
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
       ResetL();

    }
    private void ResetL()
{
    
    tf_auteur.setText(null);
    tf_contenu.setText(null);
    tf_prix.setText(null);
    tf_quantite.setText(null);
    tf_titre.setText(null);
       
    
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
  
    private void setCellValueFromTableToTextFieldlivre(){
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
                
           
           
           
            Image imageURL= new Image("file:///C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images/" + photo);

                    
       
        imgview.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
     @FXML
     public void updatelivre(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
 
       
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
              ResetL();
          
        
        
    }
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
 /*------------------------------------USER&BLOG---------------------------*/
  /* -----------------------------User------------------------------------------*/
    
    private void Adduser(ActionEvent event) throws SQLException {
            
        int i=0;
        String username =tf_name.getText();    
        String mail =tf_email.getText();
        String password = tf_password.getText();
        String role = tf_role.getText();
        ServiceUser sl = new ServiceUser();
        User u = new User(username, mail, password, role);
        System.out.println(u);
        i=sl.ajouterUser(u);
if (i == 1)
    {
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User ajouter");
                alert.showAndWait();
                afficheruser();
                loadDataUser();
                

    }
 
 }
  public void afficheruser(){

             username_colum.setCellValueFactory(new PropertyValueFactory <>("username"));
             email_colum.setCellValueFactory(new PropertyValueFactory <>("email"));
             role_colum.setCellValueFactory(new PropertyValueFactory <>("role"));
            
    }

private void loadDataUser() {
   datau.clear();
         try {
           pst =con.prepareStatement("Select * from fos_user");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datau.add(new  User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("roles")));
     }      
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_viewU.setItems(datau);
    } 

  @FXML
     public void updateUser(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
     
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           User x=gettempU(edittedcell);
           int c=x.getId();
          
            String Nomp = tf_name.getText();
           String mail=tf_email.getText();
           String pswd = tf_password.getText();
           String role=tf_role.getText();            
            
   
            
            ServiceUser prod=new ServiceUser();
            User u=new User(c, Nomp, mail, pswd, role);
         
       
            System.out.println(u);
            i=prod.modifier(u);
              if(i==1)
        {
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User modifier");
                alert.showAndWait();
           afficheruser();
           loadDataUser();
           
        }
          
        ResetU();
        
    }
     
@FXML
public void deleteUser(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            User x=gettempU(edittedcell);  
            System.out.println(x);
            int i=x.getId();
             System.out.println(i);
            ServiceUser liv=new ServiceUser();
           
           
            
            int s=liv.deleteUser(i);
                     
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("User supprimer");
                alert.showAndWait();
           afficheruser();
           loadDataUser();
           System.out.println(s);
        }
            
              ResetU();
           
        
    }

      public User gettempU(TableColumn.CellEditEvent edittedCell) 
      {
        User test = tab_viewU.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
        private void setCellValueFromTableToTextFieldUser()
        {
       tab_viewU.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
           
        @Override
        public void handle(MouseEvent event) {
User us=tab_viewU.getItems().get(tab_viewU.getSelectionModel().getSelectedIndex());



tf_name.setText(us.getUsername());
 tf_email.setText(us.getEmail());
 tf_role.setText(us.getRole());
         
        }
});
    
}
        
 private void ResetU()
{
    
    tf_name.setText(null);
    tf_email.setText(null);
    tf_password.setText(null);
    tf_role.setText(null);
    
}
 
    private void DecoU(javafx.scene.input.MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
     /* -----------------------------Blog------------------------------------------*/
    
         @FXML
    private void AddBlog(ActionEvent event) throws SQLException {
            
        int i=0;
        String photo = null;
        String sujet =tf_sujet.getText();    
        String description =tf_description.getText();

        ServiceBlog Bl = new ServiceBlog();
       
       
         Image image1=null;
         image1= imgviewb.getImage();
         
          photo = saveToFileImageNormal(image1);
           Blog B = new Blog(sujet, description, photo);
             System.out.println(B);
            i=Bl.ajouterBlog(B);
         
if (i == 1)
    {
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Blog ajouter");
                alert.showAndWait();
                 afficherblog();
                 loadDataBlog();
                 
                

    }
 ResetB();
 }
    @FXML
  public void afficherblog(){

             sujet_colum.setCellValueFactory(new PropertyValueFactory <>("sujet"));
             description_colum.setCellValueFactory(new PropertyValueFactory <>("description"));
             
            
    }

  
 private void loadDataBlog() {
   datab.clear();
         try {
           pst =con.prepareStatement("Select * from blog");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datab.add(new  Blog(rs.getInt("idb"), rs.getString("sujet"), rs.getString("description"), rs.getString("type")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
       }
        t_view.setItems(datab);
    } 
  
  
  
  
public void deleteBlog(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Blog x=getttemp(edittedcell);
            int i=x.getIdb();
            ServiceBlog liv=new ServiceBlog();
           
           
            
            int s=liv.deleteBlog(i);
              if(s==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Blog supprimer");
                alert.showAndWait();
           afficherblog();
           loadDataBlog();
        }
              
          
             
          
           
        
    }
        
        public Blog getttemp(TableColumn.CellEditEvent edittedCell) 
        {
        Blog test = t_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
        
          
        public void searchBlog()
        {
searchb.setOnKeyReleased(e->
{
    if(searchb.getText().equals(""))
    {
        loadDataBlog();
    }
    else
    {
        datab.clear();
          String sql = "Select * from blog where sujet LIKE '%"+searchb.getText()+"%'"
                + "UNION Select * from blog where description LIKE '%"+searchb.getText()+"%'" ;
    try 
    {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
         String sujet =rs.getString("sujet");  
         String description=rs.getString("description");
         String type=rs.getString("type");
         
     
                      
    
             datab.add(new Blog(sujet, description, type));
 
        }
        t_view.setItems(datab);
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }  
        
        public static String saveToFileImageNormalblog(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images");
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
    private void addImageblog(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgviewb.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
 private void ResetB()
{
    
    tf_sujet.setText(null);
    tf_description.setText(null);
       
    
}
 
    

    

   
    
    private void setCellValueFromTableToTextFieldBlog()
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
                photo = getImagebyBlogId(l.getIdb());
                System.out.println(photo);
           
           
           
            Image imageURL= new Image("file:///C:\\wamp64\\www\\pidev\\IngenSchoolIntegration\\src\\com\\school\\images/" + photo);

                    
       
        imgviewb.setImage(imageURL);
             } catch (SQLException ex) 
             {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
    
    
    public String getImagebyBlogId(int ide) throws SQLException
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

public Reclamation gettempr(TableColumn.CellEditEvent edittedCell) 
      {
        Reclamation test = (Reclamation) tab_view1.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
    @FXML
  
public void deleteReclamation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Reclamation x=gettempr(edittedcell);  
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

public Attestation gettempA(TableColumn.CellEditEvent edittedCell) 
      {
        Attestation test = (Attestation) tab_view11.getSelectionModel().getSelectedItem();
        
        return test;
    }


    @FXML 
    public void deleteAttestation(ActionEvent event) throws SQLException, AWTException, MalformedURLException 
{
        
        
 TableColumn.CellEditEvent edittedcell = null;
            Attestation x=gettempA(edittedcell);  
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
 private void ResetS()
{
    
    des_area.setText(null);
    datepicker1.setValue(null);
     w.setValue(null);
}

     @FXML
    private void Ajouter(ActionEvent event) {
        
         String description = des_area.getText();
            Date date = Date.valueOf(datepicker1.getValue());

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

    @FXML
    private void DecoU(ActionEvent event) {
    }

 


}