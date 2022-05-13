/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Equipe;
import com.mycomany.utils.Statics;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Moemen
 */
public class ServiceEquipe {
        public static boolean resultOk = true;

    //singleton
public static ServiceEquipe instance = null;
//imitilimation conmection request
private ConnectionRequest req;
public static ServiceEquipe getInstance(){
    if(instance == null )
            instance = new ServiceEquipe();
    return instance;
}
public ServiceEquipe()
{
    req = new ConnectionRequest();
}

    //ajout 

public void ajoutEquipe(Equipe equipe)
{
        String url =Statics.BASE_URL+"/equipe/AjoutEquipeJSON?numEquipe="+equipe.getNumEquipe();// 
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }


//affichage
 public ArrayList<Equipe>AffichageEquipe() {
        ArrayList<Equipe> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/equipe/testJSONequipe";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapEquipe = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapEquipe.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Equipe e1 = new Equipe();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("idEquipe").toString());
                        
                        float numero = Float.parseFloat((obj.get("numEquipe").toString()));
                        

                        
                        e1.setIdEquipe((int)id);
                        e1.setNumEquipe((int)numero);
                        result.add(e1);
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
//Detail Equipe bensba l detail n5alihoa lel5r ba3d delete+update
public Equipe DetailleEquipe( int id ,Equipe e1 ) {
        
        String url = Statics.BASE_URL+"/equipe/AffichageEquipeParIDJSON?idEquipe="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                e1.setNbreEmp((int) obj.get("NbreEmp"));
                e1.setNumEquipe((int) obj.get("NumEquipe"));
                e1.setCorX((int) obj.get("cordnerX"));
                e1.setCorY((int) obj.get("cordnerY"));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return e1;
        
        
    }



//Delete 
public boolean deleteEquipe(int id ) {
        String url = Statics.BASE_URL +"/equipe/deleteEquipeJSON?idEquipe="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }


//Update 
public boolean modifierEquipe(Equipe equipe) {
        String url = Statics.BASE_URL +"/equipe/updateNumEquipeJSON?idEquipe="+equipe.getIdEquipe()+"&numEquipe="+equipe.getNumEquipe();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
}

