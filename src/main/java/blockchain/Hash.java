package blockchain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hash {
    //to hash transactions
    private List<String> hashedTransactions(List<Transaction> transactions)
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
    private String hashTwoNodesIntoOne(String hashedTransactions)
    {
        return Hashing.sha256().hashString(hashedTransactions,StandardCharsets.UTF_8).toString();
    }
    public String getMerkleRoot(List<Transaction> transactions)
    {
        List<String> hashedTransactions = new ArrayList<String>();
        hashedTransactions = hashedTransactions(transactions);
        int maxSize = (int)Math.ceil(Math.log(transactions.size())/Math.log(2));
        for(int i=transactions.size()-1; i<Math.pow(2,maxSize); i++)
        {
            hashedTransactions.add(hashedTransactions.get(hashedTransactions.size()-1));
        }
        List<String> listOfHashedNodes = new ArrayList<>();
        String currentTransactionToHash;


        while(hashedTransactions.size()!=1)
        {
            for(int i=0;i<hashedTransactions.size();i+=2)
            {
                currentTransactionToHash="";
                currentTransactionToHash +=hashedTransactions.get(i)+hashedTransactions.get(i+1);
                currentTransactionToHash = hashTwoNodesIntoOne(currentTransactionToHash);
                listOfHashedNodes.add(currentTransactionToHash);
            }
            hashedTransactions.clear();
            hashedTransactions.addAll(listOfHashedNodes);
            listOfHashedNodes.clear();
        }
        return hashedTransactions.get(0);
    }
    //hashing timestamp
    public StringBuilder getHashedTimestamp(Timestamp timestamp)
    {
        StringBuilder hashedTimestamp = null;
        hashedTimestamp.append(Hashing.sha256().hashString(String.valueOf(timestamp),StandardCharsets.UTF_8));
        return hashedTimestamp;
    }
    public void mine(int nonce, String previousHash)
    {
        while(!Hashing.sha256().hashString(String.valueOf(nonce),
                StandardCharsets.UTF_8).toString().equals(previousHash))
        {
            nonce++;
        }
    }
    public String createBlockHash(int nonce, String previousHash, String merkleRoot, String timeStamp)
    {
        return Hashing.sha256().hashString(
                String.valueOf(nonce)
                +previousHash
                +timeStamp
                +merkleRoot,
                StandardCharsets.UTF_8).toString();
    }
}
