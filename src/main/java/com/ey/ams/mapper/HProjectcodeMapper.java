/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HProjectcodeMapper.java
* --------------------
* Created on Feb 3, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 3, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HProjectcode;

public class HProjectcodeMapper extends BaseMapper implements RowMapper<HProjectcode> {

    @Override
    public HProjectcode mapRow(ResultSet rs, int rowNum) throws SQLException {
	HProjectcode ob = new HProjectcode();
	int colindex = 0;
	
	colindex = findColumn(rs, "prjcode");
	if (colindex > 0) {
	    ob.setPrjcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "prjname");
	if (colindex > 0) {
	    ob.setPrjname(rs.getString(colindex));
	}
	return ob;
    }

}
