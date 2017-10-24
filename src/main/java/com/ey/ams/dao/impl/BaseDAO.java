/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* BaseDAO.java
* --------------------
* Created on Jan 26, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Jan 26, 2017: Original version (ALA)
*
*/
package com.ey.ams.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ey.ams.utils.JdbcService;


public class BaseDAO {
	private JdbcService jdbcService;
	
	public JdbcService getJdbcService() {
		return jdbcService;
	}


	public void setJdbcService(JdbcService jdbcService) {
		this.jdbcService = jdbcService;
	}

	/*protected String getCommonArgs() {

		return "deleted = 0, modifierid = 1";
	}*/
}
