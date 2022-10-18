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


    public void writeFromFile(List<User> users) {

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
                    int intHour = Integer.parseInt(hour);

                    String minute = dateTime.get("minute").toString();
                    int intMinute = Integer.parseInt(minute);

                    String second = dateTime.get("second").toString();
                    int intSecond = Integer.parseInt(second);

                    String nano = dateTime.get("nano").toString();
                    int intNano = Integer.parseInt(nano);

                    LocalDateTime localDate = LocalDateTime.of(intYear,intMonth,intDayOfMonth,intHour,intMinute,intSecond,intNano);
                    transaction.setLocalDateTimeFromJSON(localDate);
                    wallet.addTransactions(transaction);
                }

                String balanceJSON = walletJSON.get("balance").toString();
                BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(balanceJSON));
                wallet.addBalance(balance);
                user.setWallet(wallet);
                users.add(user);
            }

//
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}