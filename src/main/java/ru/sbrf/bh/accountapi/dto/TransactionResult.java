package ru.sbrf.bh.accountapi.dto;

import ru.sbrf.bh.accountapi.enumeration.OperationStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TransactionResult implements Serializable {
    private BigDecimal renewedAmount;
    private Set<String> messages;
    private OperationStatus status;


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

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}