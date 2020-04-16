/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;
import com.school.Entite.User;

/**
 *
 * @author MrStealYourMom
 */
public class Participation {
    private int id;
    private User idUser;
    private int idEvent;
    private Evenement event;
    private int idu;
    private String email;

    public Participation(int idu,Evenement event) {
        this.event = event;
        this.idu = idu;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Participation(int id, String email, int idEvent) {
        this.id = id;
         this.email = email;
        this.idEvent = idEvent;
       
    }
    

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
    

    public Participation() {
    }

    public Participation( int idu,int idEvent) {
        this.idEvent = idEvent;
        this.idu = idu;
    }

    public Participation(int id, int idUser, int idEvent) {
        this.id = id;
        this.idUser=new User(idUser);
        this.idEvent = idEvent;
    }

    public int getId() {
        return id;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public User getIdUser() {
        return idUser;
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
    public void setUser(User User){
        this.idUser=User;
    }
    
    
    
}
