/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HDevtables.java
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

public class HDevtables extends HObject {
    private String devcode;
    private String devname;
    
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
    
}
