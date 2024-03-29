package util;

import java.util.List;
import java.util.Scanner;

import util.validator.StringValidator;
import util.validator.Validator;

public class UserInput {
    public static int scanInput(Scanner scanner, int upperLimit, String s, boolean cancel){
        if(!s.isEmpty())
            System.out.println(s);

        String input = scanner.nextLine();

        int option = Integer.parseInt(input);
        if(cancel && option == -1)
            return option;
        if(option < 1 || option > upperLimit)
            throw new IllegalArgumentException("Invalid input! Enter a number between 1-"+ upperLimit);
        return option;
    }

    public static String scanInput(Scanner scanner, List<String> options, String s, String err){
        if(!s.isEmpty())
            System.out.println(s);

        String input = scanner.nextLine();

        if(!options.contains(input))
            throw new IllegalArgumentException(err);
        return input;
    }

    public static String scanInput(Scanner scanner, String s, String regex){
        Validator validator = new StringValidator();
        if(!s.isEmpty())
            System.out.println(s);
        String input = scanner.nextLine().trim();
        if(!validator.isValid(input, regex))
            throw new IllegalArgumentException("Invalid input!");
        return input;
    }
}
