package JSON;

import logger.Logger;
import users.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WriteFromFile {

    public void writeFromFile(List<User> users) {
        //InputStream is =  WriteFromFile.class.getClassLoader().getResourceAsStream("C:\\Users\\Joachim\\Backend\\Simple-Blockchain\\src\\main\\resources\\users.json");
        InputStream inputStream=null;
        byte[] array = new byte[1000];

        try
        {
            inputStream=new FileInputStream("C:\\Users\\Joachim\\Backend\\Simple-Blockchain\\src\\main\\resources\\users.json");
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
            inputStream.close();
            //https://www.javatpoint.com/iterate-json-array-java
        }catch (IOException e)
        {
            Logger.printError(e,WriteFromFile.class);
        }
    }
}
