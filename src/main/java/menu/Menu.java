package menu;

import blockchain.*;

import users.User;

import java.util.List;

public class Menu {
    private List<Block> blockchain;
    private List<User> users;
    private String tempHash, name;
    private Block lastBlock;
    private Block secondLastBlock;
    private MenuOperations menuOperations;
    //private final Logger logger = Logger.getLogger(String.valueOf(Menu.class));
    public Menu(MenuOperations operations)
    {
        this.menuOperations = operations;
        menu();
    }
    private void menu() {

        boolean loop = true;
        while (loop) {
            //get option from menu
            int option = menuOperations.getOption(blockchain, users);
            lastBlock = menuOperations.setLastBlock(blockchain);
            switch (option) {
                case 1 -> menuOperations.addTransactionsToBlock(users, blockchain);
                case 2 -> menuOperations.searchBlockByHash(blockchain);
                case 3 -> menuOperations.searchBlockByID(blockchain);
                case 4 -> menuOperations.addNewUser(users);
                case 5 -> menuOperations.printUserInformation(users);
                case 6 -> menuOperations.showLastBlock(lastBlock);
                case 7 -> menuOperations.writeDataToFile(users);
                case 8 -> menuOperations.getDataFromFile(users);
                case 9 -> menuOperations.endOperations(loop);

                default -> {
                    throw new RuntimeException("Unsupported option: " + option);
                }
            }
        }
    }
}
