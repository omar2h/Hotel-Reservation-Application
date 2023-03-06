package Test.frontend;

import java.util.Scanner;

import frontend.AdminMenu;

public class AdminMenuTester {
    public static void addRoom(Scanner scanner){
        AdminMenu.addARoom(scanner);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminMenu.populateTestData();
        AdminMenu.displayAllRooms();
        AdminMenu.displayAllCustomers();
        AdminMenu.displayAllReservations();
        scanner.close();
    }
}
