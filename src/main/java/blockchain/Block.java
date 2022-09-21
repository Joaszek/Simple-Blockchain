package blockchain;


import users.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static jdk.vm.ci.meta.ValueKind.Illegal;


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
        try {
            this.hash=this.bytesToHex(this.hashCode());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        this.blockHeader=new BlockHeader();

    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public void setFirst()
    {
        this.isFirst=true;
        this.previousHash= null;
    }
    public void seeBlock()
    {
        System.out.println("Hash: "+this.getHash());
        System.out.println("Previous Hash: "+this.getPreviousHash());
        System.out.println("Transactions: ");
        this.getTransactions();
        System.out.println();
        System.out.println("Block Header");
        System.out.println("Previous Block Header hash"+this.blockHeader.getPreviousBlockHeaderHash());
        System.out.println("Previous Block hash: "+this.getPreviousHash());
        System.out.println("Transaction hash: " + this.blockHeader.getTransactionsHash());
        System.out.println("Timestamp: "+ this.blockHeader.getDate());
    }

    //////
    //rozdzielić interakcje, dodawać przez argumenty
    //////
    public void addTransactionToBlock(List<User> users)
    {

        Scanner scanner = new Scanner(System.in);
        double transactionValue;
        String user1Name, user2Name, transactionName;
        Transaction temp;
        boolean quit=true;
        boolean isUser1Capable=false;

        int option;
        while(quit)
        {
        System.out.println("TRANSACTION");

        System.out.println("Enter User 1 Name: ");
        user1Name=checkIfUserExists(users);

        System.out.println("Enter User 2 Name: ");
        user2Name=checkIfUserExists(users);

        transactionValue=checkIfOperationIsPossible(user1Name);

        transactionName=settransactionName();
        this.transactions.add(newTransaction(user1Name,user2Name,transactionValue));
        addTransactionToWallet(newTransaction(user1Name,user2Name,transactionValue));
        addTransactionToWallet(newTransaction(user2Name,user1Name,-1*transactionValue));

        }

        System.out.println("Add new transaction(1) or quit(2)?");
        option=scanner.nextInt();
        isUser1Capable=false;
        //to take enter
        temp1=scanner.nextLine();
        if(option==2)quit=false;

    }

    private String settransactionName()
    {
        System.out.println("Enter Transaction Name: ");
        transactionName=scanner.nextLine();
        return transactionName;
    }


    private String checkIfUserExists(List<User> users)
    {
        Scanner scanner = new Scanner(System.in);
        String userName = null;
        System.out.println("Sign user name: ");
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
        System.out.println("Didn't find user name: "+ userName);
        return null;
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

    private Transaction newTransaction(String user1Name, String user2Name, double transactionValue)
    {
        Transaction transaction = new Transaction();
        transaction.setTransactionValue(transactionValue);
        transaction.setTransactionName();
    }

    public void setPreviousHash(Block block)
    {
        this.previousHash=block.getHash();
    }
    public String getHash()
    {
        return this.hash;
    }

    public void getTransactions() {

        for(Transaction transaction:transactions)
        {
            //do poprawy
            //Transactions: [blockchain.Transaction@78e03bb5, blockchain.Transaction@5e8c92f4]
            System.out.println("Name: "+transaction.getTransactionName());
            System.out.println("Value: "+ transaction.getTransactionValue());
            System.out.println("Time: "+ transaction.getTimestamp());
        }
    }




    public String getPreviousHash() {
        return previousHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private String bytesToHex(Integer hashCode) throws NoSuchAlgorithmException {
        String hash = String.valueOf(hashCode);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                hash.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void setBlockHeaderTransactionsHash() throws NoSuchAlgorithmException {
        blockHeader.setTransactionsHash(bytesToHex(blockHeader.hashCode()));
    }
    public void setBlockHeaderPreviousBlockHeaderHashCode(Block block)
    {
        blockHeader.setPreviousBlockHeaderHash(block.getHash());
    }
    public void setBlockHeaderTimestamp()
    {
        blockHeader.setDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}
