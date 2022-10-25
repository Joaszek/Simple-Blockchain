package blockchain;

import hashing.Hash;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class Block {

    private List<Transaction> transactions = new LinkedList<>();
    private final LocalDateTime timeOfBlockCreation = LocalDateTime.now();
    private BlockHeader blockHeader;
    private static final int blockchainVersion=0;

    public long getTimeUsedToMine() {
        return timeUsedToMine;
    }

    public void setTimeUsedToMine(long timeUsedToMine) {
        this.timeUsedToMine = timeUsedToMine;
    }

    private long timeUsedToMine;

    public UUID getBlockUUID() {
        return blockID;
    }

    private final UUID blockID;

    private String hash;

    public Block(){
        this.blockID=UUID.randomUUID();
        this.blockHeader = new BlockHeader();
    }

    public Block(boolean isFirst)
    {
        this.blockID=UUID.randomUUID();
        BlockHeader header = new BlockHeader();
        header.setNonce(0);
        header.setMerkleRoot("");
        StringBuilder timeStamp = new StringBuilder();
        timeStamp.append(timeOfBlockCreation);
        header.setTimeStamp(timeStamp);
        this.setBlockHeader(header);
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public String getHash() {
        return hash;
    }

    private void setHash(String hash) {
        this.hash = hash;
    }

    public void sign(String previousHash)
    {
        StringBuilder timeStamp1= new StringBuilder(String.valueOf(LocalDateTime.now()));
        getBlockHeader().setTimeStamp(timeStamp1);

        int difficultyTarget =BlockHeader.getDifficultyTarget();
        int nonce = getBlockHeader().getNonce();
        String merkleRoot = getBlockHeader().getMerkleRoot();
        StringBuilder timeStamp = getBlockHeader().getTimeStamp();

        setHash(Hash.mine(
                this,nonce,previousHash,
                difficultyTarget,merkleRoot,
                timeStamp));
    }
}

