package blockchain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hash {
    //to hash transactions
    //make them power(2,n) to make binary tree
    public List<String> hashedTransactions(List<Transaction> transactions)
    {
        List<String> transactionsList = new LinkedList<String>();
        for(Transaction transaction : transactions)
        {
            transactionsList.add(Hashing.sha256().hashString(transaction.getTransactionName(),StandardCharsets.UTF_8).toString());
            transactionsList.add(Hashing.sha256().hashString(String.valueOf(transaction.getTransactionValue()),StandardCharsets.UTF_8).toString());
            transactionsList.add(Hashing.sha256().hashString(String.valueOf(transaction.getTimestamp()),StandardCharsets.UTF_8).toString());
        }
        return transactionsList;
    }
    public String createMerkelRoot(StringBuilder hashedTransactions)
    {
        return Hashing.sha256().hashString(hashedTransactions.toString(),StandardCharsets.UTF_8).toString();
    }
    public StringBuilder getHashedTimestamp(Timestamp timestamp)
    {
        StringBuilder hashedTimestamp = null;
        hashedTimestamp.append(Hashing.sha256().hashString(String.valueOf(timestamp),StandardCharsets.UTF_8));
        return hashedTimestamp;
    }
    private void changeTransactionsToTree(List<Transaction> transactions)
    {
        List<String> hashedTransactions = new ArrayList<String>();
        hashedTransactions = hashedTransactions(transactions);
        int maxSize = (int)Math.ceil(Math.log(transactions.size())/Math.log(2));
        for(int i=transactions.size()-1; i<Math.pow(2,maxSize); i++)
        {
            hashedTransactions.add(hashedTransactions.get(hashedTransactions.size()-1));
        }
        List<String> listOfHashedNodes = new ArrayList<String>();
        while()
    }

}
