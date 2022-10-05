package users;

import blockchain.Transaction;
import wallet.Wallet;

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
       user.getWallet().addBalance(price.negate());
    }
    public BigDecimal getWalletBalance(User user)
    {
        return user.getWallet().getBalance();
    }
    public LinkedList<Transaction> getWalletTransactions(User user)
    {
        return user.getWallet().getTransactions();
    }
    public void addWalletTransaction(User user, Transaction transaction)
    {
       user.getWallet().addTransactions(transaction);
    }
    public void setFirstBalance(User user)
    {
        user.getWallet().addBalance(BigDecimal.valueOf(1000));
    }
    public void setWallet(User user)
    {
        user.setWallet(new Wallet());
    }

}
