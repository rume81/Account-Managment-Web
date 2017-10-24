/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020; by EY.
*
* --------------------
* HTrailBalanceTemp.java
* --------------------
* Created on Mar 2; 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 2; 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HTrailBalanceTemp;

public class HTrailBalanceTempMapper  extends BaseMapper implements RowMapper<HTrailBalanceTemp> {
    @Override
    public HTrailBalanceTemp mapRow(ResultSet rs, int rowNum) throws SQLException {
	HTrailBalanceTemp ob = new HTrailBalanceTemp();
	
	int colindex = 0;
	
	colindex = findColumn(rs, "keycode");
	if (colindex > 0) {
	    ob.setKeycode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devname");
	if (colindex > 0) {
	    ob.setDevname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "accode");
	if (colindex > 0) {
	    ob.setAccode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acname");
	if (colindex > 0) {
	    ob.setAcname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acsubcode");
	if (colindex > 0) {
	    ob.setAcsubcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acsubname");
	if (colindex > 0) {
	    ob.setAcsubname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "budget");
	if (colindex > 0) {
	    ob.setBudget(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "balance_before_provision");
	if (colindex > 0) {
	    ob.setBalance_before_provision(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "debit_amount");
	if (colindex > 0) {
	    ob.setDebit_amount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "credit_amount");
	if (colindex > 0) {
	    ob.setCredit_amount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "balance_amount");
	if (colindex > 0) {
	    ob.setBalance_amount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "subtotal_key");
	if (colindex > 0) {
	    ob.setSubtotal_key(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "subtotal_content");
	if (colindex > 0) {
	    ob.setSubtotal_content(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorcode");
	if (colindex > 0) {
	    ob.setVendorcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorname");
	if (colindex > 0) {
	    ob.setVendorname(rs.getString(colindex));
	}
	
	return ob;
    }
   
}
