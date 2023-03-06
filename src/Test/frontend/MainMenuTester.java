package Test.frontend;

import java.util.Scanner;

import Test.service.ReservationServiceTester;
import frontend.MainMenu;

public class MainMenuTester {
    public static void reserveTest(Scanner scanner){
        ReservationServiceTester.AddRoom();
        MainMenu.reserve(scanner);
    } 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        reserveTest(scanner);
        scanner.close();
    }
}
