/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HJournal.java
* --------------------
* Created on Feb 2, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 2, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HJournal;
import com.ey.ams.model.RJournal;

public class RJournalMapper extends BaseMapper implements RowMapper<RJournal> {

    @Override
    public RJournal mapRow(ResultSet rs, int rowNum) throws SQLException {
	RJournal ob = new RJournal();
	int colindex = 0;
	
	colindex = findColumn(rs, "je_number");
	if (colindex > 0) {
	    ob.setJe_number(rs.getLong(colindex));
	}
	
	colindex = findColumn(rs, "s_number");
	if (colindex > 0) {
	    ob.setS_number(rs.getLong(colindex));
	}
	
	colindex = findColumn(rs, "l_number");
	if (colindex > 0) {
	    ob.setL_number(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "yyyy");
	if (colindex > 0) {
	    ob.setYyyy(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "mm");
	if (colindex > 0) {
	    ob.setMm(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "dd");
	if (colindex > 0) {
	    ob.setDd(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devname");
	if (colindex > 0) {
	    ob.setDevname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "draccode");
	if (colindex > 0) {
	    ob.setDraccode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "debit_subject_name");
	if (colindex > 0) {
	    ob.setDebit_subject_name(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "dracsubcode");
	if (colindex > 0) {
	    ob.setDracsubcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "debit_details_name");
	if (colindex > 0) {
	    ob.setDebit_details_name(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "dramount");
	if (colindex > 0) {
	    ob.setDramount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "craccode");
	if (colindex > 0) {
	    ob.setCraccode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "credit_subject_name");
	if (colindex > 0) {
	    ob.setCredit_subject_name(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "cracsubcode");
	if (colindex > 0) {
	    ob.setCracsubcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "credit_details_name");
	if (colindex > 0) {
	    ob.setCredit_details_name(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "cramount");
	if (colindex > 0) {
	    ob.setCramount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "descode");
	if (colindex > 0) {
	    ob.setDescode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "desname");
	if (colindex > 0) {
	    ob.setDesname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorname");
	if (colindex > 0) {
	    ob.setVendorname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "transtime");
	if (colindex > 0) {
	    ob.setTranstime(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "sundry");
	if (colindex > 0) {
	    ob.setSundry(rs.getInt(colindex));
	}
		
	colindex = findColumn(rs, "date");
	if (colindex > 0) {
	    ob.setDate(rs.getString(colindex));
	}
	
	
	return ob;
    }

}
