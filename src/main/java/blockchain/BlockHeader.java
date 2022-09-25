package blockchain;

import java.sql.Timestamp;
import java.util.List;

public class BlockHeader {

    private String previousBlockHeaderHash;
    private String epochTime;
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

    public String getDate() {
        return epochTime;
    }

    public void setDate(String epochTime) {
        this.epochTime = epochTime;
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
    public int getNonce()
    {
        return nonce;
    }
    public void setNonce(int nonce)
    {
        this.nonce = nonce;
    }

    public void setTimestampHashed(String bytesToHex) {
        this.timeStampHashed = bytesToHex;
    }
}
