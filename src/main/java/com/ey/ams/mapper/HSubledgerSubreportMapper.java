package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HSubledgerSubreport;

public class HSubledgerSubreportMapper extends BaseMapper implements RowMapper<HSubledgerSubreport> {

	@Override
	public HSubledgerSubreport mapRow(ResultSet rs, int rowNum) throws SQLException {
		HSubledgerSubreport ob = new HSubledgerSubreport();
		int colIndex = 0;
/*
     		if(null==sub.getPrjcode()){
    		    sub.setPrjcode("");
    		}
    		if(null==sub.getPrjname()){
    		    sub.setPrjname("");
    		}
    		if(null==sub.getVendorcode()){
    		    sub.setVendorcode("");
    		}
    		if(null==sub.getVendorname()){
    		    sub.setVendorname("");
    		}
 */
		colIndex = findColumn(rs, "aggregate_key");
		if (colIndex > 0) {
			ob.setAggregate_key(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "accode");
		if (colIndex > 0) {
			ob.setAccode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "acname");
		if (colIndex > 0) {
			ob.setAcname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "acsubcode");
		if (colIndex > 0) {
			ob.setAcsubcode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "acsubname");
		if (colIndex > 0) {
			ob.setAcsubname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "devcode");
		if (colIndex > 0) {
			ob.setDevcode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "devname");
		if (colIndex > 0) {
			ob.setDevname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "je_number");
		if (colIndex > 0) {
			ob.setJe_number(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "s_number");
		if (colIndex > 0) {
			ob.setS_number(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "l_number");
		if (colIndex > 0) {
			ob.setL_number(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "yyyy");
		if (colIndex > 0) {
			ob.setYyyy(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "mm");
		if (colIndex > 0) {
			ob.setMm(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "dd");
		if (colIndex > 0) {
			ob.setDd(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "draccode");
		if (colIndex > 0) {
			ob.setDraccode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "dracname");
		if (colIndex > 0) {
			ob.setDracname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "dracsubcode");
		if (colIndex > 0) {
			ob.setDracsubcode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "dracsubname");
		if (colIndex > 0) {
			ob.setDracsubname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "dramount");
		if (colIndex > 0) {
			ob.setDramount(rs.getDouble(colIndex));
		}

		colIndex = findColumn(rs, "cramount");
		if (colIndex > 0) {
			ob.setCramount(rs.getDouble(colIndex));
		}

		colIndex = findColumn(rs, "balance");
		if (colIndex > 0) {
			ob.setBalance(rs.getDouble(colIndex));
		}

		colIndex = findColumn(rs, "descode");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null!=val)
			ob.setDescode(val);
		    else
			ob.setDescode("");
		}

		colIndex = findColumn(rs, "desname");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null!=val)
			ob.setDesname(val);
		    else
			ob.setDesname("");
		}

		colIndex = findColumn(rs, "sundry");
		if (colIndex > 0) {
			ob.setSundry(rs.getInt(colIndex));
		}

		colIndex = findColumn(rs, "vendorcode");
		if (colIndex > 0) {
			ob.setVendorcode(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "vendorname");
		if (colIndex > 0) {
			ob.setVendorname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "otherdata");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null!=val)
			ob.setOtherdata(val);
		    else
			ob.setOtherdata("");
		}

		colIndex = findColumn(rs, "drctax");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null!=val)
			ob.setDrctax(val);
		    else
			ob.setDrctax("");
		}

		colIndex = findColumn(rs, "crctax");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null!=val)
			ob.setCrctax(val);
		    else
			ob.setCrctax("");
		}

		colIndex = findColumn(rs, "prjcode");
		if (colIndex > 0) {
		    String val = rs.getString(colIndex);
		    if(null != val)
			ob.setPrjcode(val);
		    else
			ob.setPrjcode("");
		}

		colIndex = findColumn(rs, "prjname");
		if (colIndex > 0) {
			ob.setPrjname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "dracname");
		if (colIndex > 0) {
			ob.setDrcname(rs.getString(colIndex));
		}

		colIndex = findColumn(rs, "cracname");
		if (colIndex > 0) {
		    	String val = rs.getString(colIndex);
		    	if(null!=val)
		    	    ob.setCrcname(val);
		    	else
		    	    ob.setCrcname("");
		}

		return ob;
	}

}
