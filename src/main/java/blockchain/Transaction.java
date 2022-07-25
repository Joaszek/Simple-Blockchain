package blockchain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionName;
    private double transactionValue;
    private Timestamp timestamp;


    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp= Timestamp.valueOf(LocalDateTime.now());
    }
}
