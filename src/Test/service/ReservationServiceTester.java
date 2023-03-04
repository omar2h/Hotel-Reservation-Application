package Test.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.regex.Pattern;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;
import service.ReservationService;

public class ReservationServiceTester {
    static ReservationService reservationService = ReservationService.getReservationService();
    public static void AddRoom(){
        IRoom room1 = new Room("100", 150, RoomType.SINGLE);
        IRoom room2 = new Room("200", 150, RoomType.SINGLE);
        IRoom room3 = new Room("300", 150, RoomType.DOUBLE);

        // add rooms
        reservationService.addRoom(room1);
        reservationService.addRoom(room2);
        reservationService.addRoom(room3);

        reservationService.printAllRooms();
    }

    public static void AddDuplicateRoom() throws IllegalArgumentException {
        IRoom room1 = new Room("100", 150, RoomType.SINGLE);
        IRoom room2 = new Room("100", 150, RoomType.SINGLE);
        IRoom room3 = new Room("300", 150, RoomType.DOUBLE);

        // add rooms
        reservationService.addRoom(room1);
        reservationService.addRoom(room2);
        reservationService.addRoom(room3);

        reservationService.printAllRooms();
    }

    public static void ReserveARoom() throws IllegalArgumentException {
        Customer customer1 = new Customer("null", "null", "a@a.com");
        IRoom room1 = new Room("100", 150, RoomType.SINGLE);

        String dateStr1 = "01/01/2020";
        String dateStr2 = "10/01/2020";

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate myDateObj1 = LocalDate.parse(dateStr1, f);
        LocalDate myDateObj2 = LocalDate.parse(dateStr2, f);

        Reservation res = reservationService.reserveARoom(customer1, room1, myDateObj1, myDateObj2);

        System.out.println(res);
        System.out.println();
        reservationService.printAllReservations();
    }

    public static void findARoom() throws IllegalArgumentException {
        String dateStr1 = "01/01/2020";
        String dateStr2 = "10/01/2020";

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate myDateObj1 = LocalDate.parse(dateStr1, f);
        LocalDate myDateObj2 = LocalDate.parse(dateStr2, f);
        Collection<IRoom> rooms = reservationService.findRooms(myDateObj1, myDateObj2);
        System.out.println(rooms);
}

    public static void main(String[] args) {
        try{
            
            AddRoom();
            ReserveARoom();
            findARoom();

        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
