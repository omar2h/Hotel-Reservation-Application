package api;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.HashMap;

import exceptions.EmailNotFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {
    private static HotelResource hotelResourceInstance;
    private static ReservationService reservationService = ReservationService.getReservationService();
    private static CustomerService customerService = CustomerService.getCustomerService();

    private HotelResource(){}

    public static HotelResource getHotelResource(){
        if(hotelResourceInstance == null)
            hotelResourceInstance = new HotelResource();
        return hotelResourceInstance;
    }

    public Customer getCustomer(String email) throws EmailNotFoundException {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) throws IllegalArgumentException {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail,
                                IRoom room,
                                LocalDate checkInDate,
                                LocalDate checkOutDate) throws EmailNotFoundException {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) throws EmailNotFoundException {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }

    public Collection<IRoom> findARoom(LocalDate checkInDate, LocalDate checkOuDate){
        return reservationService.findRooms(checkInDate, checkOuDate);
    }
}
