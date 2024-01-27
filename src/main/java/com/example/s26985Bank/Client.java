package com.example.s26985Bank;

public class Client {

    private String clientId;
    private double saldo;
    private String name;

    private String surname;

    public Client(String clientId, double saldo, String name, String surname) {
        this.clientId = clientId;
        this.saldo = saldo;
        this.name = name;
        this.surname = surname;
    }


    public String getClientId() {
        return clientId;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
