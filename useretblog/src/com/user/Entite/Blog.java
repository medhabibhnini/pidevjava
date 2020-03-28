/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.Entite;

import java.util.Objects;


/**
 *
 * @author Daly
 */
public class Blog 
{
    private int idb ;
    private String sujet ;
    private String description ;
    
    

    public Blog(int idb, String sujet, String description) {
        this.idb = idb;
        this.sujet = sujet;
        this.description = description;
       
       
    }

    public Blog(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
        
        
    }
    

    @Override
    public String toString() {
        return "Blog{" + "idb=" + idb + ", sujet=" + sujet + ", description=" + description + '}';
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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public Blog() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.idb != other.idb) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

   

    
    
    
    
    
    
    
}
