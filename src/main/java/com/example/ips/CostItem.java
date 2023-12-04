package com.example.ips;

public class CostItem {
    private String companyName;
    private double expenses;

    public CostItem(String companyName, double expenses) {
        this.companyName = companyName;
        this.expenses = expenses;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

}