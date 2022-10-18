package blockchain;

import logger.Logger;
import hashing.Hash;
import users.User;
import users.UserManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlockManager {

    //print Information about Block
    public static void seeBlock(Block block)
    {
        System.out.println("Block ID: " + block.getBlockUUID());
        System.out.println("Status: ");
        System.out.println("Timestamp: " + block.getBlockHeader().getTimeStamp());
        System.out.println("Difficulty: " + BlockHeader.getDifficultyTarget());
        System.out.println("Time used to mine: " + block.getTimeUsedToMine()+" milliseconds");
        System.out.println("Hash: " + block.getHash());
        System.out.println("Block UUID: " + block.getBlockUUID());
        System.out.println("Nonce: " + block.getBlockHeader().getNonce());
        System.out.println("Transactions: ");
        printTransactions(block);
    }

    //add Transaction to the block
    public static void addTransactionToBlock(List<User> users, Block block)
    {
        List<Transaction> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        BigDecimal transactionValue;
        String sender, receiver, transactionName;
        boolean quit=true;

        while(quit)
        {
            System.out.println("TRANSACTION");

            sender=getAndCheckIfUserExists(users,"Sender");

            receiver=getAndCheckIfUserExists(users,"Receiver");

            transactionValue=checkIfOperationIsPossible(users,sender);

            transactionName=getTransactionName();

            finalizeTransaction(sender, receiver,users,transactionValue);


            transactions.add(
                    newTransaction(
                            transactionValue,
                            transactionName));

            addTransactionToWallet(sender,
                    newTransaction(
                            transactionValue.negate(),
                            transactionName),
                    users);

            addTransactionToWallet(
                    receiver,
                    newTransaction(
                            transactionValue,
                            transactionName),
                    users);
            System.out.println("Add new transaction(1) or quit(2)?");
            if(scanner.nextInt()==2)quit=false;
            block.setTransactions(transactions);
        }


    }


    //check if user exists, if not then create one
    private static String getAndCheckIfUserExists(List<User> users, String whichUser)
    {
        Scanner scanner = new Scanner(System.in);
        String userName = null;
        System.out.println("Sign "+ whichUser+" name: ");
        try
        {
            userName = scanner.nextLine();
        }catch (Exception e)
        {
            Logger.printError(e,BlockManager.class);
        }
        for(User user : users)
        {
            if(user.getName().equals(userName))
            {
                return user.getName();
            }
        }
        //didn't find the name of the user
        boolean nameDoesntExist = true;
        while(nameDoesntExist)
        {
            System.out.println("Didn't find the name of the user. " +
                    "Enter new name: ");
            userName = scanner.nextLine();
            for(User user : users)
            {
                if(user.getName().equals(userName))
                {
                    nameDoesntExist = false;
                    break;
                }
            }
        }
        return userName;
    }


    //check if balance of the sender is enough to run the transaction
    private static BigDecimal checkIfOperationIsPossible(List<User> users,String sender) {

        Scanner scanner = new Scanner(System.in);
        BigDecimal transactionValue = new BigDecimal(0);
        BigDecimal stateOfTheWallet = new BigDecimal(0);

        for(User user : users)
        {
            if(user.getName().equals(sender))
            {
                stateOfTheWallet=user.getWallet().getBalance();
            }
        }

        System.out.println("Enter Transaction Value: ");
        try {
            transactionValue = scanner.nextBigDecimal();
        }catch (Exception e)
        {
            Logger.printError(e,BlockManager.class);
        }
        //if transactionValue is negative or equal 0
        while(transactionValue.compareTo(BigDecimal.valueOf(0))<=0|| transactionValue.compareTo(stateOfTheWallet) > 0)
        {
            if(transactionValue.compareTo(BigDecimal.valueOf(0))<=0)
            {
                System.out.println("Transaction value should be greater than 0");
                System.out.println("Enter transaction Value: ");
                transactionValue=scanner.nextBigDecimal();
            }
            if(transactionValue.compareTo(stateOfTheWallet)>0)
            {
                System.out.println("Sender doesn't have that much money");
                System.out.println("Enter transaction Value: ");
                transactionValue=scanner.nextBigDecimal();
            }

        }
        return transactionValue;
    }



    //get the name of the transaction
    private static String getTransactionName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Transaction Name: ");
        return scanner.nextLine();
    }

    //change the balance of the users
    private static void finalizeTransaction(String sender, String receiver, List<User> users, BigDecimal transactionValue)
    {
        UserManager userManager = new UserManager();
        for(User user:users)
        {
            if(user.getName().equals(sender))
            {
                //user that is sending the money
                userManager.give(user, transactionValue);
            }
            else if(user.getName().equals(receiver))
            {
                //user that is receiving money
                userManager.receive(user, transactionValue);
            }
        }
    }

    //create new transaction
    private static Transaction newTransaction(BigDecimal transactionValue,
                                              String transactionName)
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionValue);
        transaction.setTransactionName(transactionName);
        transaction.setLocalDateTime();
        return transaction;
    }

    //print the information about the transactions in the block
    public static  void printTransactions(Block block) {

        for(Transaction transaction: block.getTransactions())
        {
            System.out.println("Name: "+transaction.getTransactionName());
            System.out.println("Value: "+ transaction.getTransactionValue());
            System.out.println("Time: "+ transaction.getDateTime());
        }
    }

    //add transaction to wallet of the user
    private static void addTransactionToWallet(String userName, Transaction transaction, List<User> users)
    {
        UserManager userManager = new UserManager();
        for(User user :users)
        {
            if(user.getName().equals(userName))
            {
                userManager.addWalletTransaction(user,transaction);
            }
        }
    }
    //create and set hash of the block
    public static void signBlockWithHash(Block block, String previousHash)
    {
        int difficultyTarget =BlockHeader.getDifficultyTarget();
        int nonce = block.getBlockHeader().getNonce();
        String merkleRoot = block.getBlockHeader().getMerkleRoot();
        StringBuilder timeStamp = block.getBlockHeader().getTimeStamp();

        block.setHash(Hash.mine(
                block,nonce,previousHash,
                difficultyTarget,merkleRoot,
                timeStamp));
    }


    //set difficultyTarget in menu
    public static void setDifficultyTarget()
    {
        int difficultyTarget;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter difficulty target: ");
        try{
            difficultyTarget = scanner.nextInt();
        }catch (Exception e)
        {
            throw new RuntimeException("Unsupported value for difficulty target: ");
        }
        BlockHeader.setDifficultyTarget(difficultyTarget);
    }
    public static void setBlockHeaderTimeStamp(Block block)
    {
        StringBuilder timeStamp= new StringBuilder(String.valueOf(LocalDateTime.now()));
        block.getBlockHeader().setTimeStamp(timeStamp);
    }
}
