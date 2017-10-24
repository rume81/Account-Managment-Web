/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectTrialBalance.java
* --------------------
* Created on Mar 6, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 6, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HProjectTrialBalance extends HObject {
    private String name;
    private String subtotal_name;
    private String aggregate_key;
    private String acname;
    private String acsubname;
    private double debit_amount_sum;
    private double credit_amount_sum;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSubtotal_name() {
	return subtotal_name;
    }

    public void setSubtotal_name(String subtotal_name) {
	this.subtotal_name = subtotal_name;
    }

    public String getAggregate_key() {
	return aggregate_key;
    }

    public void setAggregate_key(String aggregate_key) {
	this.aggregate_key = aggregate_key;
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

    public double getDebit_amount_sum() {
	return debit_amount_sum;
    }

    public void setDebit_amount_sum(double debit_amount_sum) {
	this.debit_amount_sum = debit_amount_sum;
    }

    public double getCredit_amount_sum() {
	return credit_amount_sum;
    }

    public void setCredit_amount_sum(double credit_amount_sum) {
	this.credit_amount_sum = credit_amount_sum;
    }

}
