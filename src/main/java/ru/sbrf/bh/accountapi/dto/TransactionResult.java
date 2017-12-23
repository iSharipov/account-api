package ru.sbrf.bh.accountapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TransactionResult implements Serializable {
    private BigDecimal renewedAmount;
    private Set<String> messages;
    private Status status;


    public BigDecimal getRenewedAmount() {
        return renewedAmount;
    }

    public void setRenewedAmount(BigDecimal renewedAmount) {
        this.renewedAmount = renewedAmount;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        if (messages == null) {
            messages = new HashSet<>();
        }
        messages.add(message);
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

