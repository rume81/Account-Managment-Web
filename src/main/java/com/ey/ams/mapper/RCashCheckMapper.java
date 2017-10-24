package com.ey.ams.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.ams.model.RCashCheck;

public class RCashCheckMapper extends BaseMapper implements RowMapper<RCashCheck> {

    @Override
    public RCashCheck mapRow(ResultSet rs, int rowNum) throws SQLException {
	RCashCheck ob = new RCashCheck();
	int colIndex = 0;
	
	colIndex = findColumn(rs, "department");
	if(colIndex >0) {
		ob.setDepartment(rs.getString(colIndex));
	}
	
	colIndex = findColumn(rs, "max_department_name");
	if(colIndex >0) {
		ob.setMax_department_name(rs.getString(colIndex));
	}
	
	colIndex = findColumn(rs, "previous_balance_sum");
	if(colIndex >0) {
		ob.setPrevious_balance_sum(Double.parseDouble(rs.getString(colIndex)));
	}
	
	colIndex = findColumn(rs, "debit_amount_sum");
	if(colIndex >0) {
		ob.setDebit_amount_sum(Double.parseDouble(rs.getString(colIndex)));
	}
	
	colIndex = findColumn(rs, "credit_amount_sum");
	if(colIndex >0) {
		ob.setCredit_amount_sum(Double.parseDouble(rs.getString(colIndex)));
	}
	
	colIndex = findColumn(rs, "today_balance");
	if(colIndex >0) {
		ob.setToday_balance(Double.parseDouble(rs.getString(colIndex)));
	}
	
	colIndex = findColumn(rs, "total_balance_amount");
	if(colIndex >0) {
		ob.setTotal_balance_amount(Double.parseDouble(rs.getString(colIndex)));
	}
	
	colIndex = findColumn(rs, "acsubcode");
	if(colIndex >0) {
		ob.setAcsubcode(rs.getString(colIndex));
	}
	
	colIndex = findColumn(rs, "acsubname");
	if(colIndex >0) {
		ob.setAcsubname(rs.getString(colIndex));
	}
		
	return ob;
    }

}
