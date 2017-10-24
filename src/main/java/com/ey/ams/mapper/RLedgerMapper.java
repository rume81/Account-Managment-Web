package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HLedger;
import com.ey.ams.model.RLedger;

public class RLedgerMapper extends BaseMapper implements RowMapper<RLedger> {

	@Override
	public RLedger mapRow(ResultSet rs, int rowNum) throws SQLException {
		RLedger ob = new RLedger();
		int colIndex = 0;
		
		colIndex = findColumn(rs, "accode");
		if(colIndex >0) {
			ob.setAccode(rs.getString(colIndex));
		}
		
		colIndex = findColumn(rs, "acname");
		if(colIndex >0) {
			ob.setAcname(rs.getString(colIndex));
		}
		
		colIndex = findColumn(rs, "acsubcode");
		if(colIndex >0) {
			ob.setAcsubcode(rs.getString(colIndex));
		}
		
		colIndex = findColumn(rs, "acsubname");
		if(colIndex >0) {
			ob.setAcsubname(rs.getString(colIndex));
		}
		
		colIndex = findColumn(rs, "devcode");
		if(colIndex >0) {
			ob.setDevcode(rs.getString(colIndex));
		}
		
		colIndex = findColumn(rs, "devname");
		if(colIndex >0) {
			ob.setDevname(rs.getString(colIndex));
		}
		
		
		return ob;
	}
}
