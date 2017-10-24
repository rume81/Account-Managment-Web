/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HJournal.java
* --------------------
* Created on Feb 2, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 2, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class RJournal extends HObject {
    private long je_number;
    private long s_number;
    private int l_number; 
    private int yyyy; 
    private int mm; 
    private int dd;
    private String devcode;
    private String devname; 
    private String draccode;
    private String debit_subject_name;
    private String dracsubcode; 
    private String debit_details_name;
    private double dramount;
    private String craccode;
    private String credit_subject_name;
    private String cracsubcode; 
    private String credit_details_name;
    private double cramount;
    private String descode;
    private String desname;
    private String vendorname;
    private String transtime; 
    private int sundry; 
    private String date;
    public long getJe_number() {
        return je_number;
    }
    public void setJe_number(long je_number) {
        this.je_number = je_number;
    }
    public long getS_number() {
        return s_number;
    }
    public void setS_number(long s_number) {
        this.s_number = s_number;
    }
    public int getL_number() {
        return l_number;
    }
    public void setL_number(int l_number) {
        this.l_number = l_number;
    }
    public int getYyyy() {
        return yyyy;
    }
    public void setYyyy(int yyyy) {
        this.yyyy = yyyy;
    }
    public int getMm() {
        return mm;
    }
    public void setMm(int mm) {
        this.mm = mm;
    }
    public int getDd() {
        return dd;
    }
    public void setDd(int dd) {
        this.dd = dd;
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
    public String getDraccode() {
        return draccode;
    }
    public void setDraccode(String draccode) {
        this.draccode = draccode;
    }
    public String getDebit_subject_name() {
        return debit_subject_name;
    }
    public void setDebit_subject_name(String debit_subject_name) {
        this.debit_subject_name = debit_subject_name;
    }
    public String getDracsubcode() {
        return dracsubcode;
    }
    public void setDracsubcode(String dracsubcode) {
        this.dracsubcode = dracsubcode;
    }
    public String getDebit_details_name() {
        return debit_details_name;
    }
    public void setDebit_details_name(String debit_details_name) {
        this.debit_details_name = debit_details_name;
    }
    public double getDramount() {
        return dramount;
    }
    public void setDramount(double dramount) {
        this.dramount = dramount;
    }
    public String getCraccode() {
        return craccode;
    }
    public void setCraccode(String craccode) {
        this.craccode = craccode;
    }
    public String getCredit_subject_name() {
        return credit_subject_name;
    }
    public void setCredit_subject_name(String credit_subject_name) {
        this.credit_subject_name = credit_subject_name;
    }
    public String getCracsubcode() {
        return cracsubcode;
    }
    public void setCracsubcode(String cracsubcode) {
        this.cracsubcode = cracsubcode;
    }
    public String getCredit_details_name() {
        return credit_details_name;
    }
    public void setCredit_details_name(String credit_details_name) {
        this.credit_details_name = credit_details_name;
    }
    public double getCramount() {
        return cramount;
    }
    public void setCramount(double cramount) {
        this.cramount = cramount;
    }
    public String getDescode() {
        return descode;
    }
    public void setDescode(String descode) {
        this.descode = descode;
    }
    public String getDesname() {
        return desname;
    }
    public void setDesname(String desname) {
        this.desname = desname;
    }
    public String getVendorname() {
        return vendorname;
    }
    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }
    public String getTranstime() {
        return transtime;
    }
    public void setTranstime(String transtime) {
        this.transtime = transtime;
    }
    public int getSundry() {
        return sundry;
    }
    public void setSundry(int sundry) {
        this.sundry = sundry;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }    
}
