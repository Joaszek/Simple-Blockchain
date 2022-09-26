package blockchain;


import users.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


public class Block {
    private List<Transaction> transactions;
    private String hash;
    private String previousHash;
    boolean isFirst;
    private static int id;
    private BlockHeader blockHeader;


    public Block(){
        //core
        this.isFirst=false;
        this.transactions=new LinkedList<>();
        this.blockHeader=new BlockHeader();
        id++;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public void setFirst()
    {
        this.isFirst=true;
        this.previousHash= null;
    }

    //check Block data
    public void seeBlock() {
        System.out.println("Hash: " + this.getHash());
        //System.out.println("Previous Hash: " + this.getPreviousHash());
        System.out.println("Transactions: ");
        this.getTransactions();
        System.out.println();
        System.out.println("Block Header");
        System.out.println("Previous Block Header hash" + this.blockHeader.getPreviousBlockHeaderHash());
        //System.out.println("Previous Block hash: " + this.getPreviousHash());
        System.out.println("Transaction hash: " + this.blockHeader.getTransactionsHash());
        System.out.println("Timestamp: " + this.blockHeader.getDate());
    }

    public int getId() {
        return id;
    }

    public String getHash()
    {
        return this.hash;
    }


    //Transactions
    public void addTransactionToBlock(List<User> users)
    {

        Scanner scanner = new Scanner(System.in);
        double transactionValue;
        String user1Name, user2Name, transactionName;
        boolean quit=true;

        int option;
        while(quit)
        {
        System.out.println("TRANSACTION");

        user1Name=getAndCheckIfUserExists(users,"Sender");

        user2Name=getAndCheckIfUserExists(users,"Receiver");

        transactionValue=checkIfOperationIsPossible(user1Name);

        transactionName=getTransactionName();

        finalizeTransaction(user1Name, user2Name,users,transactionValue);


        this.transactions.add(
                newTransaction(
                    user1Name,
                    user2Name,
                    transactionValue,
                    transactionName));

        addTransactionToWallet(user1Name,
                newTransaction(
                        user1Name,
                        user2Name,
                        transactionValue,
                        transactionName),
                users);

        addTransactionToWallet(
                user2Name,
                newTransaction(
                        user2Name,
                        user1Name,
                        -1*transactionValue,
                        transactionName),
                users);
        }

        System.out.println("Add new transaction(1) or quit(2)?");
        if(scanner.nextInt()==2)quit=false;
    }

    private String getAndCheckIfUserExists(List<User> users, String whichUser)
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

    private double checkIfOperationIsPossible(String user1Name) {

        Scanner scanner = new Scanner(System.in);
        double transactionValue = 0;

        System.out.println("Enter Transaction Value: ");
        try {
            transactionValue = scanner.nextDouble();
        }catch (Exception e)
        {
            throw new RuntimeException("Unsupported value: " + transactionValue);
        }
        while(transactionValue<=0)
        {
            System.out.println("Transaction value should be greater than 0");
            System.out.println("Enter transactionValue: ");
            transactionValue=scanner.nextDouble();
        }
        return transactionValue;
    }

    private String getTransactionName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Transaction Name: ");
        return scanner.nextLine();
    }

    private void finalizeTransaction(String user1Name, String user2Name, List<User> users, double transactionValue)
    {
        for(User user:users)
        {
            if(user.getName().equals(user1Name))
            {
                user.give(transactionValue);
            }
            else if(user.getName().equals(user2Name))
            {
                user.receive(transactionValue);
            }
        }
    }

    private Transaction newTransaction(String user1Name, String user2Name,
                                       double transactionValue,String transactionName)
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionValue);
        transaction.setTransactionName(transactionName);
        transaction.setTimestamp();
        return transaction;
    }
    private void addTransactionToWallet(String userName,Transaction transaction, List<User> users)
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
    public List<Transaction> getTransactions()
    {
        return this.transactions;
    }

    public void printTransactions() {

        for(Transaction transaction:transactions)
        {
            //do poprawy
            //Transactions: [blockchain.Transaction@78e03bb5, blockchain.Transaction@5e8c92f4]
            System.out.println("Name: "+transaction.getTransactionName());
            System.out.println("Value: "+ transaction.getTransactionValue());
            System.out.println("Time: "+ transaction.getTimestamp());
        }
    }

}

