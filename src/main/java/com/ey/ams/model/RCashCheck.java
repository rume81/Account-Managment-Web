package com.ey.ams.model;

public class RCashCheck extends HObject {
    private String department;
    private String max_department_name;
    private double previous_balance_sum;
    private double debit_amount_sum; 
    private double credit_amount_sum;
    private double today_balance; 
    private double total_balance_amount;
    private String acsubcode;
    private String acsubname;
    
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getMax_department_name() {
        return max_department_name;
    }
    public void setMax_department_name(String max_department_name) {
        this.max_department_name = max_department_name;
    }
    public double getPrevious_balance_sum() {
        return previous_balance_sum;
    }
    public void setPrevious_balance_sum(double previous_balance_sum) {
        this.previous_balance_sum = previous_balance_sum;
    }
    public double getDebit_amount_sum() {
        return debit_amount_sum;
    }
    public void setDebit_amount_sum(double debit_amount_sum) {
        this.debit_amount_sum = debit_amount_sum;
    }
    public double getCredit_amount_sum() {
        return credit_amount_sum;
    }
    public void setCredit_amount_sum(double credit_amount_sum) {
        this.credit_amount_sum = credit_amount_sum;
    }
    public double getToday_balance() {
        return today_balance;
    }
    public void setToday_balance(double today_balance) {
        this.today_balance = today_balance;
    }
    public double getTotal_balance_amount() {
        return total_balance_amount;
    }
    public void setTotal_balance_amount(double total_balance_amount) {
        this.total_balance_amount = total_balance_amount;
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
    
    
}
