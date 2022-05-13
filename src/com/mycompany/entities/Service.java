/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author rachdi
 */
public class Service {
    
    private int id;
    private String nomService;

    public Service(int idService, String nomService) {
        this.id = idService;
        this.nomService = nomService;
    }

    public Service() {
        
    }

    public int getIdService() {
        return id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setIdService(int idService) {
        this.id = idService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    @Override
    public String toString() {
        return "Service{" + "idService=" + id + ", nomService=" + nomService + '}';
    }
    
    
}

    

