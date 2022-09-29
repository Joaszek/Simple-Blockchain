package menu;

import blockchain.Block;
import org.jetbrains.annotations.NotNull;
import users.User;

import java.util.List;

public interface MenuOperations {

    public int getOption(List<Block> blockchain, List<User> users);
    public void addTransactionToBlock();
    public void searchBlockByHash(List<Block> blockchain);
    public void searchBlockByID(List<Block> blockchain);
    public void addNewUser(List<User> users);
    public void printUserInformation();
    public void showLastBlock();
    public void writeDataToFile();
    public void getDataFromFile();
    public void endOperations();

}
