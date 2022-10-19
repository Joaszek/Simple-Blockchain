package menu;

import JSON.WriteFromFile;
import JSON.WriteToFile;
import blockchain.Block;
import blockchain.BlockManager;
import checkInputs.CheckInputs;
import hashing.Hash;
import hashing.HashStrings;
import logger.Logger;
import org.jetbrains.annotations.NotNull;
import users.User;
import users.UserManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuExecute implements MenuOperations
{

    @Override
    public int getOption(@NotNull List<Block> blockchain, @NotNull List<User> users) {
        int sizeOfBlockchain = blockchain.size();
        int sizeOfUsers = users.size();

        int option;
        System.out.println("Current number of blocks:" + sizeOfBlockchain);
        System.out.println("Current number of users: "+ sizeOfUsers);
        System.out.println("Menu:");
        System.out.println("1. Add transactions to block");
        System.out.println("2. Show block by hash: ");
        System.out.println("3. Show block by UUID: ");
        System.out.println("4. Create user");
        System.out.println("5. Show user by name");
        System.out.println("6. Show last block");
        System.out.println("7. Send users to users.json");
        System.out.println("8. Send blockchain to file");
        System.out.println("9. Get users from file");
        System.out.println("10. Print list of users");
        System.out.println("11. Quit");
        System.out.println("Choice: ");
        option = CheckInputs.getCorrectInput(0,11,"unsupported input");
        return option;
    }

    @Override
    public void addTransactionsToBlock(List<User> users, List<Block> blockchain) {
        Block block = new Block();
        Hash hash = new Hash();
        Block lastBlock =blockchain.get(blockchain.size()-1);

        BlockManager.addTransactionToBlock(users,block);
        block.getBlockHeader().setMerkleRoot(hash.getMerkleRoot(block.getTransactions()));
        BlockManager.setBlockHeaderTimeStamp(block);
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

        blockchain.stream()
                .filter(block -> block.getBlockUUID().
                compareTo(UUID.fromString(uuidString))==0)
                .findFirst().ifPresent(BlockManager::seeBlock);
    }

    @Override
    public void addNewUser(List<User> users) {
        UserManager userManager = new UserManager();
        users.add(userManager.createNewUser(users));
    }

    @Override
    public void printUserInformation(List<User> users) {
        AtomicBoolean didNotFindUser = new AtomicBoolean(false);
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        name = scanner.nextLine();
        users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst().ifPresent(user -> {
                    System.out.println(user);
                    didNotFindUser.set(true);
                });
        if(!didNotFindUser.get()) System.out.println("User not found");
    }

    @Override
    public void showLastBlock(Block lastBlock) {
        BlockManager.seeBlock(lastBlock);
    }

    @Override
    public void writeUsersToFile(List<User> users) {
        WriteToFile writeToFile = new WriteToFile();
        try {
            writeToFile.writeUsersToFile(users);
        } catch (IOException | URISyntaxException e) {
            Logger.printError(e,BlockManager.class);
        }
        System.out.println("File will be added when you finish program");
    }

    @Override
    public void writeBlockchainToFile(List<Block> blockchain) {
       WriteToFile writeToFile = new WriteToFile();
        try{
            writeToFile.writeBlockchainToFile(blockchain);
        }catch (IOException e)
        {
            Logger.printError(e, BlockManager.class);
        }
        System.out.println("File will be added when you finish program");
    }

    @Override
    public void readUsersJSON(List<User> users) {
        WriteFromFile writeFromFile = new WriteFromFile();
        writeFromFile.writeFromFile(users);
        System.out.println("Added users: "+users.get(users.size()-1)+ "and "+users.get(users.size()-2));
    }

    @Override
    public void endOperations() {
        System.out.println("You chose to finish.");
        System.exit(0);
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
        Block block = new Block(true);
        String hash="0";
        block.setHash(HashStrings.hash(hash));
        blockchain.add(block);
    }
    public void beforeUsingBlockchain(List<User> users)
    {
        System.out.println("You need to add user to use blockchain");
        addNewUser(users);
        System.out.println("""
                Every user starts with balance equal to zero
                Blockchain has by default one user
                His name is Satoshi Nakamoto and has balance equal to 1000
                First transaction's sender has to be Satoshi Nakamoto(creator of bitcoin)""");

    }

    @Override
    public void printUsers(List<User> users) {
        users.forEach(user -> System.out.println(user.getName()));
    }
}
