package users;

import blockchain.Transaction;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;


public class User {
    private UUID uuid;
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    private Wallet wallet;
    public User()
    {

    }
    public User(boolean isFirst)
    {

    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
