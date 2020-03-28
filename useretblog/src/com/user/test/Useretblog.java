/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.user.Utils.DataBase;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Mohamedhabib - pc
 */
public class Useretblog extends Application {
    
      @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
            
            Scene scene = new Scene(root);
            
      
       
        primaryStage.setTitle("IngenSchool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
     
    }
    
}
