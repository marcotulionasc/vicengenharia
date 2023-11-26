package com.example.appautenticao.domain;

public class Account {
    private String accountName;
    private String amount;
    private String dueDate;
    private String category;

    public Account() {
    }

    public Account(String accountName, String amount, String dueDate, String category) {
        this.accountName = accountName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


