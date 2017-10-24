/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectBudget.java
* --------------------
* Created on Mar 3, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 3, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HProjectBudget extends HObject {
    private String prjcode; 
    private String prjname;
    private String aggregatekey;
    private String acname;
    private String acsubname;
    private double debitamount;
    private double creditamount;
    
    public String getPrjcode() {
        return prjcode;
    }
    public void setPrjcode(String prjcode) {
        this.prjcode = prjcode;
    }
    public String getPrjname() {
        return prjname;
    }
    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }
    public String getAggregatekey() {
        return aggregatekey;
    }
    public void setAggregatekey(String aggregatekey) {
        this.aggregatekey = aggregatekey;
    }
    public String getAcname() {
        return acname;
    }
    public void setAcname(String acname) {
        this.acname = acname;
    }
    public String getAcsubname() {
        return acsubname;
    }
    public void setAcsubname(String acsubname) {
        this.acsubname = acsubname;
    }
    public double getDebitamount() {
        return debitamount;
    }
    public void setDebitamount(double debitamount) {
        this.debitamount = debitamount;
    }
    public double getCreditamount() {
        return creditamount;
    }
    public void setCreditamount(double creditamount) {
        this.creditamount = creditamount;
    }
}
