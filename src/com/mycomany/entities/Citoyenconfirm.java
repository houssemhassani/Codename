/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Asus
 */
public class Citoyenconfirm {
    private int confirmee,nonconfirmee;

    public Citoyenconfirm() {
    }

    public int getConfirmee() {
        return confirmee;
    }

    public Citoyenconfirm(int confirmee, int nonconfirmee) {
        this.confirmee = confirmee;
        this.nonconfirmee = nonconfirmee;
    }

    public void setConfirmee(int confirmee) {
        this.confirmee = confirmee;
    }

    public int getNonconfirmee() {
        return nonconfirmee;
    }

    public void setNonconfirmee(int nonconfirmee) {
        this.nonconfirmee = nonconfirmee;
    }

    @Override
    public String toString() {
        return "Citoyenconfirm{" + "confirmee=" + confirmee + ", nonconfirmee=" + nonconfirmee + '}';
    }
    
    
}
