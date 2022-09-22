package menu;

import JSON.*;
import blockchain.*;

import org.jetbrains.annotations.NotNull;
import users.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private List<Block> blockchain;
    private List<User> users;
    private String tempHash, name;
    private Block lastBlock;
    private Block secondLastBlock;
    //private final Logger logger = Logger.getLogger(String.valueOf(Menu.class));
    public Menu()
    {
        menu();
    }
    private void menu()
    {
        boolean loop = true;
        while(loop)
        {
            //get option from menu
            int option = getOption(blockchain, users);

            switch (option)            {
                case 1-> addTransactionToBlock();
                case 2-> searchBlockByHash();
                case 3-> searchBlockByID();
                case 4-> addNewUser();
                case 5-> printUserInformation();
                case 6-> showLastBlock(lastBlock);
                case 7-> writeDataToFile(users);
                case 8-> getDataFromFile();
                case 9-> endLoop(loop);


                default -> {
                    throw new RuntimeException("Unsupported option: " + option);
                }
            }
        }
    }

    //get resposne from the user
    private int getOption(@NotNull List<Block> blockchain, @NotNull List<User> users) {

        int sizeOfBlockchain = blockchain.size();
        int sizeOfUsers = users.size();


        Scanner scanner= new Scanner(System.in);
        int option;
        System.out.println("Current number of blocks:" + sizeOfBlockchain);
        System.out.println("Current number of users: "+ sizeOfUsers);
        System.out.println("Menu:");
        System.out.println("1. Add transactions to block");
        System.out.println("2. Show block by hash: ");
        System.out.println("3. Show block by index: ");
        System.out.println("4. Create users");
        System.out.println("5. Show user by name");
        System.out.println("6. Show last block");
        System.out.println("7. Send users to UsersDoc.json");
        System.out.println("8. Write from file");
        System.out.println("9. Quit");
        System.out.println("Choice: ");

        option = scanner.nextInt();

        //logger.info("Current number of users")

        return option;

    }

    //case 1: add transactions to block
    private void addTransactionToBlock() {

        Block block = new Block();

        block.addTransactionToBlock(users);
        blockchain.add(block);
        lastBlock.setBlockHeaderTimestamp();
        //setPreviousBlockHeaderHash
        lastBlock.
                setBlockHeaderPreviousBlockHeaderHashCode(
                        blockchain.get(blockchain.size()-2));
        //setPreviousBlockHash
        lastBlock.
                setPreviousHash(
                        blockchain.get(blockchain.size()-2));
    }

    //case 2: Show Block by hash
    private void searchBlockByHash() {

        String tempHash;
        Scanner scanner= new Scanner(System.in);

        System.out.println("Sign hash: ");
        tempHash= scanner.nextLine();
        for(Block block : blockchain)
        {
            if(block.getHash().equals(tempHash))
            {
                block.seeBlock();
            }
        }
    }

    //case 3: Show Block by index
    private void searchBlockByID() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Enter ID: ");
        choice= scanner.nextInt();
        for(Block block : blockchain)
        {
            if(block.getId()==choice)
            {
                block.seeBlock();
            }
        }
    }


    //case 4: Create users
    private void addNewUser() {
        /*
        users.add(new User());
        if(user1.returnID()==3)
        {
            //set first balance for 1000
            users.get(0).setName();
            users.get(0).setFirstBalance();
            return;
        }
        //set others balance for 0
        users.get(users.size()-1).setName();
        users.get(users.size()-1).setWallet();*/

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
        lastBlock.seeBlock();
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
