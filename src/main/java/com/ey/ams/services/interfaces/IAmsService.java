/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* IAmsService.java
* --------------------
* Created on Jan 26, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Jan 26, 2017: Original version (ALA)
*
*/
package com.ey.ams.services.interfaces;

import java.util.List;
import java.util.Map;

import com.ey.ams.model.HAcparameters;
import com.ey.ams.model.HActable;
import com.ey.ams.model.HBaseData;
import com.ey.ams.model.HCtc;
import com.ey.ams.model.HDescription;
import com.ey.ams.model.HDevcodeselect;
import com.ey.ams.model.HDevtables;
import com.ey.ams.model.HFscrtables;
import com.ey.ams.model.HJournal;
import com.ey.ams.model.HLedger;
import com.ey.ams.model.HProjectBudget;
import com.ey.ams.model.HProjectTrialBalance;
import com.ey.ams.model.HProjectcode;
import com.ey.ams.model.HReportparameters;
import com.ey.ams.model.HSubledgerSubreport;
import com.ey.ams.model.HTrailBalanceTemp;
import com.ey.ams.model.HUser;
import com.ey.ams.model.HVendors;
import com.ey.ams.model.RACListPrint;
import com.ey.ams.model.RCashCheck;
import com.ey.ams.model.RJournal;
import com.ey.ams.model.RLedger;
import com.ey.ams.model.RTransferSlip;
import com.ey.ams.model.RTransferSlipSub;

public interface IAmsService {
    
    public HUser getUserValidation(HUser user);
    
    public List<HActable> loadAccountRegister();
    
    public List<HFscrtables> getFSCodeRange();
    
    public boolean deleteAccountRow(String ids);
    
    public boolean registerAccount(HActable obj);
    
    public boolean updateAccount(HActable obj);
    
    public boolean deleteAllFromActables();
    
    public boolean moveFromActablewkToActables();
    
    public boolean updateActable();
    
    public void acTablemntFormOpen();
    
    public boolean veryfyKeyCode(String keycode);
    
    public List<HDevtables> getDevCode();
    
    public List<HDevtables> getDevCodeById(String devcode);
    
    public List<RCashCheck> getCashCheck(String devcode);
    
    public List<RACListPrint> getActableReportData(String devcode);
    
    public List<HJournal> journalCopy(String devcode);
    
    public List<HDescription> descriptionCode(String devcode);
    
    public List<HProjectcode> projectCode();
    
    public List<HVendors> vendorCode();
    
    public HDevtables BumonSansho(String devCode);
    
    public List<HDevcodeselect> bumonSelect(String devcode);
    
    public List<HAcparameters> getDebitTax();
    
    public HDevcodeselect getDetails(String key);
    
    public HVendors TORI_SANSHO(String selVendor, int SHORI);
    
    public HProjectcode PRJCODE(String code);
    
    public HDescription descriptionDetails(String selProj);
    
    public boolean deletejewrk();
    
    public boolean SaveJournal(List<HJournal> journal);
    
    public String FURIDEN_TO_SIWAKE (int handan1,int FURIDEN_MODE,HJournal j);
    
    public List<RTransferSlip> getTransferSlip();
    
    public List<RTransferSlipSub> getTransferSlipSub(String devCode);
    
    public String journalCreate(Map param);
    
    public List<RJournal> getJournalReport(int sq, String BUMON, String YMD_FROM, String YMD_TO, String SIWAKE_FROM, String SIWAKE_TO);
    
    public String FURIDEN_KENSAKU2(int SHORI,Map param);
    
    public List<HJournal> getJE_search_result(int sq);
    
    public int getJE_no();
    
    public boolean delete_jewrk_update();
    
    public boolean insert_jewrk_update(long je_no);
    
    public String TB_Q_CONTROL(int SHORI,Map param);
    
    public HReportparameters getReportparametersData();
    
    public List<HTrailBalanceTemp> getTrailBalanceTemp();
    
    public List<HTrailBalanceTemp> getCustomerTrialBalance();
    
    public List<HTrailBalanceTemp> getTrialbalancesubjectsby();
    
    public String projectExpenses(Map param);
    
    public List<HProjectBudget> getProjectBudget();
    
    public List<HProjectTrialBalance> getProjectTrialBalance();
    
    public String budget(Map param);
	
	/* Opening Balance Maintenance  Ole Ul Islam*/
    
    public List<HActable> loadOpeningBalance();
    
    public boolean updatebgbalance(HActable obj);
    
    public List<HDescription> loadDescription();
    
    public List<HDevcodeselect> devSelect();
    
    public List<HCtc> loadCtc();
    
    public String GENKIN_SUITO(Map param);
    
    public String GENKIN_CHECK(Map param);
    
    public boolean insertDescription(HDescription obj);
    
    public boolean updateDescription(HDescription obj);
    
    public boolean deleteDescription(HDescription obj);
    
    public HDevtables getDevname(String devname);
    
    public HDevcodeselect debitDetails(String key);
    
    public List<HActable> acFrom();
    
    public List<HDevcodeselect> devCodeSelect();
    
    public HReportparameters selectReportParameters();
    
    public List<HLedger> getLedger();
    
    public String GL_Q_CON2(Map param);
    
    public String GL_Q_CONTROL(Map param);
    
    public List<HLedger> getSubledger();
    
    public List<RLedger> getRSubledger();
    
    public List<HSubledgerSubreport> getSubledgerSubreport(String accode);
    
    public HVendors addVendor(HVendors vendor);
    
    public HProjectcode addProject(HProjectcode proj);
    
    public HDescription addDescription(HDescription desc);

    /* Ole Ul Islam */
}
