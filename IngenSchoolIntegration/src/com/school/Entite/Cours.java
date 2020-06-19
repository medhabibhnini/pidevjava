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
 * @author Zied
 */
public class Cours {
    private int idcours;
    private String titreCours;
    private String matiere;
    private Date updated_at;
    private String duree;
    private String image_name;
    private String pdfname;
    

    public Cours(int idcours, String titreCours, String matiere,Date updated_at,String duree,String image_name,String pdfname) {
        this.idcours=idcours;
        this.titreCours=titreCours;
        this.matiere=matiere;
        this.updated_at=updated_at;
        this.duree=duree;
        this.image_name=image_name;
        this.pdfname=pdfname;
    }

    
        public Cours(String titreCours, String matiere,String duree,String image_name,String pdfname) {
        this.titreCours=titreCours;
        this.matiere=matiere;
        this.duree=duree;
        this.image_name=image_name;
        this.pdfname=pdfname;
    }



    public Cours(int idcours, String titreCours, String matiere,Date updated_at, String duree,String pdfname) {
        this.idcours = idcours;
        this.titreCours = titreCours;
        this.matiere = matiere;
        this.updated_at = updated_at;
        this.duree = duree;
        this.pdfname = pdfname;
    }
    
        public Cours(String titreCours, String matiere,Date updated_at, String duree) {

        this.titreCours = titreCours;
        this.matiere = matiere;
        this.updated_at = updated_at;
        this.duree = duree;

    }
    
        public Cours(int idcours, String titreCours, String matiere, String duree) {
        this.idcours = idcours;
        this.titreCours = titreCours;
        this.matiere = matiere;
        this.duree = duree;
    }

    public Cours(int idcours, String titreCours, String matiere,String duree, String image_name,String pdfname) {
        this.idcours = idcours;
        this.titreCours = titreCours;
        this.matiere = matiere;
        this.duree = duree;
        this.image_name = image_name;
        this.pdfname = pdfname;
    }

    public Cours(int idcours, String duree, String matiere) {
        this.idcours = idcours;this.duree = duree;this.matiere = matiere;
    }

    public Cours() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
        @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Cours other = (Cours) obj;
        if (this.idcours != other.idcours) {
            return false;
        }
        if (!Objects.equals(this.titreCours, other.titreCours)) {
            return false;
        }
        if (!Objects.equals(this.matiere, other.matiere)) {
            return false;
        }
        if (!Objects.equals(this.updated_at, other.updated_at)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.image_name, other.image_name)) {
            return false;
        }
        if (!Objects.equals(this.pdfname, other.pdfname)) {
            return false;
        }

        return true;
    }
    
    
    
    

    public int getIdcours() {
        return idcours;
    }

    public void setIdcours(int idcours) {
        this.idcours = idcours;
    }

    public String getTitreCours() {
        return titreCours;
    }

    public void setTitreCours(String titreCours) {
        this.titreCours = titreCours;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

    



    @Override
    public String toString() {
        return "Cours{" + "idcours=" + idcours + ", titreCours=" + titreCours + ", matiere=" + matiere + ", updated_at=" + updated_at  + ", duree=" + duree  + ", image_name=" + image_name + ", pdfname=" + pdfname + '}';
    }

   


  
    
}
