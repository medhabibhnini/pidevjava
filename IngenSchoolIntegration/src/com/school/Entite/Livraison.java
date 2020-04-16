/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;

/**
 *
 * @author Mohamedhabib - pc
 */
public class Livraison {
    private int idlivraison;
    private Commande C;
    private int user;
    private int idc;
    private String username;
    private String livre;
    
    

    public Livraison(int idlivraison, Commande C, int user, int idc) {
        this.idlivraison = idlivraison;
        this.C = C;
        this.user = user;
        this.idc = idc;
    }
    public Livraison(int idlivraison,String username, int idc) {
        this.idlivraison = idlivraison;
       
        this.username = username;
        this.idc = idc;
    }
      public Livraison(int idlivraison,String username, String livre) {
        this.idlivraison = idlivraison;
       
        this.username = username;
        this.livre = livre;
    }

    public String getLivre() {
        return livre;
    }

    public void setLivre(String livre) {
        this.livre = livre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public Livraison(int idlivraison, int user, int idc) {
        this.idlivraison = idlivraison;
        this.user = user;
        this.idc = idc;
    }
    

    public Livraison(int idlivraison, Commande C, int user) {
        this.idlivraison = idlivraison;
        this.C = C;
        this.user = user;
    }

    public Livraison(Commande C, int user) {
        this.C = C;
        this.user = user;
    }
    

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
   

    public int getIdlivraison() {
        return idlivraison;
    }

    public Commande getC() {
        return C;
    }

    public void setC(Commande C) {
        this.C = C;
    }

 
    @Override
    public String toString()
    {
        return "livraison{" + "id livraison=" + idlivraison + ", user=" + user + ", Commande=" + C + '}';
    }
}
