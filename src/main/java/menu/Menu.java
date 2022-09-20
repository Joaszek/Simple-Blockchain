package menu;

import JSON.*;
import blockchain.*;
import users.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private void menu()
    {
        int option;
        String tempHash;
        String name;
        int choice;
        while(loop)
        {
            //menu options
            option = getOption(blockchain, users, scanner);
            //create new block
            Block newBlock = new Block();


            final Block lastBlock = blockchain.get(blockchain.size() - 1);
            //to samo dla przedostatniej


            switch (option)            {
                case 1-> createNewBlock(newBlock, lastBlock);
                case 2-> searchBlockByHash();
                case 3-> searchBlockByID();
                case 4-> addNewUser();
                case 5-> printUserInformation();
                case 6-> showLastBlock(lastBlock);
                case 7-> writeDataToFile();
                case 8-> getDataFromFile();
                case 9-> endLoop(loop);
                default -> {
                    throw new RuntimeException("Unsupported option: " + option);
                }
            }
        }
    }

    private void endLoop(boolean loop) {
        loop = false;
    }

    private void getDataFromFile() {
        WriteFromFile.writeFromFile(users);
    }

    private void writeDataToFile() {
        try {
            WriteToFile.writeToFile(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showLastBlock(Block lastBlock) {
        lastBlock.seeBlock();
    }

    private void printUserInformation() {
        String name;
        //works
        //to take enter
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

    private void addNewUser() {
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
        users.get(users.size()-1).setWallet();
    }

    private void searchBlockByID() {
        int choice;
        System.out.println("Choose by ID: ");
        choice= scanner.nextInt();
        for(Block block : blockchain)
        {
            if(block.getId()==choice)
            {
                block.seeBlock();
            }
        }
    }

    private void searchBlockByHash() {
        String tempHash;
        System.out.println("Sign hash: ");
        scanner.nextLine();
        tempHash= scanner.nextLine();
        for(Block block : blockchain)
        {
            if(block.getHash().equals(tempHash))
            {
                block.seeBlock();
            }
        }
    }

    private void createNewBlock(Block newBlock, Block lastBlock) {
        //works
        newBlock.addTransactionToBlock(users);
        blockchain.add(newBlock);
        lastBlock.setId(idNumber);
        idNumber++;
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

    //function to get user information
    private int getOption(List<Block> blockchain, List<User> users, Scanner scanner) {
        int option;
        System.out.println("Current number of blocks:" + blockchain.size());
        System.out.println("Current number of users: "+ users.size());
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
        return option;
    }


}
