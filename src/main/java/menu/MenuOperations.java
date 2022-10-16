package menu;

import blockchain.Block;
import users.User;

import java.util.List;

public interface MenuOperations {

    int getOption(List<Block> blockchain, List<User> users);
    void addTransactionsToBlock(List<User> users, List<Block> blockchain);
    void searchBlockByHash(List<Block> blockchain);
    void searchBlockByUUID(List<Block> blockchain);
    void addNewUser(List<User> users);
    void printUserInformation(List<User> users);
    void showLastBlock(Block lastBlock);
    void writeUsersToFile(List<User> users);
    void writeBlockchainToFile(List<Block> blockchain);
    void endOperations();
    Block setLastBlock(List<Block> blockchain);
    void createFirstUser(List<User> users);
    void createFirstBlock(List<Block> blockchain);
    int userChoseWrongOption();
}
