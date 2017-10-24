/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HDevcodeselectMapper.java
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

import com.ey.ams.model.HDevcodeselect;

public class HDevcodeselectMapper extends BaseMapper implements RowMapper<HDevcodeselect> {
    @Override
    public HDevcodeselect mapRow(ResultSet rs, int rowNum) throws SQLException {
	HDevcodeselect ob = new HDevcodeselect();
	int colindex = 0;
	
	colindex = findColumn(rs, "agkey");
	if (colindex > 0) {
	    ob.setAgkey(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acname");
	if (colindex > 0) {
	    ob.setAcname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acsubname");
	if (colindex > 0) {
	    ob.setAcsubname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "devcode");
	if (colindex > 0) {
	    ob.setDevcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "accode");
	if (colindex > 0) {
	    ob.setAccode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "acsubcode");
	if (colindex > 0) {
	    ob.setAcsubcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "taxcr");
	if (colindex > 0) {
	    ob.setTaxcr(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "keycode");
	if (colindex > 0) {
	    ob.setKeycode(rs.getString(colindex));
	}
	
	return ob;
    }

}
