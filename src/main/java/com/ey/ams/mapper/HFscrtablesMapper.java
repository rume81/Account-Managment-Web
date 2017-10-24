/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HFscrtablesMapper.java
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

import com.ey.ams.model.HFscrtables;

public class HFscrtablesMapper extends BaseMapper implements RowMapper<HFscrtables> {

    @Override
    public HFscrtables mapRow(ResultSet rs, int rowNum) throws SQLException {
	HFscrtables ob = new HFscrtables();
	int colindex = 0;
	
	colindex = findColumn(rs, "fscr");
	if (colindex > 0) {
	    ob.setFscr(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "fscrname");
	if (colindex > 0) {
	    ob.setFscrname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "dscr");
	if (colindex > 0) {
	    ob.setDscr(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "repsort");
	if (colindex > 0) {
	    ob.setRepsort(rs.getString(colindex));
	}
	
	return ob;
    }

}
