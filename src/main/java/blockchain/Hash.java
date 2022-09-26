package blockchain;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Hash {
    public StringBuilder getHashedTransactions(List<Transaction> transactions)
    {
        StringBuilder hashedTransactions = null;
        String sha256hex = Hashing.sha256()
                .hashString("cosinu", StandardCharsets.UTF_8)
                .toString();
        for(Transaction transaction : transactions)
        {
            hashedTransactions.append(Hashing.sha256().hashString(transaction.getTransactionName(),StandardCharsets.UTF_8));
            hashedTransactions.append(Hashing.sha256().hashString(String.valueOf(transaction.getTransactionValue()),StandardCharsets.UTF_8));
            hashedTransactions.append(Hashing.sha256().hashString(String.valueOf(transaction.getTimestamp()),StandardCharsets.UTF_8));
        }
        return hashedTransactions;
    }
    public StringBuilder getHashedTimestamp()
    {
        //return new StringBuilder
        return null;
    }

}
