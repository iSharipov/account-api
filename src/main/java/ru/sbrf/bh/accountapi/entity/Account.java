package ru.sbrf.bh.accountapi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Account")
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "ACCOUNT__NUMBER", unique = true)
    private String accountNumber;

    @Column(name = "ACCOUNT_UUID")
    private String accountUuid;

    private BigDecimal amount;

    @JoinTable(name = "ACCOUNT_TRANSACTION",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    @OneToMany
    private Set<Transaction> transactions;

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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}