/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Entite;
import com.divers.Entite.Enseignant;

/**
 *
 * @author MrStealYourMom
 */
public class Participation {
    private int id;
    private Enseignant idUser;
    private Evenement idEvent;

    public Participation() {
    }

    public Participation(int id, int idUser, int idEvent) {
        this.id = id;
        this.idUser=new Enseignant(idUser);
        this.idEvent=new Evenement(idEvent);
    }

    public int getId() {
        return id;
    }

    public int getIdEvent() {
        return this.idEvent.getIdevenement();
    }

    public int getIdUser() {
        return idUser.getId();
    }
    
    public String getUser(){
        return this.idUser.getUsername();
    }
    public String getEvent(){
        return this.idEvent.getNomevent();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent.setIdevenement(idEvent);
    }

    public void setIdUser(int idUser) {
        this.idUser.setId(idUser);
    }
    public void setUser(Enseignant User){
        this.idUser=User;
    }
    public void setEvent(Evenement evnt){
        this.idEvent=evnt;
    }
    
    
    
}
