package blockchain;

import java.sql.Timestamp;
import java.util.List;

public class BlockHeader {

    private String previousBlockHeaderHash;
    private Timestamp date;
    private String transactionsHash;

    private String MerkleRoot;

    private int nonce;
    private String timeStampHashed;

    public String getPreviousBlockHeaderHash() {
        return previousBlockHeaderHash;
    }

    public void setPreviousBlockHeaderHash(String previousBlockHeaderHash) {
        this.previousBlockHeaderHash = previousBlockHeaderHash;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTransactionsHash() {
        return transactionsHash;
    }

    public void setTransactionsHash(String transactionsHash) {
        this.transactionsHash = transactionsHash;
    }

    public String getMerkleRoot() {
        return MerkleRoot;
    }
    private void setMerkleRoot(String merkleRoot)
    {

    }

    public void setTimestampHashed(String bytesToHex) {
        this.timeStampHashed = bytesToHex;
    }
}
