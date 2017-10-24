/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* RTransferSlip.java
* --------------------
* Created on Feb 15, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 15, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class RTransferSlip extends HObject {
    private String devcode;
    private String devname;
    private long je_number;
    private long s_number;
    private int yyyy;
    private int mm;
    private int dd;
    private String abstract_name;
    
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
    public String getAbstract_name() {
        return abstract_name;
    }
    public void setAbstract_name(String abstract_name) {
        this.abstract_name = abstract_name;
    }
    
    
}
