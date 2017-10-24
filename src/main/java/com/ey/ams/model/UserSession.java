package com.ey.ams.model;

public class UserSession {
    private int sessionId;
    private String clientIpAddress;
        
    protected HUser user = null;
    
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public String getClientIpAddress() {
        return clientIpAddress;
    }
    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }
    public HUser getUser() {
        return user;
    }
    public void setUser(HUser user) {
        this.user = user;
    }
}
