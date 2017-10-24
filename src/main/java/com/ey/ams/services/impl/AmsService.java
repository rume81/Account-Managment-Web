/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* AmsService.java
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
package com.ey.ams.services.impl;

import com.ey.ams.services.interfaces.IAmsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.ams.dao.interfaces.IAmsDAO;
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

public class AmsService implements IAmsService {
	private final Logger logger = LoggerFactory.getLogger(AmsService.class);
	
    private IAmsDAO amsDao;
    
    public void setAmsDao(IAmsDAO amsDao) {
        this.amsDao = amsDao;
    }
    // ==================================================================Local_Function=================================================================
    public String Right(String str, int pos){
	String val = str;
	int len = val.length();
	int right_pos = len-(pos);
		 
	String right = val.substring(right_pos, len);
		 
	return right;
    }
    
    public String LeftB(String st, int len) {
	String res = "";
	for (int i = 1; i < st.length(); i++) {
		if (res.getBytes().length > len)
			break;
		res = st.substring(0, i + 1);
	}
	return res;
    }
    
    //To get the closing date
    public long YM_GET (long TUKI_DATA, long HI_DATA) {
    	long KESSAN_NEN = 0;
    	long KESSAN_TUKI = 0;
    	long NEN_DATA = 0;
    	
    	HBaseData base = amsDao.getBaseData();
    	
    	KESSAN_NEN = base.getYeyyyy();
    	KESSAN_TUKI = base.getYemm();
    	
    	if (TUKI_DATA> KESSAN_TUKI) {
    		NEN_DATA = KESSAN_NEN - 1;
    	}
    	if(TUKI_DATA <= KESSAN_TUKI) {
    		NEN_DATA = KESSAN_NEN;
    	}
    	return (NEN_DATA * 10000 + TUKI_DATA * 100 + HI_DATA);
    }
    
    public String Ignore(String str,String ch) {
	String res="";
	for(int i=0;i<str.length();i++) {
	    String partstring = str.substring(i, i+1);
	    if(!partstring.equals(ch)){
		res = res+partstring;
	    }
	}
	return res;
    }

    // ==================================================================Local_Function_End=============================================================
    @Override
    public HUser getUserValidation(HUser user) {
	return amsDao.getEmployeeByPassword(false, user.getUser_name(), user.getPassword());
    }

    @Override
    public List<HActable> loadAccountRegister() {
	return amsDao.loadAccountRegister();
    }

    @Override
    public List<HFscrtables> getFSCodeRange() {
	return amsDao.getFSCodeRange();
    }

    @Override
    public boolean deleteAccountRow(String ids) {
	return amsDao.deleteAccountRow(ids);	
    }

    @Override
    public boolean registerAccount(HActable obj) {
	return amsDao.registerAccount(obj);
    }

    @Override
    public boolean updateAccount(HActable obj) {
	return amsDao.updateAccount(obj);
    }
    
    @Override
    public boolean deleteAllFromActables() {
	return amsDao.deleteAllFromActables();
    }

    @Override
    public boolean moveFromActablewkToActables() {
	return amsDao.moveFromActablewkToActables();
    }

    @Override
    public boolean updateActable() {
	return amsDao.updateActable();
    }

    @Override
    public void acTablemntFormOpen() {
	amsDao.acTablemntFormOpen();
    }

    @Override
    public boolean veryfyKeyCode(String keycode) {
	return amsDao.veryfyKeyCode(keycode);
    }

    @Override
    public List<HDevtables> getDevCode() {
	return amsDao.getDevCode();
    }
    
    @Override
    public List<HDevtables> getDevCodeById(String devcode) {
	return amsDao.getDevCodeById(devcode);
    }
    
    @Override
    public List<RACListPrint> getActableReportData(String devcode) {
	return amsDao.getActableReportData(devcode);
    }
    
    @Override
    public List<HJournal> journalCopy(String devcode) {
	return amsDao.journalCopy(devcode);
    }
    
    @Override
    public List<HDescription> descriptionCode(String devcode) {
	return amsDao.descriptionCode(devcode);
    }
    
    @Override
    public List<HProjectcode> projectCode() {
	return amsDao.projectCode();
    }
    
    @Override
    public List<HVendors> vendorCode() {
	return amsDao.vendorCode();
    }

    @Override
    public HDevtables BumonSansho(String devCode) {
	return amsDao.BumonSansho(devCode);
    }
    
    @Override
    public List<HDevcodeselect> bumonSelect(String devcode) {
	return amsDao.bumonSelect(devcode);
    }
    
    @Override
    public List<HAcparameters> getDebitTax() {
	return amsDao.getDebitTax();
    }
    
    @Override
    public HDevcodeselect getDetails(String key) {
	return amsDao.getDetails(key);
    }
    
    @Override
    public HVendors TORI_SANSHO(String selVendor, int SHORI) {
	Integer dum;
	String GYO;
	HVendors vendor = null;
	String shori = String.valueOf(SHORI);
	
	if(shori.substring(1, 2).equals("0")) {
		GYO = Right(shori,1);
	} else {
		GYO = Right(shori,2);
	}
	
	if(shori.substring(0,1).equals("1")) {
	    if(!selVendor.equals("")){
		vendor = amsDao.TORI_SANSHO(selVendor);
	    }
	}
	return vendor;
    }
    

    @Override
    public HProjectcode PRJCODE(String code) {
	if(code.equals(""))
	    return null;
	return amsDao.PRJCODE(code);
    }
    
    @Override
    public HDescription descriptionDetails(String selProj) {
	return amsDao.descriptionDetails(selProj);
    }
    
    @Override
    public boolean deletejewrk() {
	return amsDao.deletejewrk();
    }
    
    @Override
    public boolean SaveJournal(List<HJournal> journal) {
	return amsDao.SaveJournal(journal);
    }
    
    @Override
    public String FURIDEN_TO_SIWAKE (int handan1,int FURIDEN_MODE,HJournal j){
	//To post the contents of the transfer slip to the journal table.
	//Database processing related
	//Start Variables
	String BUMON="";
	String BUMON_N="";
	long YY,MM,DD;
	int SHOKUCHI; 
	int SS;
	long LAST_DEN_NO=0;
	long LAST_SIWAKE_NO=0;
	int GYO;
	long CUR_JE_NO=0;
	//Data in front of the slip
	int MAEBAN;
	int MAENEN;
	int MAETUKI;
	int MAEHI;
	String MAEBUMONCODE;
	String MAEBUMONNAME;
	long NEXT_NO;
	//End Variables
	
	//When in edit mode, delete the original data.
	if(FURIDEN_MODE == 2){
	    amsDao.deleteJournalData(j.getJe_number(),j.getDevcode());
	} 
			
	//Retention of non-consolidated data
	BUMON = j.getDevcode();//String.valueOf(cmbDebCode.getSelectedItem());
	BUMON_N = j.getDevname();
	YY = j.getYyyy();
	MM = j.getMm();
	DD = j.getDd();
	CUR_JE_NO=j.getJe_number();
	
	//Judgment of composite journal
	SHOKUCHI = amsDao.judgment();
	String msg="";
	
	//Judgment of composite journal
	SS = 1; //default is a simple serial number processing
	//If F! Debit item code 1 = "1110" Or F! Credit courses code 1 = "1110" Or F! Debit item code 2 = "1110" Or F! Credit courses code 2 = "1110" Or F! Debit subjects code 3 = "1110" = Or F! credit courses code 3 "1110" Or F! debit item code 4 = "1110" Or F! credit courses code 4 = "1110" Or F! debit item code 5 = "1110" Or F! credit courses code 5 = "1110" Or F! debit item code 6 = "1110" Or F! credit courses code 6 = "1110" Or F! debit item code 7 = "1110" Or F! credit course code 7 = "1110" Or F! debit item code 8 = "1110" Or F! credit courses code 8 = "1110" Or F! debit item code 9 = "1110" Or F! credit courses code 9 = "1110" Or F! debit item code 10 = "1110" Or F! credit courses code 10 = "1110" Then
	//SS = 1
	//ElseIf (F! Debit item code 1> = "1120" And F! Debit item code 1 <= "1150") Or (F! Credit courses code 1> = "1120" And F! Credit courses code 1 <= " 1150 ") Or (F! debit item code 2> =" 1120 "And F! debit item code 2 <=" 1150 ") Or (F! credit courses code 2> =" 1120 "And F! credit courses code 2 < = "1150") Or (F! debit item code 3> = "1120" And F! debit item code 3 <= "1150") Or (F! credit courses code 3> = "1120" And F! credit course code 3 <= "1140") Or (F! debit item code 4> = "1120" And F! debit item code 4 <= "1150") Or (F! credit courses code 4> = "1120" And F! credit course code 4 <= "1150") Or (F! debit item code 5> = "1120" And F! debit item code 5 <= "1150") Or (F! credit courses code 5> = "1120" And F ! credit courses code 5 <= "1150") Then
	//SS = 2
	//ElseIf (F! Debit item code 6> = "1120" And F! Debit item code 6 <= "1150") Or (F! Credit courses code 6> = "1120" And F! Credit courses code 6 <= " 1150 ") Or (F! debit item code 7> =" 1120 "And F! debit item code 7 <=" 1150 ") Or (F! credit courses code 7> =" 1120 "And F! credit courses code 7 < = "1150") Or (F! debit item code 8> = "1120" And F! debit item code 8 <= "1150") Or (F! credit courses code 8> = "1120" And F! credit course code 8 <= "1140") Or (F! debit item code 9> = "1120" And F! debit item code 9 <= "1150") Or (F! credit courses code 9> = "1120" And F! credit course code 9 <= "1150") Or (F! debit item code 10> = "1120" And F! debit item code 10 <= "1150") Or (F! credit courses code 10> = "1120" And F ! credit courses code 10 <= "1150") Then
	//SS = 2
	//ElseIf F! Debit item code 1 = "1170" Or F! Credit courses code 1 = "1170" Or F! Debit item code 2 = "1170" Or F! Credit courses code 2 = "1170" Or F! Debit subjects code 3 = "1170" = Or F! credit courses code 3 "1170" Or F! debit item code 4 = "1170" Or F! credit courses code 4 = "1170" Or F! debit item code 5 = "1170" Or F! credit courses code 5 = "1170" Or F! debit item code 6 = "1170" Or F! credit courses code 6 = "1170" Or F! debit item code 7 = "1170" Or F! credit course code 7 = "1170" Or F! debit item code 8 = "1170" Or F! credit courses code 8 = "1170" Or F! debit item code 9 = "1170" Or F! credit courses code 9 = "1170" Or F! debit item code 10 = "1170" Or F! credit courses code 10 = "1170" Then
	//SS = 3
	//Else
	//SS = 4
	//End If
	//When the new mode, the acquisition of slip number
	
	if(FURIDEN_MODE == 1){
	    if(SS == 1){
		//In the case of cash
		amsDao.dropView("lastslipnumber");
		amsDao.createLastSlipNoView(j.getDevcode(),0,1);
		List<HJournal> lastSlipNo = amsDao.getLastSlipNo();
		LAST_DEN_NO = 0;
		if(lastSlipNo.size()>0){
		    LAST_DEN_NO = lastSlipNo.get(0).getS_number();
		}
	    }
	    else if(SS == 2){
		//In the case of cash
		amsDao.dropView("lastslipnumber");
		amsDao.createLastSlipNoView(j.getDevcode(),j.getMm(),2);
		List<HJournal> lastSlipNo = amsDao.getLastSlipNo();
		LAST_DEN_NO = 2000;
		if(lastSlipNo.size()>0){
		    LAST_DEN_NO = lastSlipNo.get(0).getS_number();
		}		   				
	    }
	    else if(SS == 3){
		//In the case of cash
		amsDao.dropView("lastslipnumber");
		amsDao.createLastSlipNoView(j.getDevcode(),j.getMm(),3);
		List<HJournal> lastSlipNo = amsDao.getLastSlipNo();
		LAST_DEN_NO = 5000;
		if(lastSlipNo.size()>0){
		    LAST_DEN_NO = lastSlipNo.get(0).getS_number();
		}
	    }
	    else if(SS == 4){
		//In the case of cash
		amsDao.dropView("lastslipnumber");
		amsDao.createLastSlipNoView(j.getDevcode(),j.getMm(),4);
		List<HJournal> lastSlipNo = amsDao.getLastSlipNo();
		LAST_DEN_NO = 7000;
		if(lastSlipNo.size()>0){
		    LAST_DEN_NO = lastSlipNo.get(0).getS_number();
		}	
	    }
	    
	    amsDao.dropView("lastjournalnumber");
	    amsDao.createLastJournalNumberView();
	    LAST_SIWAKE_NO = 0;
	    LAST_SIWAKE_NO = amsDao.getLastJournalNo();
	}
	
	//新規伝票の転記
	//F.Requery
	//0301追加
	List<HJournal> rsD=amsDao.getAmountCheck("DR");
	if(rsD.size()>0) {
	    msg = "借方金額が入力されていない行があります";
	    return msg;
	}
		
	List<HJournal> rsC=amsDao.getAmountCheck("CR");
	if(rsC.size()>0) {
	    msg = "貸方金額が入力されていない行があります";
	    return msg;
	}
				
	//--------------------
	double difference = amsDao.getDrCrDifference();
	if(difference!=0){
	    msg ="貸借金額が不一致です";
	    return msg;
	}
	
	List<HJournal> rsbD=amsDao.getBlankAccodeCheck("DR");
	if(rsbD.size()>0) {
	    msg = "金額が入っているのに科目が入力されていません";
	    return msg;
	}
	
	List<HJournal> rsbC=amsDao.getBlankAccodeCheck("CR");
	if(rsbC.size()>0) {
	    msg = "金額が入っているのに科目が入力されていません";
	    return msg;
	}
	
	List<HJournal> rsbAC=amsDao.getBlankDrCrAmountCheck();
	if(rsbAC.size()>0) {
	    msg = "貸借とも金額がゼロの行があります";
	    return msg;
	}
		
	if(j.getJe_number()==0 && FURIDEN_MODE == 2)
	{
	    msg = "仕訳番号が入っていません";
	    return msg;
	}
	
	if((j.getYyyy() == 0) || (j.getMm()==0) || (j.getDd()==0) /*|| (j.getYyyy()==null) || (j.getMm()==null) || (j.getDd()==null)*/)
	{
	    msg = "年月日が入っていません";
	    return msg;
	}
	//行番の設定
	GYO = 1;
	List<HJournal> journal = amsDao.getJewrk();
	
	//部門、仕訳番号、伝票番号、計上年月日、借方キー、貸方キーの設定
	if(FURIDEN_MODE == 1){
	    //新規伝票の時
    	    amsDao.updateJournalNumberNew(LAST_SIWAKE_NO,LAST_DEN_NO,BUMON,BUMON_N,YY,MM,DD);
	} else{
	    //修正の時（仕訳番号、伝票番号はそのまま）
	    amsDao.updateJournalNumberUpdate(BUMON, BUMON_N, YY, MM, DD,j.getJe_number(),j.getS_number());
	}
    
	for(HJournal jor:journal){
    	    amsDao.updateJournalLnumber(GYO,jor);
    	    GYO=GYO+1;
	}
    
	amsDao.updateJournaldrkeycrkey();
    
	//仕訳テーブルへ追加
	amsDao.InsertJournalData(SHOKUCHI);
	//仕訳登録履歴へ追加
	amsDao.InsertJewrk_bufData(SHOKUCHI);
	    
	if(FURIDEN_MODE == 1)
	{
    	    //新規伝票場合、次の伝票に項目を送る
	    msg = "伝票番号　" + (LAST_DEN_NO + 1) + "番で登録しました";
	    //伝票の印刷
	    amsDao.dropView("JE_search_result_no");
	    amsDao.createViewJeSearchResult(LAST_SIWAKE_NO);
	    
	    amsDao.dropView("JE_search_result");
	    amsDao.createViewJeSearchResult(BUMON);
	            
	    //Call FURI_REP_MAKE
	    FURI_REP_MAKE();
	    //DoCmd.OpenReport "transfer slip",,, "journal number =" & Format (LAST_SIWAKE_NO + 1)
	    //DoCmd.Close acReport, "transfer slip"

	    //Storage of data in front of the slip
	    MAENEN = j.getYyyy();
	    MAETUKI = j.getMm();
	    MAEHI = j.getDd();
	    MAEBUMONCODE = j.getDevcode();
	    MAEBUMONNAME = j.getDevname();
	    //Clear the current input data
	    if(handan1 == 1){
        	//Call FURIDEN_CLEAR
        	amsDao.FURIDEN_CLEAR();
	    } 
	    else{
		amsDao.update_jewrk();
	    }
     
	    //Ready for the next slip input
	    /*txtYear.setText(String.valueOf(MAENEN));
	    txtMonth.setText(String.valueOf(MAETUKI));
	    txtDay.setText(String.valueOf(MAEHI));
	    cmbDebCode.setSelectedItem(MAEBUMONCODE);
	    txtDevName.setText(MAEBUMONNAME);
	    txtJournalNumber.setText("");
	    amsDao.journalCopy(MAEBUMONCODE);*/
	}
    
	if(FURIDEN_MODE == 2){
	    amsDao.insert_jewrk_update(j.getJe_number());
	    
	    int je_no = amsDao.getJE_no();
	    if(je_no!=0){
		if(je_no>j.getJe_number()){
		    msg="updated";
		    amsDao.FURIDEN_CLEAR();
		    amsDao.SIWAKE_TO_FURIDEN(je_no);
		} else{
		    msg="次の伝票はありません。";
		    amsDao.FURIDEN_CLEAR();
		    amsDao.delete_jewrk_update();
		}		
	    } else{
		msg="次の伝票はありません。";
		amsDao.FURIDEN_CLEAR();
		amsDao.delete_jewrk_update();
	    }
	}
	return msg;
    }
      
    public void FURI_REP_MAKE() {
	// Transfer slip reporting
	// Previous journal numbers
	long MAE_BAN;
	// before the slip number
	long MAE_DEN;
	String TEKIYO = "";
	String MAE_BUMONCODE = "";
	String MAE_BUMONNAME;
	int MAE_NEN, MAE_TUKI, MAE_HI;

	amsDao.deleteFromTransferSlip();

	// TEKIYO = "";
	List<HJournal> journ = new ArrayList<HJournal>();
	
	List<HJournal> rs = amsDao.getJE_search_result();
	if (rs.size()>0) {
	    HJournal rss = rs.get(0);
		
	    String desname = rss.getDesname();
	    if (!desname.equals("")) {
		TEKIYO = desname + (char) 13 + (char) 10;
	    }

	    MAE_BUMONCODE = rss.getDevcode();
	    MAE_BUMONNAME = rss.getDevname();
	    MAE_NEN = rss.getYyyy();
	    MAE_TUKI = rss.getMm();
	    MAE_HI = rss.getDd();
	    MAE_BAN = rss.getJe_number();
	    MAE_DEN = rss.getS_number();
		
	    int index = rs.size();
	    for(int i=1;i<index;i++) {
		HJournal rst =rs.get(i);
		HJournal journal = new HJournal();
		long journal_num = rst.getJe_number();
		if (journal_num != MAE_BAN) {
		    journal.setDevcode(MAE_BUMONCODE);
		    journal.setDevname(MAE_BUMONNAME);
		    journal.setJe_number(MAE_BAN);
		    journal.setS_number(MAE_DEN);
		    journal.setYyyy(MAE_NEN);
		    journal.setMm(MAE_TUKI);
		    journal.setDd(MAE_HI);
		    if (TEKIYO.getBytes().length > 254) {
			TEKIYO = LeftB(TEKIYO, 254);
		    }
		    journal.setDesname(TEKIYO);
		    journ.add(journal);
		}

		desname = rss.getDesname();
		if (!desname.equals("")) {
		    TEKIYO = desname + (char) 13 + (char) 10;
		}

		MAE_BUMONCODE = rss.getDevcode();
		MAE_BUMONNAME = rss.getDevname();
		MAE_NEN = rss.getYyyy();
		MAE_TUKI = rss.getMm();
		MAE_HI = rss.getDd();
		MAE_BAN = rss.getJe_number();
		MAE_DEN = rss.getS_number();
	    }
	    
	    HJournal journal = new HJournal();
	    journal.setDevcode(MAE_BUMONCODE);
	    journal.setDevname(MAE_BUMONNAME);
	    journal.setJe_number(MAE_BAN);
	    journal.setS_number(MAE_DEN);
	    journal.setYyyy(MAE_NEN);
	    journal.setMm(MAE_TUKI);
	    journal.setDd(MAE_HI);
	    if (TEKIYO.getBytes().length > 254) {
		TEKIYO = LeftB(TEKIYO, 254);
	    }
	    journal.setDesname(TEKIYO);

	    journ.add(journal);
	}
		
	

	for (HJournal jor : journ) {
	    amsDao.InsertTransferSlip(jor);
	}

	/*JasperReportViewer nw = JasperReportViewer.getInstance("transferslipreport");
	nw.pack();
	if (nw.isVisible()) {
	} else {
		getDesktopPane().add(nw);
		nw.setVisible(true);
	}
	nw.setIconifiable(true);
	nw.setMaximizable(false);
	nw.setClosable(true);

	try {
		nw.setMaximum(true);
	} catch (PropertyVetoException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/

	// dispose();
    }
    
    @Override
    public List<RTransferSlip> getTransferSlip() {
	return amsDao.getTransferSlip();
    }
   
    @Override
    public List<RTransferSlipSub> getTransferSlipSub(String devCode) {
	return amsDao.getTransferSlipSub(devCode);
    }
    
    @Override
    public String journalCreate(Map param) {
	
	int NARABI;
	String BUMON = "";
	long NEN_FROM = 0;
	long TUKI_FROM;
	long HI_FROM = 0;
	long NEN_TO = 0;
	long TUKI_TO;
	long HI_TO;
	long KESSAN_NEN;
	long KESSAN_TUKI;
	long YMD_FROM = 0;
	long YMD_TO = 0;
	long SIWAKE_FROM;
	long SIWAKE_TO;
	String JOKEN = null;
	String SQL_TEMP;
				
	if(String.valueOf(param.get("cmbRearrangement")).equals("1")) {
		NARABI = 1;
	} else{
		NARABI = 2;
	}
		
	BUMON 		= String.valueOf(param.get("cmbDepartment"));
	TUKI_FROM 	= Long.parseLong(String.valueOf(param.get("txtMonth")));
	HI_FROM 	= Long.parseLong(String.valueOf(param.get("txtFromDay")));
	TUKI_TO 	= Long.parseLong(String.valueOf(param.get("txtMonth2")));
	HI_TO 		= Long.parseLong(String.valueOf(param.get("txtUntilday")));
	YMD_FROM 	= YM_GET(TUKI_FROM, HI_FROM);
	YMD_TO 		= YM_GET(TUKI_TO, HI_TO);
	NEN_FROM 	= (YMD_FROM / 10000);
	NEN_TO		= (YMD_TO / 10000);
	SIWAKE_FROM = Long.parseLong(String.valueOf(param.get("txtDocumentRangeFrom")));
	SIWAKE_TO 	= Long.parseLong(String.valueOf(param.get("txtDocumentRangeTo")));
	
	
	amsDao.dropView("journal_table");
		
	if(NARABI == 1) {
	    //Journal numerical order
	    amsDao.createViewJournalTable(BUMON, YMD_FROM, YMD_TO, SIWAKE_FROM, SIWAKE_TO,1);
	} else if(NARABI ==2) {
	    amsDao.createViewJournalTable(BUMON, YMD_FROM, YMD_TO, SIWAKE_FROM, SIWAKE_TO,2);
	}
	
	if(NARABI ==1) {
		JOKEN = "入力順";
	} else if(NARABI == 2) {
		JOKEN = "日付順";
	}
	
	boolean fo = amsDao.getReportparameters();
			
	String je_extraction_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
	
	if(fo) {
	    amsDao.updateReportparameters(je_extraction_cond,1);
	} else {
	    amsDao.updateReportparameters(je_extraction_cond,2);
	}	
	String result="-1";
	if (NARABI == 1) {
	    result = "rptJournal/"+BUMON+ "~" +YMD_FROM+ "~" +YMD_TO+ "~" +SIWAKE_FROM+ "~" + SIWAKE_TO;
	} else if (NARABI == 2) {
	    result = "rptJournal2/"+BUMON + "~" +YMD_FROM + "~" +YMD_TO +"~" +SIWAKE_FROM + "~" + SIWAKE_TO;
	}
	
	return result;
    }
    
    @Override
    public List<RJournal> getJournalReport(int sq, String BUMON, String YMD_FROM, String YMD_TO, String SIWAKE_FROM,
	    String SIWAKE_TO) {
	return amsDao.getJournalReport(sq, BUMON, YMD_FROM, YMD_TO, SIWAKE_FROM, SIWAKE_TO);
    }
    
    @Override
    public String FURIDEN_KENSAKU2(int SHORI,Map param) {
	
	String StrSQL;
	int dum;
	String SIWAKE_T;
	
	String BUMON = null;
	Long SIWAKE_FROM;
	Long SIWAKE_TO;
	Long NEN_FROM;

	Long TUKI_FROM;
	Long HI_FROM; 
	Long NEN_TO;
	Long TUKI_TO;
	Long HI_TO;
	Long START_YMD;
	Long END_YMD;
	Double KINGAKU_FROM;
	Double KINGAKU_TO;
	String[] ACSEL = new String[6];
	//ReDim ACSEL (6) As String 'elective array
	String TORISAKI;
	String TEKIYO;
	String JOKEN;
	Long SIWAKE_SHORI;
	String AC_JOKEN; 
	
	String msg="-1";
	
	if(String.valueOf(param.get("cmbSelectDepartment")).equals("")) {
		BUMON ="01";
	} else {
		BUMON = String.valueOf(param.get("cmbSelectDepartment"));
	}
	if(param.get("txtJournalFrom").equals("")) {
		SIWAKE_FROM =0L;
	} else {
		SIWAKE_FROM = Long.valueOf(String.valueOf(param.get("txtJournalFrom")));
	}
	if(param.get("txtJournalTo").equals("")) {
		SIWAKE_TO = 999999L;
	} else {
		SIWAKE_TO = Long.valueOf(String.valueOf(param.get("txtJournalTo")));
	}
	if(param.get("txtYearFrom").equals("")) {
		NEN_FROM = 1996L;
	} else {
		NEN_FROM = Long.valueOf(String.valueOf(param.get("txtYearFrom")));
	}
	
	if(param.get("txtMonthFrom").equals("")) {
		TUKI_FROM = 4L;
	} else {
		TUKI_FROM = Long.valueOf(String.valueOf(param.get("txtMonthFrom")));
	}
	if(param.get("txtDayFrom").equals("")) {
		HI_FROM = 1L;
	} else {
		HI_FROM = Long.valueOf(String.valueOf(param.get("txtDayFrom")));
	}
	if(param.get("txtYearTo").equals("")) {
		NEN_TO = 2099L;
	} else {
		NEN_TO = Long.valueOf(String.valueOf(param.get("txtYearTo")));
	}
	if(param.get("txtMonthTo").equals("")) {
		TUKI_TO = 3L;
	} else {
		TUKI_TO = Long.valueOf(String.valueOf(param.get("txtMonthTo")));
	}
	if(param.get("txtDayTo").equals("")) {
		HI_TO = 31L;
	} else {
		HI_TO = Long.valueOf(String.valueOf(param.get("txtDayTo")));
	}
	if(param.get("txtAmountRangeFrom").equals("")) {
		KINGAKU_FROM = 0D;
	} else {
		KINGAKU_FROM = Double.valueOf(Ignore(String.valueOf(param.get("txtAmountRangeFrom")),","));
	}
	if(param.get("txtAmountrangeTo").equals("")) {
		KINGAKU_TO = 0D;
	} else {
		KINGAKU_TO = Double.valueOf(Ignore(String.valueOf(param.get("txtAmountrangeTo")),","));
	}
	if(param.get("txtMatchDescription").equals("")) {
		TEKIYO = "";
	} else {
		TEKIYO = String.valueOf(param.get("txtMatchDescription"));
	}
	if(param.get("txtMatchVendor").equals("")) {
		TORISAKI = "";
	} else {
		TORISAKI = String.valueOf(param.get("txtMatchVendor"));
	}
	
	if(String.valueOf(param.get("cmbAccountSelect1")).equals("")) {
		ACSEL[0] ="";
	} else {
		ACSEL[0] = String.valueOf(param.get("cmbSelectDepartment")) + String.valueOf(param.get("cmbAccountSelect1"));
	}
	if(String.valueOf(param.get("cmbAccountSelect2")).equals("")) {
		ACSEL[1] ="";
	} else {
		ACSEL[1] = String.valueOf(param.get("cmbSelectDepartment")) + String.valueOf(param.get("cmbAccountSelect2"));
	}
	if(String.valueOf(param.get("cmbAccountSelect3")).equals("")) {
		ACSEL[2] ="";
	} else {
		ACSEL[2] = String.valueOf(param.get("cmbSelectDepartment")) + String.valueOf(param.get("cmbAccountSelect3"));
	}
	if(ACSEL[1].equals(String.valueOf(param.get("cmbSelectDepartment")))) {
		ACSEL[1] = ACSEL[0];
	}
	if(ACSEL[2].equals(String.valueOf(param.get("cmbSelectDepartment")))) {
		ACSEL[2] = ACSEL[0];
	}
	
	START_YMD = NEN_FROM * 10000 + TUKI_FROM * 100 + HI_FROM;
	END_YMD = NEN_TO * 10000 + TUKI_TO * 100 + HI_TO;
	
	if(SHORI == 1 || SHORI == 2) {
		SIWAKE_T = "journals";
	} else {
		SIWAKE_T = "jewrk_buf";
	}
	
	amsDao.dropView("je_search_result_no");
		
	if(ACSEL[0].length() <4 && ACSEL[1].length() <4 && ACSEL[2].length() <4) {
		AC_JOKEN = "";
	} else if (ACSEL[1].length() <4 && ACSEL[2].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "')";
	} else if (ACSEL[0].length() <4 && ACSEL[2].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[1] + "' OR crkey = '" + ACSEL[1] + "')";
	} else if(ACSEL[0].length() <4 && ACSEL[1].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[2] + "' OR crkey = '" + ACSEL[2] + "')";
	} else if(ACSEL[0].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[1] + "' OR crkey = '" + ACSEL[1] + "' OR drkey = '" + ACSEL[2] + "' OR crkey = ' "+ ACSEL[2] +"') ";
	} else if(ACSEL[1].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[2] + "' OR crkey= ' "+ ACSEL[2] +"') ";
	} else if(ACSEL[2].length() <4) {
		AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[1] + "' OR crkey = ' "+ ACSEL[1] +"') ";
	} else {
		AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[1] + "' OR crkey = '"+ ACSEL[1] +"' OR drkey = '"+ ACSEL[2] +"' OR crkey = '"+ ACSEL[2] +"') ";
	}
	
	if(TEKIYO =="" && TORISAKI == "") {
		StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND devcode = '"+ BUMON + "' " + AC_JOKEN + "GROUP BY je_number";
	} else if(TEKIYO != "" && TORISAKI =="") {
		StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND desname LIKE '%" + TEKIYO + "%' AND devcode ='" + BUMON + "' " + AC_JOKEN + "GROUP BY je_number";
	} else if(TEKIYO =="" && TORISAKI != "") {
		StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND vendorname LIKE '%"+ TORISAKI +"%' AND devcode ='"+ BUMON +"' "+ AC_JOKEN +" GROUP BY je_number";
	} else {
		StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND vendorname LIKE '%"+ TORISAKI +"%' AND desname LIKE '%"+ TEKIYO +"%' AND department code ='"+ BUMON +"' "+ AC_JOKEN +" GROUP BY je_number";
	}
	
	//System.out.println(StrSQL);
	amsDao.createView("je_search_result_no", StrSQL);
	
	amsDao.dropView("je_search_result");
	
	StrSQL = "SELECT " + SIWAKE_T +".je_number,"+ SIWAKE_T + ".s_number," + SIWAKE_T + ".l_number," + SIWAKE_T + ".devcode," + SIWAKE_T + ".devname, "+ SIWAKE_T +".draccode, "+ SIWAKE_T +".dracname, "+ SIWAKE_T +".dracsubcode, "+ SIWAKE_T +".dracsubname, "+ SIWAKE_T +".craccode, "+ SIWAKE_T +".cracname, "+ SIWAKE_T +".cracsubcode, "+ SIWAKE_T +".cracsubname, "+ SIWAKE_T +".yyyy, "+ SIWAKE_T +".mm, "+ SIWAKE_T + ".dd," + SIWAKE_T + ".dramount," + SIWAKE_T + ".cramount," + SIWAKE_T + ".balance," + SIWAKE_T + ".descode," + SIWAKE_T + ".desname, "+ SIWAKE_T +".transtime, "+ SIWAKE_T +".drctax, "+ SIWAKE_T +".crctax, "+ SIWAKE_T +".vendorcode, "+ SIWAKE_T +".vendorname, "+ SIWAKE_T +".otherdata, "+ SIWAKE_T +".prjcode, "+ SIWAKE_T +".prjname, ctc.name, ctc_1.name as n1";
	StrSQL = StrSQL + " FROM ((je_search_result_no LEFT JOIN " + SIWAKE_T + " ON je_search_result_no.je_number =" + SIWAKE_T + ".je_number) LEFT JOIN ctc ON journals.drctax = ctc.code) LEFT JOIN ctc AS ctc_1 ON journals.crctax = ctc_1.code WHERE ((( "+ SIWAKE_T +".devcode) = '"+ BUMON +"')) ORDER BY "+ SIWAKE_T +".s_number, "+ SIWAKE_T +".l_number; ";
	
	//System.out.println(StrSQL);
	amsDao.createView("je_search_result", StrSQL);
	
	amsDao.delete_jewrk_update();
	
	List<HJournal> journal_search = amsDao.getJE_search_result();
	if(SHORI ==1 || SHORI ==3) {
		boolean f = false;
		if(journal_search.size()>0) {
		    f= true;
		}
		
		if(!f){
			//JOptionPane.showMessageDialog(null, "該当伝票がありません");
		    msg = "該当伝票がありません";
		} else {
		    msg = "2";
		}
	} 
	else if(SHORI == 2) 
	{
		//Call FURI_REP_MAKE
		FURI_REP_MAKE();
	}
	
	return msg;
    }
    
    @Override
    public HReportparameters getReportparametersData() {
	return amsDao.getReportparametersData();
    }
    
    @Override
    public List<HTrailBalanceTemp> getTrailBalanceTemp() {
	return amsDao.getTrailBalanceTemp();
    }
    
    @Override
    public String TB_Q_CONTROL(int SHORI,Map param)
    {
	//2002/12/08 移行
	//Public Function TB_Q_CONTROL(SHORI=1)
	//残高試算表
	String BUMON; 
	long NEN_FROM;
	long TUKI_FROM;
	long HI_FROM;
	long NEN_TO;
	long TUKI_TO;
	long HI_TO;
	long KESSAN_NEN=0;
	long KESSAN_TUKI=0;
	long YMD_FROM;
	long YMD_TO;
	long KISHU;
	int dum;
	String ZS_AC="";
	String ZS_AC_NM="";
	double ZS_Amt=0d;
	String msg = "";
		    	    
	//フォームの初期値算定
	//NULL値のチェック
	if(String.valueOf(param.get("cmbDepartment")).equals("")) {
	    BUMON = "";
	} else {
	    //フォームからデータを取得
	    BUMON = String.valueOf(param.get("cmbDepartment")).split(" | ")[0];
	}
	if(String.valueOf(param.get("txtMonth")).equals("")) {
	    TUKI_FROM = 4;
	} else {
	    //フォームからデータを取得
	    TUKI_FROM = Long.parseLong(String.valueOf(param.get("txtMonth")));
	}
	if(String.valueOf(param.get("txtMonth2")).equals("")) {
	    TUKI_TO = 3;
	} else {
	    //フォームからデータを取得
	    TUKI_TO = Long.parseLong(String.valueOf(param.get("txtMonth2")));
	}
	if(String.valueOf(param.get("txtFromDay")).equals("")) {
	    HI_FROM = 1;
	} else {
	    //フォームからデータを取得
	    HI_FROM = Long.parseLong(String.valueOf(param.get("txtFromDay")));
	}
	if(String.valueOf(param.get("txtUntilday")).equals("")) {
	    HI_TO = 31;
	} else {
	    //フォームからデータを取得
	    HI_TO = Long.parseLong(String.valueOf(param.get("txtUntilday")));
	}
	    
	//フォームからデータを取得
	YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	YMD_TO = YM_GET(TUKI_TO, HI_TO);
	NEN_FROM = YMD_FROM / 10000;
	NEN_TO = YMD_TO / 10000;
	
	List<HBaseData> bases = amsDao.getBaseDatas();
	if ((SHORI==1) && (bases.size()>0)) {
	    HBaseData base = bases.get(0);
	    KESSAN_NEN = base.getYeyyyy();
	    KESSAN_TUKI = base.getYemm();
	} else {
	    HBaseData base = bases.get(0);
	    KESSAN_NEN = base.getYeyyyy();
	    KESSAN_TUKI = base.getYemm();
	}
	    
	KISHU = (KESSAN_NEN - 1) * 10000 + (KESSAN_TUKI + 1) * 100 + 1;
	    
	//初期値設定
	if(SHORI!=2){
	    ZS_AC = "3000";
	    ZS_AC_NM = "前期繰越正味財産額";
	    //Set rst = dbs.OpenRecordset("SELECT 部門コード, 月度, 繰越正味財産額,指定正味財産期首残高 FROM 報告書データ保持 WHERE (部門コード='" & BUMON & "');", dbOpenDynaset)
	   List<HReportdataretention> rp = amsDao.getZS_Amt(BUMON);
	   if(rp.size()>0){
	       double carryoverbalance_of_paymentsbalance = rp.get(0).getCarriedforwardnetasset();
	       double specifiednetassetsopeningbalance = rp.get(0).getSpecifiednetassetsopeningbalance();
	   					
	       ZS_Amt = (carryoverbalance_of_paymentsbalance + specifiednetassetsopeningbalance) * -1;
	   }
	    	   
	}
	    
	//残高試算表クエリーのパラメータセット
	if((SHORI == 1) || (SHORI==3)){
	    dum = amsDao.TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
	} else if(SHORI == 2){
	    //Call TORI_SIWAKE_T
	    amsDao.TORI_SIWAKE_T();
	    dum = amsDao.TB_Q_SET3(BUMON, YMD_FROM, YMD_TO, KISHU);
	}
	    
	//If OBJ_EXIST("試算表TEMP") = 2 Then
	//DoCmd.DeleteObject acTable, "試算表TEMP"
	//End If
	
	amsDao.deleteTrialBalanceTemp();
	
	amsDao.insertTrialBalanceTemp();
		    
	if((SHORI == 1) || (SHORI==3)){
	    //前期繰越正味財産額の作成
	    
	    boolean fo1 = amsDao.insertTrialBalanceTemp(BUMON,ZS_AC,ZS_AC_NM,ZS_Amt);
	}
	//小計行の処理
	List<HTrialBalanceSubtotalMaster> subtotal_master = amsDao.getTrialBalanceSubtotalMaster();
	    
	for(HTrialBalanceSubtotalMaster rst:subtotal_master)
	{
	    amsDao.updateTrialBalanceTemp(rst);    	
	}
	//reportparameters
	boolean fo = amsDao.getReportparameters();
		
	String je_trial_balance_cond = "抽出条件　部門" + BUMON + "" + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで";
	    
	if(fo){
	    if((SHORI==1) || (SHORI==3)){
		amsDao.updateReportparameters(je_trial_balance_cond,3);
	    }
	}else {
	    if(SHORI==1){
		amsDao.updateReportparameters(je_trial_balance_cond,4);
	    }
	}	   
		
	if(SHORI==1){
	    if(BUMON.equals("999")){
		//DoCmd.OpenReport "残高試算表", acPreview	
		//CallReport("rptTrialBalance",rparam);
		msg = "rptTrialBalance";
    	    } else {
    		//DoCmd.OpenReport "残高試算表全部門", acPreview
    		//CallReport("rptTrialBalanceAllDepartments",rparam);
    		msg = "rptTrialBalanceAllDepartments";
    	    }
	} else if(SHORI==2){
	    //DoCmd.OpenReport "取引先別残高試算表", acPreview
	    //CallReport("CustomerTrialBalance",rparam);
	    msg = "rptCustomerTrialBalance";
	} else if(SHORI==3){
	    //DoCmd.OpenReport "残高試算表科目別", acPreview 	
	    //CallReport("trialbalancesubjectsby",rparam);
	    msg = "rptTrialbalancesubjectsby";
	}
	
	return msg;
    }
        
    @Override
    public String projectExpenses(Map param) {
	String msg="";
	long YMD_FROM;
	long YMD_TO;

	long TUKI_FROM = Long.parseLong(String.valueOf(param.get("txtMonth")));
	long HI_FROM = Long.parseLong(String.valueOf(param.get("txtFromDay")));
	long TUKI_TO = Long.parseLong(String.valueOf(param.get("txtMonth2")));
	long HI_TO = Long.parseLong(String.valueOf(param.get("txtUntilday")));

	YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	YMD_TO = YM_GET(TUKI_TO, HI_TO);
	
	amsDao.deleteProjectBudget();
	
	amsDao.insertProjectBudget(YMD_FROM, YMD_TO,1);
	
	amsDao.insertProjectBudget(YMD_FROM, YMD_TO,2);
		
	//DoCmd.OpenReport "プロジェクト別収支", acViewPreview
	//CallReport("rptProjectBudget","");
	msg = "rptProjectBudget";
	
	return msg;
    }
    
    @Override
    public String budget(Map param) {
	String msg="";
	boolean fo = true;
	long YMD_FROM;
	long YMD_TO;

	long TUKI_FROM = Long.parseLong(String.valueOf(param.get("txtMonth")));
	long HI_FROM = Long.parseLong(String.valueOf(param.get("txtFromDay")));
	long TUKI_TO = Long.parseLong(String.valueOf(param.get("txtMonth2")));
	long HI_TO = Long.parseLong(String.valueOf(param.get("txtUntilday")));

	YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	YMD_TO = YM_GET(TUKI_TO, HI_TO);

	amsDao.deleteProjectBudget();

	amsDao.insertProjectBudget(YMD_FROM, YMD_TO,3);
	
	amsDao.insertProjectBudget(YMD_FROM, YMD_TO,4);
	
	//DoCmd.OpenReport "プロジェクト別収支2", acViewPreview
	//CallReport("rptProjectBudget2","");
	
	if(fo){
	    msg = "rptProjectBudget2";
	    return msg;
	} else{	
	    StringBuffer sql;
	    sql = new StringBuffer("INSERT INTO tb_work (aggregate_key, debit_amount, subtotal_key) SELECT journals.drkey, SUM(journals.dramount) AS debit_amount_sum, IF(LEFT(draccode,1) = '7',prjcode, NULL) AS formula1 FROM journals GROUP BY journals.drkey, IF(LEFT(draccode,1) ='7',prjcode,NULL);");
	    sql = new StringBuffer("INSERT INTO tb_work (aggregate_key, credit_amount, subtotal_key) SELECT journals.crkey, SUM(journals.cramount) AS credit_amount_sum, IF(LEFT(craccode,1) = '7',prjcode, NULL) AS formula1 FROM journals GROUP BY journals.crkey, IF(LEFT(craccode,1) ='7',prjcode,NULL);");
	    sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(subtotal_key < '200', '4001', IF(subtotal_key >= '200' AND subtotal_key < '300','4002', IF(subtotal_key>='300' AND subtotal_key <'400','4003', IF(subtotal_key = '410','4004', IF(subtotal_key = '510','4005','4006'))))) WHERE tb_work.subtotal_key<>NULL;");
	    sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(MID(aggregate_key,4,1)<='6','1000',IF(MID(aggregate_key,4,1)='7','4006','6000')) WHERE tb_work.subtotal_key IS NULL;");
	    sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = '6002' WHERE (LEFT (subtotal_name,1) =6 AND ( NOT MID(aggregate_key,4,4) ='8110' OR MID(aggregate_key,4,4)='8115'));");
	    sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(MID(aggregate_key,4,1)<='6','1000',IF(MID(aggregate_key,4,1)='7','4006','6000')) WHERE (tb_work.subtotal_key IS NULL AND MID(aggregate_key,4,4) ='8390');");
	    amsDao.QueryBudget(sql);
	
	    //DoCmd.OpenReport "PRJ試算表", acViewPreview
	    //CallReport("rptProjectTrialBalance","");
	    
	    msg = "rptProjectTrialBalance";
	    return msg;
	}
	
    }
    
    @Override
    public List<HJournal> getJE_search_result(int sq) {
	return amsDao.getJE_search_result(sq);
    }
    
    @Override
    public int getJE_no() {
	return amsDao.getJE_no();
    }
    
    @Override
    public boolean delete_jewrk_update() {
	return amsDao.delete_jewrk_update();
    }
    
    @Override
    public boolean insert_jewrk_update(long je_no) {
	return amsDao.insert_jewrk_update(je_no);
    }
    

    @Override
    public List<HTrailBalanceTemp> getCustomerTrialBalance() {
	return amsDao.getCustomerTrialBalance();
    }
    
    @Override
    public List<HTrailBalanceTemp> getTrialbalancesubjectsby() {
	return amsDao.getTrialbalancesubjectsby();
    }
    
    @Override
    public List<HProjectBudget> getProjectBudget() {
	return amsDao.getProjectBudget();
    }
    
    @Override
    public List<HProjectTrialBalance> getProjectTrialBalance(){
	return amsDao.getProjectTrialBalance();
    }
    
	/* Opening Balance Maintenance  Ole Ul Islam*/
    
    @Override
    public List<HActable> loadOpeningBalance() {
    	return amsDao.loadOpeningBalance();
    }
    
    @Override
    public boolean updatebgbalance(HActable obj) {
    	return amsDao.updatebgbalance(obj);
    }
    
    @Override
    public List<HDescription> loadDescription() {
    	return amsDao.loadDescription();
    }
    
    @Override
    public List<HDevcodeselect> devSelect() {
    	return amsDao.devSelect();
    }
    
    @Override
    public List<HCtc> loadCtc() {
    	return amsDao.loadCtc();
    }
    
    @Override
    public boolean insertDescription(HDescription obj) {
    	return amsDao.insertDescription(obj);
    }
    
    @Override
    public boolean updateDescription(HDescription obj) {
    	return amsDao.updateDescription(obj);
    }
    
    @Override
    public boolean deleteDescription(HDescription obj) {
    	return amsDao.deleteDescription(obj);
    }
    
    @Override
    public HDevtables getDevname(String devname) {
    	return amsDao.devName(devname);
    }
    
    @Override
    public HDevcodeselect debitDetails(String key) {
    	return amsDao.debitDetails(key);
    }
    
    @Override
    public List<HActable> acFrom() {
    	return amsDao.acFrom();
    }
    
    @Override
    public List<HDevcodeselect> devCodeSelect() {
    	return amsDao.devCodeSelect();
    }
    
    /* Cashier Appendix report*/
    @Override
    public String GENKIN_CHECK(Map param) {
    	
	String msg ="";
	String BUMON = null;
	long NEN_FROM;
	long TUKI_FROM = 0;
	long HI_FROM = 0;
	long NEN_TO;
	long TUKI_TO = 0;
	long HI_TO = 0;
	long KESSAN_NEN = 0;
	long KESSAN_TUKI = 0;
	long YMD_FROM;
	long YMD_TO;
	long KISHU;
	int dum;
	String GENKIN_AC;

	// フォームの初期値算定
	// NULL値のチェック
	if (String.valueOf(param.get("cmbBumon")).equals("")) {
	    BUMON = "";
	} else {
	    // フォームからデータを取得
	    BUMON = String.valueOf(param.get("cmbBumon"));
	}
	
	if (String.valueOf(param.get("txtTukiFrom")).equals("")) {
	    TUKI_FROM = 4;
	} else {
	    // フォームからデータを取得
	    TUKI_FROM = Long.parseLong(String.valueOf(param.get("txtTukiFrom")));
	}
	
	if (String.valueOf(param.get("txtTukiTo")).equals("")) {
	    TUKI_TO = 4;
	} else {
	    // フォームからデータを取得
	    TUKI_TO = Long.parseLong(String.valueOf(param.get("txtTukiTo")));
	}
	if (String.valueOf(param.get("txtHiFrom")).equals("")) {
	    HI_FROM = 1;
	} else {
	    // フォームからデータを取得
	    HI_FROM = Long.parseLong(String.valueOf(param.get("txtHiFrom")));
	}
	if (String.valueOf(param.get("txtHiTo")).equals("")) {
	    HI_TO = 31;
	} else {
	    // フォームからデータを取得
	    HI_TO = Long.parseLong(String.valueOf(param.get("txtHiTo")));
	}
	
	// フォームからデータを取得
	YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	YMD_TO = YM_GET(TUKI_TO, HI_TO);
	NEN_FROM = YMD_FROM / 10000;
	NEN_TO = YMD_TO / 10000;
		
	HBaseData base = amsDao.getBaseData();
	KESSAN_NEN = base.getYeyyyy();
	KESSAN_TUKI = base.getYemm();
		
	KISHU = (KESSAN_NEN - 1) * 10000 + (KESSAN_TUKI + 1) * 100 + 1;
	GENKIN_AC = "1110";
		
	String view_name; 
	StringBuffer sql;
		
	/*
	view_name = "debit_expense_item_totals_repetitive_remaining";
	sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
	QueryDefs(sql, view_name);
	*/
	amsDao.dropView("debit_expense_item_totals_repetitive_remaining");
	sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
	amsDao.createView("debit_expense_item_totals_repetitive_remaining", sql.toString());
	//amsDao.debit_expense_item_totals_repetitive_remaining(KISHU, YMD_FROM, BUMON);
		
	/*
	view_name = "credit_expense_item_totals_repetitive_remaining";
	sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
	QueryDefs(sql, view_name);
	*/
	
	amsDao.dropView("credit_expense_item_totals_repetitive_remaining");
	sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
	amsDao.createView("credit_expense_item_totals_repetitive_remaining", sql.toString());
	//amsDao.credit_expense_item_totals_repetitive_remaining(KISHU, YMD_FROM, BUMON);
		
	/*
	view_name = "balance_before_provision";
	sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"+BUMON+"'");
	QueryDefs(sql, view_name);
	*/
	
	amsDao.dropView("balance_before_provision");
	sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"+BUMON+"'");
	amsDao.createView("balance_before_provision", sql.toString());
	/*
	view_name = "debit_expense_item_totals";
	sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
	QueryDefs(sql, view_name);
	*/
	amsDao.dropView("debit_expense_item_totals");
	sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
	amsDao.createView("debit_expense_item_totals", sql.toString());
	/*   
	view_name = "credit_expense_item_totals";
	sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode ='"+ BUMON +"' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
	QueryDefs(sql, view_name);
	*/
	amsDao.dropView("credit_expense_item_totals");
	sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode ='"+ BUMON +"' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
	amsDao.createView("credit_expense_item_totals", sql.toString());
	/*
	view_name = "cash_check_table";
	sql = new StringBuffer("SELECT DISTINCTROW LEFT(devcode,3) AS department, MAX(department_couses_details_by_opening_balance_auxuliary_ledger.devname) AS max_department_name, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision) AS previous_balance_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount) AS debit_amount_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount) AS credit_amount_sum, IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount),0) - IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount),0) AS today_balance, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount) AS total_balance_amount,department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode, department_couses_details_by_opening_balance_auxuliary_ledger.acsubname FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.accode = '1110' GROUP BY LEFT(devcode,3),department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode,department_couses_details_by_opening_balance_auxuliary_ledger.acsubname HAVING department = '010';");
	*/
	amsDao.dropView("cash_check_table");
	sql = new StringBuffer("SELECT DISTINCTROW LEFT(devcode,3) AS department, MAX(department_couses_details_by_opening_balance_auxuliary_ledger.devname) AS max_department_name, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision) AS previous_balance_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount) AS debit_amount_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount) AS credit_amount_sum, IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount),0) - IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount),0) AS today_balance, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount) AS total_balance_amount,department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode, department_couses_details_by_opening_balance_auxuliary_ledger.acsubname FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.accode = '1110' GROUP BY LEFT(devcode,3),department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode,department_couses_details_by_opening_balance_auxuliary_ledger.acsubname HAVING department = '010';");
	amsDao.createView("cash_check_table", sql.toString());
	amsDao.cash_check_table();
	/*
		
	//DoCmd.OpenReport "現金チェック表", acPreview
		
	String param = "";
	CallReport("rptCashCheck",param);*/
	
	String param2="";
	if(BUMON.equals(""))
	    param2 = "010";
	else
	    param2 = BUMON;
	msg = "rptCashCheck" + "~" + param2;
		
	return msg;	
    }
    
    @Override
    public String GL_Q_CON2(Map param) {
    	String msg = "";
    	
    	//総勘定元帳
	    int NARABI=0;
	    long NEN_FROM=0;  //開始年
	    long NEN_TO=0;    //終了年
	    long TUKI_FROM=0;  //開始月
	    long TUKI_TO=0;    //終了月
	    long HI_FROM=0;  //開始日
	    long HI_TO=0;    //終了日
	    long YMD_FROM=0;
	    long YMD_TO=0;
	    long KESSAN_NEN=0; //決算年
	    long KESSAN_TUKI;  //決算月
	    String AC_FROM;   //開始科目
	    String AC_TO;   //終了科目
	    String ACSEL[] = new String[6];   //選択科目配列
	    String ACSEL_2[] = new String[6];   //選択科目配列
	    String BUMON; //部門
	    long KISHU=0;
	    String SHUKEI_KEY;
	    String JOKEN="";
	    String KAMOKU_JOKEN = "";
	    double ZANDAKA_TRAN=0;
	    String SQL_TEMP;
	    int dum=0;
	    long GYOHAN=0;
	    
	    // null check from front end
	    //get the value from html file
	    
	    BUMON 		= String.valueOf(param.get("cmbBumon"));
		NARABI 		= Integer.parseInt(String.valueOf(param.get("cmbNarabi")));
		AC_FROM 	= String.valueOf(param.get("cmbAcFrom"));
		AC_TO 		= String.valueOf(param.get("cmbAcTo"));
		ACSEL[0] 	= String.valueOf(param.get("cmbAcSel1"));
		ACSEL[1] 	= String.valueOf(param.get("cmbAcSel2"));
		ACSEL[2] 	= String.valueOf(param.get("cmbAcSel3"));
		ACSEL[3] 	= String.valueOf(param.get("cmbAcSel4"));
		ACSEL[4] 	= String.valueOf(param.get("cmbAcSel5"));
		ACSEL[5] 	= String.valueOf(param.get("cmbAcSel6"));
		String cmbPrjCode = String.valueOf(param.get("cmbPrjCode"));
		
		if(ACSEL[0].equals(" ")){
	        ACSEL_2[0] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[0] = ACSEL[0];
	    }
	    if(ACSEL[1].equals(" ")){ 
	        ACSEL_2[1] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[1] = ACSEL[1];
		}
	    if(ACSEL[2].equals(" ")){
	        ACSEL_2[2] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[2] = ACSEL[2];
	    }
	    if(ACSEL[3].equals(" ")){
	    	ACSEL_2[3] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[3] = ACSEL[3];
	    }
	    if(ACSEL[4].equals(" ")){
	    	ACSEL_2[4] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[4] = ACSEL[4];
	    }
	    if(ACSEL[5].equals(" ")){
	    	ACSEL_2[5] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[5] = ACSEL[5];
	    }
		
		//SAI_SEL = "1110";
	    
		TUKI_FROM = Integer.parseInt(String.valueOf(param.get("txtTukiFrom")));
		TUKI_TO = Integer.parseInt(String.valueOf(param.get("txtTukiTo")));
		HI_FROM = Integer.parseInt(String.valueOf(param.get("txtHiFrom")));
		HI_TO = Integer.parseInt(String.valueOf(param.get("txtHiTo")));
		YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO = YM_GET(TUKI_TO, HI_TO);
		NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    dum = TB_Q_SET2(BUMON, YMD_FROM, YMD_TO, KISHU);
	    
	    StringBuffer sql = new StringBuffer("INSERT INTO departments_subject_trial_balance SELECT DISTINCTROW before_provision_balance_aggregation.aggregate_key,MAX(before_provision_balance_aggregation.department_code) AS department_code, MAX(devtables.devname) AS devname,MAX(before_provision_balance_aggregation.item_code) AS item_code, MAX(before_provision_balance_aggregation.item_name) AS item_name,SUM(before_provision_balance_aggregation.budget) AS budget, SUM(before_provision_balance_aggregation.balance_before_provision) AS balance_before_provision,SUM(debit_department_subjects_totals.debit_amount) AS debit_amount, SUM(credit_department_subjects_totals.credit_amount) AS credit_amount,balance_before_provision + IFNULL(debit_amount,0)-IFNULL(credit_amount,0) AS balance_amount FROM before_provision_balance_aggregation LEFT JOIN debit_department_subjects_totals ON before_provision_balance_aggregation.aggregate_key = debit_department_subjects_totals.debit_key LEFT JOIN credit_department_subjects_totals ON before_provision_balance_aggregation.aggregate_key = credit_department_subjects_totals.crkey LEFT JOIN devtables ON before_provision_balance_aggregation.department_code = devtables.devcode GROUP BY before_provision_balance_aggregation.aggregate_key");
	    amsDao.TableDefs(sql,"departments_subject_trial_balance");
	    
	    amsDao.ledger_tran_clear();
	    
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'' AS formula1, '前繰残高' AS formula2, '' AS formula3,'' AS formula4,departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + AC_FROM +"' AND departments_subject_trial_balance.aggregate_key <='"+ BUMON + AC_TO +"999999"+"')");
	    //logger
	    } else {
	    	//KAMOKU_JOKEN = "(departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[0] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[1] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[2] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[3] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[4] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[5] +"')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[0] +"')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		}
	    	}
	    	
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'', '前繰残高', '','',departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, '', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + AC_FROM +"') AND (departments_subject_trial_balance.aggregate_key <= '"+ BUMON + AC_TO +"999999')");
	    } else {
	    	//KAMOKU_JOKEN = "(departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[0] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[1] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[2] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[3] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[4] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[5] +"')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[0] +"')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		}
	    	}
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, ' ', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    
	    amsDao.QueryDefsUpdate("UPDATE journals SET journals.PRJCODE = '' WHERE journals.prjcode Is Null OR journals.prjcode = 'null'");
    	//amsDao.updateJournalPrjCode();
    	
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,subjects_key_journal.dramount, 0 AS cramount,subjects_key_journal.craccode AS subject_code,credit_department_subjects.acname AS subject_name,subjects_key_journal.cracsubcode AS specific_code,subjects_key_journal.cracsubname AS specific_name, subjects_key_journal.descode,subjects_key_journal.desname,subjects_key_journal.drkey";
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.PRJCODE,subjects_key_journal.PRJNAME FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode= devtables.devcode WHERE (((subjects_key_journal.drkey)>= '"+ BUMON + AC_FROM +"' AND (subjects_key_journal.drkey) <= '"+ BUMON + AC_TO +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " AND (yyyy * 10000 +mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ cmbPrjCode + "%'";
	    } else{
	    	//KAMOKU_JOKEN = "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (subjects_key_journal.drkey >='"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] + "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    	
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		}
	    	}
	    	
	    	SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode WHERE (("+ KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd) >= "+ YMD_FROM +" AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ cmbPrjCode +"%'";
	    }
	    amsDao.QueryDefsInsert(SQL_TEMP);
    	
	 //////////////
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,0 AS dramount,subjects_key_journal.cramount,subjects_key_journal.draccode AS subject_code,debit_department_subjects.acname AS subject_name,subjects_key_journal.dracsubcode AS specific_code,subjects_key_journal.dracsubname AS specific_name,subjects_key_journal.descode, subjects_key_journal.descode, subjects_key_journal.crkey";
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	        SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key) WHERE ((subjects_key_journal.crkey >= '"+ BUMON + AC_FROM +"' AND subjects_key_journal.crkey <= '"+ BUMON + AC_TO +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ cmbPrjCode + "%'";
	    } else{
	    	//KAMOKU_JOKEN = "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] + "' AND subjects_key_journal.crkey <= '" + BUMON + ACSEL_2[1] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] + "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2 [5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    	    
	    	KAMOKU_JOKEN ="";
		if(!ACSEL_2[0].equals("")){
		    KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
		}
		if(!ACSEL_2[1].equals("")){
		    if(KAMOKU_JOKEN.equals("")){
		    	KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
		} else{
		    KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
		}
	}
	if(!ACSEL_2[2].equals("")){
	    if(KAMOKU_JOKEN.equals("")){
		KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	} else{
	    KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	}
    }
	if(!ACSEL_2[3].equals("")){
	    if(KAMOKU_JOKEN.equals("")){
		KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    } else{
		KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    }
	}
	if(!ACSEL_2[4].equals("")){
	    if(KAMOKU_JOKEN.equals("")){
		KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	} else{
		KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	}
     }if(!ACSEL_2[5].equals("")){
	if(KAMOKU_JOKEN.equals("")){
	    KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	} else{
	    KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	}
     }
	   		
     SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key WHERE (("+ KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd)>="+ YMD_FROM +" AND (yyyy * 10000 + mm * 100 + dd) <="+ YMD_TO +")) AND prjcode LIKE '"+ cmbPrjCode + "%'";
	    }
	    amsDao.QueryDefsInsert(SQL_TEMP);	 	    
	//////////////    
    	
    	if(NARABI == 1){
    		SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.je_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd";
    	} else{
    	    SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.je_number,ledger_tran.l_number";
    	}
    	StringBuffer SQL = new StringBuffer("INSERT into ledger (aggregate_key,accode,acname,acsubcode,acsubname,devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,draccode,dracname,dracsubcode,dracsubname,dramount,cramount,balance,descode,desname,sundry,vendorcode,vendorname,otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW ledger_tran.aggregate_key,actablesaggregation2.item_code AS accode,actablesaggregation2.item_name AS acname, '' AS acsubcode, '' AS acsubname,ledger_tran.devcode,ledger_tran.devname,ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.draccode,ledger_tran.dracname,ledger_tran.dracsubcode,ledger_tran.dracsubname,ledger_tran.dramount,ledger_tran.cramount,ledger_tran.balance,ledger_tran.descode,ledger_tran.desname,ledger_tran.sundry,ledger_tran.vendorcode,ledger_tran.vendorname,ledger_tran.otherdata,ledger_tran.drctax,ledger_tran.crctax,ledger_tran.prjcode,ledger_tran.prjname FROM ledger_tran INNER JOIN actablesaggregation2 ON ledger_tran.aggregate_key = actablesaggregation2.aggregate_key " + SQL_TEMP);
    	
    	amsDao.TableDefs(SQL, "ledger");
    	logger.info("Insert to ledger " +SQL);
    	
    	String origCode = amsDao.getLedgerWithoutDetail();
    	
    	String sqlLdgDel = "";
    	if(!origCode.equals("")){
    		sqlLdgDel = "DELETE FROM ledger WHERE ((je_number=0 OR je_number=999999) AND aggregate_key NOT IN("+origCode+"))";
    	} else{
    		sqlLdgDel = "DELETE FROM ledger WHERE (je_number=0 OR je_number=999999)";
    	}
    	
		amsDao.QueryDefsUpdate(sqlLdgDel);
		
		List<HLedger> ledgers = amsDao.getLedger();
		for(HLedger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			String sqlDel = "UPDATE ledger SET balance="+ZANDAKA_TRAN+" WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			amsDao.QueryDefsUpdate(sqlDel);
    		}
    		if(ldg.getSundry() == 1){
    			String sqlDel = "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			amsDao.QueryDefsUpdate(sqlDel);
    		}
    	}
		
		if(NARABI == 1){
    		JOKEN = "入力順";
    	} else if(NARABI == 2){
    		JOKEN = "日付順";
    	}
		
		boolean fo = amsDao.getReportparameters();
		
		String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
		if(fo) {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,1);
		} else {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,2);
		}
		
	String param2="";
		//amsDao.selectReportParameters();
		
	if(NARABI == 1){
    		param2 = "総勘定元帳";
    		//CallReport("subledger",param);
    		//msg= "subledger"+ param2;
    		msg = "subledger" + "~" + param2;
    	} else if(NARABI == 2){
    		param2 = "総勘定元帳";
    		//CallReport("subledger2",param);
    		msg= "subledger2" + "~" + param2;
    	}
		
    	return msg;
    }
    
    @Override
    public String GL_Q_CONTROL(Map param) {
    	String msg = "";
    	
    	int NARABI=0;
	    long NEN_FROM=0;  //開始年
	    long NEN_TO=0;    //終了年
	    long TUKI_FROM=0;  //開始月
	    long TUKI_TO=0;    //終了月
	    long HI_FROM=0;  //開始日
	    long HI_TO=0;    //終了日
	    long YMD_FROM=0;
	    long YMD_TO=0;
	    long KESSAN_NEN=0; //決算年
	    long KESSAN_TUKI;  //決算月
	    String AC_FROM;   //開始科目
	    String AC_TO;   //終了科目
	    String ACSEL[] = new String[6];   //選択科目配列
	    String ACSEL_2[] = new String[6];   //選択科目配列
	    String SAI_SEL = "";   //細目まで指定
	    String BUMON; //部門
	    long KISHU=0;
	    String SHUKEI_KEY;
	    String JOKEN="";
	    String KAMOKU_JOKEN;
	    double ZANDAKA_TRAN=0;
	    String SQL_TEMP;
	    int dum=0;
	    long GYOHAN=0;
	    
	    // null check from front end
	    //get the value from html file
	    
	    BUMON 		= String.valueOf(param.get("cmbBumon"));
		NARABI 		= Integer.parseInt(String.valueOf(param.get("cmbNarabi")));
		AC_FROM 	= String.valueOf(param.get("cmbAcFrom"));
		AC_TO 		= String.valueOf(param.get("cmbAcTo"));
		ACSEL[0] 	= String.valueOf(param.get("cmbAcSel1"));
		ACSEL[1] 	= String.valueOf(param.get("cmbAcSel2"));
		ACSEL[2] 	= String.valueOf(param.get("cmbAcSel3"));
		ACSEL[3] 	= String.valueOf(param.get("cmbAcSel4"));
		ACSEL[4] 	= String.valueOf(param.get("cmbAcSel5"));
		ACSEL[5] 	= String.valueOf(param.get("cmbAcSel6"));
		String cmbPrjCode = String.valueOf(param.get("cmbPrjCode"));
		
		if(ACSEL[0].equals(" ")){
	        ACSEL_2[0] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[0] = ACSEL[0];
	    }
	    if(ACSEL[1].equals(" ")){ 
	        ACSEL_2[1] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[1] = ACSEL[1];
		}
	    if(ACSEL[2].equals(" ")){
	        ACSEL_2[2] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[2] = ACSEL[2];
	    }
	    if(ACSEL[3].equals(" ")){
	    	ACSEL_2[3] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[3] = ACSEL[3];
	    }
	    if(ACSEL[4].equals(" ")){
	    	ACSEL_2[4] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[4] = ACSEL[4];
	    }
	    if(ACSEL[5].equals(" ")){
	    	ACSEL_2[5] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[5] = ACSEL[5];
	    }
		
	    SAI_SEL = String.valueOf((param.get("cmbSaiSel")));
	    
	    TUKI_FROM = Integer.parseInt(String.valueOf(param.get("txtTukiFrom")));
	    TUKI_TO = Integer.parseInt(String.valueOf(param.get("txtTukiTo")));
	    HI_FROM = Integer.parseInt(String.valueOf(param.get("txtHiFrom")));
	    HI_TO = Integer.parseInt(String.valueOf(param.get("txtHiTo")));
	    YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	    YMD_TO = YM_GET(TUKI_TO, HI_TO);
	    NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    dum = TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
	    
	    amsDao.ledger_tran_clear();
	    
	    if(!SAI_SEL.equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + SAI_SEL +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + SAI_SEL + "999999" + "'))");
	    }
	    else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ((((department_couses_details_by_opening_balance_auxuliary_ledger.keycode) >= '"+ BUMON + AC_FROM +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + AC_TO + "999999" +"'))");
	    } else {
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		}
	    	}
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    
	    if(!SAI_SEL.equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + SAI_SEL +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + SAI_SEL + "999999" +"'))");
	    } else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + AC_FROM +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + AC_TO   + "999999" +"'))");
	    } else {
	    	//KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[0] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +" ZZZZZZ')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL[0].equals("")){
	    		KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +"ZZZZZZ')";
	    		}
	    	}
	    	amsDao.QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, '', '残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    amsDao.QueryDefsUpdate("UPDATE journals SET journals.PRJCODE = '' WHERE journals.prjcode Is Null OR journals.prjcode = 'null'");
    	
	    
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW journals.devcode,devtables.devname,journals.je_number,journals.s_number,journals.l_number,journals.yyyy,journals.mm,journals.dd,journals.dramount, 0 AS cramount,journals.craccode AS craccode,credit_department_subjects_specific.acname AS acname,journals.cracsubcode AS cracsubcode,credit_department_subjects_specific.acsubname AS acsubname,journals.descode,journals.desname,journals.drkey";
	    if(!SAI_SEL.equals("")){
	    	SQL_TEMP += ",journals.sundry,journals.vendorcode,journals.vendorname,journals.otherdata,drctax,crctax, prjcode, prjname FROM devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode LEFT JOIN credit_department_subjects_specific ON journals.drkey = credit_department_subjects_specific.aggregate_key WHERE ((journals.crkey>='"+ BUMON + SAI_SEL /*+"' And journals.crkey<='"+ BUMON + SAI_SEL + "999999" */+ "') AND (yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " And (yyyy * 10000 + mm * 100 + dd)<="+ YMD_TO +") AND PRJCODE LIKE '"+ cmbPrjCode + "%'";
	    } else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((((journals.drkey)>='" + BUMON + AC_FROM /*+ "' AND (journals.drkey)<='" + BUMON + AC_TO + "999999" */+ "') AND (yyyy*10000+mm*100+dd >=" + YMD_FROM + " AND yyyy*10000+mm*100+dd <=" + YMD_TO + "))) AND prjcode LIKE '"+ cmbPrjCode +"%'";
	    } else{
	    	//KAMOKU_JOKEN = "((journals.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')) AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
	    	KAMOKU_JOKEN ="(";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		}
	    	}
	    	KAMOKU_JOKEN +=") AND PRJCODE LIKE '"+ cmbPrjCode +"%'";
	    	SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((" + KAMOKU_JOKEN + ") AND ((yyyy*10000+mm*100+dd) >=" + YMD_FROM + " AND (yyyy*10000+mm*100+dd)<=" + YMD_TO + ") AND (dramount<>0))";
	    }
	    amsDao.QueryDefsInsert(SQL_TEMP);
	    
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey";
    	if(!SAI_SEL.equals("")){
    		SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((((journals.crkey)>='" + BUMON + SAI_SEL + "' AND (journals.crkey)<='" + BUMON + SAI_SEL + "999999" + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))) AND prjcode LIKE '"+ cmbPrjCode +"%'";
    	}else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
    	    SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((((journals.crkey)>='" + BUMON + AC_FROM + "' AND (journals.crkey)<='" + BUMON + AC_TO + "999999" + "') AND (yyyy*10000+mm*100+dd> =" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))) AND prjcode LIKE '"+ cmbPrjCode +"%'";
    	} else{
    		//KAMOKU_JOKEN = "((journals.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[1] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[5] +"ZZZZZZ')) AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] + "%'";
    		KAMOKU_JOKEN ="(";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		}
	    	}
	    	KAMOKU_JOKEN +=") AND PRJCODE LIKE '"+ cmbPrjCode +"%'";
    	    SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((" + KAMOKU_JOKEN + ") AND ((yyyy*10000+mm*100+dd) >=" + YMD_FROM + " AND (yyyy*10000+mm*100+dd) <=" + YMD_TO + ") AND (cramount<>0))";
    	}
    	amsDao.QueryDefsInsert(SQL_TEMP);
	    
    	if(NARABI == 1){
    		SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.je_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd";
    	} else{
    	    SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.je_number,ledger_tran.l_number";
    	}
    	StringBuffer SQL = new StringBuffer("INSERT INTO ledger(aggregate_key, accode, acname, acsubcode, acsubname, devcode, devname, je_number, s_number, l_number, yyyy, mm, dd, draccode, dracname, dracsubcode, dracsubname, dramount, cramount, balance, descode, desname, sundry, vendorcode, vendorname, otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW ledger_tran.aggregate_key, actables.accode AS accode, actables.acname AS acname, actables.acsubcode AS acsubcode, actables.acsubname AS acsubname,ledger_tran.devcode, ledger_tran.devname, ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.draccode, ledger_tran.dracname,ledger_tran.dracsubcode, ledger_tran.dracsubname, ledger_tran.dramount, ledger_tran.cramount, ledger_tran.balance, ledger_tran.descode, ledger_tran.desname, ledger_tran.sundry, ledger_tran.vendorcode,ledger_tran.vendorname, ledger_tran.otherdata, ledger_tran.drctax,ledger_tran.crctax,ledger_tran.prjcode, ledger_tran.prjname FROM ledger_tran INNER JOIN actables ON ledger_tran.aggregate_key = actables.keycode " + SQL_TEMP);
    	
    	amsDao.TableDefs(SQL,"ledger");
    	
    	String origCode = amsDao.getLedgerWithoutDetail();
    	
    	String sqlLdgDel = "";
    	if(!origCode.equals("")){
    		sqlLdgDel = "DELETE FROM ledger WHERE ((je_number=0 OR je_number=999999) AND aggregate_key NOT IN("+origCode+"))";
    	} else{
    		sqlLdgDel = "DELETE FROM ledger WHERE (je_number=0 OR je_number=999999)";
    	}
    	amsDao.QueryDefsUpdate(sqlLdgDel);
    	
    	List<HLedger> ledgers = getLedger(); 
    	for(HLedger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			String sqlDel = "UPDATE ledger SET balance="+ZANDAKA_TRAN+" WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			amsDao.QueryDefsUpdate(sqlDel);
    		}
    		if(ldg.getSundry() == 1){
    			String sqlDel = "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			amsDao.QueryDefsUpdate(sqlDel);
    		}
    	}
    	
    	if(NARABI == 1){
    		JOKEN = "入力順";
    	} else if(NARABI == 2){
    		JOKEN = "日付順";
    	}
    	
    	boolean fo = amsDao.getReportparameters();
		
		String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
		if(fo) {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,1);
		} else {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,2);
		}
		
		String param2="";
		amsDao.selectReportParameters();
		
	if(NARABI == 1){
    		param2 = "補助元帳";
    		//CallReport("subledger",param);
    		msg= "subledger" + "~" + param2;
    	} else if(NARABI == 2){
    		param2 = "補助元帳";
    		//CallReport("subledger2",param);
    		msg= "subledger2" + "~" + param2;
    	}
    	
    	return msg;
    }
    
    @Override
    public String GENKIN_SUITO(Map param) {
    	String msg = "";
    	int NARABI;
		long NEN_FROM;			//開始年
		long NEN_TO;			//終了年
		long TUKI_FROM;			//開始月
		long TUKI_TO;			//終了月
		long HI_FROM;			//開始日
		long HI_TO;				//終了日
		long YMD_FROM;
		long YMD_TO;
		long KESSAN_NEN;		//決算年
		long KESSAN_TUKI;		//決算月
		String AC_FROM;			//開始科目
		String AC_TO;			//終了科目
		String ACSEL[] = new String[6]; 	//選択科目配列
		String ACSEL_2[] = new String[6];   //選択科目配列
		String SAI_SEL; 					//細目まで指定
		String BUMON;						//部門
		long KISHU = 0; 
		String SHUKEI_KEY;
		String JOKEN ;
		String KAMOKU_JOKEN;
		double ZANDAKA_TRAN = 0;
		String SQL_TEMP;
		int dum;
		
		// null check from front end
		
		//get the value from html file
		
		BUMON = String.valueOf(param.get("cmbBumon"));
		NARABI = Integer.parseInt(String.valueOf(param.get("cmbNarabi")));
		AC_FROM = String.valueOf(param.get("cmbAcFrom"));
		AC_TO 	= String.valueOf(param.get("cmbAcTo"));
		ACSEL[0] = String.valueOf(param.get("cmbAcSel1"));
		ACSEL[1] = String.valueOf(param.get("cmbAcSel2"));
		ACSEL[2] = String.valueOf(param.get("cmbAcSel3"));
		ACSEL[3] = String.valueOf(param.get("cmbAcSel4"));
		ACSEL[4] = String.valueOf(param.get("cmbAcSel5"));
		ACSEL[5] = String.valueOf(param.get("cmbAcSel6"));
		
		// null check from front end
		
		SAI_SEL = "1110";
	    
		TUKI_FROM = Integer.parseInt(String.valueOf(param.get("txtTukiFrom")));
		TUKI_TO = Integer.parseInt(String.valueOf(param.get("txtTukiTo")));
		HI_FROM = Integer.parseInt(String.valueOf(param.get("txtHiFrom")));
		HI_TO = Integer.parseInt(String.valueOf(param.get("txtHiTo")));
		YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO = YM_GET(TUKI_TO, HI_TO);
		NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    dum = TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
	    
	    amsDao.ledger_tran_clear();
	    
	    if(SAI_SEL != " ") {
	    	amsDao.SAI_SEL_not_null();
	    } else if(ACSEL[0].equals(" ") && ACSEL[1].equals(" ") && ACSEL[2].equals(" ") && ACSEL[3].equals(" ") && ACSEL[4].equals(" ") && ACSEL[5].equals(" ")) {
	    	amsDao.ACSEL_null();
	    } else {
	    	//KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[0] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[0] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[1] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[2] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[3] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[4] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[4] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[5] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    	amsDao.KAMOKU_JOKEN(BUMON);
	    }
	    
	    amsDao.insertLedger();
	    amsDao.getLedger();
	    
	    List<HLedger> ledgers = amsDao.getLedger();
	    for(HLedger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			amsDao.updateLedger(ZANDAKA_TRAN, ldg);
    		}
    		if(ldg.getSundry() == 1){
    			amsDao.updateLedger2(ldg);
    		}
    	}
	    if(NARABI == 1) {
	   		 JOKEN = "入力順";
	   	 } else {
	   		 JOKEN = "日付順";
	   	 }
	    boolean fo = amsDao.getReportparameters();
	    
	    String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
	    
	    if(fo) {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,1);
		} else {
		    amsDao.updateReportparametersLedger(ledger_extrantion_cond,2);
		}
	    
	    msg = "rptCashAccountingBook";
	    return msg;
    }
    
    @Override
    public List<RCashCheck> getCashCheck(String devcode){
	return amsDao.getCashCheck(devcode);
    }
    
    //@Override
    public int TB_Q_SET(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
		// 対象範囲の試算表テーブルを作成する
		int n = 0;
		/*String view_name;*/
		StringBuffer sql;

		if (!BUMON.equals("999")) {
			amsDao.dropView("debit_expense_item_totals_repetitive_remaining");
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			amsDao.createView("debit_expense_item_totals_repetitive_remaining", sql.toString());

			amsDao.dropView("credit_expense_item_totals_repetitive_remaining");
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			amsDao.createView("credit_expense_item_totals_repetitive_remaining", sql.toString());

			amsDao.dropView("balance_before_provision");
			sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"
					+ BUMON + "'");
			amsDao.createView("balance_before_provision", sql.toString());
			
			amsDao.dropView("debit_expense_item_totals");
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode = '"
							+ BUMON + "' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			amsDao.createView("debit_expense_item_totals", sql.toString());

			amsDao.dropView("credit_expense_item_totals");
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode ='"
							+ BUMON + "' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
			amsDao.createView("credit_expense_item_totals", sql.toString());

		} else {
			amsDao.dropView("debit_expense_item_totals_repetitive_remaining");
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode <'100' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			amsDao.createView("debit_expense_item_totals_repetitive_remaining", sql.toString());

			amsDao.dropView("credit_expense_item_totals_repetitive_remaining");
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode <'100' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			amsDao.createView("credit_expense_item_totals_repetitive_remaining", sql.toString());

			amsDao.dropView("balance_before_provision");
			sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0) + IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode <'100'");
			amsDao.createView("balance_before_provision", sql.toString());

			amsDao.dropView("debit_expense_item_totals");
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
							+ " AND journals.devcode <'100' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			amsDao.createView("debit_expense_item_totals", sql.toString());

			amsDao.dropView("credit_expense_item_totals");
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
							+ " AND journals.devcode <'100' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null)");
			amsDao.createView("credit_expense_item_totals", sql.toString());
		}
		
		
		n = 1;

		return n;
	}
    
    public int TB_Q_SET2(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
	// 対象範囲の試算表テーブルを作成する（科目コードで集約）
	int n = 0;
	String view_name;
	StringBuffer sql;

	// DoCmd.OpenQuery "科目キー仕訳テーブル作成"
	sql = new StringBuffer(
			"INSERT INTO subjects_key_journal (je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,dramount,cramount,balance,descode,desname,memo,transtime,sundry,vendorcode,vendorname,otherdata,prjcode,prjname,drctax,crctax) SELECT DISTINCTROW journals.je_number,journals.s_number,journals.l_number, CONCAT(devcode,draccode) AS drkey,CONCAT(devcode,craccode) AS crkey, journals.devcode,journals.devname,journals.draccode,journals.dracname,journals.dracsubcode,journals.dracsubname,journals.craccode,journals.cracname,journals.cracsubcode,journals.cracsubname,journals.yyyy,journals.mm,journals.dd,journals.dramount,journals.cramount,journals.balance,journals.descode,journals.desname,journals.memo,journals.transtime,journals.sundry,journals.vendorcode,journals.vendorname,journals.otherdata,journals.prjcode,journals.prjname,journals.drctax,journals.crctax FROM journals");
	amsDao.TableDefs(sql, "subjects_key_journal");

	view_name = "debit_department_subjects_totals_repetitive_remaining";
	sql = new StringBuffer(
			"SELECT DISTINCTROW subjects_key_journal.drkey AS debit_key, Max(subjects_key_journal.devcode) AS department_code, Max(debit_department_subjects.devname) AS department_name,Max(subjects_key_journal.draccode) AS debit_item_code, Max(debit_department_subjects.acname) AS debit_course_title, Sum(subjects_key_journal.dramount) AS debit_amount FROM subjects_key_journal LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey =debit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd > "
					+ (KISHU - 1) + ") AND (yyyy * 10000 +mm * 100 + dd < " + YMD_FROM
					+ ") AND subjects_key_journal.devcode = '" + BUMON
					+ "' GROUP BY subjects_key_journal.drkey HAVING((Not(Max(subjects_key_journal.draccode))Is Null))");
	amsDao.QueryDefs(sql, view_name);

	view_name = "credit_department_subjects_totals_repetitive_remaining";
	sql = new StringBuffer(
			"SELECT DISTINCTROW subjects_key_journal.crkey AS credit_key, Max(subjects_key_journal.devcode) AS department_code, Max(credit_department_subjects.devname) AS department_name,Max(subjects_key_journal.craccode) AS credit_item_code, Max(credit_department_subjects.acname) AS debit_course_title, Sum(subjects_key_journal.cramount) AS credit_amount FROM credit_department_subjects RIGHT JOIN subjects_key_journal ON credit_department_subjects.aggregate_key = subjects_key_journal.crkey WHERE (yyyy * 10000 + mm * 100 + dd > "
					+ (KISHU - 1) + ")  AND (yyyy * 10000 +mm * 100 + dd < " + YMD_FROM
					+ ") AND subjects_key_journal.devcode ='" + BUMON
					+ "' GROUP BY subjects_key_journal.crkey HAVING((Not(Max(subjects_key_journal.craccode))Is Null))");
	amsDao.QueryDefs(sql, view_name);

	view_name = "before_provision_balance_aggregation";
	sql = new StringBuffer(
			"SELECT DISTINCTROW actablesaggregation2.aggregate_key,actablesaggregation2.department_code,actablesaggregation2.item_code,actablesaggregation2.item_name,actablesaggregation2.budget,(actablesaggregation2.beginning_balance + IFNULL(debit_department_subjects_totals_repetitive_remaining.debit_amount,0) - IFNULL(credit_department_subjects_totals_repetitive_remaining.credit_amount,0)) AS balance_before_provision FROM actablesaggregation2 LEFT JOIN debit_department_subjects_totals_repetitive_remaining ON actablesaggregation2.aggregate_key = debit_department_subjects_totals_repetitive_remaining.debit_key LEFT JOIN credit_department_subjects_totals_repetitive_remaining ON actablesaggregation2.aggregate_key = credit_department_subjects_totals_repetitive_remaining.credit_key WHERE actablesaggregation2.department_code = '"
					+ BUMON + "'");
	amsDao.QueryDefs(sql, view_name);

	view_name = "debit_department_subjects_totals";
	sql = new StringBuffer(
			"SELECT DISTINCTROW subjects_key_journal.drkey AS debit_key, Max(subjects_key_journal.devcode) AS department_code, Max(debit_department_subjects.devname) AS department_name, Max(subjects_key_journal.draccode) AS debit_item_code, Max(debit_department_subjects.acname) AS debit_course_title,Sum(subjects_key_journal.dramount) AS debit_amount FROM subjects_key_journal LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd >= "
					+ YMD_FROM + ") AND (yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
					+ ") AND (subjects_key_journal.devcode = '" + BUMON
					+ "') GROUP BY subjects_key_journal.drkey HAVING((Not(Max(subjects_key_journal.draccode))Is Null))");
	amsDao.QueryDefs(sql, view_name);

	view_name = "credit_department_subjects_totals";
	sql = new StringBuffer(
			"SELECT DISTINCTROW subjects_key_journal.crkey, Max(subjects_key_journal.devcode) AS department_code, Max(credit_department_subjects.devname) AS department_name, Max(subjects_key_journal.craccode) AS credit_item_code, Max(credit_department_subjects.acname) AS credit_course_title,Sum(subjects_key_journal.cramount) AS credit_amount FROM subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey=credit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd >= "
					+ YMD_FROM + ") AND (yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
					+ ")AND (subjects_key_journal.devcode = '" + BUMON
					+ "') GROUP BY subjects_key_journal.crkey HAVING ((Not(Max(subjects_key_journal.craccode))Is Null))");
	amsDao.QueryDefs(sql, view_name);

	n = 1;

	return n;
}
    
    @Override
    public HReportparameters selectReportParameters() {
    	return amsDao.selectReportParameters();
    }
    
    @Override
    public List<HLedger> getLedger() {
    	return amsDao.getLedger();
    }
    
    @Override
    public List<HLedger> getSubledger() {
    	return amsDao.getSubledger();
    }
    
    @Override
    public List<RLedger> getRSubledger() {
    	return amsDao.getRSubledger();
    }
    
    @Override
    public List<HSubledgerSubreport> getSubledgerSubreport(String accode) {
    	return amsDao.getSubledgerSubreport(accode);
    }
    
    public HVendors addVendor(HVendors vendor){
	return amsDao.addVendor(vendor);
    }
    
    public HProjectcode addProject(HProjectcode proj){
	return amsDao.addProject(proj);
    }
    
    public HDescription addDescription(HDescription desc){
	return amsDao.addDescription(desc);
    }
    /* Ole Ul Islam */

    
}
