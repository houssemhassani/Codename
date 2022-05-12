/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;



import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Demande;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hamza
 */
public class ServiceDemande {
    
    
     //singleton 
    public static ServiceDemande instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceDemande getInstance() {
        if(instance == null )
            instance = new ServiceDemande();
        return instance ;
    }
    
    
    
    public ServiceDemande() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutDemande(Demande d) {
        
        String url =Statics.BASE_URL+"/demande/newjson?num="+d.getNumDemande()+"&type="+d.getTypeDemande();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    }
    
    
    //affichage
    
    public ArrayList<Demande>affichageDemande() {
        ArrayList<Demande> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/demande/showjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Demande re = new Demande();
                        
                        //dima id fi codename one float 
                        float id = Float.parseFloat(obj.get("id").toString());
                        System.out.println("++++++"+id);
                        
                        float num = Float.parseFloat(obj.get("numDemande").toString());
                        
                        String type = obj.get("typeDemande").toString();
                        String etat = obj.get("etat").toString();
                        
                        re.setId((int)id);
                        re.setNumDemande((int)num);
                        re.setTypeDemande(type);
                        re.setEtat(etat);
                        
                        
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request 

        return result;
        
        
    }
        
    //Delete 
    public boolean deleteDemande(int id ) {
        String url = Statics.BASE_URL +"/demande/deletejson?iddemande="+id;
        
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
    public boolean modifierDemande(Demande d) {
        String url = Statics.BASE_URL +"/demande/editjson?iddemande="+d.getId();
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
