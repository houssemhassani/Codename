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
import com.mycompany.entities.Service;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hamza
 */
public class ServiceService {

    //singleton 
    public static ServiceService instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceService();
        }
        return instance;
    }

    public ServiceService() {
        req = new ConnectionRequest();

    }

    //ajout 
    public void ajoutService(Service s) {

        String url = Statics.BASE_URL + "/service/new?nomService=" + s.getNomService();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    }

    //affichage
    public ArrayList<Service> affichageService() {

        ArrayList<Service> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/service/affiche";
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                Map<String, Object> mapService = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapService.get("root");
                System.err.println(mapService);
                for (Map<String, Object> obj : listOfMaps) {
                    Service service = new Service();
                    
                    String nomServiceString = obj.get("nomservice").toString();
                    
                    service.setNomService(nomServiceString);
                    
                    result.add(service);
                    System.out.println(service);
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    /*    
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
    public boolean modifierDemande(Secteur d) {
        String url = Statics.BASE_URL +"/demande/editjson?iddemande="+d.getId_secteur();
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
        
    }*/
}
