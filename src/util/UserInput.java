package util;

import java.util.Scanner;

public class UserInput {
    public static int scanInput(int upperLimit){
        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());
        if(option < 1 || option > upperLimit)
            throw new IllegalArgumentException("Invalid input! Enter a number between 1-"+upperLimit);
        return option;
        
    }
}
