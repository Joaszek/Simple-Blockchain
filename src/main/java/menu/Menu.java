package menu;

import JSON.*;
import blockchain.*;

import org.jetbrains.annotations.NotNull;
import users.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Block> blockchain;
    private List<User> users;
    private String tempHash, name;
    private Block lastBlock;
    private Block secondLastBlock;
    private MenuOperations menuOperations;
    //private final Logger logger = Logger.getLogger(String.valueOf(Menu.class));
    public Menu(MenuOperations operations)
    {
        this.menuOperations = operations;
        menu();
    }
    private void menu()
    {

        boolean loop = true;
        while(loop)
        {
            //get option from menu
            int option = menuOperations.getOption(blockchain, users);

            switch (option)            {
                case 1-> menuOperations.addTransactionToBlock();
                case 2-> menuOperations.searchBlockByHash(blockchain);
                case 3-> menuOperations.searchBlockByID(blockchain);
                case 4-> menuOperations.addNewUser( users);
                case 5-> menuOperations.printUserInformation();
                case 6-> menuOperations.showLastBlock();
                case 7-> menuOperations.writeDataToFile();
                case 8-> menuOperations.getDataFromFile();
                case 9-> menuOperations.endOperations();

                default -> {
                    throw new RuntimeException("Unsupported option: " + option);
                }
            }
        }
    }


    //case 4: Create users
    private void addNewUser() {

    }


    //case 5: Show user information
    private void printUserInformation() {
        String name;
        //works
        //to take enter
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Name: ");
        name = scanner.nextLine();
        for(User user : users)
        {
            if(user.getName().equals(name))
            {
                System.out.println("Name: "+ user.getName());
                System.out.println("Wallet: "+ user.getWalletBalance());
                System.out.println("Transactions: ");
                for(Transaction transaction :user.getWalletTransactions())
                {
                    System.out.println("Name: "+transaction.getTransactionName());
                    System.out.println("Value: "+transaction.getTransactionValue());
                    System.out.println("Time: "+transaction.getTimestamp());
                }
            }
        }
    }
    //case 6: show last block info
    private void showLastBlock(Block lastBlock) {
        BlockManager.seeBlock(lastBlock);
    }

    //case 7: Send users to UsersDoc.json
    private void writeDataToFile(List<User> users) {
        try {
            WriteToFile.writeToFile((LinkedList<User>) users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //case 8: Get DataFromFile

    private void getDataFromFile() {
        WriteFromFile.writeFromFile((LinkedList<User>) users);
    }


    //case 9: endLoop
    private void endLoop(boolean loop) {
        loop = false;
    }





}
