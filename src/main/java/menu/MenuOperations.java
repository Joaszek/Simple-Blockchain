package menu;

import blockchain.Block;
import users.User;

import java.util.List;

public interface MenuOperations {

    public int getOption(List<Block> blockchain, List<User> users);
    public void addTransactionsToBlock(List<User> users, List<Block> blockchain);
    public void searchBlockByHash(List<Block> blockchain);
    public void searchBlockByID(List<Block> blockchain);
    public void addNewUser(List<User> users);
    public void printUserInformation(List<User> users);
    public void showLastBlock(Block lastBlock);
    public void writeDataToFile(List<User> users);
    public void getDataFromFile(List<User> users);
    public void endOperations(boolean loop);
    public Block setLastBlock(List<Block> blockchain);
}
