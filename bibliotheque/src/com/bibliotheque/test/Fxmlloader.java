/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibliotheque.test;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Mohamedhabib - pc
 */
public class Fxmlloader {
    private Pane view;
    
    /*public Pane getPage(String fileName)
    {
        URL fileUrl = com.bibliotheque.test.class.getRessource("/com.bibliotheque.test"+fileName+".fxml");
        try {
            view  = new FXMLLoader().load(fileUrl);
        } catch (IOException ex) {
            Logger.getLogger(Fxmlloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return view;
    }*/
}
