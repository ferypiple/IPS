package com.example.ips;

public class CostItem {
    private String companyName;
    private int expenses;

    public CostItem(String companyName, int expenses) {
        this.companyName = companyName;
        this.expenses = expenses;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

}