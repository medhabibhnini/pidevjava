/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.Entite;


/**
 *
 * @author Daly
 */
public class Blog 
{
    private int idb ;
    private String sujet ;
    private String description ;
    private String type ;
    private User U ;

    public Blog(int idb, String sujet, String description, String type, User U) {
        this.idb = idb;
        this.sujet = sujet;
        this.description = description;
        this.type = type;
        this.U = U;
    }

    public Blog(String sujet, String description, String type, User U) {
        this.sujet = sujet;
        this.description = description;
        this.type = type;
        this.U = U;
    }
    

    @Override
    public String toString() {
        return "Blog{" + "idb=" + idb + ", sujet=" + sujet + ", description=" + description + ", type=" + type + ", U=" + U + '}';
    }
    

    public int getIdb() {
        return idb;
    }

    public void setIdb(int idb) {
        this.idb = idb;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getU() {
        return U;
    }

    public void setU(User U) {
        this.U = U;
    }
    
    
    
    
    
    
}
