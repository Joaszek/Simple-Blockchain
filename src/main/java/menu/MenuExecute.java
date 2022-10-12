package menu;

import JSON.WriteFromFile;
import JSON.WriteToFile;
import blockchain.Block;
import blockchain.BlockManager;
import hashing.Hash;
import hashing.HashStrings;
import logger.Logger;
import org.jetbrains.annotations.NotNull;
import users.User;
import users.UserManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuExecute implements MenuOperations
{

    @Override
    public int getOption(@NotNull List<Block> blockchain, @NotNull List<User> users) {
        int sizeOfBlockchain = blockchain.size();
        int sizeOfUsers = users.size();
        boolean isValid=false;
        Scanner scanner= new Scanner(System.in);

        String option;
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
        option = scanner.next();
        int i=0;
        while(!isValid) {
            System.out.println(i);
            isValid=checkinput(option);
            if(isValid)
            {
                i++;
                System.out.println(i);
                isValid=checkinput(String.valueOf(userChoseWrongOption()));
            }
        }
        System.out.println("wyszedł");
        return Integer.parseInt(option);
    }

    @Override
    public void addTransactionsToBlock(List<User> users, List<Block> blockchain) {
        Block block = new Block();
        Hash hash = new Hash();
        Block lastBlock =blockchain.get(blockchain.size()-1);

        BlockManager.addTransactionToBlock(users,block);
        block.getBlockHeader().setMerkleRoot(hash.getMerkleRoot(block.getTransactions()));
        BlockManager.signBlockWithHash(block, lastBlock.getHash());
        blockchain.add(block);
        System.out.println("Block was successfully added to blockchain.");
    }

    @Override
    public void searchBlockByHash(List<Block> blockchain) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Enter hash: ");

        final String tempHash = scanner.nextLine();

        blockchain.stream()
                .filter(block -> block.getHash().equals(tempHash))
                .findFirst()
                .ifPresent(BlockManager::seeBlock);
    }

    @Override
    public void searchBlockByUUID(List<Block> blockchain) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter UUID: ");
        String uuidString= scanner.nextLine();

        for(Block block : blockchain)
        {
            if(block.getBlockUUID().
                    compareTo(UUID.fromString(uuidString))==0)
            {
                BlockManager.seeBlock(block);
            }
        }
    }

    @Override
    public void addNewUser(List<User> users) {
        UserManager userManager = new UserManager();
        users.add(userManager.createNewUser());
    }

    @Override
    public void printUserInformation(List<User> users) {
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        name = scanner.nextLine();
        for(User user : users)
        {
            if(user.getName().equals(name))
            {
                //System.out.println(user);
            }
        }
    }

    @Override
    public void showLastBlock(Block lastBlock) {
        BlockManager.seeBlock(lastBlock);
    }

    @Override
    public void writeDataToFile(List<User> users) {
        try {
            WriteToFile.writeToFile((LinkedList<User>) users);
        } catch (IOException e) {
            Logger.printError(e,BlockManager.class);
        }
    }

    @Override
    public void getDataFromFile(List<User> users) {
        WriteFromFile.writeFromFile((LinkedList<User>) users);
    }

    @Override
    public void endOperations(boolean loop) {
        loop =false;
    }

    @Override
    public Block setLastBlock(List<Block> blockchain) {
        return blockchain.get(blockchain.size()-1);
    }

    @Override
    public void createFirstUser(List<User> users) {
        users.add(new User(true));
    }

    @Override
    public void createFirstBlock(List<Block> blockchain) {
        Block block = new Block();
        String hash="0";
        block.setHash(HashStrings.hash(hash));
        blockchain.add(block);
    }

    @Override
    public int userChoseWrongOption() {
        int option=10;
        Scanner scanner = new Scanner(System.in);

        while(option<0||option>9)
        {
            System.out.println("You chose wrong option");
            System.out.println("Enter correct option");
            try{
                option = scanner.nextInt();
            }catch(NumberFormatException e)
            {
                throw new NumberFormatException();
            }
        }
        return option;




    }
    public boolean checkinput(String input)
    {
        int cos;
        try {
            cos=Integer.parseInt(input);
            if(cos>=0&&cos<=9)
            {
                return true;
            }
        }catch(NumberFormatException e)
        {
            return false;
        }
        return false;
    }
}
