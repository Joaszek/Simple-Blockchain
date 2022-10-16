package JSON;


import blockchain.Block;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import users.User;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteToFile {


    public void writeUsersToFile(@NotNull List<User> users) throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/users.json"),users);
    }
    public void writeBlockchainToFile(List<Block> blockchain) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/blockchain.json"),blockchain);
    }

}