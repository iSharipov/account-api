package ru.sbrf.bh.accountapi.entity;

import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.enumeration.OperationType;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Transaction")
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @JoinTable(name = "ACCOUNT_TRANSACTION",
            joinColumns = @JoinColumn(name = "TRANSACTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    @ManyToOne
    private Account account;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @Column(name = "OPERATION_STATUS")
    private String operationStatus;

    private String amount;

    private String messages;

    @Column(name = "TRANSACTION_DATE_TIME")
    private Date transactionDateTime;

    public Transaction(Account account, OperationType operationType, TransactionResult result, String amount) {
        this.account = account;
        this.operationType = operationType.name();
        this.operationStatus = result.getStatus().name();
        this.transactionDateTime = new Date();
        this.amount = amount;
        this.messages = result.getMessages() != null ? result.getMessages().toString() : "";

    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;

    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getAmount() {
        return amount;
    }

    public String getMessages() {
        return messages;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }
}