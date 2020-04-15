/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Mohamedhabib - pc
 */
public class Commande {
    private int idcommande;
    private Livre l;
    private Date datecommande;
    private int user;
 private int idl;
 private String idln;
 private LocalDate date;
 
 
    public Commande(Livre l, int user) {
        this.l = l;
        this.user = user;
    }

    public Commande(Livre l) {
     this.l = l;
    }

    public String getIdln() {
        return idln;
    }

    public void setIdln(String idln) {
        this.idln = idln;
    }

    public Commande(int idcommande, int user, String idln, Date datecommande) {
        this.idcommande = idcommande;
        this.datecommande = datecommande;
        this.user = user;
        this.idln = idln;
    }

   

  
  

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public Commande(int idcommande, int user,int idl, Date datecommande) {
        this.idcommande = idcommande;
        this.user = user;
        this.idl = idl;
        this.datecommande = datecommande;
        
    }

    public Commande(int idcommande, int user, Livre livre, Date datecommande) {
        this.idcommande = idcommande;
        this.l = livre;
        this.datecommande = datecommande;
        this.user = user;
    }

    public Commande(Livre l, int user, Date datecommande) {
        this.l = l;
        this.datecommande = datecommande;
        this.user = user;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public Livre getL() {
        return l;
    }

    public void setL(Livre l) {
        this.l = l;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }
  
      @Override
    public String toString()
    {
         return "livraison{" + "id commande=" + idcommande + ", Livre=" + l + ",  User=" + user + ", datecommande=" + datecommande + '}';
    }
    
    
}
