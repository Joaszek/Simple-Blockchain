package users;

import blockchain.BlockManager;
import blockchain.Transaction;
import logger.Logger;
import wallet.Wallet;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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


    public User createNewUser(List<User> users) {
        boolean nameAlreadyExists = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User name");
        String userName = scanner.next();
        User user = new User(userName);

        while(!nameAlreadyExists)
        {
            for(int i = 0; i < users.size(); i++)
            {
                if(users.get(i).getName().equals(user.getName()))
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
        System.out.println("Created new user");
        return user;
    }

    private static User getAndCheckIfUserExists(List<User> users, String whichUser, UUID uuid)
    {
        Scanner scanner = new Scanner(System.in);
        String userName = null;
        System.out.println("Sign "+ whichUser+" name: ");
        try
        {
            userName = scanner.nextLine();
        }catch (Exception e) {
            Logger.printError(e, BlockManager.class);
        }

        final String userNameFinal = userName;

        User user1 = users.stream().filter(user -> user.getName().equals(userNameFinal)&&user.getUuid().equals(uuid)).findFirst().get();
        if(user1 != null) {
            return user1;
        }



        //didn't find the user
        boolean userDoesntExist = true;

        while(userDoesntExist)
        {
            System.out.println("Didn't find the user. " +
                    "Enter name: ");
            userName = scanner.nextLine();


            final String newUserNameFinal = userName;

            userDoesntExist = users.stream()
                    .noneMatch(user -> user.getName().equals(newUserNameFinal));

        }
        return userName;
    }
}
