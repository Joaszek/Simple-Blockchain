package JSON;

import blockchain.Transaction;
import com.google.common.io.Files;
import logger.Logger;
//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import users.User;
import org.json.JSONArray;
import org.json.JSONObject;
import wallet.Wallet;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class WriteFromFile {

    //check if file exists
    public void writeFromFile(List<User> users) {
        //InputStream is =  WriteFromFile.class.getClassLoader().getResourceAsStream("C:\\Users\\Joachim\\Backend\\Simple-Blockchain\\src\\main\\resources\\users.json");

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/resources/usersToRead.json"));
            JSONObject objJsonObject = new JSONObject(obj.toString());
            JSONArray jsonArray = objJsonObject.getJSONArray("users");

            for (int i = 0; i < jsonArray.length(); i++) {

                User user = new User();
                String uuid = jsonArray.getJSONObject(i).getString("uuid");
                user.setUuid(UUID.fromString(uuid));
                String name = jsonArray.getJSONObject(i).getString("name");
                user.setName(name);
                JSONObject walletJSON = jsonArray.getJSONObject(i).getJSONObject("wallet");

                Wallet wallet = new Wallet();


                JSONArray transactions = walletJSON.getJSONArray("transactions");
                for (int j = 0; j < transactions.length(); j++)
                {
                    Transaction transaction = new Transaction();


                    String transactionName = transactions.getJSONObject(0).get("transactionName").toString();
                    transaction.setTransactionName(transactionName);

                    String transactionValue = transactions.getJSONObject(0).get("transactionValue").toString();
                    transaction.setTransactionValue(BigDecimal.valueOf(Double.parseDouble(transactionValue)));


                    JSONObject dateTime = transactions.getJSONObject(0).getJSONObject("dateTime");

                    String year = dateTime.get("year").toString();
                    int intYear = Integer.parseInt(year);

                    String month = dateTime.get("monthValue").toString();
                    int intMonth = Integer.parseInt(month);

                    String dayOfMonth = dateTime.get("dayOfMonth").toString();
                    int intDayOfMonth = Integer.parseInt(dayOfMonth);

                    String hour = dateTime.get("hour").toString();
                    String minute = dateTime.get("minute").toString();
                    String second = dateTime.get("second").toString();
                    String nano = dateTime.get("nano").toString();
                    LocalDateTime localDate = new LocalDateTime(LocalDateTime.of());
                }

                String balance = walletJSON.get("balance").toString();
            }
//
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}