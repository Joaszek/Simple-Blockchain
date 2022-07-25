package blockchain;


import users.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


public class Block {
    private LinkedList<Transaction> transactions;
    private String hash;
    private String previousHash;
    boolean isFirst;
    private int id;
    private BlockHeader blockHeader;

    Block(){
        //core
        this.isFirst=false;
        this.transactions=new LinkedList<>();
        try {
            this.hash=this.bytesToHex(this.hashCode());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //visualisation

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
    public void addTransactionToBlock(LinkedList<User> users)
    {

        Scanner scanner = new Scanner(System.in);
        String transactionName;
        double transactionValue;
        String user1Name;
        String user2Name;
        String temp1;
        Transaction temp;
        boolean quit=true;
        boolean isUser1Capable=false;
        boolean FoundUser1=false;
        boolean FoundUser2=false;

        int option;
        while(quit)
        {
            temp= new Transaction();
            System.out.println("Adding new transaction:");

            //checking if users exist
            System.out.println("Sender name: ");
            user1Name= scanner.nextLine();
            System.out.println("Recipient name: ");
            user2Name = scanner.nextLine();

            System.out.println("Transaction name: ");
            transactionName= scanner.nextLine();
            System.out.println("Transaction value: ");
            transactionValue = scanner.nextDouble();
            while(transactionValue<0)
            {
                System.out.println("Value must be greater/equal 0");
                transactionValue = scanner.nextDouble();
            }

            for(User user:users)
            {
                if(user.getName().equals(user1Name))FoundUser1=true;

                if(user.getName().equals(user2Name))FoundUser2=true;
            }

            if(FoundUser1 && FoundUser2)
            {
                for(User user : users)
                {
                    if (user.getName().equals(user1Name)&&user.checkIfCapable(transactionValue))
                    {
                        isUser1Capable=true;
                    }
                }
                if(isUser1Capable)
                {
                    for(User user : users)
                    {
                        if(user.getName().equals(user1Name))
                        {
                            //add transaction to block
                            temp.setTransactionName(transactionName);
                            temp.setTransactionValue(transactionValue);
                            temp.setTimestamp();
                            this.transactions.add(temp);

                            //add transaction to user1
                            user.addWalletTransaction(
                                    transactionName,
                                    (-1)*transactionValue);
                            user.give(transactionValue);
                        }
                        if(user.getName().equals(user2Name))
                        {
                            user.addWalletTransaction(transactionName,transactionValue);
                            user.receive(transactionValue);
                        }
                    }
                }
                else
                {
                    System.out.println("not enough coins");
                }
            }
            else
            {
                System.out.println("Didn't find the user");
            }
            FoundUser1=false;
            FoundUser2=false;
            System.out.println("Add new transaction(1) or quit(2)?");
            option=scanner.nextInt();
            isUser1Capable=false;
            //to take enter
            temp1=scanner.nextLine();
            if(option==2)quit=false;
        }
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
