/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HJournal.java
* --------------------
* Created on Feb 2, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Feb 2, 2017: Original version (ALA)
*
*/
package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HJournal;
import com.ey.ams.model.RTransferSlipSub;

public class RTransferSlipSubMapper extends BaseMapper implements RowMapper<RTransferSlipSub> {

    @Override
    public RTransferSlipSub mapRow(ResultSet rs, int rowNum) throws SQLException {
	RTransferSlipSub ob = new RTransferSlipSub();
	int colindex = 0;
	
	/*colindex = findColumn(rs, "s_number");
	if (colindex > 0) {
	    ob.setS_number(rs.getLong(colindex));
	}
	
	colindex = findColumn(rs, "je_number");
	if (colindex > 0) {
	    ob.setJe_number(rs.getLong(colindex));
	}
	
	colindex = findColumn(rs, "l_number");
	if (colindex > 0) {
	    ob.setL_number(rs.getInt(colindex));
	}*/
	
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
	
	colindex = findColumn(rs, "dramount");
	if (colindex > 0) {
	    ob.setDramount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "cramount");
	if (colindex > 0) {
	    ob.setCramount(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "balance");
	if (colindex > 0) {
	    ob.setBalance(rs.getDouble(colindex));
	}
	
	colindex = findColumn(rs, "descode");
	if (colindex > 0) {
	    ob.setDescode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "desname");
	if (colindex > 0) {
	    ob.setDesname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "memo");
	if (colindex > 0) {
	    ob.setMemo(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "transtime");
	if (colindex > 0) {
	    ob.setTranstime(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "drctax");
	if (colindex > 0) {
	    ob.setDrctax(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "crctax");
	if (colindex > 0) {
	    ob.setCrctax(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorcode");
	if (colindex > 0) {
	    ob.setVendorcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "vendorname");
	if (colindex > 0) {
	    ob.setVendorname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "otherdata");
	if (colindex > 0) {
	    ob.setOtherdata(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "prjcode");
	if (colindex > 0) {
	    ob.setPrjcode(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "prjname");
	if (colindex > 0) {
	    ob.setPrjname(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "name");
	if (colindex > 0) {
	    ob.setName(rs.getString(colindex));
	}
	
	colindex = findColumn(rs, "n1");
	if (colindex > 0) {
	    ob.setN1(rs.getString(colindex));
	}
	
	return ob;
    }

}
