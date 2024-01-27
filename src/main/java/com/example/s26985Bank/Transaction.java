package com.example.s26985Bank;

public class Transaction {

    private double newSaldo;
    private String clientId;
    private TransactionStatus transactionStatus;
    private String message;

    public Transaction(double newSaldo, String clientId, TransactionStatus transactionStatus, String message) {
        this.newSaldo = newSaldo;
        this.clientId = clientId;
        this.transactionStatus = transactionStatus;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
