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

public class HJournal extends HObject {
    private long s_number;
    private String abstract_name;
    private long je_number; 
    private int l_number; 
    private String drkey; 
    private String crkey; 
    private String devcode; 
    private String devname; 
    private String draccode; 
    private String dracname; 
    private String dracsubcode; 
    private String dracsubname; 
    private String craccode; 
    private String cracname; 
    private String cracsubcode; 
    private String cracsubname; 
    private int yyyy; 
    private int mm; 
    private int dd; 
    private double dramount; 
    private double cramount; 
    private double balance; 
    private String descode; 
    private String desname; 
    private String memo; 
    private String transtime; 
    private int sundry; 
    private String drctax; 
    private String crctax; 
    private double drctaxamount; 
    private double crctaxamount; 
    private String vendorcode; 
    private String vendorname; 
    private String otherdata; 
    private String receivecode; 
    private String prjcode; 
    private String prjname;
    private String name;
    
    public long getS_number() {
        return s_number;
    }
    public void setS_number(long s_number) {
        this.s_number = s_number;
    }
    public long getJe_number() {
        return je_number;
    }
    public void setJe_number(long je_number) {
        this.je_number = je_number;
    }
    public int getL_number() {
        return l_number;
    }
    public void setL_number(int l_number) {
        this.l_number = l_number;
    }
    public String getDrkey() {
        return drkey;
    }
    public void setDrkey(String drkey) {
        this.drkey = drkey;
    }
    public String getCrkey() {
        return crkey;
    }
    public void setCrkey(String crkey) {
        this.crkey = crkey;
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
    public String getDracname() {
        return dracname;
    }
    public void setDracname(String dracname) {
        this.dracname = dracname;
    }
    public String getDracsubcode() {
        return dracsubcode;
    }
    public void setDracsubcode(String dracsubcode) {
        this.dracsubcode = dracsubcode;
    }
    public String getDracsubname() {
        return dracsubname;
    }
    public void setDracsubname(String dracsubname) {
        this.dracsubname = dracsubname;
    }
    public String getCraccode() {
        return craccode;
    }
    public void setCraccode(String craccode) {
        this.craccode = craccode;
    }
    public String getCracname() {
        return cracname;
    }
    public void setCracname(String cracname) {
        this.cracname = cracname;
    }
    public String getCracsubcode() {
        return cracsubcode;
    }
    public void setCracsubcode(String cracsubcode) {
        this.cracsubcode = cracsubcode;
    }
    public String getCracsubname() {
        return cracsubname;
    }
    public void setCracsubname(String cracsubname) {
        this.cracsubname = cracsubname;
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
    public double getDramount() {
        return dramount;
    }
    public void setDramount(double dramount) {
        this.dramount = dramount;
    }
    public double getCramount() {
        return cramount;
    }
    public void setCramount(double cramount) {
        this.cramount = cramount;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
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
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
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
    public String getDrctax() {
        return drctax;
    }
    public void setDrctax(String drctax) {
        this.drctax = drctax;
    }
    public String getCrctax() {
        return crctax;
    }
    public void setCrctax(String crctax) {
        this.crctax = crctax;
    }
    public double getDrctaxamount() {
        return drctaxamount;
    }
    public void setDrctaxamount(double drctaxamount) {
        this.drctaxamount = drctaxamount;
    }
    public double getCrctaxamount() {
        return crctaxamount;
    }
    public void setCrctaxamount(double crctaxamount) {
        this.crctaxamount = crctaxamount;
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
    public String getOtherdata() {
        return otherdata;
    }
    public void setOtherdata(String otherdata) {
        this.otherdata = otherdata;
    }
    public String getReceivecode() {
        return receivecode;
    }
    public void setReceivecode(String receivecode) {
        this.receivecode = receivecode;
    }
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
  
    public String getAbstract_name() {
        return abstract_name;
    }
    public void setAbstract_name(String abstract_name) {
        this.abstract_name = abstract_name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
}
