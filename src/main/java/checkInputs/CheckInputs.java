package checkInputs;

import java.util.Scanner;

public class CheckInputs {
    public static int getCorrectInput(int min, int max, String message)
    {
        boolean isCorrect = false;
        int ret = min;
        var scanner = new Scanner(System.in);
        while(!isCorrect) {
            var input = scanner.nextLine();
            try {
                ret = Integer.parseInt(input);
                if(ret >= min && ret <= max) {
                    isCorrect = true;
                }
                else
                {
                    System.out.println(message);
                }
            } catch (NumberFormatException e) {
                System.out.println(message);
            }
        }
        return ret;
    }
}
