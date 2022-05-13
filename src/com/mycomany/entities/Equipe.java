/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

/**
 *
 * @author Moemen
 */
public class Equipe {
    int idEquipe;
    int numEquipe;
    int nbreEmp=0;
    String Service;
    int CorX;
    int CorY;

    public int getCorX() {
        return CorX;
    }

    public void setCorX(int CorX) {
        this.CorX = CorX;
    }

    public int getCorY() {
        return CorY;
    }

    public void setCorY(int CorY) {
        this.CorY = CorY;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getNumEquipe() {
        return numEquipe;
    }

    public void setNumEquipe(int numEquipe) {
        this.numEquipe = numEquipe;
    }

    public int getNbreEmp() {
        return nbreEmp;
    }

    public void setNbreEmp(int nbreEmp) {
        this.nbreEmp = nbreEmp;
    }

    public String getService() {
        return Service;
    }

    public void setService(String Service) {
        this.Service = Service;
    }

    public Equipe() {
    }

    public Equipe(int numEquipe) {
        this.numEquipe = numEquipe;
    }

    public Equipe(int numEquipe, String Service) {
        this.numEquipe = numEquipe;
        this.Service = Service;
    }

    @Override
    public String toString() {
        return "Equipe{" + "idEquipe=" + idEquipe + ", numEquipe=" + numEquipe + ", nbreEmp=" + nbreEmp + ", Service=" + Service + ", CorX=" + CorX + ", CorY=" + CorY + '}';
    }

   
    
    
}
