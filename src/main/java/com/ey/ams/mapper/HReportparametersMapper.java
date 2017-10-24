/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HReportparametersMapper.java
* --------------------
* Created on Feb 16, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 16, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HReportparameters;

public class HReportparametersMapper extends BaseMapper implements RowMapper<HReportparameters> {
    @Override
    public HReportparameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	HReportparameters ob = new HReportparameters();
	
	int colindex = 0;
	
	colindex = findColumn(rs, "je_extraction_cond");
	if (colindex > 0) {
	    ob.setJe_extraction_cond(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "trial_balance_cond");
	if (colindex > 0) {
	    ob.setTrial_balance_cond(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "ledger_extrantion_cond");
	if (colindex > 0) {
	    ob.setLedger_extrantion_cond(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "abstrac_aggregate_cond");
	if (colindex > 0) {
	    ob.setAbstrac_aggregate_cond(rs.getString(colindex));
	}
	
	return ob;
    }

}
