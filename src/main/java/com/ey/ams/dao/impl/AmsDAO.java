/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* AmsDAO.java
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
package com.ey.ams.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.ams.dao.interfaces.IAmsDAO;
import com.ey.ams.mapper.HAcparametersMapper;
import com.ey.ams.mapper.HActableMapper;
import com.ey.ams.mapper.HBaseDataMapper;
import com.ey.ams.mapper.HCtcMapper;
import com.ey.ams.mapper.HDescriptionMapper;
import com.ey.ams.mapper.HDevcodeselectMapper;
import com.ey.ams.mapper.HDevtablesMapper;
import com.ey.ams.mapper.HFscrtablesMapper;
import com.ey.ams.mapper.HJewrk_updatedMapper;
import com.ey.ams.mapper.HJournalMapper;
import com.ey.ams.mapper.HLedgerMapper;
import com.ey.ams.mapper.HProjectBudgetMapper;
import com.ey.ams.mapper.HProjectTrialBalanceMapper;
import com.ey.ams.mapper.HProjectcodeMapper;
import com.ey.ams.mapper.HReportdataretentionMapper;
import com.ey.ams.mapper.HReportparametersMapper;
import com.ey.ams.mapper.HSubledgerSubreportMapper;
import com.ey.ams.mapper.HTrailBalanceTempMapper;
import com.ey.ams.mapper.HTrialBalanceSubtotalMasterMapper;
import com.ey.ams.mapper.HVendorsMapper;
import com.ey.ams.mapper.RACListPrintMapper;
import com.ey.ams.mapper.RCashCheckMapper;
import com.ey.ams.mapper.RJournalMapper;
import com.ey.ams.mapper.RLedgerMapper;
import com.ey.ams.mapper.RTransferSlipMapper;
import com.ey.ams.mapper.RTransferSlipSubMapper;
import com.ey.ams.mapper.UserMapper;
import com.ey.ams.model.HAcparameters;
import com.ey.ams.model.HActable;
import com.ey.ams.model.HBaseData;
import com.ey.ams.model.HCtc;
import com.ey.ams.model.HDescription;
import com.ey.ams.model.HDevcodeselect;
import com.ey.ams.model.HDevtables;
import com.ey.ams.model.HFscrtables;
import com.ey.ams.model.HJewrk_updated;
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

public class AmsDAO extends BaseDAO implements IAmsDAO {
    private final Logger logger = LoggerFactory.getLogger(AmsDAO.class);

    @Override
    public HUser getEmployeeByPassword(boolean isDeleted, String userName, String pass) {
	HUser profile = null;
	try {
	    profile = getJdbcService().getJdbcTemplate().queryForObject(
		    "SELECT * FROM user WHERE user_name ='" + userName + "' AND password=MD5('" + pass + "')",
		    new UserMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return profile;
    }

    @Override
    public List<HActable> loadAccountRegister() {
	List<HActable> allAccount = new ArrayList<HActable>();

	try {
	    StringBuffer strSql = new StringBuffer("SELECT actablewk.* FROM actablewk ORDER BY actablewk.keycode");

	    logger.info("loadAccountRegister() Query - > " + strSql.toString());

	    allAccount = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HActableMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return allAccount;
    }

    @Override
    public List<HFscrtables> getFSCodeRange() {
	List<HFscrtables> fscrtables = new ArrayList<HFscrtables>();

	try {
	    StringBuffer strSql = new StringBuffer("SELECT fscrtables.fscr, fscrtables.fscrname FROM fscrtables");

	    logger.info("getFSCodeRange() Query - > " + strSql.toString());

	    fscrtables = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HFscrtablesMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fscrtables;
    }

    @Override
    public boolean deleteAccountRow(String ids) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM actablewk WHERE rowid IN(" + ids + ")");

	    logger.info("deleteAccountRow(String ids) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean registerAccount(HActable obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO actablewk (keycode,devcode,accode,acname,acsubcode,acsubname,fscr) VALUES(" + "'"
			    + obj.getKeycode() + "','" + obj.getDevcode() + "','" + obj.getAccode() + "','"
			    + obj.getAcname() + "','" + obj.getAcsubcode() + "','" + obj.getAcsubname() + "','"
			    + obj.getFscr() + "')");

	    logger.info("registerAccount(HActable obj) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean updateAccount(HActable obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE  actablewk SET keycode ='" + obj.getKeycode()
		    + "', devcode ='" + obj.getDevcode() + "',accode ='" + obj.getAccode() + "',acname ='"
		    + obj.getAcname() + "',acsubcode ='" + obj.getAcsubcode() + "',acsubname ='" + obj.getAcsubname()
		    + "',fscr ='" + obj.getFscr() + "' WHERE rowid =" + obj.getRowid());

	    logger.info("updateAccount(HActable obj) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean deleteAllFromActables() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM actables");

	    logger.info("deleteAllFromActables() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean moveFromActablewkToActables() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO actables (keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr) SELECT keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr FROM actablewk");

	    logger.info("moveFromActablewkToActables() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateActable() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "UPDATE actables SET actables.bgbalance = 0 WHERE bgbalance IS NULL");

	    logger.info("updateActable() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    StringBuffer strSql = new StringBuffer("UPDATE actables SET actables.budget = 0 WHERE budget IS NULL");

	    logger.info("updateActable() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public void acTablemntFormOpen() {
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM actablewk");

	    logger.info("acTablemntFormOpen() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    StringBuffer strSql = new StringBuffer("ALTER TABLE actablewk AUTO_INCREMENT = 1;");

	    logger.info("acTablemntFormOpen() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO actablewk (keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr) SELECT keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr FROM actables");

	    logger.info("acTablemntFormOpen() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean veryfyKeyCode(String keycode) {
	List<HActable> allAccount = new ArrayList<HActable>();
	boolean Fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM actablewk WHERE actablewk.keycode='" + keycode + "'");

	    logger.info("veryfyKeyCode(String keycode) Query - > " + strSql.toString());

	    allAccount = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HActableMapper());
	    if (allAccount.size() > 0)
		Fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Fo;
    }

    @Override
    public List<HDevtables> getDevCode() {
	List<HDevtables> alldev = new ArrayList<HDevtables>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM devtables");

	    logger.info("getDevCode() Query - > " + strSql.toString());

	    alldev = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDevtablesMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return alldev;
    }
    
    @Override
    public List<HDevtables> getDevCodeById(String devcode) {
	List<HDevtables> alldev = new ArrayList<HDevtables>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM devtables WHERE devcode='"+devcode+"'");

	    logger.info("getDevCode() Query - > " + strSql.toString());

	    alldev = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDevtablesMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return alldev;
    }

    @Override
    public List<RACListPrint> getActableReportData(String devcode) {
	List<RACListPrint> aclist = new ArrayList<RACListPrint>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT actables.keycode, actables.devcode, devtables.devname, actables.accode, actables.acname, actables.acsubcode, actables.acsubname, actables.fscr, fscrtables.fscrname FROM (actables LEFT JOIN fscrtables ON actables.fscr = fscrtables.fscr) LEFT JOIN devtables ON actables.devcode = devtables.devcode AND devtables.devcode ='"
			    + devcode + "' ORDER BY actables.keycode");

	    logger.info("getActableReportData(String devcode) Query - > " + strSql.toString());

	    aclist = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new RACListPrintMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return aclist;
    }

    @Override
    public List<HJournal> journalCopy(String devcode) {
	List<HJournal> slipnumber = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT journals.s_number, journals.desname AS abstract_name FROM journals WHERE journals.devcode = '"
			    + devcode + "'" + "GROUP BY journals.s_number ORDER BY journals.s_number DESC LIMIT 1");

	    logger.info("journalCopy(String devcode) Query - > " + strSql.toString());
	    slipnumber = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return slipnumber;
    }

    @Override
    public List<HDescription> descriptionCode(String devcode) {
	List<HDescription> descriptionList = new ArrayList<HDescription>();
	try {
	    // StringBuffer strSql = new StringBuffer("SELECT
	    // description.descode, MAX(description.desname) AS
	    // abstractname,desid FROM description WHERE description.devcode
	    // LIKE CONCAT(MID('"+devcode+"',1,2),'%') GROUP BY
	    // description.descode");
	    StringBuffer strSql = new StringBuffer(
		    "SELECT description.descode, description.desname AS abstractname,desid FROM description WHERE description.devcode LIKE CONCAT(MID('010',1,2),'%')");

	    logger.info("journalCopy(String devcode) Query - > " + strSql.toString());
	    descriptionList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDescriptionMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return descriptionList;
    }

    @Override
    public List<HProjectcode> projectCode() {
	List<HProjectcode> prjlist = new ArrayList<HProjectcode>();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT project_code.prjcode, project_code.prjname FROM project_code");

	    logger.info("projectCode() Query - > " + strSql.toString());
	    prjlist = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HProjectcodeMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return prjlist;
    }

    @Override
    public List<HVendors> vendorCode() {
	List<HVendors> vendorlist = new ArrayList<HVendors>();
	try {

	    StringBuffer strSql = new StringBuffer("SELECT vendors.vendorcode, vendors.vendorname FROM vendors");

	    logger.info("vendorCode() Query - > " + strSql.toString());

	    vendorlist = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HVendorsMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return vendorlist;
    }

    @Override
    public HDevtables BumonSansho(String devCode) {
	HDevtables dept = new HDevtables();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM devtables WHERE devcode ='" + devCode + "'");

	    logger.info("BumonSansho(String devCode) Query - > " + strSql.toString());

	    dept = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDevtablesMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dept;
    }

    @Override
    public List<HDevcodeselect> bumonSelect(String devcode) {
	try {
	    float minAmount = 0;

	    StringBuffer strSqlD = new StringBuffer("DROP VIEW IF EXISTS devcodeselect");
	    logger.info("bumonSelect(String devcode) Query - > " + strSqlD.toString());

	    getJdbcService().getJdbcTemplate().execute(strSqlD.toString());

	    StringBuffer strSql = new StringBuffer(
		    "CREATE VIEW devcodeselect AS SELECT Mid(actables.keycode,4,10) AS agkey, actables.devcode, actables.accode, actables.acname, actables.acsubcode, actables.acsubname, acparameters.taxcr,acparameters.keycode FROM actables "
			    + "LEFT JOIN acparameters ON actables.keycode = acparameters.keycode WHERE actables.devcode ='"
			    + devcode + "'");

	    logger.info("bumonSelect(String devcode) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	List<HDevcodeselect> devSel = new ArrayList<HDevcodeselect>();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT devcodeselect.agkey, devcodeselect.acname, devcodeselect.acsubname FROM devcodeselect");

	    logger.info("bumonSelect(String devcode) Query - > " + strSql.toString());

	    devSel = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDevcodeselectMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return devSel;
    }

    @Override
    public List<HAcparameters> getDebitTax() {
	List<HAcparameters> tax = new ArrayList<HAcparameters>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT acparameters.keycode, acparameters.taxcr FROM acparameters");
	    logger.info("bumonSelect(String devcode) Query - > " + strSql.toString());

	    tax = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HAcparametersMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return tax;
    }

    @Override
    public HDevcodeselect getDetails(String key) {
	HDevcodeselect devSel = new HDevcodeselect();
	try {

	    StringBuffer strSql = new StringBuffer("SELECT * FROM devcodeselect WHERE agkey ='" + key + "'");

	    logger.info("getDetails(String key) Query - > " + strSql.toString());

	    devSel = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDevcodeselectMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return devSel;
    }

    @Override
    public HVendors TORI_SANSHO(String selVendor) {
	HVendors vendor = new HVendors();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM vendors WHERE vendorcode ='" + selVendor + "'");

	    logger.info("TORI_SANSHO(String selVendor) Query - > " + strSql.toString());

	    vendor = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HVendorsMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return vendor;
    }
    
    @Override
    public HVendors addVendor(HVendors vendor) {
	HVendors ven = TORI_SANSHO(vendor.getVendorcode());
	if(!ven.getVendorname().equals(vendor.getVendorname())){
        	try {
        	    StringBuffer strSql = new StringBuffer(
        		    "INSERT INTO vendors(vendorcode, vendorname) VALUES('"+vendor.getVendorcode()+"','"+vendor.getVendorname()+"');");
        	    
        	    logger.info("add vendor Query - > " + strSql.toString());
        
        	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
        
        	    ven = vendor;
        
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
	}
	return ven;
    }

    @Override
    public HProjectcode PRJCODE(String code) {
	HProjectcode proj = null;
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM project_code WHERE prjcode ='" + code + "'");

	    logger.info("PRJCODE(String code) Query - > " + strSql.toString());

	    proj = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HProjectcodeMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return proj;
    }
    
    @Override
    public HProjectcode addProject(HProjectcode proj) {
	HProjectcode pro = PRJCODE(proj.getPrjcode());
	if(!proj.getPrjname().equals(proj.getPrjname())){
        	try {
        	    StringBuffer strSql = new StringBuffer(
        		    "INSERT INTO project_code(prjcode, prjname) VALUES('"+proj.getPrjcode()+"','"+proj.getPrjname()+"');");
        	    
        	    logger.info("add project Query - > " + strSql.toString());
        
        	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
        
        	    pro = proj;
        
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
	}
	return pro;
    }

    @Override
    public HDescription descriptionDetails(String selProj) {
	HDescription des = new HDescription();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT description.* FROM description WHERE description.desid=" + selProj);

	    logger.info("descriptionDetails(String selProj) Query - > " + strSql.toString());

	    des = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDescriptionMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return des;
    }
    
    @Override
    public HDescription getDescription(String descode) {
	HDescription des = new HDescription();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT description.* FROM description WHERE description.descode=" + descode);

	    logger.info("descriptionDetails(String selProj) Query - > " + strSql.toString());

	    des = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDescriptionMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return des;
    }
    
    @Override
    public HDescription addDescription(HDescription desc) {
	HDescription dec = getDescription(desc.getDescode());
	if(!dec.getDesname().equals(desc.getDesname()))
	{
        	try {
        	    StringBuffer strSql = new StringBuffer(
        		    "INSERT INTO description(devcode, devname, dramount, cramount, descode, desname) VALUES('"+desc.getDevcode()+"','"+desc.getDevname()+"',0,0,'"+desc.getDescode()+"','"+desc.getDesname()+"');");
        	    
        	    logger.info("add description Query - > " + strSql.toString());
        
        	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
        
        	    dec = desc;
        
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
	}
	return dec;
    }

    @Override
    public boolean deletejewrk() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM jewrk");

	    logger.info("deletejewrk() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean SaveJournal(List<HJournal> journal) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO jewrk(je_number,s_number,devcode,devname,l_number,draccode,dracname,dramount,craccode,cracname,cramount,vendorcode,vendorname,"
			    + "dracsubcode,dracsubname,drctax,cracsubcode,cracsubname,yyyy,mm,dd,crctax,prjcode,prjname,descode,desname) VALUES(");
	    int size = journal.size();
	    for (int ind = 0; ind < size; ind++) {
		HJournal j = journal.get(ind);
		strSql.append(j.getJe_number() + "," + j.getS_number() + ",'" + j.getDevcode() + "','" + j.getDevname()
			+ "'," + j.getL_number() + ",'" + j.getDraccode() + "','" + j.getDracname() + "',"
			+ j.getDramount() + ",'" + j.getCraccode() + "','" + j.getCracname() + "'," + j.getCramount()
			+ ",'" + j.getVendorcode() + "','" + j.getVendorname() + "','" + j.getDracsubcode() + "','"
			+ j.getDracsubname() + "','" + j.getDrctax() + "','" + j.getCracsubcode() + "','"
			+ j.getCracsubname() + "'," + j.getYyyy() + "," + j.getMm() + "," + j.getDd() + ",'"
			+ j.getCrctax() + "','" + j.getPrjcode() + "','" + j.getPrjname() + "','" + j.getDescode()
			+ "','" + j.getDesname() + "')");
		if (ind < (size - 1)) {
		    strSql.append(",(");
		}
	    }

	    logger.info("SaveJournal(List<HJournal> journal) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean SIWAKE_TO_FURIDEN(long SHORI) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO jewrk (je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname,"
			    + "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname) "
			    + "SELECT je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname,"
			    + "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname FROM JE_search_result WHERE je_number = "
			    + SHORI);

	    logger.info("SIWAKE_TO_FURIDEN(long SHORI) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean deleteJournalData(long jno, String devcode) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "DELETE FROM journals WHERE journals.je_number = " + jno + " AND devcode ='" + devcode + "'");

	    logger.info("deleteJournalData(int jno,String devcode) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public int judgment() {
	int SHOKUCHI = 0;
	try {
	    StringBuffer strSql = new StringBuffer("SELECT jewrk.draccode,jewrk.dracsubcode, jewrk.craccode,"
		    + "jewrk. cracsubcode, (sum(dramount) - sum(cramount)) AS balance "
		    + "FROM jewrk GROUP BY jewrk.draccode, jewrk.dracsubcode, " + "jewrk.craccode, jewrk.cracsubcode");
	    logger.info("judgment() Query - > " + strSql.toString());

	    List<HJournal> jour = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	    for (HJournal j : jour) {
		double difference = j.getBalance();
		if (difference != 0)
		    SHOKUCHI = 1;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SHOKUCHI;
    }

    @Override
    public boolean dropView(String viewName) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DROP VIEW IF EXISTS " + viewName);

	    logger.info("dropView(String viewName) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean createLastSlipNoView(String devcode, int mon, int query) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (query == 1) {
		strSql = new StringBuffer(
			"CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode "
				+ "FROM journals WHERE journals.devcode ='" + devcode
				+ "' ORDER BY journals.s_number DESC");
	    } else if (query == 2) {
		strSql = new StringBuffer(
			"CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE not(journals.draccode = '1110' OR journals.craccode = '1110') "
				+ "AND (journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode = '1130' OR journals.craccode = '1130' OR journals.draccode = '1140' OR journals.craccode = '1140' "
				+ "OR journals.draccode = '1150' OR journals.craccode = '1150') AND journals.devcode = '"
				+ devcode + "' AND journals.mm =" + mon + " ORDER BY journals.s_number DESC");
	    } else if (query == 3) {
		strSql = new StringBuffer(
			"CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE NOT(journals.draccode = '1110' OR journals.craccode = '1110') "
				+ "AND NOT(journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode= '1130' OR journals.craccode = '1130' OR journals.draccode ='1140' OR journals.craccode = '1140' "
				+ "OR journals.draccode = '1150' OR journals.craccode ='1150') AND (journals.draccode = '1170' or journals.craccode = '1170') "
				+ "AND journals.devcode = '" + devcode + "' AND journals.mm =" + mon
				+ " ORDER BY journals.s_number DESC");
	    } else if (query == 4) {
		strSql = new StringBuffer(
			"CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE "
				+ "NOT (journals.draccode = '1110' OR journals.craccode = '1110') AND NOT (journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode = '1130' "
				+ "or journals.craccode = '1130' or journals.draccode = '1140' or journals.craccode = '1140' or journals.draccode = '1150' or journals.craccode = '1150') "
				+ "and not (journals.draccode = '1170' or journals.craccode = '1170') and journals.devcode = '"
				+ devcode + "' AND journals.mm = '" + mon
				+ "' AND journals.s_number> 7000 ORDER BY journals.s_number DESC");
	    }
	    logger.info("createLastSlipNoView(String devcode) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HJournal> getLastSlipNo() {
	List<HJournal> lastSlip = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM lastslipnumber");
	    logger.info("getLastSlipNo() Query - > " + strSql.toString());

	    lastSlip = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return lastSlip;
    }

    @Override
    public boolean createLastJournalNumberView() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "CREATE VIEW lastjournalnumber AS SELECT journals.je_number FROM journals ORDER BY journals.je_number DESC");

	    logger.info("createLastJournalNumberView() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public int getLastJournalNo() {
	int j_no = 0;
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM lastjournalnumber limit 1");
	    logger.info("getLastSlipNo() Query - > " + strSql.toString());

	    j_no = getJdbcService().getJdbcTemplate().queryForInt(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return j_no;
    }

    @Override
    public List<HJournal> getAmountCheck(String type) {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (type.equals("DR")) {
		strSql = new StringBuffer(
			"SELECT * FROM jewrk WHERE (IFNULL(jewrk.draccode,0) = 0 AND jewrk.draccode <> '') AND (jewrk.dramount = 0 OR IFNULL(jewrk.dramount,0)=0)");
	    } else if (type.equals("CR")) {
		strSql = new StringBuffer(
			"SELECT * FROM jewrk WHERE (IFNULL(jewrk.craccode,0) = 0 AND jewrk.craccode <> '') AND (jewrk.cramount = 0 OR IFNULL (jewrk.cramount,0)=0)");
	    }
	    logger.info("getAmountCheck(String type) Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public double getDrCrDifference() {
	double difference = 0;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT IFNULL(sum(jewrk.dramount),0) -  IFNULL(sum(jewrk.cramount),0) AS balance FROM jewrk");
	    logger.info("getDrCrDifference() Query - > " + strSql.toString());

	    HJournal journal = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HJournalMapper());
	    difference = journal.getBalance();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return difference;
    }

    @Override
    public List<HJournal> getBlankAccodeCheck(String type) {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (type.equals("DR")) {
		strSql = new StringBuffer(
			"SELECT jewrk.draccode,sum(jewrk.dramount) AS balance FROM jewrk GROUP BY jewrk.draccode HAVING(jewrk.draccode is null OR jewrk.draccode = '') AND (sum(jewrk.dramount) <> 0)");
	    } else if (type.equals("CR")) {
		strSql = new StringBuffer(
			"SELECT jewrk.craccode,sum(jewrk.cramount) AS balance FROM jewrk GROUP BY jewrk.craccode HAVING(jewrk.craccode is null OR jewrk.craccode = '') AND (sum(jewrk.cramount) <> 0)");
	    }
	    logger.info("getBlankAccodeCheck(String type) Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public List<HJournal> getBlankDrCrAmountCheck() {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT jewrk.dramount, jewrk.cramount FROM jewrk WHERE (((jewrk.dramount) = 0) AND ((jewrk.cramount) = 0))");

	    logger.info("getBlankDrCrAmountCheck(String type) Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public List<HJournal> getJewrk() {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM jewrk ORDER BY l_number");

	    logger.info("getJewrk() Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public boolean updateJournalNumberNew(long LAST_SIWAKE_NO, long LAST_DEN_NO, String BUMON, String BUMON_N, long YY,
	    long MM, long DD) {
	boolean fo = true;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE jewrk SET jewrk.je_number =" + (LAST_SIWAKE_NO + 1)
		    + ", jewrk.s_number =" + (LAST_DEN_NO + 1) + ", jewrk.devcode = '" + BUMON + "', jewrk.devname = '"
		    + BUMON_N + "', jewrk.yyyy =" + YY + ",jewrk.mm = " + MM + ", jewrk.dd = " + DD);

	    logger.info(
		    "updateJournalNumberNew(long LAST_SIWAKE_NO,long LAST_DEN_NO,String BUMON,String BUMON_N,long YY,long MM,long DD) Query - > "
			    + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateJournalNumberUpdate(String BUMON, String BUMON_N, long YY, long MM, long DD, long j_no,
	    long s_no) {
	boolean fo = true;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE jewrk SET jewrk.je_number =" + j_no + ", jewrk.s_number = "
		    + s_no + ", jewrk.devcode = '" + BUMON + "', jewrk.devname = '" + BUMON_N + "',jewrk.yyyy = " + YY
		    + ", jewrk.mm = " + MM + ", jewrk.dd = " + DD);

	    logger.info(
		    "updateJournalNumberUpdate(String BUMON,String BUMON_N,long YY,long MM,long DD,int j_no,int s_no) Query - > "
			    + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateJournalLnumber(int GYO, HJournal obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE jewrk SET jewrk.l_number =" + GYO + " WHERE s_number="
		    + obj.getS_number() + " AND je_number=" + obj.getJe_number() + " AND drkey='" + obj.getDrkey()
		    + "' AND crkey='" + obj.getCrkey() + "'");

	    logger.info("updateJournalLnumber(int GYO,HJournal obj) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateJournaldrkeycrkey() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "UPDATE jewrk SET jewrk.drkey = CONCAT(jewrk.devcode,jewrk.draccode,jewrk.dracsubcode), jewrk.crkey = CONCAT(jewrk.devcode,jewrk.craccode,jewrk.cracsubcode)");

	    logger.info("updateJournaldrkeycrkey() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean InsertJournalData(int SHOKUCHI) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO journals(je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"
			    + "dramount,cramount,balance,descode,desname,memo,transtime,sundry,drctax,crctax,drctaxamount,crctaxamount,vendorcode,vendorname,otherdata,receivecode,prjcode,prjname) "
			    + " SELECT je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"
			    + "dramount,cramount,balance,descode,desname,memo,NOW() AS transtime, " + SHOKUCHI
			    + " as sundry,drctax,crctax,drctaxamount,crctaxamount, "
			    + "vendorcode,vendorname,otherdata,receivecode,prjcode,prjname FROM jewrk");
	    logger.info("InsertJournalData(int SHOKUCHI) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean InsertJewrk_bufData(int SHOKUCHI) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO jewrk_buf (je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"
			    + "dramount,cramount,balance,descode,desname,memo,transtime,sundry,drctax,crctax,drctaxamount,crctaxamount,vendorcode,vendorname,otherdata,receivecode,prjcode,prjname) "
			    + "SELECT je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd, "
			    + "dramount,cramount,balance,descode,desname,memo,NOW() AS transtime, " + SHOKUCHI
			    + " as sundry,drctax,crctax,drctaxamount,crctaxamount, "
			    + "vendorcode,vendorname,otherdata,receivecode,prjcode,prjname FROM jewrk");
	    logger.info("InsertJewrk_bufData(int SHOKUCHI) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean createViewJeSearchResult(long LAST_SIWAKE_NO) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "CREATE VIEW JE_search_result_no AS SELECT je_number FROM journals WHERE je_number ="
			    + (LAST_SIWAKE_NO + 1));

	    logger.info("createViewJeSearchResult(long LAST_SIWAKE_NO) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean createViewJeSearchResult(String BUMON) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("CREATE VIEW JE_search_result AS "
		    + "SELECT journals.je_number, journals.s_number, journals.l_number, journals.devcode, journals.devname, journals.draccode, journals.dracname,journals.dracsubcode,"
		    + "journals.dracsubname, journals.craccode,journals.cracname,journals.cracsubcode,journals.cracsubname,journals.yyyy,journals.mm, journals.dd, journals.dramount, "
		    + "journals.cramount,journals.balance,journals.descode, journals.desname,journals.transtime, journals.drctax, journals.crctax, journals.vendorcode, "
		    + "journals.vendorname, journals.otherdata, journals.prjcode, journals.prjname, ctc_1.name FROM JE_search_result_no LEFT JOIN journals ON JE_search_result_no.je_number= "
		    + "journals.je_number LEFT JOIN ctc ON journals.drctax = ctc.code LEFT JOIN ctc AS ctc_1 ON journals.crctax = ctc_1.code WHERE journals.devcode='"
		    + BUMON + "' " + "ORDER BY journals.s_number, journals.l_number");

	    logger.info("createViewJeSearchResult(String BUMON) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean deleteFromTransferSlip() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM transfer_slip");

	    logger.info("deleteFromTransferSlip() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HJournal> getJE_search_result(int sq) {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM je_search_result WHERE je_number=" + sq + " order by je_number,l_number");

	    logger.info("getJE_search_result() Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public List<HJournal> getJE_search_result() {
	List<HJournal> journal = new ArrayList<HJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM JE_search_result");

	    logger.info("getJE_search_result() Query - > " + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public int getJE_no() {
	int je_no = 0;
	String je_done = "";
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM jewrk_updated");

	    logger.info("getJE_no() Query - > " + strSql.toString());

	    List<HJewrk_updated> je_no_done = getJdbcService().getJdbcTemplate().query(strSql.toString(),
		    new Object[] {}, new HJewrk_updatedMapper());

	    for (HJewrk_updated j : je_no_done) {
		if (je_done.equals(""))
		    je_done = String.valueOf(j.getJe_number());
		else
		    je_done = je_done + "," + String.valueOf(j.getJe_number());
	    }
	} catch (Exception e) {
	    // e.printStackTrace();
	}

	try {
	    StringBuffer strSql = new StringBuffer("");

	    if (je_done.equals("")) {
		strSql = new StringBuffer("SELECT * FROM je_search_result_no order by je_number limit 1");
	    } else {
		strSql = new StringBuffer("SELECT * FROM je_search_result_no WHERE je_number NOT IN(" + je_done
			+ ") order by je_number limit 1");
	    }

	    logger.info("getJE_no() Query - > " + strSql.toString());

	    je_no = getJdbcService().getJdbcTemplate().queryForInt(strSql.toString());

	} catch (Exception e) {
	    // e.printStackTrace();
	}
	return je_no;
    }
    
    @Override
    public List<RCashCheck> getCashCheck(String devcode) {
	List<RCashCheck> cash = new ArrayList<RCashCheck>();

	try {
	    StringBuffer sql = new StringBuffer("SELECT DISTINCTROW LEFT(devcode,3) AS department, MAX(department_couses_details_by_opening_balance_auxuliary_ledger.devname) AS max_department_name,SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision) AS previous_balance_sum,SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount) AS debit_amount_sum,SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount) AS credit_amount_sum,IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount),0) - IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount),0) AS today_balance,SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount) AS total_balance_amount,department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode, department_couses_details_by_opening_balance_auxuliary_ledger.acsubname FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.accode = '1110' GROUP BY LEFT(devcode,3),department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode,department_couses_details_by_opening_balance_auxuliary_ledger.acsubname HAVING department = '"+devcode+"'");

	    cash = getJdbcService().getJdbcTemplate().query(sql.toString(), new Object[] {},
		    new RCashCheckMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return cash;
    }
    
    @Override
    public boolean InsertTransferSlip(HJournal jor) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO transfer_slip (devcode, devname, je_number, s_number, yyyy, mm, dd, abstract_name) VALUES('"
			    + jor.getDevcode() + "','" + jor.getDevname() + "'," + jor.getJe_number() + ","
			    + jor.getS_number() + "," + jor.getYyyy() + "," + jor.getMm() + "," + jor.getDd() + ",'"
			    + jor.getDesname() + "')");

	    logger.info("InsertTransferSlip(HJournal jor) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean FURIDEN_CLEAR() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM jewrk");

	    logger.info("FURIDEN_CLEAR() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean update_jewrk() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE jewrk set dramount = 0,cramount = 0");

	    logger.info("update_jewrk() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<RTransferSlip> getTransferSlip() {
	List<RTransferSlip> transfer = new ArrayList<RTransferSlip>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM transfer_slip");

	    logger.info("getTransferSlip() Query - > " + strSql.toString());

	    transfer = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new RTransferSlipMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return transfer;
    }

    @Override
    public List<RTransferSlipSub> getTransferSlipSub(String devCode) {
	List<RTransferSlipSub> transfersub = new ArrayList<RTransferSlipSub>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT journals.je_number,journals.s_number,journals.l_number,journals.devcode,journals.devname, journals.draccode, journals.dracname, journals.dracsubcode, journals.dracsubname, journals.craccode, journals.cracname, journals.cracsubcode, journals.cracsubname, journals.yyyy, journals.mm, journals.dd,journals.dramount,journals.cramount,journals.balance,journals.descode,journals.desname,journals.memo, journals.transtime, journals.drctax, journals.crctax, journals.vendorcode, journals.vendorname, journals.otherdata, journals.prjcode, journals.prjname, ctc.name, ctc_1.name as n1 FROM ((je_search_result_no LEFT JOIN journals ON je_search_result_no.je_number =journals.je_number) LEFT JOIN ctc ON journals.drctax = ctc.code) LEFT JOIN ctc AS ctc_1 ON journals.crctax = ctc_1.code WHERE ((( journals.devcode) = '"
			    + devCode + "')) ORDER BY journals.s_number, journals.l_number");

	    logger.info("getTransferSlipSub() Query - > " + strSql.toString());

	    transfersub = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new RTransferSlipSubMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return transfersub;
    }

    @Override
    public HBaseData getBaseData() {
	HBaseData base = new HBaseData();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM basedatas");

	    logger.info("getBaseData() Query - > " + strSql.toString());

	    base = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HBaseDataMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return base;
    }

    @Override
    public List<HBaseData> getBaseDatas() {
	List<HBaseData> bases = new ArrayList<HBaseData>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM basedatas");

	    logger.info("getBaseDatas() Query - > " + strSql.toString());

	    bases = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HBaseDataMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return bases;
    }

    @Override
    public Boolean createViewJournalTable(String BUMON, long YMD_FROM, long YMD_TO, long SIWAKE_FROM, long SIWAKE_TO,
	    int sq) {
	boolean fo = false;
	try {
	    String sql = "";
	    if (sq == 1) {
		sql = "SELECT DISTINCTROW je_number, s_number, l_number, yyyy, mm, dd, journals.devcode, "
			+ "Debit_Department_Subjects_Specific.devname,journals.draccode, "
			+ "Debit_Department_Subjects_Specific.acname AS debit_subject_name, "
			+ "journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname AS debit_details_name, "
			+ "dramount, journals.craccode, Credit_Department_Subjects_Specific.acname AS credit_subject_name, "
			+ "journals.cracsubcode, Credit_Department_Subjects_Specific.acsubname AS credit_details_name, "
			+ "cramount, journals.descode, journals.desname, journals.vendorname, transtime, sundry , "
			+ "yyyy * 10000 + mm * 100 + dd AS date FROM journals LEFT JOIN Debit_Department_Subjects_Specific ON "
			+ "journals.drkey = Debit_Department_Subjects_Specific.aggregate_key LEFT JOIN "
			+ "Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key "
			+ "WHERE journals.devcode = '" + BUMON + "' AND yyyy * 10000 + mm * 100 + dd >= " + YMD_FROM
			+ " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO + " AND s_number >=" + SIWAKE_FROM
			+ " AND s_number <=" + SIWAKE_TO + " ORDER BY je_number, l_number, yyyy, mm, dd";
	    } else if (sq == 2) {
		sql = "SELECT DISTINCTROW je_number, s_number, l_number, yyyy, mm, dd, journals.devcode, "
			+ "Debit_Department_Subjects_Specific.devname,journals.draccode, Debit_Department_Subjects_Specific.acname "
			+ "AS debit_subject_name, journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname "
			+ "AS debit_details_name, dramount, journals.craccode, Credit_Department_Subjects_Specific.acname "
			+ "AS credit_subject_name, journals.cracsubcode, Credit_Department_Subjects_Specific.acsubname "
			+ "AS credit_details_name, cramount, journals.descode, journals.desname,journals.vendorname, "
			+ "transtime, sundry , yyyy * 10000 + mm * 100 + dd AS date FROM journals "
			+ "LEFT JOIN Debit_Department_Subjects_Specific ON journals.drkey = Debit_Department_Subjects_Specific.aggregate_key "
			+ "LEFT JOIN Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key "
			+ "WHERE journals.devcode = '" + BUMON + "' AND yyyy * 10000 + mm * 100 + dd >= " + YMD_FROM
			+ " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO + " AND s_number >=" + SIWAKE_FROM
			+ " AND s_number <=" + SIWAKE_TO + " ORDER BY yyyy, mm, dd, je_number,l_number";
	    }
	    StringBuffer strSql = new StringBuffer("CREATE VIEW journal_table AS " + sql);

	    logger.info(
		    "createViewJournalTable(String BUMON, long YMD_FROM, long YMD_TO, long SIWAKE_FROM, long SIWAKE_TO) Query - > "
			    + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean getReportparameters() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM reportparameters");

	    logger.info("getReportparameters() Query - > " + strSql.toString());

	    HReportparameters rptPara = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(),
		    new Object[] {}, new HReportparametersMapper());
	    if (rptPara != null) {
		fo = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public HReportparameters getReportparametersData() {
	HReportparameters rptPara = new HReportparameters();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM reportparameters");

	    logger.info("getReportparametersData() Query - > " + strSql.toString());

	    rptPara = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HReportparametersMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rptPara;
    }

    @Override
    public boolean updateReportparameters(String param, int sq) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("");

	    if (sq == 1) {
		strSql = new StringBuffer("UPDATE reportparameters SET je_extraction_cond = '" + param + "'");
	    } else if (sq == 2) {
		strSql = new StringBuffer("INSERT INTO reportparameters(je_extraction_cond) VALUES('" + param + "')");
	    } else if (sq == 3) {
		strSql = new StringBuffer("UPDATE reportparameters SET trial_balance_cond = '" + param + "'");
	    } else if (sq == 4) {
		strSql = new StringBuffer("INSERT INTO reportparameters(trial_balance_cond) VALUES('" + param + "')");
	    }

	    logger.info("updateReportparameters(String param,int sq) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<RJournal> getJournalReport(int sq, String BUMON, String YMD_FROM, String YMD_TO, String SIWAKE_FROM,
	    String SIWAKE_TO) {
	List<RJournal> journal = new ArrayList<RJournal>();
	try {
	    StringBuffer strSql = new StringBuffer("");

	    if (sq == 1) {
		strSql = new StringBuffer(
			"SELECT DISTINCTROW journals.je_number, journals.s_number, journals.l_number, journals.yyyy, journals.mm, journals.dd,"
				+ "journals.devcode, Debit_Department_Subjects_Specific.devname, journals.draccode, Debit_Department_Subjects_Specific.acname "
				+ "AS debit_subject_name, journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname AS debit_details_name, journals.dramount,"
				+ "journals.craccode, Credit_Department_Subjects_Specific.acname AS credit_subject_name, journals.cracsubcode,"
				+ "Credit_Department_Subjects_Specific.acsubname AS credit_details_name, journals.cramount, journals.descode,"
				+ "journals.desname,journals.vendorname, journals.transtime, journals.sundry , yyyy * 10000 + mm * 100 + dd "
				+ "AS date FROM journals LEFT JOIN Debit_Department_Subjects_Specific ON journals.drkey = Debit_Department_Subjects_Specific.aggregate_key "
				+ "LEFT JOIN Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key WHERE journals.devcode ='"
				+ BUMON + "' AND yyyy * 10000 + mm * 100 + dd >= " + YMD_FROM
				+ " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO + " AND s_number >= " + SIWAKE_FROM
				+ " AND s_number <= " + SIWAKE_TO + " ORDER BY " + "je_number, l_number, yyyy, mm, dd");
	    } else if (sq == 2) {
		strSql = new StringBuffer(
			"SELECT DISTINCTROW journals.je_number, journals.s_number, journals.l_number, journals.yyyy, journals.mm, journals.dd,"
				+ "journals.devcode, Debit_Department_Subjects_Specific.devname, journals.draccode, Debit_Department_Subjects_Specific.acname "
				+ "AS debit_subject_name, journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname AS debit_details_name, journals.dramount,"
				+ "journals.craccode, Credit_Department_Subjects_Specific.acname AS credit_subject_name, journals.cracsubcode,"
				+ "Credit_Department_Subjects_Specific.acsubname AS credit_details_name, journals.cramount, journals.descode,"
				+ "journals.desname,journals.vendorname, journals.transtime, journals.sundry , yyyy * 10000 + mm * 100 + dd "
				+ "AS date FROM journals LEFT JOIN Debit_Department_Subjects_Specific ON journals.drkey = Debit_Department_Subjects_Specific.aggregate_key "
				+ "LEFT JOIN Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key WHERE journals.devcode ='"
				+ BUMON + "' AND yyyy * 10000 + mm * 100 + dd >= " + YMD_FROM
				+ " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO + " AND s_number >= " + SIWAKE_FROM
				+ " AND s_number <= " + SIWAKE_TO + " ORDER BY " + "yyyy, mm, dd, je_number,l_number");
	    }

	    logger.info(
		    "getJournalReport(int sq, String BUMON, String YMD_FROM, String YMD_TO, String SIWAKE_FROM, String SIWAKE_TO) Query - > "
			    + strSql.toString());

	    journal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new RJournalMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return journal;
    }

    @Override
    public boolean createView(String name, String sql) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("CREATE VIEW " + name + " AS " + sql);

	    logger.info("createView(String name,String sql) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean delete_jewrk_update() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM jewrk_updated");

	    logger.info("delete_jewrk_update() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean insert_jewrk_update(long je_no) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("INSERT INTO jewrk_updated (je_number) VALUES(" + je_no + ")");

	    logger.info("insert_jewrk_update(String je_no) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HReportdataretention> getZS_Amt(String BUMON) {
	List<HReportdataretention> rp = new ArrayList<HReportdataretention>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT devcode,monthly,carryoverbalance_of_paymentsbalance,specifiednetassetsopeningbalance FROM reportdataretention WHERE devcode ='"
			    + BUMON + "'");

	    logger.info("getZS_Amt(String BUMON) Query - > " + strSql.toString());

	    rp = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HReportdataretentionMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rp;
    }

    @Override
    public int TB_Q_SET(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
	// 対象範囲の試算表テーブルを作成する
	int n = 0;
	String view_name;
	StringBuffer sql;

	if (!BUMON.equals("999")) {
	    view_name = "debit_expense_item_totals_repetitive_remaining";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
			    + (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
			    + " AND journals.devcode = '" + BUMON
			    + "' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "credit_expense_item_totals_repetitive_remaining";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
			    + (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
			    + " AND journals.devcode = '" + BUMON
			    + "' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "balance_before_provision";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"
			    + BUMON + "'");
	    QueryDefs(sql, view_name);

	    view_name = "debit_expense_item_totals";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
			    + YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode = '"
			    + BUMON + "' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "credit_expense_item_totals";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
			    + YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode ='"
			    + BUMON + "' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
	    QueryDefs(sql, view_name);

	} else {
	    view_name = "debit_expense_item_totals_repetitive_remaining";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
			    + (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
			    + " AND journals.devcode <'100' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "credit_expense_item_totals_repetitive_remaining";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
			    + (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
			    + " AND journals.devcode <'100' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "balance_before_provision";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0) + IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode <'100'");
	    QueryDefs(sql, view_name);

	    view_name = "debit_expense_item_totals";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
			    + YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
			    + " AND journals.devcode <'100' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
	    QueryDefs(sql, view_name);

	    view_name = "credit_expense_item_totals";
	    sql = new StringBuffer(
		    "SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
			    + YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
			    + " AND journals.devcode <'100' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
	    QueryDefs(sql, view_name);
	}
	n = 1;
	return n;
    }

    @Override
    public void TORI_SIWAKE_T() {
	// 取引先別仕訳テーブル作成
	// DoCmd.OpenQuery "取引先別仕訳テーブルクリア"
	try {
	    String strSql = "DELETE trading_destination_journal.* FROM trading_destination_journal";

	    logger.info("TORI_SIWAKE_T() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	// DoCmd.OpenQuery "取引先別仕訳テーブル追加"
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO trading_destination_journal (je_number, l_number, drkey, crkey, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, cracsubcode, cracsubname, yyyy, mm, dd, dramount,"
			    + "cramount, balance, descode, desname, memo, transtime, sundry, drctax, crctax, drctaxamount, crctaxamount, vendorcode)"
			    + "SELECT DISTINCTROW journals.je_number, journals.l_number, COALESCE(journals.drkey,'') + COALESCE(journals.vendorcode,'') AS drkey, COALESCE(journals.crkey,'') + COALESCE(journals.vendorcode,'') AS crkey,"
			    + "journals.devcode, journals.devname, journals.draccode, journals.dracname, journals.dracsubcode, journals.dracsubname, journals.craccode, journals.cracname, journals.cracsubcode,"
			    + "journals.cracsubname, journals.yyyy, journals.mm, journals.dd, journals.dramount, journals.cramount, journals.balance, journals.descode, journals.desname, journals.memo,"
			    + "journals.transtime, journals.sundry, journals.drctax, journals.crctax, journals.drctaxamount, journals.crctaxamount, journals.vendorcode "
			    + "FROM journals "
			    + "WHERE COALESCE(journals.vendorcode,'')> '' AND journals.vendorcode <> 'null'"
			    + "ORDER BY journals.je_number, journals.l_number;");

	    logger.info("TORI_SIWAKE_T() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Override
    public int TB_Q_SET3(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
	// 対象範囲の取引先別試算表テーブルを作成する
	int n = 0;
	String view_name;
	StringBuffer sql;

	view_name = "vendor_specific_debit_expense_item_totals_repetitive_remaining";
	sql = new StringBuffer(
		"SELECT DISTINCTROW trading_destination_journal.drkey, Max(trading_destination_journal.devcode) AS department_code,Max(customer_specific_debit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.draccode) AS debit_item_code,Max(customer_specific_debit_department_subjects_specific.acname) AS debit_course_title, Max(trading_destination_journal.dracsubcode) AS debit_details_code,Max(customer_specific_debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(trading_destination_journal.dramount) AS debit_amount FROM trading_destination_journal LEFT JOIN customer_specific_debit_department_subjects_specific ON trading_destination_journal.drkey = customer_specific_debit_department_subjects_specific.keycode WHERE (yyyy * 10000 + mm * 100 + dd) > "
			+ (KISHU - 1) + " AND (yyyy * 10000 + mm * 100 + dd) < " + YMD_FROM
			+ " AND trading_destination_journal.devcode = ' " + BUMON
			+ "' GROUP BY trading_destination_journal.drkey HAVING(Not(Max(trading_destination_journal.draccode))Is Null)");
	QueryDefs(sql, view_name);

	view_name = "vendor_specific_credit_expense_item_totals_repetitive_remaining";
	sql = new StringBuffer(
		"SELECT DISTINCTROW trading_destination_journal.crkey, Max(trading_destination_journal.devcode) AS department_code,Max(customer_specific_credit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.craccode) AS credit_item_code,Max(customer_specific_credit_department_subjects_specific.acname) AS credit_course_title, Max(trading_destination_journal.cracsubcode) AS credit_details_code,Max(customer_specific_credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(trading_destination_journal.cramount) AS credit_amount FROM trading_destination_journal LEFT JOIN customer_specific_credit_department_subjects_specific ON trading_destination_journal.crkey = customer_specific_credit_department_subjects_specific.keycode WHERE (yyyy * 10000 + mm * 100 + dd) > "
			+ (KISHU - 1) + " AND (yyyy * 10000 + mm * 100 + dd) < " + YMD_FROM
			+ " AND trading_destination_journal.devcode = ' " + BUMON
			+ "' GROUP BY trading_destination_journal.crkey HAVING(Not(Max(trading_destination_journal.craccode))Is Null)");
	QueryDefs(sql, view_name);

	view_name = "vendor_balance_before_provision";
	sql = new StringBuffer(
		"SELECT DISTINCTROW vendor_specific_subject_code.keycode,vendor_specific_subject_code.devcode,vendor_specific_subject_code.accode,actables.acname,vendor_specific_subject_code.acsubcode,actables.acsubname,IFNULL(vendor_specific_subject_code.balance,0) + IFNULL(vendor_specific_debit_expense_item_totals_repetitive_remaining.debit_amount,0) - IFNULL(vendor_specific_credit_expense_item_totals_repetitive_remaining.credit_amount,0) AS balance_before_provision,vendor_specific_subject_code.vendorcode,vendors.vendorname FROM vendors RIGHT JOIN actables RIGHT JOIN vendor_specific_subject_code LEFT JOIN vendor_specific_debit_expense_item_totals_repetitive_remaining ON vendor_specific_subject_code.keycode = vendor_specific_debit_expense_item_totals_repetitive_remaining.debit_item_code LEFT JOIN vendor_specific_credit_expense_item_totals_repetitive_remaining ON vendor_specific_subject_code.keycode = vendor_specific_credit_expense_item_totals_repetitive_remaining.credit_item_code ON actables.devcode = vendor_specific_subject_code.devcode AND actables.accode = vendor_specific_subject_code.accode AND actables.acsubcode = vendor_specific_subject_code.acsubcode ON vendors.vendorcode = vendor_specific_subject_code.vendorcode WHERE vendor_specific_subject_code.devcode ='"
			+ BUMON + "'");
	QueryDefs(sql, view_name);

	view_name = "vendor_debit_expense_item_totals";
	sql = new StringBuffer(
		"SELECT DISTINCTROW trading_destination_journal.drkey, Max(trading_destination_journal.devcode) AS department_code, Max(customer_specific_debit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.draccode) AS debit_item_code, Max(customer_specific_debit_department_subjects_specific.acname) AS debit_course_title, Max(trading_destination_journal.dracsubcode) AS debit_details_code, Max(customer_specific_debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(trading_destination_journal.dramount) AS debit_amount FROM trading_destination_journal LEFT JOIN customer_specific_debit_department_subjects_specific ON trading_destination_journal.drkey = customer_specific_debit_department_subjects_specific.keycode WHERE yyyy * 10000 + mm * 100 + dd >= "
			+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
			+ " AND trading_destination_journal.devcode = '" + BUMON
			+ "' GROUP BY trading_destination_journal.drkey HAVING((Not(Max(trading_destination_journal.draccode)) Is Null))");
	QueryDefs(sql, view_name);

	view_name = "vendor_credit_expense_item_totals";
	sql = new StringBuffer(
		"SELECT DISTINCTROW trading_destination_journal.crkey, Max(trading_destination_journal.devcode) AS department_code, Max(customer_specific_credit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.craccode) AS credit_item_code, Max(customer_specific_credit_department_subjects_specific.acname) AS credit_course_title, Max(trading_destination_journal.cracsubcode) AS credit_details_code, Max(customer_specific_credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(trading_destination_journal.cramount) AS credit_amount FROM trading_destination_journal LEFT JOIN customer_specific_credit_department_subjects_specific ON trading_destination_journal.crkey = customer_specific_credit_department_subjects_specific.keycode WHERE yyyy * 10000 + mm * 100 + dd >= "
			+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
			+ " AND trading_destination_journal.devcode = '" + BUMON
			+ "' GROUP BY trading_destination_journal.crkey HAVING((Not(Max(trading_destination_journal.craccode)) Is Null))");
	QueryDefs(sql, view_name);

	n = 1;

	return n;
    }

    @Override
    public void QueryDefs(StringBuffer sql, String ViewName) {
	dropView(ViewName);

	createView(ViewName, sql.toString());
    }

    @Override
    public boolean deleteTrialBalanceTemp() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM trial_balance_temp");

	    logger.info("deleteTrialBalanceTemp() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean insertTrialBalanceTemp() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO trial_balance_temp (keycode,devcode,devname,accode,acname,acsubcode,acsubname,budget,balance_before_provision,debit_amount,credit_amount,balance_amount,subtotal_key,subtotal_content) SELECT DISTINCTROW balance_before_provision.keycode,balance_before_provision.devcode,devtables.devname,balance_before_provision.accode,balance_before_provision.acname,balance_before_provision.acsubcode,balance_before_provision.acsubname,balance_before_provision.budget,balance_before_provision.balance_before_provision,debit_expense_item_totals.debit_amount,credit_expense_item_totals.credit_amount,IFNULL(balance_before_provision,0) + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_amount, '' AS subtotal_key, '' AS subtotal_content FROM devtables RIGHT JOIN balance_before_provision ON devtables.devcode = balance_before_provision.devcode LEFT JOIN debit_expense_item_totals ON balance_before_provision.keycode = debit_expense_item_totals.drkey LEFT JOIN credit_expense_item_totals ON balance_before_provision.keycode = credit_expense_item_totals.crkey");

	    logger.info("insertTrialBalanceTemp() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean insertTrialBalanceTemp(String BUMON, String ZS_AC, String ZS_AC_NM, double ZS_Amt) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO trial_balance_temp (keycode,devcode,devname,accode,acname,budget,balance_before_provision,debit_amount,credit_amount,balance_amount) SELECT CONCAT('"
			    + BUMON + "','" + ZS_AC + "'), '" + BUMON + "', '' , '" + ZS_AC + "', '" + ZS_AC_NM + "', "
			    + ZS_Amt + ", " + ZS_Amt + ", 0, 0, " + ZS_Amt);

	    logger.info("insertTrialBalanceTemp(String BUMON, String ZS_AC,String ZS_AC_NM,double ZS_Amt) Query - > "
		    + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean updateTrialBalanceTemp(HTrialBalanceSubtotalMaster rst) {
	boolean fo = false;

	try {
	    StringBuffer strSql = new StringBuffer("UPDATE trial_balance_temp SET trial_balance_temp.subtotal_key = '"
		    + rst.getKEY() + "', trial_balance_temp.subtotal_content ='" + rst.getNAIYO()
		    + "' WHERE trial_balance_temp.accode>= '" + rst.getAC_FROM() + "' AND trial_balance_temp.accode<= '"
		    + rst.getAC_TO() + "'");

	    logger.info("getTrialBalanceSubtotalMaster() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;

    }

    @Override
    public List<HTrialBalanceSubtotalMaster> getTrialBalanceSubtotalMaster() {

	List<HTrialBalanceSubtotalMaster> tb = new ArrayList<HTrialBalanceSubtotalMaster>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM trial_balance_subtotal_master");

	    logger.info("getTrialBalanceSubtotalMaster() Query - > " + strSql.toString());

	    tb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HTrialBalanceSubtotalMasterMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return tb;

    }

    @Override
    public List<HTrailBalanceTemp> getTrailBalanceTemp() {

	List<HTrailBalanceTemp> tb = new ArrayList<HTrailBalanceTemp>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM trial_balance_temp");

	    logger.info("getTrailBalanceTemp() Query - > " + strSql.toString());

	    tb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HTrailBalanceTempMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return tb;
    }

    @Override
    public List<HTrailBalanceTemp> getCustomerTrialBalance() {

	List<HTrailBalanceTemp> tb = new ArrayList<HTrailBalanceTemp>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCTROW vendor_balance_before_provision.keycode,vendor_balance_before_provision.devcode,devtables."
			    + "devname,vendor_balance_before_provision.accode,vendor_balance_before_provision.acname,"
			    + "vendor_balance_before_provision.acsubcode,vendor_balance_before_provision.acsubname,vendor_balance_before_provision."
			    + "balance_before_provision,IFNULL(vendor_debit_expense_item_totals.debit_amount,0) AS debit_amount,IFNULL(vendor_credit_expense_item_totals.credit_amount,0) AS credit_amount,"
			    + " IFNULL(balance_before_provision,0) + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_amount,vendor_balance_before_provision.vendorcode,"
			    + "vendor_balance_before_provision.vendorname FROM ((vendor_balance_before_provision LEFT JOIN "
			    + "vendor_debit_expense_item_totals ON vendor_balance_before_provision.keycode = vendor_debit_expense_item_totals.drkey) "
			    + "LEFT JOIN vendor_credit_expense_item_totals ON vendor_balance_before_provision.keycode = vendor_credit_expense_item_totals.crkey) "
			    + "LEFT JOIN devtables ON vendor_balance_before_provision.devcode = devtables.devcode");

	    logger.info("getCustomerTrialBalance() Query - > " + strSql.toString());

	    tb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HTrailBalanceTempMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return tb;
    }

    @Override
    public List<HTrailBalanceTemp> getTrialbalancesubjectsby() {

	List<HTrailBalanceTemp> tb = new ArrayList<HTrailBalanceTemp>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT by_category_subject_details_trial_balance.devcode,by_category_subject_details_trial_balance.devname,by_category_subject_details_trial_balance.accode,"
			    + "by_category_subject_details_trial_balance.acname, Sum(IFNULL(by_category_subject_details_trial_balance.budget,0)) AS budget, Sum(IFNULL(by_category_subject_details_trial_balance."
			    + "balance_before_provision,0)) AS balance_before_provision, Sum(IFNULL(by_category_subject_details_trial_balance.debit_amount,0)) AS debit_amount, Sum(IFNULL(by_category_subject_details_trial_balance"
			    + ".credit_amount,0)) AS credit_amount, Sum(IFNULL(by_category_subject_details_trial_balance.balance_amount,0)) AS balance_amount FROM by_category_subject_details_trial_balance GROUP BY "
			    + "by_category_subject_details_trial_balance.devcode,by_category_subject_details_trial_balance.devname,by_category_subject_details_trial_balance.accode,"
			    + "by_category_subject_details_trial_balance.acname");

	    logger.info("getTrialbalancesubjectsby() Query - > " + strSql.toString());

	    tb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HTrailBalanceTempMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return tb;
    }

    @Override
    public boolean deleteProjectBudget() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM project_budget");

	    logger.info("deleteProjectBudget() Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean insertProjectBudget(long YMD_FROM, long YMD_TO, int sql) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (sql == 1) {
		strSql = new StringBuffer(
			"INSERT INTO project_budget (prjcode,prjname,aggregatekey,debitamount) SELECT journals.prjcode, journals.prjname, journals.drkey, SUM(journals.dramount) AS dramount FROM journals WHERE journals.draccode >='5000' AND yyyy * 10000 + mm * 100 + dd >="
				+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO
				+ " GROUP BY journals.prjcode, journals.prjname, journals.drkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.drkey");
	    } else if (sql == 2) {
		strSql = new StringBuffer(
			"INSERT INTO project_budget (prjcode,prjname,aggregatekey,creditamount) SELECT journals.prjcode, journals.prjname, journals.crkey, SUM(journals.cramount) AS cramount FROM journals WHERE journals.craccode >= '5000' AND yyyy * 10000 + mm * 100 + dd >="
				+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO
				+ " GROUP BY journals.prjcode, journals.prjname, journals.crkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.crkey");
	    } else if (sql == 3) {
		strSql = new StringBuffer(
			"INSERT INTO project_budget (prjcode,prjname,aggregatekey,debitamount) SELECT journals.prjcode, journals.prjname, journals.drkey, SUM(journals.dramount) AS dramount FROM journals WHERE journals.draccode >= '4000' AND yyyy * 10000 + mm * 100 + dd >="
				+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO
				+ " GROUP BY journals.prjcode, journals.prjname, journals.drkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.drkey");
	    } else if (sql == 4) {
		strSql = new StringBuffer(
			"INSERT INTO project_budget (prjcode,prjname,aggregatekey,creditamount) SELECT journals.prjcode, journals.prjname, journals.crkey, SUM(journals.cramount) AS cramount FROM journals WHERE journals.craccode >= '4000' AND yyyy * 10000 + mm * 100 + dd >="
				+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO
				+ " GROUP BY journals.prjcode, journals.prjname, journals.crkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.crkey");
	    }

	    logger.info("insertTrialBalanceTemp(String BUMON, String ZS_AC,String ZS_AC_NM,double ZS_Amt) Query - > "
		    + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public List<HProjectBudget> getProjectBudget() {

	List<HProjectBudget> pb = new ArrayList<HProjectBudget>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT project_budget.prjcode, project_budget.prjname, project_budget.aggregatekey, actables.acname, actables.acsubname, SUM(IFNULL(project_budget.debitamount,0)) AS debitamount, SUM(IFNULL(project_budget.creditamount,0)) AS creditamount FROM project_budget INNER JOIN actables ON project_budget.aggregatekey = actables.keycode GROUP BY project_budget.prjcode, project_budget.prjname, project_budget.aggregatekey, actables.acname, actables.acsubname ORDER BY project_budget.prjcode, project_budget.aggregatekey");

	    logger.info("getProjectBudget() Query - > " + strSql.toString());

	    pb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HProjectBudgetMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return pb;
    }

    @Override
    public List<HProjectTrialBalance> getProjectTrialBalance() {

	List<HProjectTrialBalance> pb = new ArrayList<HProjectTrialBalance>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT business_segment_master.name,project_trial_balance.* FROM project_trial_balance LEFT JOIN business_segment_master ON project_trial_balance.subtotal_name = business_segment_master.business_segment WHERE project_trial_balance.aggregate_key >='0101120100'");

	    logger.info("getProjectTrialBalance() Query - > " + strSql.toString());

	    pb = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HProjectTrialBalanceMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return pb;
    }

    @Override
    public void QueryBudget(StringBuffer strSql) {
	try {
	    logger.info("QueryBudget(StringBuffer strSql) Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /* Opening Balance Maintenance Ole Ul Islam */

    @Override
    public List<HActable> loadOpeningBalance() {
	List<HActable> allAccount = new ArrayList<HActable>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCTROW  actables.devcode, actables.accode, actables.acname, actables.acsubcode, actables.acsubname, actables.bgbalance, actables.budget, actables.budgetmd, actables.keycode FROM actables");
	    allAccount = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HActableMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return allAccount;
    }

    @Override
    public boolean updatebgbalance(HActable obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE actables SET actables.bgbalance = '" + obj.getBgbalance()
		    + "' WHERE keycode = '" + obj.getKeycode() + "'");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HDescription> loadDescription() {
	List<HDescription> description = new ArrayList<HDescription>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT description.desid,description.devcode,description.devname,description.draccode,"
			    + "description.dracname,description.dracsubcode,description.dracsubname,description.craccode,"
			    + "description.cracname,description.cracsubcode,description.cracsubname,description.dramount,"
			    + "description.cramount,description.descode,description.desname,description.drctax,"
			    + "description.crctax FROM description ORDER BY description.descode");
	    description = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDescriptionMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return description;
    }

    @Override
    public List<HDevcodeselect> devSelect() {
	List<HDevcodeselect> devSel = new ArrayList<HDevcodeselect>();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT devcodeselect.agkey, devcodeselect.acname, devcodeselect.acsubname FROM devcodeselect");

	    logger.info("bumonSelect(String devcode) Query - > " + strSql.toString());

	    devSel = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDevcodeselectMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return devSel;
    }

    @Override
    public List<HCtc> loadCtc() {
	List<HCtc> loadCtc = new ArrayList<HCtc>();

	try {

	    StringBuffer strSql = new StringBuffer("SELECT DISTINCTROW ctc.code,ctc.name FROM ctc");
	    loadCtc = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HCtcMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return loadCtc;
    }

    @Override
    public boolean insertDescription(HDescription obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO description (devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, cracsubcode, cracsubname, dramount, cramount, descode, desname, drctax, crctax) "
			    + "VALUES('" + obj.getDevcode() + "','" + obj.getDevname() + "','" + obj.getDraccode()
			    + "','" + obj.getDracname() + "','" + obj.getDracsubcode() + "','" + obj.getDracsubname()
			    + "','" + obj.getCraccode() + "','" + obj.getCracname() + "','" + obj.getCracsubcode()
			    + "','" + obj.getCracsubname() + "','" + obj.getDramount() + "','" + obj.getCramount()
			    + "','" + obj.getDescode() + "','" + obj.getDesname() + "','" + obj.getDrctax() + "','"
			    + obj.getCrctax() + "')");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateDescription(HDescription obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE  description SET devcode ='" + obj.getDevcode()
		    + "',devname ='" + obj.getDevname() + "'," + "draccode ='" + obj.getDraccode() + "',dracname ='"
		    + obj.getDracname() + "'," + "dracsubcode ='" + obj.getDracsubcode() + "',dracsubname ='"
		    + obj.getDracsubname() + "'," + "craccode ='" + obj.getCraccode() + "',cracname ='"
		    + obj.getCracname() + "'," + "cracsubcode ='" + obj.getCracsubcode() + "',cracsubname ='"
		    + obj.getCracsubname() + "'," + "dramount ='" + obj.getDramount() + "',cramount ='"
		    + obj.getCramount() + "'," + "descode ='" + obj.getDescode() + "',desname ='" + obj.getDesname()
		    + "'," + "drctax ='" + obj.getDrctax() + "',crctax ='" + obj.getCrctax() + "' WHERE desid = '"
		    + obj.getDesid() + "'");

	    logger.info(strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean deleteDescription(HDescription obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM description WHERE desid ='" + obj.getDesid() + "'");
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public HDevtables devName(String devname) {
	HDevtables dev = new HDevtables();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM devtables WHERE devcode ='" + devname + "'");

	    // logger.info("TORI_SANSHO(String selVendor) Query - > " +
	    // strSql.toString());

	    dev = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDevtablesMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dev;
    }

    @Override
    public HDevcodeselect debitDetails(String key) {
	HDevcodeselect devSel = new HDevcodeselect();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT devcodeselect.acsubcode,devcodeselect.acname, devcodeselect.acsubname,devcodeselect.taxcr FROM devcodeselect WHERE devcodeselect.agkey ='"
			    + key + "'");

	    logger.info("getDetails(String key) Query - > " + strSql.toString());

	    devSel = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HDevcodeselectMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return devSel;
    }

    @Override
    public List<HActable> acFrom() {
	List<HActable> acfrom = new ArrayList<HActable>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT actablesaggregation.accode,actablesaggregation.acname FROM actablesaggregation");
	    logger.info("getDetails(String key) Query - > " + strSql.toString());
	    acfrom = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HActableMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return acfrom;
    }

    @Override
    public List<HDevcodeselect> devCodeSelect() {
	List<HDevcodeselect> devSel = new ArrayList<HDevcodeselect>();
	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT devcodeselect.agkey,devcodeselect.accode,devcodeselect.acname, devcodeselect.acsubcode,devcodeselect.acsubname FROM devcodeselect");

	    logger.info("bumonSelect(String devcode) Query - > " + strSql.toString());

	    devSel = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HDevcodeselectMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return devSel;
    }

    @Override
    public boolean cash_check_table() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCTROW LEFT(devcode,3) AS department, MAX(department_couses_details_by_opening_balance_auxuliary_ledger.devname) AS max_department_name, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision) AS previous_balance_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount) AS debit_amount_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount) AS credit_amount_sum, IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount),0) - IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount),0) AS today_balance, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount) AS total_balance_amount,department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode, department_couses_details_by_opening_balance_auxuliary_ledger.acsubname FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.accode = '1110' GROUP BY LEFT(devcode,3),department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode,department_couses_details_by_opening_balance_auxuliary_ledger.acsubname HAVING department = '010';");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean ledger_tran_clear() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM ledger_tran");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean SAI_SEL_not_null() {
	boolean fo = false;

	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname,' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='010' + '1110' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '010' + '1110' + '999999'");
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean ACSEL_null() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number)SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='010' + AC_FROM AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '010' + AC_TO + '999999'");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean KAMOKU_JOKEN(String BUMON) {
	boolean fo = false;
	String KAMOKU_JOKEN;
	String ACSEL_2[] = new String[6];
	try {
	    KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[0] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[0]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[1] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[1]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[2] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[2]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[3] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[3]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[4] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[4]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[5] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("
			    + KAMOKU_JOKEN + ");");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean SAI_SEL_not_null2(String BUMON, String SAI_SEL) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='"
			    + BUMON + SAI_SEL
			    + "' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
			    + BUMON + SAI_SEL + "999999" + "'");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean ACSEL_null2(String BUMON, String AC_FROM, String AC_TO) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='"
			    + BUMON + AC_FROM
			    + "' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
			    + BUMON + AC_TO + "999999" + "'");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean KAMOKU_JOKEN2(String BUMON) {
	boolean fo = false;
	String KAMOKU_JOKEN;
	String ACSEL_2[] = new String[6];
	try {
	    KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[0] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[0]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[1] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[1]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[2] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[2]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[3] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[3]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[4] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[4]
		    + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON
		    + ACSEL_2[5] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"
		    + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE "
			    + KAMOKU_JOKEN + "");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean SAI_SEL_not_null3(String BUMON, String SAI_SEL, long YMD_FROM, long YMD_TO) {

	boolean fo = false;
	try {
	    // String SQL_TEMP = "INSERT INTO ledger_tran (devcode, devname,
	    // je_number,
	    // s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,
	    // desname, aggregate_key,sundry,vendorcode,vendorname,otherdata)
	    // SELECT DISTINCTROW journals.devcode, devtables.devname,
	    // journals.je_number,journals.s_number, journals.l_number,
	    // journals.yyyy,journals.mm, journals.dd, journals.dramount, 0 AS
	    // cramount, journals.craccode AS partner_subject_code,
	    // credit_department_subjects_specific.acname AS
	    // oponent_course_title, journals.cracsubcode AS
	    // partner_details_code,
	    // credit_department_subjects_specific.acsubname AS
	    // partner_details_name,journals.descode, journals.desname,
	    // journals.drkey, ";
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode, desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.dramount, 0 AS cramount, journals.craccode AS partner_subject_code, credit_department_subjects_specific.acname AS oponent_course_title, journals.cracsubcode AS partner_details_code, credit_department_subjects_specific.acsubname AS partner_details_name,journals.descode, journals.desname, journals.drkey, journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((journals.drkey >='"
			    + BUMON + SAI_SEL /*
					       * + "' AND journals.drkey <='" +
					       * BUMON + SAI_SEL + "999999"
					       */ + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM
			    + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean ACSEL_null3(String BUMON, String SAI_SEL, String AC_TO, String AC_FROM, long YMD_TO) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode, desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.dramount, 0 AS cramount, journals.craccode AS partner_subject_code, credit_department_subjects_specific.acname AS oponent_course_title, journals.cracsubcode AS partner_details_code, credit_department_subjects_specific.acsubname AS partner_details_name,journals.descode, journals.desname, journals.drkey,journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((journals.drkey >='"
			    + BUMON + SAI_SEL + "' AND journals.drkey <='" + BUMON + AC_TO + "999999"
			    + "') AND (yyyy*10000+mm*100+dd>=" + AC_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO
			    + "))");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean KAMOKU_JOKEN3(String BUMON, long YMD_FROM, long YMD_TO) {
	boolean fo = false;
	String KAMOKU_JOKEN;
	String ACSEL_2[] = new String[6];
	try {
	    KAMOKU_JOKEN = "(journals.drkey >= '" + BUMON + ACSEL_2[0] + "' AND journals.drkey <= '" + BUMON
		    + ACSEL_2[0] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[1] + "' AND journals.drkey <= '"
		    + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[2]
		    + "' AND journals.drkey <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON
		    + ACSEL_2[3] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.drkey >= '"
		    + BUMON + ACSEL_2[4] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[4]
		    + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[5] + "' AND journals.drkey <= '" + BUMON
		    + ACSEL_2[5] + "ZZZZZZ')";
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode, desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.dramount, 0 AS cramount, journals.craccode AS partner_subject_code, credit_department_subjects_specific.acname AS oponent_course_title, journals.cracsubcode AS partner_details_code, credit_department_subjects_specific.acsubname AS partner_details_name,journals.descode, journals.desname, journals.drkey, journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE (("
			    + KAMOKU_JOKEN + ") AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<="
			    + YMD_TO + ") AND debit_amount<>0)");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean SAI_SEL_not_null4(String BUMON, String SAI_SEL, long YMD_FROM, long YMD_TO) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey,journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((journals.crkey >='"
			    + BUMON + SAI_SEL + "' AND journals.crkey <='" + BUMON + SAI_SEL + "999999"
			    + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO
			    + "))");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean ACSEL_null4(String BUMON, String AC_TO, String AC_FROM, long YMD_FROM, long YMD_TO) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey, journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((journals.crkey >='"
			    + BUMON + AC_FROM + "' AND journals.crkey <='" + BUMON + AC_TO + "999999"
			    + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO
			    + "))");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean KAMOKU_JOKEN4(String BUMON, long YMD_FROM, long YMD_TO) {
	boolean fo = false;
	String KAMOKU_JOKEN;
	String ACSEL_2[] = new String[6];
	try {
	    KAMOKU_JOKEN = "(journals.drkey >= '" + BUMON + ACSEL_2[0] + "' AND journals.drkey <= '" + BUMON
		    + ACSEL_2[0] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[1] + "' AND journals.drkey <= '"
		    + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[2]
		    + "' AND journals.drkey <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON
		    + ACSEL_2[3] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.drkey >= '"
		    + BUMON + ACSEL_2[4] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[4]
		    + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[5] + "' AND journals.drkey <= '" + BUMON
		    + ACSEL_2[5] + "ZZZZZZ')";
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey, journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ("
			    + KAMOKU_JOKEN + " AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<="
			    + YMD_TO + ") AND (cramount<>0))");

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean insertLedger() {
	boolean fo = false;
	int NARABI = 0;
	StringBuffer SQL_TEMP;
	try {
	    if (NARABI == 1) {
		SQL_TEMP = new StringBuffer(
			"ORDER BY ledger_tran.aggregate_key, ledger_tran.je_number, ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd");
	    } else {
		SQL_TEMP = new StringBuffer(
			"ORDER BY ledger_tran.aggregate_key, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.je_number, ledger_tran.l_number");
	    }
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO ledger(aggregate_key, accode, acname, acsubcode, acsubname, devcode, devname, je_number, s_number, l_number, yyyy, mm, dd, draccode, dracname, dracsubcode, dracsubname, dramount, cramount, balance, descode, desname, sundry, vendorcode, vendorname, otherdata) SELECT DISTINCTROW ledger_tran.aggregate_key, actables.accode AS accode, actables.acname AS acname, actables.acsubcode AS acsubcode, actables.acsubname AS acsubname, ledger_tran.devcode, ledger_tran.devname, ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.draccode, ledger_tran.dracname, ledger_tran.dracsubcode, ledger_tran.dracsubname, ledger_tran.dramount, ledger_tran.cramount, ledger_tran.balance, ledger_tran.descode, ledger_tran.desname, ledger_tran.sundry, ledger_tran.vendorcode, ledger_tran.vendorname, ledger_tran.otherdata FROM ledger_tran INNER JOIN actables ON ledger_tran.aggregate_key = actables.keycode "
			    + SQL_TEMP);

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HLedger> getLedger() {
	List<HLedger> ledger = new ArrayList<HLedger>();

	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM ledger");
	    ledger = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HLedgerMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return ledger;
    }

    @Override
    public boolean updateLedger(double ZANDAKA_TRAN, HLedger ldg) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE ledger SET balance=" + ZANDAKA_TRAN
		    + " WHERE aggregate_key='" + ldg.getAggregate_key() + "' AND je_number=" + ldg.getJe_number());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    public boolean updateLedger2(HLedger ldg) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"
			    + ldg.getAggregate_key() + "' AND je_number=" + ldg.getJe_number());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean updateReportparametersLedger(String param, int sq) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("");

	    if (sq == 1) {
		strSql = new StringBuffer("UPDATE reportparameters SET ledger_extrantion_cond = '" + param + "'");
	    } else if (sq == 2) {
		strSql = new StringBuffer(
			"INSERT INTO reportparameters(ledger_extrantion_cond) VALUES('" + param + "')");
	    }

	    // logger.info("updateReportparameters(String param,int sq) Query -
	    // > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    public HReportparameters selectReportParameters() {
	HReportparameters rparameters = new HReportparameters();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCTROW reportparameters.ledger_extrantion_cond FROM reportparameters");
	    rparameters = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new Object[] {},
		    new HReportparametersMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rparameters;
    }

    @Override
    public boolean ledgerTranDepartmentCauses(String BUMON, String AC_FROM, String AC_TO) {
	boolean fo = false;
	try {
	    StringBuffer sql = new StringBuffer(
		    "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'' AS formula1, '前繰残高' AS formula2, '' AS formula3,'' AS formula4,departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"
			    + BUMON + AC_FROM + "' AND departments_subject_trial_balance.aggregate_key <='" + BUMON
			    + AC_TO + "999999" + "')");
	    getJdbcService().getJdbcTemplate().execute(sql.toString());

	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    /*
     * @Override public boolean insertLedgerTranKAMOKU_JOKEN(String
     * KAMOKU_JOKEN, String ACSEL_2[], String BUMON) { boolean fo = false;
     * KAMOKU_JOKEN =""; if(!ACSEL_2[0].equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[0] +"')"; } if(!ACSEL_2[1].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[1] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[1] +"')"; } } if(!ACSEL_2[2].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[2] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[2] +"')"; } } if(!ACSEL_2[3].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[3] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[3] +"')"; } } if(!ACSEL_2[4].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[4] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[4] +"')"; } }if(!ACSEL_2[5].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[5] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[5] +"')"; } } try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'', '前繰残高', '','',departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE ("
     * + KAMOKU_JOKEN +")");
     * getJdbcService().getJdbcTemplate().execute(sql.toString());
     * 
     * fo = true; } catch (Exception e) { e.printStackTrace(); }
     * 
     * return fo; }
     * 
     * @Override public boolean insertLedgerTran1(String BUMON, String AC_FROM,
     * String AC_TO) { boolean fo = false; try { StringBuffer sql = new
     * StringBuffer(
     * "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, '', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"
     * + BUMON + AC_FROM +
     * "') AND (departments_subject_trial_balance.aggregate_key <= '"+ BUMON +
     * AC_TO +"999999')");
     * getJdbcService().getJdbcTemplate().execute(sql.toString());
     * 
     * fo = true; } catch (Exception e) { e.printStackTrace(); } return fo; }
     * public boolean insertLedgerTranKAMOKU_JOKEN1(String KAMOKU_JOKEN, String
     * ACSEL_2[], String BUMON) { boolean fo = false; KAMOKU_JOKEN ="";
     * if(!ACSEL_2[0].equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[0] +"')"; } if(!ACSEL_2[1].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[1] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[1] +"')"; } } if(!ACSEL_2[2].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[2] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[2] +"')"; } } if(!ACSEL_2[3].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[3] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[3] +"')"; } } if(!ACSEL_2[4].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[4] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[4] +"')"; } }if(!ACSEL_2[5].equals("")){
     * if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN +=
     * "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[5] +"')"; } else{ KAMOKU_JOKEN +=
     * " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON +
     * ACSEL_2[5] +"')"; } } try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, ' ', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE ("
     * + KAMOKU_JOKEN +")");
     * getJdbcService().getJdbcTemplate().execute(sql.toString()); fo = true; }
     * catch (Exception e) { e.printStackTrace(); } return fo; }
     */

    @Override
    public boolean updateJournalPrjCode() {
	boolean fo = false;

	try {
	    StringBuffer sql = new StringBuffer(
		    "UPDATE journals SET journals.PRJCODE = '' WHERE journals.prjcode Is Null OR journals.prjcode = 'null'");
	    getJdbcService().getJdbcTemplate().execute(sql.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    /*
     * @Override public boolean insertLedgerTran2(String BUMON, String AC_FROM,
     * String AC_TO, long YMD_FROM, long YMD_TO, String cmbPrjCode) { boolean fo
     * = false; try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,subjects_key_journal.dramount, 0 AS cramount,subjects_key_journal.craccode AS subject_code,credit_department_subjects.acname AS subject_name,subjects_key_journal.cracsubcode AS specific_code,subjects_key_journal.cracsubname AS specific_name, subjects_key_journal.descode,subjects_key_journal.desname,subjects_key_journal.drkey,subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.PRJCODE,subjects_key_journal.PRJNAME FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode= devtables.devcode WHERE (((subjects_key_journal.drkey)>= '"
     * + BUMON + AC_FROM +"' AND (subjects_key_journal.drkey) <= '"+ BUMON +
     * AC_TO +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM +
     * " AND (yyyy * 10000 +mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"
     * + cmbPrjCode + "%'");
     * getJdbcService().getJdbcTemplate().execute(sql.toString()); } catch
     * (Exception e) { e.printStackTrace(); } return fo; }
     * 
     * @Override public boolean insertLedgerTranKAMOKU_JOKEN2(String
     * KAMOKU_JOKEN, String ACSEL_2[], String BUMON, long YMD_FROM, long YMD_TO,
     * String cmbPrjCode) { boolean fo = false; KAMOKU_JOKEN ="";
     * if(!ACSEL_2[0].equals("")){ KAMOKU_JOKEN +=
     * "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[0] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
     * } if(!ACSEL_2[1].equals("")){ if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN
     * += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON +
     * ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[2].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON +
     * ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[3].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON +
     * ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[4].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[4] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON +
     * ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4]
     * +"ZZZZZZ')"; } }if(!ACSEL_2[5].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] +
     * "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON +
     * ACSEL_2[5] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5]
     * +"ZZZZZZ')"; } } try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,subjects_key_journal.dramount, 0 AS cramount,subjects_key_journal.craccode AS subject_code,credit_department_subjects.acname AS subject_name,subjects_key_journal.cracsubcode AS specific_code,subjects_key_journal.cracsubname AS specific_name, subjects_key_journal.descode,subjects_key_journal.desname,subjects_key_journal.drkey,subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode WHERE (("
     * + KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd) >= "+ YMD_FROM +
     * " AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +
     * ")) AND prjcode LIKE '"+ cmbPrjCode +"%'");
     * getJdbcService().getJdbcTemplate().execute(sql.toString()); } catch
     * (Exception e) { e.printStackTrace(); } return fo; }
     * 
     * @Override public boolean insertLedgerTran3(String BUMON, String AC_FROM,
     * String AC_TO, long YMD_FROM, long YMD_TO, String cmbPrjCode) { boolean fo
     * = false; try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,0 AS dramount,subjects_key_journal.cramount,subjects_key_journal.draccode AS subject_code,debit_department_subjects.acname AS subject_name,subjects_key_journal.dracsubcode AS specific_code,subjects_key_journal.dracsubname AS specific_name,subjects_key_journal.descode, subjects_key_journal.descode, subjects_key_journal.crkey,subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key) WHERE ((subjects_key_journal.crkey >= '"
     * + BUMON + AC_FROM +"' AND subjects_key_journal.crkey <= '"+ BUMON + AC_TO
     * +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM +
     * " AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +
     * ")) AND prjcode LIKE '"+ cmbPrjCode + "%'");
     * getJdbcService().getJdbcTemplate().execute(sql.toString()); } catch
     * (Exception e) { e.printStackTrace(); } return fo; }
     * 
     * @Override public boolean insertLedgerTranKAMOKU_JOKEN3(String
     * KAMOKU_JOKEN, String ACSEL_2[], String BUMON, long YMD_FROM, long YMD_TO,
     * String cmbPrjCode) { boolean fo = false; KAMOKU_JOKEN ="";
     * if(!ACSEL_2[0].equals("")){ KAMOKU_JOKEN +=
     * "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[0] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
     * } if(!ACSEL_2[1].equals("")){ if(KAMOKU_JOKEN.equals("")){ KAMOKU_JOKEN
     * += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON +
     * ACSEL_2[1] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[2].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON +
     * ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[3].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON +
     * ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3]
     * +"ZZZZZZ')"; } } if(!ACSEL_2[4].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON +
     * ACSEL_2[4] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4]
     * +"ZZZZZZ')"; } }if(!ACSEL_2[5].equals("")){ if(KAMOKU_JOKEN.equals("")){
     * KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[5] +
     * "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
     * } else{ KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON +
     * ACSEL_2[5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5]
     * +"ZZZZZZ')"; } }
     * 
     * try { StringBuffer sql = new StringBuffer(
     * "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,0 AS dramount,subjects_key_journal.cramount,subjects_key_journal.draccode AS subject_code,debit_department_subjects.acname AS subject_name,subjects_key_journal.dracsubcode AS specific_code,subjects_key_journal.dracsubname AS specific_name,subjects_key_journal.descode, subjects_key_journal.descode, subjects_key_journal.crkey,subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key WHERE (("
     * + KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd)>="+ YMD_FROM +
     * " AND (yyyy * 10000 + mm * 100 + dd) <="+ YMD_TO +")) AND prjcode LIKE '"
     * +cmbPrjCode + "%'");
     * getJdbcService().getJdbcTemplate().execute(sql.toString()); } catch
     * (Exception e) { e.printStackTrace(); } return fo; }
     */
    @Override
    public boolean TableDefs(StringBuffer sql, String TableName) {

	try {
	    StringBuffer Strsql = new StringBuffer("DELETE FROM " + TableName);
	    getJdbcService().getJdbcTemplate().execute(Strsql.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	boolean fo = false;
	try {
	    StringBuffer strsql = new StringBuffer(sql);
	    getJdbcService().getJdbcTemplate().execute(strsql.toString());
	    logger.info("Insert from dao in ledger" + strsql);
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public String getLedgerWithoutDetail() {
	String code = "";
	List<HLedger> aggkey = new ArrayList<HLedger>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCTROW aggregate_key FROM ledger WHERE je_number<>0 AND je_number<>999999");
	    aggkey = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HLedgerMapper());

	    for (HLedger ld : aggkey) {
		if (code.equals("")) {
		    code = ld.getAggregate_key();
		} else {
		    code = code + "," + ld.getAggregate_key();
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	String origCode;
	if (code.endsWith(","))
	    origCode = code.substring(0, code.length() - 1);
	else
	    origCode = code;

	return origCode;
    }

    public boolean QueryDefsUpdate(String sql) {
	boolean fo = false;
	try {
	    StringBuffer strsql = new StringBuffer(sql);
	    getJdbcService().getJdbcTemplate().execute(strsql.toString());
	    logger.info("Delete from ledger " + strsql);
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean QueryDefsInsert(String sql) {
	boolean fo = false;
	try {
	    StringBuffer strsql = new StringBuffer(sql);
	    logger.info("QueryDefsInsert" + strsql.toString());
	    getJdbcService().getJdbcTemplate().execute(strsql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HLedger> getSubledger() {
	List<HLedger> ledger = new ArrayList<HLedger>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT accode,acname,acsubcode,acsubname,devcode,devname FROM ledger");
	    ledger = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HLedgerMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return ledger;
    }

    @Override
    public List<RLedger> getRSubledger() {
	List<RLedger> ledger = new ArrayList<RLedger>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT accode,acname,acsubcode,acsubname,devcode,devname FROM ledger");
	    ledger = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new RLedgerMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return ledger;
    }

    @Override
    public List<HSubledgerSubreport> getSubledgerSubreport(String accode) {
	List<HSubledgerSubreport> ledgerSubreport = new ArrayList<HSubledgerSubreport>();

	try {
	    StringBuffer sql = new StringBuffer(
		    "SELECT ledger.*,drctc.name as drcname,crctc.name as crcname FROM ledger Left Join ctc as drctc ON ledger.drctax = drctc.code Left Join ctc as crctc ON crctc.code = ledger.crctax WHERE accode= '"
			    + accode + "' ORDER BY accode,je_number");

	    ledgerSubreport = getJdbcService().getJdbcTemplate().query(sql.toString(), new Object[] {},
		    new HSubledgerSubreportMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return ledgerSubreport;
    }
   
    /* Ole Ul Islam */
}
