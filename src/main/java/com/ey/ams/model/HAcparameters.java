/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HAcparameters.java
* --------------------
* Created on Feb 6, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 6, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HAcparameters extends HObject {
    private String keycode;
    private String taxcr;
    
    public String getKeycode() {
        return keycode;
    }
    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }
    public String getTaxcr() {
        return taxcr;
    }
    public void setTaxcr(String taxcr) {
        this.taxcr = taxcr;
    }
}
