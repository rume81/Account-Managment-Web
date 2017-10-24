/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HBaseData.java
* --------------------
* Created on Feb 16, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 16, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HBaseData extends HObject {
    private String cname;
    private int yeyyyy; 
    private int yemm;
    private String base_dir;
    private String data_dir;
    
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public int getYeyyyy() {
        return yeyyyy;
    }
    public void setYeyyyy(int yeyyyy) {
        this.yeyyyy = yeyyyy;
    }
    public int getYemm() {
        return yemm;
    }
    public void setYemm(int yemm) {
        this.yemm = yemm;
    }
    public String getBase_dir() {
        return base_dir;
    }
    public void setBase_dir(String base_dir) {
        this.base_dir = base_dir;
    }
    public String getData_dir() {
        return data_dir;
    }
    public void setData_dir(String data_dir) {
        this.data_dir = data_dir;
    }

}
