/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectcode.java
* --------------------
* Created on Feb 3, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 3, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HProjectcode extends HObject {
    private String prjcode;
    private String prjname;
    
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
    
}
