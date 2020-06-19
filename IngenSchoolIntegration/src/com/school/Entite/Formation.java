/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.Entite;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author syrin
 */
public class Formation {
    private int idf;

    private String nomf;
    private String matieref;
    private int idc;
    private String dureef;
    private int prixf;

    public Formation(int idf,  String nomf, String matieref, int idc, String dureef, int prixf) {
        this.idf = idf;

        this.nomf = nomf;
        this.matieref = matieref;
        this.idc = idc;
        this.dureef = dureef;
        this.prixf = prixf;
    }
    public Formation(int idf,  String nomf, String matieref, String dureef, int prixf, int idc) {
        this.idf = idf;

        this.nomf = nomf;
        this.matieref = matieref;
        this.idc = idc;
        this.dureef = dureef;
        this.prixf = prixf;
    }
 
        public Formation(int idf,  String nomf, String matieref,  String dureef, int prixf) {
        this.idf = idf;

        this.nomf = nomf;
        this.matieref = matieref;

        this.dureef = dureef;
        this.prixf = prixf;
    }
             public Formation( String nomf, String matieref,  String dureef, int prixf) {


        this.nomf = nomf;
        this.matieref = matieref;

        this.dureef = dureef;
        this.prixf = prixf;
    }
  
    

        public Formation(  String nomf, String matieref,  String dureef, int prixf,int idc) {
        this.nomf = nomf;
        this.matieref = matieref;
        this.dureef = dureef;
        this.prixf = prixf;
        this.idc = idc;
    }
    
    
    
    


    @Override
    public String toString() {
        return "Formation{" + "idf=" + idf  + ", nomf=" + nomf + ", matieref=" + matieref +  ", dureef=" + dureef +  ", prixf=" + prixf + '}';
    }



    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }




    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getMatieref() {
        return matieref;
    }

    public void setMatieref(String matieref) {
        this.matieref = matieref;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getDureef() {
        return dureef;
    }

    public void setDureef(String dureef) {
        this.dureef = dureef;
    }

    public int getPrixf() {
        return prixf;
    }

    public void setPrixf(int prixf) {
        this.prixf = prixf;
    }

    
    
    
   
    
    
}
