package ru.sbrf.bh.accountapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionResult implements Serializable {
    private BigDecimal renewedAmount;
    private String message;
    private Status status;


    public BigDecimal getRenewedAmount() {
        return renewedAmount;
    }

    public void setRenewedAmount(BigDecimal renewedAmount) {
        this.renewedAmount = renewedAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        SUCCESS,
        ERROR
    }
}

