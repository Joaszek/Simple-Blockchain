package JSON;

import logger.Logger;
//import org.json.simple.JSONObject;
import users.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteFromFile {

    //check if file exists
    public void writeFromFile(List<User> users) {
        //InputStream is =  WriteFromFile.class.getClassLoader().getResourceAsStream("C:\\Users\\Joachim\\Backend\\Simple-Blockchain\\src\\main\\resources\\users.json");
        InputStream inputStream=null;
        byte[] array = new byte[10000];

        try
        {
            inputStream=new FileInputStream("src/main/resources/usersToRead.json");
        }catch (IOException e)
        {
            Logger.printError(e,WriteFromFile.class);
        }
        try {
            assert inputStream != null;
            System.out.println("Bytes: "+inputStream.available());
            inputStream.read(array);
            String data = new String(array);
            System.out.println(data);
//            JSONObject json = new JSONObject(data);

  //          JSONArray usersArray = json.getJSONArray("id");

    //        System.out.println(usersArray);
    //        JSONArray jsonArray = new JSONArray(.getEntity(String.class));
    //        for(int i =0; i< jsonArray.length(); i++){
    //            if(jsonArray.get(i) instanceof JSONObject){
            // JSONObject jsnObj = (JSONObject)jsonArray.get(i);
    //                String finalValue = (String)jsnObj.get("id");
    //            }
    //        }




            inputStream.close();
            //https://www.javatpoint.com/iterate-json-array-java
        }catch (IOException e)
        {
            Logger.printError(e,WriteFromFile.class);
        }
        String data = "{\"data\":[{\"username\":\"ramu\",\"email\":\"ramu@sneppets.com\",\"is_enabled\":true,\"_id\":\"123597\"},{\"username\":\"raju\",\"email\":\"raju@sneppets.com\",\"is_enabled\":true,\"_id\":\"123598\"}],\"meta\":{\"total\":2,\"references\":{}}}";
        JSONObject jsonObj = new JSONObject(data);
        System.out.println(data);
        List<String> idList = getIdList(jsonObj);
        System.out.println("List of user id's : " + idList);
    }

    private static List<String> getIdList(JSONObject jsonObj) {
        List<String> idList = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(jsonObj.get("data").toString());
        if (jsonArr.length() > 0) {
            for (int i=0; i<jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                idList.add((obj.get("_id").toString()));
            }
        }
        return idList;
    }
}
