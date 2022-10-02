package blockchain;

import hashing.Hash;
import users.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlockManager {

    //print Information about Block
    public static void seeBlock(Block block)
    {
        System.out.println("Block ID: " + block.getBlockID());
        System.out.println("Status: ");
        System.out.println("Timestamp: " + block.getBlockHeader().getTimeStamp());
        System.out.println("Difficulty: " + block.getBlockHeader().getDifficultyTarget());
        System.out.println("Size: ");
        System.out.println("Time used to mine: ");
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

            transactionValue=checkIfOperationIsPossible(sender);

            transactionName=getTransactionName();

            finalizeTransaction(sender, receiver,users,transactionValue);


            transactions.add(
                    newTransaction(
                            sender,
                            receiver,
                            transactionValue,
                            transactionName));

            addTransactionToWallet(sender,
                    newTransaction(
                            sender,
                            receiver,
                            transactionValue.negate(),
                            transactionName),
                    users);

            addTransactionToWallet(
                    receiver,
                    newTransaction(
                            receiver,
                            sender,
                            transactionValue,
                            transactionName),
                    users);
        }

        System.out.println("Add new transaction(1) or quit(2)?");
        if(scanner.nextInt()==2)quit=false;
        block.setTransactions(transactions);
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
            throw new RuntimeException("Unsupported name: " + userName);
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
    private static BigDecimal checkIfOperationIsPossible(String sender) {

        Scanner scanner = new Scanner(System.in);
        BigDecimal transactionValue = new BigDecimal(0);

        System.out.println("Enter Transaction Value: ");
        try {
            transactionValue = scanner.nextBigDecimal();
        }catch (Exception e)
        {
            throw new RuntimeException("Unsupported value: " + transactionValue);
        }
        //if transactionValue is negative or equal 0
        while(transactionValue.compareTo(BigDecimal.valueOf(0))<=0)
        {
            System.out.println("Transaction value should be greater than 0");
            System.out.println("Enter transactionValue: ");
            transactionValue=scanner.nextBigDecimal();
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
    private static void finalizeTransaction(String user1Name, String user2Name, List<User> users, BigDecimal transactionValue)
    {
        for(User user:users)
        {
            if(user.getName().equals(user1Name))
            {
                //user that is sending the money
                user.give(transactionValue);
            }
            else if(user.getName().equals(user2Name))
            {
                //user that is receiving money
                user.receive(transactionValue);
            }
        }
    }

    //create new transaction
    private static Transaction newTransaction(String user1Name, String user2Name,
                                              BigDecimal transactionValue,
                                              String transactionName)
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionValue);
        transaction.setTransactionName(transactionName);
        transaction.setTimestamp();
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
        for(User user :users)
        {
            if(user.getName().equals(userName))
            {
                user.addWalletTransaction(transaction);
                return;
            }
        }
    }
    //create and set hash of the block
    public static void signBlockWithHash(Block block, String previousHash)
    {
        Hash hash = new Hash();
        String merkleRoot = hash.getMerkleRoot(block.getTransactions());
        hash.mine(block.getBlockHeader().getNonce(), previousHash);
        block.setHash(hash.createBlockHash(
                block.getBlockHeader().getNonce(),
                previousHash,
                merkleRoot,
                block.getBlockHeader().getTimeStamp()
        ));
    }
}
