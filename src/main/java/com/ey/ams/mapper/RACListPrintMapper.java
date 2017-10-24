/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* RACListPrintMapper.java
* --------------------
* Created on Feb 1, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 1, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.RACListPrint;

public class RACListPrintMapper extends BaseMapper implements RowMapper<RACListPrint> {

    @Override
    public RACListPrint mapRow(ResultSet rs, int rowNum) throws SQLException {
	RACListPrint ob = new RACListPrint();
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
	
	colindex = findColumn(rs, "fscr");
	if (colindex > 0) {
	    ob.setFscr(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "fscrname");
	if (colindex > 0) {
	    ob.setFscrname(rs.getString(colindex));
	}
	
	return ob;
    }

}
