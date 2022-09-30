package users;

import blockchain.Transaction;

import java.util.LinkedList;


public class Wallet {
    private LinkedList<Transaction> transactions;
    private double balance;



    public Wallet() {
        this.transactions= new LinkedList<>();
    }


    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) {

        this.transactions.add(transaction);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "transactions=" + transactions +
                ", balance=" + balance +
                '}';
    }
}
