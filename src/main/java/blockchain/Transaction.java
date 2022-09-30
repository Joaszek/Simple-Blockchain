package blockchain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionName;
    private double transactionValue;
    private LocalDateTime dateTime;


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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTimestamp() {
        this.dateTime= LocalDateTime.now();
    }


}
