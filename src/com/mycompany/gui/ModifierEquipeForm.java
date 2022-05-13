/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Equipe;
import com.mycompany.services.ServiceEquipe;

/**
 *
 * @author Lenovo
 */
public class ModifierEquipeForm extends BaseForm {
    
    Form current;
    public ModifierEquipeForm(Resources res , Equipe e1) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Equipe");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Numero = new TextField(String.valueOf(e1.getNumEquipe()), "Numero" , 20 , TextField.ANY);
        TextField NbreEmp = new TextField(String.valueOf(e1.getNbreEmp()) , "Nbre Emp" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        
        
        
        
        Numero.setUIID("NewsTopLine");
        NbreEmp.setUIID("NewsTopLine");
        
        Numero.setSingleLineTextArea(true);
        NbreEmp.setSingleLineTextArea(true);
        NbreEmp.setEditable(false);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           e1.setNumEquipe(Integer.parseInt(Numero.getText()));
           
           
       
       //appel fonction modfier reclamation men service
       
       if(ServiceEquipe.getInstance().modifierEquipe(e1)) { // if true
           new ListEquipeForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListEquipeForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Numero),
                createLineSeparator(),
                new FloatingHint(NbreEmp),
                createLineSeparator(),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }

    
}
