/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HomeController.java
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
package com.ey.ams.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
import com.ey.ams.model.UserSession;
import com.ey.ams.services.interfaces.IAmsService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRProperties;

import com.ey.ams.controller.BaseController;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController implements ApplicationContextAware{
	
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private ApplicationContext applicationContext;
    
    private IAmsService amsService; 
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	this.applicationContext = applicationContext;
    }
    
    public HUser getUser(){
	HUser user = getSessionService().getUserSession().getUser();
	return user;
    }
    
    public void setAmsService(IAmsService amsService) {
        this.amsService = amsService;
    }

    // ==================================================GET============================================================
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView init(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	
	if (validSession) {
	    return new ModelAndView("redirect:/home");
	}

	return new ModelAndView("redirect:/login");
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) throws Exception {

	boolean validSession = getSessionService().isSessionValid();

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("login", mm);
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	return new ModelAndView("home", mm);
    }
    
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public ModelAndView userLogout(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	getSessionService().invalidateSession(getSessionService().getUserSession().getSessionId());

	return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value = "/勘定科目メンテ", method = RequestMethod.GET)
    public ModelAndView accountmaintenance(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	
	amsService.acTablemntFormOpen();
	List<HActable> actabledata = amsService.loadAccountRegister();
	List<HFscrtables> fscr = amsService.getFSCodeRange();
	List<HDevtables> devlist = amsService.getDevCode();
	
	mm.addAttribute("acdata",actabledata);
	mm.addAttribute("fscr",fscr);
	mm.addAttribute("devlist",devlist);
	
	return new ModelAndView("accountmaintenance", mm);
    }
    
    @RequestMapping(value = "/振替伝票  入力/{FURIDEN_MODE}", method = RequestMethod.GET)
    public ModelAndView journalentry(@PathVariable int FURIDEN_MODE, HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("FURIDEN_MODE=" + FURIDEN_MODE);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	
	DateFormat Dateyear = new SimpleDateFormat("yyyy");
	Date date = new Date();
	String year= Dateyear.format(date);
	
	DateFormat DateMonth = new SimpleDateFormat("MM");
	String month=DateMonth.format(date);
	
	DateFormat DateDay = new SimpleDateFormat("dd");
	String day= DateDay.format(date);
	
	List<HDevtables> devlist = amsService.getDevCode();
	String devcode ="";
	if(devlist!=null)
	    devcode = devlist.get(0).getDevcode();
	List<HJournal> journalCopy = amsService.journalCopy(devcode);
	if(journalCopy.size()==0){
	    HJournal j =new HJournal();
	    j.setS_number(1);
	    j.setAbstract_name("");
	    journalCopy.add(j);
	}
	List<HDescription> description = amsService.descriptionCode(devcode);
	List<HProjectcode> prjlist = amsService.projectCode();
	List<HVendors> vendors = amsService.vendorCode();
	HDevtables selectedDev = amsService.BumonSansho(devcode);
	List<HDevcodeselect> devSel = amsService.bumonSelect(devcode);
	//List<HAcparameters> debTax = amsService.getDebitTax();
	List<HCtc> ctc = amsService.loadCtc();
	
	List<HJournal> journal = new ArrayList<HJournal>();
	if(FURIDEN_MODE==2){
	    int je_no = amsService.getJE_no();
	    journal = amsService.getJE_search_result(je_no);
	    mm.addAttribute("lineNum", journal.size());	
	    mm.addAttribute("searchback", "/振替伝票修正・印刷");	    
	} else{
	    mm.addAttribute("lineNum",0);
	}
		
	mm.addAttribute("devlist",devlist);
	mm.addAttribute("journalCopy",journalCopy);
	mm.addAttribute("description",description);
	mm.addAttribute("prjlist",prjlist);
	mm.addAttribute("vendors",vendors);	
	mm.addAttribute("selectedDev",selectedDev);
	mm.addAttribute("devSel",devSel);
	mm.addAttribute("debTax",ctc);
	mm.addAttribute("year",year);
	mm.addAttribute("month",month);
	mm.addAttribute("day",day);
	mm.addAttribute("FURIDEN_MODE", FURIDEN_MODE);
	mm.addAttribute("searchjournal", journal);
	
	return new ModelAndView("journalentry", mm);
    }
    
    @RequestMapping(value = "/仕訳帳", method = RequestMethod.GET)
    public ModelAndView journalcreate(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDevtables> devlist = amsService.getDevCode();
	
	mm.addAttribute("devlist",devlist);
		
	return new ModelAndView("journalcreate", mm);
    }
    
    @RequestMapping(value = "/振替伝票修正・印刷", method = RequestMethod.GET)
    public ModelAndView journalsearch(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDevtables> devlist = amsService.getDevCode();
	String devcode = "";
	if(devlist!=null)
	    devcode = devlist.get(0).getDevcode();
	List<HDevcodeselect> devSel = amsService.bumonSelect(devcode);
	
	mm.addAttribute("devlist",devlist);
	mm.addAttribute("devSel",devSel);
	
	return new ModelAndView("documentsearch", mm);
    }
    
    @RequestMapping(value = "/残高試算表", method = RequestMethod.GET)
    public ModelAndView trialbalance_home(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	amsService.acTablemntFormOpen();
	
	List<HDevtables> devlist = amsService.getDevCode();
		
	mm.addAttribute("devlist",devlist);
	
	return new ModelAndView("trialbalance", mm);
    }
    
    /* Opening Balance Maintenance Oli Ul Islam  */
    
    @RequestMapping(value ="/期首残高設定", method = RequestMethod.GET)
    public ModelAndView openingbalance(HttpServletRequest request) throws Exception {
    	boolean validSession = getSessionService().isSessionValid();
	
		
		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());
		
	
		if (!validSession) {
		    return new ModelAndView("redirect:/");
		}
		
		amsService.acTablemntFormOpen();
		List<HActable> actabledata = amsService.loadAccountRegister();
		
		mm.addAttribute("acdata",actabledata);
		
		return new ModelAndView("openingbalance", mm);
    }
    
    @RequestMapping(value="/摘要登録", method = RequestMethod.GET)
    public ModelAndView description(HttpServletRequest request) throws Exception {
    	boolean validSession = getSessionService().isSessionValid();
    	
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSeassion", validSession);
    	mm.addAttribute("currentuser", getUser());
    	
    	if(!validSession) {
    		return new ModelAndView("redirect:/");
    	}
    	
    	List<HDescription> description = amsService.loadDescription();
    	List<HDevtables> devcode = amsService.getDevCode();
    	List<HDevcodeselect> devSelect = amsService.devSelect();
    	List<HCtc> loadCtc = amsService.loadCtc();
    	
    	mm.addAttribute("devcode",devcode);
    	mm.addAttribute("description",description);
    	mm.addAttribute("devSelect",devSelect);
    	mm.addAttribute("loadCtc",loadCtc);
    	mm.addAttribute("lineNum",0);
    	
    	return new ModelAndView("description",mm);
    }
    
    @RequestMapping(value="/元帳", method= RequestMethod.GET)
    public ModelAndView creatingLedger(HttpServletRequest request) throws Exception {
    	boolean validSession = getSessionService().isSessionValid();
    	
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSession", validSession);
    	mm.addAttribute("currentuser",getUser());
    	
    	if(!validSession) {
    		return new ModelAndView("redirect:/");
    	}
    	
    	List<HDevtables> devcode = amsService.getDevCode();
    	List<HActable> acfrom = amsService.acFrom();
    	List<HProjectcode> prjcode = amsService.projectCode();
    	List<HDevcodeselect> devsel = amsService.devCodeSelect();
    	
    	mm.addAttribute("devcode",devcode);
    	mm.addAttribute("acfrom",acfrom);
    	mm.addAttribute("prjcode",prjcode);
    	mm.addAttribute("devsel",devsel);
    	
    	return new ModelAndView("creatingledger",mm);
    }
     /* Ole Ul Islam  */
 // ==================================================POST============================================================

    @RequestMapping(value = "/users/valideduser", method = RequestMethod.POST)
    public ModelAndView userVerify(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 2) {
	    logger.warn("Expecting atleast 2 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String uname = requestUriSplit[0];
	String pass = requestUriSplit[1];

	HUser user = new HUser();
	user.setUser_name(uname);
	user.setPassword(pass);

	HUser sysuser = amsService.getUserValidation(user);
	String user_name = "-1";
	if (sysuser != null) {
	    UserSession session = getSessionService().insertSession(request, sysuser);
	    user_name = sysuser.getUser_name();
	}

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", user_name);

	return new ModelAndView("result", mm);
    }
        
    @RequestMapping(value = "/accountdelete", method = RequestMethod.POST)
    public ModelAndView accountdelete(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	boolean deleted = amsService.deleteAccountRow(requestUri);	
	
	amsService.deleteAllFromActables();
	amsService.moveFromActablewkToActables();
	amsService.updateActable();
	
	ModelMap mm = new ModelMap();
	if(deleted)
	    mm.addAttribute("msg", "1");
	else
	    mm.addAttribute("msg", "-1");
	
	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/accountregister", method = RequestMethod.POST)
    public ModelAndView accountregister(HttpServletRequest request) throws Exception {
	UserSession session = getSessionService().getUserSession();

	Map<String, String[]> parameterMap = request.getParameterMap();
	
	String rowid[] = parameterMap.get("rowid");
	String changetype[] = parameterMap.get("changetype");
	String devcode[] = parameterMap.get("devcode");
	String accode[] = parameterMap.get("accode");
	String acname[] = parameterMap.get("acname");
	String acsubcode[] = parameterMap.get("acsubcode");
	String acsubname[] = parameterMap.get("acsubname");
	String fscr[] = parameterMap.get("fscr");
	
		
	for(int i=0;i<changetype.length;i++){
	    if(changetype[i].equals("1")){
		HActable ac = new HActable();
		if(!rowid[i].equals(""))
		    ac.setRowid(Integer.parseInt(rowid[i]));
		ac.setDevcode(devcode[i]);
		ac.setAccode(accode[i]);
		ac.setAcname(acname[i]);
		ac.setAcsubcode(acsubcode[i]);
		ac.setAcsubname(acsubname[i]);
		ac.setFscr(fscr[i]);
		ac.setKeycode(devcode[i] + accode[i] + acsubcode[i]);
		
		amsService.updateAccount(ac);
		
	    } else if(changetype[i].equals("2")){
		HActable ac = new HActable();
		ac.setDevcode(devcode[i]);
		ac.setAccode(accode[i]);
		ac.setAcname(acname[i]);
		ac.setAcsubcode(acsubcode[i]);
		ac.setAcsubname(acsubname[i]);
		ac.setFscr(fscr[i]);
		ac.setKeycode(devcode[i] + accode[i] + acsubcode[i]);
		
		amsService.registerAccount(ac);
	    }
	}
	
	amsService.deleteAllFromActables();
	amsService.moveFromActablewkToActables();
	amsService.updateActable();

	amsService.acTablemntFormOpen();
	List<HActable> actabledata = amsService.loadAccountRegister();
	List<HFscrtables> fscrlist = amsService.getFSCodeRange();
	
	ModelMap mm = new ModelMap();
	
	mm.addAttribute("acdata",actabledata);
	mm.addAttribute("fscr",fscrlist);
	
	
	return new ModelAndView("accountmaintenancesub", mm);
    }
    
    @RequestMapping(value = "/keycodevalidate", method = RequestMethod.POST)
    public ModelAndView keycodevalidate(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	boolean found = false;
	if(requestUri==null)
	   found = false;
	else{
	    String keycode = requestUri;
	    found = amsService.veryfyKeyCode(keycode);	
	}
	ModelMap mm = new ModelMap();
	if(found)
	    mm.addAttribute("msg", "-1");
	else
	    mm.addAttribute("msg", "1");
	
	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/bumonsansho", method = RequestMethod.POST)
    public ModelAndView bumonsansho(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	
	String devcode = requestUri;
	List<HDescription> description = amsService.descriptionCode(devcode);
	List<HProjectcode> prjlist = amsService.projectCode();
	List<HVendors> vendors = amsService.vendorCode();
	HDevtables selectedDev = amsService.BumonSansho(devcode);
	List<HDevcodeselect> devSel = amsService.bumonSelect(devcode);
	List<HAcparameters> debTax = amsService.getDebitTax();	
	
	ModelMap mm = new ModelMap();
	
	mm.addAttribute("description",description);
	mm.addAttribute("prjlist",prjlist);
	mm.addAttribute("vendors",vendors);
	mm.addAttribute("lineNum",0);
	mm.addAttribute("selectedDev",selectedDev.getDevname());
	mm.addAttribute("devSel",devSel);
	mm.addAttribute("debTax",debTax);
		
	return new ModelAndView("journalentrysub", mm);
    }
    
    @RequestMapping(value = "/getdetails", method = RequestMethod.POST)
    public ModelAndView getdetails(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	
	String key = requestUri;
	HDevcodeselect details = amsService.getDetails(key);
		
	ModelMap mm = new ModelMap();
	mm.addAttribute("details",details);
	
		
	return new ModelAndView("getdetails", mm);
    }
    
    @RequestMapping(value = "/tori_sansho", method = RequestMethod.POST)
    public ModelAndView TORI_SANSHO(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 2) {
	    logger.warn("Expecting atleast 2 arguments but received " + requestUriSplit.length);
	    return null;
	}
	
	String selVendor = requestUriSplit[0];
	int shori = Integer.parseInt(requestUriSplit[1]);
	HVendors vendor = amsService.TORI_SANSHO(selVendor, shori);
	
	ModelMap mm = new ModelMap();
	if(vendor!=null)
	    mm.addAttribute("msg",vendor.getVendorname());
	else
	    mm.addAttribute("msg","-1");
		
	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/prjdetails", method = RequestMethod.POST)
    public ModelAndView prjdetails(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	
	
	String prjcode = requestUri;
	HProjectcode prj = amsService.PRJCODE(prjcode);
	
	ModelMap mm = new ModelMap();
	if(prj!=null)
	    mm.addAttribute("msg",prj.getPrjname());
	else
	    mm.addAttribute("msg","-1");
		
	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/descdetails", method = RequestMethod.POST)
    public ModelAndView descdetails(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	
	
	String desid = requestUri;
	HDescription des = amsService.descriptionDetails(desid);
	
	ModelMap mm = new ModelMap();
	if(des!=null){
	    String value = des.getDraccode()+"~"+des.getDracname()+"~"+des.getDracsubcode()+"~"+des.getDracsubname()+"~"+des.getDramount()
	    +"~"+des.getCraccode()+"~"+des.getCracname()+"~"+des.getCracsubcode()+"~"+des.getCracsubname()+"~"+des.getCramount()
	    +"~"+des.getDesname()+"~"+des.getDrctax()+"~"+des.getCrctax();
	    
	    mm.addAttribute("msg",value);
	} else
	    mm.addAttribute("msg","-1");
		
	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/journalsave", method = RequestMethod.POST)
    public ModelAndView journalsave(HttpServletRequest request) throws Exception {

	Map<String, String[]> parameterMap = request.getParameterMap();
	String linenum  = parameterMap.get("linenum")[0];
	int l_number = 0;
	if(!linenum.equals(""))
	    l_number = Integer.parseInt(linenum);
	
	String txtYear = parameterMap.get("txtYear")[0];
	int yyyy  = 0;
	if(!txtYear.equals(""))
	yyyy	= Integer.parseInt(txtYear);
	
	String txtMonth = parameterMap.get("txtMonth")[0];
	int mm =0;
	if(!txtMonth.equals(""))
	mm = Integer.parseInt(txtMonth);
	
	String txtDay = parameterMap.get("txtDay")[0];
	int dd =0;
	if(!txtDay.equals(""))
	dd = Integer.parseInt(txtDay);
	
	String txtJournalNumber = parameterMap.get("txtJournalNumber")[0];
	String FM = parameterMap.get("FURIDEN_MODE")[0];
	int FURIDEN_MODE = 1;
	if(!FM.equals("")){
	    FURIDEN_MODE = Integer.parseInt(FM);
	}
	
	int je_number = 0; 	
	if(!txtJournalNumber.equals(""))
	je_number = Integer.parseInt(txtJournalNumber);
	
	String txtNo = parameterMap.get("txtNo")[0];
	int s_number =0;
	if(!txtNo.equals(""))
	s_number = Integer.parseInt(txtNo);
		
	String cmbDebCode = parameterMap.get("cmbDebCode")[0];
	String txtDevName = parameterMap.get("txtDevName")[0];
	String cmbSlipCopy = parameterMap.get("cmbSlipCopy")[0];
	
	String txtLabelNo[] = parameterMap.get("txtLabelNo");
	String cmbDebitAccount[] = parameterMap.get("cmbDebitAccount");
	String txtDebitAccode[] = parameterMap.get("txtDebitAccode");
	
	String txtDebitAccount[] = parameterMap.get("txtDebitAccount");
	String txtDevitAmount[] = parameterMap.get("txtDevitAmount");
	String cmbCreditAccount[] = parameterMap.get("cmbCreditAccount");
	String txtCreditAccode[] = parameterMap.get("txtCreditAccode");
	String txtCreditAccount[] = parameterMap.get("txtCreditAccount");
	String txtCreditAmount[] = parameterMap.get("txtCreditAmount");
	String cmbVendor[] = parameterMap.get("cmbVendor");
	String txtVendor[] = parameterMap.get("txtVendor");
	String txtDebitAccountSub[] = parameterMap.get("txtDebitAccountSub");
	String txtDebitAccountSub2[] = parameterMap.get("txtDebitAccountSub2");
	String cmbDebitTax[] = parameterMap.get("cmbDebitTax");
	String txtCreditAccountSub[] = parameterMap.get("txtCreditAccountSub");
	String txtCreditAccountSub2[] = parameterMap.get("txtCreditAccountSub2");
	String cmbDebitTax2[] = parameterMap.get("cmbDebitTax2");
	String cmbProject[] = parameterMap.get("cmbProject");
	String txtProject[] = parameterMap.get("txtProject");
	String cmbDescription[] = parameterMap.get("cmbDescription");
	String txtDescription[] = parameterMap.get("txtDescription");
	
	List<HJournal> journals = new ArrayList<HJournal>();
	for(int l=0;l<l_number;l++){
	    if(!txtLabelNo[l].equals("0")){
		if((cmbVendor[l].equals("")) && (!txtVendor[l].equals(""))){
		    //Add the Vendor name in DB
		    HVendors vendor = new HVendors();
		    vendor.setVendorcode(txtVendor[l]);
		    vendor.setVendorname(txtVendor[l]);
		    
		    vendor = amsService.addVendor(vendor);
		    
		    cmbVendor[l] = vendor.getVendorcode();
		    
		}
		
		if((cmbProject[l].equals("")) && (!txtProject[l].equals(""))){
		    //Add the Vendor name in DB
		    HProjectcode proj = new HProjectcode();
		    proj.setPrjcode(txtProject[l]);
		    proj.setPrjname(txtProject[l]);
		    
		    proj = amsService.addProject(proj);
		    
		    cmbProject[l] = proj.getPrjcode();
		    
		}
		
		if((cmbDescription[l].equals("")) && (!txtDescription[l].equals(""))){
		    //Add the Vendor name in DB
		    HDescription desc = new HDescription();
		    desc.setDevcode(cmbDebCode);
		    desc.setDevname(txtDevName);
		    desc.setDramount(0d);
		    desc.setCramount(0d);
		    desc.setDescode(txtDescription[l]);
		    desc.setDesname(txtDescription[l]);
		    
		    desc = amsService.addDescription(desc);
		    
		    cmbDescription[l] = desc.getDescode();
		    
		}
		
		HJournal j = new HJournal();
        	j.setJe_number(je_number);
        	j.setS_number(s_number);
        	int line = 0;
        	if(!txtLabelNo[l].equals(""))
        	    line = Integer.parseInt(txtLabelNo[l]);
        	j.setL_number(line);
        	j.setDevcode(cmbDebCode);
        	j.setDevname(txtDevName);
        	j.setYyyy(yyyy);
        	j.setMm(mm);
        	j.setDd(dd);
        	j.setDraccode(txtDebitAccode[l]);
        	j.setDracname(txtDebitAccount[l]);
        	
        	Double dramount=0.0D;
		if(!txtDevitAmount[l].equals(""))
		    dramount=Double.parseDouble(txtDevitAmount[l]);
        	j.setDramount(dramount);
        	j.setCraccode(txtCreditAccode[l]);
        	j.setCracname(txtCreditAccount[l]);
        	
        	Double cramount = 0.0D;
		if(!txtCreditAmount[l].equals(""))
		    cramount = Double.parseDouble(txtCreditAmount[l]);
		j.setCramount(cramount);
		j.setVendorcode(cmbVendor[l]);
		j.setVendorname(txtVendor[l]);
		j.setDracsubcode(txtDebitAccountSub[l]);
		j.setDracsubname(txtDebitAccountSub2[l]);
		j.setDrctax(cmbDebitTax[l]);
		j.setCracsubcode(txtCreditAccountSub[l]);
		j.setCracsubname(txtCreditAccountSub2[l]);
		j.setYyyy(yyyy);
		j.setMm(mm);
		j.setDd(dd);
		j.setCrctax(cmbDebitTax2[l]);
		j.setPrjcode(cmbProject[l]);
		j.setPrjname(txtProject[l]);
		j.setDescode(cmbDescription[l]);
		j.setDesname(txtDescription[l]);
		
		journals.add(j);
	    }
	}
	
	boolean fo = amsService.deletejewrk();
	
	boolean fs = amsService.SaveJournal(journals);
	
	String msg = "";
	if(FURIDEN_MODE==1)
	    msg = amsService.FURIDEN_TO_SIWAKE(1,FURIDEN_MODE, journals.get(0));
	else
	    msg = amsService.FURIDEN_TO_SIWAKE(2,FURIDEN_MODE, journals.get(0));
	
	
		
	ModelMap mmap = new ModelMap();
	if(msg.contains("伝票番号"))
	    mmap.addAttribute("msg",msg);
	else if(!fs)
	    mmap.addAttribute("msg","-1");
	else
	    mmap.addAttribute("msg",msg);
	
	return new ModelAndView("result", mmap);
    }
    
    @RequestMapping(value = "/journalcreate", method = RequestMethod.POST)
    public ModelAndView createjournal(HttpServletRequest request) throws Exception {

	Map<String, String[]> parameterMap = request.getParameterMap();
		
	String cmbDepartment = parameterMap.get("cmbDepartment")[0];
	String cmbRearrangement = parameterMap.get("cmbRearrangement")[0];
	String txtMonth = parameterMap.get("txtMonth")[0];
	String txtMonth2 = parameterMap.get("txtMonth2")[0];
	String txtFromDay = parameterMap.get("txtFromDay")[0];
	String txtUntilday = parameterMap.get("txtUntilday")[0];
	String txtDocumentRangeFrom = parameterMap.get("txtDocumentRangeFrom")[0];
	String txtDocumentRangeTo = parameterMap.get("txtDocumentRangeTo")[0];
	
	Map param = new HashMap();
	param.put("cmbDepartment", cmbDepartment);
	param.put("cmbRearrangement", cmbRearrangement);
	param.put("txtMonth", txtMonth);
	param.put("txtMonth2", txtMonth2);
	param.put("txtFromDay", txtFromDay);
	param.put("txtUntilday", txtUntilday);
	param.put("txtDocumentRangeFrom", txtDocumentRangeFrom);
	param.put("txtDocumentRangeTo", txtDocumentRangeTo);
	
	String msg = amsService.journalCreate(param);
	
	ModelMap mmap = new ModelMap();
	
	mmap.addAttribute("msg",msg);
	
	return new ModelAndView("result", mmap);
    }
    
    
    @RequestMapping(value = "/documentsearch", method = RequestMethod.POST)
    public ModelAndView documentsearch(HttpServletRequest request) throws Exception {

	Map<String, String[]> parameterMap = request.getParameterMap();
		
	String cmbSelectDepartment = parameterMap.get("cmbSelectDepartment")[0];
	String txtJournalFrom = parameterMap.get("txtJournalFrom")[0];
	String txtJournalTo = parameterMap.get("txtJournalTo")[0];
	String txtYearFrom = parameterMap.get("txtYearFrom")[0];
	String txtMonthFrom = parameterMap.get("txtMonthFrom")[0];
	String txtDayFrom = parameterMap.get("txtDayFrom")[0];
	String txtYearTo = parameterMap.get("txtYearTo")[0];
	String txtMonthTo = parameterMap.get("txtMonthTo")[0];	
	String txtDayTo = parameterMap.get("txtDayTo")[0];
	String txtAmountRangeFrom = parameterMap.get("txtAmountRangeFrom")[0];
	String txtAmountrangeTo = parameterMap.get("txtAmountrangeTo")[0];
	String txtMatchDescription = parameterMap.get("txtMatchDescription")[0];
	String txtMatchVendor = parameterMap.get("txtMatchVendor")[0];
	String cmbAccountSelect1 = parameterMap.get("cmbAccountSelect1")[0];
	String cmbAccountSelect2 = parameterMap.get("cmbAccountSelect2")[0];
	String cmbAccountSelect3 = parameterMap.get("cmbAccountSelect3")[0];
	
	Map param = new HashMap();
	param.put("cmbSelectDepartment", cmbSelectDepartment);
	param.put("txtJournalFrom", txtJournalFrom);
	param.put("txtJournalTo", txtJournalTo);
	param.put("txtYearFrom", txtYearFrom);
	param.put("txtMonthFrom", txtMonthFrom);
	param.put("txtDayFrom", txtDayFrom);
	param.put("txtYearTo", txtYearTo);
	param.put("txtMonthTo", txtMonthTo);
	param.put("txtDayTo", txtDayTo);
	param.put("txtAmountRangeFrom", txtAmountRangeFrom);
	param.put("txtAmountrangeTo", txtAmountrangeTo);
	param.put("txtMatchDescription", txtMatchDescription);
	param.put("txtMatchVendor", txtMatchVendor);	
	param.put("cmbAccountSelect1", cmbAccountSelect1);
	param.put("cmbAccountSelect2", cmbAccountSelect2);
	param.put("cmbAccountSelect3", cmbAccountSelect3);
	
	String msg = amsService.FURIDEN_KENSAKU2(1,param);
	
	ModelMap mmap = new ModelMap();
	
	mmap.addAttribute("msg",msg);
	
	return new ModelAndView("result", mmap);
    }
    
    @RequestMapping(value = "/trialbalance", method = RequestMethod.POST)
    public ModelAndView trialbalance(HttpServletRequest request) throws Exception {

	Map<String, String[]> parameterMap = request.getParameterMap();
		
	String cmbDepartment = parameterMap.get("cmbDepartment")[0];
	String txtMonth = parameterMap.get("txtMonth")[0];
	String txtMonth2 = parameterMap.get("txtMonth2")[0];
	String txtFromDay = parameterMap.get("txtFromDay")[0];
	String txtUntilday = parameterMap.get("txtUntilday")[0];
	int SHORI = Integer.parseInt(parameterMap.get("SHORI")[0]);
	
	
	Map param = new HashMap();
	param.put("cmbDepartment", cmbDepartment);
	param.put("txtMonth", txtMonth);
	param.put("txtMonth2", txtMonth2);
	param.put("txtFromDay", txtFromDay);
	param.put("txtUntilday", txtUntilday);
	
	String msg = "";
	
	if(SHORI==5){
	    msg = amsService.budget(param);
	} else if(SHORI==4){
	    msg = amsService.projectExpenses(param);
	} else{
	    msg = amsService.TB_Q_CONTROL(SHORI,param);
	}
	
	ModelMap mmap = new ModelMap();
	
	mmap.addAttribute("msg",msg);
	
	return new ModelAndView("result", mmap);
    }
    
    /* Opening Balance Maintenance Ole Ul Islam  */
	
    @RequestMapping(value = "/updatebgbalance", method = RequestMethod.POST)
    public ModelAndView updatebgbalance(HttpServletRequest request) throws Exception {
    	
    	Map<String, String[]> parameterMap = request.getParameterMap();
    	
    	String changetype[] = parameterMap.get("changetype");
    	String keycode[] 	= parameterMap.get("keycode");
    	String bgbalance[] 	= parameterMap.get("bgbalance");
    	
    	for(int i=0;i<changetype.length;i++){
    	    if(changetype[i].equals("1")){
    		HActable ac = new HActable();
    		ac.setKeycode(keycode[i]);
    		ac.setBgbalance(Double.parseDouble(bgbalance[i]));
    		
    		amsService.updatebgbalance(ac);
    	    } 
    	}
    	ModelMap mm = new ModelMap();
		
	    mm.addAttribute("msg", "1");
	    return new ModelAndView("result", mm);
    	
    }
    @RequestMapping(value = "/insertDescription", method = RequestMethod.POST)
    public ModelAndView insertDescription(HttpServletRequest request) throws Exception {
    	
    	String requestUri = getStringFromHttpRequest(request);
    	logger.info("requestUri=" + requestUri);

    	String[] requestUriSplit = requestUri.split("~");

    	if (requestUriSplit.length < 16) {
    	    logger.warn("Expecting atleast 16 arguments but received " + requestUriSplit.length);
    	    return null;
    	}
    	
    	String devcode = requestUriSplit[0];
    	String devname = requestUriSplit[1];
    	String draccode = requestUriSplit[2];
    	String dracname = requestUriSplit[3];
    	String dracsubcode = requestUriSplit[4];
    	String dracsubname = requestUriSplit[5];
    	String craccode = requestUriSplit[6];
    	String cracname = requestUriSplit[7];
    	String cracsubcode = requestUriSplit[8];
    	String cracsubname = requestUriSplit[9];
    	String dramount = requestUriSplit[10];
    	String cramount = requestUriSplit[11];
    	String descode = requestUriSplit[12];
    	String desname = requestUriSplit[13];
    	String drctax = requestUriSplit[14];
    	String crctax = requestUriSplit[15]; 
    	
    	HDescription obj = new HDescription();
    	
    	obj.setDevcode(devcode);
    	obj.setDevname(devname);
    	obj.setDraccode(draccode);
    	obj.setDracname(dracname);
    	obj.setDracsubcode(dracsubcode);
    	obj.setDracsubname(dracsubname);
    	obj.setCraccode(craccode);
    	obj.setCracname(cracname);
    	obj.setCracsubcode(cracsubcode);
    	obj.setCracsubname(cracsubname);
    	obj.setDramount(Double.parseDouble(dramount));
    	obj.setCramount(Double.parseDouble(cramount));
    	obj.setDescode(descode);
    	obj.setDesname(desname);
    	obj.setDrctax(drctax);
    	obj.setCrctax(crctax);
    	
    	boolean fo = amsService.insertDescription(obj);
    	
    	ModelMap mm = new ModelMap();
		if(fo)
		mm.addAttribute("msg", "1");
		else 
			mm.addAttribute("msg", "-1");
		
		return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/updateDescription", method = RequestMethod.POST)
    public ModelAndView updateDescription(HttpServletRequest request) throws Exception {
    	
    	String requestUri = getStringFromHttpRequest(request);
    	logger.info("requestUri=" + requestUri);

    	String[] requestUriSplit = requestUri.split("~");

    	if (requestUriSplit.length < 17) {
    	    logger.warn("Expecting atleast 17 arguments but received " + requestUriSplit.length);
    	    return null;
    	}
    	
    	String desid = requestUriSplit[0];
    	String devcode = requestUriSplit[1];
    	String devname = requestUriSplit[2];
    	String draccode = requestUriSplit[3];
    	String dracname = requestUriSplit[4];
    	String dracsubcode = requestUriSplit[5];
    	String dracsubname = requestUriSplit[6];
    	String craccode = requestUriSplit[7];
    	String cracname = requestUriSplit[8];
    	String cracsubcode = requestUriSplit[9];
    	String cracsubname = requestUriSplit[10];
    	String dramount = requestUriSplit[11];
    	String cramount = requestUriSplit[12];
    	String descode = requestUriSplit[13];
    	String desname = requestUriSplit[14];
    	String drctax = requestUriSplit[15];
    	String crctax = requestUriSplit[16]; 
    	
    	HDescription obj = new HDescription();
    	
    	obj.setDesid(Integer.parseInt(desid));
    	obj.setDevcode(devcode);
    	obj.setDevname(devname);
    	obj.setDraccode(draccode);
    	obj.setDracname(dracname);
    	obj.setDracsubcode(dracsubcode);
    	obj.setDracsubname(dracsubname);
    	obj.setCraccode(craccode);
    	obj.setCracname(cracname);
    	obj.setCracsubcode(cracsubcode);
    	obj.setCracsubname(cracsubname);
    	obj.setDramount(Double.parseDouble(dramount));
    	obj.setCramount(Double.parseDouble(cramount));
    	obj.setDescode(descode);
    	obj.setDesname(desname);
    	obj.setDrctax(drctax);
    	obj.setCrctax(crctax);
    	
    	boolean fo = amsService.updateDescription(obj);
    	
    	ModelMap mm = new ModelMap();
		if(fo)
		mm.addAttribute("msg", "1");
		else 
			mm.addAttribute("msg", "-1");
		
		return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value="/deleteDescription", method=RequestMethod.POST)
    public ModelAndView deleteDescription(HttpServletRequest request) throws Exception {
    	String requestUri = getStringFromHttpRequest(request);
    	
    	logger.info(requestUri);
    	
    	String desid = requestUri;
     	
    	HDescription obj = new HDescription();

    	
    	obj.setDesid(Integer.parseInt(requestUri));
    	
    	
    	boolean delete = amsService.deleteDescription(obj);
    	
    	ModelMap mm = new ModelMap();
    	if(delete) {
    		mm.addAttribute("msg",1);
    	} else {
    		mm.addAttribute("msg",-1);
    	}
    	return new ModelAndView("result",mm);
    	
    }
    
    @RequestMapping(value = "/getDevname", method = RequestMethod.POST)
    public ModelAndView getDevname(HttpServletRequest request) throws Exception {

		String requestUri = getStringFromHttpRequest(request);
	
		logger.info("requestUri=" + requestUri);
		
		String devname = requestUri;

		HDevtables description = amsService.getDevname(devname);
		
		ModelMap mm = new ModelMap();
		if(description!=null)
		    mm.addAttribute("msg",description.getDevname());
		else
		    mm.addAttribute("msg","-1");
			
		return new ModelAndView("result", mm);
    }
    @RequestMapping(value ="/debitDetails", method = RequestMethod.POST)
    public ModelAndView debitDetails(HttpServletRequest request) throws Exception {
    	String requestUri = getStringFromHttpRequest(request);
    	
    	logger.info("requestUri " +requestUri);
    	
    	
    	String key = requestUri;
    	HDevcodeselect devcode = amsService.debitDetails(key);
    	
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("devcode",devcode);
    	
    	return new ModelAndView("debitDetails",mm);
    }
    @RequestMapping(value = "/createLedger", method = RequestMethod.POST)
    public ModelAndView createLedger(HttpServletRequest request) throws Exception {

		Map<String, String[]> parameterMap = request.getParameterMap();
			
		String cmbBumon 	= parameterMap.get("cmbBumon")[0];
		String cmbNarabi 	= parameterMap.get("cmbNarabi")[0];
		String cmbPrjoken 	= parameterMap.get("cmbPrjoken")[0];
		String cmbAcFrom 	= parameterMap.get("cmbAcFrom")[0];
		String cmbAcTo 		= parameterMap.get("cmbAcTo")[0];
		String cmbPrjCode 	= parameterMap.get("cmbPrjCode")[0];
		String cmbAcSel1 	= parameterMap.get("cmbAcSel1")[0];
		String cmbAcSel2 	= parameterMap.get("cmbAcSel2")[0];
		String cmbAcSel3 	= parameterMap.get("cmbAcSel3")[0];
		String cmbAcSel4 	= parameterMap.get("cmbAcSel4")[0];
		String cmbAcSel5 	= parameterMap.get("cmbAcSel5")[0];
		String cmbAcSel6 	= parameterMap.get("cmbAcSel6")[0];
		String cmbSaiSel 	= parameterMap.get("cmbSaiSel")[0];
		String txtTukiFrom 	= parameterMap.get("txtTukiFrom")[0];
		String txtHiFrom 	= parameterMap.get("txtHiFrom")[0];
		String txtTukiTo 	= parameterMap.get("txtTukiTo")[0];
		String txtHiTo 		= parameterMap.get("txtHiTo")[0];
		int SHORI 		= Integer.parseInt(parameterMap.get("SHORI")[0]);
		
		
		Map param = new HashMap();
		
		param.put("cmbBumon", cmbBumon);
		param.put("cmbNarabi", cmbNarabi);
		param.put("cmbPrjoken", cmbPrjoken);
		param.put("cmbAcFrom", cmbAcFrom);
		param.put("cmbAcTo", cmbAcTo);
		param.put("cmbPrjCode", cmbPrjCode);
		param.put("cmbAcSel1", cmbAcSel1);
		param.put("cmbAcSel2", cmbAcSel2);
		param.put("cmbAcSel3", cmbAcSel3);
		param.put("cmbAcSel4", cmbAcSel4);
		param.put("cmbAcSel5", cmbAcSel5);
		param.put("cmbAcSel6", cmbAcSel6);
		param.put("cmbSaiSel", cmbSaiSel);
		param.put("txtTukiFrom", txtTukiFrom);
		param.put("txtHiFrom", txtHiFrom);
		param.put("txtTukiTo", txtTukiTo);
		param.put("txtHiTo", txtHiTo);
		
		String msg = "";
		
		//HBaseData bd = new HBaseData();
		
		//msg = amsService.GENKIN_SUITO(param);
		
		if(SHORI == 1) {
			msg = amsService.GL_Q_CON2(param);
		} else if(SHORI == 2) {
			msg = amsService.GENKIN_SUITO(param);
		} else if(SHORI == 3) {
			msg = amsService.GL_Q_CONTROL(param);
		} else {
			msg = amsService.GENKIN_CHECK(param);
		}
		
		ModelMap mmap = new ModelMap();
		
		mmap.addAttribute("msg",msg);
		
		return new ModelAndView("result", mmap);
    }
    
    /* Ole Ul Islam  */
	
  // ==================================================REPORT==========================================================
    @RequestMapping(value = "/rptview/{rptname}/{param}", method = RequestMethod.GET)
    public ModelAndView showReport(@PathVariable("rptname") String rptname,
	    @PathVariable("param") String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("rptname=" + rptname + ", param=" + param);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	
	mm.addAttribute("rpturl", rptname + "/" + param);
	if(rptname.equals("rptACListPrint")){
	    mm.addAttribute("back", "/勘定科目メンテ");
	} else if (rptname.equals("rptTransferslip")) {
	    mm.addAttribute("back", "/振替伝票  入力/1");
	} else if (rptname.equals("rptJournal") || rptname.equals("rptJournal2")) {
	    mm.addAttribute("back", "/仕訳帳");
	} else if (rptname.equals("rptTrialBalanceAllDepartments") || rptname.equals("rptTrialBalance") || rptname.equals("rptCustomerTrialBalance") || rptname.equals("rptTrialbalancesubjectsby") || rptname.equals("rptProjectBudget") || rptname.equals("rptProjectBudget2") || rptname.equals("rptProjectTrialBalance")) {
	    mm.addAttribute("back", "/残高試算表");
	} else if  (rptname.equals("subledger") || rptname.equals("subledger2") || rptname.equals("rptCashAccountingBook") || rptname.equals("rptCashCheck")) {
	    mm.addAttribute("back", "/元帳");
	} 
	/*else if (rptname.equals("CreditApplicationPrint")
		|| rptname.equals("BillCreditApplicationIntermediateInterestPayments")
		|| rptname.equals("BillApplicationFormList3") || rptname.equals("BillApplicationFormList2")
		|| rptname.equals("BillApplicationFormList") || rptname.equals("CreditorManagementBook3")
		|| rptname.equals("CreditorManagementBook") || rptname.equals("CreditorManagementBook2")
		|| rptname.equals("ApprovalApplicationFormAttachment")
		|| rptname.equals("ApprovalApplicationFormAttachment2")
		|| rptname.equals("ApprovalApplicationFormAttachment3") || rptname.equals("TransferSlip")
		|| rptname.equals("LoanAgreementDeed")) {
	    String[] paramUriSplit = param.split("~");
	    mm.addAttribute("back", "NotesApplicationFormPrint/" + paramUriSplit[0]);
	}*/

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	return new ModelAndView("reportView", mm);
    }
    
    @RequestMapping(value = "/rptACListPrint/{devcode}", method = RequestMethod.GET)
    public ModelAndView rptACListPrint(@PathVariable String devcode, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("devcode=" + devcode);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<RACListPrint> actable = amsService.getActableReportData(devcode);
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(actable, false);

	parameters.put("datasource", JRdataSource);
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportACListPrint", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptTransferslip/{devcode}", method = RequestMethod.GET)
    public ModelAndView rptTransferslip(@PathVariable String devcode, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("devcode=" + devcode);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<RTransferSlip> transferSlip = amsService.getTransferSlip();
	List<RTransferSlipSub> transferSlipSub = new ArrayList<RTransferSlipSub>();

	for (RTransferSlip sbd : transferSlip) {
	    List<RTransferSlipSub> tssub = amsService.getTransferSlipSub(sbd.getDevcode());
	    for (RTransferSlipSub sub : tssub) {
		transferSlipSub.add(sub);
	    }
	}
	
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(transferSlip,false);
	JRDataSource JRCustomSubReportdataSource = new JRBeanCollectionDataSource(transferSlipSub,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("jaspercustomsubreportdatasource", JRCustomSubReportdataSource);
	
	
	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportTransferSlip", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptJournal/{param}", method = RequestMethod.GET)
    public ModelAndView rptJournal(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	List<RJournal> journal = amsService.getJournalReport(1, params[0], params[1], params[2], params[3], params[4]);
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(journal,false);
		
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		
	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportJournal", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptJournal2/{param}", method = RequestMethod.GET)
    public ModelAndView rptJournal2(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	List<RJournal> journal = amsService.getJournalReport(2, params[0], params[1], params[2], params[3], params[4]);
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(journal,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportJournal2", parameters);

    }// generatePdfReport
    

    @RequestMapping(value = "/rptTrialBalanceAllDepartments/{param}", method = RequestMethod.GET)
    public ModelAndView rptTrialBalanceAllDepartments(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();
	
	List<HTrailBalanceTemp> trialbalance = amsService.getTrailBalanceTemp();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(trialbalance,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportTrialBalanceAllDepartments", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptTrialBalance/{param}", method = RequestMethod.GET)
    public ModelAndView rptTrialBalance(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();
	
	List<HTrailBalanceTemp> trialbalance = amsService.getTrailBalanceTemp();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(trialbalance,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has been declared in the jasper-views.xml file
	return new ModelAndView("pdfReportTrialBalance", parameters);

    }// generatePdfReport
    
    
    @RequestMapping(value = "/rptCustomerTrialBalance/{param}", method = RequestMethod.GET)
    public ModelAndView rptCustomerTrialBalance(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();
	
	List<HTrailBalanceTemp> trialbalance = amsService.getCustomerTrialBalance();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(trialbalance,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportCustomerTrialBalance", parameters);

    }// generatePdfReport
    
    
    @RequestMapping(value = "/rptTrialbalancesubjectsby/{param}", method = RequestMethod.GET)
    public ModelAndView rptTrialbalancesubjectsby(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();
	
	List<HTrailBalanceTemp> trialbalance = amsService.getTrialbalancesubjectsby();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(trialbalance,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportTrialbalancesubjectsby", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptProjectBudget/{param}", method = RequestMethod.GET)
    public ModelAndView rptProjectBudget(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	/*String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();*/
	
	List<HProjectBudget> projectBudget = amsService.getProjectBudget();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(projectBudget,false);
	
	parameters.put("datasource", JRdataSource);
	//parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	//parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportProjectBudget", parameters);

    }// generatePdfReport
    
    @RequestMapping(value = "/rptProjectBudget2/{param}", method = RequestMethod.GET)
    public ModelAndView rptProjectBudget2(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	/*String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();*/
	
	List<HProjectBudget> projectBudget = amsService.getProjectBudget();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(projectBudget,false);
	
	parameters.put("datasource", JRdataSource);
	//parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	//parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has ben declared in the jasper-views.xml file
	return new ModelAndView("pdfReportProjectBudget2"
		+ "", parameters);

    }// generatePdfReport
       
    
    @RequestMapping(value = "/rptProjectTrialBalance/{param}", method = RequestMethod.GET)
    public ModelAndView rptProjectTrialBalance(@PathVariable String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	String params[] = param.split("~");
	
	/*String rparam="";
	HReportparameters rptPara = amsService.getReportparametersData();
	rparam = rptPara.getTrial_balance_cond();*/
	
	List<HProjectTrialBalance> projectBudget = amsService.getProjectTrialBalance();
		
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(projectBudget,false);
	
	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	//parameters.put("reportParameter", rparam);
		
	
	// pdfReport bean has been declared in the jasper-views.xml file
	return new ModelAndView("pdfReportProjectTrialBalance"
		+ "", parameters);

    }// generatePdfReport
 
    /*pdfReport Ole Ul Islam */
    
    @RequestMapping(value="/rptCashAccountingBook/{param}", method = RequestMethod.GET)
    public ModelAndView rptCashAccountingBook(@PathVariable String param, HttpServletRequest request) throws JRException {
    	boolean validSession = getSessionService().isSessionValid();
    	logger.info("param=" + param);
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSession", validSession);

    	if (!validSession) {
    	    return new ModelAndView("redirect:/");
    	}
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String params[] = param.split("~");
    	
    	String rparam="";
    	
    	HReportparameters repParameters = amsService.selectReportParameters();
    	rparam = repParameters.getLedger_extrantion_cond();
    	
    	List<HLedger> ledger = amsService.getLedger();
    	JRDataSource jRDatasource = new JRBeanCollectionDataSource(ledger,false);
    	
    	parameters.put("datasource", jRDatasource);
    	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
    	parameters.put("reportParameter", rparam);
    	
    	return new ModelAndView("pdfCashAccountingBook",parameters);
    }
    
    @RequestMapping(value="/subledger/{param}", method = RequestMethod.GET)
    public ModelAndView subledger(@PathVariable String param, HttpServletRequest request) throws JRException {
    	boolean validSession = getSessionService().isSessionValid();
    	logger.info("param=" + param);
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSession", validSession);

    	if (!validSession) {
    	    return new ModelAndView("redirect:/");
    	}
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String params[] = param.split("~");
    	
    	String rparam="";
    	
    	HReportparameters repParameters = amsService.selectReportParameters();
    	
    	rparam = repParameters.getLedger_extrantion_cond();
    	
    	List<RLedger> ledger = amsService.getRSubledger();
    	List<HSubledgerSubreport> ledgersub = new ArrayList<HSubledgerSubreport>();
    	
    	for(RLedger hl : ledger) {
    	    List<HSubledgerSubreport> hlsub = amsService.getSubledgerSubreport(hl.getAccode());
    	    for(HSubledgerSubreport sub : hlsub) {
    		ledgersub.add(sub);
    	    }
    	}
    	    	
    	JRDataSource jRDatasource = new JRBeanCollectionDataSource(ledger,false);
    	JRDataSource jRDatasourceSub = new JRBeanCollectionDataSource(ledgersub,false);
    	
    	
    	parameters.put("datasource", jRDatasource);
    	parameters.put("jaspercustomsubreportdatasource", jRDatasourceSub);
    	parameters.put("title", param);
    	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
    	parameters.put("reportParameter", rparam);
    	
    	return new ModelAndView("subledger",parameters);
    }
    
    @RequestMapping(value="/subledger2/{param}", method = RequestMethod.GET)
    public ModelAndView subledger2(@PathVariable String param, HttpServletRequest request) throws JRException {
    	boolean validSession = getSessionService().isSessionValid();
    	logger.info("param=" + param);
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSession", validSession);

    	if (!validSession) {
    	    return new ModelAndView("redirect:/");
    	}
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String params[] = param.split("~");
    	
    	String rparam="";
    	
    	HReportparameters repParameters = amsService.selectReportParameters();
    	
    	rparam = repParameters.getLedger_extrantion_cond();
    	
    	List<RLedger> ledger = amsService.getRSubledger();
    	List<HSubledgerSubreport> ledgersub = new ArrayList<HSubledgerSubreport>();
    	
    	for(RLedger hl : ledger) {
    	    List<HSubledgerSubreport> hlsub = amsService.getSubledgerSubreport(hl.getAccode());
    	    for(HSubledgerSubreport sub : hlsub) {
    		ledgersub.add(sub);
    	    }
    	}
    	    	
    	JRDataSource jRDatasource = new JRBeanCollectionDataSource(ledger,false);
    	JRDataSource jRDatasourceSub = new JRBeanCollectionDataSource(ledgersub,false);
    	
    	
    	parameters.put("datasource", jRDatasource);
    	parameters.put("jaspercustomsubreportdatasource", jRDatasourceSub);
    	parameters.put("title", param);
    	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
    	parameters.put("reportParameter", rparam);
    	
    	return new ModelAndView("subledger2",parameters);
    }
    
    @RequestMapping(value="/rptCashCheck/{param}", method = RequestMethod.GET)
    public ModelAndView rptCashCheck(@PathVariable String param, HttpServletRequest request) throws JRException {
    	boolean validSession = getSessionService().isSessionValid();
    	logger.info("param=" + param);
    	ModelMap mm = new ModelMap();
    	mm.addAttribute("validSession", validSession);

    	if (!validSession) {
    	    return new ModelAndView("redirect:/");
    	}
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
    	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String params[] = param.split("~");
    	
    	//String rparam="";
    	
    	//HReportparameters repParameters = amsService.selectReportParameters();
    	
    	//rparam = repParameters.getLedger_extrantion_cond();
    	String devcode = "";
    	devcode = params[0];
    	    	
    	List<HDevtables> dev = amsService.getDevCodeById(devcode);
    	List<RCashCheck> cashcheck = new ArrayList<RCashCheck>();
    	
    	for(HDevtables hl : dev) {
    	    List<RCashCheck> hlsub = amsService.getCashCheck(hl.getDevcode());
    	    for(RCashCheck sub : hlsub) {
    		cashcheck.add(sub);
    	    }
    	}
    	    	
    	JRDataSource jRDatasource = new JRBeanCollectionDataSource(dev,false);
    	JRDataSource jRDatasourceSub = new JRBeanCollectionDataSource(cashcheck,false);
    	
    	
    	parameters.put("datasource", jRDatasource);
    	parameters.put("jaspercustomsubreportdatasource", jRDatasourceSub);
    	parameters.put("devcode", devcode);
    	//parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
    	//parameters.put("reportParameter", rparam);
    	
    	return new ModelAndView("cashCheck",parameters);
    }

}

