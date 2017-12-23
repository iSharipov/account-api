package ru.sbrf.bh.accountapi.vo;

public final class Params {
    private final String accountTo;
    private final String accountFrom;
    private final String amount;

    private Params(Builder builder) {
        this.accountTo = builder.accountTo;
        this.accountFrom = builder.accountFrom;
        this.amount = builder.amount;
    }

    public static final class Builder {
        private String accountTo;
        private String accountFrom;
        private String amount;

        public Builder() {

        }

        public Builder withAccountTo(String accountTo) {
            this.accountTo = accountTo;
            return this;
        }

        public Builder withAccountFrom(String accountFrom) {
            this.accountFrom = accountFrom;
            return this;
        }

        public Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Params build() {
            return new Params(this);
        }
    }

    public String getAccountTo() {
        return accountTo;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public String getAmount() {
        return amount;
    }
}