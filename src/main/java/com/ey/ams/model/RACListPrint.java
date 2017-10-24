/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* RACListPrint.java
* --------------------
* Created on Feb 1, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 1, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class RACListPrint extends HObject {
    private String keycode;
    private String devcode;
    private String devname;
    private String accode;
    private String acname;
    private String acsubcode;
    private String acsubname;
    private String fscr;
    private String fscrname;
    
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
    public String getFscr() {
        return fscr;
    }
    public void setFscr(String fscr) {
        this.fscr = fscr;
    }
    public String getFscrname() {
        return fscrname;
    }
    public void setFscrname(String fscrname) {
        this.fscrname = fscrname;
    }
}
