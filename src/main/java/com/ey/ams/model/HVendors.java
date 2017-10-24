/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HVendors.java
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

public class HVendors extends HObject {
    private String vendorcode;
    private String vendorname;
    
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
