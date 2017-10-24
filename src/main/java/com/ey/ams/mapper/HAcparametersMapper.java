/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HAcparametersMapper.java
* --------------------
* Created on Feb 6, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 6, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HAcparameters;

public class HAcparametersMapper extends BaseMapper implements RowMapper<HAcparameters> {
    @Override
    public HAcparameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	HAcparameters ob = new HAcparameters();
	int colindex = 0;
	
	colindex = findColumn(rs, "keycode");
	if (colindex > 0) {
	    ob.setKeycode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "taxcr");
	if (colindex > 0) {
	    ob.setTaxcr(rs.getString(colindex));
	}
		
	return ob;
    }

}
