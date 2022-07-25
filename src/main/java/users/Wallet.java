package users;

import blockchain.Transaction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Wallet {
    private LinkedList<Transaction> transactions;
    private double balance;



    public Wallet() {
        this.transactions= new LinkedList<>();
    }


    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(String transactionName, double transactionValue) {

        Transaction temp= new Transaction();
        temp.setTransactionName(transactionName);
        temp.setTransactionValue(transactionValue);
        temp.setTimestamp();
        this.transactions.add(temp);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

}
