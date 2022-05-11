/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author hamza
 */
public class Demande {
    
        private int id;
        private int numDemande;
        private String typeDemande;
        private String dateDemande;
        private String etat;
        private int idService;
        private int idCitoyen;

    public Demande(int id, int numDemande, String typeDemande, String dateDemande, String etat, int idService, int idCitoyen) {
        this.id = id;
        this.numDemande = numDemande;
        this.typeDemande = typeDemande;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.idService = idService;
        this.idCitoyen = idCitoyen;
    }

    public Demande() {
    }

    public Demande(int numDemande, String typeDemande) {
        this.numDemande = numDemande;
        this.typeDemande = typeDemande;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumDemande() {
        return numDemande;
    }

    public void setNumDemande(int numDemande) {
        this.numDemande = numDemande;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdCitoyen() {
        return idCitoyen;
    }

    public void setIdCitoyen(int idCitoyen) {
        this.idCitoyen = idCitoyen;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", numDemande=" + numDemande + ", typeDemande=" + typeDemande + ", dateDemande=" + dateDemande + ", etat=" + etat + ", idService=" + idService + ", idCitoyen=" + idCitoyen + '}';
    }
        
        
        
    
}
