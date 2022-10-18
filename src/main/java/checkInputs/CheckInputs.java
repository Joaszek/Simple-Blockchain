package checkInputs;

import java.util.Scanner;

public class CheckInputs {
    public static int getCorrectInput(int min, int max) {
        boolean isCorrect = false;
        int ret = min;
        var scanner = new Scanner(System.in);
        while(!isCorrect) {
            var input = scanner.next();
            try {
                ret = Integer.parseInt(input);
                if(ret >= min && ret <= max) {
                    isCorrect = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input");
            }
        }
        return ret;
    }
}
