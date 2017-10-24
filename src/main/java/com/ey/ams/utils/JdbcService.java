/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* JdbcService.java
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
package com.ey.ams.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JdbcService extends JdbcAccessor {

    private static Logger log = LoggerFactory.getLogger(JdbcService.class);

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager transactionManager;

    public TransactionStatus beginTran() {

	if (transactionManager == null) {
	    log.warn("null txn manager");

	    return null;
	}

	TransactionDefinition txn = new DefaultTransactionDefinition();
	return transactionManager.getTransaction(txn);
    }

    public void commitTran(TransactionStatus status) {
	if (transactionManager == null) {
	    log.warn("null txn manager");
	    return;
	}
	transactionManager.commit(status);
    }

    public void rollbackTran(TransactionStatus status) {

	if (transactionManager == null) {
	    log.warn("null txn manager");
	    return;
	}

	transactionManager.rollback(status);
    }

    /*
     * public Object queryForObject(String sql, RowMapper mapper){ Object ob =
     * new Object(); try { ob = getJdbcTemplate() .queryForObject(sql, mapper);
     * } catch (Exception ex) { ex.printStackTrace(); }
     * 
     * return ob; }
     */

    public JdbcTemplate getJdbcTemplate() {
	return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
	this.transactionManager = transactionManager;
    }

}
