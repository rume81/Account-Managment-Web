/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HVendorsMapper.java
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

import com.ey.ams.model.HVendors;

public class HVendorsMapper extends BaseMapper implements RowMapper<HVendors> {
    @Override
    public HVendors mapRow(ResultSet rs, int rowNum) throws SQLException {
	HVendors ob=new HVendors();
	int colindex = 0;
	
	colindex = findColumn(rs, "vendorcode");
	if (colindex > 0) {
	    ob.setVendorcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorname");
	if (colindex > 0) {
	    ob.setVendorname(rs.getString(colindex));
	}
	
	return ob;
    }

}
