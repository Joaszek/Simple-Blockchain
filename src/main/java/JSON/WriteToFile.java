package JSON;


import blockchain.Transaction;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import users.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WriteToFile {

    @SuppressWarnings("unchecked")
    public static void writeToFile(@NotNull LinkedList<User> users) throws IOException {

        JSONObject tempUser;
        JSONObject finalTempUser;
        JSONArray jsonArray = new JSONArray();
        JSONObject tempTransaction = new JSONObject();
        int iterator = 0;
        for (User user : users) {
            iterator = 0;
            tempUser = new JSONObject();
            finalTempUser = new JSONObject();
            tempUser.put("Name:", user.getName());
            tempUser.put("User ID:", user.getIdNumber());
            tempUser.put("Wallet:", user.getWalletBalance());

            for (Transaction transaction : user.getWalletTransactions()) {
                tempTransaction.put("Transaction Name: ", transaction.getTransactionName());
                tempTransaction.put("Transaction Value: ", transaction.getTransactionValue());
                tempTransaction.put("Transaction time: ", transaction.getDateTime().toString());
                tempUser.put("Transaction nr " + iterator, tempTransaction);
                iterator++;
            }
            finalTempUser.put("User: ", tempUser);
            jsonArray.add(finalTempUser);
        }
        try (FileWriter file = new FileWriter("C:\\Users\\jszewior\\Downloads\\Simple Blockchain\\src\\main\\java\\readJSON\\UsersDoc.json")) {
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}