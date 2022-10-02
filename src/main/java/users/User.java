package users;

import blockchain.Transaction;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;



public class User {
    private static int idNumber;
    private String name;
    private Wallet wallet=new Wallet();
    public User()
    {
        //idNumber++;
    }

    //Creating user

    public String getName() {
        return name;
    }
    public void setName() {
        System.out.println("Sign name: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
    }
    public int returnID()
    {
        return idNumber;
    }
    public int getIdNumber()
    {
        return idNumber;
    }



    //Balance

    public void receive(BigDecimal money)
    {
        this.wallet.setBalance(money);
    }
    public void give(BigDecimal price)
    {
        this.wallet.setBalance(-price);
    }
    public boolean checkIfCapable(Double transactionValue)
    {
        return !(wallet.getBalance() - transactionValue < 0);
    }
    public void setWallet()
    {
        this.wallet.setBalance(0);
    }
    public void setFirstBalance()
    {
        this.wallet.setBalance(1000);
    }


    public LinkedList<Transaction> getWalletTransactions()
    {
        return this.wallet.getTransactions();
    }

    public double getWalletBalance()
    {
        return this.wallet.getBalance();
    }

    public void addWalletTransaction(Transaction transaction)
    {
        this.wallet.addTransactions(transaction);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
