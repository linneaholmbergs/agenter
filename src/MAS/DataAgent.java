/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAS;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.Serializable;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linnea
 */
public class DataAgent extends Agent {

   

    public void setup() {

        try {
            List<WhiteWine> listan = readCSV();
            
            addBehaviour(new OneShotBehaviour(this) {
                
                @Override
                public void action(){
                    ACLMessage aclmsg = new ACLMessage(ACLMessage.REQUEST);
                    aclmsg.addReceiver(new AID("anton", AID.ISLOCALNAME));
                    try {
                        aclmsg.setContentObject((Serializable) listan);
                    } catch (IOException ex) {
                        Logger.getLogger(DataAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    send(aclmsg);
               
            
                    
                    
                    
                }});       
                    } catch (IOException ex) {
            Logger.getLogger(DataAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
      

        

    }

    private List<WhiteWine> readCSV() throws IOException{ 

        BufferedReader br = null;
       
            List<WhiteWine> vitaViner = new ArrayList<WhiteWine>();
            Path vinFil = Paths.get("/Users/linnea/NetBeansProjects/ik2018-Inlamning_1/src/MAS/winequality-white.csv");
            br = Files.newBufferedReader(vinFil, StandardCharsets.UTF_8);
            br.readLine();
            String line;
            while ((line = br.readLine())!= null) {
                String[] record = line.split(";");
                WhiteWine vin = new WhiteWine();
                
                vin.setFixedAcidity(Double.parseDouble(record[0]));
                vin.setVolatileAcidity(Double.parseDouble(record[1]));
                vin.setCitricAcid(Double.parseDouble(record[2]));
                vin.setResidualSugar(Double.parseDouble(record[3]));
                vin.setChlorides(Double.parseDouble(record[4]));
                vin.setFreeSulfurDioxide(Double.parseDouble(record[5]));
                vin.setTotalSulfurDioxide(Double.parseDouble(record[6]));
                vin.setDensity(Double.parseDouble(record[7]));
                vin.setpH(Double.parseDouble(record[8]));
                vin.setSulphates(Double.parseDouble(record[9]));
                vin.setAlcohol(Double.parseDouble(record[10]));
                vin.setQuality(Double.parseDouble(record[11]));
                
                
                
                vitaViner.add(vin);
                
                
                
            }   
            
            //WhiteWine[] vinArray = new WhiteWine[vitaViner.size()];
            //vitaViner.toArray(vinArray);
            
            return vitaViner;
            
            
      
    }
    

   

}
