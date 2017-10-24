/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* http://www.ams.com
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* HActable.java
* --------------------
* Created on Jan 27, 2017
*
* Revision: 
* Author: Abdullah-Al-Monsur (ALA)
* Source: 
* Id:  
*
* Jan 27, 2017: Original version (ALA)
*
*/
package com.ey.ams.model;

public class HActable extends HObject {
    private int rowid; 
    private String keycode;
    private String devcode;
    private String accode;
    private String acname;
    private String acsubcode;
    private String acsubname;
    private double bgbalance;
    private double budget;
    private double budgetmd;
    private double budgetadd;
    private double budgetmv;
    private String no;
    private String fscr;
    private boolean cachcr;
    
    public int getRowid() {
        return rowid;
    }
    public void setRowid(int rowid) {
        this.rowid = rowid;
    }
    public String getKeycode() {
        return keycode;
    }
    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }
    public String getDevcode() {
        return devcode;
    }
    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }
    public String getAccode() {
        return accode;
    }
    public void setAccode(String accode) {
        this.accode = accode;
    }
    public String getAcname() {
        return acname;
    }
    public void setAcname(String acname) {
        this.acname = acname;
    }
    public String getAcsubcode() {
        return acsubcode;
    }
    public void setAcsubcode(String acsubcode) {
        this.acsubcode = acsubcode;
    }
    public String getAcsubname() {
        return acsubname;
    }
    public void setAcsubname(String acsubname) {
        this.acsubname = acsubname;
    }
    public double getBgbalance() {
        return bgbalance;
    }
    public void setBgbalance(double bgbalance) {
        this.bgbalance = bgbalance;
    }
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
    public double getBudgetmd() {
        return budgetmd;
    }
    public void setBudgetmd(double budgetmd) {
        this.budgetmd = budgetmd;
    }
    public double getBudgetadd() {
        return budgetadd;
    }
    public void setBudgetadd(double budgetadd) {
        this.budgetadd = budgetadd;
    }
    public double getBudgetmv() {
        return budgetmv;
    }
    public void setBudgetmv(double budgetmv) {
        this.budgetmv = budgetmv;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getFscr() {
        return fscr;
    }
    public void setFscr(String fscr) {
        this.fscr = fscr;
    }
    public boolean isCachcr() {
        return cachcr;
    }
    public void setCachcr(boolean cachcr) {
        this.cachcr = cachcr;
    }
}
