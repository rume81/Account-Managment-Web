/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HJewrk_updatedMapper.java
* --------------------
* Created on Feb 24, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 24, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HJewrk_updated;

public class HJewrk_updatedMapper extends BaseMapper implements RowMapper<HJewrk_updated> {

    @Override
    public HJewrk_updated mapRow(ResultSet rs, int rowNum) throws SQLException {
	HJewrk_updated ob = new HJewrk_updated();
	int colindex = 0;
	
	colindex = findColumn(rs, "je_number");
	if (colindex > 0) {
	    ob.setJe_number(rs.getInt(colindex));
	}
	return ob;
    }
    
}
