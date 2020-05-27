/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Entite;
import java.util.Date;
import com.divers.Entite.Enseignant;
/**
 *
 * @author farouk
 */
public class Evenement {
     private int idevenement;
     private Date dateevenement;
     private String description;
     private Enseignant idenseignant;
     private String nomevent;

    public Evenement(int idevenement, Date dateevenement, String description, int idenseignant,String nomevent) {
        this.idevenement = idevenement;
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant = new Enseignant(idenseignant);
        this.nomevent=nomevent;
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
    public String getNomevent(){
        return this.nomevent;
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
    public void setEnseignant(Enseignant ens){
        this.idenseignant=ens;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "idevenement=" + idevenement + ", dateevenement=" + dateevenement + ", description=" + description + ", idenseignant=" + idenseignant + '}';
    }

    public Evenement(Date dateevenement, String description,int idEsg,String nomevent) {
        this.dateevenement = dateevenement;
        this.description = description;
        this.idenseignant=new Enseignant(idEsg);
        this.nomevent=nomevent;
    }
    public Evenement(int Id) {
        this.idevenement=Id;
    }
    public Evenement(){
        
    }
    
     
     
     
}
     
     
