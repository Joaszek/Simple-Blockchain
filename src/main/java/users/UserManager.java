package users;

import blockchain.Transaction;

import java.math.BigDecimal;
import java.util.LinkedList;

public class UserManager {
    public boolean checkIfCapable(User user, BigDecimal transactionValue)
    {
        return user.getWallet().getBalance().add(transactionValue.negate()).compareTo(new BigDecimal(0))>0;
    }
    public void receive(User user,BigDecimal money)
    {
        user.getWallet().addBalance(money);
    }
    public void give(User user,BigDecimal price)
    {
        this.wallet.setBalance(price.negate());
    }
    public double getWalletBalance(User user,)
    {
        return this.wallet.getBalance();
    }
    public LinkedList<Transaction> getWalletTransactions(User user)
    {
        return this.wallet.getTransactions();
    }
    public void addWalletTransaction(User user, Transaction transaction)
    {
        this.wallet.addTransactions(transaction);
    }
    public void setFirstBalance(User user)
    {
        this.wallet.setBalance(BigDecimal.valueOf(1000));
    }
    public void setWallet(User user)
    {
        this.wallet.setBalance(BigDecimal.valueOf(0));
    }

}
