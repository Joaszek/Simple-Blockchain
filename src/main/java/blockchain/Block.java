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
    private static int id;
    private Timestamp timeOfBlockCreation;
    private BlockHeader blockHeader;
    private static int blockchainVersion;

    private String hash;

    public Block(){
        //core
        this.transactions=new LinkedList<>();
        this.timeOfBlockCreation= Timestamp.valueOf(LocalDateTime.now());
        blockchainVersion=0;
        id++;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static int getId() {
        return id;
    }
    public Timestamp getTimeOfBlockCreation() {
        return timeOfBlockCreation;
    }
    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public static int getBlockchainVersion() {
        return blockchainVersion;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}

