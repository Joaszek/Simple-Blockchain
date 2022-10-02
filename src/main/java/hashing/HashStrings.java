package hashing;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashStrings{
    public static String hash(String toHash)
    {
        return Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();
    }
}
