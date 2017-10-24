/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectBudgetMapper.java
* --------------------
* Created on Mar 3, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 3, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HProjectBudget;

public class HProjectBudgetMapper extends BaseMapper implements RowMapper<HProjectBudget> {
    @Override
    public HProjectBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
	HProjectBudget ob = new HProjectBudget();
	int colindex = 0;
	
	colindex = findColumn(rs, "prjcode");
	if (colindex > 0) {
		ob.setPrjcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "prjname");
	if (colindex > 0) {
		ob.setPrjname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "aggregatekey");
	if (colindex > 0) {
		ob.setAggregatekey(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acname");
	if (colindex > 0) {
		ob.setAcname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acsubname");
	if (colindex > 0) {
		ob.setAcsubname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "debitamount");
	if (colindex > 0) {
		ob.setDebitamount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "creditamount");
	if (colindex > 0) {
		ob.setCreditamount(rs.getDouble(colindex));
	}
	
	return ob;
    }

}
