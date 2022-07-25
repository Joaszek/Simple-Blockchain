package blockchain;

import java.sql.Timestamp;

public class BlockHeader {

    private String previousBlockHeaderHash;
    private Timestamp date;
    private String transactionsHash;

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
}
