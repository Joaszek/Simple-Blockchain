package blockchain;


import com.google.common.hash.Hashing;
import menu.Menu;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
Transalte transactions into Strings, then connect each one of them and create hash
check if users aren't null
 */



public class Main {
    public static void main(String[]Args){
        //new Menu();
        Hash hash= new Hash();
        String example = hash.getExampleHash();
        System.out.println(example);
        System.out.println("a2358a7c4ab012e1ea06d629c04b9d7e23abce8690dbf986ae78d8393b16857a");
        System.out.println(example.length());
    }

}