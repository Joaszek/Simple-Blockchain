package users;

import blockchain.Transaction;
import wallet.Wallet;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    public void receive(User user,BigDecimal money)
    {
        user.getWallet().addBalance(money);
    }
    public void give(User user,BigDecimal price)
    {
       user.getWallet().addBalance(price.negate());
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

    public User createNewUser(List<User> users) {
        boolean nameAlreadyExists = false;
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter User name");
        String userName = scanner.next();
        while(!nameAlreadyExists)
        {
            for(int i = 0; i < users.size(); i++)
            {
                if(users.get(i).getName().equals(userName))
                {
                    System.out.println("That name already exists. Choose a new name");
                    userName = scanner.next();
                    break;
                }
                if(i==users.size() - 1)
                {
                    nameAlreadyExists=true;
                }
            }
        }
        user.setName(userName);
        this.setWallet(user);
        System.out.println("Created new user");
        return user;
    }
}
