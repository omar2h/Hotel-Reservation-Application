package frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import api.HotelResource;
import exceptions.EmailNotFoundException;
import exceptions.InvalidEmailException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import util.UserInput;
import util.printer.MenuPrinter;
import util.printer.OptionsPrinter;
import util.printer.Printer;
import util.validator.DateValidator;
import util.validator.StringValidator;
import util.validator.Validator;

public class MainMenu {
    private static final String DATE_REGEX = "^[0-3]\\d/[0-1][0-2]/\\d\\d\\d\\d$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)\\.(.+)$";
    private static final String ynRegex = "^[yYnN]$";
    private static final int CANCEL = -1;
    private static final List<String> yesNoAnswers = new ArrayList<>(Arrays.asList("y", "n", "Y", "N"));
    private static HotelResource hotelResource = HotelResource.getHotelResource();
    private static Printer printer;
    private static Validator validator;
    private static List<Object> menuItems = new ArrayList<>(Arrays.asList("Find and reserve a room",
                                                                            "See my reservations",
                                                                            "Create an account",
                                                                            "Admin",
                                                                            "Exit"));
    private MainMenu(){}
    public static void display(){
        printer = new MenuPrinter();
        printer.print(menuItems);
    }

    public static int getInput(Scanner scanner, int upperLimit) throws IllegalArgumentException {
        return UserInput.scanInput(scanner, upperLimit, "", false);
    }

    public static void run(Scanner scanner, int option){
        switch (option) {
            case 1:
                reserve(scanner);
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
        
            default:
                break;
        }
    }

    public static void reserve(Scanner scanner) throws IllegalArgumentException {
        boolean cusotmerWantsToBook = false;
        IRoom roomToReserve;
        String customerEmail;
        Reservation reservation;

        // 1. get checkIn and checkOut dates
        LocalDate[] dates = getDates(scanner);
        // 2. get available rooms according to the dates
        Collection<IRoom> availableRooms = hotelResource.findARoom(dates[0], dates[1]);
        if(availableRooms.isEmpty()){
            System.out.println("No rooms available");
            return;
        }
        // 3. display availableRooms and ask customer whether wants to book or not
        cusotmerWantsToBook = askCustomerToBook(scanner, availableRooms);
        if(!cusotmerWantsToBook)
            return;
        // 4. get customer email address
        customerEmail = getCustomerEmail(scanner);
        // 5. get room to reserve
        roomToReserve = getRoom(scanner, availableRooms);

        try{
            reservation = hotelResource.bookARoom(customerEmail, roomToReserve, dates[0], dates[1]);
            System.out.println(reservation);
        } catch(Exception e){
            System.out.println();
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static int getMenuItemsSize(){
        return menuItems.size();
    }

    private static LocalDate getDate(Scanner scanner, String dateName) throws IllegalArgumentException {
        String date;
        System.out.println("Enter "+ dateName + " Date in this format dd/mm/yyyy. Example 01/01/2020");
        date = scanner.nextLine();
        validator = new DateValidator();
        if(!validator.isValid(date, DATE_REGEX))
            throw new IllegalArgumentException("Enter Date in this format dd/mm/yyyy. Example 01/01/2020");

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, f);
    }

    private static void displayAvailableRooms(Collection<IRoom> rooms){
        printer = new OptionsPrinter();
        List<Object> availableRoomsStr = new ArrayList<>();
        for(IRoom room: rooms)
            availableRoomsStr.add(room.toString());
        printer.print(availableRoomsStr);
    }

    private static String getCustomerEmail(Scanner scanner) {
        boolean validEmail = false;
        Customer customer;
        String email = "", firstName, lastName;
        validator = new StringValidator();
        String ans;
        
        while(true){
            try {
                ans = UserInput.scanInput(scanner, yesNoAnswers, "Do you have an account with us? y/n", "Enter y or n");
                while(!validEmail){
                    try{
                        System.out.println("Enter your E-mail");
                        email = scanner.nextLine().trim().toLowerCase();
                        if(!validator.isValid(email, EMAIL_REGEX))
                            throw new InvalidEmailException("Invalid email");
                        validEmail = true;
                    }catch(Exception e){
                        System.out.println();
                        System.out.println(e.getLocalizedMessage());
                    }
                }
                if(ans.equalsIgnoreCase("y"))
                    customer = hotelResource.getCustomer(email);
                else{
                    System.out.println("Enter your First Name");
                    firstName = scanner.nextLine().trim();

                    System.out.println("Enter your Last Name");
                    lastName = scanner.nextLine().trim();
                    hotelResource.createACustomer(email, firstName, lastName);
                }
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }  
        }
        return email;
    }

    private static LocalDate[] getDates(Scanner scanner) {
        boolean validCheckInDate = false;
        boolean validCheckOutDate = false;
        LocalDate checkInDate = null, checkOutDate = null; 
        while(true){
            try{
                while(!validCheckInDate){
                    checkInDate = getDate(scanner, "Check-In");
                    validCheckInDate = true;
                }
                while(!validCheckOutDate){
                    checkOutDate = getDate(scanner, "Check-Out");
                    if(checkOutDate.isBefore(checkInDate))
                        throw new IllegalArgumentException("Check-Out Date should be set after Check-In Date");
                    validCheckOutDate =  true;
                }
                break;
            }catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }
        }
        return new LocalDate[] {checkInDate, checkOutDate};
    }

    private static boolean askCustomerToBook(Scanner scanner, Collection<IRoom> rooms){
        String ans;
        boolean cusotmerWantsToBook = false;
        displayAvailableRooms(rooms);
        while(true){
            try{
                ans = UserInput.scanInput(scanner, yesNoAnswers, "Would you like to book a room? y/n", "Enter y or n");
                if(ans.equalsIgnoreCase("y"))
                    cusotmerWantsToBook = true;
                else
                    cusotmerWantsToBook = false;
                break;
            }catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }
        }
        return cusotmerWantsToBook;
    }

    private static IRoom getRoom(Scanner scanner, Collection<IRoom> rooms) throws IllegalArgumentException {
        IRoom room;
        int roomChoice = 0;
        List<IRoom> roomsList = new ArrayList<>(rooms);
        while(true){
            try{
                roomChoice = UserInput.scanInput(scanner, rooms.size(), "Select a room (1-" + roomsList.size() + ") or -1 to cancel", true);
                break;
            }catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }
        }
        return roomsList.get(roomChoice-1);
    }
}
