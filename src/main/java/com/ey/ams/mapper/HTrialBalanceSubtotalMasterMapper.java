/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HTrialBalanceSubtotalMasterMapper.java
* --------------------
* Created on Mar 2, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 2, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HTrialBalanceSubtotalMaster;

public class HTrialBalanceSubtotalMasterMapper extends BaseMapper implements RowMapper<HTrialBalanceSubtotalMaster> {

    @Override
    public HTrialBalanceSubtotalMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
	HTrialBalanceSubtotalMaster ob = new HTrialBalanceSubtotalMaster();
	int colindex = 0;
	
	colindex = findColumn(rs, "AC_FROM");
	if (colindex > 0) {
	    ob.setAC_FROM(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "AC_TO");
	if (colindex > 0) {
	    ob.setAC_TO(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "NAIYO");
	if (colindex > 0) {
	    ob.setNAIYO(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "KEY");
	if (colindex > 0) {
	    ob.setKEY(rs.getString(colindex));
	}
	
	return ob;
    }
}
