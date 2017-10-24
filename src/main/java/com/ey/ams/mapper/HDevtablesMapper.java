/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HDevtablesMapper.java
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

import com.ey.ams.model.HDevtables;

public class HDevtablesMapper extends BaseMapper implements RowMapper<HDevtables> {

    @Override
    public HDevtables mapRow(ResultSet rs, int rowNum) throws SQLException {
	HDevtables ob = new HDevtables();
	int colindex = 0;
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devname");
	if (colindex > 0) {
	    ob.setDevname(rs.getString(colindex));
	}
	
	return ob;
    }

}
