/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* SessionService.java
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
package com.ey.ams.services.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.ams.dao.interfaces.ISessionDAO;
import com.ey.ams.model.HUser;
import com.ey.ams.model.Session;
import com.ey.ams.model.UserSession;
import com.ey.ams.services.interfaces.ISessionService;


public class SessionService implements ISessionService {

    private final Logger logger = LoggerFactory.getLogger(SessionService.class);

    private ISessionDAO sessionDao;

    private UserSession userSession;

    public void setSessionDao(ISessionDAO sessionDao) {
	this.sessionDao = sessionDao;
    }

    public void setUserSession(UserSession userSession) {
	this.userSession = userSession;
    }

    public UserSession getUserSession() {
	return userSession;
    }

    public UserSession insertSession(HttpServletRequest request, HUser user) throws Exception {
	if (!isSessionValid()) {
	    // sets session info
	    Session session = new Session();
	    session.setId(-1);
	    session.setUser(user);
	    session.setReferrer(request.getHeader("referer"));
	    session.setBrowser(request.getHeader("user-agent"));
	    session.setSearchstring(request.getQueryString());

	    String requestUrl = request.getRequestURL().toString();
	    requestUrl = requestUrl.replaceAll("'", "''");
	    session.setLocation(requestUrl);
	    String ip = request.getRemoteAddr();
	    session.setClientIpAddress(ip);
	    Date date = new Date();

	    session.setSessionStart(date);
	    session.setSessionEnd(date);

	    // insert session info in DB
	    int sessionId = sessionDao.insertSession(session);

	    // update current web session
	    userSession.setSessionId(sessionId);
	    userSession.setClientIpAddress(ip);
	    userSession.setUser(user);
	} // session not set

	logger.info("User Session Id - > " + userSession.getSessionId());
	return userSession;
    }

    public void setUser(HUser user) {
	userSession.setUser(user);
    }

    
    public boolean isSessionValid() {
	return (userSession.getSessionId() > 0 ? true : false);
    }

    public void invalidateSession(int sessionId) {
	userSession.setSessionId(0);
    }

}
