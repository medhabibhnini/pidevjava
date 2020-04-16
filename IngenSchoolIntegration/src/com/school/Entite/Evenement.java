/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;
import java.util.Date;
import com.school.Entite.User;
/**
 *
 * @author farouk
 */
public class Evenement {
     private int idevenement;
     private Date dateevenement;
     private String description;
     private User idenseignant;
     private int ens;

    public Evenement(int idevenement, Date dateevenement, String description, User idenseignant) {
        this.idevenement = idevenement;
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant = idenseignant;
    }

    public int getEns() {
        return ens;
    }

    public void setEns(int ens) {
        this.ens = ens;
    }

    public Evenement(int idevenement, Date dateevenement, String description, int idenseignant) {
        this.idevenement = idevenement;
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant = new User(idenseignant);
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
        return idenseignant.getId();
    }
    public String getEnseignant(){
        return this.idenseignant.getUsername();
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
        this.idenseignant.setId(idenseignant);
    }
    public void setEnseignant(User ens){
        this.idenseignant=ens;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idevenement=" + idevenement + ", dateevenement=" + dateevenement + ", description=" + description + ", idenseignant=" + idenseignant + '}';
    }

    public Evenement(Date dateevenement, String description,int idEsg) {
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant=new User(idEsg);
    }
    
     
     
     
}
     
     
