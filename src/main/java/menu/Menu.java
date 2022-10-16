package menu;

import blockchain.*;

import logger.Logger;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final MenuOperations menuOperations;
    public Menu(MenuOperations operations)
    {
        this.menuOperations = operations;
        menu();
    }
    private void menu() {

        List<Block> blockchain = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Block lastBlock;
        BlockManager.setDifficultyTarget();
        menuOperations.createFirstUser(users);
        menuOperations.createFirstBlock(blockchain);

        boolean loop = true;
        while (loop) {
            //get option from menu
            int option = menuOperations.getOption(blockchain, users);
            lastBlock = menuOperations.setLastBlock(blockchain);
            switch (option) {
                case 1 -> menuOperations.addTransactionsToBlock(users, blockchain);
                case 2 -> menuOperations.searchBlockByHash(blockchain);
                case 3 -> menuOperations.searchBlockByUUID(blockchain);
                case 4 -> menuOperations.addNewUser(users);
                case 5 -> menuOperations.printUserInformation(users);
                case 6 -> menuOperations.showLastBlock(lastBlock);
                case 7 -> menuOperations.writeUsersToFile(users);
                case 8 -> menuOperations.writeBlockchainToFile(blockchain);
                case 9 -> menuOperations.readUsersJSON(users);
                case 10 -> menuOperations.endOperations();

                default -> {
                    //Logger.printUnsupportedOption(Menu.class);
                }
              }
        }
    }


}
