/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HTrialBalanceSubtotalMaster.java
* --------------------
* Created on Mar 2, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 2, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HTrialBalanceSubtotalMaster extends HObject {
    private String AC_FROM;
    private String AC_TO;
    private String NAIYO;
    private String KEY;
    
    public String getAC_FROM() {
        return AC_FROM;
    }
    public void setAC_FROM(String aC_FROM) {
        AC_FROM = aC_FROM;
    }
    public String getAC_TO() {
        return AC_TO;
    }
    public void setAC_TO(String aC_TO) {
        AC_TO = aC_TO;
    }
    public String getNAIYO() {
        return NAIYO;
    }
    public void setNAIYO(String nAIYO) {
        NAIYO = nAIYO;
    }
    public String getKEY() {
        return KEY;
    }
    public void setKEY(String kEY) {
        KEY = kEY;
    }
}
