import java.util.Scanner;

import frontend.MainMenu;

public class App {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hotel Reservation Application");
        MainMenu.start(scanner);
        scanner.close();

    }
}
