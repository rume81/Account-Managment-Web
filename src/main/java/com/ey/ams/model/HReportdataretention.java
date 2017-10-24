/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HReportdataretention.java
* --------------------
* Created on Mar 1, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 1, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HReportdataretention extends HObject {
    private String devcode;
    private int monthly;
    private double carryoverbalance_of_paymentsbalance;
    private double carriedforwardnetasset;
    private double specifiednetassetsopeningbalance;
    
    public String getDevcode() {
        return devcode;
    }
    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }
    public int getMonthly() {
        return monthly;
    }
    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }
    public double getCarryoverbalance_of_paymentsbalance() {
        return carryoverbalance_of_paymentsbalance;
    }
    public void setCarryoverbalance_of_paymentsbalance(double carryoverbalance_of_paymentsbalance) {
        this.carryoverbalance_of_paymentsbalance = carryoverbalance_of_paymentsbalance;
    }
    public double getCarriedforwardnetasset() {
        return carriedforwardnetasset;
    }
    public void setCarriedforwardnetasset(double carriedforwardnetasset) {
        this.carriedforwardnetasset = carriedforwardnetasset;
    }
    public double getSpecifiednetassetsopeningbalance() {
        return specifiednetassetsopeningbalance;
    }
    public void setSpecifiednetassetsopeningbalance(double specifiednetassetsopeningbalance) {
        this.specifiednetassetsopeningbalance = specifiednetassetsopeningbalance;
    }
}
