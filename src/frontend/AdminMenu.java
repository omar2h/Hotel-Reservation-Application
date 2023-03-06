package frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomFactory;
import model.RoomType;
import util.UserInput;
import util.printer.MenuPrinter;
import util.printer.OptionsPrinter;
import util.printer.Printer;

public class AdminMenu {
    private static AdminResource adminResourceInstance = AdminResource.getAdminResource();
    private static HotelResource hotelResource = HotelResource.getHotelResource();
    static DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static MenuPrinter menuPrinter = new MenuPrinter();
    private static final String roomNumberRegex = "^(\\d)+$";
    private static final List<String> yesNoAnswers = new ArrayList<>(Arrays.asList("y", "n", "Y", "N"));
    private static final List<Object> menuItems = new ArrayList<>(Arrays.asList("See all Customers",
                                                                            "See all Rooms",
                                                                            "See all Reservations",
                                                                            "Add a Room",
                                                                            "Add Test Data",
                                                                            "Back to Main Menu"));
    private static final List<Object> roomTypes = new ArrayList<>(Arrays.asList("Single Room", "Double Room"));
    public static void display(){
        menuPrinter.print(menuItems);
    }

    public static void start(Scanner scanner){
        int option;
        boolean[] isRunning = new boolean[]{true};
        while(isRunning[0]){
            try{
                display();
                option = getInput(scanner, getMenuItemsSize());
                run(scanner, option, isRunning);
            } catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                System.out.println();
                continue;
            }
        }
    }

    public static void run(Scanner scanner, int option, boolean[] isRunning){
        switch (option) {
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllRooms();
                break;
            case 3:
                displayAllReservations();
                break;
            case 4:
                addARoom(scanner);
                break;
            case 5:
                populateTestData();
                break;
            case 6:
                isRunning[0] = false;
                break;
        
            default:
                break;
        }
    }

    public static int getInput(Scanner scanner, int upperLimit) throws IllegalArgumentException {
        return UserInput.scanInput(scanner, upperLimit, "", false);
    }

    public static void displayAllCustomers(){
        Collection<Customer> allCustomers = adminResourceInstance.getAllCustomers();
        if(allCustomers.isEmpty()){
            System.out.println("No customers found");
            System.out.println();
            return;
        }
        for(Customer customer : allCustomers){
            System.out.println(customer);
            System.out.println();
        }
    }

    public static void displayAllRooms(){
        Collection<IRoom> allRooms = adminResourceInstance.getAllRooms();
        if(allRooms.isEmpty()){
            System.out.println("No rooms found");
            System.out.println();
            return;
        }
        for(IRoom room : allRooms){
            System.out.println(room);
            System.out.println();
        }
    }

    public static void displayAllReservations(){
        adminResourceInstance.displayAllReservations();
    }

    public static void addARoom(Scanner scanner){
        IRoom room;
        String roomNumber;
        double roomPrice = 0;
        RoomType roomType;

        roomNumber = getRoomNumber(scanner);
        roomPrice = getRoomPrice(scanner);
        roomType = getRoomType(scanner);
        room = RoomFactory.getRoom(roomNumber, roomPrice, roomType);
        try{
            adminResourceInstance.addRoom(room);
            System.out.println();
            System.out.println("Room Added!");
        } catch(Exception e){
            System.out.println();
            System.out.println(e.getLocalizedMessage());
            return;
        }
    }

    public static void populateTestData(){
        // add rooms
        IRoom freeRoom1 = new FreeRoom("10", RoomType.SINGLE);
        IRoom room1 = new Room("100", 150, RoomType.SINGLE);
        IRoom room2 = new Room("200", 250, RoomType.SINGLE);
        IRoom room3 = new Room("300", 350, RoomType.SINGLE);
        IRoom room4 = new Room("400", 450, RoomType.DOUBLE);
        IRoom room5 = new Room("500", 550, RoomType.DOUBLE);
        IRoom room6 = new Room("600", 650, RoomType.DOUBLE);

        adminResourceInstance.addRoom(freeRoom1);
        adminResourceInstance.addRoom(room1);
        adminResourceInstance.addRoom(room2);
        adminResourceInstance.addRoom(room3);
        adminResourceInstance.addRoom(room4);
        adminResourceInstance.addRoom(room5);
        adminResourceInstance.addRoom(room6);

        // add customers
        hotelResource.createACustomer("r.harris@mail.com", "Rebecca", "Harris");
        hotelResource.createACustomer("a.stewart@mail.com", "Albert", "Stewart");
        hotelResource.createACustomer("f.walker@mail.com", "Frederick", "Walker");
        hotelResource.createACustomer("b.perry@mail.com", "Briony", "Perry");
        hotelResource.createACustomer("r.armstrong@mail.com", "Ryan", "Armstrong");

        // add reservations
        LocalDate checkIn1 = LocalDate.parse("01/01/2020", f);
        LocalDate checkOut1 = LocalDate.parse("10/01/2020", f);
        LocalDate checkIn2 = LocalDate.parse("01/02/2020", f);
        LocalDate checkOut2 = LocalDate.parse("10/02/2020", f);
        LocalDate checkIn3 = LocalDate.parse("01/03/2020", f);
        LocalDate checkOut3 = LocalDate.parse("10/03/2020", f);

        try{
            hotelResource.bookARoom("r.harris@mail.com", room1, checkIn1, checkOut1);
            hotelResource.bookARoom("a.stewart@mail.com", room2, checkIn2, checkOut2);
            hotelResource.bookARoom("f.walker@mail.com", room1, checkIn3, checkOut3);
            hotelResource.bookARoom("b.perry@mail.com", room3, checkIn1, checkOut1);
            hotelResource.bookARoom("r.armstrong@mail.com", room3, checkIn2, checkOut2);
        }catch(Exception e){
            System.out.println();
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static String getRoomNumber(Scanner scanner){
        String roomNumber= "";
        boolean validRoomNumber = false;
        while(!validRoomNumber){
            try{
                roomNumber = UserInput.scanInput(scanner, "Enter room number", roomNumberRegex);
                validRoomNumber = true;
            } catch (Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                System.out.println();
            }
        }
        return roomNumber;
    }

    private static double getRoomPrice(Scanner scanner){
        double roomPrice = 0;
        boolean validRoomPrice = false;
        while(!validRoomPrice){
            try{
                System.out.println("Enter room price");
                roomPrice = scanner.nextDouble();
                scanner.nextLine();
                if(roomPrice < 0)
                    throw new IllegalStateException("Room price must be positive!");
                validRoomPrice = true;
            } catch (Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                System.out.println();
            }
        }
        return roomPrice;
    }

    private static RoomType getRoomType(Scanner scanner){
        Printer printer = new OptionsPrinter();
        RoomType roomType = null;
        int roomTypeInt = 0;
        boolean validRoomType = false;
        while(!validRoomType){
            try{
                printer.print(roomTypes);
                roomTypeInt = UserInput.scanInput(scanner, roomTypes.size(), "Enter Room type (1-2)", false);
                if(roomTypeInt == 1)
                    roomType = RoomType.SINGLE;
                else
                    roomType = RoomType.DOUBLE;
                validRoomType = true;
            } catch (Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                System.out.println();
            }
        }
        return roomType;
    }

    public static int getMenuItemsSize(){
        return menuItems.size();
    }

}
