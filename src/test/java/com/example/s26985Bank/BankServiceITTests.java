package com.example.s26985Bank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

@SpringBootTest
public class BankServiceITTests {

    private Client client;
    private Client client1;

    @Autowired
    private BankService bankService;

    @AfterEach
    public void clearStorage(){
        bankService.getClientStorage().getClientList().clear();
        bankService.getTransactionStorage().getTransactionList().clear();
    }
    @Test
    void registerClient() {
        client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        client1 = bankService.registerClient("Kuba", "Test", "KT01", 2000.00);

        Assertions.assertNotNull(client);
        Assertions.assertNotNull(client1);
        Assertions.assertEquals(2, bankService.getClientStorage().getClientList().size());


    }

    @Test
    void createTransaction() {
        client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        client1 = bankService.registerClient("Kuba", "Test", "KT01", 2000.00);
        Transaction transaction = bankService.createTransaction("PK01", 1000.00);
        Transaction transaction1 = bankService.createTransaction("KT01", 3000.00);

        Assertions.assertEquals(4000.00, transaction.getNewSaldo());
        Assertions.assertEquals(4000.00, client.getSaldo());
        Assertions.assertEquals(TransactionStatus.ACCEPTED, transaction.getTransactionStatus());
        Assertions.assertEquals(client.getClientId(), transaction.getClientId());
        // ----------------
        Assertions.assertEquals(2000.00, transaction1.getNewSaldo());
        Assertions.assertEquals(2000.00, client1.getSaldo());
        Assertions.assertEquals(TransactionStatus.DECLINED, transaction1.getTransactionStatus());
        Assertions.assertEquals(client1.getClientId(), transaction1.getClientId());
        // -------------
        Assertions.assertEquals(2, bankService.getTransactionStorage().getTransactionList().size());

    }

    @Test
    void addMoneyToAccount() {
        client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Transaction transaction = bankService.addMoneyToAccount("PK01", 2000.00);
        Throwable expect1 = Assertions.assertThrows(IllegalArgumentException.class, () -> bankService.addMoneyToAccount("PK02", 2000.00));

        Assertions.assertEquals(7000.00, transaction.getNewSaldo());
        Assertions.assertEquals(7000.00, client.getSaldo());
        Assertions.assertEquals(TransactionStatus.ACCEPTED, transaction.getTransactionStatus());
        Assertions.assertEquals("accepted", transaction.getMessage());
        Assertions.assertEquals("The client with this ID, is not registered", expect1.getMessage());
        // ----
        Assertions.assertEquals(1, bankService.getTransactionStorage().getTransactionList().size());
    }

    @Test
    void getClientInfo() {
        client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Assertions.assertEquals("Patryk", client.getName());
        Assertions.assertEquals("Kosmider", client.getSurname());
        Assertions.assertEquals("PK01", client.getClientId());
        Assertions.assertEquals(5000.00, client.getSaldo());
    }
}
