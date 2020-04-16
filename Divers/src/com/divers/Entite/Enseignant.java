/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divers.Entite;

/**
 *
 * @author MrStealYourMom
 */
public class Enseignant {
    private int id;
    private String username;
    private String email;
    
    public Enseignant(){
        
    };
    public Enseignant(int id){
        this.id=id;
    }
    public Enseignant(int id, String username, String email){
        this.id=id;
        this.username=username;
        this.email=email;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    
    public String toString(){
        return this.username;
    }
    public void setId(int id)
    {
        this.id=id;
    }
}
