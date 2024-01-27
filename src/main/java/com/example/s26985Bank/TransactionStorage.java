package com.example.s26985Bank;


import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransactionStorage {
    private List<Transaction> transactionList;

    public TransactionStorage(List<Transaction> transactionList) {
        this.transactionList = new ArrayList<>();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }
}
