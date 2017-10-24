/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* RTransferSlipMapper.java
* --------------------
* Created on Feb 15, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 15, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.RTransferSlip;

public class RTransferSlipMapper extends BaseMapper implements RowMapper<RTransferSlip> {
    @Override
    public RTransferSlip mapRow(ResultSet rs, int rowNum) throws SQLException {
	RTransferSlip ob = new RTransferSlip();
	
	int colindex = 0;
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devname");
	if (colindex > 0) {
	    ob.setDevname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "je_number");
	if (colindex > 0) {
	    ob.setJe_number(rs.getLong(colindex));
	}
	
	colindex = findColumn(rs, "s_number");
	if (colindex > 0) {
	    ob.setS_number(rs.getLong(colindex));
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
	
	colindex = findColumn(rs, "abstract_name");
	if (colindex > 0) {
	    ob.setAbstract_name(rs.getString(colindex));
	}
	return ob;
    }

}
