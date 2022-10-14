package blockchain;


import menu.Menu;
import menu.MenuExecute;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[]Args)
    {
        //new Menu(new MenuExecute());
        List<Integer> args = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            args.add(i);
        }
        List<Integer> result = new ArrayList<>(args);
        for(int i=0;i<10;i++)
        {
            result.set(i, i * 10);
        }
        for(int i=0;i<10;i++)
        {
            System.out.println(args.get(i));
            System.out.println(result.get(i));
        }
    }


}