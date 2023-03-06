package Test.frontend;

import java.util.Scanner;

import Test.service.ReservationServiceTester;
import frontend.MainMenu;
import model.RoomType;

public class MainMenuTester {
    public static void reserveTest(Scanner scanner){
        ReservationServiceTester.addRoom();
        MainMenu.reserve(scanner);
    }

    public static void getCustomerReservations(Scanner scanner){
        ReservationServiceTester.addRoom();
        ReservationServiceTester.reserveARoom();
        MainMenu.getCustomerReservations(scanner);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        getCustomerReservations(scanner);
        scanner.close();
    }
}
