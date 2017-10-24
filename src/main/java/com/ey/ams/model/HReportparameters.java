/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HReportparameters.java
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

public class HReportparameters extends HObject {
    private String je_extraction_cond; 
    private String trial_balance_cond;
    private String ledger_extrantion_cond;
    private String abstrac_aggregate_cond;
    
    public String getJe_extraction_cond() {
        return je_extraction_cond;
    }
    public void setJe_extraction_cond(String je_extraction_cond) {
        this.je_extraction_cond = je_extraction_cond;
    }
    public String getTrial_balance_cond() {
        return trial_balance_cond;
    }
    public void setTrial_balance_cond(String trial_balance_cond) {
        this.trial_balance_cond = trial_balance_cond;
    }
    public String getLedger_extrantion_cond() {
        return ledger_extrantion_cond;
    }
    public void setLedger_extrantion_cond(String ledger_extrantion_cond) {
        this.ledger_extrantion_cond = ledger_extrantion_cond;
    }
    public String getAbstrac_aggregate_cond() {
        return abstrac_aggregate_cond;
    }
    public void setAbstrac_aggregate_cond(String abstrac_aggregate_cond) {
        this.abstrac_aggregate_cond = abstrac_aggregate_cond;
    }
    
    
}
