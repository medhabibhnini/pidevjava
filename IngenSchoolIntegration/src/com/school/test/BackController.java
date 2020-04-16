/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
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
import com.school.Entite.Evenement;
import com.school.Entite.Offre;
import com.school.Entite.Reclamation;
import com.school.Entite.Service;
import com.school.Service.ServiceAttestation;
import com.school.Service.ServiceEvenement;
import com.school.Service.ServiceOffre;
import com.school.Service.ServiceParticipation;
import com.school.Service.ServiceReclamation;
import com.school.Service.ServiceService;
import com.school.Utils.DataBase;


import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.util.Duration;
import javafx.util.converter.LocalDateStringConverter;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.Notifications;
import com.school.Entite.Participation;
import static com.school.test.FrontofficeController.id_user;
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
    private ObservableList<Offre> dataO;
    private ObservableList<Evenement> dataE;
    private ObservableList<Participation> dataP;
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
    private ComboBox<Attestation> w;
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
    @FXML
    private Label error_auteur;
    @FXML
    private Label error_contenu;
    @FXML
    private Label error_titre;
    @FXML
    private Label error_quantite;
    @FXML
    private Label error_prix;
    @FXML
    private TextField tf_prix1;
    @FXML
    private DatePicker datepicker11;
    @FXML
    private TextField tf_desc;
    @FXML
    private Label fileName;
    @FXML
    private TableView<Offre> tab_Offre;
    @FXML
    private TableColumn<Offre, ?> prix_col;
    @FXML
    private TableColumn<Offre, ?> date_debut_col;
    @FXML
    private TableColumn<Offre, ?> date_fin_col;
    @FXML
    private TableColumn<Offre, ?> description_col;
    @FXML
    private TextField searchOffre;
    @FXML
    private Label errorOffre;
    @FXML
    private DatePicker datepicker111;
    @FXML
    private TextField tf_desc1;
    @FXML
    private ComboBox <User>ENS;
    @FXML
    private TableView<Evenement> tab_Evenement;
    @FXML
    private TableColumn<Evenement, ?> idvenement_col;
    @FXML
    private TableColumn<Evenement, ?> date_event_col;
    @FXML
    private TableColumn<Evenement, ?> enseignant_col;
    @FXML
    private TableColumn<Evenement, ?> descriptionE_col;
    @FXML
    private TextField searchEvent;
    @FXML
    private TableView<Participation> tab_participation;
    @FXML
    private TableColumn<Participation, ?> event;
    @FXML
    private TableColumn<Participation, ?> participant;
    @FXML
    private TextField searchParticipation;
    @FXML
    private Label errorEvent;
    @FXML
    private DatePicker datepickerattes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataBase.getInstance().getConnection();
         data= FXCollections.observableArrayList();
        datac= FXCollections.observableArrayList();
        dataL= FXCollections.observableArrayList();
        dataP= FXCollections.observableArrayList();
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
            dataO= FXCollections.observableArrayList();
       dataE= FXCollections.observableArrayList();
       setCellValueFromTableToTextFieldOffre();
       setCellValueFromTableToTextFieldevent();
       afficherOffre();
       loadDataOffre();
       afficherEvenement();
       loadDataEvenement();
       afficherParticipation();
       loadDataParticipation();
       initcatcombo();
            
  

           //   w.getItems().removeAll(w.getItems());
//w.getItems().addAll("0","1","2","3");
//w.getSelectionModel().select("ida");

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
    boolean isauteurEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_auteur,error_auteur, "auteur is require");
    boolean iscontenuEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_contenu, error_contenu, "content is require");
    boolean istitreEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_titre, error_titre, "title is require");
    boolean isprixNumber=validation.TextFieldvalidation.istextFieldTypeNumber(tf_prix, error_prix, "quantity is number");
    boolean isquantiteNumber=validation.TextFieldvalidation.istextFieldTypeNumber(tf_quantite, error_quantite, "price is number");
    boolean isprixEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prix, error_prix, "price is require");
    boolean isquantiteEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_quantite, error_quantite, "price is require");
    if(isauteurEmpty && iscontenuEmpty && isprixEmpty && isquantiteEmpty && istitreEmpty && isprixNumber && isquantiteNumber){
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

         {
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("book added");
                alert.showAndWait();
                    afficherLivre();
       loadDataLivre();
       ResetL();
         }
                        }

    

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
             idliv_view.setCellValueFactory(new PropertyValueFactory <>("idln"));
             iduser_view.setCellValueFactory(new PropertyValueFactory <>("username"));
             datecom_view.setCellValueFactory(new PropertyValueFactory <>("datecommande"));
    }
private void loadDatacommande() {
   datac.clear();
         try {
           pst =con.prepareStatement("Select * from commande");

    rs=pst.executeQuery();
     while (rs.next()) {                
            int l=rs.getInt("idlivre");
              int idcommande=rs.getInt("idcommande");
            int u=rs.getInt("id_user");
              Date datecommande=rs.getDate("datecommande");
              ServiceLivre liv = new ServiceLivre();
              ServiceCommande com = new ServiceCommande();
               String email = com.getnomuserbyId(u);
              
                         
                datac.add(new Commande(idcommande, email , liv.getnomcmdbyId(l), datecommande ));
     
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
   // boolean isauteurEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_auteur,error_auteur, "auteur is require");
    //boolean iscontenuEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_contenu, error_contenu, "content is require");
    boolean istitreEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_titre, error_titre, "title is require");
    boolean isprixNumber=validation.TextFieldvalidation.istextFieldTypeNumber(tf_prix, error_prix, "quantity is number");
    boolean isquantiteNumber=validation.TextFieldvalidation.istextFieldTypeNumber(tf_quantite, error_quantite, "price is number");
    boolean isprixEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_prix, error_prix, "price is require");
    boolean isquantiteEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_quantite, error_quantite, "price is require");
    if( isprixEmpty && isquantiteEmpty && istitreEmpty && isprixNumber && isquantiteNumber){
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           Livre x=gettemp(edittedcell);
           int c=x.getIdlivre();
         
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
            
                comlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("livre"));
                //userlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("idc"));
                 userlivraison_colum.setCellValueFactory(new PropertyValueFactory <>("username"));
             
    }

private void loadDataLivraison() {
   dataL.clear();
         try {
           pst =con.prepareStatement("Select * from livraison");

    rs=pst.executeQuery();
     while (rs.next()) { 
              ServiceCommande com = new ServiceCommande();
              ServiceLivraison li= new ServiceLivraison();
              ServiceLivre liv=new ServiceLivre();
              int idcom=rs.getInt("idcommande");
             
             int idlivraison= rs.getInt("idlivraison");
       
              int u =rs.getInt("id_user");
              int idlivre=com.getlivbyIdcom(idcom);
              String livre=liv.getnomcmdbyId(idlivre);
               String email = com.getnomuserbyId(u);
               
           // System.out.println(livre);
             dataL.add(new  Livraison(idlivraison,email ,livre ));
             //dataL.add(new  Livraison(rs.getInt("idlivraison"),email ,rs.getInt("idcommande") ));
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
 @FXML
    private void DecoU(ActionEvent event) throws IOException {
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
private void initcatcombo() {
    ObservableList datacat=FXCollections.observableArrayList();
   w.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from attestation");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datacat.add(rs.getString(2));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceAttestation.class.getName()).log(Level.SEVERE, null, ex);
       }
w.setItems(dataA);

}
     @FXML
    private void Ajouter(ActionEvent event) {
        
         String description = des_area.getText();
            Date date = Date.valueOf(datepickerattes.getValue());
       
       Attestation id = w.getValue();
     int ida=id.getIda();
       int idu=id_user;
       try {

    PreparedStatement pst=con.prepareStatement("INSERT INTO service (ida,description,date,idUser ) VALUES ('" +ida+"','" +description+"','"+date+"','"+idu+"')");
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
    private void Addoffre(ActionEvent event) throws SQLException {
        if(Validchamp(tf_desc) && fileName.getText()!="Choisir Image" && Validchamp(tf_prix)){
        int i=0;
         double prixoffre = Float.valueOf(tf_prix1.getText());
         Date date_debut = Date.valueOf(datepicker1.getValue());
         Date date_fin = Date.valueOf(datepicker11.getValue());
        String description =tf_desc.getText();
        ServiceOffre Bl = new ServiceOffre();
        Offre B = new Offre(prixoffre,date_debut,date_fin,description);
       B.setImage(fileName.getText());
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
                errorOffre.setText("");

                // PUSH NOTIFICATION
                Notifications notificationBuilder=  Notifications.create()
                        .title("Nouvelle Offre")
                        .text("Consulter La Nouvelle Offre !!")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
        
    }
        }
        else{
            errorOffre.setText("Input Invalide");
        }
 }
    
    @FXML
    private void AddEvenement(ActionEvent event) throws SQLException {
        if(Validchamp(tf_desc1)){
        int i=0;
        ServiceUser se= new ServiceUser();
        Date dateevenemet = Date.valueOf(datepicker111.getValue());
        String description =tf_desc1.getText();
       // User tmp=se.getByUsername(ENS.getSelectionModel().getSelectedItem().toString());
       
        //System.out.print(id_es);
            User idenseignant =  ENS.getValue();
     
             int ide=idenseignant.getId();
        ServiceEvenement Bl = new ServiceEvenement();
        Evenement B = new Evenement(dateevenemet,description,ide);
         System.out.println(B);
         i=Bl.ajouterevenement(B);
         errorEvent.setText("");
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
        else
        {
            errorEvent.setText("Input Invalide");
        }
 }   
     
    
    
    private void afficherOffre(){

             
             prix_col.setCellValueFactory(new PropertyValueFactory <>("prixoffre"));
             date_debut_col.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
             date_fin_col.setCellValueFactory(new PropertyValueFactory <>("date_fin"));
             description_col.setCellValueFactory(new PropertyValueFactory <>("description"));
    }
    
    private void afficherParticipation(){

             event.setCellValueFactory(new PropertyValueFactory <>("IdEvent"));
             participant.setCellValueFactory(new PropertyValueFactory <>("email"));
    }
    private void loadDataParticipation(){
        
        dataP.clear();
         try {
           pst =con.prepareStatement("SELECT * FROM participation");

    rs=pst.executeQuery();
     while (rs.next()) { 
              
              int id=rs.getInt("id");
             
             int idUser= rs.getInt("idUser");
       
              int idEvent =rs.getInt("idEvent");
                 ServiceParticipation com = new ServiceParticipation();
               String email = com.getnomuserbyId(idUser);

               

             dataP.add(new  Participation(id,email,idEvent));
             //dataL.add(new  Livraison(rs.getInt("idlivraison"),email ,rs.getInt("idcommande") ));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_participation.setItems(dataP); 
      
     
    }
    
     private void loadDataOffre() {
   dataO.clear();
         try {
           pst =con.prepareStatement("Select * from offre");

    rs=pst.executeQuery();
     while (rs.next()) {                
             dataO.add(new  Offre(rs.getInt("id"), rs.getInt("prix"), rs.getDate("datedebut"), rs.getDate("datefin"),rs.getString("description")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_Offre.setItems(dataO);
        searchOffre.setText("");
    }
    
    
    private void afficherEvenement(){

             idvenement_col.setCellValueFactory(new PropertyValueFactory <>("idevenement"));
             date_event_col.setCellValueFactory(new PropertyValueFactory <>("dateevenement"));
             descriptionE_col.setCellValueFactory(new PropertyValueFactory <>("description"));
             enseignant_col.setCellValueFactory(new PropertyValueFactory <>("idenseignant"));
             
    }
    
    private void loadDataEvenement() {
   dataE.clear();
         try {
           pst =con.prepareStatement("Select * from evenement");
           ServiceUser SE=new ServiceUser();
    rs=pst.executeQuery();
     while (rs.next()) {
             Evenement tmp= new Evenement(rs.getInt("id"),rs.getDate("date"),rs.getString("description"),rs.getInt("IdEnseignant"));
//             tmp.setEnseignant(SE.getById(tmp.getIdenseignant()));
             dataE.add(tmp);
     }   
     
     List<User> ls= SE.getList();
     ObservableList<User> cls4 = FXCollections.observableArrayList();
     for (User tmp : ls)
     {
         cls4.add(tmp);
     }
     ENS.setItems(cls4);
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_Evenement.setItems(dataE);
        searchEvent.setText("");
    }
    
   
    
    
     public Offre gettempO(TableColumn.CellEditEvent edittedCell) {
        Offre test = tab_Offre.getSelectionModel().getSelectedItem();
        
        return test;
    }
     
     
     private void setCellValueFromTableToTextFieldOffre()
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
                 if(tab_Offre.getSelectionModel().getSelectedIndex()!=-1){

         TableColumn.CellEditEvent edittedcell = null;
         Offre x=gettempO(edittedcell);         
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
           errorOffre.setText("");
        }
                 }
                 else{
                                 errorOffre.setText("Choix Invalide");

                 }
               
    }

    @FXML
     public void updateOffre(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
       
       if(Validchamp(tf_desc) && fileName.getText()!="Choisir Image" && Validchamp(tf_prix) &&tab_Offre.getSelectionModel().getSelectedIndex()!=-1){
        int i;
           
            
           TableColumn.CellEditEvent edittedcell = null;
           Offre x=gettempO(edittedcell);
           int c=x.getIdoffre();
           Double prixoffre=Double.valueOf(tf_prix.getText());
           Date date_debut=Date.valueOf(datepicker.getValue());
           Date date_fin=Date.valueOf(datepicker1.getValue());
           String description=String.valueOf(tf_desc.getText());            
            
   
              //Category c = new Category(0,Namecat);
            ServiceOffre prod=new ServiceOffre();
            Offre u=new Offre(c,prixoffre,date_debut,date_fin ,description);
            u.setImage(fileName.getText());
         
       
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
           errorOffre.setText("");
           
        }
       }
       else{
                       errorOffre.setText("Input ou Choix Invalide");

       }
          
        
        

    }
     
    @FXML
     public void deleteEvent(){
         if(tab_Evenement.getSelectionModel().getSelectedIndex()!=-1){
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
    loadDataParticipation();
    errorEvent.setText("");
         }
         catch(SQLException ex)
         {
             
         }
         }
         else{
                         errorEvent.setText("Choix Invalide");

         }
         
         
     }
     
    @FXML
     public void updateEvent(){
         if(Validchamp(tf_desc1)){
         int i=0;
        ServiceUser se= new ServiceUser();
        Date dateevenemet = Date.valueOf(datepicker11.getValue());
        String description =tf_desc1.getText();
              User idenseignant =  ENS.getValue();
     
             int ide=idenseignant.getId();
        ServiceEvenement Bl = new ServiceEvenement();
       
        Evenement tmp2=tab_Evenement.getSelectionModel().getSelectedItem();
       Evenement B = new Evenement(tmp2.getIdevenement(),dateevenemet,description,ide);
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
    errorEvent.setText("");
         }
         else{
             errorEvent.setText("Input ou Choix Invalide");
         }
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
        
    @FXML
    void search_event(){
       ServiceEvenement ser = new ServiceEvenement();
                     List<Evenement> list = ser.displayClause(" WHERE id LIKE '%"+searchEvent.getText()+"%' or date LIKE '%"+searchEvent.getText()+"%' or description LIKE '%"+searchEvent.getText()+"%' or idEnseignant LIKE '%"+searchEvent.getText()+"%'");
                     ObservableList<Evenement> cls = FXCollections.observableArrayList();
                     ServiceUser se= new ServiceUser();
                     for (Evenement aux : list)
                     {
                         Evenement tmp= new Evenement(aux.getIdevenement(),aux.getDateevenement(), aux.getDescription(), aux.getIdenseignant());
                         // tmp.setEnseignant(se.getById(tmp.getIdenseignant()));
                          cls.add(tmp);  
                     }
                     tab_Evenement.setItems(cls);
                      /*  searchEvent.setOnKeyReleased(e->{
    if(searchEvent.getText().equals("")){
        loadDataEvenement();
    }
    else{
        dataE.clear();
        String sql = "SELECT * FROM evenement WHERE `id` LIKE LIKE '%"+searchEvent.getText()+"%'"
                + "UNION SELECT * FROM evenement where `description` LIKE '%"+searchEvent.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
            int id=rs.getInt("id");
             
             Date date= rs.getDate("date");
       String desc=rs.getString("description");
              int idEvent =rs.getInt("idEvent");
              int idUser =rs.getInt("idUser");
                 ServiceParticipation com = new ServiceParticipation();
               String email = com.getnomuserbyId(idUser);

               

             dataE.add(new  Evenement(id,date,desc,idEvent));
 
        }
        tab_Evenement.setItems(dataE);
    } catch (SQLException ex) {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
    */
       
    }
    
    @FXML
            
    void search_participation(){
      /*  ServiceParticipation ser = new ServiceParticipation();
                     List<Participation> list = ser.displayClause(" WHERE idUser LIKE '%"+searchParticipation.getText()+"%' or idEvent LIKE '%"+searchParticipation.getText()+"%'");
                     ObservableList<Participation> cls = FXCollections.observableArrayList();
                     ServiceUser se= new ServiceUser();
                     for (Participation aux : list)
                     {
                          aux.getEmail();
                          cls.add(aux);  
                     }
                     tab_participation.setItems(cls);*/
                     searchParticipation.setOnKeyReleased(e->{
    if(searchParticipation.getText().equals("")){
        loadDataParticipation();
    }
    else{
        dataP.clear();
          String sql = "Select * from participation where idUser LIKE '%"+searchParticipation.getText()+"%'"
                + "UNION Select * from participation where idEvent LIKE '%"+searchParticipation.getText()+"%'" ;
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
            int id=rs.getInt("id");
             
             int idUser= rs.getInt("idUser");
       
              int idEvent =rs.getInt("idEvent");
                 ServiceParticipation com = new ServiceParticipation();
               String email = com.getnomuserbyId(idUser);

               

             dataP.add(new  Participation(id,email,idEvent));
 
        }
        tab_participation.setItems(dataP);
    } catch (SQLException ex) {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
    }
    
    
    
    @FXML
    public void print_offre(){
        ServiceOffre se= new ServiceOffre();
        try{
         List<Offre> list = se.afficheroffre();
       
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                        File saveFile = fileChooser.showSaveDialog(tab_Offre.getScene().getWindow());
                        System.out.println(saveFile.getAbsolutePath());
                        OutputStream file= new FileOutputStream(new File(saveFile.getAbsolutePath()));
                        Document document = new Document();
                        PdfWriter.getInstance(document,file);
                        document.open();
                        pdf.addMetaData(document);
                        pdf.addTitlePage(document, list);
                        document.close(); 
                        file.close();
          }
        catch(Exception ex)
        {
            
        }


    }
    
    @FXML
    public void print_event(){
                        
        ServiceEvenement se= new ServiceEvenement();
        try{
         List<Evenement> list = se.afficherevenement();
          ServiceUser SE= new ServiceUser();
                     for (Evenement aux : list)
                     {
                         aux.setEnseignant(SE.getById(aux.getIdenseignant()));
                     }
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                        File saveFile = fileChooser.showSaveDialog(tab_Offre.getScene().getWindow());
                        System.out.println(saveFile.getAbsolutePath());
                        OutputStream file= new FileOutputStream(new File(saveFile.getAbsolutePath()));
                        Document document = new Document();
                        PdfWriter.getInstance(document,file);
                        document.open();
                        pdf.addMetaData2(document);
                        pdf.addTitlePage2(document, list);
                        document.close(); 
                        file.close();
          }
        catch(Exception ex)
        {
            
        }
        
                        
                        

    }
    @FXML
    public void open_image(){
        try{
        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                        new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"),
                        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png")
                        );
                        File saveFile = fileChooser.showOpenDialog(tab_Offre.getScene().getWindow());
                        System.out.println(saveFile.getName());
                        fileName.setText(saveFile.getName());
                        File output = new File("\"C:\\\\wamp64\\\\www\\\\pidev\\\\IngenSchoolIntegration\\\\src\\\\com\\\\school\\\\images\""+saveFile.getName());
                        Files.copy(saveFile.toPath(),output.toPath());
        }
                        catch(Exception ex)
        {
            
        }
    }
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }

    
 


}

    