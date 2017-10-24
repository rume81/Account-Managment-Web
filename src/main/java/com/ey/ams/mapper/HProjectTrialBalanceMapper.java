/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectTrialBalanceMapper.java
* --------------------
* Created on Mar 6, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Mar 6, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HProjectTrialBalance;

public class HProjectTrialBalanceMapper extends BaseMapper implements RowMapper<HProjectTrialBalance> {

    @Override
    public HProjectTrialBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
	HProjectTrialBalance ob = new HProjectTrialBalance();
	int colindex = 0;

	colindex = findColumn(rs, "name");
	if (colindex > 0) {
	    ob.setName(rs.getString(colindex));
	}

	colindex = findColumn(rs, "subtotal_name");
	if (colindex > 0) {
	    ob.setSubtotal_name(rs.getString(colindex));
	}

	colindex = findColumn(rs, "aggregate_key");
	if (colindex > 0) {
	    ob.setAggregate_key(rs.getString(colindex));
	}

	colindex = findColumn(rs, "acname");
	if (colindex > 0) {
	    ob.setAcname(rs.getString(colindex));
	}

	colindex = findColumn(rs, "acsubname");
	if (colindex > 0) {
	    ob.setAcsubname(rs.getString(colindex));
	}

	colindex = findColumn(rs, "debit_amount_sum");
	if (colindex > 0) {
	    ob.setDebit_amount_sum(rs.getDouble(colindex));
	}

	colindex = findColumn(rs, "credit_amount_sum");
	if (colindex > 0) {
	    ob.setCredit_amount_sum(rs.getDouble(colindex));
	}
	return ob;
    }

}
