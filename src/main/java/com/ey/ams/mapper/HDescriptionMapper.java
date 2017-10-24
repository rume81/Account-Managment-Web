/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HDescriptionMapper.java
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

import com.ey.ams.model.HDescription;

public class HDescriptionMapper extends BaseMapper implements RowMapper<HDescription> {
	@Override
	public HDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
		HDescription ob = new HDescription();
		int colindex = 0;

		colindex = findColumn(rs, "desid");
		if (colindex > 0) {
			ob.setDesid(rs.getInt(colindex));
		}

		colindex = findColumn(rs, "devcode");
		if (colindex > 0) {
			ob.setDevcode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "devname");
		if (colindex > 0) {
			ob.setDevname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "draccode");
		if (colindex > 0) {
			ob.setDraccode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "dracname");
		if (colindex > 0) {
			ob.setDracname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "dracsubcode");
		if (colindex > 0) {
			ob.setDracsubcode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "dracsubname");
		if (colindex > 0) {
			ob.setDracsubname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "craccode");
		if (colindex > 0) {
			ob.setCraccode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "cracname");
		if (colindex > 0) {
			ob.setCracname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "cracsubcode");
		if (colindex > 0) {
			ob.setCracsubcode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "cracsubname");
		if (colindex > 0) {
			ob.setCracsubname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "dramount");
		if (colindex > 0) {
			ob.setDramount(rs.getDouble(colindex));
		}

		colindex = findColumn(rs, "cramount");
		if (colindex > 0) {
			ob.setCramount(rs.getDouble(colindex));
		}

		colindex = findColumn(rs, "descode");
		if (colindex > 0) {
			ob.setDescode(rs.getString(colindex));
		}

		colindex = findColumn(rs, "desname");
		if (colindex > 0) {
			ob.setDesname(rs.getString(colindex));
		}

		colindex = findColumn(rs, "drctax");
		if (colindex > 0) {
			ob.setDrctax(rs.getString(colindex));
		}

		colindex = findColumn(rs, "crctax");
		if (colindex > 0) {
			ob.setCrctax(rs.getString(colindex));
		}

		colindex = findColumn(rs, "abstractname");
		if (colindex > 0) {
			ob.setAbstractname(rs.getString(colindex));
		}
		return ob;
	}

}
