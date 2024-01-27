package com.example.s26985Bank;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    private ClientStorage clientStorage;
    private TransactionStorage transactionStorage;
    public BankService(ClientStorage clientStorage, TransactionStorage transactionStorage) {
        this.clientStorage = clientStorage;
        this.transactionStorage = transactionStorage;
    }


    public Client registerClient (String name, String surname, String clientId, double saldo){
        Client client = new Client(clientId, saldo, name, surname);
        clientStorage.addClient(client);
        return client;
    }

    public Transaction createTransaction(String clientId, double amountOfMoney){
        Transaction transaction = null;
        for (Client client : clientStorage.getClientList()){
            if(client.getClientId().equals(clientId)){
                if(client.getSaldo() - amountOfMoney > 0){
                    double newSaldo = client.getSaldo() - amountOfMoney;
                    transaction = new Transaction(newSaldo, client.getClientId(), TransactionStatus.ACCEPTED, "accepted");
                    client.setSaldo(newSaldo);
                    transactionStorage.addTransaction(transaction);
                    return transaction;
                } else {
                    transaction = new Transaction(client.getSaldo(), client.getClientId(), TransactionStatus.DECLINED, "Not enough money to make this transaction");
                    return transaction;
                }
            }
        }
        throw new IllegalArgumentException("The client with this ID, is not registered");
    }

    public Transaction addMoneyToAccount(String clientId, double amountOfMoney){
        for (Client client : clientStorage.getClientList()){
            if(client.getClientId().equals(clientId)){
                double newSaldo = client.getSaldo() + amountOfMoney;
                client.setSaldo(newSaldo);
                Transaction transaction = new Transaction(newSaldo, clientId, TransactionStatus.ACCEPTED, "accepted");
                transactionStorage.addTransaction(transaction);
                return transaction;
            }
        }
        throw new IllegalArgumentException("The client with this ID, is not registered");
    }

    public Client getClientInfo(String clientId){
        for (Client client : clientStorage.getClientList()){
            if(client.getClientId().equals(clientId)){
                return client;
            }
        }
        throw new IllegalArgumentException("The client with this ID, is not registered");
    }


}
