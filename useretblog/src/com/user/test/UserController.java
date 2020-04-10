/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import com.user.Entite.User;
import com.user.Service.ServiceUser;
import com.user.Utils.DataBase;
import java.awt.AWTException;
import com.user.Entite.Blog;
import com.user.Service.ServiceBlog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * FXML Controller class
 *
 * @author Daly
 */
public class UserController implements Initializable 
{
    
    private ObservableList<User> data;
    private ObservableList<Blog> datab;
    private ResultSet rs=null;
    private PreparedStatement pst;
    private Connection con;
    private String path ;
    
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_role;
    @FXML
    private TableColumn<User, ?> username_colum;
    @FXML
    private TableColumn<User, ?> email_colum;
    @FXML
    private TableColumn<User, ?> role_colum;
    @FXML
    private TableView<User> tab_view;
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
    private TextField search;
    @FXML
    private Button browse;
    @FXML
    private TextField timage;
    @FXML
    private ImageView imgview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        con = DataBase.getInstance().getConnection();
        data= FXCollections.observableArrayList();
        datab= FXCollections.observableArrayList();
        afficheruser();
        loadDataUser();
        setCellValueFromTableToTextFieldUser();
        setCellValueFromTableToTextFieldprod();
        afficherblog();
        loadDataBlog();
        searchBlog();
        
        
    } 
    
    
    
    
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
   data.clear();
         try {
           pst =con.prepareStatement("Select * from fos_user");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("roles")));
     }      
         }
       catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_view.setItems(data);
    } 

  @FXML
     public void updateprod(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
     
       
        int i;
           
            
    TableColumn.CellEditEvent edittedcell = null;
           User x=gettemp(edittedcell);
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
            User x=gettemp(edittedcell);  
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

      public User gettemp(TableColumn.CellEditEvent edittedCell) 
      {
        User test = tab_view.getSelectionModel().getSelectedItem();
        
        return test;
    }
      
        private void setCellValueFromTableToTextFieldUser()
        {
       tab_view.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
           
        @Override
        public void handle(MouseEvent event) {
User us=tab_view.getItems().get(tab_view.getSelectionModel().getSelectedIndex());



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
         image1= imgview.getImage();
         
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
  
  
  
  
                   @FXML
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
search.setOnKeyReleased(e->
{
    if(search.getText().equals(""))
    {
        loadDataBlog();
    }
    else
    {
        datab.clear();
          String sql = "Select * from blog where sujet LIKE '%"+search.getText()+"%'"
                + "UNION Select * from blog where description LIKE '%"+search.getText()+"%'" ;
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
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }  
        
        public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\pidevjava\\useretblog\\src\\com\\user\\images");
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
    
    
 private void ResetB()
{
    
    tf_sujet.setText(null);
    tf_description.setText(null);
       
    
}
 
    

    

    @FXML
    private void DecoB(javafx.scene.input.MouseEvent event) throws IOException 
    {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
    
    private void setCellValueFromTableToTextFieldprod()
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
                photo = getImagebyId(l.getIdb());
                System.out.println(photo);
           
           
           
            Image imageURL= new Image("file:///C:/wamp64/www/pidevjava/useretblog/src/com/user/images/" + photo);

                    
       
        imgview.setImage(imageURL);
             } catch (SQLException ex) 
             {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
    
    
    public String getImagebyId(int ide) throws SQLException
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
