/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* IAmsDAO.java
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
package com.ey.ams.dao.interfaces;

import java.util.List;

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
import com.ey.ams.model.HReportdataretention;
import com.ey.ams.model.HReportparameters;
import com.ey.ams.model.HSubledgerSubreport;
import com.ey.ams.model.HTrailBalanceTemp;
import com.ey.ams.model.HTrialBalanceSubtotalMaster;
import com.ey.ams.model.HUser;
import com.ey.ams.model.HVendors;
import com.ey.ams.model.RACListPrint;
import com.ey.ams.model.RCashCheck;
import com.ey.ams.model.RJournal;
import com.ey.ams.model.RLedger;
import com.ey.ams.model.RTransferSlip;
import com.ey.ams.model.RTransferSlipSub;

public interface IAmsDAO {
    
    public HUser getEmployeeByPassword(boolean isDeleted, String userName, String pass);
    
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
    
    public List<RACListPrint> getActableReportData(String devcode);
    
    public List<HJournal> journalCopy(String devcode);
    
    public List<HDescription> descriptionCode(String devcode);
    
    public List<HProjectcode> projectCode();
    
    public List<HVendors> vendorCode();
    
    public HDevtables BumonSansho(String devCode);
    
    public List<HDevcodeselect> bumonSelect(String devcode);
    
    public List<HAcparameters> getDebitTax();
    
    public HDevcodeselect getDetails(String key);
    
    public HVendors TORI_SANSHO(String selVendor);
    
    public HProjectcode PRJCODE(String code);
    
    public HDescription descriptionDetails(String selProj);
    
    public boolean deletejewrk();
    
    public boolean SaveJournal(List<HJournal> journal);
    
    public boolean deleteJournalData(long jno,String devcode);
    
    public int judgment();
    
    public boolean dropView(String viewName);
    
    public boolean createLastSlipNoView(String devcode,int mon,int query);
    
    public List<HJournal> getLastSlipNo();
    
    public boolean createLastJournalNumberView();
    
    public int getLastJournalNo();
    
    public List<HJournal> getAmountCheck(String type);
    
    public double getDrCrDifference();
    
    public List<HJournal> getBlankAccodeCheck(String type);
    
    public List<HJournal> getBlankDrCrAmountCheck();
    
    public List<HJournal> getJewrk();
    
    public boolean updateJournalNumberNew(long LAST_SIWAKE_NO,long LAST_DEN_NO,String BUMON,String BUMON_N,long YY,long MM,long DD);
    
    public boolean updateJournalNumberUpdate(String BUMON,String BUMON_N,long YY,long MM,long DD,long j_no,long s_no);
    
    public boolean updateJournalLnumber(int GYO,HJournal obj);
    
    public boolean updateJournaldrkeycrkey();
    
    public boolean InsertJournalData(int SHOKUCHI);
    
    public boolean InsertJewrk_bufData(int SHOKUCHI);
    
    public boolean createViewJeSearchResult(long LAST_SIWAKE_NO);
    
    public boolean createViewJeSearchResult(String BUMON);
    
    public boolean deleteFromTransferSlip();
    
    public List<HJournal> getJE_search_result(int sq);
    
    public List<HJournal> getJE_search_result();
    
    public boolean InsertTransferSlip(HJournal jor);
    
    public boolean FURIDEN_CLEAR();
    
    public boolean update_jewrk();
    
    public List<RTransferSlip> getTransferSlip();
    
    public List<RTransferSlipSub> getTransferSlipSub(String devCode);
    
    public HBaseData getBaseData();
    
    public Boolean createViewJournalTable(String BUMON, long YMD_FROM, long YMD_TO, long SIWAKE_FROM, long SIWAKE_TO,int sq);
    
    public boolean getReportparameters();
    
    public HReportparameters getReportparametersData();
    
    public boolean updateReportparameters(String param,int sq);
    
    public List<RJournal> getJournalReport(int sq, String BUMON, String YMD_FROM, String YMD_TO, String SIWAKE_FROM, String SIWAKE_TO);
    
    public boolean createView(String name,String sql);
    
    public int getJE_no();
    
    public boolean delete_jewrk_update();
    
    public boolean insert_jewrk_update(long je_no);
    
    public boolean SIWAKE_TO_FURIDEN(long SHORI);
    
    public List<HBaseData> getBaseDatas();
    
    public List<HReportdataretention> getZS_Amt(String BUMON);
    
    public int TB_Q_SET(String BUMON, long YMD_FROM, long YMD_TO, long KISHU);
    
    public void TORI_SIWAKE_T();
    
    public int TB_Q_SET3(String BUMON, long YMD_FROM, long YMD_TO, long KISHU);
    
    public boolean deleteTrialBalanceTemp();
    
    public boolean insertTrialBalanceTemp();
    
    public boolean insertTrialBalanceTemp(String BUMON, String ZS_AC,String ZS_AC_NM,double ZS_Amt);
    
    public List<HTrialBalanceSubtotalMaster> getTrialBalanceSubtotalMaster();
    
    public boolean updateTrialBalanceTemp(HTrialBalanceSubtotalMaster rst);
    
    public List<HTrailBalanceTemp> getTrailBalanceTemp();
    
    public List<HTrailBalanceTemp> getCustomerTrialBalance();
    
    public List<HTrailBalanceTemp> getTrialbalancesubjectsby();
    
    public boolean deleteProjectBudget();
    
    public boolean insertProjectBudget(long YMD_FROM,long YMD_TO,int sql);
    
    public List<HProjectBudget> getProjectBudget();
    
    public void QueryBudget(StringBuffer strSql);
    
    public List<HProjectTrialBalance> getProjectTrialBalance();
    
    /* Opening Balance Maintenance  Ole Ul Islam */
	
	public List<HActable> loadOpeningBalance();
    
    public boolean updatebgbalance(HActable obj);
    
    public List<HDescription> loadDescription();
    
    public List<HDevcodeselect> devSelect();
    
    public List<HCtc> loadCtc();
    
    public boolean insertDescription(HDescription obj);
    
    public boolean updateDescription(HDescription obj);
    
    public boolean deleteDescription(HDescription obj);
    
    public HDevtables devName(String devname);
    
    public HDevcodeselect debitDetails(String key);
    
    public List<HActable> acFrom();
    
    public List<HDevcodeselect> devCodeSelect();
    
    public boolean cash_check_table();

    
    public boolean ledger_tran_clear();
    
    public boolean SAI_SEL_not_null();
    
    public boolean ACSEL_null();
    
    public boolean KAMOKU_JOKEN(String BUMON);
    
    public boolean SAI_SEL_not_null2(String BUMON, String SAI_SEL);
    
    public boolean ACSEL_null2(String BUMON, String AC_FROM, String AC_TO);
    
    public boolean KAMOKU_JOKEN2(String BUMON);
    
    public boolean SAI_SEL_not_null3(String BUMON, String SAI_SEL, long YMD_FROM, long YMD_TO);
    
    public boolean ACSEL_null3(String BUMON, String SAI_SEL, String AC_TO, String AC_FROM, long YMD_TO);
    
    public boolean KAMOKU_JOKEN3(String BUMON, long YMD_FROM, long YMD_TO);
    
    public boolean SAI_SEL_not_null4(String BUMON, String SAI_SEL, long YMD_FROM, long YMD_TO);
    
    public boolean ACSEL_null4(String BUMON, String AC_TO, String AC_FROM, long YMD_FROM, long YMD_TO);
    
    public boolean KAMOKU_JOKEN4(String BUMON, long YMD_FROM, long YMD_TO);
    
    public boolean insertLedger();
    
    public List<HLedger> getLedger();
    
    public boolean updateLedger(double ZANDAKA_TRAN, HLedger ldg);
    
    public boolean updateLedger2(HLedger ldg);
    
    public boolean updateReportparametersLedger(String param,int sq);
    
    public HReportparameters selectReportParameters();
    
    //GL_Q_CON2 function
    
    public boolean ledgerTranDepartmentCauses(String BUMON, String AC_FROM, String AC_TO);
    
/*    public boolean insertLedgerTranKAMOKU_JOKEN(String KAMOKU_JOKEN, String ACSEL_2[], String BUMON);
    
    public boolean insertLedgerTran1(String BUMON, String AC_FROM, String AC_TO);
    
    public boolean insertLedgerTranKAMOKU_JOKEN1(String KAMOKU_JOKEN, String ACSEL_2[], String BUMON);*/
    
    public boolean updateJournalPrjCode();
    
   /* public boolean insertLedgerTran2(String BUMON, String AC_FROM, String AC_TO, long YMD_FROM, long YMD_TO, String cmbPrjCode);
    
    public boolean insertLedgerTranKAMOKU_JOKEN2(String KAMOKU_JOKEN, String ACSEL_2[], String BUMON, long YMD_FROM, long YMD_TO, String cmbPrjCode);
    
    public boolean insertLedgerTran3(String BUMON, String AC_FROM, String AC_TO, long YMD_FROM, long YMD_TO, String cmbPrjCode);
    
    public boolean insertLedgerTranKAMOKU_JOKEN3(String KAMOKU_JOKEN, String ACSEL_2[], String BUMON, long YMD_FROM, long YMD_TO, String cmbPrjCode);*/
    
    public boolean TableDefs(StringBuffer sql, String TableName);
    
    public String getLedgerWithoutDetail();
    
    public boolean QueryDefsUpdate(String sql);
    
    public boolean QueryDefsInsert(String sql);
    
    public List<HLedger> getSubledger();
    
    public List<RLedger> getRSubledger();
    
    public List<HSubledgerSubreport> getSubledgerSubreport(String accode);
    
    public void QueryDefs(StringBuffer sql, String ViewName);
    
    public List<RCashCheck> getCashCheck(String devcode);
    
    public HVendors addVendor(HVendors vendor);
    
    public HProjectcode addProject(HProjectcode proj);
    
    public HDescription getDescription(String descode);
    
    public HDescription addDescription(HDescription desc);
    
    /* Ole Ul Islam */
}
