/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HReportdataretentionMapper.java
* --------------------
* Created on Mar 1, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 1, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HReportdataretention;

public class HReportdataretentionMapper extends BaseMapper implements RowMapper<HReportdataretention> {

    @Override
    public HReportdataretention mapRow(ResultSet rs, int rowNum) throws SQLException {
	HReportdataretention ob =new HReportdataretention();
	
	int colindex = 0;
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "monthly");
	if (colindex > 0) {
	    ob.setMonthly(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "carryoverbalance_of_paymentsbalance");
	if (colindex > 0) {
	    ob.setCarryoverbalance_of_paymentsbalance(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "carriedforwardnetasset");
	if (colindex > 0) {
	    ob.setCarriedforwardnetasset(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "specifiednetassetsopeningbalance");
	if (colindex > 0) {
	    ob.setSpecifiednetassetsopeningbalance(rs.getDouble(colindex));
	}
	
	return ob;
    }

}
