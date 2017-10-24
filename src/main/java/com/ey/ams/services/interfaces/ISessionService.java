/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* ISessionService.java
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
package com.ey.ams.services.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.ey.ams.model.HUser;
import com.ey.ams.model.UserSession;


public interface ISessionService {
    public UserSession insertSession(HttpServletRequest request,HUser user)  throws Exception;
	
    public boolean isSessionValid();

    public void invalidateSession(int sessionId);
	
    public UserSession getUserSession();
	
    //public void setEmployee(HEmployee emp);
}
