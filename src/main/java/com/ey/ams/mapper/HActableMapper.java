/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HActableMapper.java
* --------------------
* Created on Jan 27, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Jan 27, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HActable;

public class HActableMapper extends BaseMapper implements RowMapper<HActable> {

    @Override
    public HActable mapRow(ResultSet rs, int rowNum) throws SQLException {
	HActable ob = new HActable();
	int colindex = 0;
	
	colindex = findColumn(rs, "rowid");
	if (colindex > 0) {
	    ob.setRowid(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "keycode");
	if (colindex > 0) {
	    ob.setKeycode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
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
	
	colindex = findColumn(rs, "bgbalance");
	if (colindex > 0) {
	    ob.setBgbalance(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "budget");
	if (colindex > 0) {
	    ob.setBudget(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "budgetmd");
	if (colindex > 0) {
	    ob.setBudgetmd(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "budgetadd");
	if (colindex > 0) {
	    ob.setBudgetadd(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "budgetmv");
	if (colindex > 0) {
	    ob.setBudgetmv(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "no");
	if (colindex > 0) {
	    ob.setNo(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "fscr");
	if (colindex > 0) {
	    ob.setFscr(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "cachcr");
	if (colindex > 0) {
	    ob.setCachcr(rs.getBoolean(colindex));
	}
	
	
	return ob;
    }

}
