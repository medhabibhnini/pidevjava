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
    private int idEvent;

    public Participation() {
    }

    public Participation(int id, int idUser, int idEvent) {
        this.id = id;
        this.idUser=new Enseignant(idUser);
        this.idEvent = idEvent;
    }

    public int getId() {
        return id;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdUser() {
        return idUser.getId();
    }
    
    public String getUser(){
        return this.idUser.getUsername();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdUser(int idUser) {
        this.idUser.setId(idUser);
    }
    public void setUser(Enseignant User){
        this.idUser=User;
    }
    
    
    
}
