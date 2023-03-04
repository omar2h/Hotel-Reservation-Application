package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.UserInput;
import util.printer.MenuPrinter;

public class MainMenu {
    private static MenuPrinter menuPrinter = new MenuPrinter();
    private static List<String> menuItems = new ArrayList<>(Arrays.asList("Find and reserve a room",
                                                                            "See my reservations",
                                                                            "Create an account",
                                                                            "Admin",
                                                                            "Exit"));
    public static void display(){
        menuPrinter.print(menuItems);
    }

    public static int getInput(int upperLimit) throws IllegalArgumentException {
        return UserInput.scanInput(upperLimit);
    }

    public static int getMenuItemsSize(){
        return menuItems.size();
    }
}
