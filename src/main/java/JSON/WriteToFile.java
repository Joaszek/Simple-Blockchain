package JSON;


import blockchain.Block;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import users.User;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WriteToFile {


    public void writeUsersToFile(@NotNull List<User> users) throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        String usersAsString  = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
        objectMapper.writeValue(new File("src/main/resources/users.json"),usersAsString);
    }
    public void writeBlockchainToFile(List<Block> blockchain) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter();
        objectMapper.writeValue(new File("src/main/resources/blockchain.json"),blockchain);
    }

}