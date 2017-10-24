/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020; by EY.
*
* --------------------
* HTrailBalanceTemp.java
* --------------------
* Created on Mar 2; 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 2; 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

import com.ey.ams.model.HObject;

public class HTrailBalanceTemp extends HObject {
    private String keycode; 
    private String devcode; 
    private String devname; 
    private String accode; 
    private String acname; 
    private String acsubcode; 
    private String acsubname; 
    private Double budget; 
    private Double balance_before_provision; 
    private Double debit_amount; 
    private Double credit_amount; 
    private Double balance_amount; 
    private String subtotal_key; 
    private String subtotal_content;
    private String vendorcode;
    private String vendorname;
    
    public String getKeycode() {
        return keycode;
    }
    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }
    public String getDevcode() {
        return devcode;
    }
    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }
    public String getDevname() {
        return devname;
    }
    public void setDevname(String devname) {
        this.devname = devname;
    }
    public String getAccode() {
        return accode;
    }
    public void setAccode(String accode) {
        this.accode = accode;
    }
    public String getAcname() {
        return acname;
    }
    public void setAcname(String acname) {
        this.acname = acname;
    }
    public String getAcsubcode() {
        return acsubcode;
    }
    public void setAcsubcode(String acsubcode) {
        this.acsubcode = acsubcode;
    }
    public String getAcsubname() {
        return acsubname;
    }
    public void setAcsubname(String acsubname) {
        this.acsubname = acsubname;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    public Double getBalance_before_provision() {
        return balance_before_provision;
    }
    public void setBalance_before_provision(Double balance_before_provision) {
        this.balance_before_provision = balance_before_provision;
    }
    public Double getDebit_amount() {
        return debit_amount;
    }
    public void setDebit_amount(Double debit_amount) {
        this.debit_amount = debit_amount;
    }
    public Double getCredit_amount() {
        return credit_amount;
    }
    public void setCredit_amount(Double credit_amount) {
        this.credit_amount = credit_amount;
    }
    public Double getBalance_amount() {
        return balance_amount;
    }
    public void setBalance_amount(Double balance_amount) {
        this.balance_amount = balance_amount;
    }
    public String getSubtotal_key() {
        return subtotal_key;
    }
    public void setSubtotal_key(String subtotal_key) {
        this.subtotal_key = subtotal_key;
    }
    public String getSubtotal_content() {
        return subtotal_content;
    }
    public void setSubtotal_content(String subtotal_content) {
        this.subtotal_content = subtotal_content;
    }
    public String getVendorcode() {
        return vendorcode;
    }
    public void setVendorcode(String vendorcode) {
        this.vendorcode = vendorcode;
    }
    public String getVendorname() {
        return vendorname;
    }
    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }
    
   
}
