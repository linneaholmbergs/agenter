/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAS;

import java.io.Serializable;

/**
 *
 * @author linnea
 */
public class WhiteWine implements Serializable{
    
    private double fixedAcidity;
    private double volatileAcidity;
    private double citricAcid;
    private double residualSugar;
    private double chlorides;
    private double freeSulfurDioxide;
    private double totalSulfurDioxide;
    private double density;
    private double pH;
    private double sulphates;
    private double alcohol;
    private double quality;

    public WhiteWine(double fixedAcidity, double volatileAcidity, double citricAcid, double residualSugar, double chlorides, double freeSulfurDioxide, double totalSulfurDioxide, double density, double pH, double sulphates, double alcohol, double quality) {
        this.fixedAcidity = fixedAcidity;
        this.volatileAcidity = volatileAcidity;
        this.citricAcid = citricAcid;
        this.residualSugar = residualSugar;
        this.chlorides = chlorides;
        this.freeSulfurDioxide = freeSulfurDioxide;
        this.totalSulfurDioxide = totalSulfurDioxide;
        this.density = density;
        this.pH = pH;
        this.sulphates = sulphates;
        this.alcohol = alcohol;
        this.quality = quality;
    }

    public WhiteWine() {
    }
    
    

    public void setFixedAcidity(double fixedAcidity) {
        this.fixedAcidity = fixedAcidity;
    }

    public void setVolatileAcidity(double volatileAcidity) {
        this.volatileAcidity = volatileAcidity;
    }

    public void setCitricAcid(double citricAcid) {
        this.citricAcid = citricAcid;
    }

    public void setResidualSugar(double residualSugar) {
        this.residualSugar = residualSugar;
    }

    public void setChlorides(double chlorides) {
        this.chlorides = chlorides;
    }

    public void setFreeSulfurDioxide(double freeSulfurDioxide) {
        this.freeSulfurDioxide = freeSulfurDioxide;
    }

    public void setTotalSulfurDioxide(double totalSulfurDioxide) {
        this.totalSulfurDioxide = totalSulfurDioxide;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    public void setSulphates(double sulphates) {
        this.sulphates = sulphates;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getFixedAcidity() {
        return fixedAcidity;
    }

    public double getVolatileAcidity() {
        return volatileAcidity;
    }

    public double getCitricAcid() {
        return citricAcid;
    }

    public double getResidualSugar() {
        return residualSugar;
    }

    public double getChlorides() {
        return chlorides;
    }

    public double getFreeSulfurDioxide() {
        return freeSulfurDioxide;
    }

    public double getTotalSulfurDioxide() {
        return totalSulfurDioxide;
    }

    public double getDensity() {
        return density;
    }

    public double getpH() {
        return pH;
    }

    public double getSulphates() {
        return sulphates;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public double getQuality() {
        return quality;
    }
    
    @Override
    public String toString(){
        return Double.toString(fixedAcidity) + ";" +  Double.toString(volatileAcidity) + ";" + Double.toString(citricAcid) + ";" + Double.toString(residualSugar) + ";" + Double.toString(chlorides) + ";" + Double.toString(freeSulfurDioxide) + ";" + Double.toString(totalSulfurDioxide) + ";" + Double.toString(density) + ";" + Double.toString(pH) + ";" + Double.toString(sulphates) + ";" + Double.toString(alcohol) + ";" + Double.toString(quality);
    }
    
}
