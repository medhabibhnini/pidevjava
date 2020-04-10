/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Entite;
import java.util.Date;
/**
 *
 * @author farouk
 */
public class Evenement {
     private int idevenement;
     private Date dateevenement;
     private String description;
     private int idenseignant;

    public Evenement(int idevenement, Date dateevenement, String description, int idenseignant) {
        this.idevenement = idevenement;
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant = idenseignant;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public Date getDateevenement() {
        return dateevenement;
    }

    public String getDescription() {
        return description;
    }

    public int getIdenseignant() {
        return idenseignant;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public void setDateevenement(Date dateevenement) {
        this.dateevenement = dateevenement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdenseignant(int idenseignant) {
        this.idenseignant = idenseignant;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idevenement=" + idevenement + ", dateevenement=" + dateevenement + ", description=" + description + ", idenseignant=" + idenseignant + '}';
    }

    public Evenement(Date dateevenement, String description,int idEsg) {
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant=idEsg;
    }
     
     
     
}
     
     
