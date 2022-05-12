/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author Gaston
 */
public class Publication {
     private int id;
     private String status;
     private String photo;
    

    public Publication() {
    }

     
     
    public Publication(int id, String status, String photo) {
        this.id = id;
        this.status = status;
        this.photo = photo;
        
    }

    public Publication(String status, String photo) {
        this.status = status;
        this.photo = photo;
      
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
     
}
