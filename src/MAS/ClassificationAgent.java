/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAS;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author linnea
 */
public class ClassificationAgent extends Agent {

    List<WhiteWine> mottagetData;
    ACLMessage msg;
    RConnection rConnection = null;
    REXP rexp = null;
    File filename;


    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                msg = receive();

                if (msg != null) {
                    try {

                        mottagetData = ((List<WhiteWine>) msg.getContentObject());
                        
                        

                        //calculateInR(data);
                        writeToCSV(mottagetData);

                        
                        calculateInR();
                    } catch (UnreadableException ex) {
                        Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (IOException ex) {
                        Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (REXPMismatchException ex) {
                        Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (REngineException ex) {
                        Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
    }

    public void writeToCSV(List<WhiteWine> data) throws IOException {

        FileWriter fw = null;
        BufferedWriter bw = null;
        System.out.println("Innea i csv");

        try {
            filename = new File("/Users/linnea/NetBeansProjects/ik2018-Inlamning_1/src/MAS/mottagetDataCsv.csv");
            System.out.println("Under filename");
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);

            if (!filename.exists()) {
                filename.createNewFile();
            }
            System.out.println("Innan wrtierer");
            String header = "fixed acidity;volatile acidity;citric acid;residual sugar;chlorides;free sulfur dioxide;total sulfur dioxide;density;pH;sulphates;alcohol;target";

            bw.write(header + "\n");
            for (WhiteWine vin : data){
                bw.write(vin.toString() + "\n");
            }
            
            bw.flush();
            bw.close();
            fw.close();
            System.out.println("Efter writer");

        } catch (IOException ex) {
            Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ClassificationAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void calculateInR() throws REXPMismatchException, RserveException, REngineException {

        rConnection = new RConnection();

        
        rexp = rConnection.eval("viner <- read.table('/Users/linnea/NetBeansProjects/ik2018-Inlamning_1/src/MAS/mottagetDataCsv.csv', header = TRUE, fill = TRUE, sep=\";\")\n" +
                                "library(class)\n" +
                                "set.seed(1234)\n" +
                                "standard.features<-scale(viner[,11:1])\n" +
                                "standardData <- cbind(standard.features,viner[12])\n" +
                                "anyNA(standardData)\n" +
                                "head(standardData)\n" +
                                "standard66 <- sample(1:nrow(standardData),as.integer(0.66*nrow(standardData)))\n" +
                                "standardTrain66 <- standardData[standard66,]\n" +
                                "standardTest66 <- standardData[-standard66,]\n" +
                                "pred <- knn(standardTrain66[,-12],standardTest66[,-12],standardTrain66[,12],k=3)\n" +
                                "confus66 <- table(Target = pred,Prediction = standardTest66[,12])\n" +
                                "accuracyStand66 <- (sum(diag(confus66))/sum(confus66))*100\n" +
                                "capture.output({accuracyStand66})");
//        rexp = rConnection.eval("viner <- read.table('/Users/linnea/NetBeansProjects/ik2018-Inlamning_1/src/MAS/mottagetDataCsv.csv', header = TRUE, fill = TRUE, sep=\";\")\n" +
//                                "library(class)\n" +
//                                "set.seed(1234)\n" +
//                                "standard.features<-scale(viner[,11:1])\n" +
//                                "standardData <- cbind(standard.features,viner[12])\n" +
//                                "anyNA(standardData)\n" +
//                                "head(standardData)\n" +
//                                "standard66 <- sample(1:nrow(standardData),as.integer(0.66*nrow(standardData)))\n" +
//                                "standardTrain66 <- standardData[standard66,]\n" +
//                                "standardTest66 <- standardData[-standard66,]\n" +
//                                "pred <- knn(standardTrain66[,-12],standardTest66[,-12],standardTrain66[,12],k=3)\n" +
//                                "confus <- table(Target = pred,Prediction = standardTest66[,12]))\n" +
//                                "accuracy <- (sum(diag(confus66))/sum(confus66))*100\n" +
//                                "capture.output({confus})");
        String[] output = rexp.asStrings();
        Arrays.asList(output);
        for (String ab: output){
            System.out.println(ab);
        }
    }
}
