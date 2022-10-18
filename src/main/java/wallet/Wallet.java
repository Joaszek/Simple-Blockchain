package wallet;

import blockchain.Transaction;

import java.math.BigDecimal;
import java.util.LinkedList;


public class Wallet {
    private LinkedList<Transaction> transactions;
    private BigDecimal balance;



    public Wallet() {
        this.transactions= new LinkedList<>();
        this.balance= new BigDecimal(0);
    }


    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) {

        this.transactions.add(transaction);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addBalance(BigDecimal balance) {
        this.balance = new BigDecimal(String.valueOf(this.balance)).add(balance);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "transactions=" + transactions.toString() +
                ", balance=" + balance +
                '}';
    }
}
