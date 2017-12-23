package ru.sbrf.bh.accountapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Account")
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "ACCOUNT__NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_UUID")
    private String accountUuid;

    private BigDecimal amount;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.accountUuid = UUID.randomUUID().toString();
        this.amount = BigDecimal.ZERO;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal increaseAmount(BigDecimal augend) {
        BigDecimal temp = this.amount.add(augend);
        this.amount = new BigDecimal(temp.toString());
        return temp;
    }

    public BigDecimal substractAmount(BigDecimal subtrahend) {
        BigDecimal temp = this.amount.subtract(subtrahend);
        this.amount = new BigDecimal(temp.toString());
        return temp;
    }
}