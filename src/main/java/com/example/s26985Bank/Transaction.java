package com.example.s26985Bank;

public class Transaction {

    private double newSaldo;
    private String clientId;
    private TransactionStatus transactionStatus;

    public Transaction(double newSaldo, String clientId, TransactionStatus transactionStatus) {
        this.newSaldo = newSaldo;
        this.clientId = clientId;
        this.transactionStatus = transactionStatus;
    }

    public double getNewSaldo() {
        return newSaldo;
    }

    public void setNewSaldo(double newSaldo) {
        this.newSaldo = newSaldo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
