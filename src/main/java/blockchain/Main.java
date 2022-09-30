package blockchain;


import menu.Menu;
import menu.MenuExecute;



public class Main {
    public static void main(String[]Args){
        MenuExecute menuOperations = new MenuExecute();
        new Menu(menuOperations);
    }


}