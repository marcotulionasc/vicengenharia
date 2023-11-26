package com.example.appautenticao.domain;

public class Account {
    private String id;
    private String accountName;
    private String amount;
    private String dueDate;
    private String category;

    // Construtor vazio necessário para o Firebase
    public Account() {}

    public Account(String id, String accountName, String amount, String dueDate, String category) {
        this.id = id;
        this.accountName = accountName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
    }
}

