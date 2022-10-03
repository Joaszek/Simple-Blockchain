package hashing;

import blockchain.Block;
import blockchain.Transaction;
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
            transactionsList.add(HashStrings.hash(transaction.getTransactionName()));
            transactionsList.add(HashStrings.hash(String.valueOf(transaction.getTransactionValue())));
            transactionsList.add(HashStrings.hash(String.valueOf(transaction.getDateTime())));
        }
        return transactionsList;
    }
    private String hashTwoNodesIntoOne(String hashedTransactions)
    {
        return HashStrings.hash(hashedTransactions);
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

    public static String mine(Block block,int nonce, String previousHash, int difficultyTarget, String merkleRoot, StringBuilder timeStamp)
    {
        String subStringOfPreviousHash= previousHash.substring(0, difficultyTarget);
        String subStringOfCurrentHash=createBlockHash(nonce,previousHash,merkleRoot, timeStamp).substring(0,difficultyTarget);
        String finalHash=null;
        while(!subStringOfCurrentHash.equals(subStringOfPreviousHash))
        {
            finalHash = createBlockHash(nonce,previousHash,merkleRoot, timeStamp);
            subStringOfCurrentHash= finalHash.substring(0,difficultyTarget);
            nonce++;
        }
        block.getBlockHeader().setNonce(nonce);
        return finalHash;
    }
    private static String createBlockHash(int nonce, String previousHash,
                                  String merkleRoot, StringBuilder timeStamp)
    {
        return HashStrings.hash(String.valueOf(nonce)
                +previousHash
                +timeStamp
                +merkleRoot);
    }
}
