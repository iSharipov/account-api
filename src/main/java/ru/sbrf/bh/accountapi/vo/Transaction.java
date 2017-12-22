package ru.sbrf.bh.accountapi.vo;

public final class Transaction {
    private final String personIdFrom;
    private final String personIdTo;
    private final String accountNumberFrom;
    private final String accountNumberTo;
    private final String transactionAmount;

    private Transaction(Builder builder) {
        this.personIdFrom = builder.personIdFrom;
        this.personIdTo = builder.personIdTo;
        this.accountNumberFrom = builder.accountNumberFrom;
        this.accountNumberTo = builder.accountNumberTo;
        this.transactionAmount = builder.transactionAmount;
    }

    public static final class Builder {

        private String personIdFrom;
        private String personIdTo;
        private String accountNumberFrom;
        private String accountNumberTo;
        private String transactionAmount;

        public Builder() {

        }

        public Builder withPersonIdFrom(String personIdFrom) {
            this.personIdFrom = personIdFrom;
            return this;
        }

        public Builder withPersonIdTo(String personIdTo) {
            this.personIdTo = personIdTo;
            return this;
        }

        public Builder withAccountNumberFrom(String accountNumberFrom) {
            this.accountNumberFrom = accountNumberFrom;
            return this;
        }

        public Builder withAccountNumberTo(String accountNumberTo) {
            this.accountNumberTo = accountNumberTo;
            return this;
        }

        public Builder withTransactionAmount(String transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

    public String getPersonIdFrom() {
        return personIdFrom;
    }

    public String getPersonIdTo() {
        return personIdTo;
    }

    public String getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }
}