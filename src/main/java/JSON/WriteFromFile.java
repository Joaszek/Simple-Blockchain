package JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import users.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class WriteFromFile {
    @SuppressWarnings("unchecked")
    public static void writeFromFile(LinkedList<User> users) {
        //InputStream is =  WriteFromFile.class.getClassLoader().getResourceAsStream("/resource/test.txt");

        User tempUser;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\jszewior\\Downloads\\Simple Blockchain\\src\\main\\java\\JSON\\writeFromFile.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;
            System.out.println(jsonArray);
            jsonArray.forEach(user->parseUsersObjects( (JSONObject) user));
        } catch (FileNotFoundException e)
        {
            //e.printStackTrace(); zamien na loggera
        }  catch (ParseException e)
        {
            e.printStackTrace();
        }catch (IOException e ) {
//e.printStackTrace(); zamien na loggera        }
        }

    }
    private static void parseUsersObjects(JSONObject user)
    {
        JSONObject userObject = (JSONObject) user.get("User: ");
        System.out.println("User:");
        String name = (String) userObject.get("Name:");
        System.out.println("Name: "+name);
        String IdNumber = String.valueOf(userObject.get("User ID:"));
        System.out.println("ID:"+IdNumber);
        Double wallet = (Double) userObject.get("Wallet:");
        System.out.println("Wallet:"+wallet);
    }
}
