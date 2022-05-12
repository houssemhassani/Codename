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
import com.mycompany.entites.Publication;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Gaston
 */
public class ServicePublication {
    
    public static ServicePublication instance= null;
     public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServicePublication getInstance(){
        if(instance == null)
            instance = new ServicePublication();
        return instance;
    }
    public ServicePublication(){
        req = new ConnectionRequest();
    }
    
    public void ajoutPublication(Publication publication){
        String url =Statics.BASE_URL+"ajout/pub/addPubJSON/new?status="+publication.getStatus()+"&photo="+publication.getPhoto();
        
        req.setUrl(url);
        req.addResponseListener((e->{
            String str = new String(req.getResponseData());
            System.out.println("data =="+str);
        }));
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    public ArrayList<Publication>affichagePublication(){
    
    ArrayList<Publication> result = new ArrayList<>();
    String url = Statics.BASE_URL+"ajout/pub/AllPub";
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>(){
        
        @Override
        public void actionPerformed(NetworkEvent evt){
        JSONParser jsonp;
        jsonp = new JSONParser();
        try{
            Map<String,Object>mapPublication = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapPublication.get("root");
              System.err.println(mapPublication);
            for(Map<String,Object> obj : listOfMaps){
                Publication pu = new Publication();
                
                float id = Float.parseFloat(obj.get("id").toString());
                String status = obj.get("status").toString();
                String photo = obj.get("photo").toString();
                
                pu.setId((int) id);
                pu.setStatus(status);
                pu.setPhoto(photo);
                
                result.add(pu);
                System.out.println(pu);
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;  
    }

 public boolean deletePublication(int id ) {
        String url = Statics.BASE_URL+"ajout/pub/deletePubJSON/"+id;
        
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

public boolean modifierPublication(Publication Publication) {
        String url = Statics.BASE_URL+"ajout/pub/updatePubJSON/"+Publication.getId()+"?status="+Publication.getStatus()+"&photo="+Publication.getPhoto();
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
