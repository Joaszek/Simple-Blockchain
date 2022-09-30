package hashing;

import blockchain.Transaction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hash {
    //to hash transactions
    private List<String> hashedTransactions(List<Transaction> transactions)
    {
        List<String> transactionsList = new LinkedList<String>();
        for(Transaction transaction : transactions)
        {/*
            String.valueOf(transaction.getDateTime()
            transactionsList.add(HashStrings.hash(transaction.getTransactionName()));
            transactionsList.add(String.valueOf(transaction.getTransactionValue());
            transactionsList.add(HashStrings.hash(transaction.getTransactionName());*/
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

        while(hashedTransactions.size()!=1)
        {
            for(int i=0;i<hashedTransactions.size();i+=2)
            {
                String currentTransactionToHash="";
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

    public int mine(int nonce, String previousHash)
    {
        while(!Hashing.sha256().hashString(String.valueOf(nonce),
                StandardCharsets.UTF_8).toString().equals(previousHash))
        {
            nonce++;
        }
        return nonce;
    }
    public String createBlockHash(int nonce, String previousHash,
                                  String merkleRoot, StringBuilder timeStamp)
    {
        return Hashing.sha256().hashString(
                String.valueOf(nonce)
                +previousHash
                +timeStamp
                +merkleRoot,
                StandardCharsets.UTF_8).toString();
    }
}
