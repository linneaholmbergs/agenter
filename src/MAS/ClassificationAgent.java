/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAS;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linnea
 */
public class ClassificationAgent extends Agent{
    List<WhiteWine> mottagetData;
    
    
    @Override
        protected void setup() {
            addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action(){
             ACLMessage msg = receive();
             
             if(msg!=null){
                 try {
                     
                     mottagetData = ((List<WhiteWine>)msg.getContentObject());
                     for(WhiteWine vin : mottagetData){
                         System.out.println(vin.getChlorides());
                     }
                 
                 
                 } catch (UnreadableException ex) {
                     Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
                 }
                    
                
                
             }
            }});
        }
}
          

         
    

