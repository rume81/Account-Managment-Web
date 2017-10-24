/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HBaseDataMapper.java
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

import com.ey.ams.model.HBaseData;

public class HBaseDataMapper extends BaseMapper implements RowMapper<HBaseData> {
    @Override
    public HBaseData mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBaseData ob = new HBaseData();
	int colindex = 0;
	
	colindex = findColumn(rs, "cname");
	if (colindex > 0) {
	    ob.setCname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "yeyyyy");
	if (colindex > 0) {
	    ob.setYeyyyy(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "yemm");
	if (colindex > 0) {
	    ob.setYemm(rs.getInt(colindex));
	}
	
	colindex = findColumn(rs, "base_dir");
	if (colindex > 0) {
	    ob.setBase_dir(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "data_dir");
	if (colindex > 0) {
	    ob.setData_dir(rs.getString(colindex));
	}
	
	return ob;
    }

}
