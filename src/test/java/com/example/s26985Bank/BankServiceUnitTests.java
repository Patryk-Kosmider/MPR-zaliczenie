package com.example.s26985Bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
@ExtendWith(MockitoExtension.class)
public class BankServiceUnitTests {

    @Mock
    private ClientStorage clientStorage;
    @Mock
    private TransactionStorage transactionStorage;
    @InjectMocks
    private BankService bankService;

    @Test
    void registerClient() {
        Client client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Assertions.assertNotNull(client);
        Assertions.assertEquals("PK01", client.getClientId());
        Assertions.assertEquals("Patryk", client.getName());
        Assertions.assertEquals("Kosmider", client.getSurname());
        Assertions.assertEquals(5000.00, client.getSaldo());
    }

    @Test
    void createTransaction() {
        Client client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Mockito.when(clientStorage.getClientList()).thenReturn(List.of(client));
        Transaction transaction = bankService.createTransaction("PK01", 1000.00);
        Throwable except2 = Assertions.assertThrows(IllegalArgumentException.class, () -> bankService.createTransaction("PK02", 2000.00 ));


        Assertions.assertEquals(4000.00, transaction.getNewSaldo());
        Assertions.assertEquals(4000.00, client.getSaldo());
        Assertions.assertEquals(client.getClientId(), transaction.getClientId());
        Assertions.assertEquals(TransactionStatus.ACCEPTED, transaction.getTransactionStatus());
        Assertions.assertEquals("accepted", transaction.getMessage());
        Assertions.assertEquals("The client with this ID, is not registered", except2.getMessage());


    }

    @Test
    void createTransactionDeclined() {
        Client client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Mockito.when(clientStorage.getClientList()).thenReturn(List.of(client));
        Transaction transaction = bankService.createTransaction("PK01", 6000.00);

        Assertions.assertEquals(5000.00, transaction.getNewSaldo());
        Assertions.assertEquals("Not enough money to make this transaction", transaction.getMessage());
        Assertions.assertEquals("PK01", transaction.getClientId());
    }

    @Test
    void addMoneyToAccount(){
        Client client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Mockito.when(clientStorage.getClientList()).thenReturn(List.of(client));
        Transaction transaction = bankService.addMoneyToAccount("PK01", 1000.00);

        Throwable except1 = Assertions.assertThrows(IllegalArgumentException.class, () -> bankService.addMoneyToAccount("PK02", 1000.00));
        Assertions.assertEquals(6000.00, transaction.getNewSaldo());
        Assertions.assertEquals(6000.00, client.getSaldo());
        Assertions.assertEquals("PK01", transaction.getClientId());
        Assertions.assertEquals(TransactionStatus.ACCEPTED, transaction.getTransactionStatus());
        Assertions.assertEquals("accepted", transaction.getMessage());
        Assertions.assertEquals("The client with this ID, is not registered", except1.getMessage());


    }

    @Test
    void getClientInfo() {
        Client client = bankService.registerClient("Patryk", "Kosmider", "PK01", 5000.00);
        Mockito.when(clientStorage.getClientList()).thenReturn(List.of(client));
        Client clientToGetInfo = bankService.getClientInfo("PK01");


        Throwable except1 = Assertions.assertThrows(IllegalArgumentException.class, () -> bankService.getClientInfo("PKO2"));
        Assertions.assertEquals("Patryk", clientToGetInfo.getName());
        Assertions.assertEquals("Kosmider", clientToGetInfo.getSurname());
        Assertions.assertEquals(5000.00, clientToGetInfo.getSaldo());
        Assertions.assertEquals("PK01", clientToGetInfo.getClientId());
        Assertions.assertEquals("The client with this ID, is not registered", except1.getMessage());
    }
}
