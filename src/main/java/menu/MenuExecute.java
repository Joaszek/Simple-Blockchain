package menu;

import blockchain.Block;
import blockchain.BlockManager;
import blockchain.Transaction;
import org.jetbrains.annotations.NotNull;
import users.User;

import java.util.List;
import java.util.Scanner;

public class MenuExecute implements MenuOperations
{

    @Override
    public int getOption(List<Block> blockchain, @NotNull List<User> users) {
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

    @Override
    public void addTransactionToBlock() {
        Block block = new Block();
/*
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
                        blockchain.get(blockchain.size()-2));*/
    }

    @Override
    public void searchBlockByHash(List<Block> blockchain) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Sign hash: ");

        final String tempHash = scanner.nextLine();

        //

        blockchain.stream()
                .filter(block -> block.getHash().equals(tempHash))
                .findFirst()
                .ifPresent(BlockManager::seeBlock);
    }

    @Override
    public void searchBlockByID(List<Block> blockchain) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Enter ID: ");
        choice= scanner.nextInt();
        for(Block block : blockchain)
        {
            if(block.getId()==choice)
            {
                BlockManager.seeBlock(block);
            }
        }
    }

    @Override
    public void addNewUser(List<User> users) {
        users.add(new User());
        if(true)
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

    @Override
    public void printUserInformation(List<User> users) {
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

    @Override
    public void showLastBlock() {

    }

    @Override
    public void writeDataToFile() {

    }

    @Override
    public void getDataFromFile() {

    }

    @Override
    public void endOperations() {

    }
}
