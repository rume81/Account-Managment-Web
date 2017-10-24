package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.HCtc;

public class HCtcMapper extends BaseMapper implements RowMapper<HCtc> {
	@Override
	public HCtc mapRow(ResultSet rs, int rowNum) throws SQLException {
		HCtc ob = new HCtc();
		int colindex = 0;
		
		colindex = findColumn(rs, "code");
		if (colindex > 0) {
		    ob.setCode(rs.getString(colindex));
		}
		colindex = findColumn(rs, "name");
		if(colindex > 0) {
			ob.setName(rs.getString(colindex));
		}
		
		return ob;
	}
}
