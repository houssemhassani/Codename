/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Employee;
import com.mycomany.utils.Statics;
import com.mycompany.gui.AjoutCitoyenForm;
import com.mycompany.gui.SessionManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField username,TextField prenom,TextField cin,TextField password,TextField email, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/register?nom="+username.getText().toString()+"&email="+email.getText().toString()+
                "&motDePasse="+password.getText().toString()+"&prenom="+prenom.getText().toString()+"&cin="+cin.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(username.getText().equals(" ") || password.getText().equals("") || email.getText().equals("") || cin.getText().equals("")||prenom.getText().equals("")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            System.out.println(json);
            if(json.equals("success")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
        });
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/login?cin="+username.getText().toString()+"&motDePasse="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("motDePasse").toString());
                SessionManager.setUserName(user.get("cin").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new AjoutCitoyenForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String cin,String email, Resources rs ) 
    {

        String url = Statics.BASE_URL+"/reset-password?cin="+cin;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        req.addResponseListener((e) ->{
            JSONParser j = new JSONParser();
             json = new String(req.getResponseData()) + "";
            try {
                System.out.println("data =="+json);
                String password = json;
                System.out.println(password);
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
     public String  genererQrCode(Employee cit)
    {
        FileOutputStream fos=null;
        try {
            ByteArrayOutputStream out=QRCode.from(cit.toString()).to(ImageType.JPG).stream();
            String path="C:\\Users\\Asus\\Documents\\NetBeansProjects\\Project_Pidev\\src\\images\\"+cit.getId()+".jpg";
            File f=new File(path);
            fos = new FileOutputStream(f);
            fos.write(out.toByteArray());
            fos.flush();
            System.out.println(path);
            //System.out.println(f.toPath());
            return cit.getNom();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            return null;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        } 
        
        }

}
