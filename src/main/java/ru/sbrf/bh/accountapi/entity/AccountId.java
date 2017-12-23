package ru.sbrf.bh.accountapi.entity;

import java.io.Serializable;

public class AccountId implements Serializable {

    private Long id;
    private String accountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}