package blockchain;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import JSON.WriteFromFile;
import JSON.WriteToFile;
import users.*;

// TODO: 15.07.2022
/*
add junit tests
Transalte transactions into Strings, then connect each one of them and create hash
check if users aren't null
 */

/*
Done:

 */


public class Main {


    public static void main(String[]Args){

        //variables to blockchain
        boolean  loop=true;
        List<Block> blockchain = new ArrayList<>();
        List<User> users = new ArrayList<>();

        //variables to menu

        Scanner scanner = new Scanner(System.in);
        int idNumber=0;


        User user1=new User();

        //set first block
        String hashOption;
        blockchain.add(new Block());
        blockchain.get(0).setFirst();
        currentBlock=blockchain.get(0);
        blockchain.get(0).setId(0);
        idNumber++;

        System.out.println(user1.returnID());
        Log4j
        process(loop, blockchain, users, scanner, idNumber, user1);

    }
}
