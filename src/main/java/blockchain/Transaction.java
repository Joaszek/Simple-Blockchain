package blockchain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionName;
    private BigDecimal transactionValue;
    private LocalDateTime dateTime;


    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTimestamp() {
        this.dateTime= LocalDateTime.now();
    }

}
