package com.example.s26985Bank;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClientStorage {
    private List <Client> clientList;

    public ClientStorage(List<Client> clientList) {
        this.clientList = new ArrayList<>();
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client){
        clientList.add(client);
    }
}
