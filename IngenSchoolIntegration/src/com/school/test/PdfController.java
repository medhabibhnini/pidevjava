/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.test;

import com.school.Entite.Cours;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JComponent;

import javax.swing.SwingUtilities;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.FontPropertiesManager;
import org.icepdf.ri.util.PropertiesManager;


/**
 * FXML Controller class
 *
 * @author Zied
 */

public class PdfController implements Initializable {
    
    private ObjectProperty<ImageView> currentImage;
    private String pdfPath;
    private SwingController swingController;
    private JComponent viewerPanel;
    private Cours SelectedCours;
    @FXML
    private Label matiere;
    @FXML
    private Label titre;
    @FXML
    private Label pdf;
    @FXML
    private BorderPane borderPane;

    
    
    public void initData(Cours cours){
        SelectedCours = cours;
        matiere.setText(SelectedCours.getMatiere());
        titre.setText(SelectedCours.getTitreCours());
        pdf.setText(SelectedCours.getPdfname());

    }
        @FXML
       private void GoBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("back.fxml")));
                    stage.setScene(scene);
                    stage.show();
 
    
        }
       @FXML
       private void createViewer(BorderPane borderPane) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                swingController = new SwingController();
                swingController.setIsEmbeddedComponent(true);
                PropertiesManager properties = new PropertiesManager(System.getProperties(),
                        ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, "false");
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ROTATE, "false");
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_TOOL, "false");
                properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "1.25");
                properties.setBoolean(PropertiesManager.PROPERTY_SHOW_STATUSBAR_VIEWMODE, Boolean.FALSE);
                properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_PAGENAV, "false");
                ResourceBundle messageBundle = ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE);
                //new FontPropertiesManager(properties, System.getProperties(), messageBundle);
                swingController.getDocumentViewController().setAnnotationCallback(
                        new org.icepdf.ri.common.MyAnnotationCallback(swingController.getDocumentViewController()));
                SwingViewBuilder factory = new SwingViewBuilder(swingController, properties);
                viewerPanel = factory.buildViewerPanel();
                viewerPanel.revalidate();
                SwingNode swingNode = new SwingNode();
                swingNode.setContent(viewerPanel);
                borderPane.setCenter(swingNode);
                swingController.setToolBarVisible(false);
                swingController.setUtilityPaneVisible(false);
            });
        } catch (InterruptedException | InvocationTargetException e) {
        }
    }
       @FXML
       private void openDocument(String document) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingController.openDocument(document);
                viewerPanel.revalidate();
            }
        });
    }
       @FXML
       public String loadPDF(String adresse) throws IOException {
        System.out.println("In load PDf");
        if (!adresse.toLowerCase().endsWith("pdf")) {
            return null;
        }
        String fileName = adresse.substring(adresse.lastIndexOf("/") + 1,
                adresse.lastIndexOf("."));
        String suffix = adresse.substring(adresse.lastIndexOf("."),
                adresse.length());
        File temp = null;
    try {
            InputStream input = new URL(adresse).openStream();
            temp = File.createTempFile(fileName, suffix);
            temp.deleteOnExit();
            Files.copy(input, Paths.get(temp.toURI()),
                    StandardCopyOption.REPLACE_EXISTING);
    } catch (MalformedURLException ex) {
  Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
    }
        return temp.getAbsolutePath();
    }
    
        
  @Override
    public void initialize(URL url, ResourceBundle rb) {
                     //String pdfname = pdf.getText();
        //pdfPath = "file://C:/wamp64/www/etudes/src/com/etudes/pdf/" + pdfname;
     //pdfPathLoad = loadPDF("http://21-04-2017/17854381660C617.pdf");
     //createViewer(borderPane);
     //openDocument(pdfPath );
     //System.out.println("loading file from "+pdfPath);
    }
        @FXML
    private void OpenPdf(ActionEvent event) throws IOException {

     
                    
                     String pdfname = pdf.getText();
        pdfPath = "src//com//etudes//pdf//" + pdfname;
     //pdfPathLoad = loadPDF("http://21-04-2017/17854381660C617.pdf");
     createViewer(borderPane);
     openDocument(pdfPath );
     System.out.println("loading file from "+pdfPath);
        
    }
    
    
    
 
    

      
    
    
  
 
    
    
    
    }
    /**
     * Initializes the controller class.
     */
  
    
