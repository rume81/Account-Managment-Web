/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HFscrtables.java
* --------------------
* Created on Jan 27, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Jan 27, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HFscrtables extends HObject {
    private String fscr;
    private String fscrname;
    private String dscr;
    private String repsort;
    
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
    public String getDscr() {
        return dscr;
    }
    public void setDscr(String dscr) {
        this.dscr = dscr;
    }
    public String getRepsort() {
        return repsort;
    }
    public void setRepsort(String repsort) {
        this.repsort = repsort;
    }
}
