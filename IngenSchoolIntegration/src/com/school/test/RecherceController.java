/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;

import com.school.Entite.Cours;
import com.school.Service.ServiceCours;
import com.school.Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Zied
 */
public class RecherceController implements Initializable {
    private ObservableList<Cours> data;
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private TableView<Cours> tab_cours;
    @FXML
    private TableColumn<Cours, ?> idcours;
    @FXML
    private TableColumn<Cours, ?> titrecours;
    @FXML
    private TableColumn<Cours, ?> matierecours;
    @FXML
    private TableColumn<Cours, ?> datecours;
    @FXML
    private TableColumn<Cours, ?> dureecours;
    @FXML
    private TextField tf_recherche;
    @FXML
    private ImageView vw_image;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con = DataSource.getInstance().getCnx();
         data= FXCollections.observableArrayList();
         afficherCours();
         loadDataCours();
         setCellValueFromTableToTextFieldcours();
         search();
    }    
    
    private void setCellValueFromTableToTextFieldcours(){
    tab_cours.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Cours cour=tab_cours.getItems().get(tab_cours.getSelectionModel().getSelectedIndex());






    
         TableColumn.CellEditEvent edittedcell = null;
            Cours c=gettemp(edittedcell);  
            
      
            String photo;
            try {
                photo = getImagebyId(c.getIdcours());
                
           
           
           
            Image imageURL= new Image("file:///C:\\wamp64\\www\\etudes\\src\\com\\etudes\\images/" + photo);

                    
       
        vw_image.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
});

    }
     public Cours gettemp(TableColumn.CellEditEvent edittedCell) {
        Cours test = tab_cours.getSelectionModel().getSelectedItem();
        
        return test;
    }
         public String getImagebyId(int ide) throws SQLException
    {
        String i="";
          Statement ste;
        String  id=null;
           String query="SELECT image_name as image_name FROM cours WHERE id LIKE '%"+ide+"%'";
           ste=con.createStatement();
        ResultSet rst = ste.executeQuery(query); 
         while(rst.next())
        {
             i=rst.getString("image_name");
            
        }
      
        
        return i;
    }
         
    private void loadDataCours() {
   data.clear();
         try {
           pst =con.prepareStatement("Select * from cours");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new  Cours(rs.getInt("id"), rs.getString("titreCours"), rs.getString("matiere"), rs.getDate("updated_at"), rs.getString("duree"), rs.getString("pdfname")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_cours.setItems(data);
    }
    private void afficherCours(){

             titrecours.setCellValueFactory(new PropertyValueFactory <>("titreCours"));
             matierecours.setCellValueFactory(new PropertyValueFactory <>("matiere"));
             dureecours.setCellValueFactory(new PropertyValueFactory <>("duree"));
             datecours.setCellValueFactory(new PropertyValueFactory <>("updated_at"));
    }
         
         
         
         public void search()
        {
search.setOnKeyReleased(e->
{
    if(search.getText().equals(""))
    {
        loadDataCours();
    }
    else
    {
        data.clear();
          String sql = "Select * from cours where titreCours LIKE '%"+search.getText()+"%'"
                + "UNION Select * from cours where matiere LIKE '%"+search.getText()+"%'" ;
    try 
    {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
         String titre =rs.getString("titreCours");  
         String matiere=rs.getString("matiere");
         Date date = rs.getDate("updated_at");
         String duree=rs.getString("duree");

         
     
                      
    
             data.add(new Cours(titre, matiere, date,duree));
 
        }
        tab_cours.setItems(data);
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
   
      
        
        }
    
    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Front.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
